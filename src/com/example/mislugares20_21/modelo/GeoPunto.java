package com.example.mislugares20_21.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class implements a Geographic Point on Earth
 *
 * @version 1.1
 * @author Carlos@Cano
 */
@XmlRootElement (name="posicion")
 public class GeoPunto implements Serializable{



	   public static final GeoPunto SIN_POSICION = new GeoPunto(0.0,0.0);
	    /**
	     * This method take a picture from the public storage, which can be Gallery or another app,
	     *
	     * @return String return latitude and longitude as a String
	     * @author Carlos Cano
	     */


	    private double longitud, latitud;


	    public GeoPunto() {
	    	
	    	
	    }


	    public GeoPunto(double longitud, double latitud) {

	        this.latitud = latitud;
	        this.longitud = longitud;

	    }

	    @Override
	    public GeoPunto clone() {


	        return new GeoPunto(longitud,latitud);
	    }

	    public static GeoPunto getPuntoSinPosicion() {

	        return SIN_POSICION.clone();
	    }


	    public double getLatitud() {
	        return latitud;
	    }




	    public void setLatitud(double latitud) {
	        this.latitud = latitud;
	    }

	    public double getLongitud() {
	        return longitud;
	    }

	    public void setLongitud(double longitud) {
	        this.longitud = longitud;
	    }


	    /**
	     * This method take a picture from the public storage, which can be Gallery or another app,
	     * @author Carlos Cano
	     *
	     *
	     * @param punto . Coordenate on Earth
	     *
	     * @return double representing the distancias between the current point and the point passed as
	     * a parameter
	     */

	    public double distancia(GeoPunto punto) {

	        final double RADIO_TIERRA = 6371000; // en metros
	        double dLat = Math.toRadians(latitud - punto.latitud);
	        double dLon = Math.toRadians(longitud - punto.longitud);
	        double lat1 = Math.toRadians(latitud);
	        double lat2 = Math.toRadians(latitud);
	        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	                Math.sin(dLon / 2) * Math.sin(dLon / 2) *
	                        Math.cos(lat1) * Math.cos(lat2);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        return c * RADIO_TIERRA;
	    }

	    @Override
	    public String toString() {
	        return "GeoPunto{" +
	                "longitud=" + longitud +
	                ", latitud=" + latitud +
	                '}';
	    }



	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        GeoPunto geoPunto = (GeoPunto) o;
	        return Double.compare(geoPunto.longitud, longitud) == 0 && Double.compare(geoPunto.latitud, latitud) == 0;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(longitud, latitud);
	    }
	}
