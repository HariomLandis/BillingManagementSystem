package com.billing.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.billing.management.entity.Bill;
import com.billing.management.entity.Customer;

public class Dbill {
	
	Scanner sc;
	static Dproduct dp;
	static Connection con;
	static PreparedStatement ps;
	static Customer c;

	public Dbill() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		sc = new Scanner(System.in);
		dp = new Dproduct();
	}
	public static int getbillid() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select max(bid) from bill");
		ResultSet i=ps.executeQuery();
		i.next();
		int res=i.getInt(1);
		return res;
	}
	public int addbill(Bill b) throws SQLException {
		int i=Dbill.getbillid();
		System.out.println("entered into addbill");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("insert into bill values(?,?,?,?,?)");
		int id=i+1;
		ps.setInt(1,id);
		ps.setInt(2, b.getBprice());
		ps.setString(3, b.getBdate());
		ps.setString(4, b.getCnumber());
		ps.setString(5, b.getStatus());
		return ps.executeUpdate();
	}
	public List<Bill> paid(String num) throws SQLException {
		List<Bill> list=new ArrayList<Bill>();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from bill where cnumber=? and status=?");
		ps.setString(1, num);
		ps.setString(2, "not paid");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Bill b=new Bill();
			b.setBid(rs.getInt(1));
			b.setBprice(rs.getInt(2));
			b.setBdate(rs.getString(3));
			b.setCnumber(rs.getString(4));
			b.setStatus(rs.getString(5));
			list.add(b);
		}
		return list;
	}
	public List<Bill> viewbills(String num) throws SQLException {
		List<Bill> list=new ArrayList<Bill>();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from bill where cnumber=? ");
		ps.setString(1, num);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Bill b=new Bill();
			b.setBid(rs.getInt(1));
			b.setBprice(rs.getInt(2));
			b.setBdate(rs.getString(3));
			b.setCnumber(rs.getString(4));
			b.setStatus(rs.getString(5));
			list.add(b);
		}
		return list;
	}
	public List<Bill> viewallbills() throws SQLException {
		List<Bill> list=new ArrayList<Bill>();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from bill  ");
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Bill b=new Bill();
			b.setBid(rs.getInt(1));
			b.setBprice(rs.getInt(2));
			b.setBdate(rs.getString(3));
			b.setCnumber(rs.getString(4));
			b.setStatus(rs.getString(5));
			list.add(b);
		}
		return list;
	}
	public int approveBills() throws SQLException {
		System.out.println("enter unique mobile number of the customer whose bills you want to approve");
		String num=sc.next();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("update bill set status=? where cnumber=?");
		ps.setString(1, "paid");
		ps.setString(2, num);
		return ps.executeUpdate();
	}
	/*public static void main(String[] args) throws SQLException {
		//System.out.println(Dbill.getbillid());
		Date d=new Date(0);
		System.out.println(d);
		Bill b=new Bill();
		b.setBdate("ere");
		b.setBid(6);
		b.setBprice(2323);
		b.setCnumber("2323");
		System.out.println(addbill(b));
		
	}*/
}
