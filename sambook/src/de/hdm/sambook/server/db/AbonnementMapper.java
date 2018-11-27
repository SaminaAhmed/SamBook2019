package de.hdm.sambook.server.db;

import de.hdm.sambook.shared.bo.Abonnement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class AbonnementMapper {
	private static AbonnementMapper aMapper = null;

	protected AbonnementMapper() {
	}

	public static AbonnementMapper aMapper() {
		if (aMapper == null) {
			aMapper = new AbonnementMapper();
		}

		return aMapper;
	}

	public Abonnement findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT erstellungszeitpunkt FROM abonnement WHERE id=" + id + " ORDER BY erstellungszeitpunkt");

			if (rs.next()) {
				Abonnement a = new Abonnement();
				a.setId(rs.getInt("id"));
				a.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Abonnement> findAll() {
		Connection con = DBConnection.connection();

		Vector<Abonnement> result = new Vector();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, erstellungszeitpunkt FROM abonnement  ORDER BY id");

			while (rs.next()) {
				Abonnement a = new Abonnement();
				a.setId(rs.getInt("id"));
				a.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));

				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Abonnement insert(Abonnement a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM abonnement ");

			if (rs.next()) {

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO abonnement (id, erstellungszeitpunkt) VALUES (" + a.getId() + ","
						+ a.getErstellungszeitpunkt() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return a;
	}

	public Abonnement update(Abonnement a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE abonnement SET erstellungszeitpunkt=\"" + a.getErstellungszeitpunkt() + "\" "
					+ "WHERE id=" + a.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return a;
	}

	public void delete(Abonnement a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM abonnement WHERE id=" + a.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}