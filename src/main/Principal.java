package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Principal {
    public static JFrame janela;
    public static void main(String[] args) throws Exception {
        
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Titulo do jogo");
        new Principal().setIcone();

        PainelDoJogo painelDoJogo = new PainelDoJogo();
        janela.add(painelDoJogo);

        painelDoJogo.config.carregarConfiguracoes();
        if(painelDoJogo.telaCheiaAtiva == true){
            janela.setUndecorated(true);
        }

        janela.pack(); // Ajusta o tamanho da janela com base no painel

        janela.setLocationRelativeTo(null); // Centraliza a janela na tela
        janela.setVisible(true);
        
        painelDoJogo.setarObjetos();
        painelDoJogo.iniciarThreadDoJogo(); // Inicia o loop do jogo
    
    }

    public void setIcone(){
        ImageIcon icone = new ImageIcon(getClass().getClassLoader().getResource("img/spritesjogador/boy_down_1.png"));
        janela.setIconImage(icone.getImage());
    }
}
