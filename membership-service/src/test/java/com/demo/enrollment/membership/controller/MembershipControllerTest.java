package com.demo.enrollment.membership.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.enrollment.MembershipApplication;
import com.demo.enrollment.membership.dto.MemberMasterDto;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MembershipApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc

class MembershipControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int localServerPort;

	private String getApplicationRootUrl() {
		return "http://localhost:" + localServerPort;
	}

	private boolean debug = log.isDebugEnabled();

	@Test
	public void testNewSubscriber() throws Exception {

		String newSubscriberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/newSubscriber";
		if (debug)
			log.debug(newSubscriberUrl);
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus("Y");
		memberDto.setDateOfBirth("01/01/1950");
		memberDto.setFirstName("TEST FIRST");
		memberDto.setLastName("TEST LAST");
		memberDto.setPhoneNumber("123456");
		memberDto.setSubscriptionId("A1234500");
		memberDto.setMemberId("A1234500");
		memberDto.setMembershipStatus("Y");
		memberDto.setRequestedEffectiveDate("10/01/2020");
		memberDto.setDeletionDate(null);

		ResponseEntity<MemberMasterDto> responseEntity = testRestTemplate.postForEntity(newSubscriberUrl, memberDto,
				MemberMasterDto.class);

		if (debug)
			log.debug("StatusCode: " + responseEntity.getStatusCode());
		if (debug)
			log.debug("responseEntity.getBody(): " + responseEntity.getBody());

		String getSubscriberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500";

		ResponseEntity<MemberMasterDto> createdResponseEntity = testRestTemplate.getForEntity(getSubscriberUrl,
				MemberMasterDto.class);
		assertThat(createdResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(createdResponseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}

	@Test
	public void testNewMember() {
		String newMemberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/newMember";
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus("Y");
		memberDto.setDateOfBirth("01/01/1960");
		memberDto.setFirstName("SPOUSE FIRST");
		memberDto.setLastName("SPOUSE LAST");
		memberDto.setPhoneNumber("123456");
		memberDto.setSubscriptionId("A1234500");
		memberDto.setMemberId("A1234501");
		memberDto.setMembershipStatus("Y"); //
		memberDto.setRequestedEffectiveDate("10/01/2020");
		memberDto.setDeletionDate(null);
		ResponseEntity<MemberMasterDto> responseEntity = testRestTemplate.postForEntity(newMemberUrl, memberDto,
				MemberMasterDto.class);

		if (debug)
			log.debug("StatusCode: " + responseEntity.getStatusCode());
		if (debug)
			log.debug("responseEntity.getBody(): " + responseEntity.getBody());

		String getMemberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500/A1234501";

		ResponseEntity<MemberMasterDto> createdResponseEntity = testRestTemplate.getForEntity(getMemberUrl,
				MemberMasterDto.class);
		assertThat(createdResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(createdResponseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}

	@Test
	public void testFindSubscriberById() {
		String findSubscriberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500";

		ResponseEntity<MemberMasterDto> responseEntity = testRestTemplate.getForEntity(findSubscriberUrl,
				MemberMasterDto.class);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}

	@Test
	public void testFindMemberById() {
		String findMemberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500/A1234501";

		ResponseEntity<MemberMasterDto> responseEntity = testRestTemplate.getForEntity(findMemberUrl,
				MemberMasterDto.class);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getSubscriptionSeqId()).isNotNull();
	}

	@Test
	public void testUpdateSubscriber() {
		String getUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500";

		ResponseEntity<MemberMasterDto> initGetResponseEntity = testRestTemplate.getForEntity(getUrl,
				MemberMasterDto.class);
		assertThat(initGetResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(initGetResponseEntity.getBody().getSubscriptionSeqId()).isNotNull();
		assertThat(initGetResponseEntity.getStatusCodeValue()).isEqualTo(200);
		MemberMasterDto getMemberDto = initGetResponseEntity.getBody();
		assertThat(getMemberDto.getSubscriptionSeqId()).isNotNull();

		String updateUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/updateSubscriber";
		getMemberDto.setFirstName("TEST FIRST UPDATE");
		testRestTemplate.put(updateUrl, getMemberDto);

		ResponseEntity<MemberMasterDto> updatedResponseEntity = testRestTemplate.getForEntity(getUrl,
				MemberMasterDto.class);
		assertThat(updatedResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(updatedResponseEntity.getBody().getFirstName()).isNotNull().isEqualTo("TEST FIRST UPDATE");
		assertThat(updatedResponseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testUpdateMember() {
		String getUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500/A1234501";

		ResponseEntity<MemberMasterDto> initGetResponseEntity = testRestTemplate.getForEntity(getUrl,
				MemberMasterDto.class);
		assertThat(initGetResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(initGetResponseEntity.getBody().getSubscriptionSeqId()).isNotNull();

		MemberMasterDto getMemberDto = initGetResponseEntity.getBody();
		assertThat(getMemberDto.getSubscriptionSeqId()).isNotNull();

		String updateUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/updateMember";
		getMemberDto.setFirstName("Spouse FIRST UPDATE");
		testRestTemplate.put(updateUrl, getMemberDto);

		ResponseEntity<MemberMasterDto> updatedResponseEntity = testRestTemplate.getForEntity(getUrl,
				MemberMasterDto.class);
		assertThat(updatedResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(updatedResponseEntity.getBody().getFirstName()).isNotNull().isEqualTo("Spouse FIRST UPDATE");
	}

	@Test
	public void testDeleteMember() {
		String getMemberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500/A1234501";

		String deleteUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/deleteMember/A1234500/A1234501";

		testRestTemplate.delete(deleteUrl);

		ResponseEntity<MemberMasterDto> updatedResponseEntity = testRestTemplate.getForEntity(getMemberUrl,
				MemberMasterDto.class);
		assertThat(updatedResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(updatedResponseEntity.getBody().getDeletionDate()).isNotNull();

	}

	@Test
	public void testDeleteSubscriber() {

		String getSubscriberUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/A1234500";

		String deleteUrl = getApplicationRootUrl() + "/enrollment/api/v1/members/deleteSubscriber/A1234500";

		testRestTemplate.delete(deleteUrl);

		ResponseEntity<MemberMasterDto> updatedResponseEntity = testRestTemplate.getForEntity(getSubscriberUrl,
				MemberMasterDto.class);
		assertThat(updatedResponseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(updatedResponseEntity.getBody().getDeletionDate()).isNotNull();

	}

}
