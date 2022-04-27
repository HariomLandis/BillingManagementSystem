package com.billing.management.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String cpass;
	private String cname;
	private String cnum;
	private String cadd;
	private List<Integer> pid;
	
	
	public Customer() {
		pid=new ArrayList<Integer>();
	}
	@Override
	public String toString() {
		return "Customer [Customer pass=" +  cpass + ", Customer name=" + cname + ", Customer number=" + cnum + ", Customer address=" + cadd
				+ "]";
	}
	
	public void add(int i) {
		this.pid.add(i);
	}

	public List<Integer> getPid() {
		return pid;
	}



	public void setPid(List<Integer> pid) {
		this.pid = pid;
	}



	public String getCpass() {
		return cpass;
	}
	public void setCpass(String cpass) {
		this.cpass = cpass;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getCadd() {
		return cadd;
	}
	public void setCadd(String cadd) {
		this.cadd = cadd;
	}
	
	
}
