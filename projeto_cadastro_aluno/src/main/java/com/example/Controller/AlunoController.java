package com.example.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.Model.Aluno;

public class AlunoController {
    private Connection conn;

    public AlunoController(Connection conn) {
        this.conn = conn;
    }

    public void adicionarAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos (nome, email, data_nascimento) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String sql = "UPDATE alunos SET nome = ?, email = ?, data_nascimento = ? WHERE email = ?"; // ou onde você usa o ID
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setDate(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getEmail()); // Use o ID se você tiver
            stmt.executeUpdate();
        }
    }
    
    public void excluirAluno(String email) throws SQLException {
        String sql = "DELETE FROM alunos WHERE email = ?"; // ou onde você usa o ID
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Aluno> listarAlunos() throws SQLException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setDataNascimento(rs.getDate("data_nascimento"));
                alunos.add(aluno);
            }
        }
        return alunos;
    }
}
