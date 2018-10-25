/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Sucursales {
  
    public int Id;
    public String Nombre;    
    public Barrio Barrio;
    public String Latitud;
    public String Longitud;
    public String Telefono1;
    public String Telefono2;
    public String Direccion;
    public int BarrioId;
    
    
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
    
    public static ArrayList<Barrio> obtenerTodasLasSucursales(Connection db){
        ArrayList<Sucursales> sucursales = new ArrayList<Sucursales>();
        ArrayList<Barrio> barrios = new ArrayList<Barrio>();
        
        try {
            String querySucursales = "select s.Id,s.Nombre,s.Latitud,s.Longitud,s.Telefono1,s.Telefono2,s.Direccion,b.Id barrioId, b.nombre as barrio"
                    + " FROM sucursales as s "
                    + "JOIN barrios as b on s.Barrio = b.Id ORDER BY b.Id";                    
               Statement st = db.createStatement();          
            ResultSet rs = st.executeQuery(querySucursales);
            while(rs.next()){         
                Boolean existeBarrio = false;
                //creo sucursal
                Sucursales sucursal = new Sucursales();
                sucursal.Id = rs.getInt("Id");
                sucursal.Nombre = rs.getString("Nombre");
                sucursal.Latitud = rs.getString("Latitud");
                sucursal.Longitud = rs.getString("Longitud");
                sucursal.Telefono1 = rs.getString("Telefono1");
                sucursal.Telefono2 = rs.getString("Telefono2");
                sucursal.Direccion = rs.getString("Direccion"); 
                //reviso si el barrio de la sucursal ya existe, si existe entonces le agrego al barrio esa sucursal.
                for(int i = 0; i < barrios.size(); i++){
                    Barrio  b = barrios.get(i);
                    if(b.Id == rs.getInt("barrioId")){
                        b.Sucursales.add(sucursal);
                        existeBarrio = true;
                    }
                }
                if(existeBarrio){
                    continue;
                }
                //Creo un nuevo barrio si no existe en la lista de barrios
                Barrio barrio = new Barrio();                                                
                barrio.Id = rs.getInt("barrioId");
                barrio.Nombre = rs.getString("barrio");
                barrio.Sucursales = new ArrayList<Sucursales>();
                barrio.Sucursales.add(sucursal);   
                barrios.add(barrio);
                sucursales.add(sucursal);
            }   
        }catch(Exception ex){
            String s = ex.getMessage();
            return null;
        }
        
        return barrios;
    }
    //Verifico que no haya una sucursal con la misma direccion geografica
      public static boolean ExisteDireccion(Connection db, Sucursales sucursalCrear) {
          try{
          String querySucursal = "SELECT Id FROM sucursales "
                  + " WHERE Direccion = '"+sucursalCrear.Direccion+"' AND Latitud = '"+sucursalCrear.Latitud+"' AND Longitud = '"+sucursalCrear.Longitud+"'";
          
            Statement st = db.createStatement();          
            ResultSet rs = st.executeQuery(querySucursal);
            
            if(rs.next()){ 
                return true;
            }
          }catch(Exception ex){
              //si cae en catch no crear.
               return true;
          }                    
        return false;
    }
    
      
    public static void AgregarSucursal(Connection db, Sucursales sucursalCrear) {
        try{
        Statement statement = db.createStatement();

                // insert the data
                statement.executeUpdate("INSERT INTO sucursales(Direccion,Latitud,Longitud,Barrio,Nombre,Telefono1,Telefono2)   VALUES ( '"+sucursalCrear.Direccion+"','"+sucursalCrear.Latitud+"','"+sucursalCrear.Longitud+"',"+sucursalCrear.BarrioId+",'"+sucursalCrear.Nombre+"','"+sucursalCrear.Telefono1+"','"+sucursalCrear.Telefono2+"')"
                                                            );    
        }catch(Exception ex){
            
        }
    }
    

}
