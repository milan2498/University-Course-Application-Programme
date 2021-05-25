package com.cg.mts.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;

@Repository
public interface IUniversityStaffRepository extends JpaRepository<UniversityStaffMember, Integer> {

	@Query("select u.courses from UniversityStaffMember u where u.staffId=:id")
	Set<Course> getAllCoursesByStaffId(@Param("id") int id);
}
