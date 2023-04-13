package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import daoImplementacion.DaoCliente;
import daoImplementacion.DaoProducto;

public class FactoryDerby implements Factory {
	
	
	public Connection conectarBD(String uri) {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			Connection conn = DriverManager.getConnection(uri);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoCliente getDaoCliente() {
		return new DaoCliente();
	}

	public DaoProducto getDaoProducto() {
		return new DaoProducto();
	}
}
