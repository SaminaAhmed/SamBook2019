package de.hdm.sambook.server.db;

import de.hdm.sambook.shared.bo.Nutzer;
import de.hdm.sambook.shared.bo.Pinnwand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class NutzerMapper {
	private static NutzerMapper nMapper = null;

	protected NutzerMapper() {
	}

	public static NutzerMapper nMapper() {
		if (nMapper == null) {
			nMapper = new NutzerMapper();
		}

		return nMapper;
	}

	public Nutzer findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT erstellungszeitpunkt, nickname, vorname, nachname, email FROM nutzer WHERE id=" + id
							+ " ORDER BY erstellungszeitpunkt");

			if (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				n.setNickname(rs.getString("nickname"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setEmail(rs.getString("email"));
				return n;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Pinnwand> getPinnwandOf(Nutzer n) {
		return PinnwandMapper.pMapper().findByOwner(n);
	}

	public Vector<Nutzer> findByEmail(String email) {
		Connection con = DBConnection.connection();
		Vector<Nutzer> result = new Vector<Nutzer>();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT erstellungszeitpunkt, nickname, vorname, nachname, email FROM nutzer WHERE id=" + email
							+ " ORDER BY erstellungszeitpunkt");

			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				n.setNickname(rs.getString("nickname"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setEmail(rs.getString("email"));
				result.addElement(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Nutzer> findAll() {
		Connection con = DBConnection.connection();

		Vector<Nutzer> result = new Vector();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, erstellungszeitpunkt, nickname, vorname, nachname, email FROM nutzer  ORDER BY id");

			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				n.setNickname(rs.getString("nickname"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setEmail(rs.getString("email"));

				result.addElement(n);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Nutzer insert(Nutzer n) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM nutzer ");

			if (rs.next()) {

				n.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO nutzer (id, erstellungszeitpunkt, nickname, vorname, nachname, email) VALUES ("
								+ n.getId() + "," + n.getErstellungszeitpunkt() + "," + n.getNickname() + ","
								+ n.getVorname() + "," + n.getNachname() + "," + n.getEmail() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return n;
	}

	public Nutzer update(Nutzer n) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE nutzer SET erstellungszeitpunkt=\"" + n.getErstellungszeitpunkt()
					+ "SET nickname=\"" + n.getNickname() + "SET vorname=\"" + n.getVorname() + "SET nachname=\""
					+ n.getNachname() + "SET email=\"" + n.getEmail() + "\"" + "WHERE id=" + n.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return n;
	}

	public void delete(Nutzer n) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM nutzer WHERE id=" + n.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<Nutzer> findByPinnwand(Pinnwand p) {

		return findByPinnwand(p.getId());
	}

	private Vector<Nutzer> findByPinnwand(int id) {
		Connection con = DBConnection.connection();
		Vector<Nutzer> result = new Vector<Nutzer>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, nickname, vorname, nachname, email FROM nutzer" + "WHERE id=" + id + "ORDER BY id");
			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				n.setNickname(rs.getString("nickname"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setEmail(rs.getString("email"));

				result.addElement(n);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;

	}
}