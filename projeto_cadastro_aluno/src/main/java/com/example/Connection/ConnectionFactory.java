package com.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/cadastro_aluno"; // Altere para a URL do seu banco de dados
    private static final String USER = "postgres"; // Altere para seu usuário do banco de dados
    private static final String PASSWORD = "postgres"; // Altere para sua senha do banco de dados

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
