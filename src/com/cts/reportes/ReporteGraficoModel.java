/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.reportes;

import com.cts.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author teban
 */
public class ReporteGraficoModel {
    
    public void Graficar(String fecha){
        PreparedStatement ps;
        ResultSet rs;
        Conexion con = new Conexion();
        Connection acceso;
        
        try {
            String sql = "SELECT Nombre, Monto FROM traductor INNER JOIN trabajo on traductor.IdTraductor = trabajo.IdTraductor WHERE trabajo.FechaTrabajo = ?";
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            DefaultPieDataset dateset = new DefaultPieDataset();
            while(rs.next()){
                dateset.setValue(rs.getString("Nombre")+": "+rs.getString("Monto"), rs.getDouble("Monto"));
            }
            JFreeChart jf = ChartFactory.createPieChart3D("Reporte de Trabajo", dateset);
            ChartFrame f = new ChartFrame("Total de Trabajo por dia", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public void GraficarBarra (String fecha){
        PreparedStatement ps;
        ResultSet rs;
        Conexion con = new Conexion();
        Connection acceso;
        
        try {
            String sql = "SELECT Nombre, Monto FROM traductor INNER JOIN trabajo on traductor.IdTraductor = trabajo.IdTraductor WHERE trabajo.FechaTrabajo = ?";
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            while(rs.next()){
                defaultCategoryDataset.setValue(rs.getDouble("Monto"), rs.getString("Monto"), rs.getString("Nombre") );
            }
            JFreeChart jf = ChartFactory.createBarChart3D("Reporte de Trabajo", "Monto", "Monto", defaultCategoryDataset);
            /*ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(500,40,500,400);*/
            ChartFrame f = new ChartFrame("Total de Trabajo por dia", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public void GraficarArea (String fecha){
        PreparedStatement ps;
        ResultSet rs;
        Conexion con = new Conexion();
        Connection acceso;
        
        try {
            String sql = "SELECT Nombre, Monto FROM traductor INNER JOIN trabajo on traductor.IdTraductor = trabajo.IdTraductor WHERE trabajo.FechaTrabajo = ?";
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            while(rs.next()){
                defaultCategoryDataset.setValue(rs.getDouble("Monto"), rs.getString("Monto"), rs.getString("Nombre") );
            }
            JFreeChart jf = ChartFactory.createAreaChart("Reporte de Trabajo", "Monto", "Monto", defaultCategoryDataset);
            /*ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(500,40,500,400);*/
            ChartFrame f = new ChartFrame("Total de Trabajo por dia", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public void GraficarStakedArea (String fecha){
        PreparedStatement ps;
        ResultSet rs;
        Conexion con = new Conexion();
        Connection acceso;
        
        try {
            String sql = "SELECT Nombre, Monto FROM traductor INNER JOIN trabajo on traductor.IdTraductor = trabajo.IdTraductor WHERE trabajo.FechaTrabajo = ?";
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            while(rs.next()){
                defaultCategoryDataset.setValue(rs.getDouble("Monto"), rs.getString("Monto"), rs.getString("Nombre") );
            }
            JFreeChart jf = ChartFactory.createStackedAreaChart("Reporte de Trabajo", "Monto", "Monto", defaultCategoryDataset);
            /*ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(500,40,500,400);*/
            ChartFrame f = new ChartFrame("Total de Trabajo por dia", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
}
