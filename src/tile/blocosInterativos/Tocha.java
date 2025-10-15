package tile.blocosInterativos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import main.PainelDoJogo;

public class Tocha extends BlocosInterativos{
    
    PainelDoJogo painel;
    boolean ativa = true;
    int contadorDeSprite = 0;
    int numeroDoSprite = 1;
    
    BufferedImage imagem1, imagem2, imagem3, imagem4, imagem5, imagem6;

    public Tocha(PainelDoJogo painel, int coluna, int linha) {
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

        imagem1 = setup("/img/fogueira/tocha0", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem2 = setup("/img/fogueira/tocha1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem3 = setup("/img/fogueira/tocha2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem4 = setup("/img/fogueira/tocha3", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem5 = setup("/img/fogueira/tocha4", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem6 = setup("/img/fogueira/tocha5", painel.tamanhoDoTile, painel.tamanhoDoTile);

        baixo1 = imagem1; // começa com a primeira imagem

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
                    case 5: baixo1 = imagem5; break;
                    case 6: baixo1 = imagem6; break;
                }

                contadorDeSprite = 0;
            }
        }
    
    }
    
    public void desenharLuz(Graphics2D g2, int telaX, int telaY) {
        if (!ativa) return;

        // 🔥 Raio base da luz com leve variação (tremulação)
        double variacao = Math.sin(System.currentTimeMillis() * 0.008) * 3; // leve oscilação
        int raio = (int) (painel.tamanhoDoTile * 2 + variacao);

        
        // Ponto central da luz
        Point2D centro = new Point2D.Float(
            telaX + painel.tamanhoDoTile / 2f, 
            telaY + painel.tamanhoDoTile / 2f
        );

        // Gradiente claro, alaranjado no centro
        Color cor[] = new Color[7];
        float fracao[] = new float[7];

        cor[0] = new Color(255, 180, 50, 200); 
        cor[1] = new Color(255, 200, 80, 160); 
        cor[2] = new Color(255, 220, 120, 120);
        cor[3] = new Color(255, 240, 180, 80); 
        cor[4] = new Color(255, 250, 220, 50); 
        cor[5] = new Color(255, 255, 240, 30);
        cor[6] = new Color(255, 255, 245, 10);

        fracao[0] = 0f;
        fracao[1] = 0.2f;
        fracao[2] = 0.4f;
        fracao[3] = 0.6f;
        fracao[4] = 0.75f;
        fracao[5] = 0.9f;
        fracao[6] = 1f;

        RadialGradientPaint gradiente = new RadialGradientPaint(centro, raio, fracao, cor);

        g2.setPaint(gradiente);
        g2.fillOval(
            telaX - raio + painel.tamanhoDoTile / 2, 
            telaY - raio + painel.tamanhoDoTile / 2, 
            raio * 2, 
            raio * 2
        );
    }
}
