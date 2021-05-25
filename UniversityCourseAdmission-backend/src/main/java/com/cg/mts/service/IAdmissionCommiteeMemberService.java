package com.cg.mts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.AdmissionStatus;

@Service
public interface IAdmissionCommiteeMemberService {

	void saveAdmissionCommiteeMember(AdmissionCommiteeMember e);

	AdmissionCommiteeMember getUserByAdmissionCommiteeMemberId(int id);

	AdmissionCommiteeMember getAdmissionCommiteeMemberByStaffId(int id);

	boolean deleteUserByAdmissionCommiteeMemberId(int id);

	List<AdmissionCommiteeMember> getAllAdmissionCommiteeMembers();

	boolean updateAdmissionCommiteeMember(AdmissionCommiteeMember e);

	boolean deleteAdmissionCommiteeMemberByStaffId(int id);

	boolean provideAdmissionResult(int id, String as);

}
