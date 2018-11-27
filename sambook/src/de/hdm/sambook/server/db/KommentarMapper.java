package de.hdm.sambook.server.db;

import de.hdm.sambook.shared.bo.Beitrag;
import de.hdm.sambook.shared.bo.Kommentar;
import de.hdm.sambook.shared.bo.Nutzer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class KommentarMapper {
	private static KommentarMapper kMapper = null;

	protected KommentarMapper() {
	}

	public static KommentarMapper kMapper() {
		if (kMapper == null) {
			kMapper = new KommentarMapper();
		}

		return kMapper;
	}

	public Kommentar findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT erstellungszeitpunkt, text FROM kommentar WHERE id=" + id
					+ " ORDER BY erstellungszeitpunkt");

			if (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				k.setText(rs.getString("text"));
				return k;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Kommentar> findAll() {
		Connection con = DBConnection.connection();

		Vector<Kommentar> result = new Vector();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, erstellungszeitpunkt, text FROM kommentar  ORDER BY id");

			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				k.setText(rs.getString("text"));

				result.addElement(k);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

//	public Vector<Kommentar> findByBeitrag(Beitrag b) {
//		return findByBeitrag(b.getId());
//	}
//	
//	public Vector<Kommentar> findByBeitrag(Nutzer n) {
//		return findByBeitrag(n.getId());
//	}

	public Vector<Kommentar> findByBeitrag(int id) {
		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, erstellungszeitpunkt, text" + "FROM kommentar" + "WHERE id =" + id + "ORDER BY id");
			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				k.setText(rs.getString("text"));

				result.addElement(k);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	

	public Kommentar insert(Kommentar k) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM kommentar ");

			if (rs.next()) {

				k.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO kommentar (id, erstellungszeitpunkt, text) VALUES (" + k.getId() + ","
						+ k.getErstellungszeitpunkt() + "," + k.getText() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return k;
	}

	public Kommentar update(Kommentar k) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE kommentar SET erstellungszeitpunkt=\"" + k.getErstellungszeitpunkt()
					+ "SET text=\"" + k.getText() + "\" " + "WHERE id=" + k.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return k;
	}

	public void delete(Kommentar k) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM kommentar WHERE id=" + k.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}