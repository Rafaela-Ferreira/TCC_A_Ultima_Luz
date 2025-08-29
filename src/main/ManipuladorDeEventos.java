package main;

public class ManipuladorDeEventos {
    PainelDoJogo painel;
    EventoRetangulo eventoRetangulo[][];

    int eventoAnteriorX, eventoAnteriorY;
    boolean podeTocarEvento = true;

    public ManipuladorDeEventos(PainelDoJogo painel){
        this.painel = painel;

        eventoRetangulo = new EventoRetangulo[painel.maxColunasMundo][painel.maxLinhasMundo];

        int coluna = 0;
        int linha = 0;
        while(coluna < painel.maxColunasMundo && linha < painel.maxLinhasMundo){
            
            eventoRetangulo[coluna][linha] = new EventoRetangulo();
            eventoRetangulo[coluna][linha].x = 23;
            eventoRetangulo[coluna][linha].y = 23;
            eventoRetangulo[coluna][linha].width = 2;
            eventoRetangulo[coluna][linha].height = 2;
            eventoRetangulo[coluna][linha].eventoRetanguloPadraoX = eventoRetangulo[coluna][linha].x;
            eventoRetangulo[coluna][linha].eventoRetanguloPadraoY = eventoRetangulo[coluna][linha].y;
            
            coluna++;
            if(coluna == painel.maxLinhasMundo){
                coluna = 0;
                linha++;
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
            if(bater (27,16, "direita") == true){
            buracoDeDano(27, 16, painel.estadoDoDialogo);
            }
            if(bater (23,19, "any") == true){
            buracoDeDano(23, 19, painel.estadoDoDialogo);
            }

            if(bater(23,12, "cima") == true){
                piscinaDeCura(23,12, painel.estadoDoDialogo);
            }
            if(bater (19,16, "esquerda") == true){
                teletransporte(19, 16, painel.estadoDoDialogo);
            }
        }

        
    }

    public boolean bater(int coluna, int linha, String direcao){
        
        boolean bater = false;

        painel.jogador.areaSolida.x = painel.jogador.mundoX + painel.jogador.areaSolida.x;
        painel.jogador.areaSolida.y = painel.jogador.mundoY + painel.jogador.areaSolida.y;
        eventoRetangulo[coluna][linha].x = coluna*painel.tamanhoDoTile + eventoRetangulo[coluna][linha].x;
        eventoRetangulo[coluna][linha].y = linha*painel.tamanhoDoTile + eventoRetangulo[coluna][linha].y;
        
        if(painel.jogador.areaSolida.intersects(eventoRetangulo[coluna][linha]) && 
            eventoRetangulo[coluna][linha].eventoJaAconteceu == false){
            if(painel.jogador.direcao.contentEquals(direcao) || direcao.contentEquals("any")){
                bater = true;


                eventoAnteriorX = painel.jogador.mundoX;
                eventoAnteriorY = painel.jogador.mundoY;
            }
        }
        painel.jogador.areaSolida.x = painel.jogador.areaSolidaPadraoX;
        painel.jogador.areaSolida.y = painel.jogador.areaSolidaPadraoY;
        eventoRetangulo[coluna][linha].x = eventoRetangulo[coluna][linha].eventoRetanguloPadraoX;
        eventoRetangulo[coluna][linha].y = eventoRetangulo[coluna][linha].eventoRetanguloPadraoY;
        
        return bater;
    }

    public void buracoDeDano(int coluna, int linha, int estadoDoJogo){
        painel.estadoDoJogo = estadoDoJogo;
        painel.iniciarEfeitoSonoro(7);
        painel.interfaceDoUsuario.dialogoAtual = "Você cai no buraco";
        painel.jogador.vida -=1;
        //eventoRetangulo[coluna][linha].eventoJaAconteceu = true;
        podeTocarEvento = false;
    }

    public void  piscinaDeCura(int coluna, int linha, int estadoDoJogo){
        if(painel.teclado.precionarEnter == true){
            painel.estadoDoJogo = estadoDoJogo;
            painel.jogador.cancelarAtaque = true;
            painel.iniciarEfeitoSonoro(2);
            painel.interfaceDoUsuario.dialogoAtual = "Você tomou água de cura!\nSua vida foi renovada.";
            painel.jogador.vida = painel.jogador.vidaMaxima;
            painel.criarObjetos.setInimigos();
        }
        
    }

    public void teletransporte(int coluna, int linha, int estadoDoJogo){
        painel.estadoDoJogo = estadoDoJogo;
        painel.interfaceDoUsuario.dialogoAtual = "Teletransportado!";
        painel.jogador.mundoX = painel.tamanhoDoTile*37;
        painel.jogador.mundoY = painel.tamanhoDoTile*10;
    }
}
