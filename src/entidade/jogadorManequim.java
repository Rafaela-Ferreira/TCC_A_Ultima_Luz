package entidade;

import main.PainelDoJogo;

//classe usada para cutscene
public class jogadorManequim extends Entidade{

    public static final String nomeNpc = "Manequim";

    public jogadorManequim(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;
        
        nome = nomeNpc;
        getImagem();
    }

    public void getImagem(){
        if(painel.jogador != null) {
            cima1 = painel.jogador.cima1;
            cima2 = painel.jogador.cima2;
            baixo1 = painel.jogador.baixo1;
            baixo2 = painel.jogador.baixo2;
            esquerda1 = painel.jogador.esquerda1;
            esquerda2 = painel.jogador.esquerda2;
            direita1 = painel.jogador.direita1;
            direita2 = painel.jogador.direita2;
        }
        // cima1 = setup("/res/jogador/boy_up_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // cima2 = setup("/res/jogador/boy_up_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // baixo1 = setup("/res/jogador/boy_down_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // baixo2 = setup("/res/jogador/boy_down_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // esquerda1 = setup("/res/jogador/boy_left_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // esquerda2 = setup("/res/jogador/boy_left_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // direita1 = setup("/res/jogador/boy_right_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // direita2 = setup("/res/jogador/boy_right_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
}
