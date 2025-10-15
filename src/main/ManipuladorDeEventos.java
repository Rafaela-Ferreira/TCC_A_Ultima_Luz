package main;

import dados.Progresso;
import entidade.Entidade;

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

        eventoMestre.dialogo[3][0] = "Altar da Aurora Partida\nO seu progresso salvo foi salvo!";
        eventoMestre.dialogo[4][0] = "Escadaria Ruída\nO seu progresso salvo foi salvo!";
        eventoMestre.dialogo[5][0] = "Ladeira Dos Pilares Quegrados\nO seu progresso salvo foi salvo!";
        eventoMestre.dialogo[6][0] = "Praça Do Despertar\nO seu progresso salvo foi salvo!";
        eventoMestre.dialogo[7][0] = "Portão Silencioso\nO seu progresso salvo foi salvo!";


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
            
            //criação dos eventos -- apagar depois
            if(bater (0, 27,16, "direita") == true){
                buracoDeDano(painel.estadoDoDialogo);
            }
            else if(bater (0, 23,19, "any") == true){
                buracoDeDano(painel.estadoDoDialogo);
            }

            //mapa 1 - Altar da aurora partida

            //fogueira - Altar da aurora partida
            else if(bater(0, 11,10, "any") == true){
                //nomeDaFogueira = "[Altar da Aurora Partida]";
                fogueira(3, painel.estadoDoDialogo);
            }
            //teleporte entre mapas do 0 para o 1 - Praça do Despertar - ida
            else if(bater(0, 40,44, "any") == true){
                teleporteMapa(1, 10,39, painel.fora);

            }
            //teleporte entre mapas do 1 para o 0 - Altar da aurora partida - volta
            else if(bater(1, 10,39, "any") == true){
                teleporteMapa(0, 40,44, painel.fora);
            }

            //mapa 2 - Praça do Despertar

            //fogueira - Praça do Despertar
            else if(bater(1, 11,37, "any") == true){
                //teleporteMapa(4, 10,39, painel.fora);
                fogueira(4, painel.estadoDoDialogo);
            }
            
            else if(bater(1, 8,5, "any") == true){
                fogueira(5, painel.estadoDoDialogo);//mapa 1 - 1.5 Câmara das correntes - porta bloqueada
            }
            else if(bater(1, 23,3, "any") == true){
                fogueira(6, painel.estadoDoDialogo);//mapa 1 - 1.6 Escadaria Ruida (Gula) - porta bloqueada
            }
            else if(bater(1, 44,3, "any") == true){
                fogueira(7, painel.estadoDoDialogo);//mapa  1- 1.7 Entrada Para Luxúria - caminho livre
            }


            //teleporte entre mapas do 1 para o 3 - 1.7 Sala do espelho Carmesim (Luxúria) - ida mapa 3
            else if(bater(1, 45,7, "any") == true){
                teleporteMapa(3, 10,10, painel.fora);

            }
            //teleporte entre mapas do 3 para o 1 - 1.7 Sala do espelho Carmesim (Luxúria) - volta
            else if(bater(3, 10,10, "any") == true){
                teleporteMapa(1, 45,7, painel.fora);
            }

            //teleporte entre mapas do 1 para o 4 - 1.6 Escadaria ruida (Gula) - ida mapa 4
            else if(bater(1, 24,4, "any") == true){
                teleporteMapa(4, 10,10, painel.fora);
            }
            //teleporte entre mapas do 4 para o 1 - 1.6 Escadaria ruida (Gula) - volta
            else if(bater(4, 10,10, "any") == true){
                teleporteMapa(1, 24,4, painel.fora);
            }


            






            /* 
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
            else if(bater (0, 12,9, "any") == true){
                teleporteMapa(2, 9, 41, painel.masmorra);    
            }
            //teleporte entre mapas do 2 para o 0
            else if(bater (2, 9,41, "any") == true){
                teleporteMapa(0, 12,9, painel.fora);
            }

            //teleporte entre mapas do 0 para o 2
            else if(bater (2, 8,7, "any") == true){
                teleporteMapa(3, 26, 41, painel.masmorra);    
            }
            //teleporte entre mapas do 2 para o 0
            else if(bater (3, 26,41, "any") == true){
                teleporteMapa(2, 8,7, painel.masmorra);
            }

            //evento cutscene - boss 01
            else if(bater (3, 25,27, "any") == true){ senhorEsqueleto(); }
            
            */
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


    



    public void senhorEsqueleto(){
        if(painel.batalhaComChefeAtiva == false && Progresso.senhorEsqueletoPadrao == false){
            painel.estadoDoJogo = painel.estadoCutscene;
            painel.gerenciadorDeCutscene.numeroDaCena = painel.gerenciadorDeCutscene.senhorEsqueleto;
        }
    }
}
