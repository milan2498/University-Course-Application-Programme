package com.cg.mts.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "APPLICANT")
public class Applicant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "APPLICANT_ID")
	private int applicantId;
	
	@Column
	private String username; // By Enam
	
	@Column
	private String password; // By Enam

	@Column(name = "APPLICANT_FIRSTNAME")
	private String applicantFirstName;

	@Column(name = "APPLICANT_LASTNAME")
	private String applicantLastName;

	/*@NotEmpty(message = "mobile number should not be empty")
	@NotNull(message = "mobile number is required")
	@Length(min = 10, max = 10, message = "phone.length.error")*/
	@Column(name = "PH_NO")
	private String mobileNumber;

	/*@NotEmpty(message = "degree should not be empty")
	@NotNull(message = "degree is required")
	//@Length(min = 3, max = 10, message = "${degree.length.error}")*/
	@Column(name = "DEGREE",length = 20)
	private String applicantDegree;

	/*@NotNull(message = "percent is required")
	@Range(min=0,max=100)*/
	@Column(name = "GRAD_PER")
	private int applicantGraduationPercent;

	/*@OneToOne
	@JoinColumn(name = "ADMISSION_ID")
	private Admission admission;*/

	/*@OneToOne
	@JoinColumn(name = "COURSE_ID")*/ 
	@Column(name = "COURSE_ID")
	private int courseId;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER")
	private Gender gender;

	/*@OneToOne
	@JoinColumn(name = "ADDRESS")
	@NotEmpty(message = "address should not be empty")*/
	private String address;

	@Column(name = "EMAIL_ID")
	/*@NotEmpty(message = "email should not be empty")
	@NotNull(message = "email is required")
	@Email(message = "email should be valid")*/
	private String emailId;
	
	@Column(name="ROLE")
	private String role;
	public Applicant() {

	}



	public Applicant(int applicantId, String username, String password, String applicantFirstName, String applicantLastName, String mobileNumber,int courseId,String applicantDegree, int applicantGraduationPercent, Gender gender, String address, String emailId,String role) {
		this.applicantId = applicantId;
		this.username = username;
		this.password = password;
		this.applicantFirstName = applicantFirstName;
		this.applicantLastName = applicantLastName;
		this.mobileNumber = mobileNumber;
		this.applicantDegree = applicantDegree;
		this.applicantGraduationPercent = applicantGraduationPercent;
		//this.admission = admission;
		this.courseId = courseId;
		this.gender = gender;
		this.address = address;
		this.emailId = emailId;
		this.role=role;
	}

	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	// By Enam
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	// By Enam
	
	public String getApplicantFirstName() {
		return applicantFirstName;
	}

	public void setApplicantFirstName(String applicantFirstName) {
		this.applicantFirstName = applicantFirstName;
	}

	public String getApplicantLastName() {
		return applicantLastName;
	}

	public void setApplicantLastName(String applicantLastName) {
		this.applicantLastName = applicantLastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getApplicantDegree() {
		return applicantDegree;
	}

	public void setApplicantDegree(String applicantDegree) {
		this.applicantDegree = applicantDegree;
	}

	public int getApplicantGraduationPercent() {
		return applicantGraduationPercent;
	}

	public void setApplicantGraduationPercent(int applicantGraduationPercent) {
		this.applicantGraduationPercent = applicantGraduationPercent;
	}

	

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}