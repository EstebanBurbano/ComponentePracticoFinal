/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author teban
 */
public class Conexion {
 
    Connection con;
    private final String DB="cts5";
    private final String URL="jdbc:mysql://localhost:3306/"+DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER="root";
    private final String PASS="root";
    
    public Connection Conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(URL,USER,PASS);
        } catch (Exception e) {
        }
        return con;
        
    }
    
}
