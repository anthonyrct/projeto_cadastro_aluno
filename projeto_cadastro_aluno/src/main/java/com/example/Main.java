package com.example;

import com.example.View.JanelaPrincipal;

public class Main {
    public static void main(String[] args) {
        // Inicia a aplicação
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JanelaPrincipal janela = new JanelaPrincipal();
                janela.setVisible(true);
            }
        });
    }
}