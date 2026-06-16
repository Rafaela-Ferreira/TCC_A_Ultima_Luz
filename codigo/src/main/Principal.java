package main;



import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Principal {
    public static JFrame janela;
    public static void main(String[] args) throws Exception {
        
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("A Última Luz");
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
        ImageIcon icone = new ImageIcon(getClass().getClassLoader().getResource("res/logoAultimaLuz.jpg")); 
        janela.setIconImage(icone.getImage());
    }

    
    // terminal powershell
    // mkdir bin
    // javac -d bin (Get-ChildItem src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
    //OBS: tiver que usar o código abaixo por conta de acentuação do portuguÊs
    // javac -encoding UTF-8 -d bin (Get-ChildItem src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
    
    // usar SEMPRE que atualizar o projeto (alteração nos recursos/arquivos de texto/imagem/som) 
    // Copy-Item -Recurse -Force res bin/

    // Rodar o projeto
    // java -cp bin main.Principal
    
}


