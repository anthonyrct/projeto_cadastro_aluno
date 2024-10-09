package com.example.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastroUsuario extends JFrame {
    public CadastroUsuario() {
        // Configuração básica do JFrame para Cadastro
        setTitle("Cadastro de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        panel.add(nomeLabel);

        JTextField nomeField = new JTextField(20);
        nomeField.setBounds(100, 20, 160, 25);
        panel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 60, 80, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField(20);
        emailField.setBounds(100, 60, 160, 25);
        panel.add(emailField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(100, 100, 100, 25);
        panel.add(cadastrarButton);

        // Aqui você pode adicionar a lógica de cadastro de usuário ao clicar no botão
    }
}
