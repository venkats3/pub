package com.demo.enrollment.membership.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.enrollment.membership.entity.MemberMaster;
import com.demo.enrollment.membership.services.MembershipService;

@Component
public class MemberFacade {
	@Autowired
	private MembershipService membershipService;

	@Autowired
	private ModelMapper modelMapper;
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

	private static String toStringDate(Calendar calendar) {
		if (calendar != null) {
			return dateFormatter.format(calendar.getTime());
		}
		return null;
	}

	private static Calendar toCalendar(String dateString) {
		if (dateString != null) {
			try {
				Date utilDate = dateFormatter.parse(dateString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(utilDate);
				return cal;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public MemberMasterDto getMemberBySubscriptionId(String id) {
		MemberMaster foundMemberMaster = membershipService.findMemberBySubscriptionId(id);
		if (foundMemberMaster != null) {
			return convertToMemberMasterDto(foundMemberMaster);
		} else {
			return null;
		}
	}

	public MemberMasterDto getMemberBySubscriptionIdAndMemberId(String subscriberId, String memberId) {
		MemberMaster foundMemberMaster = membershipService.getMemberBySubscriptionIdAndMemberId(subscriberId, memberId);
		if (foundMemberMaster != null) {
			return convertToMemberMasterDto(foundMemberMaster);
		} else {
			return null;
		}
	}

	private MemberMasterDto convertToMemberMasterDto(MemberMaster memberMaster) {
		// MemberMasterDto orderDto = modelMapper.map(order, MemberMasterDto.class);
		MemberMasterDto memberDto = new MemberMasterDto();
		memberDto.setActivationStatus(memberMaster.getActivationStatus());
		memberDto.setDateOfBirth(toStringDate(memberMaster.getDateOfBirth()));
		memberDto.setFirstName(memberMaster.getFirstName());
		memberDto.setLastName(memberMaster.getLastName());
		memberDto.setPhoneNumber(memberMaster.getPhoneNumber());
		memberDto.setSubscriptionId(memberMaster.getSubscriptionId());
		memberDto.setMemberId(memberMaster.getMemberId());
		memberDto.setMembershipStatus(memberMaster.getMembershipStatus());
		memberDto.setSubscriptionSeqId(memberMaster.getSubscriptionSeqId());
		memberDto.setRequestedEffectiveDate(toStringDate(memberMaster.getRequestedEffectiveDate()));
		memberDto.setDeletionDate(toStringDate(memberMaster.getDeletionDate()));
		return memberDto;
	}

	private MemberMaster convertToMemberMaster(MemberMasterDto memberMasterDto) {
		MemberMaster memberMaster = new MemberMaster();
		memberMaster.setActivationStatus(memberMasterDto.getActivationStatus());
		memberMaster.setDateOfBirth(toCalendar(memberMasterDto.getDateOfBirth()));
		memberMaster.setFirstName(memberMasterDto.getFirstName());
		memberMaster.setLastName(memberMasterDto.getLastName());
		memberMaster.setPhoneNumber(memberMasterDto.getPhoneNumber());
		memberMaster.setSubscriptionId(memberMasterDto.getSubscriptionId());
		memberMaster.setMemberId(memberMasterDto.getMemberId());
		memberMaster.setMembershipStatus(memberMasterDto.getMembershipStatus());
		memberMaster.setSubscriptionSeqId(memberMasterDto.getSubscriptionSeqId());
		memberMaster.setRequestedEffectiveDate(toCalendar(memberMasterDto.getRequestedEffectiveDate()));
		memberMaster.setDeletionDate(toCalendar(memberMasterDto.getDeletionDate()));
		return memberMaster;
	}

	public MemberMasterDto updateMember(MemberMasterDto reqMemberMasterDto) {
		MemberMaster memberMasterEntity = convertToMemberMaster(reqMemberMasterDto);// modelMapper.map(reqMemberMasterDto,
																					// MemberMaster.class);
		MemberMaster updatedMember = membershipService.updateMember(memberMasterEntity);
		MemberMasterDto updateMemberMasterDto = convertToMemberMasterDto(updatedMember);
		return updateMemberMasterDto;
	}

	public MemberMasterDto createMember(MemberMasterDto reqMemberMasterDto) {
		// MemberMaster memberMasterEntity = modelMapper.map(reqMemberMasterDto,
		// MemberMaster.class);
		MemberMaster memberMasterEntity = convertToMemberMaster(reqMemberMasterDto);
		MemberMaster createdMember = membershipService.createMember(memberMasterEntity);
		MemberMasterDto createdMemberDto = convertToMemberMasterDto(createdMember);
		return createdMemberDto;
	}

	public MemberMasterDto deleteSubscription(MemberMasterDto memberMasterDto, String asOfDate) {
		Calendar asOfDateCal = toCalendar(asOfDate);
		MemberMaster memberMasterEntity = convertToMemberMaster(memberMasterDto);
		MemberMaster updatedMember = membershipService.deleteSubscription(memberMasterEntity, asOfDateCal);
		MemberMasterDto updatedMemberMasterDto = convertToMemberMasterDto(updatedMember);
		return updatedMemberMasterDto;
	}

	public MemberMasterDto deleteMember(MemberMasterDto memberMasterDto, String asOfDate) {
		Calendar asOfDateCal = toCalendar(asOfDate);
		MemberMaster memberMasterEntity = convertToMemberMaster(memberMasterDto);
		MemberMaster updatedMember = membershipService.deleteMember(memberMasterEntity, asOfDateCal);
		MemberMasterDto updatedMemberMasterDto = convertToMemberMasterDto(updatedMember);
		return updatedMemberMasterDto;
	}
}
