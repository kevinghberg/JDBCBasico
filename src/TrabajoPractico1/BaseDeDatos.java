package TrabajoPractico1;

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
		
		
		// insert
		String insert = "INSERT INTO Persona (nombre, edad) VALUES (?, ?)";
		PreparedStatement stm = conn.prepareStatement(insert);
		stm.setString(1, "Juan");
		stm.setInt(2, 30);
		stm.executeUpdate();
		
		
		//select
		String select = "SELECT * FROM Persona";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(select);
		while (rs.next()) {
		    String nombre = rs.getString("nombre");
		    int edad = rs.getInt("edad");
		    System.out.println(nombre + " tiene " + edad + " años");
		}
		
		
		
		conn.close();

	}

}
