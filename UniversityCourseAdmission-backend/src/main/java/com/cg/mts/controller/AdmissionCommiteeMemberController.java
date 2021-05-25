package com.cg.mts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.AdmissionStatus;

import com.cg.mts.exceptions.DataNotFoundException;
import com.cg.mts.exceptions.EmptyDataException;
import com.cg.mts.service.AdmissionCommiteeMemberService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/AdmissionCommiteeMember")
@CrossOrigin(origins = "http://localhost:4200")
public class AdmissionCommiteeMemberController {

	@Autowired
	AdmissionCommiteeMemberService service;

	// To Save the Admission Committee Member details
	@PostMapping
	public ResponseEntity<?> saveAdmissionCommiteeMember(@Valid @RequestBody AdmissionCommiteeMember acm) {

		service.saveAdmissionCommiteeMember(acm);

		return new ResponseEntity<>("Admission Commitee Member saved successfully!", HttpStatus.OK);
	}

	// To Retrieve the Admission Committee Member Detail by Admission Committee Id
	@GetMapping("/getUserByAdmissionCommiteeMemberId/{acmid}")
	public ResponseEntity<?> findUserByAdmissionCommiteeMemberId(@PathVariable("acmid") int id) {
		AdmissionCommiteeMember acm = service.getUserByAdmissionCommiteeMemberId(id);
		if (acm == null)
			throw new DataNotFoundException("Request", "Admission Commitee Member with id " + id + " not found");
		return new ResponseEntity<>(acm, HttpStatus.OK);
	}

	// To Retrieve the Admission Committee Member Detail by Staff Id
	@GetMapping("/getUserByStaffId/{sid}")
	public ResponseEntity<?> getAdmissionCommiteeMember(@PathVariable("sid") int memId) {
		AdmissionCommiteeMember acm = service.getAdmissionCommiteeMemberByStaffId(memId);
		if (acm == null)
			throw new DataNotFoundException("Request", "Admission Commitee Member with id " + memId + " not found");
		return new ResponseEntity<AdmissionCommiteeMember>(acm, HttpStatus.OK);
	}

	// To Retrieve all the Admission Committee Members details
	@GetMapping
	public List<AdmissionCommiteeMember> getAllAdmissionCommiteeMembers() {
		List<AdmissionCommiteeMember> list = service.getAllAdmissionCommiteeMembers();
		if (list.size() == 0)
			throw new EmptyDataException("No Admission Commitee Member in database");
		return list;
	}

	// To Update the Admission Committee Member detail
	@PutMapping
	public String updateAdmissionCommiteeMember(@Valid @RequestBody AdmissionCommiteeMember acm) {
		if (service.updateAdmissionCommiteeMember(acm))
			return "Admission Commitee member data with id " + acm.getAdmissionCommiteeMemberId() + " updated";
		else
			throw new DataNotFoundException("Update",
					"Admission Commitee Member with id" + acm.getAdmissionCommiteeMemberId() + " not found");
	}

	// To Delete the Admission Committee Member detail by Staff Id
	@DeleteMapping("/deleteUserByStaffId/{sid}")
	public String deleteAdmissionCommiteeMember(@PathVariable("sid") int id) {
		if (service.deleteAdmissionCommiteeMemberByStaffId(id))
			return "Admission Commitee Member data deleted";
		else
			throw new DataNotFoundException("Delete",
					"Admission Commitee Member with id to delete " + id + " not found");
	}

	// To Delete the Admission Committee Member detail by Admission Committee Id
	@Transactional
	@DeleteMapping("/deleteUserByAdmissionCommiteeMemberId/{acmid}")
	public String deleteUserByAdmissionCommiteeMemberId(@PathVariable("acmid") int id) {
		if (service.deleteUserByAdmissionCommiteeMemberId(id))
			return "Admission Commitee Member data with id " + id + " deleted";
		else
			throw new DataNotFoundException("Delete",
					"Admission Commitee Member with id to delete " + id + " not found");

	}

	// To Update the Admission Committee Member Name by Admission Committee Id
	@PatchMapping("/updateAdmissionCommiteeMemberName/{​​​​​acmid}​​​​​/{​​​​​acmname}​​​​​")
	public String updateAdmissionCommiteeMemberName(@RequestParam("acmid") int acmid,
			@RequestParam("acmname") String acmname) {
		AdmissionCommiteeMember acm = service.getUserByAdmissionCommiteeMemberId(acmid);
		if (acm == null)
			throw new DataNotFoundException("Update",
					"Admission Commitee Member with name to update with id " + acmid + " not found");

		acm.setAdmissionCommiteeMemberName(acmname);
		service.updateAdmissionCommiteeMember(acm);
		return "Admission Commitee Member name update sucessfull";
	}

	// To Update the Admission Committee Member Contact by Admission Committee Id
	@PatchMapping("/updateAdmissionCommiteeMemberContact/{​​​​​acmid}​​​​​/{​​​​​acmcont}​​​​​")
	public String updateAdmissionCommiteeMemberContact(@RequestParam("acmid") int acmid,
			@RequestParam("acmcont") String acmcont) {
		AdmissionCommiteeMember acm = service.getUserByAdmissionCommiteeMemberId(acmid);
		if (acm == null)
			throw new DataNotFoundException("Update",
					"Admission Commitee Member with contact to update with id " + acmid + " not found");

		acm.setAdmissionCommiteeMemberContact(acmcont);
		service.updateAdmissionCommiteeMember(acm);
		return "Admission Commitee Member contact update sucessfull";
	}

	@CrossOrigin
	// To Update the Admission Status by Admission Id
	@PutMapping("/updateAdmissionStatus/{​​​​​adid}​​​​​/{​​​​​adstatus}​​​​​")
	public String updateAdmissionStatus(@RequestParam("adid") int adid,
			@RequestParam("adstatus") String adstatus) {
		if (service.provideAdmissionResult(adid, adstatus))
			return "Admission Result data of " + adid + " updated";
		else
			throw new DataNotFoundException("Update", "Admission Commitee Member with id" + adid + " not found");

	}

}