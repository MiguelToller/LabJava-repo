/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 * Classe que representa um aluno do sistema de cadastro.
 * @author Miguel
 */
public class Aluno {
    
    private String nome;
    private LocalDate dataNasc;
    private char sexo;
    private String matricula;
    private String curso;
    private String cpf;
    private String endereco;
    private String estado;
    private String telefone;

    public Aluno(String nome, LocalDate dataNasc, char sexo, String matricula, String curso, String cpf, String endereco, String estado, String telefone) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.matricula = matricula;
        this.curso = curso;
        this.cpf = cpf;
        this.endereco = endereco;
        this.estado = estado;
        this.telefone = telefone;
    }
    
    public Object[] obterDados() {
        return new Object[] {nome, dataNasc, sexo, matricula, curso, cpf, endereco, estado, telefone};
    }
    
}
