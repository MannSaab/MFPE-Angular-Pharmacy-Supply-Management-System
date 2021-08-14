package com.cognizant.medicalrepresentativeschedule.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.feignclient.MedicineStockFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.util.CsvParseUtil;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedRepScheduleServiceImpl implements MedRepScheduleService {

	@Autowired
	private MedicineStockFeignClient medicineStockClient;


	@Autowired
	AuthenticationFeignClient authFeignClient;

	@Override
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate) throws TokenValidationFailedException {
		log.info("Start");

		if (!isValidSession(token)) {
			log.info("End");
			
			return null;
		}

		List<RepSchedule> repSchedules = new ArrayList<>();

		List<Doctor> doctors = CsvParseUtil.parseDoctors();
		
		log.debug("docters : {}", doctors);
		List<String> medicalRepresentatives = null;
		try {
			
			medicalRepresentatives = authFeignClient.allUserDetails();
			log.debug("medicalRepresentatives : {}", medicalRepresentatives);
		}
		catch(FeignException e) {
			log.error("error-->",e);
		}
		

		LocalDate localDate = scheduleStartDate;

		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);

		LocalDate today = LocalDate.now();
		if (scheduleStartDate.isBefore(today)) {

			log.info("End");
			return repSchedules;
		}

		if (scheduleStartDate.equals(today)) {

			if (now.isAfter(one)) {
				localDate = localDate.plusDays(1);
			}

		}

		for (int i = 0; i < doctors.size(); i++) {

			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				localDate = localDate.plusDays(1);
			}

			Doctor doctor = doctors.get(i);
			String medicalRepresentative = medicalRepresentatives.get(i % medicalRepresentatives.size());

			RepSchedule repSchedule = new RepSchedule();
			repSchedule.setId(i + 1);
			repSchedule.setRepName(medicalRepresentative);
			repSchedule.setDoctorName(doctor.getName());
			repSchedule.setDoctorContactNumber(doctor.getContactNumber());
			repSchedule.setMeetingDate(localDate);
			repSchedule.setMeetingSlot(doctor.getTime());
			repSchedule.setTreatingAilment(doctor.getTreatingAilment());
			
			List<String> medicinesByTreatingAilment =  medicineStockClient.getMedicineByAilment(doctor.getTreatingAilment());
			
			//System.out.println(((HttpEntity<?>) medicineStockClient.getMedicineByAilment(doctor.getTreatingAilment())).getBody());
			
			repSchedule.setMedicines(medicinesByTreatingAilment);

			log.debug("repSchedule : {}", repSchedule);

			repSchedules.add(repSchedule);

			localDate = localDate.plusDays(1);
		}

		log.debug("repSchedules : {}", repSchedules);

		log.info("End");
		return repSchedules;
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		
		log.info("Start");
		
		JwtResponse response = authFeignClient.verifyToken(token);
		//if (!response.isValid()) {
		//	log.info("End");

		//	throw new TokenValidationFailedException("Invalid Token");
		//}

		log.info("End");
		return true;
	}
	
	

}
