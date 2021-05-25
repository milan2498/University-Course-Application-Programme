package com.cg.mts.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ADMISSION")

public class Admission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ADMISSION_Id")
	private int admissionId;

	@Column(name = "COURSE_ID")
	private int courseId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "ADMISSION_DATE")
	private LocalDate admissionDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private AdmissionStatus status;

	@OneToOne(cascade = CascadeType.ALL)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name = "APPLICANT_APPLICANT_ID")
	private Applicant applicant;

	@ManyToOne(cascade = CascadeType.DETACH)
	
	@JoinColumn(name = "ADMISSION_COMMITEE_STAFF_ID")
	private AdmissionCommiteeMember admissioncommitee;

	public Admission() {
		super();
	}

	public Admission(int admissionId, int courseId, LocalDate admissionDate, AdmissionStatus status,Applicant applicant) {
		super();
		this.admissionId = admissionId;
		this.courseId = courseId;
		this.admissionDate = admissionDate;
		this.status = status;
		this.applicant=applicant;
	}

	public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	@Enumerated(EnumType.STRING)
	public AdmissionStatus getStatus() {
		return status;
	}

	public void setStatus(AdmissionStatus status) {
		this.status = status;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	

	
}
