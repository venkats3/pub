package com.demo.enrollment.membership.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.enrollment.membership.entity.MemberMaster;

@Repository
public interface MembershipRepository extends JpaRepository<MemberMaster, Long> {
	// MemberMaster findMemberBySubscriptionIdAndMemberId(String subscriptionId,
	// String memberId);
	MemberMaster findBySubscriptionIdAndMemberId(String subscriptionId, String memberId);

	List<MemberMaster> findBySubscriptionId(String subscriptionId);

}