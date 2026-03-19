package tile.blocosInterativos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.PainelDoJogo;

public class TesteDeFogueira extends BlocosInterativos {
    
    PainelDoJogo painel;
    boolean ativa = true;
    int contadorDeSprite = 0;
    int numeroDoSprite = 1;
    
    BufferedImage imagem1, imagem2, imagem3, imagem4;

    int escala = 2;
    int largura;
    int altura;

    public TesteDeFogueira(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;


        largura = painel.tamanhoDoTile * escala;
        altura = painel.tamanhoDoTile * escala;

        imagem1 = setup("/res/fogueira/fogueira1", largura, altura);
        imagem2 = setup("/res/fogueira/fogueira2", largura, altura);
        imagem3 = setup("/res/fogueira/fogueira3", largura, altura);
        imagem4 = setup("/res/fogueira/fogueira4", largura, altura);

        baixo1 = imagem1;
    }

    public void atualizar() {
        if (ativa) {
            contadorDeSprite++;

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

        int ajusteY = altura - painel.tamanhoDoTile;

        g2.drawImage(baixo1, telaX, telaY - ajusteY, null);


    }
}
