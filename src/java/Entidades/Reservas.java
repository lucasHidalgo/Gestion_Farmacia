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
public class Reservas {
    public int Id;
    public int ProductoId;
    public int Cantidad;
    public boolean FueCancelado;
    public int CajeroId;
    public int ClienteId;
    public Date FechaReserva;
    public int CajeroCancelacionId;
}
