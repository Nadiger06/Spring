package org.impelsys.SpringHibernateIntegration;

import java.util.List;

import org.impelsys.config.SpringHibernateXMLConfig;
import org.impelsys.data.HibernateEmployeeDao;
import org.impelsys.model.Department;
import org.impelsys.model.Employee;
import org.impelsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
	@Autowired
	@Qualifier("hibernateDao")
	static HibernateEmployeeDao hibernateDao;
	@Autowired
	@Qualifier("employeeService")
	static EmployeeService service;
   
	public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext context=new  AnnotationConfigApplicationContext(SpringHibernateXMLConfig.class);
    	List<Employee> list;
    	System.out.println("In app.java");
    	hibernateDao=(HibernateEmployeeDao) context.getBean("hibernateDao");
    	  	
    	Employee emp=new Employee();
    	emp.setEmpName("New emp0");
    	emp.setEmpPhoneNum("1122");
    	Department dept=new Department();
    	dept.setDeptId(4);
    	emp.setDepartment(dept);
    	hibernateDao.addEmployee(emp);
    	list=hibernateDao.getAllEmp();
    	
    	//list=service.getAllEmployees();
    	for(Employee emp1:list){
    		System.out.println(emp1.toString());
    	}
    }
}
