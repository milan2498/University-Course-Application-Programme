package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mts.entities.Admission;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.AdmissionStatus;

import com.cg.mts.exceptions.DataNotFoundException;
import com.cg.mts.exceptions.DuplicateDataException;

import com.cg.mts.repository.IAdmissionCommiteeMemberRepository;
import com.cg.mts.repository.IAdmissionRepository;

@Component
public class AdmissionCommiteeMemberService implements IAdmissionCommiteeMemberService {

	@Autowired

	IAdmissionCommiteeMemberRepository repository;

	@Autowired

	IAdmissionRepository repo;

	// Method to save Admission Committee Member
	public void saveAdmissionCommiteeMember(AdmissionCommiteeMember e) {

		if (repository.existsByStaffId(e.getStaffId())) {
			throw new DuplicateDataException(
					"University Staff with id : " + e.getStaffId() + " already exists.");
		}
		if (repository.existsByAdmissionCommiteeMemberId(e.getAdmissionCommiteeMemberId())) {
			throw new DuplicateDataException(
					"Admission Commitee Member with id : " + e.getAdmissionCommiteeMemberId() + " already exists.");
		}
		repository.save(e);
	}

	// Method to get Admission Committee Member by Staff Id
	public AdmissionCommiteeMember getAdmissionCommiteeMemberByStaffId(int id) {
		Optional<AdmissionCommiteeMember> opt = repository.findById(id);
		if (opt.isPresent())
			return opt.get();
		return null;
	}
	
	// Method to get Admission Committee Member by Admission Committee Member Id
	public AdmissionCommiteeMember getUserByAdmissionCommiteeMemberId(int id) {
		Optional<AdmissionCommiteeMember> opt = repository.findByAdmissionCommiteeMemberId(id);
		if (opt.isPresent())
			return opt.get();
		return null;
	}

	// Method to get all the Admission Committee Members
	public List<AdmissionCommiteeMember> getAllAdmissionCommiteeMembers() {
		List<AdmissionCommiteeMember> list = (List<AdmissionCommiteeMember>) repository.findAll();
		return list;
	}

	// Method to update Admission Committee Member
	public boolean updateAdmissionCommiteeMember(AdmissionCommiteeMember e) {
		if (repository.existsByAdmissionCommiteeMemberId(e.getAdmissionCommiteeMemberId())) {
			repository.save(e);
			return true;
		}
		return false;
	}

	// Method to delete Admission Committee Member by Staff Id
	public boolean deleteAdmissionCommiteeMemberByStaffId(int id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);

			return true;
		}
		return false;
	}

	// Method to delete Admission Committee Member by Admission Committee Member Id
	public boolean deleteUserByAdmissionCommiteeMemberId(int id) {
		if (repository.existsByAdmissionCommiteeMemberId(id)) {
			repository.deleteByAdmissionCommiteeMemberId(id);

			return true;
		}
		return false;
	}

	// Method to update Admission Status by Admission Id
	public boolean provideAdmissionResult(int adid, String as) {

		if (!(repo.existsById(adid))) {
			throw new DataNotFoundException("update", "Admission with id " + adid + " not found...");
		} else {
			Admission ad = repo.findById(adid).get();
			ad.setStatus(AdmissionStatus.valueOf(as.toUpperCase()));
			repo.save(ad);
			return true;
		}
	}

}