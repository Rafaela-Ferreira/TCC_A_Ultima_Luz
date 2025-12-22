package main.inimigo;

import entidade.Entidade;
import java.util.Random;
import main.PainelDoJogo;
import objeto.ObjCoracao;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;
import objeto.ObjPedra;

public class LodoVerde extends Entidade{

    PainelDoJogo painel;

    public LodoVerde(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;
        
        tipo = tipoInimigo;
        nome ="Lodo Verde";
        velocidadePadrao = 1;
        velocidade = velocidadePadrao;
        vidaMaxima = 4;
        vida = vidaMaxima;
        ataque = 5;
        defesa = 0;
        exp = 2;

        projetil = new ObjPedra(painel);

        areaSolida.x = 3;
        areaSolida.y = 18;
        areaSolida.width = 42;
        areaSolida.height = 30;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        getImagem();
    }
    
    public void getImagem(){
        cima1 = setup("/res/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setAcao(){


        if(pastaAtiva == true){

            verificarSeParouDePerseguir_ou_nao(painel.jogador, 15,100);

            //procurar a direção para ir
            //para o inimigo seguir o jogador
            procurarCaminho(getColunaAtual(painel.jogador), getLinhaAtual(painel.jogador));

            //verifique se ele atira um projétil - apenas o vermelho atira pedra
            //verificarSeAtirou_ou_nao(200, 30);
        }
        else{
            //verifique se ele começa a perseguir
            verificarSeComecouAPerseguir_ou_nao(painel.jogador, 5, 100);

            getDirecaoAleatoria(120);
        }
  
    }

    public void acaoAoDano(){
        contadorDeBloqueioDeAcao = 0;
        //direcao = painel.jogador.direcao;
        pastaAtiva = true;
    }


    public void verificarDrop(){
        //lançar um dado
        int i = new Random().nextInt(100)+1;

        //definir o drop do inimigo 
        if(i < 50){
            droparItem(new ObjMoedaBronze(painel));
        }
        if(i >= 50 && i < 75){
            droparItem(new ObjCoracao(painel));
        }
        if(i >= 75 && i < 100){
            droparItem(new ObjMana(painel));
        }
    }
}