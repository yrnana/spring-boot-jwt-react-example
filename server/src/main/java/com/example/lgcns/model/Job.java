package com.example.lgcns.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Job
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "employees" })
@Getter
@Setter
@ToString(exclude = { "employees" })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "jobId" })
@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Size(max = 10)
	@Column(name = "job_id")
	private String jobId;

	@Size(max = 35)
	@Column(name = "job_title", nullable = false)
	private String jobTitle;

	@Digits(integer = 8, fraction = 0)
	@Column(name = "min_salary")
	private BigDecimal minSalary;

	@Digits(integer = 8, fraction = 0)
	@Column(name = "max_salary")
	private BigDecimal maxSalary;

	// ========= OneToMany =========

	@OneToMany(mappedBy = "job")
	private List<Employee> employees;
}