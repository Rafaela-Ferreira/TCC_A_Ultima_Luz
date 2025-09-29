package main.inimigo;

import java.util.Random;

import entidade.Entidade;
import main.PainelDoJogo;
import objeto.ObjCoracao;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;

public class InimigoOrc extends Entidade{

    PainelDoJogo painel;

    public InimigoOrc(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;
        
        tipo = tipoInimigo;
        nome ="Orc";
        velocidadePadrao = 1;
        velocidade = velocidadePadrao;
        vidaMaxima = 10;
        vida = vidaMaxima;
        ataque = 1;
        defesa = 2;
        exp = 10;
        poderDoEmpurrao = 5;


        areaSolida.x = 4;
        areaSolida.y = 4;
        areaSolida.width = 40;
        areaSolida.height = 44;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaAtaque.width = 48;
        areaAtaque.height = 48;
        direcaoDoMovimento1 = 40;
        direcaoDoMovimento2 = 80;

        getImagem();
        getImagemAtaque();
    }
    
    public void getImagem(){
        cima1 = setup("/img/inimigo/orc_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/inimigo/orc_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/inimigo/orc_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/inimigo/orc_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/inimigo/orc_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/inimigo/orc_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/inimigo/orc_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/inimigo/orc_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void getImagemAtaque(){
        ataqueCima1 = setup("/img/inimigo/orc_attack_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueCima2 = setup("/img/inimigo/orc_attack_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueBaixo1 = setup("/img/inimigo/orc_attack_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueBaixo2 = setup("/img/inimigo/orc_attack_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueEsquerda1 = setup("/img/inimigo/orc_attack_left_1", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        ataqueEsquerda2 = setup("/img/inimigo/orc_attack_left_2", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        ataqueDireita1 = setup("/img/inimigo/orc_attack_right_1", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        ataqueDireita2 = setup("/img/inimigo/orc_attack_right_2", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
    }

    public void setAcao(){


        if(pastaAtiva == true){

            verificarSeParouDePerseguir_ou_nao(painel.jogador, 15,100);

            //procurar a direção para ir
            //para o inimigo seguir o jogador
            procurarCaminho(getColunaAtual(painel.jogador), getLinhaAtual(painel.jogador));

        }
        else{
            //verifique se ele começa a perseguir
            verificarSeComecouAPerseguir_ou_nao(painel.jogador, 5, 100);

            getDirecaoAleatoria(120);
        }

        //verifique se ele atacou
        if(atacar == false){
            verificarSeAtacou_ou_nao(30, painel.tamanhoDoTile*4, painel.tamanhoDoTile);
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

