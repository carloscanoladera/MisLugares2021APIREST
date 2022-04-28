package com.example.mislugares20_21.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lugar lugar = new Lugar();
		
		lugar.setPosicion(new GeoPunto(0,0));
		lugar.setTipo(TipoLugar.BAR);
		
		List<Lugar> lista = new ArrayList<Lugar>();
		
		lista.add(lugar);
		
		Lugares lugares = new Lugares(lista);
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance( Lugares.class );
			
			Marshaller jaxbMarshaller   = jaxbContext.createMarshaller();
			
			jaxbMarshaller.marshal(lugares, new File( "Lugares.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
