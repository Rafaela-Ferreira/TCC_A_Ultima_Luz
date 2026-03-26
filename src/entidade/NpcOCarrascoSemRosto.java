package entidade;

import main.PainelDoJogo;
import objeto.ObjEspadaNormal;
import objeto.ObjMachado;
import objeto.ObjPicareta;

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
        
        cima1 = setup("/res/npc/OcarrascoSemRosto_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/OcarrascoSemRosto_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/OcarrascoSemRosto_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/OcarrascoSemRosto_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/OcarrascoSemRosto_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/OcarrascoSemRosto_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/OcarrascoSemRosto_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/OcarrascoSemRosto_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

   
    public void setDialogo(){
        dialogo[0][0] = "...\nEu já segurei lâminas como estas antes.\nNão para lutar… mas para cumprir ordens.";
        dialogo[0][1] = "Cada arma aqui conhece o gosto do fim.\nEscolha a sua... e arque com o que ela exige.";
        dialogo[0][2] = "Não tenho rosto… porque deixei de precisar de um.\nA culpa apaga mais que o tempo.";
        
        dialogo[1][0] = "Volte, se ainda tiver forças para carregar outra escolha.";

        dialogo[2][0] = "Sem almas... sem aço.\nAté o fim tem seu preço.";

        dialogo[3][0] = "Você já carrega peso demais.\nMais uma arma... só vai afundar você."; 

        dialogo[4][0] = "Uma arma empunhada é um juramento.\nQuebrá-lo não é tão simples.";
        dialogo[4][1] = "Você não pode vender um item equipado!";

    }
    public void setItens(){

        //deve vender somente armas
        inventario.add(new ObjEspadaNormal(painel));
        inventario.add(new ObjMachado(painel));
        inventario.add(new ObjPicareta(painel));

    }
    public void falar(){

        faceJogador();
        painel.estadoDoJogo = painel.trocaDeEstado;
        painel.interfaceDoUsuario.npc = this;
    }
}
