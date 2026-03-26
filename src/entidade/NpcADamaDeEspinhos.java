package entidade;

import main.PainelDoJogo;
import objeto.ObjPocaoAzul;
import objeto.ObjPocaoVermelha;


public class NpcADamaDeEspinhos extends Entidade{

    public NpcADamaDeEspinhos(PainelDoJogo painel){
        super(painel);

        direcao = "baixo";
        velocidade = 1;

        getImagem();
        setDialogo();
        setItens();
    }

    public void getImagem(){
        
        cima1 = setup("/res/npc/damaDeEspinho_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/damaDeEspinho_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/damaDeEspinho_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/damaDeEspinho_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/damaDeEspinho_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/damaDeEspinho_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/damaDeEspinho_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/damaDeEspinho_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        dialogo[0][0] = "Ah... um viajante.\nMinhas poções não curam apenas feridas...\nalgumas despertam verdades que você talvez não queira ver.";
        dialogo[0][1] = "Cada frasco aqui foi cultivado com espinhos e paciência.\nDeseja aliviar sua dor... ou alimentar sua coragem?";

        dialogo[1][0] = "Volte sempre...\nA dor nunca deixa de visitar, hehe.";

        dialogo[2][0] = "Hm... suas almas são tão escassas quanto prudência.\nVolte quando tiver algo de valor.";

        dialogo[3][0] = "Cuidado, viajante.\nAté mesmo bênçãos se tornam fardos quando carregadas em excesso."; 

        dialogo[4][0] ="Tirar isso de você agora?\nNão... algumas escolhas precisam ser suportadas até o fim.";
        dialogo[4][1] = "Você não pode vender um item equipado!";

    }


    public void setItens(){
        inventario.add(new ObjPocaoAzul(painel));
        inventario.add(new ObjPocaoAzul(painel));
        inventario.add(new ObjPocaoVermelha(painel));
        inventario.add(new ObjPocaoVermelha(painel));
        
    }
    
    public void falar(){

        faceJogador();
        painel.estadoDoJogo = painel.trocaDeEstado;
        painel.interfaceDoUsuario.npc = this;
    }
}

