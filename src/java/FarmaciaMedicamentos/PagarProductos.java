/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FarmaciaMedicamentos;

import Entidades.Producto;
import Entidades.Sucursales;
import Entidades.Ventas;
import GestionFdb.Database;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
 */
@WebServlet(name = "PagarProductos", urlPatterns = {"/PagarProductos"})
public class PagarProductos extends HttpServlet {
 //traer todos los medicamentos en base a la sucursal y que tengan stock
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {            
    JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
    int idSucursal = data.get("idSucursal").getAsInt();    
    JsonArray productos = data.get("productoId").getAsJsonArray();
     Producto prod = null;
     int cantidad = 0;
    Connection db = Database.obtenerDb();
    for (JsonElement p : productos) {
        JsonObject prducto = p.getAsJsonObject();
        int id = prducto.get("idProducto").getAsInt();
        cantidad = prducto.get("cantidad").getAsInt();
        prod     = Producto.ObtenerProductoPorId(db,id) ;        
}        
     PrintWriter out = response.getWriter();                  
    
    
    boolean seGeneroVenta = Ventas.GenerarVenta(db,prod,cantidad);
    JsonObject innerObject = new JsonObject();
    if(seGeneroVenta){        
        innerObject.addProperty("success", "true");
    }else{
        innerObject.addProperty("success", "false");
    }
    Gson gson = new Gson();
    String json = gson.toJson(innerObject);          
     out.print(json);    
  }    
}
