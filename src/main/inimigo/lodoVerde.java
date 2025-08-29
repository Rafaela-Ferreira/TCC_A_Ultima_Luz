package main.inimigo;

import java.util.Random;

import entidade.Entidade;
import main.PainelDoJogo;

public class lodoVerde extends Entidade{

    PainelDoJogo painel;

    public lodoVerde(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;
        
        tipo = 2;
        nome ="Lodo Verde";
        velocidade = 1;
        vidaMaxima = 5;
        vida = vidaMaxima;
        ataque = 5;
        defesa = 0;
        exp = 2;

        areaSolida.x = 3;
        areaSolida.y = 18;
        areaSolida.width = 42;
        areaSolida.height = 30;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        getImagem();
    }
    
    public void getImagem(){
        cima1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setAcao(){
        contadorDeBloqueioDeAcao++;

        //esperar 120 (2 segundos) para mudar de direção
        if(contadorDeBloqueioDeAcao == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1; //0 - 100

            if(i <= 25){
                direcao = "cima";
            }
            if(i > 25 && i <= 50){
                direcao = "baixo";
            }
            if(i > 50 && i <= 75){
                direcao = "esquerda";
            }
            if(i > 75 && i <= 100){
                direcao = "direita";
            }

            contadorDeBloqueioDeAcao = 0;
        }
    }

    public void acaoAoDano(){
        contadorDeBloqueioDeAcao = 0;
        direcao = painel.jogador.direcao;
    }
}
