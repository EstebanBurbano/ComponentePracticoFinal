/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cts_cartas;

import com.cts.model.Conexion;
import com.cts.view.LoginForm;
import com.cts.view.Menu;

/**
 *
 * @author teban
 */
public class Cts_Cartas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion = new Conexion();
        if(conexion.Conectar()!=null)
            System.out.println("Conexion Correcta");
        else
            System.out.println("Conexion Incorrecta");
        
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
      //Menu menu = new Menu();
      //menu.setVisible(true);
    }
    
    
}
