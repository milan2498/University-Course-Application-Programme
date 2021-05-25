package com.cg.mts.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.DAOUser;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.exceptions.DataNotFoundException;

import com.cg.mts.exceptions.DuplicateDataException;
//import com.cg.mts.exceptions.EmptyDataException;

import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.repository.IUniversityStaffRepository;
import com.cg.mts.repository.UserDao;

@Service
public class UniversityStaffService implements IUniversityStaffService {

	@Autowired
	IUniversityStaffRepository universityRepo;

	@Autowired
	ICourseRepository courseRepo;

	@Autowired
	UserDao userRepository;


	// Method to get All Staffs
	public List<UniversityStaffMember> viewAllStaffs() {
		List<UniversityStaffMember> list = (List<UniversityStaffMember>) universityRepo.findAll();
		return list;
	}

	// Method to get one Staff Details
	public UniversityStaffMember viewStaff(int id) {
		Optional<UniversityStaffMember> opt = universityRepo.findById(id);
		if (opt.isPresent())
			return opt.get();
		return null;
	}


	// Method to add Staff Details as well User Details
	public void addStaff(UniversityStaffMember staff) throws DuplicateDataException{
		if(universityRepo.existsById(staff.getStaffId())) {
			throw new DuplicateDataException("Staff with id "+staff.getStaffId()+" already exists");
		}
		// User is created here in User table
		DAOUser newUser = new DAOUser();
		newUser.setUsername(staff.getUsername());
		newUser.setPassword(staff.getPassword());
		newUser.setRole(staff.getRole());
		newUser.setLoggedIn(false);
		userRepository.save(newUser);

		universityRepo.save(staff);
	}


	// Method to update Staff Details
	public boolean updateStaff(UniversityStaffMember staff) {
		UniversityStaffMember newStaff=universityRepo.findById(staff.getStaffId()).orElseThrow(()->new DataNotFoundException("update", "failed"));
		if (universityRepo.existsById(staff.getStaffId())) {
			
			newStaff.setUsername(staff.getUsername());
			newStaff.setPassword(staff.getPassword());
			newStaff.setRole(staff.getRole());
			newStaff.setStaffId(staff.getStaffId());
			universityRepo.save(newStaff);
			return true;
		}
		return false;
	}


	public boolean removeStaff(int id) {
		if(universityRepo.existsById(id)) {		
			universityRepo.deleteById(id);
			return true;
		}
		return false;
	}

	// Method to Add courses By Staff
	public void addCourse(Course c, int staffId) throws DuplicateDataException {
		if (courseRepo.existsById(c.getCourseId()))
			throw new DuplicateDataException("Course with id " + c.getCourseId() + " Already exists");
		if (!universityRepo.existsById(staffId))
			throw new DataNotFoundException("Checking", "Staff with id " + staffId + " not present in database");
		courseRepo.saveByStaffId(c.getCourseId(), c.getCourseDuration(), c.getCourseEndDate(), c.getCourseFees(),
				c.getCourseName(), c.getCourseStartDate(), staffId);
	}

	// Method to remove Course by Staff
	public boolean removeCourse(int id) {
		if (courseRepo.existsById(id)) {
			courseRepo.deleteById(id);
			return true;
		}
		return false;
	}


	// Method to update Course by Staff
	public boolean updateCourse(Course c, int courseId) {
		if (courseRepo.existsById(courseId)) {
			courseRepo.updateCourseDetails(c.getCourseName(), c.getCourseDuration(), c.getCourseStartDate(),
					c.getCourseEndDate(), c.getCourseFees(), courseId);
			return true;
		}
		return false;
	}

	// Method to get All Course Details under the Given Staff ID
	public Set<Course> viewAllCoursesUnderThisStaffId(int id) {
		Set<Course> set = (Set<Course>) universityRepo.getAllCoursesByStaffId(id);
		return set;
	}

}
