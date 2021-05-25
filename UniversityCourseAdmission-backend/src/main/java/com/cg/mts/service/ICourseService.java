package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;

public interface ICourseService {

	void addCourse(Course c);
	boolean updateCourse(Course c);

	boolean removeCourse(int id);

	Course viewCourse(int id);

	List<Course> viewAllCourses();

	List<Course> ShowBTechCourse();

	List<Course> ShowBScCourse();

	List<Course> showMTechCourse();

	List<Course> year4CourseDuration();

	List<Course> year3CourseDuration();

	List<Course> year2CourseDuration();

	List<Course> fees5LacCourses();

	List<Course> fees8LacCourses();

	List<Course> fees4LacCourses();

}
