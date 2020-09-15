package com.demo.enrollment.membership.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.enrollment.membership.dto.MemberFacade;
import com.demo.enrollment.membership.dto.MemberMasterDto;

@RunWith (SpringRunner.class)
@WebMvcTest(value = MembershipController.class)

class MembershipControllerTest {

	@MockBean
	private MemberFacade memberFacade;
	
	@Autowired
	MembershipController membershipController;
	
	@Test
	public void testNewSubscriber() {
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus("Y");
		memberDto.setDateOfBirth("01/01/1950");
		memberDto.setFirstName("TEST FIRST");
		memberDto.setLastName("TEST LAST");
		memberDto.setPhoneNumber("123456");
		memberDto.setSubscriptionId("A1234500");
		memberDto.setMemberId("A1234500");
		memberDto.setMembershipStatus("Y");
		//memberDto.setSubscriptionSeqId(memberMaster.getSubscriptionSeqId());
		memberDto.setRequestedEffectiveDate("10/01/2020");
		memberDto.setDeletionDate(null);
		ResponseEntity<MemberMasterDto> responseEntity = membershipController.newSubscriber(memberDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}
	
	@Test
	public void testNewMember() {
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus("Y");
		memberDto.setDateOfBirth("01/01/1960");
		memberDto.setFirstName("SPOUSE FIRST");
		memberDto.setLastName("SPOUSE LAST");
		memberDto.setPhoneNumber("123456");
		memberDto.setSubscriptionId("A1234500");
		memberDto.setMemberId("A1234501");
		memberDto.setMembershipStatus("Y");
		//memberDto.setSubscriptionSeqId(memberMaster.getSubscriptionSeqId());
		memberDto.setRequestedEffectiveDate("10/01/2020");
		memberDto.setDeletionDate(null);
		ResponseEntity<MemberMasterDto> responseEntity = membershipController.newMember(memberDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}
	@Test
	public void testFindSUbscriberById() {
		ResponseEntity<MemberMasterDto> responseEntity = membershipController.findMemberById("A1234500");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}
	@Test
	public void testFindMemberById() {
		ResponseEntity<MemberMasterDto> responseEntity = membershipController.findMemberById("A1234500", "A1234501");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}
	@Test
	public void testUpdateSubscriber() {
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus("Y");
		memberDto.setDateOfBirth("01/01/1950");
		memberDto.setFirstName("TEST FIRST");
		memberDto.setLastName("TEST LAST");
		memberDto.setPhoneNumber("123456");
		memberDto.setSubscriptionId("A1234500");
		memberDto.setMemberId("A1234500");
		memberDto.setMembershipStatus("Y");
		memberDto.setSubscriptionSeqId(1L);
		memberDto.setRequestedEffectiveDate("10/01/2020");
		memberDto.setDeletionDate(null);
		ResponseEntity<MemberMasterDto> responseEntity = membershipController.updateSubscriber(memberDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}
	
	@Test
	public void testUpdateMember() {
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus("Y");
		memberDto.setDateOfBirth("01/01/1960");
		memberDto.setFirstName("Spose FIRST");
		memberDto.setLastName("Spouse LAST");
		memberDto.setPhoneNumber("123456");
		memberDto.setSubscriptionId("A1234500");
		memberDto.setMemberId("A1234501");
		memberDto.setMembershipStatus("Y");
		memberDto.setSubscriptionSeqId(2L);
		memberDto.setRequestedEffectiveDate("10/01/2020");
		memberDto.setDeletionDate(null);
		ResponseEntity<MemberMasterDto> responseEntity = membershipController.updateSubscriber(memberDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}
	
	@Test
	public void testDeleteSubscriber() {
		ResponseEntity responseEntity = membershipController.deleteSubscription("1");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(((MemberMasterDto)responseEntity.getBody()).getDeletionDate()).isNotNull();
	}
	
	@Test
	public void testDeleteMember() {
		ResponseEntity responseEntity = membershipController.deleteMember("A1234500", "A1234501");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(((MemberMasterDto)responseEntity.getBody()).getDeletionDate()).isNotNull();
	}	
}
