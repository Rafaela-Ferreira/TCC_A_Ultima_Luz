package entidade;

import main.PainelDoJogo;
import objeto.ObjEspadaNormal;
import objeto.ObjMachado;

public class NpcOCarrascoSemRosto extends Entidade{

    public NpcOCarrascoSemRosto(PainelDoJogo painel){
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

        dialogo[2][0] = "Você precisa de mais almas para comprá-los!";

        dialogo[3][0] = "Você não pode carregar mais nada!"; 

        dialogo[4][0] = "Você não pode vender um item equipado!";

    }
    public void setItens(){

        //deve vender somente espadas/armas
        inventario.add(new ObjEspadaNormal(painel));
        inventario.add(new ObjMachado(painel));

    }
    public void falar(){

        faceJogador();
        painel.estadoDoJogo = painel.trocaDeEstado;
        painel.interfaceDoUsuario.npc = this;
    }
}
