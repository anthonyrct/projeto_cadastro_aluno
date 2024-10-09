package com.example.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.Controller.AlunoController;
import com.example.Model.Aluno;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlunoForm extends JFrame{
     private JTextField nomeField;
    private JTextField emailField;
    private JTextField dataNascimentoField;
    private JButton adicionarButton;
    private JTable alunosTable;
    private AlunoController alunoController;
    private DefaultTableModel tableModel;

    public AlunoForm(Connection conn) {
        alunoController = new AlunoController(conn);

        setTitle("Gerenciamento de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 10, 80, 25);
        panel.add(nomeLabel);

        nomeField = new JTextField(20);
        nomeField.setBounds(100, 10, 160, 25);
        panel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 40, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 40, 160, 25);
        panel.add(emailField);

        JLabel dataNascimentoLabel = new JLabel("Data Nasc.:");
        dataNascimentoLabel.setBounds(10, 70, 80, 25);
        panel.add(dataNascimentoLabel);

        dataNascimentoField = new JTextField(20);
        dataNascimentoField.setBounds(100, 70, 160, 25);
        panel.add(dataNascimentoField);

        adicionarButton = new JButton("Adicionar Aluno");
        adicionarButton.setBounds(10, 100, 150, 25);
        panel.add(adicionarButton);

        // Configurando a tabela e seu modelo
        tableModel = new DefaultTableModel(new String[]{"Nome", "Email", "Data Nasc."}, 0);
        alunosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(alunosTable);
        scrollPane.setBounds(10, 150, 360, 100);
        panel.add(scrollPane);

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarAluno();
            }
        });

        // Atualiza a tabela ao iniciar
        try {
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + ex.getMessage());
        }
    }

    private void adicionarAluno() {
        try {
            String nome = nomeField.getText();
            String email = emailField.getText();
            java.sql.Date dataNascimento = java.sql.Date.valueOf(dataNascimentoField.getText());

            // Validando se os campos estão preenchidos
            if (nome.isEmpty() || email.isEmpty() || dataNascimentoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
                return;
            }

            Aluno aluno = new Aluno(nome, email, dataNascimento);
            alunoController.adicionarAluno(aluno);
            JOptionPane.showMessageDialog(this, "Aluno adicionado com sucesso!");

            // Limpa os campos
            nomeField.setText("");
            emailField.setText("");
            dataNascimentoField.setText("");

            // Atualiza a tabela
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar aluno: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato yyyy-MM-dd.");
        }
    }

    private void atualizarTabela() throws SQLException {
        // Limpa a tabela antes de atualizá-la
        tableModel.setRowCount(0);

        // Obter a lista de alunos do controlador
        List<Aluno> alunos = alunoController.listarAlunos();

        // Adiciona cada aluno à tabela
        for (Aluno aluno : alunos) {
            tableModel.addRow(new Object[]{aluno.getNome(), aluno.getEmail(), aluno.getDataNascimento()});
        }
    }

    }
