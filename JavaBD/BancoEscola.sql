CREATE DATABASE escola;
USE escola;

CREATE TABLE Aluno (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
idade INT NOT NULL,
curso VARCHAR(50) NOT NULL
);

CREATE TABLE Professor (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
idade INT NOT NULL,
disciplina VARCHAR(50)
);

CREATE TABLE Matricula (
id INT AUTO_INCREMENT PRIMARY KEY,
id_aluno INT,
id_professor INT,
data_matricula DATE,
FOREIGN KEY (id_aluno) REFERENCES aluno(id),
FOREIGN KEY (id_professor) REFERENCES professor(id)
);

INSERT INTO aluno (nome, idade, curso)
VALUES
('Joao', 20, 'Matematica'),
('Maria', 22, 'Historia'),
('Pedro', 21, 'Ciencia da Computacao'),
('Ana', 19, 'Biologia'),
('Carlos', 23, 'Economia');

INSERT INTO professor (nome, idade, disciplina)
VALUES
('Ricardo', 35, 'Java'),
('Zamba', 50, 'Sistemas Distribuidos'),
('Ana', 51, 'Automatos');

INSERT INTO matricula (id_aluno, id_professor, data_matricula)
VALUES
(1, 1, '2023-01-15'),
(2, 2, '2023-02-20'),
(3, 3, '2023-03-10'),
(4, 1, '2023-04-05'),
(5, 2, '2023-05-12');

-- Consulta de Alunos por Curso
SELECT nome, curso
FROM aluno;

-- Consulta de Professores por Disciplina
SELECT nome, disciplina
FROM professor;
