package main.inimigo.chefao;

import java.util.Random;

import dados.Progresso;
import entidade.Entidade;
import main.PainelDoJogo;
import objeto.ObjCoracao;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;
import objeto.ObjPortaDeFerro;

public class SenhorEsqueleto extends Entidade{

    PainelDoJogo painel;
    public static final String nomeBoss = "Senhor Esqueleto";

    public SenhorEsqueleto(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;
        
        tipo = tipoInimigo;
        chefe = true;
        nome = nomeBoss;
        velocidadePadrao = 1;
        velocidade = velocidadePadrao;
        vidaMaxima = 50;
        vida = vidaMaxima;
        ataque = 10;
        defesa = 2;
        exp = 50;
        poderDoEmpurrao = 5;
        dormir = true;


        int tamanho = painel.tamanhoDoTile*5;
        areaSolida.x = 48;
        areaSolida.y = 48;
        areaSolida.width = tamanho - 48*2;
        areaSolida.height = tamanho - 48;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaAtaque.width = 170;
        areaAtaque.height = 170;
        direcaoDoMovimento1 = 25;
        direcaoDoMovimento2 = 50;

        getImagem();
        getImagemAtaque();
        setDialogo();
    }
    
    //32x32 - 5x maior que o jogador - 240x240
    public void getImagem(){

        int i = 5;
        if(furia == false){
            cima1 = setup("/img/inimigo/skeletonlord_up_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            cima2 = setup("/img/inimigo/skeletonlord_up_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            baixo1 = setup("/img/inimigo/skeletonlord_down_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            baixo2 = setup("/img/inimigo/skeletonlord_down_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            esquerda1 = setup("/img/inimigo/skeletonlord_left_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            esquerda2 = setup("/img/inimigo/skeletonlord_left_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            direita1 = setup("/img/inimigo/skeletonlord_right_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            direita2 = setup("/img/inimigo/skeletonlord_right_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        }

        if(furia == true){
            cima1 = setup("/img/inimigo/skeletonlord_phase2_up_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            cima2 = setup("/img/inimigo/skeletonlord_phase2_up_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            baixo1 = setup("/img/inimigo/skeletonlord_phase2_down_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            baixo2 = setup("/img/inimigo/skeletonlord_phase2_down_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            esquerda1 = setup("/img/inimigo/skeletonlord_phase2_left_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            esquerda2 = setup("/img/inimigo/skeletonlord_phase2_left_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            direita1 = setup("/img/inimigo/skeletonlord_phase2_right_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
            direita2 = setup("/img/inimigo/skeletonlord_phase2_right_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        }



        
    }

    public void getImagemAtaque(){

        int i = 5;

        if(furia == false){
            ataqueCima1 = setup("/img/inimigo/skeletonlord_attack_up_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);
            ataqueCima2 = setup("/img/inimigo/skeletonlord_attack_up_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);
            ataqueBaixo1 = setup("/img/inimigo/skeletonlord_attack_down_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);
            ataqueBaixo2 = setup("/img/inimigo/skeletonlord_attack_down_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);

            ataqueEsquerda1 = setup("/img/inimigo/skeletonlord_attack_left_1", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
            ataqueEsquerda2 = setup("/img/inimigo/skeletonlord_attack_left_2", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
            ataqueDireita1 = setup("/img/inimigo/skeletonlord_attack_right_1", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
            ataqueDireita2 = setup("/img/inimigo/skeletonlord_attack_right_2", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
        }

        if(furia == true){
            ataqueCima1 = setup("/img/inimigo/skeletonlord_phase2_attack_up_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);
            ataqueCima2 = setup("/img/inimigo/skeletonlord_phase2_attack_up_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);
            ataqueBaixo1 = setup("/img/inimigo/skeletonlord_phase2_attack_down_1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);
            ataqueBaixo2 = setup("/img/inimigo/skeletonlord_phase2_attack_down_2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i*2);

            ataqueEsquerda1 = setup("/img/inimigo/skeletonlord_phase2_attack_left_1", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
            ataqueEsquerda2 = setup("/img/inimigo/skeletonlord_phase2_attack_left_2", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
            ataqueDireita1 = setup("/img/inimigo/skeletonlord_phase2_attack_right_1", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
            ataqueDireita2 = setup("/img/inimigo/skeletonlord_phase2_attack_right_2", painel.tamanhoDoTile*i*2, painel.tamanhoDoTile*i);
        }
        
    }

    public void setDialogo(){
        dialogo[0][0] = "Ninguém pode roubar meu tesouro!";
        dialogo[0][1] = "Você vai morrer aqui!";
        dialogo[0][2] = "BEM-VINDO À SUA DESTRUIÇÃO!";
    }
    
    public void setAcao(){

        if(furia == false && vida < vidaMaxima/2){
            furia = true;
            getImagem();
            getImagemAtaque();
            velocidadePadrao++;
            velocidade= velocidadePadrao;
            ataque *= 2;
        }

        if(getDistaciaDoBloco(painel.jogador) < 10){
            moverEmDirecaoAoJogador(60);
        }
        else{
            getDirecaoAleatoria(120);
        }
        //verifique se ele atacou
        if(atacar == false){
            verificarSeAtacou_ou_nao(60, painel.tamanhoDoTile*7, painel.tamanhoDoTile*5);
        }
  
    }

    public void acaoAoDano(){
        contadorDeBloqueioDeAcao = 0;

    }


    public void verificarDrop(){

        painel.batalhaComChefeAtiva = false;
        Progresso.senhorEsqueletoPadrao = true;

        //restore the previous music
        painel.pararMusica();
        painel.iniciarMusica(19);

        //remove the iron doors
        for(int i = 0; i < painel.Obj[1].length; i++){
            if(painel.Obj[painel.mapaAtual][i] != null && painel.Obj[painel.mapaAtual][i].nome.equals(ObjPortaDeFerro.objNome)){
                painel.iniciarEfeitoSonoro(21);
                painel.Obj[painel.mapaAtual][i] = null;
            }
        }

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
