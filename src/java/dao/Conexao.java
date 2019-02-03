/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William
 */
public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost/comida";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String SENHA = "";
    
    private static Connection con;
    
    
    public static Connection conectar() {
        try{
    Class.forName(DRIVER);
    con = DriverManager.getConnection(URL, USER , SENHA);
    
    return con;
        }
        catch(ClassNotFoundException ex){
            System.err.println("Erro ao carregar a Classe " + ex.getMessage());
            return null;
        }
        catch(SQLException ex){
            System.err.println("Erro de Conexao " + ex.getMessage());
        return null;    
        }
        
}
    
    
    public  static void fechaConexao(){
        try{
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null , ex);
        }
    }
    
    
    
}
