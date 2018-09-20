
package Sucursales;

import Entidades.Barrio;
import Entidades.Sucursales;
import GestionFdb.Database;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "Sucursal", urlPatterns = {"/Sucursal"})
public class SucursalController extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
   

}
