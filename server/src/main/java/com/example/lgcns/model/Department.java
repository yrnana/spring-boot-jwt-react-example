package com.example.lgcns.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Department
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "manager", "employees" })
@Getter
@Setter
@ToString(exclude = { "manager", "employees" })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "departmentId" })
@Entity
@Table(name = "departments", indexes = { @Index(name = "manager_id", columnList = "manager_id") })
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id", length = 11)
	private Integer departmentId;

	@Size(max = 30)
	@Column(name = "department_name", nullable = false)
	private String departmentName;

	@Column(name = "manager_id", length = 11)
	private Integer managerId;

	// ========= ManyToOne =========

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", referencedColumnName = "employee_id", foreignKey = @ForeignKey(name = "departments_ibfk_1"), insertable = false, updatable = false)
	private Employee manager;

	// ========= OneToMany =========

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
}