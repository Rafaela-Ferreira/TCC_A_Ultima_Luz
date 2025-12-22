package entidade;

import main.PainelDoJogo;
import objeto.ObjChave;
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
        
        cima1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        dialogo[0][0] = " "; //ajustar diálogos
        
        dialogo[1][0] = "Volte sempre, hehe!";

        dialogo[2][0] = "Você precisa de mais moedas para comprá-los!";

        dialogo[3][0] = "Você não pode carregar mais nada!"; 

        dialogo[4][0] = "Você não pode vender um item equipado!";

    }

    //deve vender magias (Magia não está implementada ainda)
    public void setItens(){
        inventario.add(new ObjPocaoVermelha(painel));
        inventario.add(new ObjChave(painel));
    }
    
    public void falar(){

        faceJogador();
        painel.estadoDoJogo = painel.trocaDeEstado;
        painel.interfaceDoUsuario.npc = this;
    }
}

