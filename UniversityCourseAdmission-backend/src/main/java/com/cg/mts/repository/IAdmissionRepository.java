package com.cg.mts.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Admission;
import com.cg.mts.entities.AdmissionStatus;

@Repository
public interface IAdmissionRepository extends JpaRepository<Admission, Integer> {

	@Modifying
	@Transactional
	@Query("update Admission a set a.admissionDate=?1,a.status=?2 where a.admissionId=?3")
	void updateAdmissionTable(LocalDate ld, AdmissionStatus status, int aid);

	@Modifying
	@Transactional
	@Query(value = "insert into Admission values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	void saveByApplicantAndAdmissionCommiteeId(int admissionId, LocalDate admissionDate, int courseId, String status,
			int staffId, int applicantId);

}
