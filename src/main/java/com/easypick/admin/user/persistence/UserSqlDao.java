package com.easypick.admin.user.persistence;

import java.util.Objects;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.UserSetup;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException; 

@Repository
public class UserSqlDao  implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getSession() {
		Session session;

		try {
			session = sessionFactory.openSession();
			// session = sessionFactory.getCurrentSession();
		} catch (SessionException se) {
			session = sessionFactory.openSession();
		}

		return session;
	}

	@Override
	public String validateUser(UserSetupVo vo) throws BussinessException {
		String output="F";
		try {
			
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			Query q= this.session.createQuery("From UserSetup userSetup where userSetup.usercode=:usercode and userSetup.password=:password and userSetup.status='Y'");
			q.setParameter("usercode", vo.getUsername()).setParameter("password", vo.getPassword());
			UserSetup setup=(UserSetup) q.getSingleResult(); 
			if(Objects.nonNull(setup))
				output="T";
			this.tx.commit(); 
		} catch (Exception e) {
			 
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
		return output;
	}

}
