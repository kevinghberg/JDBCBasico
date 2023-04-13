package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DaoFactura {
	
	public void insertarProductos(Connection bd, CSVParser parser) throws SQLException {
		for (CSVRecord row : parser) {
			int idProducto = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			float valor = Float.parseFloat(row.get("valor"));
			addProducto(bd, idProducto, nombre, valor);
		}
	}

	public static void addProducto(Connection bd, int id, String name, float valor) throws SQLException {
		String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
		PreparedStatement ps = bd.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		ps.close();
		bd.commit();
	}

	public ResultSet obtenerClientes(Connection bd) throws SQLException {
		String select = "SELECT * FROM producto";
		PreparedStatement ps = bd.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public void crearTablaProducto(Connection bd) throws SQLException {
		String table = "CREATE TABLE producto(" + "idProducto INT, " + "nombre VARCHAR(500)," + "valor FLOAT,"
				+ "PRIMARY KEY(idProducto))";
		bd.prepareStatement(table).execute();
		bd.commit();
	}


}
