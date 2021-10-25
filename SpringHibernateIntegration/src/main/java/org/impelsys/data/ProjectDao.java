package org.impelsys.data;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.impelsys.model.Employee;
import org.impelsys.model.Project;
@Component("projectDao")
//@Repository("projectDao")
public class ProjectDao {
	@Autowired
	SessionFactory sessionFactory;
	Transaction tx=null;
	public boolean addProject(Project project){
		System.out.println("Adding project (in projectDao)");
		Session session=null;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(project);
			tx.commit();
			System.out.println("Project Commit");
			return true;
			}catch(Exception e){
				System.out.println("Project Rollback");
				tx.rollback();
				return false;
			}
			finally{
				if(session!=null)
					session.close();
			}
	}

}
