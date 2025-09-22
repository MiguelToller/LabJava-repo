/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.Conexao;
import beans.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class AlunoDAO {
    
    private Conexao conexao;
    private Connection conn;

    public AlunoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, idade, curso) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir aluno: "+ex.getMessage());
        }
    }
    
    public Aluno getAluno(int id) {
        String sql = "SELECT * FROM Aluno WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setCurso(rs.getString("curso"));
                
                return aluno;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar aluno: " + ex.getMessage());
            return null;
        }
    }
    
    public void editar(Aluno aluno) {
        try {
            String sql = "UPDATE aluno set nome=?, idade=?, curso=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar aluno: "+ex.getMessage());
        }
    }
    
    public void excluir(int id) {
        try {
            String sql = "DELETE FROM Aluno WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar aluno: "+ex.getMessage());
        }
    }
    
    public List<Aluno> getAlunos() {
        String sql = "SELECT * FROM aluno";  // tabela aluno
        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs = stmt.executeQuery(); // executa a consulta

            List<Aluno> listaAlunos = new ArrayList<>(); // lista de alunos

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setCurso(rs.getString("curso"));

                listaAlunos.add(aluno);
            }

            return listaAlunos;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todos os alunos: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Aluno> getAlunosNome(String nome) {
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            List<Aluno> listaAlunos = new ArrayList<>();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setCurso(rs.getString("curso"));

                listaAlunos.add(aluno);
            }

            return listaAlunos;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar alunos pelo nome: " + ex.getMessage());
            return null;
        }
    }
    
}
