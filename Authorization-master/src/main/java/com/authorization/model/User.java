package com.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" })
		 })
@Getter
@Setter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(length = 20)
	private String name;
	
	@Column(length = 20)
	private String username;
	
	private String password;

	public User(String name, String username,String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	}

}