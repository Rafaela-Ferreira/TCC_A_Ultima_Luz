package entidade;

import main.PainelDoJogo;

//classe usada para cutscene
public class jogadorManequim extends Entidade{

    public static final String nomeNpc = "Manequim";

    public jogadorManequim(PainelDoJogo painel) {
        super(painel);
        
        nome = nomeNpc;
        getImagem();
    }

    public void getImagem(){
        
        cima1 = setup("/img/spritesjogador/boy_up_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/spritesjogador/boy_up_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/spritesjogador/boy_down_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/spritesjogador/boy_down_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/spritesjogador/boy_left_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/spritesjogador/boy_left_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/spritesjogador/boy_right_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/spritesjogador/boy_right_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
}
