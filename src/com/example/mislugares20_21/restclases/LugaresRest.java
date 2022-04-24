package com.example.mislugares20_21.restclases;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PATCH;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.mislugares20_21.accesoadatos.ConexionMySQLFactory;
import com.example.mislugares20_21.accesoadatos.LugaresDAO;
import com.example.mislugares20_21.modelo.Lugar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* para REtrofit 2*/

@Path("/lugares")
@Produces(MediaType.APPLICATION_JSON)
public class LugaresRest {

	public static int ID_FAILED = -1;

	@GET
	@Path("/lugareslista")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLugares() {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();

		List<Lugar> lista = null;
		try {
			lista = dao.listaLugares();
			
			if (lista==null) {
				
				return Response.status(Status.BAD_REQUEST).entity("Lugares not found").build();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(lista).build();

	}
	
	@GET
	@Path("/lugar/{lugarid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLugarPorId(@PathParam("lugarid") String lugarid) {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();

		int id =Integer.valueOf(lugarid);

		Lugar lugar = null;
		try {
			lugar = dao.leerLugar(id);
			
			if (lugar==null) {
				
				return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(lugar).build();

	}


	@POST
	@Path("/creaLugar")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")

	public Response creaLugar(Lugar lugar) {
		int id = -1;

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();

		ObjectMapper objMapper = new ObjectMapper();
		String entityLugar = "";
		try {
			id = dao.InsertarLugar(lugar);
			lugar.setId(id);

			entityLugar = objMapper.writeValueAsString(lugar);
		} catch (JsonProcessingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Status.CREATED).entity(entityLugar).build();

	}

	@PUT
	@Path("/actualizalugar/{lugarid}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response actualizarLugarPut(@PathParam("lugarid") String lugarid, Lugar lugar) {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();
		int id= Integer.valueOf(lugarid);
		
		try {
			Lugar lugarEncontrado = dao.leerLugar(id);

			if (lugarEncontrado != null) {

				dao.modificarLugar(lugar);
				return Response.ok().build();

			}

			return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();

		}

	}
	
	@PATCH
	@Path("/patchlugar/{lugarid}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response patchLugar(@PathParam("lugarid") String lugarid, Lugar lugar) {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();
		int id = Integer.valueOf(lugarid);
		try {
			Lugar lugarEncontrado = dao.leerLugar(id);

			if (lugarEncontrado != null) {

				dao.modificarLugar(lugar);
				return Response.ok().build();

			}

			return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();

		}

	}

	@DELETE
	@Path("/deletelugar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrarLugarDelete(Lugar lugar) {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();
		int id = ID_FAILED;
		try {
			id = dao.borrarLugar(lugar);

			if (id == ID_FAILED) {

				return Response.status(Status.BAD_REQUEST).entity("Lugar not found").build();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(lugar).build();

	}

	/*
	 * 
	 * Mas comodo para RETROFIT 1 Y LA APLICACION ANDROID
	 * 
	 */

	@POST
	@Path("/insertaLugar")

	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")

	public Integer insertaLugar(Lugar lugar) {
		int id = ID_FAILED;

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();

		try {
			id = dao.InsertarLugar(lugar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return id;
		}
		lugar.setId(id);

		return id;

	}

	@POST
	@Path("/traelugar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lugar getLugaresId(Integer id) {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();

		Lugar lugar = null;
		try {
			lugar = dao.leerLugar(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lugar;

	}

	@POST
	@Path("/borralugar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int borrarLugar(Lugar lugar) {

		LugaresDAO dao = ConexionMySQLFactory.getDAOLugares();

		try {
			return dao.borrarLugar(lugar);
		} catch (SQLException e) {
			e.printStackTrace();
			return ID_FAILED;
			// TODO Auto-generated catch block

		}

	}

}
