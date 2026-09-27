-- Création de la base de données et des tables du projet
-- Société de Transport Multimodal

CREATE DATABASE IF NOT EXISTS transportdb;
USE transportdb;

CREATE TABLE IF NOT EXISTS cargaison (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(20) NOT NULL,
    distance DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS marchandise (
    id INT PRIMARY KEY,
    poids DOUBLE NOT NULL,
    volume DOUBLE NOT NULL,
    idCargaison INT NOT NULL,
    FOREIGN KEY (idCargaison) REFERENCES cargaison(id)
);
