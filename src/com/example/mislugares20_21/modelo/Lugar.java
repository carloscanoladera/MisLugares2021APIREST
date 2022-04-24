package com.example.mislugares20_21.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This Class implements place, locations.
 *
 * @version 1.1
 * @author Carlos@Cano
 * @since 1.0
 */
public class Lugar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4898561824002428353L;


    private int id;
    private String nombre="";

    private String direccion="";

    @JsonProperty("posicion")
    private GeoPunto posicion;
    private TipoLugar tipo;

    private String foto="";

    private long telefono;
    private String url="";
    private String comentario="";
    private long fecha;
    private float valoracion;


    
    
	public Lugar() {
	     fecha = System.currentTimeMillis();
	     posicion= new GeoPunto(0,0);
	     
	    }
    
    

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns an object TipoLugar
     *
     *
     * @return TipoLugar the kind o Lugar
     */

    public TipoLugar getTipo() {
        return tipo;
    }

    /**
     * This method changes/sets the kind of place
     *
     * @author Carlos Cano
     *
     * @param tipo kind of place
     *
     * @return void
     */

    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    /**
     * This method returns the place name
     *
     *
     * @return String the object Lugar name
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * This method changes/sets the name of the place
     *
     *
     * @param nombre Name of the place
     *
     * @return void
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * This method returns an Address
     *
     *
     * @return String the object Lugar address
     */

    public GeoPunto getPosicion() {
        return posicion;
    }

    public void setPosicion(GeoPunto posicion) {
        this.posicion = posicion;
    }
    public String getDireccion() {
        return direccion;
    }

    /**
     * This method changes/sets the address of the place
     *
     *
     * @param direccion Place Address
     *                      *
     * @return void
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * This method returns a phone number
     *
     *
     * @return int telephone number
     */

    public long getTelefono() {
        return telefono;
    }

    /**
     * This method changes/sets the address of the place
     * @author Carlos Cano
     *
     *
     * @param  telefono telephone number
     *                      *
     * @return void
    */

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * This method returns the photo url from this place
     * @author Carlos Cano
     *
     *
     * @return String the photo url
     */
    public String getFoto() {
        return foto;
    }

    /**
     * This method changes/sets the photo from the place
     * @author Carlos Cano
     *
     *
     * @param  foto Address
     *                      *
     * @return void
     */

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * This method return the Lugar (place) website address
     *
     *
     * @return String the website address of this Lugar
     */

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method return Lugar comments
     *
     *
     * @return String comments from this Lugar
     */

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * This method return lsat visit date to this Lugar
     *
     *
     * @return long last visit date to the place
     */

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    /**
     * This method return the rate for this Lugar
     *
     *
     * @return float the rate for the place
     */

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
    
    
  


    @Override
    public String toString() {
        return "Lugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", posicion=" + posicion +
                ", tipo=" + tipo +
                ", foto='" + foto + '\'' +
                ", telefono=" + telefono +
                ", url='" + url + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", valoracion=" + valoracion +
                '}';
    }

  

    




}




