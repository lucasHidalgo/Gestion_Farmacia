/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFdb;

import Entidades.Usuarios;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author monica
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
   JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
    String uname = data.get("usuarioMail").getAsString();
    String pword = data.get("clave").getAsString();
    
    PrintWriter out = response.getWriter();  
    
    boolean fueValidado;
    if(Usuarios.ValidarUsuario(Database.obtenerDb(),uname, pword)) { 
        fueValidado = true;
    }
    else{          
        fueValidado = false;
    }
    boolean[] result = {fueValidado};
    Gson gson = new Gson();
    String json = gson.toJson(result);          
    out.print(json);    
  }


    
}
