package com.cg.mts.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.repository.IUniversityStaffRepository;
import com.cg.mts.service.UniversityStaffService;

@SpringBootTest
class TestUniversityStaffMember {

	@Autowired
	private UniversityStaffService service;

	@MockBean
	private IUniversityStaffRepository repository;
	
	@MockBean
	private ICourseRepository courseRepo;

	Logger logger = LoggerFactory.getLogger(TestUniversityStaffMember.class);

	// Testing for getting All the Staff Details
	@Test
	public void getAllStaffsTest() {
		logger.info("Testing for getAllStaffs Started");
		when(repository.findAll())
				.thenReturn(Stream
						.of(new UniversityStaffMember(700, "Sark1", "Supratim@9087", "STAFF"),
								new UniversityStaffMember(888, "Sup2304", "Shhd_637hh","COMMITEE"))
						.collect(Collectors.toList()));
		assertEquals(2, service.viewAllStaffs().size());
		logger.info("Testing for getAllStaffs Completed");
	}

	// Testing for getting details of one staff
	@Test
	public void getStaffTest() {
		logger.info("Testing for getStaff Started");
		UniversityStaffMember staff = new UniversityStaffMember(700, "Sark1", "Supratim@9087", "STAFF");
		when(repository.findById(700)).thenReturn(Optional.of(staff));
		UniversityStaffMember expectedStaff = service.viewStaff(staff.getStaffId());
		assertThat(expectedStaff).isSameAs(staff);
		verify(repository).findById(staff.getStaffId());
		logger.info("Testing for getStaff Completed");
	}

	// Testing for saving the Staff Details
	@Test
	public void saveStaffTest() {
		logger.info("Testing for SaveStaff Started");
		UniversityStaffMember staff = new UniversityStaffMember(700, "Sark1", "Supratim@9087", "STAFF");
		when(repository.save(staff)).thenReturn(staff);
		assertEquals(staff, repository.save(staff));
		logger.info("Testing for SaveStaff Completed");
	}

	// Testing for update the Staff Details
	@Test
	public void updateStaffTest() {
		logger.info("Testing for updateStaff Started");
		UniversityStaffMember staff = new UniversityStaffMember(700, "Sark1", "Supratim@9087", "STAFF");
		when(repository.save(staff)).thenReturn(staff);
		staff.setRole("faculty");
		assertThat(repository.findById(staff.getStaffId())).isNotEqualTo(staff);
		logger.info("Testing for updateStaff Completed");
	}

	// Testing for Delete the Staff Details
	@Test
	public void deleteStaffTest() {
		logger.info("Testing for deleteStaffs Started");
		UniversityStaffMember staff = new UniversityStaffMember(700, "Sark1", "Supratim@9087", "STAFF");
		when(repository.existsById(staff.getStaffId())).thenReturn(true);
		service.removeStaff(staff.getStaffId());
		verify(repository).deleteById(700);

		// assertNotEquals(staff, service.removeStaff(700));
		logger.info("Testing for deleteStaffs Completed");
	}
}
