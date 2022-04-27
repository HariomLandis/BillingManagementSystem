package com.billing.management.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.billing.management.entity.Bill;
import com.billing.management.entity.Customer;
import com.billing.management.entity.Product;

public class Dcustomer {
	Scanner sc;
	static Dbill db;
	static int sum=0;
	static Dproduct dp;
	 static Connection con;
	 static PreparedStatement ps;
	 static Customer c;
	public Dcustomer() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		sc=new Scanner(System.in);
		dp=new Dproduct();
		db=new Dbill();
	}
	

	public int register(Customer c) throws SQLException, ClassNotFoundException {
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("insert into customer values(?,?,?,?)");
		ps.setString(1, c.getCnum());
		ps.setString(2, c.getCname());
		ps.setString(3, c.getCpass());
		ps.setString(4, c.getCadd());
		return ps.executeUpdate();
	}
	public int Clogin(Customer c) throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from customer where cnumber=? and cpassword=?");
		ps.setString(1, c.getCnum());
		ps.setString(2, c.getCpass());
		ResultSet rs=ps.executeQuery();
		if(rs.next()) 
			return 1;
		return 0;
	}
	public void viewAll() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from customer");
		//List<Customer> l=new ArrayList<Customer>();
		ResultSet rs=ps.executeQuery();
		Customer cu;
		while(rs.next()) {
			cu=new Customer();
			cu.setCadd(rs.getString(4));
			cu.setCname(rs.getString(2));
			cu.setCpass(rs.getString(3));
			cu.setCnum(rs.getString(1));
			System.out.println(cu);
		}
	}
	public void delete() throws SQLException {
		System.out.println("enter the customer unique number you want to delete");
		String num=sc.next();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("delete from customer where cnumber=?");
		ps.setString(1, num);
		
		if(ps.executeUpdate()>0)
			System.out.println("Customer removed from the database");
		else
			System.out.println("sorry customer could not be deleted check the number");
	}
	public Customer viewbynum(String num) throws SQLException {
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from customer where cnumber=?");
		ps.setString(1, num);
		ResultSet rs=ps.executeQuery();
		rs.next();
		c=new Customer();
		c.setCnum(rs.getString(1));
		c.setCadd(rs.getString(4));
		c.setCname(rs.getString(2));
		c.setCpass(rs.getString(3));
		//System.out.println(c);
		return c;
	}
	public void updatebynum() throws SQLException {
		System.out.println("enter your uninque mobile number");
		String num=sc.next();
		
		
		System.out.println("enter your updated name");
		String name=sc.next();
		
		
		System.out.println("enter your updated password");
		String pass=sc.next();
		
		System.out.println("enter your updated address");
		String add=sc.next();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("update customer set cname=? , cpassword=? ,caddress=? where cnumber=?");
		ps.setString(1, name);
		ps.setString(2, pass);
		ps.setString(3, add);
		ps.setString(4, num);
		if(ps.executeUpdate()>0) {
			System.out.println("details updated successfully");
		}else 
			System.out.println("Sorry could not update the details check the number again");
	}
	
	public void addproducts() throws SQLException, ClassNotFoundException {
		Dcustomer dc=new Dcustomer();
		ArrayList<Product> list=new ArrayList<Product>();
		System.out.println("enter your uninque mobile number");
		String num=sc.next();
		Customer c=dc.viewbynum(num);
		
		list=dp.viewall();
		String ans="y";
		while(ans=="y") {
			System.out.println("======================================================");
			
			for(Product p:list)
				System.out.println(p);
			
			System.out.println("======================================================");
			System.out.println("choose the id of the product you want:-");
			int choice=sc.nextInt();
			c.add(choice);
			System.out.println("do you want to shop more\npress 1 for yes \npress 2 form no");
			int c3=sc.nextInt();
			if(c3==1)
				ans="y";
			else
				ans="n";
				
		}
		System.out.println(c.getPid() +" are id of the products you have purchased");
		ArrayList<Integer> list1=(ArrayList<Integer>) c.getPid();
		for(int i:list1) {
			int rate=dp.getpprice(i);
			sum+=rate;
		}
		
	}
	public void viewBill( ) throws ClassNotFoundException, SQLException {
		
		System.out.println("you have to pay "+sum+" INR");
		
	}
	public void createBill() throws ClassNotFoundException, SQLException {
		System.out.println("enter your uninque mobile number");
		String num=sc.next();
		Date d=new Date(0);
		db=new Dbill();
		Bill b=new Bill();
		b.setBdate(d.toString());
		b.setStatus("not paid");
		b.setBprice(sum);
		b.setCnumber(num);
		int i=db.addbill(b);
		if(i>0)
			System.out.println("Bill successfully generated and saved in database");
		else 
			System.out.println("Sorry could not generate your bill");
	}
	public void viewpaidbill() throws SQLException {
		System.out.println("enter your uninque mobile number");
		String num=sc.next();
		List<Bill> list=db.paid(num);
		if(list.size()>0) {
			System.out.println("Your Unpaid bills are");
			for(Bill b:list)
				System.out.println(b);
		}else
			System.out.println("you have paid all the bills");
	}
	public void viweallbill() throws SQLException {
		System.out.println("enter your uninque mobile number");
		String num=sc.next();
		List<Bill> list=db.viewbills(num);
		if(list.size()>0) {
			System.out.println("Your bills are");
			for(Bill b:list)
				System.out.println(b);
		}else
			System.out.println("you don't have any bills on your account");
	}
	
}
