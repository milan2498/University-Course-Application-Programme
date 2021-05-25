package com.cg.mts.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Applicant;
import com.cg.mts.exceptions.DataNotFoundException;
import com.cg.mts.exceptions.DuplicateDataException;
import com.cg.mts.exceptions.EmptyDataException;
import com.cg.mts.service.AdmissionService;
import com.cg.mts.service.JwtUserDetailsService;

@RestController
@RequestMapping("/Admission")
@CrossOrigin(origins = "http://localhost:4200")
public class AdmissionController {

	@Autowired
	AdmissionService service;

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@GetMapping
	public List<Admission> getAdmission() {
		List<Admission> list = service.getallAdmission();
		if (list.size() == 0)
			throw new EmptyDataException("No Admission in Database.");
		return list;
	}

	@GetMapping("{aid}")
	public ResponseEntity<?> getAdmission(@PathVariable("aid") int admissionId) {
		Admission c = service.getAdmission(admissionId);
		if (c == null)
			throw new DataNotFoundException("request", "Admission with id " + admissionId + "not found");
		return new ResponseEntity<Admission>(c, HttpStatus.OK);
	}

	/*@PostMapping("/{courseId}/{staffId}/{applicantId}")
	public String saveAdmission(/*@RequestHeader("Authorization") String token, @Valid @RequestBody Admission a,
			@PathVariable("courseId") int cid, @PathVariable("staffId") int sid, @PathVariable("applicantId") int aid) {
		//String role = jwtUserDetailsService.getRoleFromToken(token);
		//if (role.equalsIgnoreCase("COMMITTEE")) {
		if(service.getAdmission(aid)==null) {
			service.addAdmission(a, cid, sid, aid);
			return "Data Saved";
		}
		else
			throw new DuplicateDataException("admission with id "+aid+" already exists");
		//} else {
			//return "Invalid Role";
		//}
	}*/

	@PutMapping//("/update")
	public String updateAdmission(/*@RequestHeader("Authorization") String token,*/@Valid @RequestBody Admission a) {
//		String role = jwtUserDetailsService.getRoleFromToken(token);
//		if (role.equalsIgnoreCase("COMMITTEE")) {
			if(service.updateAdmission(a, a.getAdmissionId()))
					return "Admission Data Saved Successfully";
			else
				throw new DataNotFoundException("update", "Admission with id " + a.getAdmissionId() + " not found");
//		} else {
//			return new ResponseEntity<>("Invalid role..", HttpStatus.BAD_REQUEST);
//		}
	}
	
	@PostMapping
	public String saveAdmission(@Valid @RequestBody Admission a) {
		if(service.getAdmission(a.getAdmissionId())==null) {
			service.addingAdmission(a);
			return "data saved";	
		}
		else
			throw new DuplicateDataException("admission with id "+a.getAdmissionId()+" already exists");
	}

	@DeleteMapping("/{aid}")
	public String deleteAdmission(/*@RequestHeader("Authorization") String token,*/ @PathVariable("aid") int admissionId) {
//		String role = jwtUserDetailsService.getRoleFromToken(token);
//		if (role.equalsIgnoreCase("COMMITTEE")) {
			if (service.deleteAdmission(admissionId))
				return "Data Deleted";
			else
				throw new DataNotFoundException("detete", "Admission with Id" + admissionId + "not found");
//		} else {
//			return "Invalid Role";
//		}
	}

	@GetMapping("/Date/{date}")
	public List<Admission> getAllByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String admissionDate) {

		List<Admission> c = service.showAllByDate(admissionDate);
		if (c == null)
			throw new DataNotFoundException("request", "Admission with date " + admissionDate + "not found");
		return c;
	}

	@GetMapping("/Course/{cid}")
	public List<Admission> getAllByCourseId(@PathVariable("cid") int courseId) {

		List<Admission> ad = service.showAllByCourseId(courseId);
		if (ad == null)
			throw new DataNotFoundException("request", "Course with id " + courseId + "not found");
		return ad;
	}

}
