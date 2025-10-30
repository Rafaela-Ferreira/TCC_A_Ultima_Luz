package entidade;

import main.PainelDoJogo;
import java.awt.Rectangle;

public class NpcEstatuaRainhaAmelia extends Entidade{

    public static final String nomeNpc = "Estatua Rainha Amelia";

    public NpcEstatuaRainhaAmelia(PainelDoJogo painel){
        super(painel);

        nome = nomeNpc;
        direcao = "baixo";
        velocidade = 4;


        areaSolida = new Rectangle();
        areaSolida.x = 2;
        areaSolida.x = 6;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 44;
        areaSolida.height = 40;

        setDialogo = -1;

        getImagem();
        setDialogo();
        
    }

    public void getImagem(){
        
        cima1 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/npc/bigrock", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        dialogo[0][0] = "Estatua da Rainha Amelia.";
        dialogo[0][1] = "Reconecte as chamas perdidas da aurora.";
        dialogo[0][2] = "E restaure o seu poder.";
    }

    public void setAcao(){ }

    public void atualizar(){ }

    public void falar(){

        faceJogador();
        iniciarDialogo(this, setDialogo);

        setDialogo++;

        if(dialogo[setDialogo][0] == null){
            setDialogo--;
        }

    }

}
