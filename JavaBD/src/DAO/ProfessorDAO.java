/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.Conexao;
import beans.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class ProfessorDAO {
    
    private Conexao conexao;
    private Connection conn;

    public ProfessorDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Professor professor) {
        String sql = "INSERT INTO professor (nome, idade, disciplina) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getDisciplina());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir professor: "+ex.getMessage());
        }
    }
    
    public Professor getProfessor(int id) {
        String sql = "SELECT * FROM Professor WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setDisciplina(rs.getString("disciplina"));
                
                return professor;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar professor: " + ex.getMessage());
            return null;
        }
    }
    
    public void editar(Professor professor) {
        try {
            String sql = "UPDATE Professor SET nome=?, idade=?, disciplina=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getDisciplina());
            stmt.setInt(4, professor.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar professor: "+ex.getMessage());
        }
    }
    
    public void excluir(int id) {
        try {
            String sql = "DELETE FROM Professor WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar professor: "+ex.getMessage());
        }
    }
    
    public List<Professor> getProfessores() {
        String sql = "SELECT * FROM professor";
        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs = stmt.executeQuery();

            List<Professor> listaProfessores = new ArrayList<>();

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setDisciplina(rs.getString("disciplina"));

                listaProfessores.add(professor);
            }

            return listaProfessores;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todos os professores: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Professor> getProfessoresNome(String nome) {
        String sql = "SELECT * FROM professor WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            List<Professor> listaProfessores = new ArrayList<>();

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setDisciplina(rs.getString("disciplina"));

                listaProfessores.add(professor);
            }

            return listaProfessores;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar professores pelo nome: " + ex.getMessage());
            return null;
        }
    }
    
}
