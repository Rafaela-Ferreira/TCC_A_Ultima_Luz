package entidade;
import java.awt.Rectangle;
import java.util.Random;

import main.PainelDoJogo;

public class NpcVelho extends Entidade{
    public NpcVelho(PainelDoJogo painel){
        super(painel);

        direcao = "baixo";
        velocidade = 1;

        
        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.x = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;

        setDialogo = -1;

        getImagem();
        setDialogo();
        
    }

    public void getImagem(){
        
        cima1 = setup("/img/npc/oldman_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/npc/oldman_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/npc/oldman_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/npc/oldman_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/npc/oldman_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/npc/oldman_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/npc/oldman_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/npc/oldman_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        dialogo[0][0] = "Olá, rapaz!";
        dialogo[0][1] = "Então você veio a esta ilha para encontrar\n o tesouro?";
        dialogo[0][2] = "Eu costumava ser um grande mago, \nmas agora... estou um pouco velho demais \npara me aventurar.";
        dialogo[0][3] = "Bem, eu te pego de surpresa.";

        dialogo[1][0] = "Se você ficar cansado, descanse na água!";
        dialogo[1][1] = "No entanto, os monstros reaparecem se você descansar.\nNão sei por que, mas é assim que funciona.";
        dialogo[1][2] = "De qualquer forma, não se esforce demais.";

        dialogo[2][0] = "Eu queria saber como abrir aquela porta...";

    }

    public void setAcao(){

        if(pastaAtiva == true){
            //para o NPC ter um objetivo
            //int metaColuna = 10;
            //int metaLinha = 9;

            //para o NPC seguir o jogador
            int metaColuna = (painel.jogador.mundoX + painel.jogador.areaSolida.x) / painel.tamanhoDoTile;
            int metaLinha = (painel.jogador.mundoY + painel.jogador.areaSolida.y) / painel.tamanhoDoTile;

            procurarCaminho(metaColuna, metaLinha);

        }else{
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
        
    }

    public void falar(){

        faceJogador();
        iniciarDialogo(this, setDialogo);

        setDialogo++;

        if(dialogo[setDialogo][0] == null){
            setDialogo--;
        }

        //segue o objetivo: jogador
        pastaAtiva = true;


        /* exibir dialogo caso a vida do jogador estiver baixa
        if(painel.jogador.vida < painel.jogador.vidaMaxima/3){
            setDialogo = 1;
        }
        */
    }

    
}
