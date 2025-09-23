CREATE DATABASE veiculos;
USE veiculos;

CREATE TABLE Veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano INT NOT NULL,
    placa VARCHAR(50) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    pessoa_id INT,
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id)
);

CREATE TABLE Pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL
);

select * from veiculo;
select * from pessoa;

INSERT INTO Pessoa VALUES
(1, 'Miguel', 'M');
