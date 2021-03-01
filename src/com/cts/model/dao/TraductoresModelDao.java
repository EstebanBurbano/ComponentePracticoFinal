/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model.dao;

import com.cts.model.Conexion;
import com.cts.model.EmpleadosModel;
import com.cts.model.TraductoresModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teban
 */
public class TraductoresModelDao {
    
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion con = new Conexion();
    Connection acceso;
    
    public TraductoresModel listarId(String ciTraductor){
        TraductoresModel traductoresModel = new TraductoresModel();
        String sql = "select * from traductor where dni=?";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, ciTraductor);
            rs=ps.executeQuery();
            while(rs.next()){
                traductoresModel.setId(rs.getInt(1));
                traductoresModel.setCedulaTraductor(rs.getString(2));
                traductoresModel.setNombreTraductor(rs.getString(3));
                traductoresModel.setApellidoTraductor(rs.getString(4));
                traductoresModel.setNombreBancoTraduc(rs.getString(5));
                traductoresModel.setNumeroCuentaBancTraduc(rs.getString(6));
                traductoresModel.setTipoCuentaBancTraduc(rs.getString(7));
                traductoresModel.setEstadoTraduc(rs.getString(8));
                
                
            }
        } catch (Exception e) {
        }
        return traductoresModel;
    }
    
    public int agregar(TraductoresModel traductoresModel) {  
        int r=0;
        String sql="INSERT INTO traductor(dni,nombre,apellido,banco,numbanco,tipocuenta,estado) VALUES (?,?,?,?,?,?,?)";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, traductoresModel.getCedulaTraductor());
            ps.setString(2, traductoresModel.getNombreTraductor());
            ps.setString(3, traductoresModel.getApellidoTraductor());
            ps.setString(4, traductoresModel.getNombreBancoTraduc());
            ps.setString(5, traductoresModel.getNumeroCuentaBancTraduc());
            ps.setString(6, traductoresModel.getTipoCuentaBancTraduc());
            ps.setString(7, traductoresModel.getEstadoTraduc());
            r=ps.executeUpdate();
              
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Actualizar(TraductoresModel traductoresModel) {  
        int r=0;
        String sql="update traductor set Dni=?, Nombre=?, Apellido=?, Banco=?, NumBanco=?, TipoCuenta=?, Estado=? where IdTraductor=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, traductoresModel.getCedulaTraductor());
            ps.setString(2, traductoresModel.getNombreTraductor());
            ps.setString(3, traductoresModel.getApellidoTraductor());
            ps.setString(4, traductoresModel.getNombreBancoTraduc());
            ps.setString(5, traductoresModel.getNumeroCuentaBancTraduc());
            ps.setString(6, traductoresModel.getTipoCuentaBancTraduc());
            ps.setString(7, traductoresModel.getEstadoTraduc());
            ps.setInt(8, traductoresModel.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    
    public int Eliminar (TraductoresModel traductoresModel) {  
        int r=0;
        String sql="update traductor set Dni=?, Nombre=?, Apellido=?, Banco=?, NumBanco=?, TipoCuenta=?, Estado=? where IdTraductor=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, traductoresModel.getCedulaTraductor());
            ps.setString(2, traductoresModel.getNombreTraductor());
            ps.setString(3, traductoresModel.getApellidoTraductor());
            ps.setString(4, traductoresModel.getNombreBancoTraduc());
            ps.setString(5, traductoresModel.getNumeroCuentaBancTraduc());
            ps.setString(6, traductoresModel.getTipoCuentaBancTraduc());
            ps.setString(7, traductoresModel.getEstadoTraduc());
            ps.setInt(8, traductoresModel.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    
    public List listar() {
        List<TraductoresModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from traductor where estado = 1");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                TraductoresModel traductoresModel = new TraductoresModel();
                traductoresModel.setId(rs.getInt(1));
                traductoresModel.setCedulaTraductor(rs.getString(2));
                traductoresModel.setNombreTraductor(rs.getString(3));
                traductoresModel.setApellidoTraductor(rs.getString(4));
                traductoresModel.setNombreBancoTraduc(rs.getString(5));
                traductoresModel.setNumeroCuentaBancTraduc(rs.getString(6));
                traductoresModel.setTipoCuentaBancTraduc(rs.getString(7));
                traductoresModel.setEstadoTraduc(rs.getString(8));
                
                datos.add(traductoresModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public List listarInactivos(){
        List<TraductoresModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from traductor where estado = 0");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                TraductoresModel traductoresModel = new TraductoresModel();
                traductoresModel.setId(rs.getInt(1));
                traductoresModel.setCedulaTraductor(rs.getString(2));
                traductoresModel.setNombreTraductor(rs.getString(3));
                traductoresModel.setApellidoTraductor(rs.getString(4));
                traductoresModel.setNombreBancoTraduc(rs.getString(5));
                traductoresModel.setNumeroCuentaBancTraduc(rs.getString(6));
                traductoresModel.setTipoCuentaBancTraduc(rs.getString(7));
                traductoresModel.setEstadoTraduc(rs.getString(8));
                
                datos.add(traductoresModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public List buscarTraductores(String buscar){
        List<TraductoresModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from traductor where IdTraductor LIKE '%"+buscar+"%' OR Nombre LIKE '%"+buscar+"%' OR Apellido LIKE '%"+buscar+"%'");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                TraductoresModel traductoresModel = new TraductoresModel();
                traductoresModel.setId(rs.getInt(1));
                traductoresModel.setCedulaTraductor(rs.getString(2));
                traductoresModel.setNombreTraductor(rs.getString(3));
                traductoresModel.setApellidoTraductor(rs.getString(4));
                traductoresModel.setNombreBancoTraduc(rs.getString(5));
                traductoresModel.setNumeroCuentaBancTraduc(rs.getString(6));
                traductoresModel.setTipoCuentaBancTraduc(rs.getString(7));
                traductoresModel.setEstadoTraduc(rs.getString(8));
                
                datos.add(traductoresModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    
    
}
