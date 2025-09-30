/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Pessoa;
import conexao.Conexao;
import beans.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author laboratorio
 */
public class VeiculoDAO {
    
    private Conexao conexao;
    private Connection conn;

    public VeiculoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (marca, modelo, ano, placa, cor, pessoa_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getPlaca());
            stmt.setString(5, veiculo.getCor());
            stmt.setInt(6, veiculo.getPessoaId().getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir veiculo: "+ex.getMessage());
        }
    }
    

    public Veiculo getVeiculo(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.first()) {
                return null; // veículo não encontrado
            }

            Veiculo v = new Veiculo();
            v.setId(rs.getInt("id"));
            v.setMarca(rs.getString("marca"));   // <--- adiciona marca
            v.setModelo(rs.getString("modelo"));
            v.setAno(rs.getInt("ano"));           // <--- adiciona ano
            v.setPlaca(rs.getString("placa"));
            v.setCor(rs.getString("cor"));        // <--- adiciona cor

            PessoaDAO pDAO = new PessoaDAO();
            Pessoa p = pDAO.getPessoaPorId(rs.getInt("pessoa_id"));
            v.setPessoaId(p);

            rs.close();
            stmt.close();

            return v;

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar veículo: " + ex.getMessage());
            return null;
        }
    }


    
    public void editar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET marca=?, modelo=?, ano=?, placa=?, cor=?, pessoa_id=? WHERE id=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getPlaca());
            stmt.setString(5, veiculo.getCor());
            stmt.setInt(6, veiculo.getPessoaId().getId());
            stmt.setInt(7, veiculo.getId());               

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar veiculo: " + ex.getMessage());
        }       
    }

    public void excluir(int id) {
        String sql = "DELETE from veiculo where id=?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir veiculo: " + ex.getMessage());
        }
    }
    
    public List<Veiculo> getVeiculos() {
        String sql = "SELECT v.*, p.id AS pessoa_id, p.nome AS pessoa_nome "
                + "FROM veiculo v " 
                + "JOIN pessoa p ON v.pessoa_id = p.id";

        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs = stmt.executeQuery();  // executa a consulta

            List<Veiculo> listaVeiculos = new ArrayList<>();  // lista de veículos

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));

                // Recupera os dados da pessoa associada ao veículo
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("pessoa_id"));
                pessoa.setNome(rs.getString("pessoa_nome"));

                veiculo.setPessoaId(pessoa);  // Associa a pessoa ao veículo
                listaVeiculos.add(veiculo);  // Adiciona o veículo à lista
            }

            return listaVeiculos;  // Retorna a lista de veículos
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todos os veículos: " + ex.getMessage());
            return null;  // Retorna null em caso de erro
        }
    }

    
}
