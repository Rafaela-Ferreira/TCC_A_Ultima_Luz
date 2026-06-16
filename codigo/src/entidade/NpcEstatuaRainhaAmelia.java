package entidade;

import java.awt.Rectangle;
import main.PainelDoJogo;

public class NpcEstatuaRainhaAmelia extends Entidade{

    public static final String nomeNpc = "Estatua Rainha Amelia";

    public NpcEstatuaRainhaAmelia(PainelDoJogo painel){
        super(painel);

        nome = nomeNpc;
        direcao = "baixo";
        velocidade = 0;


       

        getImagem();
        setDialogo();
        
    }

    public void getImagem(){
        cima1 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/NovicePyromancer1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
    }

    public void setDialogo(){
        dialogo[0][0] = "Estatua da Rainha Amelia.";
        dialogo[0][1] = "“Mesmo na queda... a aurora deve florescer.”";
        dialogo[0][2] = "Fragmentos da chama ainda resistem.\nEspalhados… esquecidos... aguardando.";
        dialogo[0][3] = "Reúna o que foi perdido.\nE talvez... o ciclo possa ser quebrado.";
    }

    public void falar(){

        faceJogador();
        iniciarDialogo(this, setDialogo);

        setDialogo++;

        if(dialogo[setDialogo][0] == null){
            setDialogo--;
        }

    }

}
