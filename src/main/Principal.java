package main;
import javax.swing.JFrame;

public class Principal {
    public static JFrame janela;
    public static void main(String[] args) throws Exception {
        
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("RPG 2D de Aventura");
        //janela.setUndecorated(true);

        PainelDoJogo painelDoJogo = new PainelDoJogo();
        janela.add(painelDoJogo);

        janela.pack(); // Ajusta o tamanho da janela com base no painel

        janela.setLocationRelativeTo(null); // Centraliza a janela na tela
        janela.setVisible(true);
        
        painelDoJogo.setarObjetos();
        painelDoJogo.iniciarThreadDoJogo(); // Inicia o loop do jogo
    

    }
}
