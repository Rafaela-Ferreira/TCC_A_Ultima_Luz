package entidade;

import java.awt.Rectangle;
import java.util.Random;
import main.PainelDoJogo;

public class NpcOVigiaPartido extends Entidade{
    
    public NpcOVigiaPartido(PainelDoJogo painel){
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
        
        cima1 = setup("/res/npc/oldman_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/oldman_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/oldman_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/oldman_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/oldman_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/oldman_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/oldman_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/oldman_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    //trocar diálogos
    public void setDialogo(){
        dialogo[0][0] = "";
        dialogo[0][1] = "";
        dialogo[0][2] = "";
        dialogo[0][3] = "";

        dialogo[1][0] = "";
        dialogo[1][1] = "";
        dialogo[1][2] = "";

        dialogo[2][0] = "";

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
