package com.amrutha.hibernateTest.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator {
	
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static Session getSession() {
		if (threadLocal.get() == null) {
			Session session = factory.openSession();
			threadLocal.set(session);
		}
		return threadLocal.get();
	}
	public static void removeSession() {
		threadLocal.remove();
	}
}
