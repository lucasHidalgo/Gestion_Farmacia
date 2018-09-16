/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lucas
 */
public class Sucursales {
    public int Id;
    public String Nombre;
    public int Barrio;
    public String Latitud;
    public String Longitud;
    public String Telefono1;
    public String Telefono2;
    public String Direccion;
    
    
    public static Sucursales obtenerSucursal(Connection conn, int idSucursal){
        Sucursales sucursal = new Sucursales();
           try{                   
            String query = "SELECT * FROM sucursales WHERE Id = "+idSucursal+"";     
            Statement st = conn.createStatement();          
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                sucursal.Id = rs.getInt("Id");
                sucursal.Nombre = rs.getString("Nombre");
                sucursal.Latitud = rs.getString("Latitud");
                sucursal.Longitud = rs.getString("Longitud");
                sucursal.Telefono1 = rs.getString("Telefono1");
                sucursal.Telefono2 = rs.getString("Telefono2");
                sucursal.Direccion = rs.getString("Direccion");                                
            }              
       }
       catch(Exception ex){
           return null;
       }               
        return sucursal;
    }

}
