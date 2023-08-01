package com.example.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/event", "root", "Guru@1311");
		Event_manage ev=new Event_manage(connection);
		ev.mainop();
	}
}
