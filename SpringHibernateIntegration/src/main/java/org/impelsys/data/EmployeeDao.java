package org.impelsys.data;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.impelsys.model.Employee;
@Component("employeeDao")
//@Repository("employeeDao")
public class EmployeeDao {
	public boolean addEmployee(Employee emp){
		System.out.println("Adding employee (in data/dao)");
		boolean flag=true;
		//perform database operations here
		System.out.println(emp.getEmpName()+emp.getEmpPhoneNum());
		
		return flag;
	}
	


}
