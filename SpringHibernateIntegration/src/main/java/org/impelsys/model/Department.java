package org.impelsys.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Department")
public class Department {
	@Id
	@Column(name="deptId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int deptId;
	@Column(name="deptName")
	String deptName;
	@Column(name="hodName")
	String hodName;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="department")
	Set<Employee> employees;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="hodId",referencedColumnName="empId")
	private Employee hodEmployee;
	public void Department(){
		
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public Employee getHodEmployee() {
		return hodEmployee;
	}
	public void setHodEmployee(Employee hodEmployee) {
		this.hodEmployee = hodEmployee;
	}
	
}
