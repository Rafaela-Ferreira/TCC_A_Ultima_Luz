package main.inimigo;

import java.util.Random;

import entidade.Entidade;
import main.PainelDoJogo;
import objeto.ObjCoracao;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;

public class InimigoMorcego extends Entidade{

    PainelDoJogo painel;

    public InimigoMorcego(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;
        
        tipo = tipoInimigo;
        nome ="Morcego";
        velocidadePadrao = 4;
        velocidade = velocidadePadrao;
        vidaMaxima = 7;
        vida = vidaMaxima;
        ataque = 7;
        defesa = 0;
        exp = 7;

        areaSolida.x = 3;
        areaSolida.y = 15;
        areaSolida.width = 42;
        areaSolida.height = 21;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        getImagem();
    }
    
    public void getImagem(){
        cima1 = setup("/img/inimigo/bat_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/inimigo/bat_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/inimigo/bat_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/inimigo/bat_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/inimigo/bat_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/inimigo/bat_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/inimigo/bat_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/inimigo/bat_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setAcao(){


        if(pastaAtiva == true){

            //verificarSeParouDePerseguir_ou_nao(painel.jogador, 15,100);

            //procurar a direção para ir
            //para o inimigo seguir o jogador
            //procurarCaminho(getColunaAtual(painel.jogador), getLinhaAtual(painel.jogador));

            //verifique se ele atira um projétil - apenas o vermelho atira pedra
            //verificarSeAtirou_ou_nao(200, 30);
        }
        else{
            //verifique se ele começa a perseguir
            //verificarSeComecouAPerseguir_ou_nao(painel.jogador, 5, 100);

            getDirecaoAleatoria(10);
        }
  
    }

    public void acaoAoDano(){
        contadorDeBloqueioDeAcao = 0;
        //direcao = painel.jogador.direcao;
        //pastaAtiva = true;
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
