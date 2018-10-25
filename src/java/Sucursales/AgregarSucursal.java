/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import Entidades.Barrio;
import Entidades.Producto;
import Entidades.Sucursales;
import GestionFdb.Database;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */@WebServlet(name = "AgregarSucursal", urlPatterns = {"/AgregarSucursal"})
public class AgregarSucursal extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        Connection db = Database.obtenerDb();
        
      JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);    
      JsonObject sucursal = data.get("nuevaSucursal").getAsJsonObject();
      
      Sucursales sucursalCrear = new Sucursales();
      sucursalCrear.Direccion = sucursal.get("Direccion").getAsString();
      sucursalCrear.Latitud = sucursal.get("Latitud").getAsString();
      sucursalCrear.Longitud = sucursal.get("Longitud").getAsString();
      sucursalCrear.Nombre = sucursal.get("Nombre").getAsString();
      sucursalCrear.Telefono1 = sucursal.get("Telefono1").getAsString();
      sucursalCrear.Telefono2 = sucursal.get("Telefono2").getAsString();
      sucursalCrear.BarrioId = sucursal.get("BarrioId").getAsInt();
      
      PrintWriter out = response.getWriter();
       JsonObject innerObject = new JsonObject();                    
       Gson gson = new Gson();
    
        if(Sucursales.ExisteDireccion(db,sucursalCrear)){
            innerObject.addProperty("success", "false");    
            innerObject.addProperty("msg", "Ya existe una sucursal con esa localizacion.");    
          String json = gson.toJson(innerObject);          
            out.print(json);   
      }                                 
      Sucursales.AgregarSucursal(db,sucursalCrear);
         innerObject.addProperty("success", "true");    
            innerObject.addProperty("msg", "Se creo la sucursal con exito.");    
          String json = gson.toJson(innerObject);          
            out.print(json);   
    }
    
      protected void ObtenerBarrios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");  
        
        Connection db = Database.obtenerDb();
        ArrayList<Barrio> barrios = Sucursales.obtenerTodasLasSucursales(db);       
                
        Gson gson = new Gson();
        String json = gson.toJson(barrios);  
        
        PrintWriter out = response.getWriter();
        out.print(json);      
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObtenerBarrios(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }   
   

}
