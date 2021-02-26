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
public class TrabajoModel {
    
    private int idTrabajo;
    private int idEmpleado;
    private int idTraductor;
    private String serie;
    private String fecha;
    private double monto;
    private String estado;

    public TrabajoModel() {
    }

    public TrabajoModel(int idTrabajo, int idEmpleado, int idTraductor, String serie, String fecha, double monto, String estado) {
        this.idTrabajo = idTrabajo;
        this.idEmpleado = idEmpleado;
        this.idTraductor = idTraductor;
        this.serie = serie;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdTraductor() {
        return idTraductor;
    }

    public void setIdTraductor(int idTraductor) {
        this.idTraductor = idTraductor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}
