package entidade;
import java.util.Random;

import main.PainelDoJogo;

public class NpcVelho extends Entidade{
    public NpcVelho(PainelDoJogo painel){
        super(painel);

        direcao = "baixo";
        velocidade = 1;

        getImagem();
        setDialogo();
        //caso queira definir uma area solida, pode definir aqui, como na classe jogador.
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
        dialogo[0] = "Olá, rapaz!";
        dialogo[1] = "Então você veio a esta ilha para encontrar\n o tesouro?";
        dialogo[2] = "Eu costumava ser um grande mago, \nmas agora... estou um pouco velho demais \npara me aventurar.";
        dialogo[3] = "Bem, eu te pego de surpresa.";

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

    public void falar(){
        //usado para personalizar as falas do npc
       super.falar();
    }

    
}
