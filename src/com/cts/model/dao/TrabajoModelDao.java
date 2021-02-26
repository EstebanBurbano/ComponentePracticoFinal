/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model.dao;

import com.cts.model.Conexion;
import com.cts.model.DetalleTrabajoModel;
import com.cts.model.TrabajoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author teban
 */
public class TrabajoModelDao {
    
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion con = new Conexion();
    Connection acceso;
    int r=0;
    
    public String NroSerieTrabajo(){
        String serie="";
        String sql = "select max(NumeroSerie) from trabajo";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                serie=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return serie;
    }
    
    public String idTrabajo( ){
        String idv="";
        String sql = "select max(IdTrabajo) from trabajo";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idv=rs.getString(1);
                   
            }
        } catch (Exception e) {
        }
        return idv;
    }
    
    public int GuardarTrabajo(TrabajoModel v){
        TrabajoModel trabajoModel = new TrabajoModel();
        String sql ="insert into trabajo(IdTraductor,IdEmpleado,NumeroSerie,FechaTrabajo,Monto,Estado)values(?,?,?,?,?,?)";
        try{
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setInt(1, v.getIdTraductor());
            ps.setInt(2, v.getIdEmpleado());
            ps.setString(3, v.getSerie());
            ps.setString(4, v.getFecha());
            ps.setDouble(5, v.getMonto());
            ps.setString(6, v.getEstado());
            r=ps.executeUpdate();  
        }catch(Exception e){
            
        }
        return r;
    }
    
    public int GuardarDetalleTrabajo(DetalleTrabajoModel detalleTrabajoModel){
        
        String sql ="insert into detalle_trabajo(IdTrabajo,IdProducto,Cantidad,PrecioVenta)values(?,?,?,?)";
        try{
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setInt(1, detalleTrabajoModel.getIdVenta());
            ps.setInt(2, detalleTrabajoModel.getIdProducto());
            ps.setInt(3, detalleTrabajoModel.getCantidad());
            ps.setDouble(4, detalleTrabajoModel.getPrecioVenta());
            r=ps.executeUpdate();  
        }catch(Exception e){
            
        }
        return r;
    }
    

    
}
