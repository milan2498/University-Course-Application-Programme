package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Course;
import com.cg.mts.exceptions.DuplicateDataException;
import com.cg.mts.repository.ICourseRepository;

@Service
public class CourseService implements ICourseService {
	@Autowired
	ICourseRepository repository;

	Course c;

	// Method to remove course
	public boolean removeCourse(int id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	// Method to add course
	public void addCourse(Course c) throws DuplicateDataException {
		if (repository.existsById(c.getCourseId()))
			throw new DuplicateDataException("Course with ID " + c.getCourseId() + " already exists");
		repository.save(c);
	}

	/*// Method to update course
	public boolean updateCourse(Course c, int cid) {
		if (repository.existsById(c.getCourseId())) {
			repository.updateCourseDetails(c.getCourseName(), c.getCourseDuration(), c.getCourseStartDate(),
					c.getCourseEndDate(), c.getCourseFees(), cid);
			return true;
		}
		return false;
	}*/
	
	
	

	// Method to view course by course id
	public Course viewCourse(int id) {
		Optional<Course> opt = repository.findById(id);
		if (opt.isPresent())
			return opt.get();
		return null;

	}

	// Method to view all courses
	public List<Course> viewAllCourses() {
		List<Course> list = (List<Course>) repository.findAll();
		return list;
	}

	// Method to show all M.tech courses
	public List<Course> showMTechCourse() {
		List<Course> coursenames = (List<Course>) repository.showMTechCourse();
		return coursenames;
	}

	// Method to show all B.tech courses
	public List<Course> ShowBTechCourse() {
		List<Course> coursenames = (List<Course>) repository.ShowBTechCourse();
		return coursenames;
	}

	// Method to show all B.Sc courses
	public List<Course> ShowBScCourse() {
		List<Course> coursenames = (List<Course>) repository.ShowBScCourse();
		return coursenames;
	}

	// Method to show all 3 year courses
	public List<Course> year3CourseDuration() {
		List<Course> coursenames = (List<Course>) repository.year3CourseDuration();
		return coursenames;
	}

	// Method to show all 4 year courses
	public List<Course> year4CourseDuration() {
		List<Course> coursenames = (List<Course>) repository.year4CourseDuration();
		return coursenames;
	}

	// Method to show all 2 year courses
	public List<Course> year2CourseDuration() {
		List<Course> coursenames = (List<Course>) repository.year2CourseDuration();
		return coursenames;
	}

	// Method to show all courses costing 5 lakhs
	public List<Course> fees5LacCourses() {
		List<Course> coursefees = (List<Course>) repository.fees5LacCourses();
		return coursefees;
	}

	// Method to show all courses costing 8 lakhs
	public List<Course> fees8LacCourses() {
		List<Course> coursefees = (List<Course>) repository.fees8LacCourses();
		return coursefees;
	}

	// Method to show all courses costing 4 lakhs
	public List<Course> fees4LacCourses() {
		List<Course> coursefees = (List<Course>) repository.fees4LacCourses();
		return coursefees;
	}

	@Override
	public boolean updateCourse(Course c) {
		if (repository.existsById(c.getCourseId())) {
			repository.save(c);
			return true;
		}
		return false;
	}

}
