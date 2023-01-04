package com.api.entity;

import java.sql.Date;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {

    @Id
    private Integer EmpId;

    private String Name;

    private String NCSID;

    private String NCSEmail;
    
//    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date BirthDayAndMonth;
    
    

	public Employee() {
		super();
	}

	public Employee(Integer empId, String name, String nCSID, String nCSEmail, Date birthDayAndMonth) {
		super();
		EmpId = empId;
		Name = name;
		NCSID = nCSID;
		NCSEmail = nCSEmail;
		BirthDayAndMonth = birthDayAndMonth;
	}

	public Integer getEmpId() {
		return EmpId;
	}

	public void setEmpId(Integer empId) {
		EmpId = empId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNCSID() {
		return NCSID;
	}

	public void setNCSID(String nCSID) {
		NCSID = nCSID;
	}

	public String getNCSEmail() {
		return NCSEmail;
	}

	public void setNCSEmail(String nCSEmail) {
		NCSEmail = nCSEmail;
	}

	public java.util.Date getBirthDayAndMonth() {
		return BirthDayAndMonth;
	}

	public void setBirthDayAndMonth(java.util.Date date) {
		BirthDayAndMonth = date;
	}


}
