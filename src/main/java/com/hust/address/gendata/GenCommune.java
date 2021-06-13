package com.hust.address.gendata;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class GenCommune {
	public void test1() {
		try {

			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springboot", "postgres","0978122361");
			
			Statement start = connection.createStatement();
			System.out.println("connected...");
			Scanner input = new Scanner(new File("d:/Database/sscm/app/data/xa.txt"));
			System.out.println("opened");
			ResultSet rs = start.executeQuery("SELECT * FROM district");
			while (rs.next()) {
				System.out.println(rs.getString("district_id") + "\t" + rs.getString("district_name"));
			}
			
			do {
				String district_id = input.next().trim();
				String commune_id = input.next().trim();
				String commune_name = input.nextLine().trim();
				String sql = "insert into commune (commune_id , district_id , commune_name ) values ('" + commune_id + "','" + district_id + "','" + commune_name + "')";
				start.executeUpdate(sql);
			} while(input.hasNext());
			
			input.close();
			
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
	public static void main(String [] args) {
		GenCommune gen = new GenCommune();
		gen.test1();
 }
}
