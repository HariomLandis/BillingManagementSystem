package com.billing.management;

import java.util.Scanner;
import com.billing.management.controller.*;

/**
 * Hello world!
 *
 */
public class App 
{
	static Ccustomer c;
	public App() {
		
	}
    public static void main( String[] args )throws Exception
    {
    	c=new Ccustomer();
    	Scanner sc = new Scanner(System.in);
		String s = "y";
		

		while (s.equals("y")) {
			System.out.println("**************MAIN MENU***************");
			
			System.out.println("press 1 for new Registration");
			System.out.println("press 2 for login");
			System.out.println("press 3 to terminate");
			int choice=sc.nextInt();
			try {
				if(choice==1) 
					c.register();
				else if(choice==2) {
					System.out.println("press 1 if you are customer \npress 2 if your admin");
					int c2=sc.nextInt();
					if(c2==1) {
						c.customerlogin();
					}else if(c2==2) {
						c.adminlogin();
					}else
						System.out.println("wrong input try again");
				}else if(choice==3) {
					s="exit";
				}
				else
					System.out.println("wrong input try again");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
    }
}
