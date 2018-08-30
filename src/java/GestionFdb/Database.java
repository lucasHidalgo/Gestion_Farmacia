/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFdb;

import java.sql.*;

public class Database {
   private static String USUARIO = "root";
   private static String PASSWORD = "";    //"root";
   private static String BASE = "jdbc:mysql://localhost/gestionfarmacia_db";  // "root";    
    
   
           
   
   public static Connection obtenerDb(){
       Connection conectar;
     try {
            //        processRequest(request, response);
            // 1. Queremos Acceder a la Base de DAtos
            // 2. creamos la base de datos

            // 0. el driver de conexion con la Base acual
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 1. conectar
            conectar = DriverManager.getConnection(
                    BASE,
                    USUARIO,
                    PASSWORD);
                        

        } catch (Exception ex) {
           return null;
        }
     return conectar;
   }
   
  
}
