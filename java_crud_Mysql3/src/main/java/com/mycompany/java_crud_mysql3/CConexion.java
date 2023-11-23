/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql3;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ziruxeno
 */
public class CConexion {
        Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "Ziruxeno1345.";
    String bd = "bdStreamdb";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            JOptionPane.showMessageDialog(null,"La conexion se ha realizado con exito");
           
        } catch (HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error al conectar a la base de datos, erro: "+ e.toString());
        }
        return conectar;
    }

    void cerrarConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

    
