package com.jmamanig.personal.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@Table(name="personal_data")
public class PersonalData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "person_id")
	private int personId;
	private String name;
	private String email;
	@Column(name = "mobile_number")
	private String mobileNumber;
	private String address;
	
}
