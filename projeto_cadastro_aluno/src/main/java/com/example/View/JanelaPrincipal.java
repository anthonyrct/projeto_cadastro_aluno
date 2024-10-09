package com.example.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JanelaPrincipal extends JFrame {
     public JanelaPrincipal() {
        // Configuração básica do JFrame
        setTitle("Sistema de Gerenciamento");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centraliza a janela na tela
        setLayout(null);  // Usaremos layout nulo para facilitar o posicionamento dos botões

        // Criação dos botões
        JButton cadastroButton = new JButton("Cadastrar Usuário");
        JButton loginButton = new JButton("Login");

        // Configurando posições e tamanhos dos botões
        cadastroButton.setBounds(100, 50, 200, 30);
        loginButton.setBounds(100, 100, 200, 30);

        // Adicionando ação para o botão "Cadastrar Usuário"
        cadastroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ao clicar, abre a tela de cadastro
                abrirTelaCadastro();
            }
        });

        // Adicionando ação para o botão "Login"
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ao clicar, abre a tela de login
                abrirTelaLogin();
            }
        });

        // Adicionando os botões ao JFrame
        add(cadastroButton);
        add(loginButton);
    }

    // Método para abrir a tela de cadastro
    private void abrirTelaCadastro() {
        CadastroUsuario cadastroTela = new CadastroUsuario();
        cadastroTela.setVisible(true);  // Mostra a janela de cadastro
    }

    // Método para abrir a tela de login
    private void abrirTelaLogin() {
        LoginUsuario loginTela = new LoginUsuario();
        loginTela.setVisible(true);  // Mostra a janela de login
    }
}
