package tile;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.PainelDoJogo;

public class Mapa extends GerenciadorDeBlocos{

    PainelDoJogo painel;
    BufferedImage mapaDoMundo[];
    public boolean miniMapaAtivo = false;

    public Mapa(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;
        criarMapaDoMundo();
    }
    
    public void criarMapaDoMundo(){
        mapaDoMundo = new BufferedImage[painel.maxMapa];
        int mapaDoMundoLargura = painel.tamanhoDoTile * painel.maxColunasMundo; 
        int mapaDoMundoAltura = painel.tamanhoDoTile * painel.maxLinhasMundo;

        for(int i = 0; i < painel.maxMapa; i++){
            
            mapaDoMundo[i] = new BufferedImage(mapaDoMundoLargura, mapaDoMundoAltura, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)mapaDoMundo[i].createGraphics();

            int coluna = 0;
            int linha = 0;

            while(coluna < painel.maxColunasMundo && linha < painel.maxLinhasMundo){
                int numeroDeBloco = numerosDoMapa[i][coluna][linha];
                int x = painel.tamanhoDoTile * coluna;
                int y = painel.tamanhoDoTile * linha;
                g2.drawImage(blocos[numeroDeBloco].imagem, x, y, null);


                coluna++;
                if(coluna == painel.maxColunasMundo){
                    coluna = 0;
                    linha++;
                }
            }
            g2.dispose();
        }
    }


    public void desenharMapaCompleto(Graphics2D g2){
        
        //Cor de fundo do mapa
        g2.setColor(Color.black);
        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);


        //desenha o mapa
        int largura = 500;
        int altura = 500;
        int x = painel.larguraTela/2 - largura/2;
        int y = painel.alturaTela/2 - altura/2;
        g2.drawImage(mapaDoMundo[painel.mapaAtual],x, y, largura, altura, null);

        //desenhar o jogador
        double escala = (double)(painel.tamanhoDoTile * painel.maxColunasMundo) / largura;
        int jogadorX = (int)(x + painel.jogador.mundoX / escala);
        int jogadorY = (int)(y + painel.jogador.mundoY / escala);
        int tamanhaDoJogador = (int)(painel.tamanhoDoTile /escala);
        g2.drawImage(painel.jogador.baixo1, jogadorX, jogadorY, tamanhaDoJogador, tamanhaDoJogador, null);
    
        //hint
        g2.setFont(painel.interfaceDoUsuario.maruMonica.deriveFont(32f));
        g2.setColor(Color.white);

        String mensagem = "Pressione 'M' \npara fechar o mapa";
        int mensagemY = 450;

        for(String linha: mensagem.split("\n")){
            g2.drawString(linha , 700, mensagemY);
            mensagemY += 40;
        }
    }


    public void desenharMiniMapa(Graphics2D g2){
        if(miniMapaAtivo == true){
            
            //desenhar mini mapa
            int largura = 200;
            int altura = 200;
            int x = painel.larguraTela - largura - 50;
            int y = 50;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(mapaDoMundo[painel.mapaAtual],x, y, largura, altura, null);


            //desenhar o jogador
            double escala = (double)(painel.tamanhoDoTile * painel.maxColunasMundo) / largura;
            int jogadorX = (int)(x + painel.jogador.mundoX / escala);
            int jogadorY = (int)(y + painel.jogador.mundoY / escala);
            int tamanhaDoJogador = (int)(painel.tamanhoDoTile /3);
            g2.drawImage(painel.jogador.baixo1, jogadorX-6, jogadorY-6, tamanhaDoJogador, tamanhaDoJogador, null);
        
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

    }
}
