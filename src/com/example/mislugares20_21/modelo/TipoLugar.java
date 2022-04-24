package com.example.mislugares20_21.modelo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Enumerated Type to define kind of places
 *Cawmbios
 * @version 1.1
 * @author Carlos@Cano
 */


public enum TipoLugar implements Serializable {

	 	OTROS("Otros", 1),
	    RESTAURANTE("Restaurante", 2),
	    BAR("Bar", 3),
	    COPAS("Copas", 4),
	    ESPECTACULO("Espectáculo", 5),
	    HOTEL("Hotel", 6),
	    COMPRAS("Compras", 7),
	    EDUCACION("Educación", 8),
	    DEPORTE("Deporte", 9),
	    NATURALEZA("Naturaleza", 10),
	    GASOLINERA("Gasolinera", 11);


    private  String texto;
    private  int recurso;


     TipoLugar(String texto, int recurso) {
        
    	
    	this.texto=texto;
        this.recurso=recurso;
    }
    
    /**
     * returns the kind of place description
     *
     *
     * @return String
     */
    public String getTexto() { return texto; }
    
    
    public String setTexto(String texto) { return this.texto= texto; }

    /**
     * returns the id related to the icon that depicts the kind of place
     *
     *
     * @return int return an icon identifier
     */
    public int getRecurso() {return recurso; }
    /**
     * This methods return the array of kind of places depicted int this enumerated type
     *
     *
     * @return String [] array of TipoLugar
     */
    public  void setRecurso(int recurso) {this.recurso= recurso; }
    

    public static TipoLugar getTipoPorNombre(String nombre) {
        TipoLugar tipoRes=null;


        for (TipoLugar tipo : TipoLugar.values()) {
            if (tipo.getTexto().contains(nombre)) {

                tipoRes=tipo;
                break;
            };
        }

        return tipoRes;

    }
    

	 @Override
	    public String toString() {
	        return "TipoLugar{" +
	                "texto='" + texto + '\'' +
	                ", recurso=" + recurso +
	                '}';
	    }
  
}
