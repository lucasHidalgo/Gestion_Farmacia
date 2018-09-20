/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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
}
