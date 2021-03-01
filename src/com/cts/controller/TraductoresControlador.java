/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.controller;

import com.cts.model.EmpleadosModel;
import com.cts.model.TraductoresModel;
import com.cts.model.dao.TraductoresModelDao;
import com.cts.reportes.ReporteExcelModel;
import com.cts.view.Traductores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author teban
 */
public class TraductoresControlador implements ActionListener {
    
    TraductoresModel traductoresModel = new TraductoresModel();
    TraductoresModelDao traductoresModelDao = new TraductoresModelDao();
    ReporteExcelModel excelModel = new ReporteExcelModel();
    Traductores traductoresVista = new Traductores();
    DefaultTableModel tblTraductores = new DefaultTableModel();

    public TraductoresControlador(Traductores v) {
        this.traductoresVista = v;
        this.traductoresVista.btnAgregar.addActionListener(this);
        //this.traductoresVista.btnEditar.addActionListener(this);
        this.traductoresVista.btnActualizar.addActionListener(this);
        this.traductoresVista.btnEliminar.addActionListener(this);
        this.traductoresVista.btnListarAct.addActionListener(this);
        this.traductoresVista.btnListarIna.addActionListener(this);
        this.traductoresVista.btnExcel.addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == traductoresVista.btnExcel) {
            excel();
        }
        
        if (e.getSource() == traductoresVista.btnListarAct) {
            limpiarTabla();
            listar(traductoresVista.tblTraductores);
            nuevo();
            traductoresVista.cbEstadoTraductor.setEnabled(false);

        }
        if (e.getSource() == traductoresVista.btnListarIna) {
            limpiarTabla();
            listarInactivos(traductoresVista.tblTraductores);
            nuevo();
            traductoresVista.cbEstadoTraductor.setEnabled(true);
        }
        if (e.getSource() == traductoresVista.btnAgregar) {
            add();
            listar(traductoresVista.tblTraductores);
            nuevo();
            traductoresVista.cbEstadoTraductor.setEnabled(false);

        }
        /*if (e.getSource() == traductoresVista.btnEditar) {
            int fila = traductoresVista.tblTraductores.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(traductoresVista, "Debee Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) traductoresVista.tblTraductores.getValueAt(fila, 0).toString());
                String dni = (String) traductoresVista.tblTraductores.getValueAt(fila, 1);
                String nombre = (String) traductoresVista.tblTraductores.getValueAt(fila, 2);
                String apellido = (String) traductoresVista.tblTraductores.getValueAt(fila, 3);
                String nombreBanco = (String) traductoresVista.tblTraductores.getValueAt(fila, 4);
                String numeroCuentaBanco = (String) traductoresVista.tblTraductores.getValueAt(fila, 5);
                String tipoCuenta = (String) traductoresVista.tblTraductores.getValueAt(fila, 6);
                String estado = (String) traductoresVista.tblTraductores.getValueAt(fila, 7);
                traductoresVista.txtIdTraductor.setText("" + id);
                traductoresVista.txtCiTraductor.setText(dni);
                traductoresVista.txtNombreTraductor.setText(nombre);
                traductoresVista.txtApellidoTraductor.setText(apellido);
                traductoresVista.txtBancoTraductor.setText(nombreBanco);
                traductoresVista.txtCuentaBanco.setText(numeroCuentaBanco);
                traductoresVista.txtTipoCuenta.setText(tipoCuenta);
                traductoresVista.cbEstadoTraductor.setSelectedItem(estado);
                
            }
            

        }*/
        if (e.getSource() == traductoresVista.btnActualizar) {
            Actualizar();
            listar(traductoresVista.tblTraductores);
            nuevo();
            traductoresVista.cbEstadoTraductor.setEnabled(false);

        }
        if (e.getSource() == traductoresVista.btnEliminar) {
            Eliminar();
            listar(traductoresVista.tblTraductores);
            nuevo();
            traductoresVista.cbEstadoTraductor.setEnabled(false);


        }
      
    }
    
    void excel(){
        excelModel.reporteTraductores();
    }
    
    void nuevo() {
        traductoresVista.txtIdTraductor.setText("");
        traductoresVista.txtCiTraductor.setText("");
        traductoresVista.txtNombreTraductor.setText("");
        traductoresVista.txtApellidoTraductor.setText("");
        traductoresVista.txtBancoTraductor.setText("");
        traductoresVista.txtCuentaBanco.setText("");
        traductoresVista.txtTipoCuenta.setText("");
        traductoresVista.cbEstadoTraductor.setSelectedItem("");
        traductoresVista.txtIdTraductor.requestFocus();
    }
    
    public void add() {
        String ci = traductoresVista.txtCiTraductor.getText();
        String nombre = traductoresVista.txtNombreTraductor.getText();
        String apellido = traductoresVista.txtApellidoTraductor.getText();
        String nombreBanco = traductoresVista.txtBancoTraductor.getText();
        String numeroBanco = traductoresVista.txtCuentaBanco.getText();
        String tipoCuenta = traductoresVista.txtTipoCuenta.getText();
        String estado = traductoresVista.cbEstadoTraductor.getSelectedItem().toString();
        
        traductoresModel.setCedulaTraductor(ci);
        traductoresModel.setNombreTraductor(nombre);
        traductoresModel.setApellidoTraductor(apellido);
        traductoresModel.setNombreBancoTraduc(nombreBanco);
        traductoresModel.setNumeroCuentaBancTraduc(numeroBanco);
        traductoresModel.setTipoCuentaBancTraduc(tipoCuenta);
        traductoresModel.setEstadoTraduc(estado);
        
        int r = traductoresModelDao.agregar(traductoresModel);
        if (r == 1) {
            JOptionPane.showMessageDialog(traductoresVista, "Traductor Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(traductoresVista, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (traductoresVista.txtIdTraductor.getText().equals("")) {
            JOptionPane.showMessageDialog(traductoresVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(traductoresVista.txtIdTraductor.getText());
            String ci = traductoresVista.txtCiTraductor.getText();
            String nombre = traductoresVista.txtNombreTraductor.getText();
            String apellido = traductoresVista.txtApellidoTraductor.getText();
            String nombreBanco = traductoresVista.txtBancoTraductor.getText();
            String numeroBanco = traductoresVista.txtCuentaBanco.getText();
            String tipoCuenta = traductoresVista.txtTipoCuenta.getText();
            String estado = traductoresVista.cbEstadoTraductor.getSelectedItem().toString();
            traductoresModel.setId(id);
            traductoresModel.setCedulaTraductor(ci);
            traductoresModel.setNombreTraductor(nombre);
            traductoresModel.setApellidoTraductor(apellido);
            traductoresModel.setNombreBancoTraduc(nombreBanco);
            traductoresModel.setNumeroCuentaBancTraduc(numeroBanco);
            traductoresModel.setTipoCuentaBancTraduc(tipoCuenta);
            traductoresModel.setEstadoTraduc(estado);
            
            int r = traductoresModelDao.Actualizar(traductoresModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(traductoresVista, "Traductor Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(traductoresVista, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void Eliminar(){
        
        if (traductoresVista.txtIdTraductor.getText().equals("")) {
            JOptionPane.showMessageDialog(traductoresVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(traductoresVista.txtIdTraductor.getText());
            String ci = traductoresVista.txtCiTraductor.getText();
            String nombre = traductoresVista.txtNombreTraductor.getText();
            String apellido = traductoresVista.txtApellidoTraductor.getText();
            String nombreBanco = traductoresVista.txtBancoTraductor.getText();
            String numeroBanco = traductoresVista.txtCuentaBanco.getText();
            String tipoCuenta = traductoresVista.txtTipoCuenta.getText();
            String estado = traductoresVista.cbEstadoTraductor.getSelectedItem().toString();
            traductoresModel.setId(id);
            traductoresModel.setCedulaTraductor(ci);
            traductoresModel.setNombreTraductor(nombre);
            traductoresModel.setApellidoTraductor(apellido);
            traductoresModel.setNombreBancoTraduc(nombreBanco);
            traductoresModel.setNumeroCuentaBancTraduc(numeroBanco);
            traductoresModel.setTipoCuentaBancTraduc(tipoCuenta);
            traductoresModel.setEstadoTraduc("0");
            
            int r = traductoresModelDao.Actualizar(traductoresModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(traductoresVista, "Traductor Eliminado con Exito.");
            } else {
                JOptionPane.showMessageDialog(traductoresVista, "Error");
            }
        }
        limpiarTabla();
        
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        tblTraductores = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblTraductores);
        List<TraductoresModel> lista = traductoresModelDao.listar();
        Object[] objeto = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCedulaTraductor();
            objeto[2] = lista.get(i).getNombreTraductor();
            objeto[3] = lista.get(i).getApellidoTraductor();
            objeto[4] = lista.get(i).getNombreBancoTraduc();
            objeto[5] = lista.get(i).getNumeroCuentaBancTraduc();
            objeto[6] = lista.get(i).getTipoCuentaBancTraduc();
            objeto[7] = lista.get(i).getEstadoTraduc();
            tblTraductores.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    public void listarInactivos(JTable tabla){
        
        centrarCeldas(tabla);
        tblTraductores = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblTraductores);
        List<TraductoresModel> lista = traductoresModelDao.listarInactivos();
        Object[] objeto = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCedulaTraductor();
            objeto[2] = lista.get(i).getNombreTraductor();
            objeto[3] = lista.get(i).getApellidoTraductor();
            objeto[4] = lista.get(i).getNombreBancoTraduc();
            objeto[5] = lista.get(i).getNumeroCuentaBancTraduc();
            objeto[6] = lista.get(i).getTipoCuentaBancTraduc();
            objeto[7] = lista.get(i).getEstadoTraduc();
            tblTraductores.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

        
    }
    
    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < traductoresVista.tblTraductores.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < traductoresVista.tblTraductores.getRowCount(); i++) {
            tblTraductores.removeRow(i);
            i = i - 1;
        }
    }
    
}
