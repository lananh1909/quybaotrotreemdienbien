package com.hust.address.gendata;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;


public class GenProvince {
	 public void test1() {
			try {

				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springboot", "postgres","0978122361");
				
				Statement start = connection.createStatement();
				System.out.println("connected...");
				Scanner input = new Scanner(new File("d:/Database/sscm/app/data/tinh.txt"));
				System.out.println("opened");
				do {
					String province_id = input.next();
					String province_name = input.nextLine();
					String sql = "insert into province (province_id, province_name)" + " values ('" + province_id.trim() + "','" + province_name.trim() + "')";
					start.executeUpdate(sql);
				} while(input.hasNext());
				
				input.close();
				
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    }
	 public static void main(String [] args) {
		 GenProvince gen = new GenProvince();
		 gen.test1();
	 }
}
