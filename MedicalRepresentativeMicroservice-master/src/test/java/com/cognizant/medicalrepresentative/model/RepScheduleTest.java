package com.cognizant.medicalrepresentative.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;

@SpringBootTest
public class RepScheduleTest {

	private RepSchedule repSchedule;
	
	@Before
	public void setup() {
		
		repSchedule = new RepSchedule();
		repSchedule.setId(1);
		repSchedule.setDoctorName("Raj");
		repSchedule.setDoctorContactNumber("8080080880");
		repSchedule.setMeetingDate(LocalDate.now());
		repSchedule.setMeetingSlot("1 PM to 2 PM");
		//repSchedule.setRepName(repName);
	}
	
	@Test
	public void testMedicineStockDetails() throws Exception {
		//log.info("Start");
		assertEquals(1, repSchedule.getId());
		assertEquals("Raj", repSchedule.getDoctorName());
		assertEquals("8080080880", repSchedule.getDoctorContactNumber());

		//log.info("End");

	}
}
