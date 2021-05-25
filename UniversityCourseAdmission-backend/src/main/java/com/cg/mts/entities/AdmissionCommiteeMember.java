package com.cg.mts.entities;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
@Entity

@Table(name = "ADMISSION_COMMITEE_MEMBER")
//@PrimaryKeyJoinColumn(name = "STAFF_ID")
public class AdmissionCommiteeMember extends UniversityStaffMember implements Serializable {

	private static final long serialVersionUID = 1L;

	// @Id

	@Valid
	@Column(name = "ADMISSION_COMMITEE_MEMBER_ID")
	private int admissionCommiteeMemberId;

	@Valid
	@NotEmpty(message = "Name must be to given")
	@NotNull(message = "Name is required")
	@Length(min = 5, max = 20, message = "Name not between 5-20 letters")
	@Column(name = "ADMISSION_COMMITEE_MEMBER_NAME")
	private String admissionCommiteeMemberName;

	@Valid
	@NotEmpty(message = "Contact must be to given")
	@NotNull(message = "Contact is required")
	@Length(min = 2, max = 50, message = "Contact invalid")
	@Column(name = "ADMISSION_COMMITEE_MEMBER_CONTACT")
	private String admissionCommiteeMemberContact;

	@Valid
	@OneToMany(mappedBy = "admissioncommitee", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Admission> admission = new HashSet<>();

	public AdmissionCommiteeMember() {
		super();
	}

	public AdmissionCommiteeMember(int admissionCommiteeMemberId, String admissionCommiteeMemberName,
			String admissionCommiteeMemberContact) {
		super();
		this.admissionCommiteeMemberId = admissionCommiteeMemberId;
		this.admissionCommiteeMemberName = admissionCommiteeMemberName;
		this.admissionCommiteeMemberContact = admissionCommiteeMemberContact;
	}

	public int getAdmissionCommiteeMemberId() {
		return admissionCommiteeMemberId;
	}

	public void setAdmissionCommiteeMemberId(int admissionCommiteeMemberId) {
		this.admissionCommiteeMemberId = admissionCommiteeMemberId;
	}

	public String getAdmissionCommiteeMemberName() {
		return admissionCommiteeMemberName;
	}

	public void setAdmissionCommiteeMemberName(String admissionCommiteeMemberName) {
		this.admissionCommiteeMemberName = admissionCommiteeMemberName;
	}

	public String getAdmissionCommiteeMemberContact() {
		return admissionCommiteeMemberContact;
	}

	public void setAdmissionCommiteeMemberContact(String admissionCommiteeMemberContact) {
		this.admissionCommiteeMemberContact = admissionCommiteeMemberContact;
	}

	public Set<Admission> getAdmission() {
		return admission;
	}

	public void setAdmission(Set<Admission> admission) {
		this.admission = admission;
	}

}
