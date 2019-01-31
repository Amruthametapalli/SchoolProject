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
@Table(name = "CLASS")
public class Klasses {
	private int klassId;
	private String klassName;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLASS_ID", unique = true, nullable = false)
	public int getKlassId() {
		return klassId;
	}

	public void setKlassId(int klassId) {
		this.klassId = klassId;
	}

	@Column(name = "CLASS_NAME")
	public String getKlassName() {
		return klassName;
	}

	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}

	public static void AddKlasses(Klasses addKlasses) throws MyException{
		try{
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			session.save(addKlasses);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED.getHttpStatus(),StatusCodes.DB_CONNECTION_FAILED.getMessage());
		}
	}

	public List<Klasses> getKlaDetails(int value) throws MyException{
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Klasses where klassId = :kla");
			query.setParameter("kla", value);
			List list = query.list();
			List<Klasses> klasList = new ArrayList<Klasses>();
			
			for(int i = 0; i<list.size(); i++) {
				klasList.add((Klasses)list.get(i));
			}
			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
			
			return klasList;		
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED); 
		}
	}
	
	public List<Klasses> getKlaDetails(String value) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Klasses where klassName = :kla");
			query.setParameter("kla", value);
			
			List list = query.list();
			List<Klasses> klasList = new ArrayList<Klasses>();
			
			for(int i=0; i<list.size(); i++) {
				klasList.add((Klasses)list.get(i));
			}
			
			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
			
			return klasList;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
}
