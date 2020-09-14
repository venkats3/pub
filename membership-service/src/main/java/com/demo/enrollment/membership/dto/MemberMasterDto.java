package com.demo.enrollment.membership.dto;

import java.util.Calendar;

import lombok.Data;

@Data
public class MemberMasterDto {
	private Long subscriptionSeqId;
	private String subscriptionId;
	private String memberId;
	private String membershipStatus;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String activationStatus;
	private String phoneNumber;
	private String responseStatus;
	private String requestedEffectiveDate;
	private String deletionDate;
}
