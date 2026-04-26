package entidade;

import java.awt.Rectangle;
import main.PainelDoJogo;

public class PortalViagemRapida extends Entidade {

    public static final String nomeNpc = "Portal";

    public PortalViagemRapida(PainelDoJogo painel) {
        super(painel);

        nome = nomeNpc;
        tipo = tipoPortal;
        direcao = "baixo";
        velocidade = 0; // portal não se move

        areaSolida = new Rectangle();
        areaSolida.x = 6;
        areaSolida.y = 6;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 44;
        areaSolida.height = 44;

        getImagem();
    }
    

    public void getImagem(){
        cima1 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/objeto/placa", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }


    public void falar(){
        faceJogador();
        painel.interfaceDoUsuario.npc = this;
        painel.interfaceDoUsuario.numeroDoComando = 0;
        painel.estadoDoJogo = painel.estadoViagemRapida;
        painel.iniciarEfeitoSonoro(10);

        //iniciarDialogo(this, 0);

      


       
    }
}

    

    

    