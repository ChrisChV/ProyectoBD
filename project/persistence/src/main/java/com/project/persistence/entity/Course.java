package com.project.persistence.entity;
// Generated 08/12/2015 06:24:17 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Course generated by hbm2java
 */
@Entity
@Table(name = "course")
public class Course implements java.io.Serializable {

	private String courseId;
	private Department department;
	private String title;
	private Byte credits;

	public Course() {
	}

	public Course(String courseId) {
		this.courseId = courseId;
	}

	public Course(String courseId, Department department, String title, Byte credits) {
		this.courseId = courseId;
		this.department = department;
		this.title = title;
		this.credits = credits;
	}

	@Id

	@Column(name = "course_id", unique = true, nullable = false, length = 8)
	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_name")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "credits", precision = 2, scale = 0)
	public Byte getCredits() {
		return this.credits;
	}

	public void setCredits(Byte credits) {
		this.credits = credits;
	}


}
