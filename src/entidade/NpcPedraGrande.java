package entidade;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.PainelDoJogo;
import objeto.ObjPortaDeFerro;
import tile.blocosInterativos.BlocosInterativos;
import tile.blocosInterativos.PlacaDeMetal;

public class NpcPedraGrande extends Entidade{

    public static final String nomeNpc = "Pedra Grande";

    public NpcPedraGrande(PainelDoJogo painel){
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
        dialogo[0][0] = "É uma rocha gigante";
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

    public void mover (String direcao){
        this.direcao = direcao;

        verificarColisao();

        if(colisaoComBloco == false){
            switch (direcao) {
                case "cima": mundoY -= velocidade; break;
                case "baixo": mundoY += velocidade; break;
                case "esquerda": mundoX -= velocidade; break;
                case "direita": mundoX += velocidade; break;
            }
        }

        detectarPlaca();
    }

    public void detectarPlaca(){
        
        ArrayList<BlocosInterativos> listaPlacaDeMetal = new ArrayList<>();
        ArrayList<Entidade> listaPedraGrande = new ArrayList<>();

        //criar a lista de placas
        for(int i = 0; i < painel.blocosI[1].length; i++){

            if(painel.blocosI[painel.mapaAtual][i] != null &&
                painel.blocosI[painel.mapaAtual][i].nome != null &&
                painel.blocosI[painel.mapaAtual][i].nome.equals(PlacaDeMetal.nomeBlocoI)){
                    listaPlacaDeMetal.add(painel.blocosI[painel.mapaAtual][i]);
            }
        }


        //criar a lista de pedra grande
        for(int i = 0; i < painel.npc[1].length; i++){

            if(painel.npc[painel.mapaAtual][i] != null &&
                painel.npc[painel.mapaAtual][i].nome.equals(NpcPedraGrande.nomeNpc)){
                    listaPedraGrande.add(painel.npc[painel.mapaAtual][i]);
            }
        }

        int contador = 0;
        //secan the placa list

        for(int i = 0; i < listaPlacaDeMetal.size(); i++){

            int Xdistancia = Math.abs(mundoX - listaPlacaDeMetal.get(i).mundoX);
            int Ydistancia = Math.abs(mundoY - listaPlacaDeMetal.get(i).mundoY);
            int distancia = Math.max(Xdistancia, Ydistancia);

            if(distancia < 8){

                if(entidadeVinculada == null){
                    entidadeVinculada = listaPlacaDeMetal.get(i);
                    painel.iniciarEfeitoSonoro(3);
                }
            }
            else{

                if(entidadeVinculada == listaPlacaDeMetal.get(i)){
                    entidadeVinculada = null;
                }
                
            }
        }
        //scan the cock list
        for(int i = 0; i < listaPedraGrande.size(); i++){

            //count the rock on the place
            if(listaPedraGrande.get(i).entidadeVinculada != null){
                contador++;
            }
        }

        //if all the rocks are on the plates, the iron door opens

        if(contador == listaPedraGrande.size()){

            for(int i = 0; i < painel.Obj[1].length; i++){

                if(painel.Obj[painel.mapaAtual][i] != null && painel.Obj[painel.mapaAtual][i].nome.equals(ObjPortaDeFerro.objNome)){

                    painel.Obj[painel.mapaAtual][i] = null;
                    painel.iniciarEfeitoSonoro(21);
                }
            }
        }
    }
    
}
