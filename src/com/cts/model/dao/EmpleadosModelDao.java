/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model.dao;

import com.cts.model.EmpleadosModel;
import com.cts.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teban
 */
public class EmpleadosModelDao {
    
    PreparedStatement ps;
    ResultSet rs;
    
    
    Conexion con = new Conexion();
    Connection acceso;
    
    public EmpleadosModel ValidarEmpleado(String usuarioEmpleado, String claveEmpleado){
        EmpleadosModel administradoresModel = new EmpleadosModel();
        String sql="SELECT * FROM empleado WHERE BINARY usuario=? AND dni=?";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, usuarioEmpleado);
            ps.setString(2, claveEmpleado);
            rs=ps.executeQuery();
            while (rs.next()){
                administradoresModel.setIdEmpleado(rs.getInt(1));
                administradoresModel.setDniEmpleado(rs.getString(2));
                administradoresModel.setNombreEmpleado(rs.getString(3));
                administradoresModel.setApellidoEmpleado(rs.getString(4));
                administradoresModel.setEstadoEmpleado(rs.getString(5));
                administradoresModel.setUsuarioEmpleado(rs.getString(6));
                
            }
            
        } catch (Exception e) {
        }
        return administradoresModel;
    }
    
    public EmpleadosModel listarId(String ciEmpleado){
        EmpleadosModel empleadosModel = new EmpleadosModel();
        String sql = "select * from empleado where dni=?";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, ciEmpleado);
            rs=ps.executeQuery();
            while(rs.next()){
                empleadosModel.setIdEmpleado(rs.getInt(1));
                empleadosModel.setDniEmpleado(rs.getString(2));
                empleadosModel.setNombreEmpleado(rs.getString(3));
                empleadosModel.setApellidoEmpleado(rs.getString(4));
                empleadosModel.setEstadoEmpleado(rs.getString(5));
                empleadosModel.setUsuarioEmpleado(rs.getString(6));
                
                
            }
        } catch (Exception e) {
        }
        return empleadosModel;
    }
    
    public int agregar(EmpleadosModel empleadosModel) {  
        int r=0;
        String sql="INSERT INTO empleado(dni,nombre,apellido,estado,usuario) VALUES (?,?,?,?,?)";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, empleadosModel.getDniEmpleado());
            ps.setString(2, empleadosModel.getNombreEmpleado());
            ps.setString(3, empleadosModel.getApellidoEmpleado());
            ps.setString(4, empleadosModel.getEstadoEmpleado());
            ps.setString(5, empleadosModel.getUsuarioEmpleado());
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
    public int Actualizar(EmpleadosModel empleadosModel) {  
        int r=0;
        String sql="update empleado set Dni=?, Nombre=?, Apellido=?, Estado=?, Usuario=? where IdEmpleado=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, empleadosModel.getDniEmpleado());
            ps.setString(2, empleadosModel.getNombreEmpleado());
            ps.setString(3, empleadosModel.getApellidoEmpleado());
            ps.setString(4, empleadosModel.getEstadoEmpleado());
            ps.setString(5, empleadosModel.getUsuarioEmpleado());
            ps.setInt(6, empleadosModel.getIdEmpleado());
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
    
    public int Eliminar (EmpleadosModel empleadosModel) {  
        int r=0;
        String sql="update empleado set Dni=?, Nombre=?, Apellido=?, Estado=?, Usuario=? where IdEmpleado=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, empleadosModel.getDniEmpleado());
            ps.setString(2, empleadosModel.getNombreEmpleado());
            ps.setString(3, empleadosModel.getApellidoEmpleado());
            ps.setString(4, empleadosModel.getEstadoEmpleado());
            ps.setString(5, empleadosModel.getUsuarioEmpleado());
            ps.setInt(6, empleadosModel.getIdEmpleado());
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
        List<EmpleadosModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from empleado where estado = 1");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                EmpleadosModel empleadosModel = new EmpleadosModel();
                empleadosModel.setIdEmpleado(rs.getInt(1));
                empleadosModel.setDniEmpleado(rs.getString(2));
                empleadosModel.setNombreEmpleado(rs.getString(3));
                empleadosModel.setApellidoEmpleado(rs.getString(4));
                empleadosModel.setEstadoEmpleado(rs.getString(5));
                empleadosModel.setUsuarioEmpleado(rs.getString(6));
                datos.add(empleadosModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public List listarInactivos() {
        List<EmpleadosModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from empleado where estado = 0");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                EmpleadosModel empleadosModel = new EmpleadosModel();
                empleadosModel.setIdEmpleado(rs.getInt(1));
                empleadosModel.setDniEmpleado(rs.getString(2));
                empleadosModel.setNombreEmpleado(rs.getString(3));
                empleadosModel.setApellidoEmpleado(rs.getString(4));
                empleadosModel.setEstadoEmpleado(rs.getString(5));
                empleadosModel.setUsuarioEmpleado(rs.getString(6));
                datos.add(empleadosModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
}
