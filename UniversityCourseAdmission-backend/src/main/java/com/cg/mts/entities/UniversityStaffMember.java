package com.cg.mts.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
//@MappedSuperclass
@Entity
@Table(name = "UNIVERSITYSTAFFS")
@Inheritance(strategy = InheritanceType.JOINED)
public class UniversityStaffMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STAFF_ID")
	private int staffId;
	

	@NotEmpty(message = "User Name must have to given")
	@NotNull(message = "User Name can't be Null")
	@Length(min = 4, max = 20, message = "Name length should be 4-20 characters")
	@Column(name = "USERNAME")
	private String username;

	@NotEmpty(message = "Password must have to given")
	@NotNull(message = "Password can't be Null")
	//@Length(min = 6, max = 20, message = "Password length should be 6-20 characters")
	@Pattern(regexp="^[a-zA-Z0-9]{6,20}",message="password must contains one lowercase letter,"
			+"one uppercase letter and a number (no special characters) and length within 6-20 characters")
	@Column(name = "STAFF_PASSWORD")
	private String password;

	@NotEmpty(message = "Staff Role must have to given")
	@NotNull(message = "Staff Role can't be Null")
	//@Length(min = 2, max = 20)
	@Column(name = "STAFF_ROLE")
	private String role;

	@JsonIgnore
	//@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy = "universitystaffs", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Course> courses = new TreeSet<>();

	public UniversityStaffMember() {

	}

	public UniversityStaffMember(int staffId, String username, String password, String role) {
		super();
		this.staffId = staffId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}


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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "UniversityStaffMember [staffId=" + staffId + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", courses=" + courses + "]";
	}
	

}
