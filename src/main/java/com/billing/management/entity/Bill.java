package com.billing.management.entity;

import java.sql.Date;

public class Bill {
	private int bid;
	private int bprice;
	private String bdate;
	private String status;
	private String cnumber;
	
	
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getBprice() {
		return bprice;
	}
	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String d) {
		this.bdate = d;
	}
	
	@Override
	public String toString() {
		return "Bill [bill id=" + bid + ", bill price=" + bprice + ", bill date=" + bdate + ", bill status=" + status + "]";
	}
	
}
