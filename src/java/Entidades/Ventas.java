/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.*;
import java.sql.Date;

/**
 *
 * @author Nata
 */
public class Ventas {
    public int Id;
    public int ClienteId;
    public Usuarios Cliente;
    public int ProductoId;
    public Producto Producto;
    public int Cantidad;
    public Date FechaVenta;
    public int CajeroId;
    public Usuarios Cajero;
    public double PrecioVenta;
    public boolean x;
    
    
    public static boolean GenerarVenta(Connection db, Producto producto, int cantidad ){
        try{                                                           
                Statement statement = db.createStatement();

                // insert the data
                statement.executeUpdate("INSERT INTO ventas(ClienteId,ProductoId,Cantidad,FechaVenta,CajeroId,PrecioVenta) "
                                                                + "VALUES (1, "+producto.Id+","+cantidad+", NOW(),1,"+producto.Precio+")"
                                                            );
        }catch(Exception ex){
            return false;
        }
        return true;
    }
}
