/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.*;


/**
 *
 * @author monica
 */
public class Usuarios {
    public int usuario_id;
    public String us_nombre;
    public String us_apellido;
    public String us_email;
    public String clave;
    public int us_tipodoc;
    public String us_numdoc;
    public int PerfilId;
    public Perfil Perfil;
    public double us_sueldo;
    public Date us_fechaalta;
    public Date us_fechabaja;
    public int us_estado;
    public int us_usuarioidalta;
    public Usuarios UsuarioAlta;
    
     public static boolean ValidarUsuario(Connection conn, String usuario,String clave){
       try{
            String query = "SELECT * FROM usuarios WHERE us_email = '"+usuario+"' AND clave = '"+clave+"'";     
            Statement st = conn.createStatement();          
            ResultSet rs = st.executeQuery(query);            
            
            if(rs.next()){
                //obtener todos los campos del usuario, crear un objeto y meterlo en la cookie o lo que sea.
                return true;
            }       
       return false;
       }
       catch(Exception ex){
           return false;
       }
      
   }
}
