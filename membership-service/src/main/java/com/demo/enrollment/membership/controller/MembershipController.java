package com.demo.enrollment.membership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.enrollment.membership.dto.MemberFacade;
import com.demo.enrollment.membership.dto.MemberMasterDto;
import com.demo.enrollment.membership.validation.MemberException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/members")
@Slf4j
public class MembershipController {

	@Autowired
	private MemberFacade memberFacade;

	@GetMapping("/{subscriptionId}")
	public @ResponseBody ResponseEntity<MemberMasterDto> findMemberById(@PathVariable String subscriptionId) {
		MemberMasterDto memberMasterDto = memberFacade.getMemberBySubscriptionId(subscriptionId);
		if (memberMasterDto == null) {
			throw new MemberException("Subscriber with Subscription id [" + subscriptionId + "] doesn't exist");
		} else {
			return ResponseEntity.ok(memberMasterDto);
		}

	}

	@GetMapping("/{subscriptionId}/{memberId}")
	public @ResponseBody ResponseEntity<MemberMasterDto> findMemberById(@PathVariable String subscriptionId,
			@PathVariable String memberId) {
		MemberMasterDto memberMasterDto = memberFacade.getMemberBySubscriptionIdAndMemberId(subscriptionId, memberId);
		if (memberMasterDto == null) {
			throw new MemberException("Member with Subscription id [" + subscriptionId + " ] and Member id [ "
					+ memberId + " ]doesn't exist");
		} else {
			return ResponseEntity.ok(memberMasterDto);
		}

	}

	@PostMapping(value = "/newSubscriber", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newSubscriber(@RequestBody MemberMasterDto reqMemberMasterDto,
			UriComponentsBuilder ucBuilder) {

		MemberMasterDto memberMasterDto = memberFacade
				.getMemberBySubscriptionId(reqMemberMasterDto.getSubscriptionId());

		if (memberMasterDto != null) {
			// memberMasterDto = memberFacade.updateMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			reqMemberMasterDto.setResponseStatus("Member Already found - Cannot create");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(reqMemberMasterDto);
		} else {
			memberMasterDto = memberFacade.createMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			memberMasterDto.setResponseStatus("New Member Created");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(memberMasterDto);
		}

	}

	@PutMapping(value = "/updateSubscriber", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSubscriber(@RequestBody MemberMasterDto reqMemberMasterDto,
			UriComponentsBuilder ucBuilder) {
		MemberMasterDto memberMasterDto = null;
		if (reqMemberMasterDto.getSubscriptionId().equals(reqMemberMasterDto.getMemberId())) {

			memberMasterDto = memberFacade.getMemberBySubscriptionId(reqMemberMasterDto.getSubscriptionId());
		} else {
			HttpHeaders headers = new HttpHeaders();
			reqMemberMasterDto.setResponseStatus("SubscriptionId and Member Id should be the same, please validate");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(reqMemberMasterDto);
		}
		if (memberMasterDto != null) {
			memberMasterDto = memberFacade.updateMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			memberMasterDto.setResponseStatus("Member Updated");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(memberMasterDto);
		} else {
			// memberMasterDto = memberFacade.createMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			reqMemberMasterDto.setResponseStatus("Member data Not found to update");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(reqMemberMasterDto);
		}

	}

	@PostMapping(value = "/newMember", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newMember(@RequestBody MemberMasterDto reqMemberMasterDto,
			UriComponentsBuilder ucBuilder) {

		MemberMasterDto memberMasterDto = memberFacade.getMemberBySubscriptionIdAndMemberId(
				reqMemberMasterDto.getSubscriptionId(), reqMemberMasterDto.getMemberId());

		if (memberMasterDto != null) {
			// memberMasterDto = memberFacade.updateMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			reqMemberMasterDto.setResponseStatus("Member Already found - Cannot create");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(reqMemberMasterDto);
		} else {
			memberMasterDto = memberFacade.createMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			memberMasterDto.setResponseStatus("New Member Created");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(memberMasterDto);
		}

	}

	@PutMapping(value = "/updateMember", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMember(@RequestBody MemberMasterDto reqMemberMasterDto,
			UriComponentsBuilder ucBuilder) {

		MemberMasterDto memberMasterDto = memberFacade.getMemberBySubscriptionIdAndMemberId(
				reqMemberMasterDto.getSubscriptionId(), reqMemberMasterDto.getMemberId());

		if (memberMasterDto != null) {
			memberMasterDto = memberFacade.updateMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			memberMasterDto.setResponseStatus("Member data updated");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(memberMasterDto);
		} else {
			// memberMasterDto = memberFacade.createMember(reqMemberMasterDto);
			HttpHeaders headers = new HttpHeaders();
			reqMemberMasterDto.setResponseStatus("Member Not found, please check the data");
			headers.setLocation(
					ucBuilder.path("/api/members/{id}").buildAndExpand(reqMemberMasterDto.getSubscriptionId()).toUri());
			return ResponseEntity.ok(reqMemberMasterDto);
		}

	}

	@DeleteMapping(value = "/deleteSubscriber/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteSubscription(@PathVariable String subscriptionId, UriComponentsBuilder ucBuilder) {

		MemberMasterDto memberMasterDto = memberFacade.getMemberBySubscriptionId(subscriptionId);

		if (memberMasterDto != null) {
			memberMasterDto = memberFacade.deleteSubscription(memberMasterDto, null);

			memberMasterDto.setResponseStatus("Subscriber Terminated");

			return ResponseEntity.ok(memberMasterDto);
		} else {
			return ResponseEntity.ok("error: Subscriber Not Found");
		}

	}

	@DeleteMapping(value = "/deleteMember/{subscriptionId}/{memberId}")
	public ResponseEntity<?> deleteMember(@PathVariable String subscriptionId, @PathVariable String memberId,
			UriComponentsBuilder ucBuilder) {

		MemberMasterDto memberMasterDto = memberFacade.getMemberBySubscriptionIdAndMemberId(subscriptionId, memberId);

		if (memberMasterDto != null) {
			memberMasterDto = memberFacade.deleteMember(memberMasterDto, null);

			memberMasterDto.setResponseStatus("Member Terminated");

			return ResponseEntity.ok(memberMasterDto);
		} else {
			return ResponseEntity.ok("error: Member Not Found");
		}

	}
}
