package com.cg.mts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.mts.entities.AdmissionCommiteeMember;

@Repository
public interface IAdmissionCommiteeMemberRepository extends JpaRepository<AdmissionCommiteeMember, Integer> {

	Optional<AdmissionCommiteeMember> findByAdmissionCommiteeMemberId(int admissionCommiteeMemberId);

	boolean existsByAdmissionCommiteeMemberId(int admissionCommiteeMemberId);
	boolean existsByStaffId(int staffId);
	void deleteByAdmissionCommiteeMemberId(int admissionCommiteeMemberId);

}
