package com.example.mislugares20_21.accesoadatos;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


import com.example.mislugares20_21.modelo.Lugar;

public interface LugaresDAO {
	
	

	
	public int InsertarLugar(Lugar lugar) throws SQLException ;
	
	
	public int borrarLugar(Lugar lugar) throws SQLException;
	
	public Lugar leerLugar(int Id) throws SQLException;
	
	
	public int modificarLugar(Lugar lugar) throws SQLException;
	
	public List<Lugar> listaLugares() throws SQLException ;
	


}
