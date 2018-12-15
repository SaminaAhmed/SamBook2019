package de.hdm.sambook.server.db;

import com.google.appengine.api.utils.SystemProperty;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection con = null;

	private static String localUrl = "jdbc:mysql://127.0.0.1:3306/sambook?user=demo&password=demo";

	public DBConnection() {
	}

	public static Connection connection() {
		if (con == null) {
			String url = null;
			try {
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {

					Class.forName("com.mysql.jdbc.GoogleDriver");
				} else {
					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;
				}

				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}

		return con;
	}
}