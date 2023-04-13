package factory;

import java.sql.Connection;

import daoImplementacion.DaoCliente;

public interface Factory {

	public Connection conectarBD(String uri);
	
	public DaoCliente getDaoCliente();
	
}