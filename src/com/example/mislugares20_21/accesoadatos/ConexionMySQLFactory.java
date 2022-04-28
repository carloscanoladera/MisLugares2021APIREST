package com.example.mislugares20_21.accesoadatos;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConexionMySQLFactory {
    private static Connection conexion = null;
    private static String DRIVER = "";
    private static String URLDB = "";
    private static String DB_NOMBRE="lugares";
    private static String USUARIO = "lugares";
    private static String CLAVE = "lugares";
    private static String CLOUD_SQL_CONNECTION_NAME="civic-matrix-279208:europe-west3:mislugares";
    private static DataSource pool=null;
    private static boolean nube= false;

    public static  void configuraConexionMySQLFactory(int BDSQL) {
        if (BDSQL == ConstantesBD.MYSQL) {
            DRIVER = "com.mysql.jdbc.Driver";
            URLDB = "jdbc:mysql://localhost:3307/mislugares";
            //System.out.println("mysql");
            nube=false;

        }
        else {

            nube=true;
            DRIVER = "com.mysql.jdbc.Driver";
            URLDB = "jdbc:mysql://34.89.184.131:3306/lugares";


        }


    }//constructor

    // crear la conexion
    public static Connection crearConexion() {
        try {

        if (!nube &&conexion == null) {
                Class.forName(DRIVER); // Cargar el driver

                conexion = (Connection) DriverManager.getConnection(URLDB, USUARIO, CLAVE);
            }
         else if (nube && conexion==null) {



        }  } catch (SQLException ex) {
            System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
            System.out.printf("Mensaje   : %s %n", ex.getMessage());
            System.out.printf("SQL estado: %s %n", ex.getSQLState());
            System.out.printf("CÓd error : %s %n", ex.getErrorCode());


        }
        catch (ClassNotFoundException ex) {
        //Logger.getLogger(SQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);

        System.out.printf("HA OCURRIDO UNA EXCEPCI�N:%n");
    }

        return conexion;
    }
    
    
    public static LugaresDAO getDAOLugares () {
    	
    	
    	return new LugaresDAOMySQL();
    	
    }



}
