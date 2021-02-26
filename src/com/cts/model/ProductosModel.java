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
public class ProductosModel {
    
    private int idProducto;
    private String nombreCarta;
    private double precioCarta;
    private int stockCarta;
    private String estadoCarta;

    public ProductosModel() {
    }

    public ProductosModel(int idProducto, String nombreCarta, double precioCarta, int stockCarta, String estadoCarta) {
        this.idProducto = idProducto;
        this.nombreCarta = nombreCarta;
        this.precioCarta = precioCarta;
        this.stockCarta = stockCarta;
        this.estadoCarta = estadoCarta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public double getPrecioCarta() {
        return precioCarta;
    }

    public void setPrecioCarta(double precioCarta) {
        this.precioCarta = precioCarta;
    }

    public int getStockCarta() {
        return stockCarta;
    }

    public void setStockCarta(int stockCarta) {
        this.stockCarta = stockCarta;
    }

    public String getEstadoCarta() {
        return estadoCarta;
    }

    public void setEstadoCarta(String estadoCarta) {
        this.estadoCarta = estadoCarta;
    }
    
    
    
}
