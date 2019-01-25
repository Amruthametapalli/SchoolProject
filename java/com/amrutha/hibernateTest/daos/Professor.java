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
@Table (name = "PROFESSOR")
public class Professor {
	private int profId;
	private String profGender;
	private String profName;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PROF_ID", unique = true, nullable = false)
	public int getProfId() {
		return profId;
	}
	
	public void setProfId(int profId) {
		this.profId = profId;
	}
	
	@Column(name = "PROF_GENDER")
	public String getProfGender() {
		return profGender;
	}
	public void setProfGender(String profGender) {
		this.profGender = profGender;
	}
	
	@Column(name = "PROF_NAME")
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	public static void AddProfessor(Professor addProfessor) throws MyException {
		try{
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			session.save(addProfessor);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED.getHttpStatus(),StatusCodes.DB_CONNECTION_FAILED.getMessage());
		}
	}
	
	public List<Professor> getProfDetails(int id) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Professor where profId = :pid");
			query.setParameter("pid",id);
			List profList = query.list();
			List<Professor> professors = new ArrayList<Professor>();
			
			for(int i = 0; i<profList.size(); i++) {
				professors.add((Professor)profList.get(i));
			}

			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
			return professors;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
	
	public List<Professor> getProfDetails(String name) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Professor where profName = :pname");
			query.setParameter("pname", name);
			
			List list = query.list();
			List<Professor> professors = new ArrayList<Professor>();
			
			for(int i = 0; i<list.size(); i++) {
				professors.add((Professor)list.get(i));
			}
			
			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
		
		    return professors;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
}
