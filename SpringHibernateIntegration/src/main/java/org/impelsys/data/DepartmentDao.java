package org.impelsys.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.impelsys.model.Department;
import org.impelsys.model.Employee;
@Component("departmentDao")
//@Repository("departmentDao")
public class DepartmentDao {
	@Autowired
	SessionFactory sessionFactory;
	Transaction tx=null;
	public boolean addDepartment(Department dept){
		System.out.println("Adding dept (in DepartmentDao)");
		Session session=null;
		try{
		session=sessionFactory.openSession();
		tx=session.beginTransaction();
/*		//---
		Set<Employee> set=new HashSet<Employee>();
		Employee e1=new Employee("Krishn1","100200","3",3000);
		Employee e2=new Employee("Krishn1","100200","3",3000);
		set.add(e1);
		set.add(e2);
		dept.setEmployees(set);
		//----
*/		session.save(dept);
		tx.commit();
		System.out.println("Commit");
		return true;
		}catch(Exception e){
			System.out.println("Rollback");
			tx.rollback();
			return false;
		}
		finally{
			if(session!=null)
				session.close();
		}
	}//add Dept close
	
	public List<Department> getAllDepartments(){
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		Query<Department> query=session.createQuery("from Department",Department.class);
	/*	//-----
		List<Department> deptList=query.list();
		for(Department dept:deptList){
			System.out.println("Employees working in dept: "+dept.getDeptName());
			for(Employee emp:dept.getEmployees())
				System.out.println(emp.getEmpId()+" -"+emp.getEmpName());
		}
		//------
*/		return query.list();
	}
	
	public int updateHod(Department dept){
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		Query<Department> query=session.createQuery("update Department set hodId=:h where deptId=:d");
		query.setParameter("h",dept.getHodEmployee());
		query.setParameter("d", dept.getDeptId());
		int status=0;
		status=query.executeUpdate();
		session.getTransaction().commit();
		return status;
	}
}
