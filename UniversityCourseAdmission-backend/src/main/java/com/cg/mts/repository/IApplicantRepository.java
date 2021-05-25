package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Applicant;
@Repository
public interface IApplicantRepository extends CrudRepository<Applicant, Integer> {

	@Query("select d from Applicant d where d.applicantDegree like '12%' ")
    List<Applicant> get12thpass();
	
    @Query("select d from Applicant d where d.applicantDegree like 'B.Tech%' ")
    List<Applicant> getBtechDegree();
    
    @Query("select p from Applicant p where p.applicantGraduationPercent>60")
    List<Applicant> showAll1stDivision();
    
    @Query("select m from Applicant m where m.gender='MALE' ")
    List<Applicant> showMaleApplicants();
    
    @Query("select f from Applicant f where f.gender='FEMALE' ")
    List<Applicant> showFemaleApplicants();
}
