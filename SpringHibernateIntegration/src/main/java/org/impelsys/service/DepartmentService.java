package org.impelsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.impelsys.data.DepartmentDao;
import org.impelsys.model.Department;
@Component("departmentService")
//@Service("departmentService")
public class DepartmentService {
	@Autowired
	@Qualifier("departmentDao")
	DepartmentDao deptDao;
	public boolean addDepartment(Department dept){
		boolean flag=false;
		flag=deptDao.addDepartment(dept);
		return flag;
	}
	
	public List<Department> getAllDepartments(){
		List<Department> list;
		list=deptDao.getAllDepartments();
		return list;
	}
	
	public int updateHod(Department dept){
		return deptDao.updateHod(dept);
	}

}
