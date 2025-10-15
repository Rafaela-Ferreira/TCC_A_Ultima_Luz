package tile.blocosInterativos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.PainelDoJogo;

public class Fogueira extends BlocosInterativos{
    
    PainelDoJogo painel;
    boolean ativa = true;
    int contadorDeSprite = 0;
    int numeroDoSprite = 1;
    
    BufferedImage imagem1, imagem2, imagem3, imagem4;

    public Fogueira(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;
        
        
        // define como obstáculo interativo
        tipo = tipoObstaculo;

        temColisao = false; // o player pode se aproximar
        
        areaSolida.x = 10;
        areaSolida.y = 20;
        areaSolida.width = 28;
        areaSolida.height = 28;
        

        int i = 2;
        imagem1 = setup("/img/fogueira/fogueira1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        imagem2 = setup("/img/fogueira/fogueira2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        imagem3 = setup("/img/fogueira/fogueira3", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        imagem4 = setup("/img/fogueira/fogueira4", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);

        baixo1 = imagem1; 
    }

    public void interagir() {
        //não está funcionando ainda
        //painel.mEventos.fogueira(painel.iniciarEstadoDoJogo);
    }

    
    public void atualizar() {
        if (ativa) {
            contadorDeSprite++;

            // altera a imagem a cada 12 frames 
            if (contadorDeSprite > 12) {
                numeroDoSprite++;
                if (numeroDoSprite > 4) {
                    numeroDoSprite = 1;
                }

                switch (numeroDoSprite) {
                    case 1: baixo1 = imagem1; break;
                    case 2: baixo1 = imagem2; break;
                    case 3: baixo1 = imagem3; break;
                    case 4: baixo1 = imagem4; break;
                }

                contadorDeSprite = 0;
            }
        }
    
    }

    public void desenhar(Graphics2D g2) {
        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

        // Desenha a fogueira
        g2.drawImage(baixo1, telaX, telaY, null);

        // --- DEBUG: visualizar área de colisão ---
        if (temColisao) {
            g2.setColor(new Color(255, 0, 0, 100)); // vermelho semi-transparente
            g2.fillRect(
                telaX + areaSolida.x,
                telaY + areaSolida.y,
                areaSolida.width,
                areaSolida.height
            );
        }
    }

    
}
