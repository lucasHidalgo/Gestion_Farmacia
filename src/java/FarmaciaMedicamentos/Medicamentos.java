/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FarmaciaMedicamentos;

import Entidades.Producto;
import Entidades.Sucursales;
import GestionFdb.Database;
import com.google.gson.*;
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
 */
@WebServlet(name = "Medicamentos", urlPatterns = {"/Medicamentos/ObtenerProductosPorSucursal"})
public class Medicamentos extends HttpServlet {
    //traer todos los medicamentos en base a la sucursal y que tengan stock
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {            
    JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
    int idSucursal = data.get("idSucursal").getAsInt();    
    Connection db = Database.obtenerDb();
    
    //obtengo sucursalId dependiendo que sucursal elija el cliente
    Sucursales sucursal = Sucursales.obtenerSucursal(db,idSucursal);
    
    //Obtengo todos los productos deacuerdo a la sucursal y valido que tenga almenos 1 de stock
    ArrayList<Producto> productos = Producto.ObtenerProductosSegunSucursal(db,sucursal.Id);
    
    PrintWriter out = response.getWriter();              
    
    Gson gson = new Gson();
    String json = gson.toJson(productos);          
    out.print(json);    
  }    
}


