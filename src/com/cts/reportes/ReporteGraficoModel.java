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
            String sql = "SELECT Monto FROM trabajo WHERE FechaTrabajo = ?";
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            DefaultPieDataset dateset = new DefaultPieDataset();
            while(rs.next()){
                dateset.setValue(rs.getString("Monto"), rs.getDouble("Monto"));
            }
            JFreeChart jf = ChartFactory.createPieChart("Reporte de Trabajo", dateset);
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
            String sql = "SELECT Monto FROM trabajo WHERE FechaTrabajo = ?";
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            while(rs.next()){
                defaultCategoryDataset.setValue(rs.getDouble("Monto"), rs.getString("Monto"), rs.getString("Monto") );
            }
            JFreeChart jf = ChartFactory.createBarChart("Reporte de Trabajo", "Monto", "Monto", defaultCategoryDataset);
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
