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
public class TraductoresModel {
    
    private int id;
    private String cedulaTraductor;
    private String nombreTraductor;
    private String apellidoTraductor;
    private String nombreBancoTraduc;
    private String numeroCuentaBancTraduc;
    private String tipoCuentaBancTraduc;
    private String estadoTraduc;

    public TraductoresModel() {
    }

    public TraductoresModel(int id, String cedulaTraductor, String nombreTraductor, String apellidoTraductor, String nombreBancoTraduc, String numeroCuentaBancTraduc, String tipoCuentaBancTraduc, String estadoTraduc) {
        this.id = id;
        this.cedulaTraductor = cedulaTraductor;
        this.nombreTraductor = nombreTraductor;
        this.apellidoTraductor = apellidoTraductor;
        this.nombreBancoTraduc = nombreBancoTraduc;
        this.numeroCuentaBancTraduc = numeroCuentaBancTraduc;
        this.tipoCuentaBancTraduc = tipoCuentaBancTraduc;
        this.estadoTraduc = estadoTraduc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedulaTraductor() {
        return cedulaTraductor;
    }

    public void setCedulaTraductor(String cedulaTraductor) {
        this.cedulaTraductor = cedulaTraductor;
    }

    public String getNombreTraductor() {
        return nombreTraductor;
    }

    public void setNombreTraductor(String nombreTraductor) {
        this.nombreTraductor = nombreTraductor;
    }

    public String getApellidoTraductor() {
        return apellidoTraductor;
    }

    public void setApellidoTraductor(String apellidoTraductor) {
        this.apellidoTraductor = apellidoTraductor;
    }

    public String getNombreBancoTraduc() {
        return nombreBancoTraduc;
    }

    public void setNombreBancoTraduc(String nombreBancoTraduc) {
        this.nombreBancoTraduc = nombreBancoTraduc;
    }

    public String getNumeroCuentaBancTraduc() {
        return numeroCuentaBancTraduc;
    }

    public void setNumeroCuentaBancTraduc(String numeroCuentaBancTraduc) {
        this.numeroCuentaBancTraduc = numeroCuentaBancTraduc;
    }

    public String getTipoCuentaBancTraduc() {
        return tipoCuentaBancTraduc;
    }

    public void setTipoCuentaBancTraduc(String tipoCuentaBancTraduc) {
        this.tipoCuentaBancTraduc = tipoCuentaBancTraduc;
    }

    public String getEstadoTraduc() {
        return estadoTraduc;
    }

    public void setEstadoTraduc(String estadoTraduc) {
        this.estadoTraduc = estadoTraduc;
    }
    
    
}
