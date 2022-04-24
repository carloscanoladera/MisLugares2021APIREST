package com.example.mislugares20_21.aplicacion;

	import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.example.mislugares20_21.accesoadatos.ConexionMySQLFactory;
import com.example.mislugares20_21.accesoadatos.ConstantesBD;

import java.sql.Connection;





	@ApplicationPath("/")
	public class MisLugares_Aplicacion  extends Application  {       
		
		

	    public MisLugares_Aplicacion() {
	    	
	    	ConexionMySQLFactory.configuraConexionMySQLFactory(ConstantesBD.MYSQL);
	    	
	    	ConexionMySQLFactory.crearConexion();
	     System.out.println("Entra");   
	    
	    }
	}
	

