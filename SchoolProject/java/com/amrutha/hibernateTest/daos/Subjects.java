package com.amrutha.hibernateTest.daos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.utils.SessionCreator;
import com.amrutha.hibernateTest.utils.StatusCodes;

@Entity
@Table(name = "SUBJECTS")
public class Subjects {
	private int subId;
	private String subName;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SUB_ID", unique = true, nullable = false)
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	
	@Column(name = "SUB_NAME")
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	public static void AddSubjects(Subjects addSubjects) throws MyException {
		try{
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			session.save(addSubjects);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED.getHttpStatus(),StatusCodes.DB_CONNECTION_FAILED.getMessage());
		}			
	}
	
	public List<Subjects> getSubDetails(int id) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Subjects where subId = :suId");
			query.setParameter("suId",id);
			List subList = query.list();
			List<Subjects> subjects = new ArrayList<Subjects>();
			
			for(int i = 0; i<subList.size(); i++) {
				subjects.add((Subjects)subList.get(i));
			}

			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
			return subjects;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
	
	public List<Subjects> getSubDetails(String name) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Subjects where subName = :suName");
			query.setParameter("suName", name);
			
			List list = query.list();
			List<Subjects> subjects = new ArrayList<Subjects>();
			
			for(int i = 0; i<list.size(); i++) {
				subjects.add((Subjects)list.get(i));
			}
			
			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
		
		    return subjects;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
}
