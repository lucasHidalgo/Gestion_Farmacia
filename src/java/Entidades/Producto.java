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
public class Producto {
    public int Id;
    public String Nombre;
    public String Codigo;
    public double Precio;
    public boolean EsVentaLibre;
    public Area Area;
    public int ProveedorId;
    public Proveedor Proveedor;
    public Via Via;
    public Tipo Tipo;
    public int Stock;
    public Barrio Barrio;
    public Perfil Perfil;
    public Reservas Reservas;
    public Ventas Ventas;
    public ProductoSucursales ProductoSucursales;
    
    public static ArrayList<Producto> ObtenerProductosSegunSucursal(Connection db,int sucursalId){
        ArrayList<Producto> productos = new ArrayList<Producto>();        
           try{                   
            String queryProductos = "SELECT * FROM productosucursales\n" +
                                    "JOIN productos as p on productosucursales.ProductoId = p.Id\n" +
                                    "WHERE sucursalId = "+sucursalId+" AND Stock >= 1";
            Statement st = db.createStatement();          
            ResultSet rs = st.executeQuery(queryProductos);
            while(rs.next()){
                Producto p = new Producto();                 
                p.Id = rs.getInt("ProductoId");
                p.Nombre = rs.getString("Nombre");
                p.Precio = rs.getDouble("Precio");
                p.EsVentaLibre = rs.getBoolean("EsVentaLibre");
                p.Stock = rs.getInt("Stock");
                productos.add(p);
            }              
       }
       catch(Exception ex){
           return null;
       }                           
        return productos;
    }
}
