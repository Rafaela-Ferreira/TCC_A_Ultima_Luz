package main;

import dados.Progresso;
import entidade.Entidade;
import entidade.NpcAliado;
import java.awt.Color;
import java.awt.Graphics2D;
import main.inimigo.InimigoInvasor;



public class ManipuladorDeEventos {
    PainelDoJogo painel;
    EventoRetangulo eventoRetangulo[][][];
    Entidade eventoMestre;

    int eventoAnteriorX, eventoAnteriorY;
    boolean podeTocarEvento = true;
    int mapaTemporario, colunaTemporaria, linhaTemporaria;

    String nomeDaFogueira = "";


    public ManipuladorDeEventos(PainelDoJogo painel){
        this.painel = painel;

        eventoMestre = new Entidade(painel);

        eventoRetangulo = new EventoRetangulo[painel.maxMapa][painel.maxColunasMundo][painel.maxLinhasMundo];

        int mapa = 0;
        int coluna = 0;
        int linha = 0;
        while(mapa < painel.maxMapa && coluna < painel.maxColunasMundo && linha < painel.maxLinhasMundo){
            
            eventoRetangulo[mapa][coluna][linha] = new EventoRetangulo();
            eventoRetangulo[mapa][coluna][linha].x = 23;
            eventoRetangulo[mapa][coluna][linha].y = 23;
            eventoRetangulo[mapa][coluna][linha].width = 2;
            eventoRetangulo[mapa][coluna][linha].height = 2;
            eventoRetangulo[mapa][coluna][linha].eventoRetanguloPadraoX = eventoRetangulo[mapa][coluna][linha].x;
            eventoRetangulo[mapa][coluna][linha].eventoRetanguloPadraoY = eventoRetangulo[mapa][coluna][linha].y;
            
            coluna++;
            if(coluna == painel.maxLinhasMundo){
                coluna = 0;
                linha++;

                if(linha == painel.maxLinhasMundo){
                    linha = 0;
                    mapa++;
                }
            }
        }
        
        setDialogo();
    }

    public void setDialogo(){
        

        eventoMestre.dialogo[0][0] = "Você cai no buraco";

        eventoMestre.dialogo[1][0] = "Você tomou água de cura!\nSua vida e sua mana foi renovada.\nO seu progresso salvo foi salvo!";
        eventoMestre.dialogo[1][1] = "Esta é uma boa água.";
        
        eventoMestre.dialogo[2][0] = "Teletransportado!";

        eventoMestre.dialogo[3][0] = "Praça do Despertar.";
        eventoMestre.dialogo[3][1] = "O seu progresso foi salvo!";
        eventoMestre.dialogo[4][0] = "Escadaria Ruída\nO seu progresso foi salvo!";
        eventoMestre.dialogo[5][0] = "Ladeira Dos Pilares Quegrados\nO seu progresso foi salvo!";
        eventoMestre.dialogo[6][0] = "Praça Do Despertar\nO seu progresso foi salvo!";
        eventoMestre.dialogo[7][0] = "Portão Silencioso\nO seu progresso foi salvo!";


        eventoMestre.dialogo[8][0] = "Porta bloqueada\nNecessário: Chave de prata";
        eventoMestre.dialogo[9][0] = "Porta bloqueada\nVolte mais tarde!";
        eventoMestre.dialogo[10][0] = "Entrada para Luxúria...";
    
    }

    public void verificarEvento(){

        //verificar se o personagem está a mais de um bloco de distância do último evento
        int distanciaX = Math.abs(painel.jogador.mundoX - eventoAnteriorX);
        int distanciaY = Math.abs(painel.jogador.mundoY - eventoAnteriorY);
        int distancia = Math.max(distanciaX, distanciaY);
        if(distancia > painel.tamanhoDoTile){
            podeTocarEvento = true;   
        }
        if(podeTocarEvento == true){


            if(bater (0, 27,16, "direita") == true){
                buracoDeDano(painel.estadoDoDialogo);
            }
            else if(bater (0, 23,19, "any") == true){
                buracoDeDano(painel.estadoDoDialogo);
            }


            //teleporte entre mapas do 0 para o 1
            else if(bater (0, 10,39, "any") == true){
                teleporteMapa(1, 12, 13, painel.interior);    
            }
            //teleporte entre mapas do 1 para o 0
            else if(bater (1, 12,13, "any") == true){
                teleporteMapa(0, 10,39, painel.fora);
            }

            //armadilha: teletransporte no mesmo mapa
            else if(bater (0, 18,16, "any") == true){
                teleporteMapa(0, 38,9, painel.fora);
            }
            
            else if(bater(1, 12, 9, "cima") == true ){
                falar(painel.npc[1][0]);
            }

            //teleporte entre mapas do 0 para o 2
            else if(bater (0, 12, 9, "any") == true){
                teleporteMapa(2, 9, 41, painel.masmorra);    
            }
            //teleporte entre mapas do 2 para o 0
            else if(bater (2, 9, 41, "any") == true){
                teleporteMapa(0, 12, 9, painel.fora);
            }

            //teleporte entre mapas do 0 para o 2
            else if(bater (2, 8, 7, "any") == true){
                teleporteMapa(3, 26, 41, painel.masmorra);    
            }
            //teleporte entre mapas do 2 para o 0
            else if(bater (3, 26, 41, "any") == true){
                teleporteMapa(2, 8, 7, painel.masmorra);
            }

            //teleporte entre mapas do 3 para o 5
            else if(bater (3, 25, 8, "any") == true){
                teleporteMapa(5, 23, 41, painel.fora);    
            }
            //teleporte entre mapas do 5 para o 3
            else if(bater (5, 23, 41, "any") == true){
                teleporteMapa(3, 25, 8, painel.masmorra);
            }


            //teleporte entre mapas do 5 para o 6
            else if(bater (5, 38, 9, "any") == true){
                teleporteMapa(6, 8, 7, painel.masmorra);    
            }
            //teleporte entre mapas do 6 para o 5
            else if(bater (6, 8, 7, "any") == true){
                teleporteMapa(5, 38, 9, painel.fora);
            }

            //teleporte entre mapas do 5 para o 4
            else if(bater (5, 10, 39, "any") == true){
                teleporteMapa(4, 12, 16, painel.interior);    
            }
            //teleporte entre mapas do 4 para o 5
            else if(bater (4, 12, 16, "any") == true){
                teleporteMapa(5, 10, 39, painel.fora);
            }

            else if(bater (6, 46, 15, "any") == true){
                teleporteMapa(10, 10, 39, painel.fora);
            }

            else if(bater (10, 10, 39, "any") == true){
                teleporteMapa(6, 46, 15, painel.masmorra);
            }

            else if(bater (6, 9, 41, "any") == true){
                teleporteMapa(7, 26, 41, painel.masmorra);
            }

            else if(bater (7, 25,8, "any") == true){
                teleporteMapa(11, 23, 39, painel.fora);
            }
           
            else if(bater (11, 23,39, "any") == true){
                teleporteMapa(7, 25, 8, painel.masmorra);
            }

            

            //FOGUEIRA mapa 0
            else if(bater(0, 21,19, "any") == true){
                fogueira(3, painel.estadoDoDialogo);
            }
            
            //FOGUEIRA mapa 2
            else if(bater(2, 15,25, "any") == true){
                fogueira(3, painel.estadoDoDialogo);
            }

            //FOGUEIRA mapa 5
            else if(bater(5, 19,38, "any") == true){
                fogueira(3, painel.estadoDoDialogo);
            }

            
            //evento cutscene - boss 01
            else if(bater (3, 25,27, "any") == true){ EronODevoradorSilencioso(); }


            //evento cutscene - boss 02
            else if(bater (7, 25,27, "any") == true){ DariusOColecionadorDeAlmas(); }
          

            

        }

        
    }



    public boolean bater(int mapa, int coluna, int linha, String direcao){
        
        boolean bater = false;

        if(mapa == painel.mapaAtual){
            painel.jogador.areaSolida.x = painel.jogador.mundoX + painel.jogador.areaSolida.x;
            painel.jogador.areaSolida.y = painel.jogador.mundoY + painel.jogador.areaSolida.y;
            eventoRetangulo[mapa][coluna][linha].x = coluna*painel.tamanhoDoTile + eventoRetangulo[mapa][coluna][linha].x;
            eventoRetangulo[mapa][coluna][linha].y = linha*painel.tamanhoDoTile + eventoRetangulo[mapa][coluna][linha].y;
            
            if(painel.jogador.areaSolida.intersects(eventoRetangulo[mapa][coluna][linha]) && 
                eventoRetangulo[mapa][coluna][linha].eventoJaAconteceu == false){
                if(painel.jogador.direcao.contentEquals(direcao) || direcao.contentEquals("any")){
                    bater = true;


                    eventoAnteriorX = painel.jogador.mundoX;
                    eventoAnteriorY = painel.jogador.mundoY;
                }
            }
            painel.jogador.areaSolida.x = painel.jogador.areaSolidaPadraoX;
            painel.jogador.areaSolida.y = painel.jogador.areaSolidaPadraoY;
            eventoRetangulo[mapa][coluna][linha].x = eventoRetangulo[mapa][coluna][linha].eventoRetanguloPadraoX;
            eventoRetangulo[mapa][coluna][linha].y = eventoRetangulo[mapa][coluna][linha].eventoRetanguloPadraoY;
        }
        return bater;
    }

    public void buracoDeDano(int estadoDoJogo){
        painel.estadoDoJogo = estadoDoJogo;
        painel.iniciarEfeitoSonoro(7);
        eventoMestre.iniciarDialogo(eventoMestre, 0);
        painel.jogador.vida -=1;
        //eventoRetangulo[coluna][linha].eventoJaAconteceu = true;
        podeTocarEvento = false;
    }

    public void  fogueira(int indiceDialogo, int estadoDoJogo){
        if(painel.teclado.precionarEnter == true){
            painel.estadoDoJogo = estadoDoJogo;
            painel.jogador.cancelarAtaque = true;
            painel.iniciarEfeitoSonoro(2);
            //eventoMestre.iniciarDialogo(eventoMestre, 1);
            eventoMestre.iniciarDialogo(eventoMestre, indiceDialogo);
            painel.jogador.vida = painel.jogador.vidaMaxima;
            painel.jogador.mana = painel.jogador.manaMaxima;
            painel.criarObjetos.setInimigos();

            //ponto de salvamento
            painel.jogador.salvarPonto();

            
            painel.salvarE_Carregar.salvar();
        }
        
    }

    //pode ser usado para armadilhas
    public void teletransporte(int coluna, int linha, int estadoDoJogo){
        painel.estadoDoJogo = estadoDoJogo;
        eventoMestre.iniciarDialogo(eventoMestre, 2);
        painel.jogador.mundoX = painel.tamanhoDoTile*37;
        painel.jogador.mundoY = painel.tamanhoDoTile*10;
    }

    //usado para teleporte entre mapas
    public void teleporteMapa(int mapa, int coluna, int linha, int area){

        painel.estadoDoJogo = painel.estadoDeTransicao;
        painel.proximaArea = area;
        mapaTemporario = mapa;
        colunaTemporaria = coluna;
        linhaTemporaria = linha;
        podeTocarEvento = false;
        painel.iniciarEfeitoSonoro(13);
    }

    public void falar(Entidade entidade){
        if(painel.teclado.precionarEnter == true){
            painel.estadoDoJogo = painel.estadoDoDialogo;
            painel.jogador.cancelarAtaque = true;
            entidade.falar();
        }
    }


    



    public void EronODevoradorSilencioso(){
        if(painel.batalhaComChefeAtiva == false && Progresso.eronODevoradorSilencioso == false){
            painel.estadoDoJogo = painel.estadoCutscene;
            painel.gerenciadorDeCutscene.numeroDaCena = painel.gerenciadorDeCutscene.eronODevoradorSilencioso;
        }
    }





    public void DariusOColecionadorDeAlmas(){
        if(painel.batalhaComChefeAtiva == false && Progresso.dariusOColecionadorDeAlmas == false){
            painel.estadoDoJogo = painel.estadoCutscene;
            painel.gerenciadorDeCutscene.numeroDaCena = painel.gerenciadorDeCutscene.dariusOColecionadorDeAlmas;
        }
    }


    public void senhorEsqueleto3(){
        if(painel.batalhaComChefeAtiva == false && Progresso.senhorEsqueletoPadrao3 == false){
            painel.estadoDoJogo = painel.estadoCutscene;
            painel.gerenciadorDeCutscene.numeroDaCena = painel.gerenciadorDeCutscene.senhorEsqueleto;
        }
    }

    public void invasaoMapa1() {

        if (Progresso.invasaoMapa1Ativa == false &&
            Progresso.invasorMapa1Derrotado == false) {

            Progresso.invasaoMapa1Ativa = true;

            // cria o invasor (hostil)
            painel.inimigo[painel.mapaAtual][0] = new InimigoInvasor(painel);
            painel.inimigo[painel.mapaAtual][0].mundoX = painel.tamanhoDoTile * 32;
            painel.inimigo[painel.mapaAtual][0].mundoY = painel.tamanhoDoTile * 20;

            // Mensagem de alerta
            painel.iniciarEfeitoSonoro(14); // som sinistro
            painel.interfaceDoUsuario.adicionarMensagem("Um espírito hostil invadiu o mundo..." );

            //TEMPORIZADOR 
            new Thread(() -> {
                try {
                    int tempoEmSegundos = 30; // 30 segundos 
                    Thread.sleep(tempoEmSegundos * 1000); //1000 = 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // cria o NPC aliado APÓS 10 segundos
                painel.npc[painel.mapaAtual][0] = new NpcAliado(painel);
                painel.npc[painel.mapaAtual][0].mundoX = painel.tamanhoDoTile * 28;
                painel.npc[painel.mapaAtual][0].mundoY = painel.tamanhoDoTile * 22;

                painel.iniciarEfeitoSonoro(15);
                painel.interfaceDoUsuario.adicionarMensagem("Um espírito aliado atravessou\nos véus para ajudá-lo!");
            }).start();
            

            painel.batalhaComChefeAtiva = true;
            podeTocarEvento = false;
        }
    }
    
    /* viagem rápida */
    public void portal(){

        if(podeTocarEvento == false){

            painel.estadoDoJogo = painel.estadoViagemRapida;
            painel.interfaceDoUsuario.numeroDoComando = 0;

            podeTocarEvento = true;
        }
    }

    public void viajarRapido(int destino){

        switch(destino){

            case 0: 
                teleporteMapa(4, 14, 16, painel.interior);
                break;
            /* 
            case 1: 
                teleporteMapa(1, 26, 41, painel.fora);
                break;

            case 2: 
                teleporteMapa(2, 26, 41, painel.interior);
                break;

            case 3: 
                teleporteMapa(3, 26, 41, painel.fora);
                break;

            case 4: 
                teleporteMapa(4, 25, 8, painel.fora);
                break;

            case 5: 
                teleporteMapa(5, 26, 41, painel.interior);
                break;

            case 6: 
                teleporteMapa(6, 26, 41, painel.fora);
                break;

            case 7: 
                teleporteMapa(7, 26, 41, painel.fora);
                break;

            case 8: 
                teleporteMapa(8, 25, 8, painel.fora);
                break;

            case 9: 
                teleporteMapa(9, 26, 41, painel.interior);
                break;
            */
        }

        podeTocarEvento = false;
    }
    

    public void desenhar(Graphics2D g2){

        g2.setColor(new Color(0, 0, 255, 120)); // azul translúcido

        for(int coluna = 0; coluna < painel.maxColunasMundo; coluna++){
            for(int linha = 0; linha < painel.maxLinhasMundo; linha++){

                EventoRetangulo er = eventoRetangulo[painel.mapaAtual][coluna][linha];
                if(er == null) continue;

                int mundoX = coluna * painel.tamanhoDoTile + er.eventoRetanguloPadraoX;
                int mundoY = linha * painel.tamanhoDoTile + er.eventoRetanguloPadraoY;

                int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
                int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

                g2.fillRect(telaX, telaY, er.width, er.height);
            }
        }
    }
    
}
