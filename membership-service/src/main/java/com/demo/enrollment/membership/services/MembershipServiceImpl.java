package com.demo.enrollment.membership.services;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.enrollment.membership.entity.MemberMaster;
import com.demo.enrollment.membership.repository.MembershipRepository;
import com.demo.enrollment.membership.validation.MemberException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MembershipServiceImpl implements MembershipService {
	private final MembershipRepository membershipRepository;

	@Autowired
	public MembershipServiceImpl(MembershipRepository membershipRepository) {
		this.membershipRepository = membershipRepository;
	}

	public MemberMaster findMemberBySubscriptionId(String id) {
		return membershipRepository.findBySubscriptionIdAndMemberId(id, id);
	}

	public MemberMaster getMemberBySubscriptionIdAndMemberId(String subscriberId, String memberId) {
		return membershipRepository.findBySubscriptionIdAndMemberId(subscriberId, memberId);
	}

	public MemberMaster updateMember(MemberMaster memberMaster) throws MemberException {
		if (memberMaster.getSubscriptionId() != null) {
			MemberMaster dbMemberMaster = membershipRepository.findBySubscriptionIdAndMemberId(
					memberMaster.getSubscriptionId(), memberMaster.getSubscriptionId());
			if (dbMemberMaster != null) {
				try {
					addAuditing(memberMaster);
					MemberMaster updatedMemberMaster = membershipRepository.save(memberMaster);
					return updatedMemberMaster;
				} catch (Exception ex) {
					throw new MemberException("Unable to update member with " + memberMaster.getSubscriptionId()
							+ ", please check the subscription Id, Subscriber Id and Member Id for correctness");
				}
			}
		}
		return null;
		/*
		 * throw new MemberException( "Member with subscription id " +
		 * memberMaster.getSubscriptionId() + " was not found to update");
		 */
	}

	private void addAuditing(MemberMaster memberMaster) {
		Calendar currentDateTime = Calendar.getInstance();
		if (memberMaster.getInsertDateTime() == null) {
			memberMaster.setInsertDateTime(currentDateTime);
			memberMaster.setInsertUser("RESTFUL");
			memberMaster.setInsertProcess("RESTFULAPP");
		}

		memberMaster.setUpdateDateTime(currentDateTime);
		memberMaster.setUpdateUser("RESTFUL");
		memberMaster.setUpdateProcess("RESTFULAPP");

	}

	public MemberMaster createMember(MemberMaster memberMasterEntity) {
		addAuditing(memberMasterEntity);
		return membershipRepository.saveAndFlush(memberMasterEntity);
	}

	public MemberMaster deleteSubscription(MemberMaster memberMaster, Calendar asOfDate) throws MemberException {
		if (asOfDate == null) {
			asOfDate = Calendar.getInstance();
		}
		if (memberMaster.getSubscriptionId() != null) {
			List<MemberMaster> dbMemberMasterList = membershipRepository
					.findBySubscriptionId(memberMaster.getSubscriptionId());
			if (dbMemberMasterList != null) {
				try {
					MemberMaster subscriber = null;
					for (MemberMaster dbMemberMaster : dbMemberMasterList) {
						dbMemberMaster.setDeletionDate(asOfDate);
						dbMemberMaster.setMembershipStatus("N");
						addAuditing(dbMemberMaster);
						MemberMaster updatedMemberMaster = membershipRepository.save(dbMemberMaster);
						if (updatedMemberMaster.getSubscriptionId().equals(updatedMemberMaster.getMemberId())) {
							subscriber = updatedMemberMaster;
						}
					}
					return subscriber;

				} catch (Exception ex) {
					throw new MemberException("Unable to update member with " + memberMaster.getSubscriptionId()
							+ ", please check the subscription Id, Subscriber Id and Member Id for correctness");
				}
			}
		}
		return null;
		/*
		 * throw new MemberException( "Member with subscription id " +
		 * memberMaster.getSubscriptionId() + " was not found to update");
		 */
	}

	public MemberMaster deleteMember(MemberMaster memberMaster, Calendar asOfDate) throws MemberException {
		if (asOfDate == null) {
			asOfDate = Calendar.getInstance();
		}
		if (memberMaster.getSubscriptionId() != null) {
			MemberMaster dbMemberMaster = membershipRepository
					.findBySubscriptionIdAndMemberId(memberMaster.getSubscriptionId(), memberMaster.getMemberId());
			if (dbMemberMaster != null) {
				try {
					memberMaster.setDeletionDate(asOfDate);
					dbMemberMaster.setMembershipStatus("N");
					addAuditing(memberMaster);
					MemberMaster updatedMemberMaster = membershipRepository.save(memberMaster);
					return updatedMemberMaster;
				} catch (Exception ex) {
					throw new MemberException("Unable to update member with " + memberMaster.getSubscriptionId()
							+ ", please check the subscription Id, Subscriber Id and Member Id for correctness");
				}
			}
		}
		return null;
		/*
		 * throw new MemberException( "Member with subscription id " +
		 * memberMaster.getSubscriptionId() + " was not found to update");
		 */
	}
}
