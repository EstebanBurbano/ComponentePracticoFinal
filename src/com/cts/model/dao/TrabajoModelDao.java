/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model.dao;

import com.cts.model.Conexion;
import com.cts.model.DetalleTrabajoModel;
import com.cts.model.TrabajoModel;
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
    
    public List listar() {
        List<TrabajoModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from trabajo where estado = 1");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                TrabajoModel trabajoModel = new TrabajoModel();
                trabajoModel.setIdTrabajo(rs.getInt(1));
                trabajoModel.setIdTraductor(rs.getInt(2));
                trabajoModel.setIdEmpleado(rs.getInt(3));
                trabajoModel.setSerie(rs.getString(4));
                trabajoModel.setFecha(rs.getString(5));
                trabajoModel.setMonto(rs.getDouble(6));
                trabajoModel.setEstado(rs.getString(7));
                
                
                datos.add(trabajoModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public List listarInactivos() {
        List<TrabajoModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from trabajo where estado = 0");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                TrabajoModel trabajoModel = new TrabajoModel();
                trabajoModel.setIdTrabajo(rs.getInt(1));
                trabajoModel.setIdTraductor(rs.getInt(2));
                trabajoModel.setIdEmpleado(rs.getInt(3));
                trabajoModel.setSerie(rs.getString(4));
                trabajoModel.setFecha(rs.getString(5));
                trabajoModel.setMonto(rs.getDouble(6));
                trabajoModel.setEstado(rs.getString(7));
                
                
                datos.add(trabajoModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    
    public List buscarTrabajo(String buscar) {
        List<TrabajoModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from trabajo where IdTrabajo LIKE '%"+buscar+"%' OR NumeroSerie LIKE '%"+buscar+"%'");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                TrabajoModel trabajoModel = new TrabajoModel();
                trabajoModel.setIdTrabajo(rs.getInt(1));
                trabajoModel.setIdTraductor(rs.getInt(2));
                trabajoModel.setIdEmpleado(rs.getInt(3));
                trabajoModel.setSerie(rs.getString(4));
                trabajoModel.setFecha(rs.getString(5));
                trabajoModel.setMonto(rs.getDouble(6));
                trabajoModel.setEstado(rs.getString(7));
                
                
                datos.add(trabajoModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int Eliminar (TrabajoModel trabajoModel) {  
        int r=0;
        String sql="update trabajo set IdTraductor=?, IdEmpleado=?, NumeroSerie=?, FechaTrabajo=?, Monto=?, Estado=? where IdTrabajo=?";   
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setInt(1, trabajoModel.getIdTraductor());
            ps.setInt(2, trabajoModel.getIdEmpleado());
            ps.setString(3, trabajoModel.getSerie());
            ps.setString(4, trabajoModel.getFecha());
            ps.setDouble(5, trabajoModel.getMonto());
            ps.setString(6, trabajoModel.getEstado());
            ps.setInt(7, trabajoModel.getIdTrabajo());
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
    
    public int Actualizar (TrabajoModel trabajoModel) {  
        int r=0;
        String sql="update trabajo set IdTraductor=?, IdEmpleado=?, NumeroSerie=?, FechaTrabajo=?, Monto=?, Estado=? where IdTrabajo=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setInt(1, trabajoModel.getIdTraductor());
            ps.setInt(2, trabajoModel.getIdEmpleado());
            ps.setString(3, trabajoModel.getSerie());
            ps.setString(4, trabajoModel.getFecha());
            ps.setDouble(5, trabajoModel.getMonto());
            ps.setString(6, trabajoModel.getEstado());
            ps.setInt(7, trabajoModel.getIdTrabajo());
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
    

    
}
