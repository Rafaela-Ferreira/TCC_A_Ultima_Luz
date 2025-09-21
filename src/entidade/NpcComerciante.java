package entidade;

import main.PainelDoJogo;
import objeto.ObjChave;
import objeto.ObjEscudoAzul;
import objeto.ObjEscudoMadeira;
import objeto.ObjEspadaNormal;
import objeto.ObjMachado;
import objeto.ObjPocaoVermelha;

public class NpcComerciante extends Entidade{

    public NpcComerciante(PainelDoJogo painel){
        super(painel);

        direcao = "baixo";
        velocidade = 1;

        getImagem();
        setDialogo();
        setItens();
    }

    public void getImagem(){
        
        cima1 = setup("/img/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        dialogo[0] = "He he, então você me encontrou.\nTenho algumas coisas boas.\nVocê quer trocar?";

    }
    public void setItens(){
        inventario.add(new ObjPocaoVermelha(painel));
        inventario.add(new ObjChave(painel));
        inventario.add(new ObjEspadaNormal(painel));
        inventario.add(new ObjMachado(painel));
        inventario.add(new ObjEscudoMadeira(painel));
        inventario.add(new ObjEscudoAzul(painel));

    }
    public void falar(){
        super.falar();
        painel.estadoDoJogo = painel.trocaDeEstado;
        painel.interfaceDoUsuario.npc = this;
    }
}
