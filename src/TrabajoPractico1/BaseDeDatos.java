package TrabajoPractico1;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDeDatos {

	public static void main(String[] args) throws SQLException {

		String driver = "org.apache.derby.jdbc.EmbeddedDriver";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		String uri = "jdbc:derby:MyDerby;create=true";
		Connection conn = DriverManager.getConnection(uri);
		
		String sql = "INSERT INTO Persona (nombre, edad) VALUES (?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "Juan");
		stmt.setInt(2, 30);
		stmt.executeUpdate();
		
		String sql2 = "SELECT * FROM Persona";
		Statement stmt2 = conn.createStatement();
		ResultSet rs = stmt2.executeQuery(sql2);
		while (rs.next()) {
		    String nombre = rs.getString("nombre");
		    int edad = rs.getInt("edad");
		    System.out.println(nombre + " tiene " + edad + " años");
		}
		
		
		
		conn.close();

	}

}
