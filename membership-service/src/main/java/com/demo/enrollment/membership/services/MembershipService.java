package com.demo.enrollment.membership.services;

import java.util.Calendar;

import com.demo.enrollment.membership.entity.MemberMaster;
import com.demo.enrollment.membership.validation.MemberException;

public interface MembershipService {
	public MemberMaster findMemberBySubscriptionId(String id);

	public MemberMaster getMemberBySubscriptionIdAndMemberId(String subscriberId, String memberId);

	public MemberMaster updateMember(MemberMaster memberMaster) throws MemberException;

	public MemberMaster createMember(MemberMaster memberMasterEntity) throws MemberException;

	public MemberMaster deleteMember(MemberMaster memberMaster, Calendar asOfDate) throws MemberException;

	public MemberMaster deleteSubscription(MemberMaster memberMaster, Calendar asOfDate) throws MemberException;

}
