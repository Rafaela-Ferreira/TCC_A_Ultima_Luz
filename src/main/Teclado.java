package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    PainelDoJogo painel;

    public boolean precionarCima, precionarBaixo, precionarEsquerda, precionarDireita, precionarEnter, teclaDeTiroPressionada;
    
    //debug
    boolean mostrarTextoDebug = false;
    

    public Teclado(PainelDoJogo painel){
        this.painel = painel;
    }


    public void keyTyped(KeyEvent e) {
        // Lógica para quando uma tecla é digitada
        //System.out.println("Tecla digitada: " + e.getKeyChar());
    }
    
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        //Estado do Titulo
        if(painel.estadoDoJogo == painel.tituloEstado){
            estadoTitilo(code);
        }

        //estado de reprodução do jogo
        else if(painel.estadoDoJogo == painel.iniciarEstadoDoJogo){
            // Lógica para quando uma tecla é pressionada
            iniciarEstadoDoJogo(code);
        }
        //pause
        else if(painel.estadoDoJogo == painel.pausarEstadoDoJogo){
            pausarEstadoDoJogo(code);
        }
        
        //dialogo
        else if(painel.estadoDoJogo == painel.estadoDoDialogo){
            dialogoEstado(code);
        } 

        //estado do personagem
        else if(painel.estadoDoJogo == painel.estadoPersonagem){
            personagemEstado(code);
        }
        //estado de opções
        else if(painel.estadoDoJogo == painel.estadoOpcoes){
            opcaoEstado(code);
        }

        //estado de game over
        else if(painel.estadoDoJogo == painel.estadoGameOver){
            estadoGameOver(code);
        }

    }

    public void estadoTitilo(int code){
        //tela inicial
        if(painel.interfaceDoUsuario.estadoDeRolagemTitulo == 0){
            if (code == KeyEvent.VK_W){
                painel.interfaceDoUsuario.numeroDoComando--;
                if( painel.interfaceDoUsuario.numeroDoComando < 0 ){
                    painel.interfaceDoUsuario.numeroDoComando = 2;
                }
            } 
            if (code == KeyEvent.VK_S) {
                painel.interfaceDoUsuario.numeroDoComando++;
                if( painel.interfaceDoUsuario.numeroDoComando > 2 ){
                    painel.interfaceDoUsuario.numeroDoComando = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if(painel.interfaceDoUsuario.numeroDoComando == 0){
                    painel.interfaceDoUsuario.estadoDeRolagemTitulo = 1;
                    painel.iniciarMusica(0);
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 1){
                    //add mais tarde
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 2){
                    System.exit(0);
                }
            }
        }

        //tela de escolha de classe
        else if(painel.interfaceDoUsuario.estadoDeRolagemTitulo == 1){
            if (code == KeyEvent.VK_W){
                painel.interfaceDoUsuario.numeroDoComando--;
                if( painel.interfaceDoUsuario.numeroDoComando < 0 ){
                    painel.interfaceDoUsuario.numeroDoComando = 3;
                }
            } 
            if (code == KeyEvent.VK_S) {
                painel.interfaceDoUsuario.numeroDoComando++;
                if( painel.interfaceDoUsuario.numeroDoComando > 3 ){
                    painel.interfaceDoUsuario.numeroDoComando = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if(painel.interfaceDoUsuario.numeroDoComando == 0){
                    System.out.println("Você escolheu Lutador");
                    painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
                    //painel.iniciarMusica(0);
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 1){
                    painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
                    //painel.iniciarMusica(0);
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 2){
                    painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
                    //painel.iniciarMusica(0);
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 3){
                    painel.interfaceDoUsuario.estadoDeRolagemTitulo = 0;
                }
            }
        }
    }
    public void iniciarEstadoDoJogo(int code){
        if (code == KeyEvent.VK_W){
            precionarCima = true;
        } 
        if (code == KeyEvent.VK_S) {
            precionarBaixo = true;
        } 
        if (code == KeyEvent.VK_A) {
            precionarEsquerda = true;
        } 
        if (code == KeyEvent.VK_D) {
            precionarDireita = true;
        }
        if (code == KeyEvent.VK_P) {
            painel.estadoDoJogo = painel.pausarEstadoDoJogo;
        }
        if(code == KeyEvent.VK_C){ 
            painel.estadoDoJogo = painel.estadoPersonagem;
        }
        if (code == KeyEvent.VK_ENTER) {
            precionarEnter = true;
        }
        if (code == KeyEvent.VK_F) {
            teclaDeTiroPressionada = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            painel.estadoDoJogo = painel.estadoOpcoes;
        }
        //debug
        if(code == KeyEvent.VK_T){
            if(mostrarTextoDebug == false){
                mostrarTextoDebug = true;
            } else if(mostrarTextoDebug == true){
                mostrarTextoDebug = false;
            }
        }
        if(code == KeyEvent.VK_R){
            switch (painel.mapaAtual) {
                case 0: painel.gerenciadorDeBlocos.carregarMapa("/mapas/mapaV3.txt", 0); break;
                case 1: painel.gerenciadorDeBlocos.carregarMapa("/mapas/interior01.txt", 1); break;
            }
            
        }

    }

    public void pausarEstadoDoJogo(int code){
        if (code == KeyEvent.VK_P) {
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
        }
    }

    public void dialogoEstado(int code){
        if (code == KeyEvent.VK_ENTER) {
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
        }
    }


    public void personagemEstado(int code){
        if (code == KeyEvent.VK_C) {
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
        }
        if(code == KeyEvent.VK_W){
            if(painel.interfaceDoUsuario.espacoLinha != 0){
                painel.interfaceDoUsuario.espacoLinha--;
                painel.iniciarEfeitoSonoro(9);
            }
        }
        if(code == KeyEvent.VK_A){
            if(painel.interfaceDoUsuario.espacoColuna != 0){
                painel.interfaceDoUsuario.espacoColuna--;
                painel.iniciarEfeitoSonoro(9);
            }
        }
        if(code == KeyEvent.VK_S){
            if(painel.interfaceDoUsuario.espacoLinha != 3){
                painel.interfaceDoUsuario.espacoLinha++;
                painel.iniciarEfeitoSonoro(9);

            }
        }
        if(code == KeyEvent.VK_D){
            if(painel.interfaceDoUsuario.espacoColuna != 4){
                painel.interfaceDoUsuario.espacoColuna++;
                painel.iniciarEfeitoSonoro(9);
            }
           
        }
        if(code == KeyEvent.VK_ENTER){
            painel.jogador.selecionarItem();
        }
    }





    public void estadoGameOver(int code){
        if(code == KeyEvent.VK_W){
            painel.interfaceDoUsuario.numeroDoComando--;
            if(painel.interfaceDoUsuario.numeroDoComando < 0){
                painel.interfaceDoUsuario.numeroDoComando = 1;
            }
            painel.iniciarEfeitoSonoro(9);
        }
        if(code == KeyEvent.VK_S){
            painel.interfaceDoUsuario.numeroDoComando++;
            if(painel.interfaceDoUsuario.numeroDoComando > 1){
                painel.interfaceDoUsuario.numeroDoComando = 0;
            }
            painel.iniciarEfeitoSonoro(9);
        }

        if(code == KeyEvent.VK_ENTER){
            if(painel.interfaceDoUsuario.numeroDoComando == 0){
                painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
                painel.tenteNovamente();
                painel.iniciarMusica(0);

            }else if(painel.interfaceDoUsuario.numeroDoComando == 1){
                painel.interfaceDoUsuario.estadoDeRolagemTitulo = 0; // voltar para o menu inicial (0 é a tela inicial, 1 é a escolha de classe)
                painel.estadoDoJogo = painel.tituloEstado;
                painel.reiniciar();
            }
            

        }
    }



    public void opcaoEstado(int code){
        if(code == KeyEvent.VK_ESCAPE){
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
        }
        if(code == KeyEvent.VK_ENTER){
            precionarEnter = true;
        }
        int maxNumComando = 0;
        switch (painel.interfaceDoUsuario.subEstado) {
            case 0: maxNumComando = 5; break;
            case 3: maxNumComando = 1; break;
        
            
        }

        if(code == KeyEvent.VK_W){
            painel.interfaceDoUsuario.numeroDoComando--;
            painel.iniciarEfeitoSonoro(9);
            if(painel.interfaceDoUsuario.numeroDoComando < 0){
                painel.interfaceDoUsuario.numeroDoComando = maxNumComando;
            }

        }
        if(code == KeyEvent.VK_S){
            painel.interfaceDoUsuario.numeroDoComando++;
            painel.iniciarEfeitoSonoro(9);
            if(painel.interfaceDoUsuario.numeroDoComando >  maxNumComando){
                painel.interfaceDoUsuario.numeroDoComando = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(painel.interfaceDoUsuario.subEstado == 0){
                if(painel.interfaceDoUsuario.numeroDoComando == 1 && painel.musica.escalaDoVolume > 0){
                    painel.musica.escalaDoVolume--;
                    painel.musica.VerificarVolume();
                    painel.iniciarEfeitoSonoro(9);
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 2 && painel.efeitoSonoro.escalaDoVolume > 0){
                    painel.efeitoSonoro.escalaDoVolume--;
                    painel.iniciarEfeitoSonoro(9);
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(painel.interfaceDoUsuario.subEstado == 0){
                if(painel.interfaceDoUsuario.numeroDoComando == 1 && painel.musica.escalaDoVolume < 5){
                    painel.musica.escalaDoVolume++;
                    painel.musica.VerificarVolume();
                    painel.iniciarEfeitoSonoro(9);
                }
                if(painel.interfaceDoUsuario.numeroDoComando == 2 && painel.efeitoSonoro.escalaDoVolume < 5){
                    painel.efeitoSonoro.escalaDoVolume++;
                    painel.iniciarEfeitoSonoro(9);
                }
            }
        }

        
    }


    public void keyReleased(KeyEvent e) {
        // Lógica para quando uma tecla é liberada
        //System.out.println("Tecla liberada: " + e.getKeyChar());
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
           // System.out.println("Movendo para cima");
            precionarCima = false;
        } else if (code == KeyEvent.VK_S) {
            //System.out.println("Movendo para baixo");
            precionarBaixo = false;
        } else if (code == KeyEvent.VK_A) {
            //System.out.println("Movendo para a esquerda");
            precionarEsquerda = false;
        } else if (code == KeyEvent.VK_D) {
            //System.out.println("Movendo para a direita");
            precionarDireita = false;
        }else if (code == KeyEvent.VK_F) {
            teclaDeTiroPressionada = false;
        }
    }
    
}
