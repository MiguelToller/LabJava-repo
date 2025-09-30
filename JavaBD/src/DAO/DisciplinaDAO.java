/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.Conexao;
import beans.Disciplina;
import beans.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class DisciplinaDAO {
    
    private Conexao conexao;
    private Connection conn;
    
    public DisciplinaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Disciplina d) {
        String sql = "INSERT INTO Disciplina (nome, carga_horaria, id_professor) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getCargaHoraria());
            stmt.setInt(3, d.getIdProfessor());
            stmt.executeUpdate();
            stmt.close();
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir disciplina: "+ex.getMessage());
        }
    }
    
    public List<Disciplina> getDisciplinas() {
        String sql = "SELECT * FROM Disciplina";
        List<Disciplina> lista = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nome"));
                d.setCargaHoraria(rs.getInt("carga_horaria"));
                d.setIdProfessor(rs.getInt("id_professor"));
                lista.add(d);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar disciplinas: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Professor> getProfessores() {
        ProfessorDAO pDAO = new ProfessorDAO();
        return pDAO.getProfessores();
    }
    
}
