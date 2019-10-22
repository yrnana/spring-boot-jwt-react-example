package com.example.lgcns.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Employee
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "department", "job", "manager" })
@Setter
@Getter
@ToString(exclude = { "department", "job", "manager" })
@NoArgsConstructor
@EqualsAndHashCode(of = { "employeeId" })
@Entity
@Table(name = "employees", indexes = { @Index(name = "job_id", columnList = "job_id"),
		@Index(name = "department_id", columnList = "department_id"),
		@Index(name = "manager_id", columnList = "manager_id") })
public class Employee {

	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "employee_id", length = 11)
	private Integer employeeId;

	@Size(max = 20)
	@Column(name = "first_name")
	private String firstName;

	@Size(max = 25)
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "hire_date", nullable = false)
	private LocalDate hireDate;

	@Digits(integer = 8, fraction = 2)
	@Column(name = "salary", nullable = false)
	private BigDecimal salary;

	@Size(max = 10)
	@Column(name = "job_id", nullable = false)
	private String jobId;

	@Column(name = "department_id", length = 11)
	private Integer departmentId;

	@Column(name = "manager_id", length = 11)
	private Integer managerId;

	// ========= ManyToOne ===========

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id", referencedColumnName = "job_id", foreignKey = @ForeignKey(name = "employees_ibfk_1"), insertable = false, updatable = false)
	private Job job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "employees_ibfk_2"), insertable = false, updatable = false)
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", referencedColumnName = "employee_id", foreignKey = @ForeignKey(name = "employees_ibfk_3"), insertable = false, updatable = false)
	private Employee manager;
}