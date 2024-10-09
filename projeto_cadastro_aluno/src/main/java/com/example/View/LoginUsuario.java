package com.example.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Adicione esta importação
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUsuario extends JFrame {
    public LoginUsuario() {
        // Configuração básica do JFrame para Login
        setTitle("Login de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField(20);
        emailField.setBounds(100, 20, 160, 25);
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 60, 80, 25);
        panel.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField(20);
        senhaField.setBounds(100, 60, 160, 25);
        panel.add(senhaField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 100, 25);
        panel.add(loginButton);

        // Adicionando ação ao botão de login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                // Verificar credenciais no banco de dados
                if (autenticarUsuario(email, senha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");

                    // Fecha a janela de login
                    dispose();

                    // Abre o AlunoForm
                    abrirAlunoForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para autenticar o usuário no banco de dados
    private boolean autenticarUsuario(String email, String senha) {
        boolean autenticado = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Conectar ao banco de dados (ajuste a URL, usuário e senha do seu banco)
            String url = "jdbc:postgresql://localhost:5432/seu_banco";
            String user = "seu_usuario";
            String password = "sua_senha";
            conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL para verificar o usuário
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            // Executa a consulta
            rs = stmt.executeQuery();

            // Se houver um resultado, o usuário foi autenticado
            if (rs.next()) {
                autenticado = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fechar as conexões
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return autenticado;
    }

    // Método para abrir o AlunoForm após o login bem-sucedido
    private void abrirAlunoForm() {
        Connection conn = null; // Certifique-se de obter a conexão da forma que for apropriada
        // Obter a conexão existente, por exemplo:
        String url = "jdbc:postgresql://localhost:5432/cadastro_aluno";
        String user = "postgres";
        String password = "postgres";

        try {
            conn = DriverManager.getConnection(url, user, password);
            AlunoForm alunoForm = new AlunoForm(conn); // Passando a conexão
            alunoForm.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir o AlunoForm: " + e.getMessage());
        }
    }
}
