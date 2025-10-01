package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entidade.jogadorManequim;
import main.inimigo.chefao.SenhorEsqueleto;
import objeto.ObjDiamente;
import objeto.ObjPortaDeFerro;

public class GerenciadorDeCutscene {
    PainelDoJogo painel;
    Graphics2D g2;
    public int numeroDaCena;
    public int faseDaCena;
    int contador = 0;
    float alpha = 0f;
    int y;
    String creditosFinais;

    //numero das cenas
    public final int NA = 0;
    public final int senhorEsqueleto = 1;
    public final int cenaFinal = 2;

    public GerenciadorDeCutscene(PainelDoJogo painel){
        this.painel = painel;

        creditosFinais = "Programação/Musica/Arte\n"
        +"RyiSnow"
        +"\n\n\n\n\n\n\n\n\n\n\n\n\n"
        +"Agradecimentos especiais\n"
        +"Alguém\n"
        +"Alguém\n"
        +"Alguém\n"
        +"Alguém\n\n\n\n\n\n"
        + "Obrigado por jogar!";
    }

    public void desenhar(Graphics2D g2){
        this.g2 = g2;

        switch (numeroDaCena) {
            case senhorEsqueleto: cenaSenhorEsqueleto(); break;
            case cenaFinal : cenaFinal(); break;

        }

    }

    public void  cenaSenhorEsqueleto(){
        
        if(faseDaCena == 0){
            painel.batalhaComChefeAtiva = true;

            //feche a porta de ferro
            for(int i =0; i < painel.Obj[1].length; i++){
                
                if(painel.Obj[painel.mapaAtual][i] == null){
                    painel.Obj[painel.mapaAtual][i] = new ObjPortaDeFerro(painel);
                    painel.Obj[painel.mapaAtual][i].mundoX = painel.tamanhoDoTile*25;
                    painel.Obj[painel.mapaAtual][i].mundoY = painel.tamanhoDoTile*28;
                    painel.Obj[painel.mapaAtual][i].temp = true;//para que possa excluir a porta depois que derrotar o chefe
                    painel.iniciarEfeitoSonoro(21);
                    break;
                }
            }
            // procurar uma vaga para o manequim
            for(int i = 0; i < painel.npc[1].length; i++){
                
                if(painel.npc[painel.mapaAtual][i] == null){

                    painel.npc[painel.mapaAtual][i] = new jogadorManequim(painel);
                    painel.npc[painel.mapaAtual][i].mundoX = painel.jogador.mundoX;
                    painel.npc[painel.mapaAtual][i].mundoY = painel.jogador.mundoY;
                    painel.npc[painel.mapaAtual][i].direcao = painel.jogador.direcao;
                    break;
                }
            }

            painel.jogador.desenho = false;
            faseDaCena++;
        }
        if(faseDaCena == 1){

            painel.jogador.mundoY -= 2;

            if(painel.jogador.mundoY < painel.tamanhoDoTile*16){
                faseDaCena++;
            }
        }
        if(faseDaCena == 2){
            //procure o chefe

            for(int i = 0; i < painel.inimigo[1].length; i++){

                if(painel.inimigo[painel.mapaAtual][i] != null && 
                    painel.inimigo[painel.mapaAtual][i].nome == SenhorEsqueleto.nomeBoss){

                    painel.inimigo[painel.mapaAtual][i].dormir = false;
                    painel.interfaceDoUsuario.npc = painel.inimigo[painel.mapaAtual][i];
                    faseDaCena ++;
                    break;
                }
            }
        } 
        if(faseDaCena == 3){
            //the boss speaks
            painel.interfaceDoUsuario.desenharDialogoNaTela();
        }
        if(faseDaCena == 4){
            //retunr to the player
            for(int i = 0; i < painel.npc[1].length; i++){
                
                if(painel.npc[painel.mapaAtual][i] != null &&
                    painel.npc[painel.mapaAtual][i].nome.equals(jogadorManequim.nomeNpc)){
                    //restore the player position
                    painel.jogador.mundoX = painel.npc[painel.mapaAtual][i].mundoX;
                    painel.jogador.mundoY = painel.npc[painel.mapaAtual][i].mundoY;

                    //deleta o manequim
                    painel.npc[painel.mapaAtual][i] = null;
                    break;
                }

            }
            //start drawing the player
            painel.jogador.desenho = true;

            //reset
            numeroDaCena = NA;
            faseDaCena = 0;
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
            painel.pararMusica();
            painel.iniciarMusica(22);
        }

    }

    public void cenaFinal(){

        if(faseDaCena == 0){
            painel.pararMusica();
            painel.interfaceDoUsuario.npc = new ObjDiamente(painel);
            faseDaCena++;
        }
        if(faseDaCena == 1){
            //display dialogo
            painel.interfaceDoUsuario.desenharDialogoNaTela();
        }
        if(faseDaCena == 2){
            //play the fanfare
            painel.iniciarEfeitoSonoro(4);
            faseDaCena++;
        }
        if(faseDaCena == 3){
            //wait until the sound effect ends
            if(contadorAlcancado(300) == true){
                faseDaCena++;
            }
        }
        if(faseDaCena == 4){

            //the screen gets darks
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            desenharFundoPreto(alpha);

            if(alpha == 1f){
                alpha = 0;
                faseDaCena++;
            }

        }
        if(faseDaCena == 5){
            desenharFundoPreto(1f);
            //the screen gets darks
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }

            String texto = "Após a batalha feroz com o Senhor Esqueleto, \n"
                            + "o menino azul finalmente encontrou o tesouro lendário. \n"
                            + "Mas este não é o fim de sua jornada. \n"
                            + "A aventura do menino azul apenas começou.";
            
            desenharString(alpha, 38f, 200, texto, 70);

            if(contadorAlcancado(600) == true){
                painel.iniciarMusica(0);
                faseDaCena++;
            }
    }
        if(faseDaCena == 6){
            desenharFundoPreto(1f);
            desenharString(1f, 120f, painel.alturaTela/2, "Nome do jogo", 40);
            
            
            if(contadorAlcancado(480) == true){
                faseDaCena++;
            }
        
        }
        if(faseDaCena == 7){
            desenharFundoPreto(1f);

            y = painel.alturaTela/2;
            desenharString(1f, 38f, y, creditosFinais, 40);
            
            if(contadorAlcancado(480) == true){
                faseDaCena++;
            }
        }
        if(faseDaCena == 8){
            desenharFundoPreto(1f);

            //scrolling the credit
            y--;
            desenharString(1f, 38f, y, creditosFinais, 40);
        }

    }
    

    public boolean contadorAlcancado(int alvo){
       
        boolean contadorAlcancado = false;

        contador++;
        if(contador > alvo){
            contadorAlcancado = true;
            contador = 0;
        }
        return contadorAlcancado;
    }

    public void desenharFundoPreto(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void desenharString(float alpha, float tamanhoFonte, int y, String texto, int linhaAltura){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(tamanhoFonte));

        for(String linha: texto.split("\n")){
            int x = painel.interfaceDoUsuario.obterTextoXCentralizado(linha);
            g2.drawString(linha, x, y);
            y += linhaAltura;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        
    }
}
