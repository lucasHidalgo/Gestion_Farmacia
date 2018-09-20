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
    
    public static ArrayList<Producto> ObtenerProductosSegunSucursal(Connection db,int sucursalId){
        ArrayList<Producto> productos = new ArrayList<Producto>();        
           try{                   
            String queryProductos = "SELECT"
                    + " p.Id productoId ,Stock ,p.Nombre prodNombre, p.Precio prodPrecio,p.EsVentaLibre, a.Nombre area ,prov.Nombre proveedor ,v.Nombre via,t.Nombre tipo "
                    + "FROM productosucursales "
                    + "JOIN productos as p on productosucursales.ProductoId = p.Id"
                    + " JOIN Area as a on p.Area = a.Id "
                    + "JOIN proveedores as prov on p.Proveedor = prov.Id "
                    + "JOIN vias as v on p.Via = v.Id "
                    + "JOIN tipos as t on p.Tipo = t.Id "
                    + "WHERE sucursalId = 1 AND Stock >= 1";
            Statement st = db.createStatement();          
            ResultSet rs = st.executeQuery(queryProductos);
            while(rs.next()){
                Producto p = new Producto();                 
                p.Id = rs.getInt("ProductoId");
                p.Nombre = rs.getString("prodNombre");
                p.Precio = rs.getDouble("prodPrecio");
                p.EsVentaLibre = rs.getBoolean("EsVentaLibre");
                p.Stock = rs.getInt("Stock");       
                
                p.Proveedor = new Proveedor();
                p.Proveedor.Nombre = rs.getString("proveedor");
                p.Tipo = new Tipo();
                p.Tipo.Nombre = rs.getString("tipo");
                p.Via = new Via();
                p.Via.Nombre = rs.getString("via");
                p.Area = new Area();
                p.Area.Nombre = rs.getString("area");
                productos.add(p);
            }              
       }
       catch(Exception ex){
           return null;
       }                           
        return productos;
    }
}
