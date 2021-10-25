package org.impelsys.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.impelsys.model.BankAccount;
@Component("bankaccountDao")
//@Repository("bankaccountDao")
public class BankAccountDao {
	@Autowired
	SessionFactory sessionFactory;
	Transaction tx=null;
	public boolean addBankAccount(BankAccount account){
		System.out.println("Adding account (in BankAccountDao)");
		Session session=null;
		try{
		session=sessionFactory.openSession();
		tx=session.beginTransaction();
		session.saveOrUpdate(account);
		tx.commit();
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
	}
	//---------Fetching bank accounts---------
	public List<BankAccount> getAllBankAccounts(){
		Session session=sessionFactory.getCurrentSession();
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		Query<BankAccount> query=session.createQuery("from BankAccount",BankAccount.class);
		return query.list();
	}

}
