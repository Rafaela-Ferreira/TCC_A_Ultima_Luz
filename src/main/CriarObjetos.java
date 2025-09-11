package main;

import entidade.NpcVelho;
import main.inimigo.lodoVerde;
import objeto.ObjChave;
import objeto.ObjCoracao;
import objeto.ObjEscudoAzul;
import objeto.ObjMachado;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;
import objeto.ObjPocaoVermelha;
import tile.blocosInterativos.ArvoreSeca;


public class CriarObjetos {
    PainelDoJogo painel;

    public CriarObjetos(PainelDoJogo painel) {
        this.painel = painel;
    }

    public void setarObjetos() {
        //como adicionar objetos ao array de objetos
        int i = 0;
        painel.Obj[i] = new ObjMoedaBronze(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*25;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*23;
        i++;

        painel.Obj[i] = new ObjMoedaBronze(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*21;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*19;
        i++;

        painel.Obj[i] = new ObjChave(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[i] = new ObjMachado(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*33;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[i] = new ObjEscudoAzul(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*16;
        i++;
        
        painel.Obj[i] = new ObjPocaoVermelha(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*22;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*27;
        i++;

        painel.Obj[i] = new ObjCoracao(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*22;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*29;
        i++;
        
        painel.Obj[i] = new ObjMana(painel);
        painel.Obj[i].mundoX = painel.tamanhoDoTile*22;
        painel.Obj[i].mundoY = painel.tamanhoDoTile*31;
        i++;
    }

    public void setBlocosInterativos(){
        int i = 0;

        painel.blocosI[i] = new ArvoreSeca(painel, 27,12); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 28,12); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 29,12); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 30,12); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 31,12); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 32,12); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 33,12); i++;

        painel.blocosI[i] = new ArvoreSeca(painel, 25,16); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 34,21); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 34,22); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 20,20); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 20,21); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 20,22); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 22,24); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 23,24); i++;
        painel.blocosI[i] = new ArvoreSeca(painel, 24,24); i++;
    }

    public void setNpc(){
        //instacia do NPC
        painel.npc[0] = new NpcVelho(painel);
        painel.npc[0].mundoX = painel.tamanhoDoTile*21;
        painel.npc[0].mundoY = painel.tamanhoDoTile*21;

        /* 
        painel.npc[1] = new NpcVelho(painel);
        painel.npc[1].mundoX = painel.tamanhoDoTile*9;
        painel.npc[1].mundoY = painel.tamanhoDoTile*10;*/

        
    }

    public void setInimigos(){

        int i = 0;
        painel.inimigo[i] = new lodoVerde(painel);
        painel.inimigo[i].mundoX = painel.tamanhoDoTile*21;
        painel.inimigo[i].mundoY = painel.tamanhoDoTile*38;
        i++;

        painel.inimigo[i] = new lodoVerde(painel);
        painel.inimigo[i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[i] = new lodoVerde(painel);
        painel.inimigo[i].mundoX = painel.tamanhoDoTile*34;
        painel.inimigo[i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[i] = new lodoVerde(painel);
        painel.inimigo[i].mundoX = painel.tamanhoDoTile*38;
        painel.inimigo[i].mundoY = painel.tamanhoDoTile*42;
        i++;


        /*
        painel.inimigo[2] = new lodoVerde(painel);
        painel.inimigo[2].mundoX = painel.tamanhoDoTile*11;
        painel.inimigo[2].mundoY = painel.tamanhoDoTile*10;

        painel.inimigo[3] = new lodoVerde(painel);
        painel.inimigo[3].mundoX = painel.tamanhoDoTile*11;
        painel.inimigo[3].mundoY = painel.tamanhoDoTile*11;*/
    }
}
