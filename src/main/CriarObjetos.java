package main;

import dados.Progresso;
import entidade.GuardiaDaLuz;
import entidade.NpcADamaDeEspinhos;
import entidade.NpcComerciante;
import entidade.NpcOCarrascoSemRosto;
import entidade.NpcOVigiaPartido;
import entidade.NpcPedraGrande;
import entidade.PortalViagemRapida;
import main.inimigo.EcoNoturno;
import main.inimigo.GuardiaoDePedra;
import main.inimigo.InimigoMorcego;
import main.inimigo.InimigoOrc;
import main.inimigo.LargatoCorrompido;
import main.inimigo.LodoVerde;
import main.inimigo.LodoVermelho;
import main.inimigo.MaoDoAbismo;
import main.inimigo.VigiaQuebrado;
import main.inimigo.chefao.AurionOArcanjoCaido;
import main.inimigo.chefao.DariusOColecionadorDeAlmas;
import main.inimigo.chefao.EronODevoradorSilencioso;
import main.inimigo.chefao.KaelgorOGuerreiroEmChamas;
import objeto.ObjAlmaGrande;
import objeto.ObjAlmaMedia;
import objeto.ObjAlmaSombria;
import objeto.ObjBarraca;
import objeto.ObjBau;
import objeto.ObjChave;
import objeto.ObjDiamente;
import objeto.ObjLanterna;
import objeto.ObjMachado;
import objeto.ObjMoedaBronze;
import objeto.ObjPocaoAzul;
import objeto.ObjPocaoVermelha;
import objeto.ObjPorta;
import objeto.ObjPortaDeFerro;
import objeto.ObjTocha;
import tile.blocosInterativos.ArvoreSeca;
import tile.blocosInterativos.ArvoreSeca2;
import tile.blocosInterativos.ArvoreSeca3;
import tile.blocosInterativos.ArvoreSeca4;
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

        painel.Obj[numeroMapa][i] = new ObjChave(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*12;
        i++;

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
        painel.Obj[numeroMapa][i].setSaque(new ObjLanterna(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*13;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPocaoVermelha(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        //Mapa: masmorra
        numeroMapa = 1;
        i = 0;
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjMachado(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*41;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*13;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoAzul(painel));
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
        numeroMapa = 2;
        i = 0;

        //condição para que não crie o porta novamente apos derrota-ló
        if(Progresso.eronODevoradorSilencioso == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }

        numeroMapa = 3;
        i = 0;
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjChave(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*29;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*17;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjChave(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*30;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*17;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjMoedaBronze(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*32;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*18;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaMedia(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*29;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*14;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaMedia(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*33;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
        i++;

        painel.Obj[numeroMapa][i] = new ObjChave(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*12;
        i++;

        numeroMapa = 4;
        i = 0;
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjBarraca(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*34;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjMachado(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*8;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*17;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaMedia(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*22;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaMedia(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*9;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*41;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*18;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*13;
        i++;

        numeroMapa = 5;
        i = 0;

        //condição para que não crie o porta novamente apos derrota-ló
        if(Progresso.dariusOColecionadorDeAlmas == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }

        numeroMapa = 6;
        i = 0;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*26;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPocaoAzul(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        painel.Obj[numeroMapa][i] = new ObjChave(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*21;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPocaoVermelha(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        painel.Obj[numeroMapa][i] = new ObjBarraca(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*37;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*37;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaGrande(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*37;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaGrande(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*41;
        i++;
        
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjTocha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*10;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*39;
        i++;

        numeroMapa = 7;
        i = 0;

        //condição para que não crie o porta novamente apos derrota-ló
        if(Progresso.aurionOArcanjoCaido == false){
            painel.Obj[numeroMapa][i] = new ObjPortaDeFerro(painel);
            painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*25;
            painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*15;
            i++;
        }

        painel.Obj[numeroMapa][i] = new ObjDiamente(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*28;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        numeroMapa = 8;
        i = 0;
        painel.Obj[numeroMapa][i] = new ObjBau(painel);
        painel.Obj[numeroMapa][i].setSaque(new ObjPocaoVermelha(painel));
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*30;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*29;
        i++;

        painel.Obj[numeroMapa][i] = new ObjPorta(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*13;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*23;
        i++;

        painel.Obj[numeroMapa][i] = new ObjChave(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*34;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaSombria(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*37;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaSombria(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*41;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaSombria(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaSombria(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        painel.Obj[numeroMapa][i] = new ObjAlmaSombria(painel);
        painel.Obj[numeroMapa][i].mundoX = painel.tamanhoDoTile*38;
        painel.Obj[numeroMapa][i].mundoY = painel.tamanhoDoTile*29;
        i++;

    }
    

    public void setBlocosInterativos(){
        int numeroMapa = 0;
        int i = 0;

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 20,17); i++;

        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 26,11, "Algumas árvores podem ser destruidas!"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 16,20, "Baús podem conter itens úteis!"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 25,19, "Precione ESC para abrir o menu de opções"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 18,22, "Precione C para abrir o inventário"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 35,19, "Precione M para abrir o mapa"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 37,21, "Precione X para abrir o mini mapa"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 39,17, "Precione ENTER para atacar"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 39,15, "Precione ESPAÇO para defender ataques"); i++;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 41,32, "Portal de viagem rápida!"); i++;


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
        numeroMapa = 1;
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

        numeroMapa = 3;
        i = 0;
        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 24,35); i++;

        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 26,15); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 26,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 26,17); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 27,15); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 28,15); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 29,15); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 27,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 28,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 29,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 30,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 31,16); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 27,17); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 27,18); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 28,18); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 29,18); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 30,18); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca3(painel, 31,18); i++;
        
        numeroMapa = 4;
        i = 0;

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 19,34); i++;

        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 27,15); i++;
        painel.blocosI[numeroMapa][i] = new PlacaDeMetal(painel, 38, 30); i++;

        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 42,15); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 26,29); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 26,30); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 26,31); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 26,32); i++;

        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,22); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,23); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,24); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,25); i++;

        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 9,37); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 10,37); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 11,37); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 12,37); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 13,37); i++;
        painel.blocosI[numeroMapa][i] = new ParedeDestrutivel(painel, 14,37); i++;


        numeroMapa = 5;
        i = 0;
        
        numeroMapa = 6;
        i = 0;

        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 25,37); i++;

        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 13,40); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 14,40); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 15,40); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 16,40); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 17,40); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 18,40); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 10,41); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 11,41); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 12,41); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca4(painel, 13,41); i++;

       
        numeroMapa = 7;
        i = 0;
        painel.blocosI[numeroMapa][i] = new MensagemNoChao(painel, 25,8, "Se decidir seguir...\nNão podera voltar!!"); i++;

        numeroMapa = 8;
        i = 0;
        painel.blocosI[numeroMapa][i] = new Fogueira(painel, 24,21); i++;
        
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 25,27); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 26,27); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 27,27); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 27,28); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 27,29); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 27,30); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 27,31); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 28,31); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 29,31); i++;

        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 27,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 28,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 29,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 30,12); i++;
        painel.blocosI[numeroMapa][i] = new ArvoreSeca2(painel, 31,12); i++;
    }

    public void setNpc(){
        int numeroMapa = 0;
        int i = 0;

        // mapa 0: Tela Inicial do jogo
        painel.npc[numeroMapa][i] = new PortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*43;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*33;
        i++;

        //mapa: Masmorra
        numeroMapa = 1;
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
        
        painel.npc[numeroMapa][i] = new PortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        numeroMapa = 3;
        i = 0;
        painel.npc[numeroMapa][i] = new PortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*19;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*38;
        i++;

        numeroMapa = 4;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcPedraGrande(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*18;
        i++;

        painel.npc[numeroMapa][i] = new NpcPedraGrande(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*26;
        i++;

        painel.npc[numeroMapa][i] = new PortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*16;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*36;
        i++;

        numeroMapa = 5;
        i = 0;

        numeroMapa = 6;
        i = 0;

        painel.npc[numeroMapa][i] = new NpcOCarrascoSemRosto(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*31;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*25;
        i++;

        painel.npc[numeroMapa][i] = new PortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*20;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*38;
        i++;

        numeroMapa = 7;
        i = 0;

        numeroMapa = 8;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcOVigiaPartido(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*24;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*41;
        i++;

        painel.npc[numeroMapa][i] = new PortalViagemRapida(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*22;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;
    
        numeroMapa = 10;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcComerciante(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        numeroMapa = 11;
        i = 0;
        painel.npc[numeroMapa][i] = new NpcADamaDeEspinhos(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;

        numeroMapa = 12;
        i = 0;
    
        painel.npc[numeroMapa][i] = new GuardiaDaLuz(painel);
        painel.npc[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.npc[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
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



        numeroMapa = 1;
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
        

        numeroMapa = 2;
        i = 0;

        if(Progresso.eronODevoradorSilencioso == false){
            painel.inimigo[numeroMapa][i] = new EronODevoradorSilencioso(painel);
            painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
            painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
            i++;
        }


        numeroMapa = 3;
        i = 0;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*33;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*36;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*7;
        i++;
        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*37;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*9;
        i++;
        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.inimigo[numeroMapa][i] = new LodoVermelho(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*38;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoOrc(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*33;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoOrc(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*36;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*25;
        i++;

        painel.inimigo[numeroMapa][i] = new InimigoOrc(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
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

        numeroMapa = 4;
        i = 0;
        painel.inimigo[numeroMapa][i] = new EcoNoturno(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*39;
        i++;

        painel.inimigo[numeroMapa][i] = new EcoNoturno(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*36;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*25;
        i++;

        painel.inimigo[numeroMapa][i] = new EcoNoturno(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*39;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*26;
        i++;

        painel.inimigo[numeroMapa][i] = new EcoNoturno(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*28;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new EcoNoturno(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*10;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*19;
        i++;

        painel.inimigo[numeroMapa][i] = new EcoNoturno(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*27;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*18;
        i++;

        numeroMapa = 5;
        i = 0;

        if(Progresso.dariusOColecionadorDeAlmas == false){
            painel.inimigo[numeroMapa][i] = new DariusOColecionadorDeAlmas(painel);
            painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
            painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
            i++;
        }


        
        numeroMapa = 6;
        i = 0;
        painel.inimigo[numeroMapa][i] = new GuardiaoDePedra(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*26;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*12;
        i++;

        painel.inimigo[numeroMapa][i] = new GuardiaoDePedra(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*20;
        i++;

        painel.inimigo[numeroMapa][i] = new GuardiaoDePedra(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*9;
        i++;

        painel.inimigo[numeroMapa][i] = new GuardiaoDePedra(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*33;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*39;
        i++;

        painel.inimigo[numeroMapa][i] = new VigiaQuebrado(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*10;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*30;
        i++;

        painel.inimigo[numeroMapa][i] = new VigiaQuebrado(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*30;
        i++;

        painel.inimigo[numeroMapa][i] = new GuardiaoDePedra(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new VigiaQuebrado(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*10;
        i++;

        painel.inimigo[numeroMapa][i] = new VigiaQuebrado(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*37;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;

        numeroMapa = 7; // chefe final - arion orgulho
        i = 0;

        if(Progresso.aurionOArcanjoCaido == false){
            painel.inimigo[numeroMapa][i] = new AurionOArcanjoCaido(painel);
            painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
            painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
            i++;
        }

        numeroMapa = 8; 
        i = 0;
        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*21;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*38;
        i++;

        painel.inimigo[numeroMapa][i] = new MaoDoAbismo(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*14;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*30;
        i++;

        painel.inimigo[numeroMapa][i] = new MaoDoAbismo(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*15;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*28;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*34;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*38;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*42;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*12;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*33;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*11;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*32;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*35;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*11;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*40;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*10;
        i++;

        painel.inimigo[numeroMapa][i] = new LargatoCorrompido(painel);
        painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*37;
        painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*8;
        i++;


        numeroMapa = 9; // chefe secreto - Kaelgor ira 
        i = 0;

        if(Progresso.kaelgorOGuerreiroEmChamas == false){
            painel.inimigo[numeroMapa][i] = new KaelgorOGuerreiroEmChamas(painel);
            painel.inimigo[numeroMapa][i].mundoX = painel.tamanhoDoTile*23;
            painel.inimigo[numeroMapa][i].mundoY = painel.tamanhoDoTile*16;
            i++;
        }

        numeroMapa = 10; 
        i = 0;
    }
}
