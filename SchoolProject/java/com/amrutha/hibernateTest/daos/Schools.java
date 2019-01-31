package com.amrutha.hibernateTest.daos;

import java.util.ArrayList;
import java.util.List;

import com.amrutha.hibernateTest.pojo.*;

public class Schools {
	private List<StuPojo> stuData = new ArrayList<StuPojo>();
	private List<DeptPojo> deptData = new ArrayList<DeptPojo>();
	private List<ProfPojo> profData = new ArrayList<ProfPojo>();
	private List<KlassPojo> klassesData = new ArrayList<KlassPojo>();
	private List<SubPojo> subData = new ArrayList<SubPojo>();
	
	public List<StuPojo> getStuData() {
		return stuData;
	}
	public void setStuData(List<StuPojo> stuData) {
		this.stuData = stuData;
	}
	public List<DeptPojo> getDeptData() {
		return deptData;
	}
	public void setDeptData(List<DeptPojo> deptData) {
		this.deptData = deptData;
	}
	public List<ProfPojo> getProfData() {
		return profData;
	}
	public void setProfData(List<ProfPojo> profData) {
		this.profData = profData;
	}
	public List<KlassPojo> getKlassesData() {
		return klassesData;
	}
	public void setClaData(List<KlassPojo> klassesData) {
		this.klassesData = klassesData;
	}
	public List<SubPojo> getSubData() {
		return subData;
	}
	public void setSubData(List<SubPojo> subData) {
		this.subData = subData;
	}

}
