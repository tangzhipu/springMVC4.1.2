package ouc.jeep.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ouc.jeep.model.Manager;

@Repository("managerDao")
public class ManagerDao{
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	public List<Manager> getAllManager(){
//    	System.err.println("session " + getCurrentSession()); 
    	return sessionFactory.openSession().createQuery("FROM manager").list();
//    	return getCurrentSession().createQuery("FROM manager").list();
    }

	public void add(Manager manager) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(manager);
		session.getTransaction().commit();
//		sessionFactory.openSession().save(manager);
//		getCurrentSession().save(manager);  
	}

	public Manager load(int id) {
		return  (Manager) getSession().load(Manager.class, id);
	}
}

