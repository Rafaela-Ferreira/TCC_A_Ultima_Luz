package entidade;

import java.awt.Color;
import java.awt.Graphics2D;

import main.PainelDoJogo;

public class Particula extends Entidade{

    Entidade gerador;
    Color cor;
    int tamanho;
    int xd;
    int yd;

    public Particula(PainelDoJogo painel, Entidade gerador, Color cor, int tamanho, 
                    int velocidade, int vidaMaxima, int xd, int yd) {
        super(painel);
        this.gerador = gerador;
        this.cor = cor;
        this.tamanho = tamanho;
        this.velocidade = velocidade;
        this.vidaMaxima = vidaMaxima;
        this.xd = xd;
        this.yd = yd;

        vida = vidaMaxima;
        int offset = (painel.tamanhoDoTile/2) - (tamanho/2);
        mundoX = gerador.mundoX + offset;
        mundoY = gerador.mundoY + offset;
    }

    public void atualizar(){
        vida--;

        if(vida < vidaMaxima/3){
            yd++;
        }

        mundoX += xd*velocidade;
        mundoY += yd*velocidade;

        if(vida == 0){
            vivo = false;
        }
    }

    public void desenhar(Graphics2D g2){
        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

        g2.setColor(cor);
        g2.fillRect(telaX, telaY, tamanho, tamanho);
    }
    
}
