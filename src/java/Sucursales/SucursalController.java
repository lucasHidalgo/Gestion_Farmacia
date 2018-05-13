
package Sucursales;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        Sucursal sucursal = new Sucursal();
        sucursal.Latitud = -34.613090;
        sucursal.Longitud = -58.499097;
        sucursal.Nombre = "sucursal desde servlet";
        
        Sucursal sucursal2 = new Sucursal();
        sucursal2.Latitud = -34.615585;
        sucursal2.Longitud = -58.497145;
        sucursal2.Nombre = "sucursal desde servlet 2";
        
        List<Sucursal> list = new ArrayList<Sucursal>();
        list.add(sucursal);
        list.add(sucursal2);
                
        Gson gson = new Gson();
        String json = gson.toJson(list);  
        
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
