package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import entidade.jogadorManequim;
import main.inimigo.chefao.SenhorEsqueleto;
import main.inimigo.chefao.SenhorEsqueleto2;
import main.inimigo.chefao.SenhorEsqueleto3;
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
    public final int cenaInicial = 3;
    public final int cenaMorte = 4;
    
    String mensagemMorteAtual;


    public void desenhar(Graphics2D g2){
        this.g2 = g2;

        switch (numeroDaCena) {
            case cenaMorte: cenaMorte(); break;

            case senhorEsqueleto: cenaSenhorEsqueleto(); break;
            case cenaFinal : cenaFinal(); break;
            case cenaInicial: cenaInicial(); break;
            
        }

    }


    


    public GerenciadorDeCutscene(PainelDoJogo painel){
        this.painel = painel;

        creditosFinais = "Programação/Musica/Arte\n"
        +"Rafaela\n"
        +"Nancy\n"
        +"Isabela\n"
        +"\n\n\n\n\n\n\n\n\n\n\n\n\n"
        +"Agradecimentos especiais\n"
        +"Isabela\n"
        +"Vinicius\n"
        +"Augusto\n"
        +"Fabio\n"
        +"Glauber\n"
        +"Zady\n"
        +"Sovat\n"
        +"Alguém\n"
        +"Alguém\n\n\n\n\n\n"
        +"Obrigado por jogar!";
    }

    

    public void cenaInicial() {

        if (faseDaCena == 0) {
            alpha = 0f;
            contador = 0;
            faseDaCena++;
        }

        if (faseDaCena == 1) {
            alpha += 0.01f;
            if (alpha > 1f) alpha = 1f;

            String texto = "Em um mundo esquecido pelas luzes,\n"
                        + "um menino desperta no coração da escuridão.\n"
                        + "Sua jornada pela última chama está prestes a começar...";

            desenharFundoPreto(1f);
            desenharString(alpha, 36f, painel.alturaTela / 2 - 100, texto, 45);

            if (contadorAlcancado(800)) {
                faseDaCena++;
                alpha = 0f; 
                contador = 0;
            }
        }

        if (faseDaCena == 2) {
            float velocidadeFade = 0.01f;

            if (contador == 0) {
                alpha += velocidadeFade;
                alpha = Math.min(alpha, 1f); 
                desenharFundoPreto(alpha);

                if (alpha >= 1f) {
                    contador = 1; 
                }
            } 
            else if (contador == 1) {
                alpha -= velocidadeFade;
                alpha = Math.max(alpha, 0f); 
                desenharFundoPreto(alpha);

                if (alpha <= 0f) {
                    faseDaCena++;
                    alpha = 0f;
                    contador = 0;
                }
            }
        }

        if (faseDaCena == 3) {
            numeroDaCena = NA;
            faseDaCena = 0;
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
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
                    //painel.inimigo[painel.mapaAtual][i].nome == SenhorEsqueleto.nomeBoss){
                    painel.inimigo[painel.mapaAtual][i].nome.equals(SenhorEsqueleto.nomeBoss) ||
                    painel.inimigo[painel.mapaAtual][i].nome.equals(SenhorEsqueleto2.nomeBoss) ||
                    painel.inimigo[painel.mapaAtual][i].nome.equals(SenhorEsqueleto3.nomeBoss) ){

                    painel.inimigo[painel.mapaAtual][i].dormir = false;
                    painel.interfaceDoUsuario.npc = painel.inimigo[painel.mapaAtual][i];
                    faseDaCena ++;
                    break;
                }
            }
        } 
        if(faseDaCena == 3){
            //o chefe fala
            painel.interfaceDoUsuario.desenharDialogoNaTela();
        }
        if(faseDaCena == 4){
            //retornar ao jogador
            for(int i = 0; i < painel.npc[1].length; i++){
                
                if(painel.npc[painel.mapaAtual][i] != null &&
                    painel.npc[painel.mapaAtual][i].nome.equals(jogadorManequim.nomeNpc)){
                    //restaurar a posição do jogador
                    painel.jogador.mundoX = painel.npc[painel.mapaAtual][i].mundoX;
                    painel.jogador.mundoY = painel.npc[painel.mapaAtual][i].mundoY;

                    //deleta o manequim
                    painel.npc[painel.mapaAtual][i] = null;
                    break;
                }

            }
            //comece a desenhar o jogador
            painel.jogador.desenho = true;

            //reiniciar
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
            //exibir diálogo
            painel.interfaceDoUsuario.desenharDialogoNaTela();
        }
        if(faseDaCena == 2){
            //tocar a fanfarra
            painel.iniciarEfeitoSonoro(4);
            faseDaCena++;
        }
        if(faseDaCena == 3){
            //Aguarde até que o efeito sonoro termine.
            if(contadorAlcancado(300) == true){
                faseDaCena++;
            }
        }
        if(faseDaCena == 4){

            //a tela fica escura
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
            //a tela fica escura
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }

            //alterar texto
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
            desenharString(1f, 120f, painel.alturaTela/2, "A Última Luz", 40);
            
            
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

            //rolando o créditos para cima
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


  
    public void cenaMorte() {

        String[] mensagensMorte = {
            "Dica: Observe os padrões dos inimigos antes de atacar.",
            "Item: Poções podem virar o jogo em batalhas longas.",
            "Dica: Fugir também é uma estratégia válida.",
            "Descrição: Relíquias antigas escondem poderes esquecidos.",
            "Dica: Explorar o mapa pode revelar atalhos e segredos."
        };
        

        if (faseDaCena == 0) {

            int i = (int)(Math.random() * mensagensMorte.length);
            mensagemMorteAtual = mensagensMorte[i];

            alpha = 0f;
            contador = 0;
            faseDaCena++;
        }

        //
        if (faseDaCena == 1) {

            alpha += 0.02f;
            if (alpha > 1f) alpha = 1f;

            desenharFundoPreto(alpha);

            desenharString(alpha, 28f,painel.alturaTela / 2, mensagemMorteAtual, 40 );

            if (alpha >= 1f) {
                contador = 0;
                faseDaCena++;
            }
        }

        //tempo de leitura
        if (faseDaCena == 2) {

            desenharFundoPreto(1f);

            desenharString(1f, 28f, painel.alturaTela / 2, mensagemMorteAtual, 40);

            // 600 frames = 10 segundos a 60 FPS
            if (contadorAlcancado(600)) {
                faseDaCena++;
            }
        }

        //
        if (faseDaCena == 3) {

            alpha -= 0.02f;
            if (alpha < 0f) alpha = 0f;

            desenharFundoPreto(alpha);

            desenharString(alpha, 28f, painel.alturaTela / 2, mensagemMorteAtual, 40 );

            if (alpha <= 0f) {
                faseDaCena++;
            }
        }

        //retorno ao jogo
        if (faseDaCena == 4) {

            painel.jogador.morto = false;
            painel.jogador.invencivel = true;
            painel.jogador.invencivelContador = 0;

            // reset da cutscene
            numeroDaCena = NA;
            faseDaCena = 0;
            contador = 0;
            alpha = 0f;

            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
        }
    }

}
