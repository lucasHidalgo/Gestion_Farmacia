/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFdb;

import java.sql.*;


/**
 *
 * @author lucas
 */
public class GestionFarmaciaDb {
   private static String USUARIO = "root";
   private static String PASSWORD = "";    //"root";
   private static String BASE = "jdbc:mysql://localhost/gestionfarmacia_db";  // "root";    
    
   
           
   
   public static String Conectar(){
     try {
            //        processRequest(request, response);
            // 1. Queremos Acceder a la Base de DAtos
            // 2. creamos la base de datos

            // 0. el driver de conexion con la Base acual
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 1. conectar
            Connection conectar = DriverManager.getConnection(
                    BASE,
                    USUARIO,
                    PASSWORD);
            return "ok";            

        } catch (Exception ex) {
            return "Error" + ex.getMessage();
        }
   }
}
