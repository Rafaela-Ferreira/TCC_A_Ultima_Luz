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
import objeto.ObjMoedaBronze;



//classe para mostrar mensagens na tela, como pontuação, tempo, ícones de itens, etc.
public class InterfaceDoUsuario {
    
    PainelDoJogo painel;
    public Font maruMonica, purisaB;
    Graphics2D g2;
    BufferedImage vidaMaxima, vidaMeio, vidaBranco, cristalCompleto, cristalVazio, moeda;
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
    public int jogadorEspacoColuna = 0;
    public int jogadorEspacoLinha = 0;
    public int npcEspacoColuna = 0;
    public int npcEspacoLinha = 0;
    int subEstado = 0;
    int contador = 0;
    public Entidade npc;
    int indicePersonagem = 0;
    String combinandoTexto = "";



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

        Entidade moedaDeBronze = new ObjMoedaBronze(painel);
        moeda = moedaDeBronze.baixo1;
        
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

        //Estado jogador
        if(painel.estadoDoJogo == painel.iniciarEstadoDoJogo){
            desenharVidaDoJogador(); //mudar para um barra, igual a do inimigo
            desenhaVidaDoInimigo();
            desenharMensagem();
        }
        //PAUSAR
        if(painel.estadoDoJogo == painel.pausarEstadoDoJogo){
            desenharVidaDoJogador();
            desenharTelaDePausa();
        }

        //DIALOGO
        if(painel.estadoDoJogo == painel.estadoDoDialogo){
            //desenharVidaDoJogador();
            desenharDialogoNaTela();
        }

        //estado do personagem
        if(painel.estadoDoJogo == painel.estadoPersonagem){
            desenharPersonagemTela();
            desenhaInventario( painel.jogador, true);
        }

        //estado de opção
        if(painel.estadoDoJogo == painel.estadoOpcoes){
            desenharTelaDeOpcoes();
        }

        //estado de game over
        if(painel.estadoDoJogo == painel.estadoGameOver){
            desenharTelaDeGameOver();
        }
        
        //estado de trasição
        if(painel.estadoDoJogo == painel.estadoDeTransicao){
            desenharTransicao();
        }

        //estado de troca
        if(painel.estadoDoJogo == painel.trocaDeEstado){
            desenharTelaDeTroca();
        }

        //estado de dormir 
        if(painel.estadoDoJogo == painel.estadoDormir){
            desenharTelaDeDormir();
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
        int tamanhoDoIcone = 32;
        int inicioDaManaX = (painel.tamanhoDoTile/2) -5;
        int inicioDaManaY = 0;

        //desenhar vida maxima
        while(i < painel.jogador.vidaMaxima/2){
            g2.drawImage(vidaBranco, x, y, tamanhoDoIcone, tamanhoDoIcone, null);
            i++;
            x += tamanhoDoIcone;
            inicioDaManaY = y + 32;

            if(i % 8 == 0){
                x = painel.tamanhoDoTile/2;
                y += tamanhoDoIcone;
            }
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

    public void desenhaVidaDoInimigo(){
        for(int i = 0; i < painel.inimigo[1].length; i++){
            Entidade inimigo = painel.inimigo[painel.mapaAtual][i];

            if(inimigo != null && inimigo.camera() == true){


                if(inimigo.barraDeVidaAtiva == true && inimigo.chefe == false){
                    
                    double umaEscala = (double)painel.tamanhoDoTile/inimigo.vidaMaxima;
                    double valorBarraDeVida = umaEscala * inimigo.vida; 

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(inimigo.getTelaX()-1, inimigo.getTelaY() - 16, painel.tamanhoDoTile+2, 12);

                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(inimigo.getTelaX(), inimigo.getTelaY() - 15, (int)valorBarraDeVida, 10);


                    inimigo.contadorBarraDeVida++;

                    if(inimigo.contadorBarraDeVida > 600){
                        inimigo.contadorBarraDeVida=0;
                       inimigo.barraDeVidaAtiva = false;
                    }
                }
                else if(inimigo.chefe == true){
                    double umaEscala = (double)painel.tamanhoDoTile*8/inimigo.vidaMaxima;
                    double valorBarraDeVida = umaEscala * inimigo.vida; 
                    
                    int x = painel.larguraTela/2 - painel.tamanhoDoTile*4;
                    int y = painel.tamanhoDoTile*10;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(x-1, y-1, painel.tamanhoDoTile * 8 +2, 22);

                    g2.setColor(new Color(255,0,30));
                    g2.fillRect( x, y, (int)valorBarraDeVida, 20);

                    //nome
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
                    g2.setColor(Color.white);
                    g2.drawString(inimigo.nome, x + 4 , y - 10);
                }
            }
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
        int x = painel.tamanhoDoTile*3;
        int y = painel.tamanhoDoTile/2;
        int largura = painel.larguraTela - (painel.tamanhoDoTile *6);
        int altura = painel.tamanhoDoTile *4;

        desenharSubJanela(x, y, largura, altura);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += painel.tamanhoDoTile;
        y += painel.tamanhoDoTile;

        if(npc.dialogo[npc.setDialogo][npc.indiceDoDialogo] != null){
            //dialogoAtual = npc.dialogo[npc.setDialogo][npc.indiceDoDialogo];

            char personagem[] = npc.dialogo[npc.setDialogo][npc.indiceDoDialogo].toCharArray();

            if(indicePersonagem < personagem.length){
                painel.iniciarEfeitoSonoro(17);
                String s = String.valueOf(personagem[indicePersonagem]);
                combinandoTexto = combinandoTexto + s;
                dialogoAtual = combinandoTexto;
                indicePersonagem++;
            }

            if(painel.teclado.precionarEnter == true){
                indicePersonagem = 0;
                combinandoTexto = "";

                if(painel.estadoDoJogo == painel.estadoDoDialogo || painel.estadoDoJogo == painel.estadoCutscene){
                    
                    npc.indiceDoDialogo++;
                    painel.teclado.precionarEnter = false;
                }
            }
        }
        else{
            npc.indiceDoDialogo = 0;

            if(painel.estadoDoJogo == painel.estadoDoDialogo){
                painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
            }
            if(painel.estadoDoJogo == painel.estadoCutscene){
                painel.gerenciadorDeCutscene.faseDaCena++;
            }
        }

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
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.vida + "/" + painel.jogador.vidaMaxima);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.mana+ "/" + painel.jogador.manaMaxima);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.forca);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.destreza);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.ataque);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.defesa);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.exp);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.proximoNivelExp);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.moeda);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        g2.drawImage(painel.jogador.armaAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-24, null);
        textoY += painel.tamanhoDoTile;
        g2.drawImage(painel.jogador.escudoAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-24, null);
   
    }
    public void desenhaInventario(Entidade entidade, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameLargura = 0;
        int frameAltura = 0;
        int espacoColuna = 0;
        int espacoLinha = 0;

        if(entidade == painel.jogador){

            frameX = painel.tamanhoDoTile*12;
            frameY = painel.tamanhoDoTile;
            frameLargura = painel.tamanhoDoTile*6;
            frameAltura = painel.tamanhoDoTile*5;

            espacoColuna = jogadorEspacoColuna;
            espacoLinha = jogadorEspacoLinha;

        }else{
            
            frameX = painel.tamanhoDoTile*2;
            frameY = painel.tamanhoDoTile;
            frameLargura = painel.tamanhoDoTile*6;
            frameAltura = painel.tamanhoDoTile*5;

            espacoColuna = npcEspacoColuna;
            espacoLinha = npcEspacoLinha;
        }
        

        desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

        //espaços
        final int espacoInicialX = frameX + 20;
        final int espacoInicialY = frameY + 20;
        int espacoX = espacoInicialX;
        int espacoY = espacoInicialY;
        int tamanhoEspaco = painel.tamanhoDoTile+3;

        //desenhar itens
        for(int i = 0; i < entidade.inventario.size(); i++){
            
            //equipar arma (cursor)
            if(entidade.inventario.get(i) == entidade.armaAtual ||
                    entidade.inventario.get(i) == entidade.escudoAtual ||
                    entidade.inventario.get(i) == entidade.luzAtual){
                
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(espacoX, espacoY, painel.tamanhoDoTile, painel.tamanhoDoTile, 10, 10);

            }
               
            g2.drawImage(entidade.inventario.get(i).baixo1, espacoX, espacoY, null);

            //display quantidade
            if(entidade == painel.jogador && entidade.inventario.get(i).quantidade > 1){
                g2.setFont(g2.getFont().deriveFont(32f));
                int quantidadeX;
                int quantidadeY;

                String s = "" + entidade.inventario.get(i).quantidade;
                quantidadeX = obterTextoXDireita(s, espacoX + 44);
                quantidadeY = espacoY + painel.tamanhoDoTile;


                //sombra
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, quantidadeX, quantidadeY);

                //numero
                g2.setColor(Color.white);
                g2.drawString(s, quantidadeX -3 , quantidadeY -3);
            }

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
        if(cursor == true){
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
            int itemSelecionado = pegarItemSelecionado(espacoColuna, espacoLinha);
            if(itemSelecionado < entidade.inventario.size()){
                desenharSubJanela(DframeX, DframeY, DframeLargura, DframeAltura);
                
                for(String linha : entidade.inventario.get(itemSelecionado).descricao.split("\n")){
                    g2.drawString(linha, tentoX, textoY);
                    textoY += 32;
                }
                
                //durabilidade - fazer uma verificação para atribuir a todos os objetos
                g2.drawString("Durabilidade: " + entidade.inventario.get(itemSelecionado).durabilidade, tentoX, textoY+20);
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
                painel.reiniciarJogo(true);
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

    public void desenharTransicao(){
        contador++;
        g2.setColor(new Color(0,0,0, contador*5));
        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);

        if(contador == 50){ //fazer a transição
            contador = 0;
            painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
            painel.mapaAtual = painel.mEventos.mapaTemporario;
            painel.jogador.mundoX = painel.tamanhoDoTile * painel.mEventos.colunaTemporaria;
            painel.jogador.mundoY = painel.tamanhoDoTile * painel.mEventos.linhaTemporaria;
            painel.mEventos.eventoAnteriorX = painel.jogador.mundoX;
            painel.mEventos.eventoAnteriorY = painel.jogador.mundoY;
            painel.alterarArea();
            
        }
    }

    public void desenharTelaDeTroca(){
        
        switch (subEstado) {
            case 0: selecionarTroca(); break;
            case 1: comprarTroca(); break;
            case 2: venderTroca(); break;
               
        }
        painel.teclado.precionarEnter = false;

    }
    public void selecionarTroca(){
        
        npc.setDialogo = 0;

        desenharDialogoNaTela();
        //desenhar janela
        int x = painel.tamanhoDoTile * 15;
        int y = painel.tamanhoDoTile * 4;
        int width = painel.tamanhoDoTile * 4;
        int height = (int)(painel.tamanhoDoTile * 3.5);
        desenharSubJanela(x, y, width, height);

        //desenha textos
        x += painel.tamanhoDoTile;
        y += painel.tamanhoDoTile;
        g2.drawString("Comprar", x, y);
        if(numeroDoComando == 0) {
            g2.drawString(">", x - 24, y);
            if(painel.teclado.precionarEnter == true){
                subEstado = 1;
            }
        }

        y += painel.tamanhoDoTile;
        g2.drawString("Vender", x, y);
        if(numeroDoComando == 1) {
            g2.drawString(">", x - 24, y);
            if(painel.teclado.precionarEnter == true){
                subEstado = 2;
            }
        }

        y += painel.tamanhoDoTile;
        g2.drawString("Sair", x, y);
        if(numeroDoComando == 2){
            g2.drawString(">", x-24, y);
            if(painel.teclado.precionarEnter == true){
                numeroDoComando = 0;
                npc.iniciarDialogo(npc, 1);
            }
        } 

    }
    public void comprarTroca(){
        //desentar inventario do player
        desenhaInventario(painel.jogador, false);

        //desentar inventario do NPC
        desenhaInventario(npc, true);

        //desenhar janela de dica
        int x = painel.tamanhoDoTile *2;
        int y = painel.tamanhoDoTile *9;
        int width = painel.tamanhoDoTile *6;
        int height = painel.tamanhoDoTile *2;
        desenharSubJanela(x, y, width, height);
        g2.drawString("[ESC] Voltar", x+24, y+60);

        //desenhar janela de moeda do jogador
        x = painel.tamanhoDoTile *12;
        y = painel.tamanhoDoTile *9;
        width = painel.tamanhoDoTile *6;
        height = painel.tamanhoDoTile *2;
        desenharSubJanela(x, y, width, height);
        g2.drawString("Moedas: " + painel.jogador.moeda , x+24, y+60);

        //desenhar preço
        int indeceItem = pegarItemSelecionado(npcEspacoColuna, npcEspacoLinha);
        if(indeceItem < npc.inventario.size()){
            x = (int)(painel.tamanhoDoTile*5.5);
            y = (int)(painel.tamanhoDoTile*5.5);
            width = (int)(painel.tamanhoDoTile*2.5);
            height = painel.tamanhoDoTile;
            desenharSubJanela(x, y, width, height);
            g2.drawImage(moeda, x+10, y+8, 32, 32,null);

            int preco = npc.inventario.get(indeceItem).preco;
            String texto = "" + preco;
            x = obterTextoXDireita(texto, painel.tamanhoDoTile*8-20);
            g2.drawString(texto, x, y+34);
        }

        //comprar item
        if(painel.teclado.precionarEnter == true){
            if(npc.inventario.get(indeceItem).preco > painel.jogador.moeda){
                subEstado = 0;
                npc.iniciarDialogo(npc, 2);
            }
            else{
                if(painel.jogador.podeObterItem(npc.inventario.get(indeceItem)) == true){
                    painel.jogador.moeda -= npc.inventario.get(indeceItem).preco;
                }
                else{
                   subEstado = 0;
                    npc.iniciarDialogo(npc, 3);
                }
            }
        }

    }
    public void venderTroca(){
        //desenhar o invetario do jogador
        desenhaInventario(painel.jogador, true);

        int x;
        int y;
        int width;
        int height;

        //desenhar janela de dica
        x = painel.tamanhoDoTile *2;
        y = painel.tamanhoDoTile *9;
        width = painel.tamanhoDoTile *6;
        height = painel.tamanhoDoTile *2;
        desenharSubJanela(x, y, width, height);
        g2.drawString("[ESC] Voltar", x+24, y+60);

        //desenhar janela de moeda do jogador
        x = painel.tamanhoDoTile *12;
        y = painel.tamanhoDoTile *9;
        width = painel.tamanhoDoTile *6;
        height = painel.tamanhoDoTile *2;
        desenharSubJanela(x, y, width, height);
        g2.drawString("Moedas: " + painel.jogador.moeda , x+24, y+60);

        //desenhar preço
        int indeceItem = pegarItemSelecionado(jogadorEspacoColuna, jogadorEspacoLinha);
        if(indeceItem < painel.jogador.inventario.size()){
            x = (int)(painel.tamanhoDoTile*15.5);
            y = (int)(painel.tamanhoDoTile*5.5);
            width = (int)(painel.tamanhoDoTile*2.5);
            height = painel.tamanhoDoTile;
            desenharSubJanela(x, y, width, height);
            g2.drawImage(moeda, x+10, y+8, 32, 32,null);

            int preco = painel.jogador.inventario.get(indeceItem).preco/2;
            String texto = "" + preco;
            x = obterTextoXDireita(texto, painel.tamanhoDoTile*18-20);
            g2.drawString(texto, x, y+34);

            //vender item
            if(painel.teclado.precionarEnter == true){
                if(painel.jogador.inventario.get(indeceItem) == painel.jogador.armaAtual ||
                    painel.jogador.inventario.get(indeceItem) == painel.jogador.escudoAtual)    {
                    
                    numeroDoComando = 0;
                    subEstado = 0;
                    npc.iniciarDialogo(npc, 4);
                }
                else{
                    if(painel.jogador.inventario.get(indeceItem).quantidade > 1){
                        painel.jogador.inventario.get(indeceItem).quantidade--;
                    }
                    else{
                        painel.jogador.inventario.remove(indeceItem);
                    }

                    painel.jogador.moeda += preco;
                    

                }
            }
        }

        

    }

    public void desenharTelaDeDormir(){
        contador++;

        if(contador < 120){
            painel.gerenciadorDeAmbientes.iluminacao.filtroAlpha += 0.01f;
            if(painel.gerenciadorDeAmbientes.iluminacao.filtroAlpha > 1f){
                painel.gerenciadorDeAmbientes.iluminacao.filtroAlpha = 1f;
            }
        }
        if(contador >= 120){
            painel.gerenciadorDeAmbientes.iluminacao.filtroAlpha -= 0.01f;
            if(painel.gerenciadorDeAmbientes.iluminacao.filtroAlpha <= 0f){
                painel.gerenciadorDeAmbientes.iluminacao.filtroAlpha = 0f;
                contador = 0;
                painel.gerenciadorDeAmbientes.iluminacao.estadoDia = painel.gerenciadorDeAmbientes.iluminacao.dia;
                painel.gerenciadorDeAmbientes.iluminacao.contadorDia = 0;
                painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
                painel.jogador.getImagem();
            }

        }
    }


    public int pegarItemSelecionado(int espacoColuna, int espacoLinha){
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

    public int obterTextoXDireita(String texto, int  bordaX){
        int length = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = bordaX - length;
        return x;
    }
    
    
   
}
