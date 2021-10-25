package org.impelsys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import org.impelsys.data.HibernateEmployeeDao;
import org.impelsys.model.Employee;
@Component("employeeService")
//@Service("employeeService")
public class EmployeeService {
	@Autowired
	@Qualifier("hibernateDao")
	HibernateEmployeeDao dao;
	public boolean addEmployeeDetails(Employee emp){
		System.out.println("Adding employee (in service)");
		//perform bussiness logic here
		boolean flag=false;
			flag=dao.addEmployee(emp);
		return flag;
	}
	public boolean delete(Employee e){
		boolean flag=false;
		flag=dao.delete(e);
		return flag;
	}
	public int update(Employee emp){
		int status=dao.update(emp);
		return status;
	}
	public int updateEmployeeNew(Employee emp){
		return dao.updateEmployeeNew(emp);
	}
	public int addAccountNew(Employee emp){
		return dao.addAccountNew(emp);
	}
	public int updateDepartment(Employee emp){
		int status=dao.updateDepartment(emp);
		return status;
	}
	public List<Employee> getEmployeeDetails(int empId){
		List<Employee> list;
		list=dao.getEmployeeDetails(empId);	
		return list;
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> list;
		list=dao.getAllEmployees();
		return list;
	}
	
	public List<Employee> employeeList(){
		List<Employee> list;
		list=dao.employeeList();
		return list;
	}
	public List<Integer> distinctEmployees(){
		List<Integer> list=new ArrayList<Integer>();
		list=dao.distinctEmployees();
		return list;
	}
	public Long getEmpCount(){
		return dao.getEmpCount();
	}
	public List<Employee> getEmployeePagination(int from,int rows){
		List<Employee> list;
		list=dao.getEmployeePagination(from, rows);
		return list;
	}
	public void assignEmployeeProjects(){
		dao.assignEmployeeProjects();
	}
	

}
