/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.controller;

import com.cts.model.EmpleadosModel;
import com.cts.model.ProductosModel;
import com.cts.model.dao.ProductosModelDao;
import com.cts.reportes.ReporteExcelModel;
import com.cts.view.Cartas;
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
public class ProductosControlador implements ActionListener{

    ProductosModel productosModel = new ProductosModel();
    ProductosModelDao productosModelDao = new ProductosModelDao();
    ReporteExcelModel excelModel = new ReporteExcelModel();
    Cartas cartasVista = new Cartas();
    DefaultTableModel tblCartas = new DefaultTableModel();

    public ProductosControlador(Cartas v) {
        this.cartasVista = v;
        this.cartasVista.btnAgregar.addActionListener(this);
        //this.cartasVista.btnEditar.addActionListener(this);
        this.cartasVista.btnActualizar.addActionListener(this);
        this.cartasVista.btnEliminar.addActionListener(this);
        this.cartasVista.btnListarAct.addActionListener(this);
        this.cartasVista.btnListarIna.addActionListener(this);
        this.cartasVista.btnExcel.addActionListener(this);
        this.cartasVista.btnBuscar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == cartasVista.btnExcel) {
            limpiarTabla();
            excel();
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(false);
        }
        
        if (e.getSource() == cartasVista.btnBuscar) {
            limpiarTabla();
            buscarEmpleados(cartasVista.tblCartas, cartasVista.txtBuscar.getText());
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(false);
        }
        
        if (e.getSource() == cartasVista.btnListarAct) {
            limpiarTabla();
            listar(cartasVista.tblCartas);
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(false);

        }
        if (e.getSource() == cartasVista.btnListarIna) {
            limpiarTabla();
            listarInactivos(cartasVista.tblCartas);
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(true);

        }
        if (e.getSource() == cartasVista.btnAgregar) {
            add();
            listar(cartasVista.tblCartas);
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(false);

        }
        /*if (e.getSource() == cartasVista.btnEditar) {
            int fila = cartasVista.tblCartas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(cartasVista, "Debee Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) cartasVista.tblCartas.getValueAt(fila, 0).toString());
                String nombre = (String) cartasVista.tblCartas.getValueAt(fila, 1);
                double precio = Double.parseDouble((String) cartasVista.tblCartas.getValueAt(fila, 2).toString());
                int stock = Integer.parseInt((String) cartasVista.tblCartas.getValueAt(fila, 3).toString());
                String estado = (String) cartasVista.tblCartas.getValueAt(fila, 4);
               
                cartasVista.txtIdPrducto.setText("" + id);
                cartasVista.txtNombreProducto.setText(nombre);
                cartasVista.txtPrecioProducto.setText("" + precio);
                cartasVista.txtStrockProducto.setText("" + stock);
                cartasVista.cbEstadoProducto.setSelectedItem(estado);
                 
            }
            

        }*/
        if (e.getSource() == cartasVista.btnActualizar) {
            Actualizar();
            listar(cartasVista.tblCartas);
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(false);

        }
        if (e.getSource() == cartasVista.btnEliminar) {
            Eliminar();
            listar(cartasVista.tblCartas);
            nuevo();
            cartasVista.cbEstadoProducto.setEnabled(false);


        }
       
    }
    
    void excel(){
        excelModel.reporte();
    }
    
    void nuevo() {
        cartasVista.txtIdPrducto.setText("");
        cartasVista.txtNombreProducto.setText("");
        cartasVista.txtPrecioProducto.setText("");
        cartasVista.txtStrockProducto.setText("");
        cartasVista.cbEstadoProducto.setSelectedItem("");
        
    }
    
    public void add() {
        String nombre = cartasVista.txtNombreProducto.getText();
        double precio = Double.parseDouble(cartasVista.txtPrecioProducto.getText());
        int stock = Integer.parseInt(cartasVista.txtStrockProducto.getText());
        String estado = cartasVista.cbEstadoProducto.getSelectedItem().toString();
        productosModel.setNombreCarta(nombre);
        productosModel.setPrecioCarta(precio);
        productosModel.setStockCarta(stock);
        productosModel.setEstadoCarta(estado);
        
        
        int r = productosModelDao.agregar(productosModel);
        if (r == 1) {
            JOptionPane.showMessageDialog(cartasVista, "Producto Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(cartasVista, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (cartasVista.txtIdPrducto.getText().equals("")) {
            JOptionPane.showMessageDialog(cartasVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(cartasVista.txtIdPrducto.getText());
            String nombre = cartasVista.txtNombreProducto.getText();
            double precio = Double.parseDouble(cartasVista.txtPrecioProducto.getText());
            int stock = Integer.parseInt(cartasVista.txtStrockProducto.getText());
            String estado = cartasVista.cbEstadoProducto.getSelectedItem().toString();
            productosModel.setIdProducto(id);
            productosModel.setNombreCarta(nombre);
            productosModel.setPrecioCarta(precio);
            productosModel.setStockCarta(stock);
            productosModel.setEstadoCarta(estado);
            
            int r = productosModelDao.Actualizar(productosModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(cartasVista, "Producto Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(cartasVista, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void Eliminar() {
         if (cartasVista.txtIdPrducto.getText().equals("")) {
            JOptionPane.showMessageDialog(cartasVista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(cartasVista.txtIdPrducto.getText());
            String nombre = cartasVista.txtNombreProducto.getText();
            double precio = Double.parseDouble(cartasVista.txtPrecioProducto.getText());
            int stock = Integer.parseInt(cartasVista.txtStrockProducto.getText());
            String estado = cartasVista.cbEstadoProducto.getSelectedItem().toString();
            productosModel.setIdProducto(id);
            productosModel.setNombreCarta(nombre);
            productosModel.setPrecioCarta(precio);
            productosModel.setStockCarta(stock);
            productosModel.setEstadoCarta("0");
            
            int r = productosModelDao.Actualizar(productosModel);
            if (r == 1) {
                JOptionPane.showMessageDialog(cartasVista, "Producto Eliminado con Exito.");
            } else {
                JOptionPane.showMessageDialog(cartasVista, "Error");
            }
        }
        limpiarTabla();
        
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        tblCartas = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblCartas);
        List<ProductosModel> lista = productosModelDao.listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdProducto();
            objeto[1] = lista.get(i).getNombreCarta();
            objeto[2] = lista.get(i).getPrecioCarta();
            objeto[3] = lista.get(i).getStockCarta();
            objeto[4] = lista.get(i).getEstadoCarta();
            tblCartas.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    public void listarInactivos(JTable tabla) {
        centrarCeldas(tabla);
        tblCartas = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblCartas);
        List<ProductosModel> lista = productosModelDao.listarInactivos();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdProducto();
            objeto[1] = lista.get(i).getNombreCarta();
            objeto[2] = lista.get(i).getPrecioCarta();
            objeto[3] = lista.get(i).getStockCarta();
            objeto[4] = lista.get(i).getEstadoCarta();
            tblCartas.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    public void buscarEmpleados(JTable tabla, String buscar) {
        centrarCeldas(tabla);
        tblCartas = (DefaultTableModel) tabla.getModel();
        tabla.setModel(tblCartas);
        List<ProductosModel> lista = productosModelDao.buscarProductos(buscar);
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdProducto();
            objeto[1] = lista.get(i).getNombreCarta();
            objeto[2] = lista.get(i).getPrecioCarta();
            objeto[3] = lista.get(i).getStockCarta();
            objeto[4] = lista.get(i).getEstadoCarta();
            tblCartas.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    
    
    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < cartasVista.tblCartas.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < cartasVista.tblCartas.getRowCount(); i++) {
            tblCartas.removeRow(i);
            i = i - 1;
        }
    }
    
}
