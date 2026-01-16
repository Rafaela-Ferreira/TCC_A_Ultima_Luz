package main;

import dados.Progresso;
import entidade.NpcADamaDeEspinhos;
import entidade.NpcEstatuaRainhaAmelia;
import entidade.NpcOCarrascoSemRosto;
import entidade.NpcOVigiaPartido;
import entidade.NpcPortalViagemRapida;
import entidade.NpcSacerdotizaCega;
import main.inimigo.LodoVerde;
import main.inimigo.chefao.SenhorEsqueleto;
import main.inimigo.chefao.SenhorEsqueleto2;
import main.inimigo.chefao.SenhorEsqueleto3;
import objeto.ObjBau;
import objeto.ObjChave;
import objeto.ObjDiamente;
import objeto.ObjPocaoAzul;
import objeto.ObjPorta;
import objeto.ObjPortaDeFerro;
import tile.blocosInterativos.ArvoreSeca;
import tile.blocosInterativos.Fogueira;
import tile.blocosInterativos.MensagemNoChao;
import tile.blocosInterativos.PlacaDeMetal;

public class CriarObjetos {
    PainelDoJogo painel;

    public CriarObjetos(PainelDoJogo painel) {
        this.painel = painel;
    }

    public void setarObjetos() {

        int numeroMapa = 0;
        int i = 0;

        
        //criar objetos no mapa 0
        painel.Obj[numeroMapa][i] = new ObjPorta(painel); 
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*86;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*62;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjChave(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*73;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*63;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoAzul(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*80;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
        i++;

        painel.Obj[numeroMapa][i] = new ObjChave(painel); 
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*42;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*30;
        i++;

        
        
        
        numeroMapa = 1;
        i = 0;
        //criar objetos no mapa 1
        

        //condição para que não crie o boss novamente apos derrota-ló
        if(Progresso.senhorEsqueletoPadrao == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }

        numeroMapa = 2;
        i = 0;
        //criar objetos no mapa 2
        


        numeroMapa = 3;
        i = 0;
        //criar objetos no mapa 3



        numeroMapa = 4;
        i = 0;
        //criar objetos no mapa 4
        
        //condição para que não crie o boss novamente apos derrota-ló
        if(Progresso.senhorEsqueletoPadrao2 == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }


        numeroMapa = 5;
        i = 0;
        //criar objetos no mapa 5


        numeroMapa = 6;
        i = 0;
        //criar objetos no mapa 6


        numeroMapa = 7;
        i = 0;
        //criar objetos no mapa 7
        
        //condição para que não crie o boss novamente apos derrota-ló
        if(Progresso.senhorEsqueletoPadrao3 == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }

        painel.Obj[numeroMapa][i] = new ObjDiamente(painel); // ATENÇÃO!! Este objeto que dispara o final do jogo!
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;


        numeroMapa = 8;
        i = 0;
        //criar objetos no mapa 8



        numeroMapa = 9;
        i = 0;
        //criar objetos no mapa 9



        numeroMapa = 10;
        i = 0;
        //criar objetos no mapa 10

        /*MAPA 9
        numeroMapa = 9;
        i = 0;
        painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*38;
        i++;

        //condição para que não crie o boss novamente apos derrota-ló
        if(Progresso.senhorEsqueletoPadrao == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }
        //criar no mapa 9
        painel.Obj[numeroMapa][i] = new ObjDiamente(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;
        */
        
        /* 
        int numeroMapa = 0;
        int i = 0;
        painel.Obj[numeroMapa][i] = new ObjTocha(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*8;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*10;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlma(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*10;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
        i++;


        painel.Obj[numeroMapa][i] = new ObjFragmentoCarmesim(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
        i++;

        painel.Obj[numeroMapa][i] = new ObjFragmentoOnirico(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        painel.Obj[numeroMapa][i] = new ObjFragmentoFaminto(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*17;
        i++;

        painel.Obj[numeroMapa][i] = new ObjFragmentoFlamejante(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*18;
        i++;

        painel.Obj[numeroMapa][i] = new ObjFragmentoSombrio(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*19;
        i++;

        painel.Obj[numeroMapa][i] = new ObjFragmentoCoracaoDaLamina(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.Obj[numeroMapa][i] = new ObjFragmentoOnirico(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;
        */

        /* 
        //como adicionar objetos ao array de objetos
        int numeroMapa = 0;
        int i = 0;
        painel.Obj[numeroMapa][i] = new ObjMoedaBronze(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*23;
        i++;

        painel.Obj[numeroMapa][i] = new ObjMoedaBronze(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*19;
        i++;

        painel.Obj[numeroMapa][i] = new ObjChave(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[numeroMapa][i] = new ObjMachado(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*33;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[numeroMapa][i] = new ObjEscudoAzul(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;
        
        painel.Obj[numeroMapa][i] = new ObjPocaoVermelha(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*22;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*27;
        i++;

        painel.Obj[numeroMapa][i] = new ObjCoracao(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*22;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*29;
        i++;
        
        painel.Obj[numeroMapa][i] = new ObjMana(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*22;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*31;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*12;
        i++;

        //saquear bau - esse bau dropa uma chave
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjChave(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*30;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*29;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjBarraca(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*13;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPocaoVermelha(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPocaoVermelha(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*17;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[numeroMapa][i] = new ObjLanterna(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*18;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBarraca(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*19;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        

        //Mapa: masmorra
        numeroMapa = 2;
        i = 0;
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPicareta(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*41;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*13;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*34;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*18;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*23;
        i++;

        //Mapa: masmorra - arena do chefe
        numeroMapa = 3;
        i = 0;

        //condição para não perier que crie o boss novamente apos derrota-ló
        if(Progresso.senhorEsqueletoPadrao == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }

        painel.Obj[numeroMapa][i] = new ObjDiamente(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;
        
        */
    }

    public void setBlocosInterativos(){


        int numeroMapa = 0;
        int i = 0;

        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 12,15, "Use W A S D para se mover."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 18,23, "Use F para atacar à distância."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 13,30, "Use C para abrir o inventário."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 42,14, "Use M para abrir o mapa."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 38,26, "Use X para visualizar o minimapa."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 26,36, "ESC para opções."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 12,23, "A morte não é o fim."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 67,65, "Precione ENTER para atacar."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 68,70, "Use ESPAÇO para se proteger."); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 11,48, "Tente bater!"); i++;

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 15,12); i++;

        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 10,49); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 10,50); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 10,51); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 10,52); i++;

        numeroMapa = 1;
        i = 0;


        numeroMapa = 2;
        i = 0;


        numeroMapa = 3;
        i = 0;


        numeroMapa = 4;
        i = 0;
        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 25,39); i++;


        numeroMapa = 5;
        i = 0;


        numeroMapa = 6;
        i = 0;


        numeroMapa = 7;
        i = 0;


        numeroMapa = 8;
        i = 0;

        numeroMapa = 9;
        i = 0;
        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 25,39); i++;


        numeroMapa = 10;
        i = 0;

        //painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,9); i++;
        //painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,10); i++;
        //painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,11); i++;
       // painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,12); i++;


        //agua tranparente - retomar depois - fazer os blocos de terra novos, sem colisão.
       // painel.blocosI[numeroMapa][i] = new Agua_01(painel, 30,15); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_02(painel, 31,15); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_02(painel, 32,15); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_02(painel, 33,15); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_03(painel, 34,15); i++;
        
       // painel.blocosI[numeroMapa][i] = new Agua_04(painel, 30,16); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_00(painel, 31,16); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_00(painel, 32,16); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_00(painel, 33,16); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_05(painel, 34,16); i++;
        
       // painel.blocosI[numeroMapa][i] = new Agua_06(painel, 30,17); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_07(painel, 31,17); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_07(painel, 32,17); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_07(painel, 33,17); i++;
       // painel.blocosI[numeroMapa][i] = new Agua_08(painel, 34,17); i++;
        
        /* 
        int numeroMapa = 0;
        int i = 0;

        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 27,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 28,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 29,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 30,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 31,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 32,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 33,12); i++;

        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 25,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 34,21); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 34,22); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 22,24); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 23,24); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca(painel, 24,24); i++;


        //Mapa: masmorra
        numeroMapa = 2;
        i = 0;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,30); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 17,31); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 17,32); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 17,34); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,34); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,33); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,22); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,24); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 38,18); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 38,19); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 38,20); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 38,21); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,13); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 18,14); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 22,28); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 30,28); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 32,28); i++;

        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 20,22); i++;
        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 8, 17); i++;
        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 39,31); i++;

        */
    }

    public void setNpc(){

        int numeroMapa = 0;
        int i = 0;
        //criar npc no mapa 0 
        
        painel.npc[numeroMapa][i] = new NpcPortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile * 25;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile * 30;


        

        /*---mapa 1-- */
        numeroMapa = 1;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcEstatuaRainhaAmelia(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;


        numeroMapa = 2;
        i = 0;
        //criar npc no mapa 2
        painel.npc[numeroMapa][i] = new NpcSacerdotizaCega(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
        i++;

        painel.npc[numeroMapa][i] = new NpcPortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*32;
        i++;


        numeroMapa = 3;
        i = 0;
        //criar npc no mapa 3

        numeroMapa = 4;
        i = 0;
        //criar npc no mapa 4


        numeroMapa = 5;
        i = 0;
        //criar npc no mapa 5


        numeroMapa = 6;
        i = 0;
        //criar npc no mapa 6
        painel.npc[numeroMapa][i] = new NpcOCarrascoSemRosto(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        numeroMapa = 7;
        i = 0;
        //criar npc no mapa 7


        numeroMapa = 8;
        i = 0;
        //criar npc no mapa 8
        painel.npc[numeroMapa][i] = new NpcADamaDeEspinhos(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;


        numeroMapa = 9;
        i = 0;
        //criar npc no mapa 9
        

        numeroMapa = 10;
        i = 0;
        //criar npc no mapa 10

        painel.npc[numeroMapa][i] = new NpcOVigiaPartido(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*40;
        i++;

        //...


        /* 
        int numeroMapa = 0;
        int i = 0;

        // mapa 0: Tela Inicial do jogo
        painel.npc[numeroMapa][i] = new NpcVelho(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        //npc novo - fantasma
        painel.npc[numeroMapa][i] = new NpcFantasma(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*11;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        //npc novo - sacerdotiza cega
        painel.npc[numeroMapa][i] = new NpcSacerdotizaCega(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;
        */

        /* 
        //mapa 1: Sala do comerciante
        numeroMapa = 1;
        i = 0;

        painel.npc[numeroMapa][i] = new NpcComerciante(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;


        //mapa 2: Masmorra
        numeroMapa = 2;
        i = 0;

        painel.npc[numeroMapa][i] = new NpcPedraGrande(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*25;
        i++;

        painel.npc[numeroMapa][i] = new NpcPedraGrande(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*11;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*18;
        i++;

        painel.npc[numeroMapa][i] = new NpcPedraGrande(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*14;
        i++;
        */
    }

    public void setInimigos(){

        int numeroMapa = 0;
        int i = 0;
        //criar inimigos no mapa 0
        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*73;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*68;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*73;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*70;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*73;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*73;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*85;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*71;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*82;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*84;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*76;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*86;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*19;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*78;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*82;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*85;
        i++;

        
        
        numeroMapa = 1;
        i = 0;
        //criar inimigos no mapa 1

        //chefão
        painel.inimigo[numeroMapa][i] = new SenhorEsqueleto(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        numeroMapa = 3;
        i = 0;
        //criar inimigos no mapa 3

        numeroMapa = 3;
        i = 0;
        //criar inimigos no mapa 3

        numeroMapa = 4;
        i = 0;
        //criar inimigos no mapa 4

        //chefão
        painel.inimigo[numeroMapa][i] = new SenhorEsqueleto2(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        numeroMapa = 5;
        i = 0;
        //criar inimigos no mapa 5


        numeroMapa = 6;
        i = 0;
        //criar inimigos no mapa 6


        numeroMapa = 7;
        i = 0;
        //criar inimigos no mapa 7

        //chefão
        painel.inimigo[numeroMapa][i] = new SenhorEsqueleto3(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        numeroMapa = 8;
        i = 0;
        //criar inimigos no mapa 8



        numeroMapa = 9;
        i = 0;
        //criar inimigos no mapa 9


        numeroMapa = 10;
        i = 0;
        //criar inimigos no mapa 10

        
        /* 
        int numeroMapa = 0;
        int i = 0;
        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;
        
        
        painel.inimigo[numeroMapa][i] = new SenhorEsqueleto(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;
        


        numeroMapa = 1;
        i = 0;
        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        /* 
        painel.inimigo[numeroMapa][i] = new SenhorEsqueleto2(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;
        */
        /* 
        int numeroMapa = 0;
        int i = 0;
        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*38;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*38;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoOrc(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*33;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*10;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*37;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        // diferentes inimigos em diferentes mapas
        // numeroMapa = 1;
        // painel.inimigo[numeroMapa][i] = new lodoVerde(painel);
        // painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*38;
        // painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        //i++;
        

        numeroMapa = 2;
        i = 0;
        painel.inimigo[numeroMapa][i] = new InimigoMorcego(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*39;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoMorcego(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*36;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*25;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoMorcego(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*39;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*26;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoMorcego(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*28;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoMorcego(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*10;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*19;
        i++;
        

        numeroMapa = 3;
        i = 0;

        painel.inimigo[numeroMapa][i] = new SenhorEsqueleto(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        */
    }
}
