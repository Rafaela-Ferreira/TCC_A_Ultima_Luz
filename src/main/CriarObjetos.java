package main;

import dados.Progresso;
import entidade.NpcADamaDeEspinhos;
import entidade.NpcComerciante;
import entidade.NpcEstatuaRainhaAmelia;
import entidade.NpcOCarrascoSemRosto;
import entidade.NpcOVigiaPartido;
import entidade.NpcPedraGrande;
import entidade.NpcPortalViagemRapida;
import entidade.NpcSacerdotizaCega;
import entidade.NpcVelho;
import main.inimigo.InimigoMorcego;
import main.inimigo.InimigoOrc;
import main.inimigo.LodoVerde;
import main.inimigo.LodoVermelho;
import main.inimigo.chefao.DariusOColecionadorDeAlmas;
import main.inimigo.chefao.EronODevoradorSilencioso;
import objeto.ObjBarraca;
import objeto.ObjBau;
import objeto.ObjChave;
import objeto.ObjCoracao;
import objeto.ObjDiamente;
import objeto.ObjEscudoAzul;
import objeto.ObjFragmentoFaminto;
import objeto.ObjLanterna;
import objeto.ObjMachado;
import objeto.ObjMana;
import objeto.ObjPicareta;
import objeto.ObjPocaoVermelha;
import objeto.ObjPorta;
import objeto.ObjPortaDeFerro;
import tile.blocosInterativos.ArvoreSeca;
import tile.blocosInterativos.Fogueira;
import tile.blocosInterativos.MensagemNoChao;
import tile.blocosInterativos.ParedeDestrutivel;
import tile.blocosInterativos.PlacaDeMetal;








public class CriarObjetos {
    PainelDoJogo painel;

    public CriarObjetos(PainelDoJogo painel) {
        this.painel = painel;
    }

    
    public void setarObjetos() {
        //como adicionar objetos ao array de objetos
        int numeroMapa = 0;
        int i = 0;

        painel.Obj[numeroMapa][i] = new ObjFragmentoFaminto(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.Obj[numeroMapa][i] = new ObjDiamente(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*23;
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


        numeroMapa = 5;
        i = 0;

        painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*26;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.Obj[numeroMapa][i] = new ObjChave(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*30;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*29;
        i++;


        //painel.Obj[numeroMapa][i] = new ObjDiamente(painel);
        //painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
        //painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        //i++;
        
        
    }
    

    public void setBlocosInterativos(){
        int numeroMapa = 0;
        int i = 0;
        //painel.blocosI[numeroMapa][i] = new TesteDeFogueira(painel, 21,19); i++;

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 20,17); i++;

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

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 14,23); i++;

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


        numeroMapa = 5;
        i = 0;

        painel.Obj[numeroMapa][i] = new MensagemNoChao(painel, 24, 22, "Teste");

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 18,36); i++;


        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 33,37); i++;
        
        numeroMapa = 6;
        i = 0;

        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 42,15); i++;
    }

    public void setNpc(){
        int numeroMapa = 0;
        int i = 0;

        // mapa 0: Tela Inicial do jogo
        painel.npc[numeroMapa][i] = new NpcVelho(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.npc[numeroMapa][i] = new NpcEstatuaRainhaAmelia(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;


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
        
        numeroMapa = 4;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcSacerdotizaCega(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        painel.npc[numeroMapa][i] = new NpcADamaDeEspinhos(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        painel.npc[numeroMapa][i] = new NpcOCarrascoSemRosto(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*16;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        painel.npc[numeroMapa][i] = new NpcOVigiaPartido(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*13;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        painel.npc[numeroMapa][i] = new NpcEstatuaRainhaAmelia(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*14;
        i++;

        painel.npc[numeroMapa][i] = new NpcPortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*32;
        i++;



        numeroMapa = 5;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcPedraGrande(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*36;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*33;
        i++;

        numeroMapa = 7;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcSacerdotizaCega(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;
    }

    public void setInimigos(){
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

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*10;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVerde(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*37;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;



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

        if(Progresso.eronODevoradorSilencioso == false){
            painel.inimigo[numeroMapa][i] = new EronODevoradorSilencioso(painel);
            painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
            painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
            i++;
        }

        numeroMapa = 5;
        i = 0;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*39;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*11;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*30;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoOrc(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*33;
        i++;

        numeroMapa = 7;
        i = 0;
        if(Progresso.dariusOColecionadorDeAlmas == false){
            painel.inimigo[numeroMapa][i] = new DariusOColecionadorDeAlmas(painel);
            painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
            painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
            i++;
        }
    }
}
