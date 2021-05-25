package com.cg.mts.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.repository.IAdmissionCommiteeMemberRepository;
import com.cg.mts.service.AdmissionCommiteeMemberService;

@SpringBootTest
class TestAdmissionCommiteeMember {

	@Autowired
	private AdmissionCommiteeMemberService service;

	@MockBean
	private IAdmissionCommiteeMemberRepository repository;

	Logger logger = LoggerFactory.getLogger(TestUniversityStaffMember.class);

	// Testing for getting one Admission Committee Member detail
	@Test
	public void getAdmissionCommiteeMemberTest() {
		logger.info("Testing for getAdmissionCommiteeMember Started");
		AdmissionCommiteeMember member = new AdmissionCommiteeMember(5000, "Satish", "satish.pandey@capgemini.com");
		when(repository.findByAdmissionCommiteeMemberId(5000)).thenReturn(Optional.of(member));
		AdmissionCommiteeMember expectedMember = service.getUserByAdmissionCommiteeMemberId(member.getAdmissionCommiteeMemberId());
		assertThat(expectedMember).isSameAs(member);
		verify(repository).findByAdmissionCommiteeMemberId(member.getAdmissionCommiteeMemberId());
		logger.info("Testing for getAdmissionCommiteeMember Ended");
	}

	// Testing for getting all the Admission Committee Member Details
	@Test
	public void getAllAdmissionCommiteeMembersTest() {
		logger.info("Testing for getAllAdmissionCommiteeMember Started");
		when(repository.findAll()).thenReturn(Stream
				.of(new AdmissionCommiteeMember(5000, "Satish", "satish.pandey@capgemini.com"),
						new AdmissionCommiteeMember(6000, "Aaryan", "aaryan.gill@capgemini.com"))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAllAdmissionCommiteeMembers().size());
		logger.info("Testing for getAllAdmissionCommiteeMember Ended");
	}

	// Testing for saving the Admission Committee Member Details
	@Test
	public void saveMemberTest() {
		logger.info("Testing for saveAdmissionCommiteeMember Started");
		AdmissionCommiteeMember member = new AdmissionCommiteeMember(8000, "Kabran", "kabran.khan@capgemini.com");
		when(repository.save(member)).thenReturn(member);
		assertEquals(member, repository.save(member));
		logger.info("Testing for saveAdmissionCommiteeMember Ended");
	}

	// Testing for updating all the Admission Committee Member Details
	@Test
	public void updateMemberTest() {
		logger.info("Testing for updateAdmissionCommiteeMember Started");
		AdmissionCommiteeMember member = new AdmissionCommiteeMember(5000, "Satish", "satish.pandey@capgemini.com");
		when(repository.save(member)).thenReturn(member);
		member.setAdmissionCommiteeMemberContact("satish.m.deshpandey@capgemini.com");
		assertThat(repository.findByAdmissionCommiteeMemberId(member.getAdmissionCommiteeMemberId())).isNotEqualTo(member);
		logger.info("Testing for updateAdmissionCommiteeMember Ended");
	}

	// Testing for deleting all the Admission Committee Member Details
	@Test
	public void deleteMemberTest() {
		logger.info("Testing for deleteAdmissionCommiteeMember Started");
		AdmissionCommiteeMember member = new AdmissionCommiteeMember(8000, "Kabran", "kabran.khan@capgemini.com");
		when(repository.existsByAdmissionCommiteeMemberId(member.getAdmissionCommiteeMemberId())).thenReturn(true);
		service.deleteUserByAdmissionCommiteeMemberId(member.getAdmissionCommiteeMemberId());
		verify(repository).deleteByAdmissionCommiteeMemberId(8000);
		logger.info("Testing for deleteAdmissionCommiteeMember Ended");
	}

}
