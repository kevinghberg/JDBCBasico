package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DaoCliente {
	
	public void insertarClientes(Connection bd, CSVParser parser) throws SQLException {
		for (CSVRecord row : parser) {
			int idCliente = Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
			addCliente(bd,idCliente,nombre,email);
		}
	}


	public static void addCliente(Connection bd, int id, String name, String email) throws SQLException {
		String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES(?, ?, ?)";
		PreparedStatement ps = bd.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.executeUpdate();
		ps.close();
		bd.commit();
	}
	
	public ResultSet obtenerClientes(Connection bd) throws SQLException {
		String select = "SELECT * FROM cliente";
		PreparedStatement ps = bd.prepareStatement(select);
		ResultSet rs = ps.executeQuery();

		return rs;
	}
	
	public void crearTablaCliente(Connection bd) throws SQLException {
		String table = "CREATE TABLE cliente(" + "idCliente INT, " + "nombre VARCHAR(500)," + "email VARCHAR(500)," + "PRIMARY KEY(idCliente))";
		bd.prepareStatement(table).execute();
		bd.commit();
	}
	
}
