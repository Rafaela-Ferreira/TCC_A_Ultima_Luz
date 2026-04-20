package entidade;

import java.awt.Rectangle;
import java.util.Random;
import main.PainelDoJogo;

public class NpcOVigiaPartido extends Entidade{
    
    public NpcOVigiaPartido(PainelDoJogo painel){
        super(painel);

        direcao = "baixo";
        velocidade = 1;

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

    public void setDialogo(){
        dialogo[0][0] = "...Você escuta?\nOs ecos… eles não param.\nCada escolha… reverbera.";
        dialogo[0][1] = "Eu já estive onde você está.\nEntre caminhos… incapaz de dar o primeiro passo.";
        dialogo[0][2] = "Não existe decisão sem perda.\nQuem tenta evitar isso… se parte.";
        dialogo[0][3] = "Escolha... mesmo que doa.\nO silêncio das não-escolhas é pior.";

        dialogo[1][0] = "Os ecos mudam… conforme você avança.\nOu… talvez seja você quem muda.";
        dialogo[1][1] = "Você ainda hesita?\nCuidado... o tempo não espera por certezas.";
        dialogo[1][2] = "Não procure respostas em mim.\nEu sou apenas o que restou da dúvida.";

        dialogo[2][0] = "...Se eu tivesse escolhido...\nTalvez não estivesse... assim.";

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
