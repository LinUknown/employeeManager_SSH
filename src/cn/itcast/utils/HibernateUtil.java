package cn.itcast.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory = sessionFactory;
	}
	
	//��ȡSessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	//��ȡSession
	public static Session getSession() {
		session = sessionFactory.openSession();
		return session;
	}
	
	//�ر�Session
	public static void closeSession(Session session) {
		if(session != null) {
			session.close();
		}
	}
}
