/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alimento;

/**
 *
 * @author William
 */
public class AlimentosDao {
    


 private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Alimento> Select() {
        List<Alimento> alimentos = new ArrayList<>();
        
        sql = "SELECT * FROM alimento;";
        try{
        con = Conexao.conectar();  //Conecta com o banco de dados
        pst = con.prepareStatement(sql);  //Prepara o SQL
        rs = pst.executeQuery(); //Executa a query e retorna um resultset
        
        while(rs.next()){
           Alimento alimento = new Alimento();
           alimento.setIdAlimento(rs.getInt("idalimento"));
            alimento.setNome(rs.getString("nome"));
            alimento.setCalorias(rs.getInt("calorias"));
            alimento.setGordura(rs.getDouble("gordura"));
            alimento.setCarboidratos(rs.getDouble("carboidratos"));
            alimento.setPorcao(rs.getString("porcao"));
           
            
           alimentos.add(alimento);
        }
        Conexao.fechaConexao();//desconecta do banco de dados
        return alimentos;
        
    }catch(SQLException ex){
      
            System.err.println("Erro de SQL no Select do Alimento" + ex.getMessage());
        
        return null;
    }
        
    }
    
    
    public boolean Insert(Alimento alimento) {
      try{
        sql = "INSERT INTO alimento (nome, calorias, gordura, carboidratos, porcao) VALUES (?,?,?,?,?,?)";
        con = Conexao.conectar();
        pst = con.prepareStatement(sql);
        pst.setString(1,alimento.getNome());
        pst.setInt(2,alimento.getCalorias());
        pst.setDouble(3, alimento.getGordura());
        pst.setDouble(4, alimento.getCarboidratos());
        pst.setString(5, alimento.getPorcao());
        
        pst.execute();
        Conexao.fechaConexao();
        return true;
      }
      catch(SQLException ex){
          System.err.println("Erro de SQL no Insert do Alimento" + ex.getMessage());
          return false;
      }
         
    }
    
    
    public boolean Delete(int id){
       try{
        sql = "DELETE FROM alimento WHERE idalimento = ? ";
        con = Conexao.conectar();
        pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        Conexao.fechaConexao();
        return true;
        
       }
       catch( SQLException ex){
           System.err.println("Erro no DELETE do Alimento " + ex.getMessage());
          return false;
      }
    }
    
    
    public boolean Update(Alimento alimento) {
      try{
        sql = "UPDATE alimento SET nome = ? , calorias = ?, gordura = ?, carboidratos =?, porcao =? WHERE id = ? ";
        con = Conexao.conectar();
        pst = con.prepareStatement(sql);
        pst.setString(1, alimento.getNome());
        pst.setInt(2,alimento.getCalorias());
        pst.setDouble(3, alimento.getGordura());
        pst.setDouble(4, alimento.getCarboidratos());
        pst.setString(5, alimento.getPorcao());
        pst.setInt(3, alimento.getIdAlimento());
        
       
        pst.executeUpdate();
         Conexao.fechaConexao();
        return true;
      }catch( SQLException ex){
          System.err.println("Erro no Update do Alimento "+ ex.getMessage());
          return false;
      }
         
    }

}