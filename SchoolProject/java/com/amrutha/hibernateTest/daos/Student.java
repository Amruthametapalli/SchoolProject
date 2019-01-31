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
@Table(name = "Student")
public class Student {
	
	private int stuId;
	private String stuName;
	private String stuGender;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STU_ID", unique = true, nullable = false)
	public int getId() {
		return stuId;
	}
	public void setId(int Id) {
		this.stuId = Id;
	}
	
	@Column(name = "STU_NAME")
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Column(name = "STU_GENDER")
	public String getStuGender() {
		return stuGender;
	}
	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}
	
	public static void AddStudent(Student addStudent) throws Exception{
		try{
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			session.save(addStudent);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED.getHttpStatus(),StatusCodes.DB_CONNECTION_FAILED.getMessage());
		}	
	}
	
	public List<Student> getStuDetails(int id) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Student where stuId = :sid");
			query.setParameter("sid",id);
			List stuList = query.list();
			List<Student> students = new ArrayList<Student>();
			
			for(int i = 0; i<stuList.size(); i++) {
				students.add((Student)stuList.get(i));
			}

			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
			return students;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
	
	public List<Student> getStuDetails(String name) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Student where stuName = :sname");
			query.setParameter("sname", name);
			
			List list = query.list();
			List<Student> students = new ArrayList<Student>();
			
			for(int i = 0; i<list.size(); i++) {
				students.add((Student)list.get(i));
			}
			
			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
		
		    return students;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
}
