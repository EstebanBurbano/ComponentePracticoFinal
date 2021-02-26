/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model;

/**
 *
 * @author teban
 */
public class DetalleTrabajoModel {
    
    private int idDetalleTrabajo;
    private int idProducto;
    private int idVenta;
    private int cantidad;
    private double precioVenta;

    public DetalleTrabajoModel() {
    }

    public DetalleTrabajoModel(int idDetalleTrabajo, int idProducto, int idVenta, int cantidad, double precioVenta) {
        this.idDetalleTrabajo = idDetalleTrabajo;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public int getIdDetalleTrabajo() {
        return idDetalleTrabajo;
    }

    public void setIdDetalleTrabajo(int idDetalleTrabajo) {
        this.idDetalleTrabajo = idDetalleTrabajo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
     
    
}
