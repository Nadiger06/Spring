package org.impelsys.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;



@Entity
@Table(name="EmployeeDetails")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="empId",unique=true,nullable=false)
	int empId;
	@Column(name="empName")
	String empName;
	@Column(name="empPhoneNum")
	String empPhoneNum;
	@Column(name="experience")
	String experience;
	@Column(name="dob")
	@DateTimeFormat(pattern="dd/mm/yyyy")
	Date dob;
	@Column(name="salary")
	int salary;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="department",referencedColumnName="deptId")
	Department department;
	
	@OneToOne(cascade=CascadeType.ALL,targetEntity=BankAccount.class,fetch=FetchType.LAZY)
	@JoinColumn(name="bankAccount",referencedColumnName="Id")
	BankAccount bankAccount;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="EmpProjects",joinColumns={@JoinColumn(name="empId",referencedColumnName="empId")},
	inverseJoinColumns={@JoinColumn(name="projectId",referencedColumnName="projectId")})
	List<Project> projectList;


	public Employee(String empName, String empPhoneNum, String experience, int salary) {
		super();
		this.empName = empName;
		this.empPhoneNum = empPhoneNum;
		this.experience = experience;
		this.salary = salary;
	}
	public Employee(){
		
	}
	public static class EmployeeFactory{
	public static Employee create(){
		return  new Employee();
	}
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPhoneNum() {
		return empPhoneNum;
	}
	public void setEmpPhoneNum(String empPhoneNum) {
		this.empPhoneNum = empPhoneNum;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empPhoneNum=" + empPhoneNum + ", experience="
				+ experience + ", dob=" + dob + ", salary=" + salary + "]";
	}

	
	
}
