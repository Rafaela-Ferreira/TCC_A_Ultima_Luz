package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entidade.Entidade;
import objeto.ObjCoracao;
import objeto.ObjMana;



//classe para mostrar mensagens na tela, como pontuação, tempo, ícones de itens, etc.
public class InterfaceDoUsuario {
    
    PainelDoJogo painel;
    Font maruMonica, purisaB;
    Graphics2D g2;
    BufferedImage vidaMaxima, vidaMeio, vidaBranco, cristalCompleto, cristalVazio;
    public boolean mensagemAtiva = false;
    //public String mensagem = "";
    //int contadorDeMensagens = 0;
    ArrayList<String> mensagens = new ArrayList<>();
    ArrayList<Integer> contadorDeMensagens = new ArrayList<>();

    public boolean finalDeJogo = false;
    public String dialogoAtual = "";
    public int numeroDoComando = 0;
    public int estadoDeRolagemTitulo = 0;

    //inventario
    public int espacoColuna = 0;
    public int espacoLinha = 0;
    int subEstado = 0;
    


    public InterfaceDoUsuario(PainelDoJogo painel) {
        
        this.painel = painel;

        try {
            InputStream entrada = getClass().getResourceAsStream("/fontes/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, entrada);
            entrada = getClass().getResourceAsStream("/fontes/PurisaBold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, entrada);

        } catch (FontFormatException e) {
            e.printStackTrace();
        }catch(IOException e){

        }

        //criar HUD Heads-Up Display 
        //é uma interface que exibe informações relevantes para o jogador, 
        //como vida, munição, mapa, etc., diretamente na tela, sobrepondo-se à imagem do jogo
       
        Entidade coracao = new ObjCoracao(painel);
        vidaMaxima = coracao.imagem;
        vidaMeio = coracao.imagem2;
        vidaBranco = coracao.imagem3;
        Entidade cristal = new ObjMana(painel);
        cristalCompleto = cristal.imagem;
        cristalVazio = cristal.imagem2;
        
    }
    public void adicionarMensagem(String texto) {
        mensagens.add(texto);
        contadorDeMensagens.add(0);
    }

    public void desenhar(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        //Titulo
        if(painel.estadoDoJogo == painel.tituloEstado){
            desenharTituloNaTela();
        }

        //INICIAR
        if(painel.estadoDoJogo == painel.iniciarEstadoDoJogo){
            desenharVidaDoJogador();
            desenharMensagem();
        }
        //PAUSAR
        if(painel.estadoDoJogo == painel.pausarEstadoDoJogo){
            desenharVidaDoJogador();
            desenharTelaDePausa();
        }

        //DIALOGO
        if(painel.estadoDoJogo == painel.estadoDoDialogo){
            desenharVidaDoJogador();
            desenharDialogoNaTela();
        }

        //estado do personagem
        if(painel.estadoDoJogo == painel.estadoPersonagem){
            desenharPersonagemTela();
            desenhaInventario();
        }

        //estado de opção
        if(painel.estadoDoJogo == painel.estadoOpcoes){
            desenharTelaDeOpcoes();
        }

        //estado de game over
        if(painel.estadoDoJogo == painel.estadoGameOver){
            desenharTelaDeGameOver();
        }
        
    }

    public void desenharTelaDeGameOver(){
        g2.setColor(new Color(0, 0,0, 150));
        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);

        int x, y;
        String texto;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        
        texto = "Game Over";
        //sombra
        g2.setColor(Color.black);
        x = obterTextoXCentralizado(texto);
        y = painel.tamanhoDoTile*4;
        g2.drawString(texto, x, y);

        //main
        g2.setColor(Color.white);
        g2.drawString(texto, x-4, y-4);

        //reiniciar
        g2.setFont(g2.getFont().deriveFont(50F));
        texto = "Reiniciar";
        x = obterTextoXCentralizado(texto);
        y += painel.tamanhoDoTile*4;
        g2.drawString(texto, x, y);
        if(numeroDoComando == 0){
            g2.drawString(">", x-40, y);
        }

        //voltar a tela inicial
        texto = "Sair";
        x = obterTextoXCentralizado(texto);
        y += 55;
        g2.drawString(texto, x, y);
        if(numeroDoComando == 1){
            g2.drawString(">", x-40, y);
        }
    }

    public void desenharVidaDoJogador(){

        //painel.jogador.vida = 4;


        int x = painel.tamanhoDoTile/2;
        int y = painel.tamanhoDoTile/2;
        int i = 0;

        //desenhar vida maxima
        while(i < painel.jogador.vidaMaxima/2){
            g2.drawImage(vidaBranco, x, y, null);
            i++;
            x += painel.tamanhoDoTile;
        }

        //reiniciar
        x = painel.tamanhoDoTile/2;
        y = painel.tamanhoDoTile/2;
        i = 0;

        //desenhar vida Atual
        while(i < painel.jogador.vida){
            g2.drawImage(vidaMeio, x, y, null);
            i++;
            if(i < painel.jogador.vida){
                g2.drawImage(vidaMaxima, x, y, null);
            }
            i++;
            x += painel.tamanhoDoTile;

        }
        //desenhar mana maxima
        x = (painel.tamanhoDoTile/2)-5;
        y = (int)(painel.tamanhoDoTile*1.5);
        i = 0;
        while(i < painel.jogador.manaMaxima){
            g2.drawImage(cristalVazio, x, y, null);
            i++;
            x +=35;
        }

        
        //desenhar mana vazia
        x = (painel.tamanhoDoTile/2)-5;
        y = (int)(painel.tamanhoDoTile*1.5);
        i = 0;
        while(i < painel.jogador.mana){
            g2.drawImage(cristalCompleto, x, y, null);
            i++;
            x +=35;
        }
    }

    public void desenharMensagem(){
        int mensagemX = painel.tamanhoDoTile;
        int mensagemY = painel.tamanhoDoTile*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        
        for(int i = 0; i < mensagens.size(); i++){
            
            if(mensagens.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(mensagens.get(i), mensagemX+2, mensagemY+2);
                g2.setColor(Color.white);
                g2.drawString(mensagens.get(i), mensagemX, mensagemY);
                
                int contador = contadorDeMensagens.get(i) + 1;
                contadorDeMensagens.set(i, contador); 
                mensagemY += 50;

                if(contadorDeMensagens.get(i) > 180){
                    mensagens.remove(i);
                    contadorDeMensagens.remove(i);

                }
                
            }
        }
    }

    public void desenharTituloNaTela(){
        
        if( estadoDeRolagemTitulo == 0){

            g2.setColor(new Color(0, 0,0));
            g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);

            //Nome do titulo
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String texto = "-- Protótipo --";
            int x = obterTextoXCentralizado(texto);
            int y = painel.tamanhoDoTile*3;
            
            //sombra
            g2.setColor(Color.gray);
            g2.drawString(texto, x+5, y+5);


            //cor principal
            g2.setColor(Color.WHITE);
            g2.drawString(texto, x, y);


            //desenhar o jogador
            x = painel.larguraTela/2 - (painel.tamanhoDoTile*2) /2;
            y += painel.tamanhoDoTile*2;
            g2.drawImage(painel.jogador.baixo1, x, y, painel.tamanhoDoTile*2, painel.tamanhoDoTile*2, null);
        
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            texto = "NOVO JOGO";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile*3.5;
            g2.drawString(texto, x, y);
            if( numeroDoComando == 0){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }

            texto = "CARREGAR JOGO";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile;
            g2.drawString(texto, x, y);
            if( numeroDoComando == 1){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }

            texto = "SAIR";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile;
            g2.drawString(texto, x, y);
            if( numeroDoComando == 2){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }
        
        }
        else if(estadoDeRolagemTitulo == 1){
            // pintar o fundo 
            g2.setColor(new Color(0, 0,0));
            g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);


            //selecionar classe
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));

            String texto = "Selecione uma classe!";
            int x = obterTextoXCentralizado(texto);
            int y = painel.tamanhoDoTile*3;
            g2.drawString(texto, x, y);

            texto = "Lutador";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile*3;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 0){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }

            texto = "Ladrão";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 1){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }

            texto = "Feiticeiro";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 2){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }

            texto = "Voltar";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile*2;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 3){
                g2.drawString(">", x-painel.tamanhoDoTile, y);
            }
        }

    }

    public void desenharDialogoNaTela(){
        //janela
        int x = painel.tamanhoDoTile*2;
        int y = painel.tamanhoDoTile/2;
        int largura = painel.larguraTela - (painel.tamanhoDoTile *4);
        int altura = painel.tamanhoDoTile *4;

        desenharSubJanela(x, y, largura, altura);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += painel.tamanhoDoTile;
        y += painel.tamanhoDoTile;

        // desenha cada índice do array dialogo[]
        
        for(String linha : dialogoAtual.split("\n")){
            g2.drawString(linha, x, y);
            y += 40;
        }
        
        
    }
    public void desenharPersonagemTela(){
        //criar frames
        final int frameX = painel.tamanhoDoTile *2;
        final int frameY = painel.tamanhoDoTile;
        final int frameLargura = painel.tamanhoDoTile*5;
        final int frameAltura = painel.tamanhoDoTile*10;

        desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

        //texto
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textoX = frameX + 20;
        int textoY = frameY  + painel.tamanhoDoTile;
        final int linhaAltura = 35;

        //nomes
        g2.drawString("Nivel", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Vida", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Mana", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Força", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Destreza", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Ataque", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Defesa", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Exp", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Proximo nivel", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Moeda", textoX, textoY);
        textoY += linhaAltura +10;
        g2.drawString("Arma", textoX, textoY);
        textoY += linhaAltura +15;
        g2.drawString("Escudo", textoX, textoY);
        textoY += linhaAltura ;


        //valores
        int bordaX = (frameX + frameLargura) - 30;

        //reset texto
        textoY = frameY + painel.tamanhoDoTile;
        String valor;

        valor = String.valueOf(painel.jogador.nivel);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.vida + "/" + painel.jogador.vidaMaxima);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.mana+ "/" + painel.jogador.manaMaxima);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.forca);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.destreza);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.ataque);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.defesa);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.exp);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.proximoNivelExp);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.moeda);
        textoX = obterTextoXEsquerda(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        g2.drawImage(painel.jogador.armaAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-24, null);
        textoY += painel.tamanhoDoTile;
        g2.drawImage(painel.jogador.EscudoAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-24, null);
   
    }
    public void desenhaInventario(){
        int frameX = painel.tamanhoDoTile*12;
        int frameY = painel.tamanhoDoTile;
        int frameLargura = painel.tamanhoDoTile*6;
        int frameAltura = painel.tamanhoDoTile*5;

        desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

        //espaços
        final int espacoInicialX = frameX + 20;
        final int espacoInicialY = frameY + 20;
        int espacoX = espacoInicialX;
        int espacoY = espacoInicialY;
        int tamanhoEspaco = painel.tamanhoDoTile+3;

        //desenhar itens
        for(int i = 0; i < painel.jogador.inventario.size(); i++){
            //equipar arma (cursor)
            if(painel.jogador.inventario.get(i) == painel.jogador.armaAtual ||
               painel.jogador.inventario.get(i) == painel.jogador.EscudoAtual){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(espacoX, espacoY, painel.tamanhoDoTile, painel.tamanhoDoTile, 10, 10);

            }
               
            g2.drawImage(painel.jogador.inventario.get(i).baixo1, espacoX, espacoY, null);

            espacoX += tamanhoEspaco;
            if( i == 4 || i == 9 || i == 14){
                espacoX = espacoInicialX;
                espacoY += tamanhoEspaco;
            }
           

        }

        //cursor
        int cursorX = espacoInicialX + (tamanhoEspaco * espacoColuna);
        int cursorY = espacoInicialY + (tamanhoEspaco * espacoLinha);
        int cursorLargura = painel.tamanhoDoTile;
        int cursorAltura = painel.tamanhoDoTile;

        //desenhar cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorLargura, cursorAltura , 10, 10);

        //frame de descrição
        int DframeX = frameX;
        int DframeY = frameY + frameAltura;
        int DframeLargura = frameLargura;
        int DframeAltura = painel.tamanhoDoTile*3;
        
        
        //desehar a descrição do item
        int tentoX = DframeX + 20;
        int textoY = DframeY + 40;
        g2.setFont(g2.getFont().deriveFont(28F));

        //pegar o item selecionado
        int itemSelecionado = pegarItemSelecionado();
        if(itemSelecionado < painel.jogador.inventario.size()){
            desenharSubJanela(DframeX, DframeY, DframeLargura, DframeAltura);
            
            for(String linha : painel.jogador.inventario.get(itemSelecionado).descricao.split("\n")){
                g2.drawString(linha, tentoX, textoY);
                textoY += 32;
            }
            

        }

    }
    public void desenharTelaDeOpcoes(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub janela
        int frameX = painel.tamanhoDoTile*6;
        int frameY = painel.tamanhoDoTile;
        int frameLargura = painel.tamanhoDoTile*10;
        int frameAltura = painel.tamanhoDoTile*10;

        desenharSubJanela(frameX, frameY, frameAltura, frameLargura);

        switch (subEstado) {
            case 0: opcao_topo(frameX, frameY); break;
            case 1: opcao_notificacaoTelaCheia(frameX, frameY); break;
            case 2: opcao_controle(frameX, frameY); break;
            case 3: opcao_sairConfirmarJogo(frameX, frameY); break;
        }

        painel.teclado.precionarEnter = false;
    }

    public void opcao_topo(int frameX, int frameY){
        int textoX;
        int textoY;

        //titulo
        String texto = "Opções";
        textoX = obterTextoXCentralizado(texto);
        textoY = frameY + painel.tamanhoDoTile;
        g2.drawString(texto, textoX, textoY);

        //tela cheia on/off
        textoX = frameX + painel.tamanhoDoTile;
        textoY += painel.tamanhoDoTile*2;
        g2.drawString("Tala cheia", textoX, textoY);
        if(numeroDoComando == 0){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                if(painel.telaCheiaAtiva == false){
                    painel.telaCheiaAtiva = true;
                }
                else if(painel.telaCheiaAtiva == true){
                    painel.telaCheiaAtiva = false;
                }
                subEstado = 1;
            }
        }

        //Música
        textoY += painel.tamanhoDoTile;
        g2.drawString("Musica", textoX, textoY);
        if(numeroDoComando == 1){
            g2.drawString(">", textoX-25, textoY);
        }

        //Efeito sonoro
        textoY += painel.tamanhoDoTile;
        g2.drawString("Efeito sonoro", textoX, textoY);
        if(numeroDoComando == 2){
            g2.drawString(">", textoX-25, textoY);
        }

        //Controles
        textoY += painel.tamanhoDoTile;
        g2.drawString("Controle", textoX, textoY);
        if(numeroDoComando == 3){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                subEstado = 2;
                numeroDoComando = 0;
            }
        }

        //Sair do jogo
        textoY += painel.tamanhoDoTile;
        g2.drawString("Sair do jogo", textoX, textoY);
        if(numeroDoComando == 4){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                subEstado = 3;
                numeroDoComando = 0;
            }
        }

        //Voltar ao jogo
        textoY += painel.tamanhoDoTile*2;
        g2.drawString("Voltar", textoX, textoY);
        if(numeroDoComando == 5){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
                numeroDoComando =0;
            }
        }

        //tela cheia check box
        textoX = frameX + (int) (painel.tamanhoDoTile*4.5);
        textoY = frameY + painel.tamanhoDoTile*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textoX, textoY, 24, 24);
        if(painel.telaCheiaAtiva == true){
            g2.fillRect(textoX, textoY, 24, 24);
        }


        //Volume da musica
        textoY += painel.tamanhoDoTile;
        g2.drawRect(textoX, textoY, 120, 24);
        int volumeLargura = 24 * painel.musica.escalaDoVolume;
        g2.fillRect(textoX, textoY, volumeLargura, 24); 

        //Volume do efeito sonoro
        textoY += painel.tamanhoDoTile;
        g2.drawRect(textoX, textoY, 120, 24);
        volumeLargura = 24 * painel.efeitoSonoro.escalaDoVolume;
        g2.fillRect(textoX, textoY, volumeLargura, 24);

        painel.config.salvarConfiguracoes();

    }

    public void opcao_notificacaoTelaCheia(int frameX, int frameY){

        int textoX = frameX + painel.tamanhoDoTile;
        int textoY = frameY + painel.tamanhoDoTile*3;

        dialogoAtual = "A alteração entrará em \nvigor após reiniciar o jogo";

        for(String linha: dialogoAtual.split("\n")){
            g2.drawString(linha, textoX, textoY);
            textoY += 40;
        }

        //voltar
        textoY = frameY + painel.tamanhoDoTile*9;
        g2.drawString("Voltar", textoX, textoY);
        if(numeroDoComando == 0){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                subEstado = 0;
            }
        }

    }

    public void opcao_controle(int frameX, int frameY){
        int textoX;
        int textoY;

        //titulo
        String texto = "Controle";
        textoX = obterTextoXCentralizado(texto);
        textoY = frameY + painel.tamanhoDoTile;
        g2.drawString(texto, textoX, textoY);

        textoX = frameX + painel.tamanhoDoTile;
        textoY += painel.tamanhoDoTile;
        g2.drawString("Move", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Confirm/ataque", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("atirar/cast", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("chacarter screen", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Pause", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Opções", textoX, textoY); textoY+=painel.tamanhoDoTile;


        textoX = frameX + painel.tamanhoDoTile*6;
        textoY = frameY + painel.tamanhoDoTile*2;
        g2.drawString("WASD", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("ENTER", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("F", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("C", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("ESC", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("T", textoX, textoY); textoY+=painel.tamanhoDoTile;

        //Voltar
        textoX = frameX + painel.tamanhoDoTile;
        textoY = frameY + painel.tamanhoDoTile*9;
        g2.drawString("Voltar", textoX, textoY);
        if(numeroDoComando == 0){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                subEstado = 0;
                numeroDoComando =3;
            }
        }

    }
    public void opcao_sairConfirmarJogo(int frameX, int frameY){
        int textoX = frameX + painel.tamanhoDoTile;
        int textoY = frameY + painel.tamanhoDoTile*3;

        dialogoAtual = "Sair do jogo e retornar a tela inicial?";

        for(String linha: dialogoAtual.split("\n")){
            g2.drawString(linha, textoX, textoY);
            textoX += 40;
        }

        //sim
        String texto = "Sim";
        textoX = obterTextoXCentralizado(texto);
        textoY  += painel.tamanhoDoTile*3;
        g2.drawString(texto, textoX, textoY);
        if(numeroDoComando == 0){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                subEstado = 0;
                painel.interfaceDoUsuario.estadoDeRolagemTitulo = 0;
                painel.estadoDoJogo = painel.tituloEstado;
            }
        }

        //não
        texto = "Não";
        textoX = obterTextoXCentralizado(texto);
        textoY  += painel.tamanhoDoTile;
        g2.drawString(texto, textoX, textoY);

        if(numeroDoComando == 1){
            g2.drawString(">", textoX-25, textoY);
            if(painel.teclado.precionarEnter == true){
                subEstado = 0;
                numeroDoComando = 4;
            }
        }
    }


    public int pegarItemSelecionado(){
        int itemSelecionado = espacoColuna + (espacoLinha * 5);
        return itemSelecionado;
    }

    
    public void desenharSubJanela(int x, int y, int largura, int altura){
        
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, largura, altura, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));; //5 pixels
        g2.drawRoundRect(x+5, y+5, largura-10, altura-10, 25, 25);
    }

    public void desenharTelaDePausa(){
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String texto = "Pausado";

        int x = obterTextoXCentralizado(texto);

        int y = painel.alturaTela/2;

        g2.drawString(texto, x, y);
    }

    public int obterTextoXCentralizado(String texto){
        int length = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = painel.larguraTela / 2 - length / 2;
        return x;
    }

    public int obterTextoXEsquerda(String texto, int  bordaX){
        int length = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = bordaX - length;
        return x;
    }
    
    
   
}
