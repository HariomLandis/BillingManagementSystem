package com.billing.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.billing.management.entity.Customer;
import com.billing.management.entity.Product;
import com.sun.tools.javac.util.List;

public class Dproduct {
	Scanner sc;
	 static Connection con;
	 static PreparedStatement ps;
	public Dproduct()throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		sc=new Scanner(System.in);
	}
	public void add(Product p) throws SQLException  {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("insert into product values(?,?,?)");
		ps.setInt(1, p.getPid());
		ps.setString(2, p.getPname());
		ps.setInt(3, p.getPprice());
		
		if(ps.executeUpdate()>0) {
			System.out.println("product added successfully");
		}else
			System.out.println("sorry could not add the product");
	}
	public void delete() throws SQLException {
		System.out.println("enter the product id ");
		int id=sc.nextInt();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("delete from product where pid=?");
		ps.setInt(1, id);
		if(ps.executeUpdate()>0) {
			System.out.println("product deleted successfully");
		}else 
			System.out.println("Sorry could not delete the product check the id again");
	}
	public void update() throws SQLException {
		System.out.println("enter the product id ");
		int id=sc.nextInt();
		System.out.println("enter the updated product name");
		String name=sc.next();
		System.out.println("enter the product updated price");
		int price=sc.nextInt();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("update product set pname=? , pprice=? where pid=?");
		ps.setString(1, name);
		ps.setInt(2, price);
		ps.setInt(3, id);
		if(ps.executeUpdate()>0) {
			System.out.println("product updated successfully");
		}else 
			System.out.println("Sorry could not update the product check the id again");
	}
	public ArrayList<Product> viewall() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps=con.prepareStatement("select * from product");
		ArrayList<Product> l=new ArrayList<Product>();
		ResultSet rs=ps.executeQuery();
		Product cu;
		while(rs.next()) {
			cu=new Product();
			cu.setPid(rs.getInt(1));
			cu.setPname(rs.getString(2));
			cu.setPprice(rs.getInt(3));
			l.add(cu);
			//System.out.println(cu);
		}
		return l;
	}

	public int getpprice(int id) throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billmanagement", "root", "root");
		ps = con.prepareStatement("select * from product where pid=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		//int rate=rs.getInt(3);
		return rs.getInt(3);
	}
}
