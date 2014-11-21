package ouc.jeep.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static final ThreadLocal session =new ThreadLocal();
	
	public static final SessionFactory sessionFactory;
	   static {
	      try {
	        sessionFactory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) {
	           throw new ExceptionInInitializerError(ex);
	      }    
	}
	
	 public static Session currentSession () throws HibernateException {
	    Session s = (Session) session.get ();
	    if(s == null) {
	      s = sessionFactory.openSession ();
	      session.set(s);
	    }
	     return s;
	   }
	
	public static void closeSession() throws HibernateException {
	    Session s = (Session) session.get ();
	    if(s != null) {
	        s.close();
	    }
	     session.set(null);
	}
	
	public static void main(String[] args) {
		System.err.println(currentSession()); 
	}
}