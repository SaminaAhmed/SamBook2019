package de.hdm.sambook.server.db;

import de.hdm.sambook.shared.bo.Beitrag;
import de.hdm.sambook.shared.bo.Pinnwand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BeitragMapper {
	private static BeitragMapper bMapper = null;

	protected BeitragMapper() {
	}

	public static BeitragMapper bMapper() {
		if (bMapper == null) {
			bMapper = new BeitragMapper();
		}

		return bMapper;
	}

	public Beitrag findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT erstellungszeitpunkt, text FROM beitrag WHERE id=" + id + " ORDER BY erstellungszeitpunkt");

			if (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				b.setText(rs.getString("text"));
				return b;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Beitrag> findAll() {
		Connection con = DBConnection.connection();

		Vector<Beitrag> result = new Vector();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, erstellungszeitpunkt, text FROM beitrag  ORDER BY id");

			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				b.setText(rs.getString("text"));

				result.addElement(b);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Beitrag insert(Beitrag b) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM beitrag ");

			if (rs.next()) {

				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO beitrag (id, erstellungszeitpunkt, text) VALUES (" + b.getId() + ","
						+ b.getErstellungszeitpunkt() + "," + b.getText() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return b;
	}

	public Beitrag update(Beitrag b) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE beitrag SET erstellungszeitpunkt=\"" + b.getErstellungszeitpunkt()
					+ "SET text=\"" + b.getText() + "\" " + "WHERE id=" + b.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return b;
	}

	public void delete(Beitrag b) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM beitrag WHERE id=" + b.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<Beitrag> findByPinnwand(Pinnwand p) {
		return findByPinnwand(p.getId());

	}

	private Vector<Beitrag> findByPinnwand(int id) {
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, erstellungszeitpunkt, text" + "FROM beitrag" + "WHERE id=" + id + "ORDER BY id");
			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				b.setText(rs.getString("text"));

				result.addElement(b);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
}