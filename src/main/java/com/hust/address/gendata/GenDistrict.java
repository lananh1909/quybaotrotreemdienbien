package com.hust.address.gendata;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class GenDistrict {
	public void test1() {
		try {

			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springboot", "postgres","0978122361");
			
			Statement start = connection.createStatement();
			System.out.println("connected...");
			Scanner input = new Scanner(new File("d:/Database/sscm/app/data/huyen.txt"));
			System.out.println("opened");
			do {
				String district_id = input.next();
				String province_id = input.next();
				String district_name = input.nextLine();
				System.out.println("/"+province_id+"/");
				String sql = "insert into district (district_id, province_id, district_name)" + " values ('" + district_id.trim() + "','" + province_id.trim() + "','" + district_name.trim() + "')";
				start.executeUpdate(sql);
			} while(input.hasNext());
			
			input.close();
			
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
	public static void main(String [] args) {
		GenDistrict gen = new GenDistrict();
		gen.test1();
 }
}
		 
