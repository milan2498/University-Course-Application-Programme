package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;

@Repository
public interface ICourseRepository extends CrudRepository<Course, Integer> {

	@Query("select c from Course c where c.courseName like 'B.TECH%' ")
	List<Course> ShowBTechCourse();

	@Query("select c from Course c where c.courseName like 'B.Sc%' ")
	List<Course> ShowBScCourse();

	@Query("select c from Course c where c.courseName like 'M.TECH%' ")
	List<Course> showMTechCourse();

	@Query("select c from Course c where c.courseDuration like '4%' ")
	List<Course> year4CourseDuration();

	@Query("select c from Course c where c.courseDuration like '3%' ")
	List<Course> year3CourseDuration();

	@Modifying
	@Transactional
	@Query(value = "insert into Course values(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	void saveByStaffId(int courseId, String duration, LocalDate end, String fees, String name, LocalDate start,
			int staffId);

	@Query("select c from Course c where c.courseDuration like '2%' ")
	List<Course> year2CourseDuration();

	@Query("select c from Course c where c.courseFees like '5000%' ")
	List<Course> fees5LacCourses();

	@Query("select c from Course c where c.courseFees like '8000%' ")
	List<Course> fees8LacCourses();

	@Query("select c from Course c where c.courseFees like '4000%' ")
	List<Course> fees4LacCourses();

	@Modifying
	@Transactional
	@Query("update Course c set c.courseName=?1,c.courseDuration=?2,c.courseStartDate=?3,c.courseEndDate=?4,c.courseFees=?5 where c.courseId=?6")
	void updateCourseDetails(String name, String duration, LocalDate start, LocalDate end, String fees, int cId);

}
