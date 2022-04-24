package com.example.mislugares22.datos;

import android.os.StrictMode;

import androidx.annotation.NonNull;

import com.example.mislugares22.modelo.Lugar;
import com.example.mislugares22.modelo.RepositorioLugares;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class LugaresBDApiRest implements RepositorioLugares {

    private static String URL_BASE= "http://192.168.1.43:8080/mislugares20_21/lugares";

    public LugaresBDApiRest() {


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
                permitNetwork().build());

    }
    @Override
    public Lugar elemento(int id) {
        return getLugarPorId(id);
    }

    @Override
    public void anyade(Lugar lugar) {

       insertaLugar(lugar);

    }

    @Override
    public int nuevo() {

        Lugar lugar = new Lugar();
        insertaLugar(lugar);

        return lugar.getId();
    }

    @Override
    public void borrar(Lugar lugar) {
        borraLugaresDelete(lugar);
    }

    @Override
    public int tamanyo() {
        return getListaLugares().size();
    }

    @Override
    public void actualiza(int id, Lugar lugar) {
        actualizaLugaresUpdate(lugar);
    }

    @Override
    public List<Lugar> listaLugares() {

     return getListaLugares();
    }

    @Override
    public Lugar getElementoPorPosicion(int pos) {
        return getListaLugares().get(pos);
    }

    @NonNull
    @Override
    public Iterator<Lugar> iterator() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Lugar next() {
        return null;
    }



    private List<Lugar> getListaLugares() {

        List<Lugar> lista= null;
        URL url=null;

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            url= new URL(URL_BASE+"/lugareslista");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }




        HttpURLConnection conexion = null;

        try {
            conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Accept", "application/json");

            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conexion.getInputStream()));
                String listaLugaresJson = reader.readLine();

                lista = objectMapper.readValue(listaLugaresJson,new TypeReference<List<Lugar>>() {});

                reader.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }


    private Lugar getLugarPorId(int id) {

        Lugar lugar= null;
        URL url=null;

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            url= new URL(URL_BASE+"/lugar/" + id);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }




        HttpURLConnection conexion = null;

        try {
            conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Accept", "application/json");

            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conexion.getInputStream()));
                String listaLugaresJson = reader.readLine();

                lugar = objectMapper.readValue(listaLugaresJson,new TypeReference<Lugar>() {});

                reader.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return lugar;
    }

    private void insertaLugar(Lugar lugar) {

        URL url=null;
        String stringLugar="";
        Lugar lugarSalvado=null;
        ObjectMapper objectMapper=null;

        try {
            url= new URL(URL_BASE+"/creaLugar");

            objectMapper = new ObjectMapper();
// Convertimos la colección de datos a un String JSON

            stringLugar = objectMapper.writeValueAsString(lugar);
        } catch (JsonProcessingException | MalformedURLException e) {
            e.printStackTrace();
        }




        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setDoOutput(true);
            try(OutputStream os = conexion.getOutputStream()) {
                byte[] input = stringLugar.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            conexion.connect();

            if (conexion.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conexion.getInputStream()));
                String lugarSaved = reader.readLine();
                lugarSalvado = objectMapper.readValue(lugarSaved, new TypeReference<Lugar>() {
                });
                reader.close();
                lugar.setId(lugarSalvado.getId());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private boolean actualizaLugaresUpdate(Lugar lugar) {


        URL url=null;
        ObjectMapper objectMapper = new ObjectMapper();
        String stringLugar="";

        try {
            url= new URL(URL_BASE+"/actualizalugar/" +lugar.getId());
            objectMapper = new ObjectMapper();
// Convertimos la colección de datos a un String JSON

            stringLugar = objectMapper.writeValueAsString(lugar);
        } catch (JsonProcessingException | MalformedURLException e) {
            e.printStackTrace();
        }



        HttpURLConnection conexion = null;

        try {
            conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("PUT");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setDoOutput(true);

            try(OutputStream os = conexion.getOutputStream()) {
                byte[] input = stringLugar.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            conexion.connect();
            if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {

                return true;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    private boolean borraLugaresDelete(Lugar lugar) {


        URL url=null;
        ObjectMapper objectMapper = new ObjectMapper();
        String stringLugar="";

        try {
            url= new URL(URL_BASE+"/deletelugar" );
            objectMapper = new ObjectMapper();
// Convertimos la colección de datos a un String JSON

            stringLugar = objectMapper.writeValueAsString(lugar);
        } catch (JsonProcessingException | MalformedURLException e) {
            e.printStackTrace();
        }



        HttpURLConnection conexion = null;

        try {
            conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("DELETE");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setDoOutput(true);

            try(OutputStream os = conexion.getOutputStream()) {
                byte[] input = stringLugar.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {

                return true;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
