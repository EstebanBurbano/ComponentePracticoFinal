/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.controller;

import com.cts.model.EmpleadosModel;
import com.cts.model.dao.EmpleadosModelDao;
import com.cts.view.Empleados;
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
public class EmpleadosControlador implements ActionListener{
    
    EmpleadosModel empleadosModel = new EmpleadosModel();
    EmpleadosModelDao empleadosModelDao = new EmpleadosModelDao();
    Empleados empleadoVista = new Empleados();
    DefaultTableModel tblEmpleados = new DefaultTableModel();

    public EmpleadosControlador(Empleados v) {
        
        this.empleadoVista = v;
        this.empleadoVista.btnAgregar.addActionListener(this);
        this.empleadoVista.btnEditar.addActionListener(this);
        this.empleadoVista.btnActualizar.addActionListener(this);
        this.empleadoVista.btnEliminar.addActionListener(this);
        this.empleadoVista.btnActListar.addActionListener(this);
        this.empleadoVista.btnInaListar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == empleadoVista.btnActListar) {
            limpiarTabla();
            listar(empleadoVista.tblEmpleados);
            nuevo();
            empleadoVista.cbEstadoEmpleado.setEnabled(false);

        }
        if (e.getSource() == empleadoVista.btnInaListar) {
            limpiarTabla();
            listarInactivos(empleadoVista.tblEmpleados);
            nuevo();
            empleadoVista.cbEstadoEmpleado.setEnabled(true);
        }
        if (e.getSource() == empleadoVista.btnAgregar) {
            add();
            listar(empleadoVista.tblEmpleados);
            nuevo();
            empleadoVista.cbEstadoEmpleado.setEnabled(false);

        }
        if (e.getSource() == empleadoVista.btnEditar) {
            int fila = empleadoVista.tblEmpleados.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(empleadoVista, "Debee Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) empleadoVista.tblEmpleados.getValueAt(fila, 0).toString());
                String dni = (String) empleadoVista.tblEmpleados.getValueAt(fila, 1);
                String nombre = (String) empleadoVista.tblEmpleados.getValueAt(fila, 2);
                String apellido = (String) empleadoVista.tblEmpleados.getValueAt(fila, 3);
                String estado = (String) empleadoVista.tblEmpleados.getValueAt(fila, 4);
                String usuario = (String) empleadoVista.tblEmpleados.getValueAt(fila, 5);
                empleadoVista.txtIdEmpleado.setText("" + id);
                empleadoVista.txtCiEmpleado.setText(dni);
                empleadoVista.txtNombreEmpleado.setText(nombre);
                empleadoVista.txtApellidoEmpleado.setText(apellido);
                empleadoVista.cbEstadoEmpleado.setSelectedItem(estado);
                empleadoVista.txtUsuarioEmpleado.setText(usuario);
            }
            

        }
        if (e.getSource() == empleadoVista.btnActualizar) {
            Actualizar();
            listar(empleadoVista.tblEmpleados);
            nuevo();
            empleadoVista.cbEstadoEmpleado.setEnabled(false);

        }
        if (e.getSource() == empleadoVista.btnEliminar) {
            Eliminar();
            listar(empleadoVista.tblEmpleados);
            nuevo();
            empleadoVista.cbEstadoEmpleado.setEnabled(false);


        }
        
        
    }
    
    void nuevo() {
        empleadoVista.txtIdEmpleado.setText("");
        empleadoVista.txtCiEmpleado.setText("");
        empleadoVista.txtNombreEmpleado.setText("");
        empleadoVista.txtApellidoEmpleado.setText("");
        empleadoVista.txtUsuarioEmpleado.setText("");
        empleadoVista.txtCiEmpleado.requestFocus();
    }
    
    public void add() {
        String ci = empleadoVista.txtCiEmpleado.getText();
        String nombre = empleadoVista.txtNombreEmpleado.getText();
        String apellido = empleadoVista.txtApellidoEmpleado.getText();
        String estado = empleadoVista.cbEstadoEmpleado.getSelectedItem().toString();
        String usuario = empleadoVista.txtUsuarioEmpleado.getText();
        empleadosModel.setDniEmpleado(ci);
        empleadosModel.setNombreEmpleado(nombre);
        empleadosModel.setApellidoEmpleado(apellido);
        empleadosModel.setEstadoEmpleado(estado);
        empleadosModel.setUsuarioEmpleado(usuario);
        
        int r = empleadosModelDao.agregar(empleadosModel);
        if (r == 1) {
            JOptionPane.showMessageDialog(empleadoVista, "Empleado Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(empleadoVista, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (empleadoVista.txtIdEmpleado.getText().equals("")) {
            JOptionPane.showMessageDialog(empleadoVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(empleadoVista.txtIdEmpleado.getText());
            String ci = empleadoVista.txtCiEmpleado.getText();
            String nombre = empleadoVista.txtNombreEmpleado.getText();
            String apellido = empleadoVista.txtApellidoEmpleado.getText();
            String estado = empleadoVista.cbEstadoEmpleado.getSelectedItem().toString();
            String usuario = empleadoVista.txtUsuarioEmpleado.getText();
            empleadosModel.setIdEmpleado(id);
            empleadosModel.setDniEmpleado(ci);
            empleadosModel.setNombreEmpleado(nombre);
            empleadosModel.setApellidoEmpleado(apellido);
            empleadosModel.setEstadoEmpleado(estado);
            empleadosModel.setUsuarioEmpleado(usuario);
            
            int r = empleadosModelDao.Actualizar(empleadosModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(empleadoVista, "Empleado Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(empleadoVista, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void Eliminar() {
        if (empleadoVista.txtIdEmpleado.getText().equals("")) {
            JOptionPane.showMessageDialog(empleadoVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(empleadoVista.txtIdEmpleado.getText());
            String ci = empleadoVista.txtCiEmpleado.getText();
            String nombre = empleadoVista.txtNombreEmpleado.getText();
            String apellido = empleadoVista.txtApellidoEmpleado.getText();
            String estado = empleadoVista.cbEstadoEmpleado.getSelectedItem().toString();
            String usuario = empleadoVista.txtUsuarioEmpleado.getText();
            empleadosModel.setIdEmpleado(id);
            empleadosModel.setDniEmpleado(ci);
            empleadosModel.setNombreEmpleado(nombre);
            empleadosModel.setApellidoEmpleado(apellido);
            empleadosModel.setEstadoEmpleado("0");
            empleadosModel.setUsuarioEmpleado(usuario);
            
            int r = empleadosModelDao.Eliminar(empleadosModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(empleadoVista, "Empleado Eliminado con Exito.");
            } else {
                JOptionPane.showMessageDialog(empleadoVista, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        tblEmpleados = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblEmpleados);
        List<EmpleadosModel> lista = empleadosModelDao.listar();
        Object[] objeto = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdEmpleado();
            objeto[1] = lista.get(i).getDniEmpleado();
            objeto[2] = lista.get(i).getNombreEmpleado();
            objeto[3] = lista.get(i).getApellidoEmpleado();
            objeto[4] = lista.get(i).getEstadoEmpleado();
            objeto[5] = lista.get(i).getUsuarioEmpleado();
            tblEmpleados.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    public void listarInactivos(JTable tabla) {
        centrarCeldas(tabla);
        tblEmpleados = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblEmpleados);
        List<EmpleadosModel> lista = empleadosModelDao.listarInactivos();
        Object[] objeto = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdEmpleado();
            objeto[1] = lista.get(i).getDniEmpleado();
            objeto[2] = lista.get(i).getNombreEmpleado();
            objeto[3] = lista.get(i).getApellidoEmpleado();
            objeto[4] = lista.get(i).getEstadoEmpleado();
            objeto[5] = lista.get(i).getUsuarioEmpleado();
            tblEmpleados.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < empleadoVista.tblEmpleados.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < empleadoVista.tblEmpleados.getRowCount(); i++) {
            tblEmpleados.removeRow(i);
            i = i - 1;
        }
    }
    
    
    
}
