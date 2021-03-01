/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model.dao;

import com.cts.model.Conexion;
import com.cts.model.EmpleadosModel;
import com.cts.model.ProductosModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teban
 */
public class ProductosModelDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion con = new Conexion();
    Connection acceso;
    int r;
    
    public int actualizarStock(int cant, int idp){
        String sql = "update producto set Stock=? where idProducto=?";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idp);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    public ProductosModel listarId(int id){
        ProductosModel productosModel = new ProductosModel();
        String sql = "select * from producto where IdProducto=?";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                productosModel.setIdProducto(rs.getInt(1));
                productosModel.setNombreCarta(rs.getString(2));
                productosModel.setPrecioCarta(rs.getDouble(3));
                productosModel.setStockCarta(rs.getInt(4));
                productosModel.setEstadoCarta(rs.getString(5));
                
                
            }
        } catch (Exception e) {
        }
        return productosModel;
    }
    
    public int agregar(ProductosModel productosModel) {  
        int r=0;
        String sql="INSERT INTO producto(nombres,precio,stock,estado) VALUES (?,?,?,?)";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, productosModel.getNombreCarta());
            ps.setDouble(2, productosModel.getPrecioCarta());
            ps.setInt(3, productosModel.getStockCarta());
            ps.setString(4, productosModel.getEstadoCarta());
        
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
    public int Actualizar(ProductosModel productosModel) {  
        int r=0;
        String sql="update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, productosModel.getNombreCarta());
            ps.setDouble(2, productosModel.getPrecioCarta());
            ps.setInt(3, productosModel.getStockCarta());
            ps.setString(4, productosModel.getEstadoCarta());
            ps.setInt(5, productosModel.getIdProducto());
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
    
    public int Eliminar (ProductosModel productosModel) {  
        int r=0;
        String sql="update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";        
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, productosModel.getNombreCarta());
            ps.setDouble(2, productosModel.getPrecioCarta());
            ps.setInt(3, productosModel.getStockCarta());
            ps.setString(4, productosModel.getEstadoCarta());
            ps.setInt(5, productosModel.getIdProducto());
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
        List<ProductosModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from producto where estado = 1");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                ProductosModel productosModel = new ProductosModel();
                productosModel.setIdProducto(rs.getInt(1));
                productosModel.setNombreCarta(rs.getString(2));
                productosModel.setPrecioCarta(rs.getDouble(3));
                productosModel.setStockCarta(rs.getInt(4));
                productosModel.setEstadoCarta(rs.getString(5));
                datos.add(productosModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public List listarInactivos() {
        List<ProductosModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from producto where estado = 0");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                ProductosModel productosModel = new ProductosModel();
                productosModel.setIdProducto(rs.getInt(1));
                productosModel.setNombreCarta(rs.getString(2));
                productosModel.setPrecioCarta(rs.getDouble(3));
                productosModel.setStockCarta(rs.getInt(4));
                productosModel.setEstadoCarta(rs.getString(5));
                datos.add(productosModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public List buscarProductos(String buscar) {
        List<ProductosModel> datos = new ArrayList<>();
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement("select * from producto where idProducto LIKE '%"+buscar+"%' OR Nombres LIKE '%"+buscar+"%'");
            rs=ps.executeQuery();
            
            while (rs.next()) {
                ProductosModel productosModel = new ProductosModel();
                productosModel.setIdProducto(rs.getInt(1));
                productosModel.setNombreCarta(rs.getString(2));
                productosModel.setPrecioCarta(rs.getDouble(3));
                productosModel.setStockCarta(rs.getInt(4));
                productosModel.setEstadoCarta(rs.getString(5));
                datos.add(productosModel);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
}
