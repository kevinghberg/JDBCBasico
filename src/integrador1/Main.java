package integrador1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import daoImplementacion.DaoCliente;
import daoImplementacion.DaoProducto;
import factory.FactoryDerby;

public class Main {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		String uri = "jdbc:derby:tp1;create=true";
		CSVParser parserCliente = leerCSV("D:\\TUDAI2023\\ARQWeb\\recursos\\jdbcderby\\JDBCBasicoDerby\\src\\csvs\\clientes.csv");
		CSVParser parserProducto = leerCSV("D:\\TUDAI2023\\ARQWeb\\recursos\\jdbcderby\\JDBCBasicoDerby\\src\\csvs\\productos.csv");
		
		FactoryDerby fa = new FactoryDerby();
		Connection bd = fa.conectarBD(uri);
		DaoCliente cliente = fa.getDaoCliente();
		DaoProducto producto = fa.getDaoProducto();
		
		producto.crearTablaProducto(bd);
		producto.insertarProductos(bd, parserProducto);
		
		cliente.crearTablaCliente(bd);
		cliente.insertarClientes(bd, parserCliente);

		bd.close();
		return;
	}


	private static CSVParser leerCSV(String uri) throws FileNotFoundException, IOException {
		CSVParser parser = null;
		parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(uri));
		return parser;
	}

}
