package main;

import entidade.Entidade;

public class ManipuladorDeEventos {
    PainelDoJogo painel;
    EventoRetangulo eventoRetangulo[][][];

    int eventoAnteriorX, eventoAnteriorY;
    boolean podeTocarEvento = true;
    int mapaTemporario, colunaTemporaria, linhaTemporaria;

    public ManipuladorDeEventos(PainelDoJogo painel){
        this.painel = painel;

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
            //criação dos eventos
            if(bater (0, 27,16, "direita") == true){
                buracoDeDano(painel.estadoDoDialogo);
            }
            else if(bater (0, 23,19, "any") == true){
                buracoDeDano(painel.estadoDoDialogo);
            }

            else if(bater(0, 23,12, "cima") == true){
                piscinaDeCura(painel.estadoDoDialogo);
            }

            //teleporte entre mapas
            else if(bater (0, 10,31, "any") == true){
                //teleporteMapa(1, 12, 13);
                
            }
            else if(bater (1, 12,13, "any") == true){
                //teleporteMapa(0, 10,31);
            }
            else if(bater(1, 12, 9, "cima") == true ){
                falar(painel.npc[1][0]);
            }

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
        painel.interfaceDoUsuario.dialogoAtual = "Você cai no buraco";
        painel.jogador.vida -=1;
        //eventoRetangulo[coluna][linha].eventoJaAconteceu = true;
        podeTocarEvento = false;
    }

    public void  piscinaDeCura(int estadoDoJogo){
        if(painel.teclado.precionarEnter == true){
            painel.estadoDoJogo = estadoDoJogo;
            painel.jogador.cancelarAtaque = true;
            painel.iniciarEfeitoSonoro(2);
            painel.interfaceDoUsuario.dialogoAtual = "Você tomou água de cura!\nSua vida e sua mana foi renovada.";
            painel.jogador.vida = painel.jogador.vidaMaxima;
            painel.jogador.mana = painel.jogador.manaMaxima;
            painel.criarObjetos.setInimigos();
        }
        
    }

    //pode ser usado para armadilhas
    public void teletransporte(int coluna, int linha, int estadoDoJogo){
        painel.estadoDoJogo = estadoDoJogo;
        painel.interfaceDoUsuario.dialogoAtual = "Teletransportado!";
        painel.jogador.mundoX = painel.tamanhoDoTile*37;
        painel.jogador.mundoY = painel.tamanhoDoTile*10;
    }

    //usado para teleporte entre mapas
    public void teleporteMapa(int mapa, int coluna, int linha){

        painel.estadoDoJogo = painel.estadoDeTransicao;
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
}
