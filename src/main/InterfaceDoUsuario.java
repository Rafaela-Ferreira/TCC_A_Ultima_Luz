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



//classe para mostrar mensagens na tela, como pontuação, tempo, ícones de itens, etc.
public class InterfaceDoUsuario {
    
    PainelDoJogo painel;
    Font maruMonica, purisaB;
    Graphics2D g2;
    BufferedImage vidaMaxima, vidaMeio, vidaBranco;
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
        final int frameX = painel.tamanhoDoTile;
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
        textoY += linhaAltura +20;
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

        g2.drawImage(painel.jogador.armaAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-14, null);
        textoY += painel.tamanhoDoTile;
        g2.drawImage(painel.jogador.EscudoAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-14, null);
   
    }
    public void desenhaInventario(){
        int frameX = painel.tamanhoDoTile*9;
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
