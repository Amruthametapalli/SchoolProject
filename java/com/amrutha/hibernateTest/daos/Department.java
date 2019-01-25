package com.amrutha.hibernateTest.daos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "DEPARTMENT")
public class Department {
	private int deptId;
	private String deptName;
	private Date dateCreated;
	private Date dateModified;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DEPT_ID", unique = true, nullable = false)
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/*
	@Column(name = "DATE_CREATED")
	public Date getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		DateCreated = dateCreated;
	}
	
	@Column(name = "DATE_MODIFIED")
	public Date getDateModified() {
		return DateModified;
	}
	public void setDateModified(Date dateModified) {
		DateModified = dateModified;
	}
	*/
	public static void AddDepartment(Department addDepartment) throws MyException {
		try{
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			session.save(addDepartment);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
	
	public List<Department> getDeptDetails(int id) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Department where deptId = :did");
			query.setParameter("did",id);
			List deptList = query.list();
			List<Department> departments = new ArrayList<Department>();
			
			for(int i = 0; i<deptList.size(); i++) {
				departments.add((Department)deptList.get(i));
			}

			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
			return departments;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
	
	public List<Department> getDeptDetails(String name) throws MyException {
		try {
			Session session = SessionCreator.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Department where deptName = :dname");
			query.setParameter("dname", name);
			
			List list = query.list();
			List<Department> departments = new ArrayList<Department>();
			
			for(int i = 0; i<list.size(); i++) {
				departments.add((Department)list.get(i));
			}
			
			session.getTransaction().commit();
			session.close();
			SessionCreator.removeSession();
		
		    return departments;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.DB_CONNECTION_FAILED);
		}
	}
}
