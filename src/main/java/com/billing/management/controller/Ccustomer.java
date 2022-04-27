package com.billing.management.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.billing.management.dao.Dbill;
import com.billing.management.dao.Dcustomer;
import com.billing.management.dao.Dproduct;
import com.billing.management.entity.Bill;
import com.billing.management.entity.Customer;
import com.billing.management.entity.Product;

public class Ccustomer {
	static Scanner sc;
	static Dproduct dp;
	static Product p;
	static Dbill db;
	static Customer c;
	static Dcustomer dc;
	public Ccustomer() throws ClassNotFoundException, SQLException {
		sc=new Scanner(System.in);
		dp=new Dproduct();
		dc=new Dcustomer();
		db=new Dbill();
	}
	
	public void register() throws ClassNotFoundException, SQLException {
		c=new Customer();
		c=chelp();
		int res=dc.register(c);
		if(res>0)
			System.out.println("Registered Successfully");
		else 
			System.out.println("Sorry could not register the user");
		
	}
	public void customerlogin() throws SQLException, ClassNotFoundException {
		c=new Customer();
		System.out.println("enter your number");
		String num=sc.next();
		c.setCnum(num);
		System.out.println("enter your password");
		String password=sc.next();
		c.setCpass(password);
		if(dc.Clogin(c)>0){
			System.out.println("Logged In Successfully");
			String s = "y";
			while(s=="y") {
				System.out.println("**************MAIN MENU***************");
				System.out.println("press 1 to update your details ");
				System.out.println("press 2 to view your details ");
				System.out.println("press 3 to delete your account");
				System.out.println("press 4 to add products to cart");
				System.out.println("press 5 to view the total price");
				System.out.println("press 6 to generate the bill");
				System.out.println("press 7 to view all the pending bills");
				System.out.println("press 8 to view all the bills.");
				System.out.println("press 9 to go back");
				int choice=sc.nextInt();
				switch(choice) {
				case 1:
					dc.updatebynum();
					break;
				case 2:
					System.out.println("enter your number");
					String num1=sc.next();
				    System.out.println(dc.viewbynum(num1));
					break;
				case 3:
					dc.delete();
					break;
				case 4:
					dc.addproducts();
					break;
				case 5:
					dc.viewBill();
					break;
				case 6:
					dc.createBill();
					break;
				case 7:
					dc.viewpaidbill();
					break;
				case 8:
					System.out.println("============Bills are============");
					for(Bill b:db.viewallbills()) {
						System.out.println(b);
					}
					break;
				case 9:
					
				case 10:
					s="exit";
					break;
				default:
					System.out.println("invalid input");
				}
			}
			
		}else
			System.out.println("sorry wrong password try again");
	}
	
	public void adminlogin() throws SQLException {
		System.out.println("enter your name");
		String name=sc.next();
		
		System.out.println("enter your password");
		String password=sc.next();
		if(name.equals("hariom") && password.equals("hariom")) {
			
			String s = "y";
			while(s=="y") {
				System.out.println("**************MAIN MENU***************");
				System.out.println("press 1 to Validate the bills ");
				System.out.println("press 2 to view all customers ");
				System.out.println("press 3 to delete customers");
				System.out.println("press 4 to add products");
				System.out.println("press 5 to delete products");
				System.out.println("press 6 to update products");
				System.out.println("press 7 to view all products");
				System.out.println("press 8 to view all the bills");
				System.out.println("press 9 to go back");
				int choice=sc.nextInt();
				switch(choice) {
				case 1:
					if(db.approveBills()>0)
						System.out.println("validated successfully");
					else
						System.out.println("could not validate check the number again");
					break;
				case 2:
					dc.viewAll();
					break;
				case 3:
					dc.delete();
					break;
				case 4:
					dp.add(phelp());
					break;
				case 5:
					dp.delete();
					break;
				case 6:
					dp.update();
					break;
				case 7:
					System.out.println(dp.viewall());
					break;
				case 8:
					
					break;
				case 9:
					s="exit";
					break;
				default:
					System.out.println("invalid input");
				}
			}
				
		}else 
			System.out.println("wrong password try again");
	}
	
	public static Customer chelp() {
		c=new Customer();
		
		System.out.println("enter your name");
		String name=sc.nextLine();
		c.setCname(name);
		
		System.out.println("enter your password");
		String pass=sc.nextLine();
		c.setCpass(pass);
		
		System.out.println("enter your number");
		String num=sc.nextLine();
		c.setCnum(num);
		
		
		System.out.println("enter your address");
		String add=sc.nextLine();
		c.setCadd(add);
		
		return c;
	}
	public Product phelp() {
		p=new Product();
		System.out.println("enter the produt id");
		int id=sc.nextInt();
		p.setPid(id);
		System.out.println("enter the product name");
		String name=sc.next();
		p.setPname(name);
		System.out.println("enter the product price");
		int price=sc.nextInt();
		p.setPprice(price);
		return p;
	}
	
}
