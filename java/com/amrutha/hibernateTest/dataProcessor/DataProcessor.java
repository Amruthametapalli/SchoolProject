package com.amrutha.hibernateTest.dataProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.DeptPojo;
import com.amrutha.hibernateTest.pojo.KlassPojo;
import com.amrutha.hibernateTest.pojo.ProfPojo;
import com.amrutha.hibernateTest.pojo.StuPojo;
import com.amrutha.hibernateTest.pojo.SubPojo;
import com.amrutha.hibernateTest.utils.StatusCodes;
import com.amrutha.hibernateTest.daos.Department;
import com.amrutha.hibernateTest.daos.Klasses;
import com.amrutha.hibernateTest.daos.Professor;
import com.amrutha.hibernateTest.daos.Student;
import com.amrutha.hibernateTest.daos.Subjects;

public class DataProcessor {
	
	HashMap<String, Integer> headers = new HashMap<String, Integer>();

	// Setting Headers
	private HashMap<String, Integer> setHeaders(String recordStr) {
		System.out.println("setting headers");
		List<String> record = new ArrayList<String>(Arrays.asList(recordStr.split(",")));
		HashMap<String, Integer> thisHeaders = new HashMap<String, Integer>();
		for (int i = 0; i < record.size(); i++)
			thisHeaders.put(record.get(i), i);
		return thisHeaders;
	}

	// Validating header fields
	public void validateHeaders(String... columns) throws Exception {
		System.out.println("validating headers");
		boolean check = true;
		for (String columnName : columns)
			if (!this.headers.containsKey(columnName)) {
				check = false;
				break;
			}

		if (check == false) {
			System.out.println("caught Exception in validating");
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Student details with TEXT/PLAIN input
	public void processStudents(String body) throws Exception {
		try {
			System.out.println("invoked student process");
			Student stuObj = new Student();
			List<String> records = new ArrayList<String>(Arrays.asList(body.split(";")));
			this.headers = this.setHeaders(records.get(0));
			this.validateHeaders("id", "name","gender");
			for (int i = 1; i < records.size(); i++) {
				System.out.println("invoking student dao");
				String[] eachRecord = records.get(i).split(",");
				stuObj.setId(Integer.parseInt(eachRecord[headers.get("id")]));
				stuObj.setStuName(eachRecord[headers.get("name")]);
				stuObj.setStuGender(eachRecord[headers.get("gender")]);
				Student.AddStudent(stuObj);
			}
		} catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Student details with APPLICATION/JSON
	public void processStu(List<StuPojo> stuData) throws Exception {
		try {
			for (int i = 0; i < stuData.size(); i++) {
				Student stu = new Student();
				stu.setId(stuData.get(i).getStuId());
				stu.setStuName(stuData.get(i).getStuName());
				stu.setStuGender(stuData.get(i).getStuGender());
				Student.AddStudent(stu);
			}
		} catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
				throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Klasses details with TEXT/PLAIN input
	public void processKlasses(String body) throws Exception {
		try {
			Klasses claObj = new Klasses();
			List<String> records = new ArrayList<String>(Arrays.asList(body.split(";")));
			HashMap<String, Integer> headers = this.setHeaders(records.get(0));
			this.validateHeaders("id", "name");
			for (int i = 1; i < records.size(); i++) {
				String[] eachRecord = records.get(i).split(",");
				claObj.setKlassId(Integer.parseInt(eachRecord[headers.get("id")]));
				claObj.setKlassName(eachRecord[headers.get("name")]);
				Klasses.AddKlasses(claObj);
			}
		} 
		catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Klasses details with APPLICATION/JSON

	public void processKla(List<KlassPojo> klaData) throws Exception {
		try {
			for (int i = 0; i < klaData.size(); i++) {
				Klasses klass = new Klasses();
				klass.setKlassId(klaData.get(i).getKlassId());
				klass.setKlassName(klaData.get(i).getKlassName());
				Klasses.AddKlasses(klass);
			}
		} 
		catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Department details with TEXT/PLAIN input
	public void processDepartment(String body) throws Exception {
		try {
			Department depObj = new Department();
			List<String> records = new ArrayList<String>(Arrays.asList(body.split(";")));
			HashMap<String, Integer> headers = this.setHeaders(records.get(0));
			this.validateHeaders("id", "name");
			for (int i = 1; i < records.size(); i++) {
				String[] eachRecord = records.get(i).split(",");
				depObj.setDeptId(Integer.parseInt(eachRecord[headers.get("id")]));
				depObj.setDeptName(eachRecord[headers.get("name")]);
				Department.AddDepartment(depObj);
			}
		} 
		catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
				throw new MyException(StatusCodes.INVALID_HEADERS);
		}

	}

	// Processing Department details with APPLICATION/JSON input
	public void processDep(List<DeptPojo> deptData) throws Exception {

		try {
			for (int i = 0; i < deptData.size(); i++) {

				Department dept = new Department();
				dept.setDeptId(deptData.get(i).getDeptId());
				dept.setDeptName(deptData.get(i).getDeptName());
				Department.AddDepartment(dept);
			}
		} 
		catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Subject details with TEXT/PLAIN input
	public void processSubjects(String body) throws Exception {
		try {
			Subjects subObj = new Subjects();
			List<String> records = new ArrayList<String>(Arrays.asList(body.split(";")));
			HashMap<String, Integer> headers = this.setHeaders(records.get(0));
			this.validateHeaders("id", "name");
			for (int i = 1; i < records.size(); i++) {
				String[] eachRecord = records.get(i).split(",");
				subObj.setSubId(Integer.parseInt(eachRecord[headers.get("id")]));
				subObj.setSubName(eachRecord[headers.get("name")]);
				Subjects.AddSubjects(subObj);
			}
		} catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
				throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Subject details with APPLICATION/JSON
	public void processSub(List<SubPojo> subData) throws Exception {
		try {
			for (int i = 0; i < subData.size(); i++) {
				Subjects sub = new Subjects();
				sub.setSubId(subData.get(i).getSubId());
				sub.setSubName(subData.get(i).getSubName());
				Subjects.AddSubjects(sub);
			}
		}
		catch (MyException e) {
			throw e;
		}
		catch(Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Professor details with TEXT/PLAIN input
	public void processProfessor(String body) throws Exception {
		try {
			Professor profObj = new Professor();
			List<String> records = new ArrayList<String>(Arrays.asList(body.split(";")));
			HashMap<String, Integer> headers = this.setHeaders(records.get(0));
			this.validateHeaders("id", "name","gender");
			for (int i = 1; i < records.size(); i++) {
				String[] eachRecord = records.get(i).split(",");
				profObj.setProfId(Integer.parseInt(eachRecord[headers.get("id")]));
				profObj.setProfName(eachRecord[headers.get("name")]);
				profObj.setProfGender(eachRecord[headers.get("gender")]);
				Professor.AddProfessor(profObj);
			}
		} 
		catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}

	// Processing Professor details with APPLICATION/JSON
	public void processProf(List<ProfPojo> proData) throws Exception {
		try {
			for (int i = 0; i < proData.size(); i++) {
				Professor prof = new Professor();
				prof.setProfId(proData.get(i).getProfId());
				prof.setProfName(proData.get(i).getProfName());
				prof.setProfGender(proData.get(i).getProfGender());
				Professor.AddProfessor(prof);
			}
		}
		catch (MyException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MyException(StatusCodes.INVALID_HEADERS);
		}
	}
}
