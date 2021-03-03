/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.controller;

import com.cts.model.DetalleTrabajoModel;
import com.cts.model.EmpleadosModel;
import com.cts.model.ProductosModel;
import com.cts.model.TraductoresModel;
import com.cts.model.TrabajoModel;
import com.cts.model.dao.EmpleadosModelDao;
import com.cts.model.dao.ProductosModelDao;
import com.cts.model.dao.TraductoresModelDao;
import com.cts.model.dao.TrabajoModelDao;
import com.cts.reportes.ReporteExcelModel;
import com.cts.reportes.ReporteGraficoModel;
import com.cts.view.Menu;
import com.cts.view.RegistroDeTrabajo;
import com.cts.view.Traductores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author teban
 */
public class TrabajoControlador implements ActionListener{
    

    TraductoresModel traductoresModel = new TraductoresModel();
    TraductoresModelDao traductoresModelDao = new TraductoresModelDao();
    RegistroDeTrabajo registroDeTrabajoVista = new RegistroDeTrabajo();
    Traductores traductoresVista = new Traductores();
    ProductosModel productosModel = new ProductosModel();
    ProductosModelDao productosModelDao = new ProductosModelDao();
    DefaultTableModel tblRegistroDeActividad = new DefaultTableModel(); 
    TrabajoModel trabajoModel = new TrabajoModel();
    TrabajoModelDao trabajoModelDao = new TrabajoModelDao();
    DetalleTrabajoModel detalleTrabajoModel = new DetalleTrabajoModel();
    ReporteGraficoModel reporteGraficoModel = new ReporteGraficoModel();
    ReporteExcelModel excelModel = new ReporteExcelModel();
      
    int idp;
    double tpagar;
    double precioProducto;
    int cantidad;
    Calendar calendar = new GregorianCalendar();
    


    public TrabajoControlador(RegistroDeTrabajo v) {
        this.registroDeTrabajoVista = v;
        this.registroDeTrabajoVista.btnAgregar.addActionListener(this);
        this.registroDeTrabajoVista.btnBuscarTraductor.addActionListener(this);
        this.registroDeTrabajoVista.btnBuscarProducto.addActionListener(this);
        this.registroDeTrabajoVista.btnCancelar.addActionListener(this);
        this.registroDeTrabajoVista.btnGenerarVenta.addActionListener(this);
        this.registroDeTrabajoVista.btnGrafico.addActionListener(this);
        this.registroDeTrabajoVista.btnBarraGrafico.addActionListener(this);
        this.registroDeTrabajoVista.btnExcel.addActionListener(this);
        this.registroDeTrabajoVista.btnArea.addActionListener(this);
        this.registroDeTrabajoVista.btnStackedArea.addActionListener(this);
        generarSerie();
        fecha();
        
    }
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == registroDeTrabajoVista.btnExcel) {
            excel();
        }
        
        if (e.getSource() == registroDeTrabajoVista.btnGrafico) {
            if(registroDeTrabajoVista.dcFechaGrafico.getDate() == null){
                JOptionPane.showMessageDialog(registroDeTrabajoVista, "Debe ingresar una fecha");
            }else{
                graficarPastel();
            }
            

        }
        if (e.getSource() == registroDeTrabajoVista.btnArea) {
            if(registroDeTrabajoVista.dcFechaGrafico.getDate() == null){
                JOptionPane.showMessageDialog(registroDeTrabajoVista, "Debe ingresar una fecha");
            }else{
                graficarArea();
            }
            

        }
        if (e.getSource() == registroDeTrabajoVista.btnStackedArea) {
            if(registroDeTrabajoVista.dcFechaGrafico.getDate() == null){
                JOptionPane.showMessageDialog(registroDeTrabajoVista, "Debe ingresar una fecha");
            }else{
                graficarStackedArea();
            }
            

        }
        if (e.getSource() == registroDeTrabajoVista.btnBarraGrafico) {
            if(registroDeTrabajoVista.dcFechaGrafico.getDate() == null){
                JOptionPane.showMessageDialog(registroDeTrabajoVista, "Debe ingresar una fecha");
            }else{
                graficarBarra();
            }
            

        }
        if (e.getSource() == registroDeTrabajoVista.btnBuscarTraductor) {
            buscarTraductor();

        }
        if (e.getSource() == registroDeTrabajoVista.btnBuscarProducto) {
            buscarProducto();

        }
        if (e.getSource() == registroDeTrabajoVista.btnAgregar) {
            agregarProducto();
            calcularTotal();    

        }
        if (e.getSource() == registroDeTrabajoVista.btnGenerarVenta) {
           if(registroDeTrabajoVista.txtTotalAPagar.getText().equals("")){
               JOptionPane.showMessageDialog(traductoresVista, "Debe ingresar Datos");
           }else{
            guardarTrabajo();
            guardarDetalleTrabajo();
            actualizarStock();
            JOptionPane.showMessageDialog(traductoresVista, "Se realizo con exito!");
            nuevo();
            generarSerie();
           }
            

        }
        
    }
    
    void excel(){
        excelModel.reporteTrabajo();
    }

    void graficarPastel(){
        try {
                String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(registroDeTrabajoVista.dcFechaGrafico.getDate());
                reporteGraficoModel.Graficar(fechaReporte);
        } catch (Exception e) {
        }
         
    }
    void graficarBarra(){
        try {
                String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(registroDeTrabajoVista.dcFechaGrafico.getDate());
                reporteGraficoModel.GraficarBarra(fechaReporte);
        } catch (Exception e) {
        }
         
    }
    void graficarArea(){
        try {
                String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(registroDeTrabajoVista.dcFechaGrafico.getDate());
                reporteGraficoModel.GraficarArea(fechaReporte);
        } catch (Exception e) {
        }
         
    }
    void graficarStackedArea(){
        try {
                String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(registroDeTrabajoVista.dcFechaGrafico.getDate());
                reporteGraficoModel.GraficarStakedArea(fechaReporte);
        } catch (Exception e) {
        }
         
    }
    
    void nuevo(){
        limpiarTabla();
        registroDeTrabajoVista.txtCodTraductor.setText("");
        registroDeTrabajoVista.txtTraductor.setText("");
        registroDeTrabajoVista.txtCodProducto.setText("");
        registroDeTrabajoVista.txtProducto.setText("");
        registroDeTrabajoVista.txtPrecio.setText("");
        registroDeTrabajoVista.txtStock.setText("");
        registroDeTrabajoVista.spCantidad.setValue(0);
        //registroDeTrabajoVista.txtNumSerie.setText("");
        registroDeTrabajoVista.txtTotalAPagar.setText("");
        registroDeTrabajoVista.txtCodTraductor.requestFocus();
    }
    
    void limpiarTabla() {
        for (int i = 0; i < tblRegistroDeActividad.getRowCount(); i++) {
            tblRegistroDeActividad.removeRow(i);
            i = i - 1;
        }
    }
    
    void actualizarStock(){
        for(int i = 0; i < tblRegistroDeActividad.getRowCount(); i++){
            idp=Integer.parseInt(registroDeTrabajoVista.tblVenta.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(registroDeTrabajoVista.tblVenta.getValueAt(i, 3).toString());
            productosModel = productosModelDao.listarId(idp);
            int stockActual=productosModel.getStockCarta() - cantidad;
            productosModelDao.actualizarStock(stockActual, idp);
        }
    }
    void fecha(){
        registroDeTrabajoVista.txtFecha.setText(""+calendar.get(Calendar.DAY_OF_MONTH)+"/0"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR));
        
    }
    void generarSerie(){
        String serie = trabajoModelDao.NroSerieTrabajo();
        if(serie == null){
            registroDeTrabajoVista.txtNumSerie.setText("000001");
        }else{
            int increment = Integer.parseInt(serie);
            increment = increment + 1;
            registroDeTrabajoVista.txtNumSerie.setText("00000"+increment);
        }
        
    }
    
    public void agregarProducto(){
        double total;
        int item=0;
        tblRegistroDeActividad = (DefaultTableModel)registroDeTrabajoVista.tblVenta.getModel();
        item=item+1;
        idp=productosModel.getIdProducto();
        String nombreProducto = registroDeTrabajoVista.txtProducto.getText();
        precioProducto = Double.parseDouble(registroDeTrabajoVista.txtPrecio.getText());
        cantidad = Integer.parseInt(registroDeTrabajoVista.spCantidad.getValue().toString());
        int stock = Integer.parseInt(registroDeTrabajoVista.txtStock.getText());
        total = cantidad*precioProducto;
        ArrayList lista = new ArrayList();
        if(stock>0){
            lista.add(item);
            lista.add(idp);
            lista.add(nombreProducto);
            lista.add(cantidad);
            lista.add(precioProducto);
            lista.add(total);
            lista.add(stock);
            Object[]ob=new Object[6];
            ob[0]=lista.get(0);
            ob[1]=lista.get(1);
            ob[2]=lista.get(2);
            ob[3]=lista.get(3);
            ob[4]=lista.get(4);
            ob[5]=lista.get(5);
            tblRegistroDeActividad.addRow(ob);
            registroDeTrabajoVista.tblVenta.setModel(tblRegistroDeActividad);
        }else{
          JOptionPane.showMessageDialog(traductoresVista, "Stock producto no disponible");
        }
        
        
    }
    
    void calcularTotal(){
        tpagar = 0;
        for(int i = 0; i < registroDeTrabajoVista.tblVenta.getRowCount(); i++){
            cantidad = Integer.parseInt(registroDeTrabajoVista.tblVenta.getValueAt(i, 3).toString());
            precioProducto = Double.parseDouble(registroDeTrabajoVista.tblVenta.getValueAt(i, 4).toString());
            tpagar= tpagar + (cantidad*precioProducto);
        }
        registroDeTrabajoVista.txtTotalAPagar.setText(""+tpagar);
    }
    
    public void buscarTraductor(){
        int r;
        String cod = registroDeTrabajoVista.txtCodTraductor.getText();
        if(registroDeTrabajoVista.txtCodTraductor.getText().equals("")){
            JOptionPane.showMessageDialog(registroDeTrabajoVista, "Debe ingresar la cedula de un Traductor");
        }else{
            traductoresModel = traductoresModelDao.listarId(cod);
            if(traductoresModel.getCedulaTraductor()!=null){
                registroDeTrabajoVista.txtTraductor.setText(traductoresModel.getNombreTraductor() + " " + traductoresModel.getApellidoTraductor());
                registroDeTrabajoVista.txtCodTraductor.requestFocus();
            }else{
                r=JOptionPane.showConfirmDialog(registroDeTrabajoVista, "Traductor no registrado desea registrar uno?");
                if(r==0){
                Traductores traductoresV = new Traductores();
                TraductoresControlador traductoresControlador = new TraductoresControlador(traductoresV);
                Menu.VentanaPrincipal.add(traductoresV);
                traductoresV.setVisible(true);
            }
                
            }
                 
            
            
        }
    }
    
    public void buscarProducto(){
        
        int id=Integer.parseInt(registroDeTrabajoVista.txtCodProducto.getText());
        if(registroDeTrabajoVista.txtCodProducto.getText().equals("")){
            JOptionPane.showMessageDialog(registroDeTrabajoVista, "Debe ingresar un producto");
        }else{
            productosModel = productosModelDao.listarId(id);
            if(productosModel.getIdProducto()!=0){
                registroDeTrabajoVista.txtProducto.setText(productosModel.getNombreCarta());
                registroDeTrabajoVista.txtPrecio.setText(""+productosModel.getPrecioCarta());
                registroDeTrabajoVista.txtStock.setText(""+productosModel.getStockCarta());
            }else{
                JOptionPane.showMessageDialog(registroDeTrabajoVista, "Producto no encontrado");
                registroDeTrabajoVista.txtCodProducto.requestFocus();
                
                
            }
                    
        }
    }
            
    void guardarTrabajo(){
        int idTraductor= traductoresModel.getId();
        int idEmpleado=1;
        String serie=registroDeTrabajoVista.txtNumSerie.getText();
        String fecha=registroDeTrabajoVista.txtFecha.getText();
        double monto = tpagar;
        String estado="1";
        trabajoModel.setIdTraductor(idTraductor);
        trabajoModel.setIdEmpleado(idEmpleado);
        trabajoModel.setSerie(serie);
        trabajoModel.setFecha(fecha);
        trabajoModel.setMonto(monto);
        trabajoModel.setEstado(estado);
        trabajoModelDao.GuardarTrabajo(trabajoModel);
             
    }
    
    void guardarDetalleTrabajo(){
        String idv=trabajoModelDao.idTrabajo();
        int idve=Integer.parseInt(idv);
        for(int i = 0; i < registroDeTrabajoVista.tblVenta.getRowCount(); i++ ){
            int idp = Integer.parseInt(registroDeTrabajoVista.tblVenta.getValueAt(i, 1).toString());
            int cant = Integer.parseInt(registroDeTrabajoVista.tblVenta.getValueAt(i, 3).toString());
            double precio = Double.parseDouble(registroDeTrabajoVista.tblVenta.getValueAt(i, 4).toString());
            detalleTrabajoModel.setIdVenta(idve);
            detalleTrabajoModel.setIdProducto(idp);
            detalleTrabajoModel.setCantidad(cant);
            detalleTrabajoModel.setPrecioVenta(precio);
            trabajoModelDao.GuardarDetalleTrabajo(detalleTrabajoModel); 
            
            
        }
        
             
    }
    
    
}
