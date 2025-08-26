/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javabd;

import Conexao.Conexao;
import DAO.AlunoDAO;
import beans.Aluno;

/**
 *
 * @author laboratorio
 */
public class JavaBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao c = new Conexao();
        c.getConexao();
        
        Aluno a = new Aluno();
        a.setNome("Miguel");
        a.setIdade(20);
        a.setCurso("Ciencia da Computacao");
        
        AlunoDAO aDAO = new AlunoDAO();
        aDAO.inserir(a);
        
    }
    
}
