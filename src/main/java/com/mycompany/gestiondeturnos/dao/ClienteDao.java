/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//En este Package las CLASES que van a tener acceso a la base de datos
package com.mycompany.gestiondeturnos.dao;

import com.mycompany.gestiondeturnos.clases.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//El objetivo es recibir el obj de la clase cliente y guardarlos en la bd
public class ClienteDao {
    
    
 //FUNCION PARA GENERAR CONEXION CON BASE DE DATOS
    public Connection conectarConDB() {
        
/*------------ Datos necesarios para la conexion con la DB-------------------------*/    
    String basededatos = "gestionturnos";
    String usuario = "root";
    String password = "";
    String host = "localhost";
    String puerto = "3306";
    //Hace referencia a q vamos a estar utilzando para conectarnos a la db
    String driver = "com.mysql.cj.jdbc.Driver"; 
    String conexionUrl = "jdbc:mysql://localhost:3306/gestionturnos?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
    
    Connection conexion = null;
    
        try {
            // de esta forma indicamos el driver que utilizamos
            Class.forName(driver);
           conexion = DriverManager.getConnection(conexionUrl, usuario, password);
      
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
    
     //LAS SIGUIENTES FUNCIONES SON INVOCADAS EN EL CODIGO DEL FORMULARIO
    
    //FUNCION PARA AGREGAR CLIENTES A LA BASE DE DATOS
    public void agregar(Cliente cliente) {
 
         try {
                Connection conexion = conectarConDB();
              
       // CONSULTA PARA AGREGAR LOS DATOS A LA TABLA CLIENTE
            String sql = "INSERT INTO `clientes`(`dni`, `nombre`, `apellido`, `telefono`) VALUES ('"
                    //Hago dinamica la inserccion de datos a la tabla clientes de la DB
                    +cliente.obtenerDni()+"','"+cliente.obtenerNombre()+"','"
                    +cliente.obtenerApellido()+"','"
                    +cliente.obtenerTel()+"')" ;

            //Esto es para poder ejecutar la consulta
              Statement statement = conexion.createStatement();
              statement.execute(sql);
              
         } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
//--------------------------------------------------------------------------------------------------
       //FUNCION PARA ELIMINAR CLIENTES
              public void eliminar(String id){
                  
                  try{
                      Connection conexion = conectarConDB();
                      String sql = "DELETE FROM `clientes` WHERE `clientes`.`dni` = " + id ;
                      
                       //Esto es para poder ejecutar la consulta
                      Statement statement = conexion.createStatement();
                      statement.execute(sql);
                  }catch(Exception ex){
                      Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
              }
    
    //ESTA FUNCION DEVOLVERA EL LISTADO DE TODOS LOS CLIENTES
    public List<Cliente> listar() {
        
          //Aqui estaran los datos de la consulta
         List<Cliente> listado = new ArrayList<>();
 
        try {
                  Connection conexion = conectarConDB();          
            String consultaClientes = "SELECT * FROM `clientes` ";
                  
            //Esto es para poder ejecutar las consultas a la base de datos
           Statement statement = conexion.createStatement();
              //Aqui van a estar los datos de mi consulta
           ResultSet resultado = statement.executeQuery(consultaClientes); 
           
           //Recorro lo q hay en ResultSet (cada una de las filas de la tabla cliente)
           while(resultado.next()){
               //x cada fila creamos un cliente y seteamos los datos
               Cliente cliente = new Cliente ();  //CREO una INSTANCIA(obj) De la clase cliente
               cliente.setDni(resultado.getString("dni"));
               cliente.setNombre(resultado.getString("nombre"));
               cliente.setApellido(resultado.getString("apellido"));
               cliente.setTelefono(resultado.getString("telefono"));
               //Guardamos los datos
               listado.add(cliente);             
               }
           
          } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return listado;//Devolvemos el listado con los datos obtenidos en la consulta
  }
    
    //ESTAS FUNCIONES SON INVOCADAS EN EL CODIGO DEL FORMULARIO
    
}




