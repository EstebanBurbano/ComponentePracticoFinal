/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.controller;

import com.cts.model.ProductosModel;
import com.cts.model.TrabajoModel;
import com.cts.model.dao.TrabajoModelDao;
import com.cts.view.Trabajo;
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
public class TrabajoMantenimientoControlador implements ActionListener {
    
    DefaultTableModel tblTrabajo = new DefaultTableModel(); 
    TrabajoModel trabajoModel = new TrabajoModel();
    TrabajoModelDao trabajoModelDao = new TrabajoModelDao();
    Trabajo trabajoVista = new Trabajo();

    public TrabajoMantenimientoControlador(Trabajo v) {
        this.trabajoVista = v;
        this.trabajoVista.btnActListar.addActionListener(this);
        this.trabajoVista.btnActualizar.addActionListener(this);
        this.trabajoVista.btnEditar.addActionListener(this);
        this.trabajoVista.btnEliminar.addActionListener(this);
        this.trabajoVista.btnInaListar.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == trabajoVista.btnEditar) {
            int fila = trabajoVista.tblTrabajo.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(trabajoVista, "Debee Seleccionar Una fila..!!");
            } else {
                int idTrabajo = Integer.parseInt((String) trabajoVista.tblTrabajo.getValueAt(fila, 0).toString());
                int idTraductor = Integer.parseInt((String) trabajoVista.tblTrabajo.getValueAt(fila, 1).toString());
                int idEmpleado = Integer.parseInt((String) trabajoVista.tblTrabajo.getValueAt(fila, 2).toString());
                String numeroSerie = (String) trabajoVista.tblTrabajo.getValueAt(fila, 3);
                String fecha = (String) trabajoVista.tblTrabajo.getValueAt(fila, 4);
                double monto = Double.parseDouble((String) trabajoVista.tblTrabajo.getValueAt(fila, 5).toString());
                String estado = (String) trabajoVista.tblTrabajo.getValueAt(fila, 6);
               
                trabajoVista.txtIdTrabajo.setText("" + idTrabajo);
                trabajoVista.txtIdTraductor.setText("" + idTraductor);
                trabajoVista.txtIdEmpleado.setText("" + idEmpleado);
                trabajoVista.txtNumeroSerie.setText(numeroSerie);
                trabajoVista.txtFecha.setText(fecha);
                trabajoVista.txtMonto.setText("" + monto);
                trabajoVista.cbEstadoTrabajo.setSelectedItem(estado);
                 
            }
            

        }
        if (e.getSource() == trabajoVista.btnActListar) {
            limpiarTabla();
            listar(trabajoVista.tblTrabajo);
            nuevo();
            trabajoVista.cbEstadoTrabajo.setEnabled(false);

        }
        if (e.getSource() == trabajoVista.btnInaListar) {
            limpiarTabla();
            listarInactivos(trabajoVista.tblTrabajo);
            nuevo();
            trabajoVista.cbEstadoTrabajo.setEnabled(true);

        }
        if (e.getSource() == trabajoVista.btnActualizar) {
            Actualizar();
            listar(trabajoVista.tblTrabajo);
            nuevo();
            trabajoVista.cbEstadoTrabajo.setEnabled(false);

        }
        if (e.getSource() == trabajoVista.btnEliminar) {
            Eliminar();
            listar(trabajoVista.tblTrabajo);
            nuevo();
            trabajoVista.cbEstadoTrabajo.setEnabled(false);


        }
       
        
    }
    
        void nuevo() {
                trabajoVista.txtIdTrabajo.setText("");
                trabajoVista.txtIdTraductor.setText("");
                trabajoVista.txtIdEmpleado.setText("");
                trabajoVista.txtNumeroSerie.setText("");
                trabajoVista.txtFecha.setText("");
                trabajoVista.txtMonto.setText("");
                trabajoVista.cbEstadoTrabajo.setSelectedItem("");
        
    }
    
    public void Actualizar() {
        if (trabajoVista.txtIdTrabajo.getText().equals("")) {
            JOptionPane.showMessageDialog(trabajoVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int idTrabajo = Integer.parseInt(trabajoVista.txtIdTrabajo.getText());
            int idTraductor = Integer.parseInt(trabajoVista.txtIdTraductor.getText());
            int idEmpleado = Integer.parseInt(trabajoVista.txtIdEmpleado.getText());
            String numeroSerie = trabajoVista.txtNumeroSerie.getText();
            String fecha = trabajoVista.txtFecha.getText();
            double monto = Double.parseDouble(trabajoVista.txtMonto.getText());
            String estado = trabajoVista.cbEstadoTrabajo.getSelectedItem().toString();
            trabajoModel.setIdTrabajo(idTrabajo);
            trabajoModel.setIdTraductor(idTraductor);
            trabajoModel.setIdEmpleado(idEmpleado);
            trabajoModel.setSerie(numeroSerie);
            trabajoModel.setFecha(fecha);
            trabajoModel.setMonto(monto);
            trabajoModel.setEstado(estado);
            
            int r = trabajoModelDao.Actualizar(trabajoModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(trabajoVista, "Producto Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(trabajoVista, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void Eliminar() {
        if (trabajoVista.txtIdTrabajo.getText().equals("")) {
            JOptionPane.showMessageDialog(trabajoVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int idTrabajo = Integer.parseInt(trabajoVista.txtIdTrabajo.getText());
            int idTraductor = Integer.parseInt(trabajoVista.txtIdTraductor.getText());
            int idEmpleado = Integer.parseInt(trabajoVista.txtIdEmpleado.getText());
            String numeroSerie = trabajoVista.txtNumeroSerie.getText();
            String fecha = trabajoVista.txtFecha.getText();
            double monto = Double.parseDouble(trabajoVista.txtMonto.getText());
            String estado = trabajoVista.cbEstadoTrabajo.getSelectedItem().toString();
            trabajoModel.setIdTrabajo(idTrabajo);
            trabajoModel.setIdTraductor(idTraductor);
            trabajoModel.setIdEmpleado(idEmpleado);
            trabajoModel.setSerie(numeroSerie);
            trabajoModel.setFecha(fecha);
            trabajoModel.setMonto(monto);
            trabajoModel.setEstado("0");
            
            int r = trabajoModelDao.Eliminar(trabajoModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(trabajoVista, "Producto Eliminado con Exito.");
            } else {
                JOptionPane.showMessageDialog(trabajoVista, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        tblTrabajo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblTrabajo);
        List<TrabajoModel> lista = trabajoModelDao.listar();
        Object[] objeto = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdTrabajo();
            objeto[1] = lista.get(i).getIdTraductor();
            objeto[2] = lista.get(i).getIdEmpleado();
            objeto[3] = lista.get(i).getSerie();
            objeto[4] = lista.get(i).getFecha();
            objeto[5] = lista.get(i).getMonto();
            objeto[6] = lista.get(i).getEstado();
            tblTrabajo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    public void listarInactivos(JTable tabla) {
        centrarCeldas(tabla);
        tblTrabajo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblTrabajo);
        List<TrabajoModel> lista = trabajoModelDao.listarInactivos();
        Object[] objeto = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdTrabajo();
            objeto[1] = lista.get(i).getIdTraductor();
            objeto[2] = lista.get(i).getIdEmpleado();
            objeto[3] = lista.get(i).getSerie();
            objeto[4] = lista.get(i).getFecha();
            objeto[5] = lista.get(i).getMonto();
            objeto[6] = lista.get(i).getEstado();
            tblTrabajo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < trabajoVista.tblTrabajo.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < trabajoVista.tblTrabajo.getRowCount(); i++) {
            tblTrabajo.removeRow(i);
            i = i - 1;
        }
    }
    
}
