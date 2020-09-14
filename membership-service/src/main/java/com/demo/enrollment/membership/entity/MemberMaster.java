package com.demo.enrollment.membership.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER_MASTER")
public class MemberMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subscriptionSeqId;

	@Column(nullable = false)
	private String subscriptionId;

	@Column(nullable = false, unique = true)
	private String memberId;

	@Column(nullable = false)
	private String membershipStatus;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private Calendar dateOfBirth;

	@Column(nullable = false)
	private String activationStatus;

	@Column
	private String phoneNumber;

	@Column(nullable = false)
	private Calendar requestedEffectiveDate;

	@Column
	private Calendar deletionDate;

	@Column
	private Calendar insertDateTime;

	@Column
	private String insertProcess;

	@Column
	private String insertUser;

	@Column
	private Calendar updateDateTime;

	@Column
	private String updateUser;

	@Column
	private String updateProcess;

}
