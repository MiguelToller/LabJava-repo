/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Pessoa;
import conexao.Conexao;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class PessoaDAO {
    private Conexao conexao;
    private Connection conn;

    public PessoaDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public Pessoa getPessoaPorId(int idPessoa) {
    String sql = "SELECT * FROM pessoa WHERE id = ?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPessoa);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Pessoa p = new Pessoa();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setSexo(rs.getString("sexo"));
            return p;
        } else {
            return null; // Pessoa não encontrada
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao buscar pessoa por ID: " + ex.getMessage());
        return null;
    }
}
    
    public List<Pessoa> getPessoas(){
        String sql = "SELECT * FROM pessoa";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Pessoa> listaPessoas = new ArrayList();
            
            while(rs.next()){
                Pessoa p = new Pessoa();   
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar todas as pessoas: " + ex.getMessage());
            return null;
        }   
      
    }
    
    public List<Pessoa> getPessoasNome(String nome, String sexo){
        // "SELECT * FROM pessoa WHERE nome LIKE ?"
        String sql = "SELECT * FROM pessoa WHERE nome LIKE ? and sexo LIKE ?";
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + sexo + "%");
            ResultSet rs = stmt.executeQuery(); //Obtenho o retorno da consulta e armazeno no ResultSet
            List<Pessoa> listaPessoas = new ArrayList(); //Preparo uma lista de objetos que vou armazenar a consulta
            
            //Percorre rs e salva as informações dentro de um objeto Pessoa e depois adiciona na lista
            while(rs.next()){
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar todas as pessoas: " + ex.getMessage());
            return null;
        }   
    }
    
    
    public void inserir(Pessoa pessoa){
        String sql = "INSERT INTO pessoa (nome, sexo) VALUES (?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao inserir pessoa:" + ex.getMessage());
        }
    }
    
    public void editar(Pessoa pessoa){
        try{
            String sql = "UPDATE pessoa set nome=?, sexo=?, WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setInt(3, pessoa.getId());
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            String sql = "delete from pessoa WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
        }
    }
    
    
    
}