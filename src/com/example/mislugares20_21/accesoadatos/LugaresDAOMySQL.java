package com.example.mislugares20_21.accesoadatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.mislugares20_21.modelo.GeoPunto;
import com.example.mislugares20_21.modelo.Lugar;
import com.example.mislugares20_21.modelo.TipoLugar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LugaresDAOMySQL implements LugaresDAO {

	Connection conexion;

	public LugaresDAOMySQL() {

		conexion = ConexionMySQLFactory.crearConexion();
	}

	@Override
	public int InsertarLugar(Lugar lugar) throws SQLException {
		// TODO Auto-generated method stub

		int idGenerado = 0;

		// nombre, codpais, tasapago, sumaimpuestos, numerohabitantes
		String sql = "INSERT INTO lugares "
				+ "( nombre, direccion, longitud,latitud,tipo,foto,telefono,url,comentario,fecha,valoracion) "
				+ "VALUES ( ?, ? ,? , ?,?, ? , ?, ? ,? , ?,?)";

		PreparedStatement sentencia;

		sentencia = (PreparedStatement) conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		preparaSentencia(sentencia, lugar);

		int filas = sentencia.executeUpdate();
		if (filas > 0) {
			idGenerado = 0;
		}

		ResultSet generatedKeys = sentencia.getGeneratedKeys();
		if (generatedKeys.next()) {
			idGenerado = generatedKeys.getInt(1);
		}
		sentencia.close();

		return idGenerado;

	}

	@Override
	public int borrarLugar(Lugar lugar) throws SQLException {

		int resultado = 0;

		// TODO Auto-generated method stub
		String sql = "DELETE FROM lugares WHERE _id=" + lugar.getId();

		PreparedStatement sentencia;

		sentencia = (PreparedStatement) conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		int filas = sentencia.executeUpdate();
		if (filas > 0) {
			resultado = 1;
		}

		return resultado;
	}

	@Override
	public Lugar leerLugar(int Id) throws SQLException {
		// TODO Auto-generated method stub

		Lugar lugar = null;

		Statement sentencia = (Statement) conexion.createStatement();
		String sql = "SELECT * FROM lugares where _id=" + Id;
		ResultSet resul = sentencia.executeQuery(sql);

		if (resul.next()) {
			lugar = construyeLugar(resul);

		}

		return lugar;

	}

	@Override
	public int modificarLugar(Lugar lugar) throws SQLException {
		// TODO Auto-generated method stub

		int resultado = 0;

		// nombre, codpais, tasapago, sumaimpuestos, numerohabitantes
		String sql = "UPDATE  lugares "
				+ "set nombre=?,  direccion= ?,  longitud = ?,  latitud=?,  tipo=?,  foto=?,  telefono=?,url=?,comentario=?,fecha=?,valoracion=? "
				+ " WHERE _id=" + lugar.getId();

		PreparedStatement sentencia;

		sentencia = (PreparedStatement) conexion.prepareStatement(sql);
		preparaSentencia(sentencia, lugar);

		int filas = sentencia.executeUpdate();
		if (filas > 0) {
			resultado = 1;
		}

		sentencia.close();

		return resultado;
	}

	@Override
	public List<Lugar> listaLugares() throws SQLException {
		List<Lugar> lista = new ArrayList<Lugar>();

		Statement sentencia = (Statement) conexion.createStatement();
		String sql = "SELECT * FROM lugares";
		ResultSet resul = sentencia.executeQuery(sql);

		while (resul.next()) {
			Lugar lugar = construyeLugar(resul);

			lista.add(lugar);

		}

		return lista;
	}

	private void MensajeExcepcion(SQLException e) {
		System.out.printf("HA OCURRIDO UNA EXCEPCI�N:%n");
		System.out.printf("Mensaje   : %s %n", e.getMessage());
		System.out.printf("SQL estado: %s %n", e.getSQLState());
		System.out.printf("C�d error : %s %n", e.getErrorCode());
	}

	private Lugar construyeLugar(ResultSet resul) throws SQLException {

		Lugar lugar = new Lugar();

		lugar.setId(resul.getInt("_id"));

		lugar.setNombre(resul.getString("nombre"));
		lugar.setDireccion(resul.getString("direccion"));
		lugar.setPosicion(new GeoPunto(resul.getDouble("longitud"), resul.getDouble("latitud")));

		lugar.setTipo(TipoLugar.values()[resul.getInt("tipo")]);
		lugar.setFoto(resul.getString("foto"));
		lugar.setTelefono(resul.getLong("telefono"));
		lugar.setUrl(resul.getString("url"));
		lugar.setComentario(resul.getString("comentario"));
		lugar.setFecha(resul.getLong("fecha"));
		lugar.setValoracion(resul.getFloat("valoracion"));

		return lugar;
	}

	private PreparedStatement preparaSentencia(PreparedStatement sentencia, Lugar lugar) throws SQLException {

		sentencia.setString(1, lugar.getNombre());
		sentencia.setString(2, lugar.getDireccion());
		sentencia.setDouble(3, lugar.getPosicion().getLongitud());
		sentencia.setDouble(4, lugar.getPosicion().getLatitud());
		sentencia.setInt(5, lugar.getTipo().getRecurso());
		sentencia.setString(6, lugar.getFoto());
		sentencia.setLong(7, lugar.getTelefono());
		sentencia.setString(8, lugar.getUrl());
		sentencia.setString(9, lugar.getComentario());
		sentencia.setLong(10, lugar.getFecha());
		sentencia.setFloat(11, lugar.getValoracion());

		return sentencia;

	}

}
