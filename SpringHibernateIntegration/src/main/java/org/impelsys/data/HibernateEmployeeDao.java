package org.impelsys.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.impelsys.model.Employee;
import org.impelsys.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component("hibernateDao")
@Transactional
//@Repository("hibernateDao")
public class HibernateEmployeeDao {
	@Autowired
	SessionFactory sessionFactory;
	//Transaction tx=null;
	public boolean addEmployee(Employee emp){
		System.out.println("Adding employee (in HibernateDao)");
		Session session=null;
		try{
		//SessionFactory sf=HibernateUtil.getSessionFactory();
		session=sessionFactory.openSession();
		//tx=session.beginTransaction();
		session.save(emp);
		//tx.commit();
		System.out.println("Commit");
		return true;
		}catch(Exception e){
			System.out.println("Rollback");
			//tx.rollback();
			return false;
		}
		finally{
			if(session!=null)
				session.close();
			//---changing from detatched state to persistance state
			/*
			session=sessionFactory.openSession();
			session.beginTransaction();
			emp.setEmpName("sai c");//Sets name to this whatever we may input in form field name
			session.update(emp);
			session.getTransaction().commit();
			session.close();//detatched
			*/
			//---------
		}
	}
	//----------------Delete employee----------------------
	public boolean delete(Employee e){
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		tx=session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();
		return false;
	}
	
	//----------Update------------
	public int update(Employee emp){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update Employee set empName=:n,empPhoneNum=:p where empId=:i");
		query.setParameter("n",emp.getEmpName());
		query.setParameter("p",emp.getEmpPhoneNum());
		query.setParameter("i",emp.getEmpId());
		int status=query.executeUpdate();
		session.getTransaction().commit();
		return status;
	}
	//-----update department------
	public int updateDepartment(Employee emp){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update Employee set department=:d where empId=:i");
		//query.setParameter("d",emp.getDepartment().getDeptId());
		query.setParameter("d",2);
		query.setParameter("i",emp.getEmpId());
		System.out.println(emp.getDepartment().getDeptId());
		int status=query.executeUpdate();
		session.getTransaction().commit();
		return status;
	}
	//-----------new Update employee-----
	public int updateEmployeeNew(Employee emp){
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query<Employee> query=session.createQuery("update Employee set department=:d where empId=:i");
		query.setParameter("d",emp.getDepartment());
		query.setParameter("i",emp.getEmpId());
		int status=query.executeUpdate();
		session.getTransaction().commit();
		return status;
	}
	//----------new update bank account---------
	public int addAccountNew(Employee emp){
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query<Employee> query=session.createQuery("update Employee set bankAccount=:b where empId=:i");
		query.setParameter("b",emp.getBankAccount());
		query.setParameter("i",emp.getEmpId());
		 System.out.println(emp.getEmpId());
		System.out.println(emp.getBankAccount().getAccountId());
		int status=query.executeUpdate();
		session.getTransaction().commit();
		return status;
	}
	//-----------------To Fetch Details--------------------
	public List<Employee> getEmployeeDetails(int empId){
		List<Employee> list=new ArrayList();
		Session session=sessionFactory.openSession();
		Employee emp=session.get(Employee.class,new Integer(empId));
		System.out.println("employee get() called");
		System.out.println(emp.toString());
		Employee emp1=session.load(Employee.class,new Integer(empId));
		System.out.println("employee load() called");
		System.out.println(emp1.toString());
		list.add(emp);
		list.add(emp1);
		return list;
		
		
		
		
	}
	//----------------All Employees---------
	public List<Employee> getAllEmployees(){
		Session session=sessionFactory.getCurrentSession();	
		//Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Employee.class);
		//criteria.add(Restrictions.eq("salary", 10000));//equal to 10k
		//criteria.add(Restrictions.gt("salary", 20000));//greater than 20k
		//criteria.add(Restrictions.lt("salary", 30000));//less than 30k
		//criteria.add(Restrictions.isNull("salary"));//sal not null
		//criteria.add(Restrictions.like("empName","k%"));// name starts with k
		//Criterion sal1= Restrictions.gt("salary",15000);
		//Criterion sal2= Restrictions.lt("salary",31000);
		//LogicalExpression andExp=Restrictions.and(sal1,sal2);
		//criteria.add(andExp);
		criteria.addOrder(Order.asc("salary"));
		//return sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
		//projection
		//total row count
		//criteria.setProjection(Projections.rowCount());
		//criteria.setProjection(Projections.distinct(Projections.property("salary")));
		//session.getTransaction().commit();//dont use this for getCurrentSession
		return criteria.list();
	}
	public List<Employee> employeeList(){
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		//Query query=session.createQuery("from Employee where empId=:e");
		Query query=session.createQuery("from Employee");
		//query.setParameter("e", 9);
		//query.setFirstResult(5);
		System.out.println("Emp list called");
		List<Employee> empList=query.list();
		return empList;
	}
	//---
	public List<Employee> getAllEmp(){
		//Session session=sessionFactory.getCurrentSession();
		Session session=sessionFactory.openSession();
		//session.beginTransaction();
		CriteriaBuilder cb=session.getCriteriaBuilder();
		CriteriaQuery<Employee> cQuery=cb.createQuery(Employee.class);
		Root<Employee> root=cQuery.from(Employee.class);
		cQuery.select(root);
		TypedQuery<Employee> qry=session.createQuery(cQuery);
		List<Employee> empList=qry.getResultList();
		//session.close();
		//session.getTransaction().commit();
		return empList;
		
	}
	public List<Integer> distinctEmployees(){
		//Session session=sessionFactory.openSession();
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.gt("salary", 15000));
		criteria.setProjection(Projections.distinct(Projections.property("salary")));
		//criteria.setProjection(Projections.sum("salary"));
		return criteria.list();
	}
	//-----Emp count--------
	public Long getEmpCount(){
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Query query=session.createQuery("select count(e.empId) from Employee e");
		//List<Employee> empList=query.list();
		Long count=(Long) query.uniqueResult();
		System.out.println(count);
		return count;
	}
	//----pagination
	public List<Employee> getEmployeePagination(int from,int rows){
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Query<Employee> query=session.createQuery("from Employee",Employee.class);
		query.setFirstResult(from);
		query.setMaxResults(rows);
		//ScrollableResults scrollableResultSet=query.scroll();
		//scrollableResultSet.first();
		
		List<Employee> list=query.list();
		return list;
	}
	//----------Assign Projects---------
	public void assignEmployeeProjects(){
		//List<Employee> empList=employeeList();
		List<Employee> empList=getAllEmp();
		List<Project> projectList=new ArrayList();
		for(int i=1;i<3;i++){
			Project p=new Project();
			p.setProjectName("Project"+i);
			p.setProjectLead("ProjectLead"+i);
			projectList.add(p);
		}
		//Session session=sessionFactory.getCurrentSession();
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		if(!session.getTransaction().isActive()){
			tx=session.beginTransaction();
			System.out.println("Transaction begin");
		}
		if(empList!=null){
			System.out.println("At assign projects");
			for(Employee emp:empList){
				emp.setProjectList(projectList);
				session.saveOrUpdate(emp);
			}
			if(tx!=null && tx.isActive()){
				System.out.println("At commit");
			tx.commit();
			}
			session.close();
		}
	}
	
	
	
	
	
	//---------------Concept---------------
	/*getCurrentSession will create a session if not exists
	 * Otherwise it uses same session in hebernate context
	 * getCurrentSession doesnt require to explicitly flush or 
	 * -----
	 * OpenSession always creates new session which needs to be explicitly flushed
	 * In single thread applications openSession is slower than getCurrentSession
	 * 
	 */

}
