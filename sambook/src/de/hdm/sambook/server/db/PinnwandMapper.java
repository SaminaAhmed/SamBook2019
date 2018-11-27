package de.hdm.sambook.server.db;

import de.hdm.sambook.shared.bo.Nutzer;
import de.hdm.sambook.shared.bo.Pinnwand;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class PinnwandMapper {
	private static PinnwandMapper pMapper = null;

	protected PinnwandMapper() {
	}

	public static PinnwandMapper pMapper() {
		if (pMapper == null) {
			pMapper = new PinnwandMapper();
		}

		return pMapper;
	}

	public Pinnwand findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT erstellungszeitpunkt FROM pinnwand WHERE id=" + id + " ORDER BY erstellungszeitpunkt");

			if (rs.next()) {
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));
				return p;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Pinnwand> findAll() {
		Connection con = DBConnection.connection();

		Vector<Pinnwand> result = new Vector();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, erstellungszeitpunkt FROM pinnwand  ORDER BY id");

			while (rs.next()) {
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setErstellungszeitpunkt(rs.getDate("erstellungszeitpunkt"));

				result.addElement(p);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}
	
	public Vector<Pinnwand> findByOwner(Nutzer owner){
		return findByOwner(owner.getId());
	}

	public Vector<Pinnwand> findByOwner(int ownerID){
		Connection con = DBConnection.connection();
		Vector<Pinnwand> result = new Vector<Pinnwand>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM pinnwand" +
			"WHERE owner=" + ownerID + "ORDER BY id");
			
		while(rs.next()) {
			Pinnwand p = new Pinnwand();
			p.setId(rs.getInt("id"));
			p.setOwnerID(rs.getInt("owner"));
			result.addElement(p);
		}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		 return result;
	}
	
	
	public Pinnwand insert(Pinnwand p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM pinnwand ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO pinnwand (id, erstellungszeitpunkt) VALUES (" + p.getId() + ","
						+ p.getErstellungszeitpunkt() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return p;
	}

	public Pinnwand update(Pinnwand p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE pinnwand SET erstellungszeitpunkt=\"" + p.getErstellungszeitpunkt() + "\" "
					+ "WHERE id=" + p.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return p;
	}

	public void delete(Pinnwand p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM pinnwand WHERE id=" + p.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}