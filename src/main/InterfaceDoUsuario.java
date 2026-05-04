package main;


import entidade.Entidade;
import entidade.GuardiaDaLuz;
import objeto.ObjEspadaNormal;
import objeto.ObjCatalisadorDeFogo;
import objeto.ObjCajadoNormal;
import objeto.ObjAdaga;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;



//classe para mostrar mensagens na tela, como pontuação, tempo, ícones de itens, etc.
public class InterfaceDoUsuario {
    
    PainelDoJogo painel;
    public Font maruMonica, purisaB;
    Graphics2D g2;

    // OBS: adicionado/modificado ----------------------------------------------------
    BufferedImage imagemPreview, vidaMaxima, vidaMeio,  alma; //almas
    int comandoAnterior = -1;

    // ---------------------------------------------------------------------

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
    int subEstadoGuardia = 0;
    int estadoSubirNivel = 0;
    int regiaoSelecionada = 0;
    int contador = 0;
    public Entidade npc;
    int indicePersonagem = 0;
    String combinandoTexto = "";
    private float alphaGameOver = 0f; // Controle da transparência (0 a 1)

    int faseGameOver = 0;
    int contadorGameOver = 0;

    public String textoMensagemPreDefinida = "";
   
    // subir de nivel
    int atributoEscolhido; 


    public InterfaceDoUsuario(PainelDoJogo painel) {
        
        this.painel = painel;

        try {
            InputStream entrada = getClass().getResourceAsStream("/res/fontes/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, entrada);
            entrada = getClass().getResourceAsStream("/res/fontes/PurisaBold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, entrada);

        } catch (FontFormatException e) {
            e.printStackTrace();
        }catch(IOException e){

        }
       
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
            desenharHUD_DoJogador(); 
            desenhaVidaDoInimigo();
            desenhaVidaDoNpcAliado();
            desenharMensagem();
        }
        //PAUSAR
        if(painel.estadoDoJogo == painel.pausarEstadoDoJogo){
            //desenharHUD_DoJogador();
           // desenharTelaDePausa();
        }

        //DIALOGO
        if(painel.estadoDoJogo == painel.estadoDoDialogo){
            //desenharHUD_DoJogador();
            desenharDialogoNaTela();
        }

        //estado do personagem
        if(painel.estadoDoJogo == painel.estadoPersonagem){
            desenharTelaStatusDoJogador();
            desenhaInventario( painel.jogador, true);
        }

        //estado de opção
        if(painel.estadoDoJogo == painel.estadoOpcoes){
            desenharTelaDeOpcoes();
        }

        //estado de game over
        if(painel.estadoDoJogo == painel.estadoGameOver 
            && painel.gerenciadorDeCutscene.numeroDaCena == painel.gerenciadorDeCutscene.NA){
                desenharTelaDeGameOver();
        }
        
        //estado de trasição
        if(painel.estadoDoJogo == painel.estadoDeTransicao){
            desenharTransicao();
        }

        //estado de troca
        if(painel.estadoDoJogo == painel.trocaDeEstado){
            if (painel.interfaceDoUsuario.npc instanceof GuardiaDaLuz) {
                desenharTelaDeTrocaGuardia(); // Só a guardiã
            } else {
                desenharTelaDeTroca(); // Comerciante ou outros NPCs
            }
        }

        //estado de dormir 
        if(painel.estadoDoJogo == painel.estadoDormir){
            desenharTelaDeDormir();
        }

        // estado Viagem Rápida
        if (painel.estadoDoJogo == painel.estadoViagemRapida) {
            desenharTelaViagemRapida();
        }

        // estado de mensagem pré-definida - dicas
        if(painel.interfaceDoUsuario.mensagemAtiva){
            desenharMensagemPreDefinia();
        }


        
    }

    public void desenharMensagemPreDefinia(){

        g2.setFont(g2.getFont().deriveFont(24F));
        g2.setColor(Color.white);

        int frameX = painel.tamanhoDoTile * 2;
        int frameY = painel.tamanhoDoTile * 8;
        int frameLargura = painel.tamanhoDoTile * 10;
        int frameAltura = painel.tamanhoDoTile * 2;

        desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

        int textoX = frameX + painel.tamanhoDoTile;
        int textoY = frameY + painel.tamanhoDoTile;

        for(String linha : textoMensagemPreDefinida.split("\n")){
            g2.drawString(linha, textoX, textoY);
            textoY += 28;
        }
  
    }
   

    public void desenharTelaViagemRapida() {
        

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int frameLargura = painel.tamanhoDoTile * 7;
        int frameAltura = painel.tamanhoDoTile * 5;

        int frameX = painel.tamanhoDoTile * 1;
        int frameY = painel.tamanhoDoTile * 4;


        desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

        switch (subEstado) {
            case 0 -> telaPrincipalDeViagemRapida(frameX, frameY, frameLargura);
            case 1 -> telaRegiao1(frameX, frameY, frameLargura);
        }

        painel.teclado.precionarEnter = false;
    }

    public void telaPrincipalDeViagemRapida(int frameX, int frameY, int frameLargura){

        int textoX;
        int textoY;

        String titulo = "Viagem Rápida";
        textoX = frameX + (frameLargura /2)- (g2.getFontMetrics().stringWidth(titulo)/2 );
        textoY = frameY + painel.tamanhoDoTile;
        g2.drawString(titulo, textoX, textoY);

        textoX = frameX + painel.tamanhoDoTile * 1;
        textoY += painel.tamanhoDoTile * 1.5;

        g2.drawString("Caminho para Capela", textoX, textoY);
        if(numeroDoComando == 0){
            g2.drawString(">", textoX - 15, textoY);
            if(painel.teclado.precionarEnter){
                subEstado = 1;
                numeroDoComando = 0;
            }
        }
        textoY += painel.tamanhoDoTile;
        textoY += painel.tamanhoDoTile * 1;
        
        // Voltar
        g2.drawString("Voltar", textoX, textoY);
        if(numeroDoComando == 1){
            g2.drawString(">", textoX - 15, textoY);
            if(painel.teclado.precionarEnter){
                painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
            }
        }
    }

    public void telaRegiao1(int frameX, int frameY, int frameLargura){

        int textoX;
        int textoY;

        String titulo = "Capela da Luz";
        textoX = frameX + (frameLargura  /2)- (g2.getFontMetrics().stringWidth(titulo)/2 );
        textoY = frameY + painel.tamanhoDoTile;
        g2.drawString(titulo, textoX, textoY);

        textoX = frameX + painel.tamanhoDoTile * 1;
        textoY += painel.tamanhoDoTile * 1.5;

        // Mapa 1
        g2.drawString("Ascender", textoX, textoY);
        if(numeroDoComando == 0){
            g2.drawString(">", textoX - 15, textoY);
            if(painel.teclado.precionarEnter){
                painel.mEventos.viajarRapido(0);
                painel.estadoDoJogo = painel.estadoDeTransicao;
            }
        }
        textoY += painel.tamanhoDoTile;
        textoY += painel.tamanhoDoTile * 1;

        // Voltar
        g2.drawString("Voltar", textoX, textoY);
        if(numeroDoComando == 1){
            g2.drawString(">", textoX - 15, textoY);
            if(painel.teclado.precionarEnter){
                subEstado = 0;
                numeroDoComando = 0;
            }
        }
    }

    public void desenharTelaDeGameOver(){
        
        // Fundo escuro translúcido
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);


        // Texto principal estilo Dark Souls
        String texto = "VOCÊ MORREU";

        // Aumenta a fonte e aplica o estilo pesado
        g2.setFont(new Font("Serif", Font.BOLD, 100));
        int x = obterTextoXCentralizado(texto);
        int y = painel.alturaTela / 2;

        // Faz o fade-in do texto
        if (alphaGameOver < 1f) {
        alphaGameOver += 0.01f;
        }
        if (alphaGameOver > 1f) {
            alphaGameOver = 1f; // evita valores acima de 1.0
        }

        // Cria uma cor vermelha intensa com transparência
        Color corVermelha = new Color(150, 0, 0, (int) (255 * alphaGameOver));
        g2.setColor(corVermelha);

        // Desenha leve sombra
        g2.drawString(texto, x + 3, y + 3);
        g2.setColor(new Color(255, 0, 0, (int) (255 * alphaGameOver)));
        g2.drawString(texto, x, y);

        // Exibe as opções após o fade
        if (alphaGameOver >= 1f) {
            g2.setFont(new Font("Serif", Font.BOLD, 40));

            // Reiniciar
            texto = "Reiniciar";
            x = obterTextoXCentralizado(texto);
            y += painel.tamanhoDoTile * 3;
            g2.setColor(Color.WHITE);
            g2.drawString(texto, x, y);
            if (numeroDoComando == 0) {
                g2.drawString(">", x - 50, y);
            }

            // Sair
            texto = "Sair";
            x = obterTextoXCentralizado(texto);
            y += 60;
            g2.drawString(texto, x, y);
            if (numeroDoComando == 1) {
                g2.drawString(">", x - 50, y);
            }
        }

    }

    public void resetarGameOver() {
        alphaGameOver = 0f;
        numeroDoComando = 0;
    }

    public void desenharHUD_DoJogador() {
        int x = 20;
        int y = 20;
        int larguraBase = 200;
        int altura = 20;

        int largura = larguraBase;
        int larguraVidaBase = larguraBase + (painel.jogador.forca * 5);
        int larguraManaBase = larguraBase + (painel.jogador.forca * 3);
        int larguraStaminaBase = larguraBase + (painel.jogador.destreza * 5);
        
        // VIDA 
        double proporcaoVida = (double) painel.jogador.vida / painel.jogador.vidaMaxima;
        int larguraVida = (int) (larguraVidaBase * proporcaoVida);

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(x - 1, y - 1, larguraVidaBase  + 2, altura + 2);

        g2.setColor(new Color(255, 0, 30)); // vermelho
        g2.fillRect(x, y, larguraVida, altura);

        // MANA
        int yMana = y + altura + 10; // 10px abaixo da barra de vida
        double proporcaoMana = (double) painel.jogador.mana / painel.jogador.manaMaxima;
        int larguraMana = (int) (larguraManaBase * proporcaoMana);

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(x - 1, yMana - 1, larguraManaBase  + 2, altura + 2);

        g2.setColor(new Color(30, 144, 255));
        g2.fillRect(x, yMana, larguraMana, altura);

        // RESISTÊNCIA 
        int yStamina = yMana + altura + 10;
        double proporcaoStamina = (double) painel.jogador.resistencia / painel.jogador.resistenciaMaxima;
        int larguraStamina = (int) (larguraStaminaBase * proporcaoStamina);

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(x - 1, yStamina - 1, larguraStaminaBase  + 2, altura + 2);

        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(x, yStamina, larguraStamina , altura);
        
        // ALMAS
        int margem = 30;

        g2.setFont(new Font("Serif", Font.BOLD, 36));
        String textoAlmas = String.format("%,d", painel.jogador.alma);

        int larguraTexto = g2.getFontMetrics().stringWidth(textoAlmas);
        int alturaTexto = g2.getFontMetrics().getHeight();

        int tamanhoIcone = 38;
        int padding = 8;
        int espacamento = 5;

        // TAMANHO DA CAIXA DINÂMICO 
        int larguraCaixa = tamanhoIcone + espacamento + larguraTexto + padding * 2;
        int alturaCaixa = Math.max(tamanhoIcone, alturaTexto) + padding;

        // posição da caixa (sempre fixa no canto)
        int xCaixa = painel.larguraTela - margem - larguraCaixa;
        int yCaixa = painel.alturaTela - margem - alturaCaixa;

        // POSIÇÕES INTERNAS (baseadas na caixa) 
        int xImagem = xCaixa + padding;
        int yImagem = yCaixa + (alturaCaixa - tamanhoIcone) / 2;

        int xTexto = xImagem + tamanhoIcone + espacamento;
        int yTexto = yCaixa + (alturaCaixa + alturaTexto) / 2 - 5;

        // fundo
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(xCaixa, yCaixa, larguraCaixa, alturaCaixa, 15, 15);

        // borda
        g2.setColor(new Color(255, 255, 255, 50));
        g2.drawRoundRect(xCaixa, yCaixa, larguraCaixa, alturaCaixa, 15, 15);

        // imagem
        try {
            BufferedImage imagemAlma = ImageIO.read(new File("res/objeto/alma.png"));
            g2.drawImage(imagemAlma, xImagem, yImagem, tamanhoIcone, tamanhoIcone, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // sombra
        g2.setColor(new Color(0, 0, 0, 180));
        g2.drawString(textoAlmas, xTexto + 2, yTexto + 2);

        // texto
        g2.setColor(new Color(255, 255, 255));
        g2.drawString(textoAlmas, xTexto, yTexto);
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

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
                    g2.setColor(Color.white);
                    g2.drawString(inimigo.nome, x + 4 , y - 10);
                }
            }
        }
    }

    public void desenhaVidaDoNpcAliado() {

        for (int i = 0; i < painel.npc[1].length; i++) {

            Entidade npc = painel.npc[painel.mapaAtual][i];

            if (npc != null && npc.tipo == npc.tipoNpcAliado && npc.camera()) {

                if (npc.barraDeVidaAtiva) {

                    double escala = (double) painel.tamanhoDoTile / npc.vidaMaxima;
                    double larguraVida = escala * npc.vida;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(npc.getTelaX()-1, npc.getTelaY() - 16, painel.tamanhoDoTile+2, 12);

                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(npc.getTelaX(), npc.getTelaY() - 15, (int)larguraVida, 10);

                    npc.contadorBarraDeVida++;

                    if (npc.contadorBarraDeVida > 300) {
                        npc.barraDeVidaAtiva = false;
                        npc.contadorBarraDeVida = 0;
                    }
                }
            }
        }
    }


    public void desenharMensagem(){
        if(mensagens.isEmpty()) return;

        g2.setFont(new Font("Serif", Font.BOLD, 28));

        for(int i = 0; i < mensagens.size(); i++){
            String msg = mensagens.get(i);

            if(msg != null){
                int larguraCaixa = 500;
                int alturaCaixa = 90;

                int x = painel.larguraTela/2 - larguraCaixa/2;
                int yBase = painel.alturaTela - 100;
                int espacamento = 25; // distância entre mensagens

                int y = yBase - (i * espacamento);

                int contador = contadorDeMensagens.get(i) + 1;
                contadorDeMensagens.set(i, contador);

                float alpha = 1.0f;
                if(contador > 300){ // últimos 30 frames somem
                    alpha = 1.0f - ((contador - 150) / 30f);
                }

                // aplica alpha
                AlphaComposite ac = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER,
                    Math.max(alpha, 0)
                );
                g2.setComposite(ac);


                FontMetrics fm = g2.getFontMetrics();
                int larguraTexto = fm.stringWidth(msg);
                int alturaTexto = fm.getHeight();

                int textoX = x + (larguraCaixa - larguraTexto) / 2;
                int textoY = y + (alturaCaixa - alturaTexto) / 2 + fm.getAscent();
                textoY -= 15;

                

                g2.setColor(Color.white);
                int linhaY = textoY;

                for (String linha : msg.split("\n")) {
                    int larguraLinha = fm.stringWidth(linha);
                    int linhaX = x + (larguraCaixa - larguraLinha) / 2;

                    // CONTORNO PRETO
                    g2.setColor(new Color(0, 0, 0, 50));
                    int offset = 2;

                    for (int dx = -offset; dx <= offset; dx++) {
                        for (int dy = -offset; dy <= offset; dy++) {
                            if (dx != 0 || dy != 0) {
                                g2.drawString(linha, linhaX + dx, linhaY + dy);
                            }
                        }
                    }

                    // TEXTO PRINCIPAL
                    g2.setColor(Color.white);
                    g2.drawString(linha, linhaX, linhaY);
                    linhaY += fm.getHeight();
                }
                
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

                if(contador > 360){
                    mensagens.remove(i);
                    contadorDeMensagens.remove(i);
                    i--;
                }
            }
        }
    }


    public void desenharTituloNaTela() {

        GradientPaint fundo = new GradientPaint(
            0, 0, new Color(10, 10, 10),
            0, painel.alturaTela, new Color(30, 30, 30)
        );
        g2.setPaint(fundo);
        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);


        if (estadoDeRolagemTitulo == 0) {
            // TELA INICIAL 
            g2.setFont(new Font("Serif", Font.BOLD, 84));
            String texto = "A ÚLTIMA LUZ";
            int x = obterTextoXCentralizado(texto);
            int y = painel.tamanhoDoTile * 6;

            g2.setColor(new Color(20, 20, 20));
            g2.drawString(texto, x + 6, y + 6);

            g2.setColor(new Color(235, 235, 235));
            g2.drawString(texto, x, y);
            
            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(235, 235, 235, 100));
            int riscoY = y - 25;
            g2.drawLine(x - 30, riscoY, x + g2.getFontMetrics().stringWidth(texto) + 30, riscoY);

            // MENU 
            g2.setFont(new Font("Serif", Font.PLAIN, 20));
            g2.setColor(new Color(200, 200, 200));

            String[] opcoes = { "Novo jogo", "Jogo salvo", "Sair" };
            y += painel.tamanhoDoTile * 2;

            for (int i = 0; i < opcoes.length; i++) {
                texto = opcoes[i];
                x = obterTextoXCentralizado(texto);

                if (numeroDoComando == i) {
                    g2.setColor(new Color(255, 140, 40));
                    g2.drawString(">", x - painel.tamanhoDoTile, y);
                    g2.setColor(new Color(255, 180, 80));
                } else {
                    g2.setColor(new Color(180, 180, 180));
                }
                g2.drawString(texto, x, y);
                y += painel.tamanhoDoTile;
            }

            g2.setColor(new Color(255, 255, 255, 60));
            for (int i = 0; i < 40; i++) {
                int ax = (int) (Math.random() * painel.larguraTela);
                int ay = (int) (Math.random() * painel.alturaTela);
                g2.fillRect(ax, ay, 2, 2);
            }

        } else if (estadoDeRolagemTitulo == 1) {
            // OBS: Adicionado -----------------------------------------------------
            // --- 1. LÓGICA DE CARREGAR A IMAGEM (Só roda se mudou a seta) ---
            if (numeroDoComando != comandoAnterior) {
                String nomeClasse = switch (numeroDoComando) {
                    case 0 -> "guerreiro";
                    case 1 -> "ladrao";
                    case 2 -> "mago";
                    case 3 -> "piromante";
                    default -> "none";
                };

                if (numeroDoComando != 4) {
                    imagemPreview = carregarPreview(nomeClasse);
                } else {
                    imagemPreview = null;
                }
                comandoAnterior = numeroDoComando; // Atualiza para não carregar de novo
            }
            // ---------------------------------------------------------------------
            // TELA DE CLASSES
            RadialGradientPaint gradiente = new RadialGradientPaint(
                new Point2D.Float(painel.larguraTela / 2f, painel.alturaTela / 2f),
                painel.larguraTela / 1.2f,
                new float[]{0f, 1f},
                new Color[]{new Color(40, 40, 40, 100), new Color(0, 0, 0, 255)}
            );
            g2.setPaint(gradiente);
            g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);

            // TÍTULO
            g2.setFont(new Font("Serif", Font.BOLD, 46));
            String titulo = "ESCOLHA SUA CLASSE";
            int xTitulo = obterTextoXCentralizado(titulo);
            int yTitulo = painel.tamanhoDoTile * 2;

            g2.setColor(new Color(230, 230, 230));
            g2.drawString(titulo, xTitulo, yTitulo);

            // OBS: comentado e alterado
            // -----------------------------------------------------
            if (imagemPreview != null) {
                int imgX = painel.tamanhoDoTile * 3;
                int imgY = painel.tamanhoDoTile * 5;
                g2.drawImage(imagemPreview, imgX, imgY, null);
            }

            // if (painel.jogador.baixo1 != null) {
            //     int imgX = painel.tamanhoDoTile * 3;
            //     int imgY = painel.tamanhoDoTile * 5;
            //     int imgLargura = painel.tamanhoDoTile * 3;
            //     int imgAltura = painel.tamanhoDoTile * 3;
            //     g2.drawImage(painel.jogador.baixo1, imgX, imgY, imgLargura, imgAltura, null);
            // }
            // ---------------------------------------------------------------------

            g2.setFont(new Font("Serif", Font.PLAIN, 20));
            String[] classes = {"Guerreiro","Ladrão", "Mago", "Piromante", "Voltar"};

            int baseY = painel.tamanhoDoTile * 5;
            int margemDireita = painel.tamanhoDoTile * 5;
            int bordaDireita = painel.larguraTela - margemDireita;

            for (int i = 0; i < classes.length; i++) {
                String texto = classes[i];
                int x = obterTextoXDireita(texto, bordaDireita);
                int y = baseY + (int)(i * painel.tamanhoDoTile);

                if (numeroDoComando == i) {
                    g2.setColor(new Color(255, 180, 60));
                    g2.drawString(">", x - painel.tamanhoDoTile, y);
                    g2.setColor(new Color(255, 180, 80));
                } else {
                    g2.setColor(new Color(160, 160, 160));
                }

                g2.drawString(texto, x, y);
            }

            // DESCRIÇÃO
            g2.setFont(new Font("Serif", Font.ITALIC, 20));
            String descricao = switch (numeroDoComando) {
                case 0 -> "Um guerreiro equilibrado, forte e resistente.";
                case 1 -> "Rápido e furtivo, mestre em ataques precisos.";
                case 2 -> "Manipula as forças arcanas com sabedoria e poder.";
                case 3 -> "Consegue controlar o fogo e causar danos explosivos aos inimigos.";
                default -> "";
            };

            if (!descricao.isEmpty()) {
                /*
                int xDesc = painel.larguraTela / 2; 
                int yDesc = painel.alturaTela - painel.tamanhoDoTile * 2;
                xDesc = Math.min(xDesc, painel.larguraTela - 250); 
                g2.setColor(new Color(200, 200, 200));
                g2.drawString(descricao, xDesc, yDesc);
                */
                int imgX = painel.tamanhoDoTile * 3;
                int imgY = painel.tamanhoDoTile * 5;
                int imgAltura = painel.tamanhoDoTile * 3;

                int xDesc = imgX; 
                int yDesc = imgY + imgAltura + painel.tamanhoDoTile; 

                g2.setColor(new Color(200, 200, 200));
                g2.drawString(descricao, xDesc, yDesc);
            }

            for (int i = 0; i < 40; i++) {
                int px = (int)(Math.random() * painel.larguraTela);
                int py = (int)(Math.random() * painel.alturaTela);
                int alpha = 30 + (int)(Math.random() * 60);
                g2.setColor(new Color(255, 255, 255, alpha));
                g2.fillRect(px, py, 2, 2);
            }
        }
    }


    public void desenharDialogoNaTela(){
        int x = painel.tamanhoDoTile * 3;
        int largura = painel.larguraTela - (painel.tamanhoDoTile * 6);
        int altura = painel.tamanhoDoTile * 3;
        int y = painel.alturaTela - altura - painel.tamanhoDoTile;

        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, largura, altura, 25, 25);

        g2.setColor(new Color(200, 200, 200, 180));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, largura, altura, 25, 25);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2.setFont(new Font("Serif", Font.PLAIN, 32));

        int textoX = x + painel.tamanhoDoTile;
        int textoY = y + painel.tamanhoDoTile;

        // ==== Efeito de digitação ====
        if (npc.dialogo[npc.setDialogo][npc.indiceDoDialogo] != null) {
            char personagem[] = npc.dialogo[npc.setDialogo][npc.indiceDoDialogo].toCharArray();

            if (indicePersonagem < personagem.length) {
                painel.iniciarEfeitoSonoro(17);
                String s = String.valueOf(personagem[indicePersonagem]);
                combinandoTexto = combinandoTexto + s;
                dialogoAtual = combinandoTexto;
                indicePersonagem++;
            }

            if (painel.teclado.precionarEnter == true) {
                indicePersonagem = 0;
                combinandoTexto = "";

                if (painel.estadoDoJogo == painel.estadoDoDialogo || painel.estadoDoJogo == painel.estadoCutscene) {
                    npc.indiceDoDialogo++;
                    painel.teclado.precionarEnter = false;
                }
            }
        } else {
            npc.indiceDoDialogo = 0;

            if (painel.estadoDoJogo == painel.estadoDoDialogo) {
                painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
            }
            if (painel.estadoDoJogo == painel.estadoCutscene) {
                painel.gerenciadorDeCutscene.faseDaCena++;
            }
        }

        for (String linha : dialogoAtual.split("\n")) {
            g2.setColor(Color.black);
            g2.drawString(linha, textoX + 2, textoY + 2);
            g2.setColor(Color.white);
            g2.drawString(linha, textoX, textoY);
            textoY += 40;
        }
        
        
    }

    public void desenharTelaStatusDoJogador(){
        //criar frames
        final int frameX = painel.tamanhoDoTile *2;
        final int frameY = painel.tamanhoDoTile;
        final int frameLargura = painel.tamanhoDoTile*5;
        final int frameAltura = painel.tamanhoDoTile*10;

        desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

        //texto
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textoX = frameX + 30;
        int textoY = frameY  + painel.tamanhoDoTile;
        final int linhaAltura = 35;

        //nomes
        g2.drawString("Nivel", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Vida", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Mana", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Resistência", textoX, textoY);//stamina ou energia
        textoY += linhaAltura;
        g2.drawString("Força", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Destreza", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Ataque", textoX, textoY);
        textoY += linhaAltura;
        g2.drawString("Defesa", textoX, textoY);
        textoY += linhaAltura;
       // g2.drawString("Exp", textoX, textoY);
       // textoY += linhaAltura;
        //g2.drawString("Proximo nivel", textoX, textoY);
        //textoY += linhaAltura;
        g2.drawString("Fragmento", textoX, textoY);
        textoY += linhaAltura +15;
        //g2.drawString("Almas", textoX, textoY);
        //textoY += linhaAltura +10;
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

        valor = String.valueOf(painel.jogador.vidaMaxima);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.manaMaxima);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.resistenciaMaxima);
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

        //valor = String.valueOf(painel.jogador.exp);
        //textoX = obterTextoXDireita(valor, bordaX);
        //g2.drawString(valor, textoX, textoY);
        //textoY += linhaAltura;

        //valor = String.valueOf(painel.jogador.proximoNivelExp);
       // textoX = obterTextoXDireita(valor, bordaX);
       // g2.drawString(valor, textoX, textoY);
        //textoY += linhaAltura;

        valor = String.valueOf(painel.jogador.fragmentoDaEspada);
        textoX = obterTextoXDireita(valor, bordaX);
        g2.drawString(valor, textoX, textoY);
        textoY += linhaAltura;

        //valor = String.valueOf(painel.jogador.alma);
        //textoX = obterTextoXDireita(valor, bordaX);
        //g2.drawString(valor, textoX, textoY);
        //textoY += linhaAltura;

        g2.drawImage(painel.jogador.armaAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-24, null);
        textoY += painel.tamanhoDoTile;
        g2.drawImage(painel.jogador.escudoAtual.baixo1, bordaX - painel.tamanhoDoTile, textoY-24, null);
   
    }

    // public void desenhaInventario(Entidade entidade, boolean cursor){

    //     int frameX = 0;
    //     int frameY = 0;
    //     int frameLargura = 0;
    //     int frameAltura = 0;
    //     int espacoColuna = 0;
    //     int espacoLinha = 0;

    //     if(entidade == painel.jogador){

    //         frameX = painel.tamanhoDoTile*12;
    //         frameY = painel.tamanhoDoTile;
    //         frameLargura = painel.tamanhoDoTile*6;
    //         frameAltura = painel.tamanhoDoTile*5;

    //         espacoColuna = jogadorEspacoColuna;
    //         espacoLinha = jogadorEspacoLinha;

    //     }else{
            
    //         frameX = painel.tamanhoDoTile*2;
    //         frameY = painel.tamanhoDoTile;
    //         frameLargura = painel.tamanhoDoTile*6;
    //         frameAltura = painel.tamanhoDoTile*5;

    //         espacoColuna = npcEspacoColuna;
    //         espacoLinha = npcEspacoLinha;
    //     }
        

    //     desenharSubJanela(frameX, frameY, frameLargura, frameAltura);

    //     //espaços
    //     final int espacoInicialX = frameX + 20;
    //     final int espacoInicialY = frameY + 20;
    //     int espacoX = espacoInicialX;
    //     int espacoY = espacoInicialY;
    //     int tamanhoEspaco = painel.tamanhoDoTile+3;

    //     //desenhar itens
    //     for(int i = 0; i < entidade.inventario.size(); i++){
            
    //         //equipar arma (cursor)
    //         if(entidade.inventario.get(i) == entidade.armaAtual ||
    //                 entidade.inventario.get(i) == entidade.escudoAtual ||
    //                 entidade.inventario.get(i) == entidade.luzAtual){
                
    //             g2.setColor(new Color(240,190,90));
    //             g2.fillRoundRect(espacoX, espacoY, painel.tamanhoDoTile, painel.tamanhoDoTile, 10, 10);

    //         }
               
    //         g2.drawImage(entidade.inventario.get(i).baixo1, espacoX, espacoY, null);

    //         //display quantidade
    //         if(entidade == painel.jogador && entidade.inventario.get(i).quantidade > 1){
    //             g2.setFont(g2.getFont().deriveFont(32f));
    //             int quantidadeX;
    //             int quantidadeY;

    //             String s = "" + entidade.inventario.get(i).quantidade;
    //             quantidadeX = obterTextoXDireita(s, espacoX + 44);
    //             quantidadeY = espacoY + painel.tamanhoDoTile;


    //             //sombra
    //             g2.setColor(new Color(60,60,60));
    //             g2.drawString(s, quantidadeX, quantidadeY);

    //             //numero
    //             g2.setColor(Color.white);
    //             g2.drawString(s, quantidadeX -3 , quantidadeY -3);
    //         }

    //         espacoX += tamanhoEspaco;
    //         if( i == 4 || i == 9 || i == 14){
    //             espacoX = espacoInicialX;
    //             espacoY += tamanhoEspaco;
    //         }
           

    //     }

    //     //cursor
    //     int cursorX = espacoInicialX + (tamanhoEspaco * espacoColuna);
    //     int cursorY = espacoInicialY + (tamanhoEspaco * espacoLinha);
    //     int cursorLargura = painel.tamanhoDoTile;
    //     int cursorAltura = painel.tamanhoDoTile;

    //     //desenhar cursor
    //     if(cursor == true){
    //         g2.setColor(Color.white);
    //         g2.setStroke(new BasicStroke(3));
    //         g2.drawRoundRect(cursorX, cursorY, cursorLargura, cursorAltura , 10, 10);

    //         //frame de descrição
    //         int DframeX = frameX;
    //         int DframeY = frameY + frameAltura;
    //         int DframeLargura = frameLargura;
    //         int DframeAltura = painel.tamanhoDoTile*3;
            
    //         // definir cor do texto **após desenhar o fundo**
    //         g2.setFont(g2.getFont().deriveFont(28F));
    //         g2.setColor(Color.white);

    //         int textoX = DframeX + 20;
    //         int textoY = DframeY + 40;
           

    //         //pegar o item selecionado
    //         int itemSelecionado = pegarItemSelecionado(espacoColuna, espacoLinha);
    //         if(itemSelecionado < entidade.inventario.size()){
    //             desenharSubJanela(DframeX, DframeY, DframeLargura, DframeAltura);
                
    //             for(String linha : entidade.inventario.get(itemSelecionado).descricao.split("\n")){
    //                 g2.drawString(linha, textoX, textoY);
    //                 textoY += 32;
    //             }
                
    //             //durabilidade - fazer uma verificação para atribuir a todos os objetos
    //             //g2.drawString("Durabilidade: " + entidade.inventario.get(itemSelecionado).durabilidade, textoX, textoY+20);
    //         }
            
    //     }
        

    // }

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
            
            // --- TRAVA DE SEGURANÇA 1: Só desenha se o item não for nulo ---
            if (entidade.inventario.get(i) != null) {
                
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
            } // --- FIM DA TRAVA DE SEGURANÇA 1 ---

            // O avanço dos "quadradinhos" do inventário fica de fora do if para a grade não quebrar
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
            
            // definir cor do texto **após desenhar o fundo**
            g2.setFont(g2.getFont().deriveFont(28F));
            g2.setColor(Color.white);

            int textoX = DframeX + 20;
            int textoY = DframeY + 40;
            

            //pegar o item selecionado
            int itemSelecionado = pegarItemSelecionado(espacoColuna, espacoLinha);
            
            // --- TRAVA DE SEGURANÇA 2: Só exibe a descrição se houver item naquele espaço! ---
            if(itemSelecionado < entidade.inventario.size() && entidade.inventario.get(itemSelecionado) != null){
                desenharSubJanela(DframeX, DframeY, DframeLargura, DframeAltura);
                
                for(String linha : entidade.inventario.get(itemSelecionado).descricao.split("\n")){
                    g2.drawString(linha, textoX, textoY);
                    textoY += 32;
                }
                
                //durabilidade - fazer uma verificação para atribuir a todos os objetos
                //g2.drawString("Durabilidade: " + entidade.inventario.get(itemSelecionado).durabilidade, textoX, textoY+20);
            }
            
        }
        

    }

    public void desenharTelaDeOpcoes(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub janela
        int frameX = painel.tamanhoDoTile*6;
        int frameY = painel.tamanhoDoTile;
        int frameLargura = painel.tamanhoDoTile*11;
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
        g2.drawString("Mover", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Confirm/ataque", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Projetável", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Inventário", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Defesa", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Mapa", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Mini Mapa", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("Opções", textoX, textoY); textoY+=painel.tamanhoDoTile;


        textoX = frameX + painel.tamanhoDoTile*6;
        textoY = frameY + painel.tamanhoDoTile*2;
        g2.drawString("WASD", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("ENTER", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("F", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("C", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("ESPAÇO", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("M", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("X", textoX, textoY); textoY+=painel.tamanhoDoTile;
        g2.drawString("ESC", textoX, textoY); textoY+=painel.tamanhoDoTile;

        //Voltar
        textoX = frameX + painel.tamanhoDoTile;
        textoY = frameY + painel.tamanhoDoTile*10;
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
                // painel.teclado.precionarEnter = false;
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
                // painel.teclado.precionarEnter = false;
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
    
    //inventario do npc comerciante
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
        g2.drawString("Almas: " + painel.jogador.alma , x+24, y+60);

    
        
        

        //desenhar preço
        int indeceItem = pegarItemSelecionado(npcEspacoColuna, npcEspacoLinha);
        if(indeceItem < npc.inventario.size()){
            x = (int)(painel.tamanhoDoTile*5.5);
            y = (int)(painel.tamanhoDoTile*5.5);
            width = (int)(painel.tamanhoDoTile*2.5);
            height = painel.tamanhoDoTile;
            desenharSubJanela(x, y, width, height);
            g2.drawImage(alma, x+10, y+8, 32, 32,null);

            int preco = npc.inventario.get(indeceItem).preco;
            String texto = "" + preco;
            x = obterTextoXDireita(texto, painel.tamanhoDoTile*8-20);
            g2.drawString(texto, x, y+34);
        }

        //comprar item
        if(painel.teclado.precionarEnter == true){
            if(npc.inventario.get(indeceItem).preco > painel.jogador.alma){
                subEstado = 0;
                npc.iniciarDialogo(npc, 2);
            }
            else{
                if(painel.jogador.podeObterItem(npc.inventario.get(indeceItem)) == true){
                    painel.jogador.alma -= npc.inventario.get(indeceItem).preco;
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
        g2.drawString("Almas: " + painel.jogador.alma , x+24, y+60);

        //desenhar preço
        int indeceItem = pegarItemSelecionado(jogadorEspacoColuna, jogadorEspacoLinha);
        if(indeceItem < painel.jogador.inventario.size()){
            x = (int)(painel.tamanhoDoTile*15.5);
            y = (int)(painel.tamanhoDoTile*5.5);
            width = (int)(painel.tamanhoDoTile*2.5);
            height = painel.tamanhoDoTile;
            desenharSubJanela(x, y, width, height);
            g2.drawImage(alma, x+10, y+8, 32, 32,null);

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

                    painel.jogador.alma += preco;
                    

                }
            }
        }

        

    }


    //inventarido da gurdiã - subir de nivel
    public void desenharTelaDeTrocaGuardia() {
        // Garante que o contexto gráfico está ativo
        g2.setFont(maruMonica);
        g2.setColor(Color.white);
        
        switch (subEstadoGuardia) {
            case 0:
                selecionarTrocaGuardia();
                break;
            case 1:
                subirNivelTroca();
                break;
        }

        painel.teclado.precionarEnter = false;
        painel.teclado.precionarEspaco = false;
    }
    
    public void selecionarTrocaGuardia() {

        int x = painel.tamanhoDoTile * 2;
        int y = painel.tamanhoDoTile * 3;
        int width = painel.tamanhoDoTile * 4;
        int height = painel.tamanhoDoTile * 3;

        desenharSubJanela(x, y, width, height);
        
        // Define fonte e cor antes de desenhar o texto
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.white);

        y += painel.tamanhoDoTile;
        g2.drawString("Subir Nível", x + 30, y);
        if (numeroDoComando == 0) {
            g2.drawString(">", x+10, y);
            if (painel.teclado.precionarEnter) {
                subEstadoGuardia = 1; // Abre a tela de subir nível
            }
        }
        y += 30;
        g2.drawString("-------------------", x+10, y);
        
       // y += painel.tamanhoDoTile;
       
        y += 45;
       
        g2.drawString("Sair", x + 30, y);
        if (numeroDoComando == 1) {
            g2.drawString(">", x+10, y);
            if (painel.teclado.precionarEnter) {
                numeroDoComando = 0;
                npc.iniciarDialogo(npc, 1);
                painel.estadoDoJogo = painel.estadoDoDialogo;
            }
        }

        if (painel.teclado.precionarCima) {
            numeroDoComando--;
            painel.teclado.precionarCima = false;
        }
        if (painel.teclado.precionarBaixo) {
            numeroDoComando++;
            painel.teclado.precionarBaixo = false;
        }
        if (numeroDoComando < 0) numeroDoComando = 1;
        if (numeroDoComando > 1) numeroDoComando = 0;
    }
    

    public void subirNivelTroca() {

        int x = painel.tamanhoDoTile * 2;
        int y = painel.tamanhoDoTile * 3;
        int width = painel.tamanhoDoTile * 6;
        int height = painel.tamanhoDoTile * 7;

        desenharSubJanela(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.white);

        int textoX = x + 40;
        int textoY = y + 40;

        g2.drawString("Subir Nível", textoX, textoY);

        int custo = 0;
        if (npc instanceof GuardiaDaLuz guardia) {
            custo = guardia.calcularCustoParaSubirNivel();
        }

        textoY += 40;
        g2.drawString("Custo: " + custo + " almas", textoX, textoY);

        textoY += 40;
        g2.drawString("----------------------------", textoX - 20, textoY);

        // FORÇA
        textoY += 40;
        g2.drawString("Força", textoX, textoY);
        if (numeroDoComando == 0) {
            g2.drawString(">", textoX - 20, textoY);
        }

        // DESTREZA
        textoY += 40;
        g2.drawString("Destreza", textoX, textoY);
        if (numeroDoComando == 1) {
            g2.drawString(">", textoX - 20, textoY);
        }

        textoY += 40;
        g2.drawString("----------------------------", textoX - 20, textoY);

        textoY += 40;
        g2.drawString("[ ENTER ] Confirmar", textoX, textoY);
        textoY += 40;
        g2.drawString("[ ESPACO ] Voltar", textoX, textoY);

        // CONTROLES
        if (painel.teclado.precionarCima) {
            numeroDoComando--;
            painel.teclado.precionarCima = false;
        }
        if (painel.teclado.precionarBaixo) {
            numeroDoComando++;
            painel.teclado.precionarBaixo = false;
        }

        if (numeroDoComando < 0) numeroDoComando = 1;
        if (numeroDoComando > 1) numeroDoComando = 0;

        // CONFIRMAR
        if (painel.teclado.precionarEnter) {
            if (npc instanceof GuardiaDaLuz guardia) {
                //guardia.tentarSubirNivel(numeroDoComando);
                atributoEscolhido = numeroDoComando;
                guardia.tentarSubirNivel(atributoEscolhido);
                
            }
            subEstadoGuardia = 0;
            painel.teclado.precionarEnter = false;
        }

        if (painel.teclado.precionarEspaco) {
            subEstadoGuardia = 0;
            painel.teclado.precionarEspaco = false;
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
        
        // Fundo translúcido sólido (sem gradiente)
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRoundRect(x, y, largura, altura, 35, 35);

        // Borda cinza suave
        g2.setColor(new Color(180, 180, 180, 200));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x + 5, y + 5, largura - 10, altura - 10, 25, 25);

        // Define a cor do texto como branco
        g2.setColor(Color.WHITE);
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
    
    // OBS: Adicionado -----------------------------------------------------
    public void confirmarEscolhaDeClasse() {
        System.err.println("nome da classsss: " + numeroDoComando);

        String classeEscolhida = "";

        // classe o jogador escolheu no menu
        switch (numeroDoComando) {
            case 0:
                classeEscolhida = "guerreiro";
                break;
            case 1:
                classeEscolhida = "ladrao";
                break;
            case 2:
                classeEscolhida = "mago";
                break;
            case 3:
                classeEscolhida = "piromante";
                break;
            case 4: // Voltar
                estadoDeRolagemTitulo = 0;
                numeroDoComando = 0;
                return; // Sai do método sem iniciar o jogo
        }

        painel.jogador.carregarImagemPorClasse(classeEscolhida);
        painel.jogador.setItens(classeEscolhida);
        // painel.reiniciarJogo(true);
        painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
        // painel.iniciarMusica(0);
    }

    // public void confirmarEscolhaDeClasse() {

    //     // Reseta o jogador para evitar bugs se ele voltar ao menu
    //     // painel.jogador.resetarAtributos();
    //     System.err.println("nome da classsss: " + numeroDoComando);

    //     switch (numeroDoComando) {
    //         case 0:
    //             definirGuerreiro();
    //             break;
    //         case 1:
    //             definirLadrao();
    //             break;
    //         case 2:
    //             definirMago();
    //             break;
    //         case 3:
    //             definirPiromante();
    //             break;
    //         case 4: // Voltar
    //             estadoDeRolagemTitulo = 0;
    //             numeroDoComando = 0;
    //             return; // Sai do método sem iniciar o jogo
    //     }

    //     // Após definir a classe, inicia o jogo
    //     painel.estadoDoJogo = painel.iniciarEstadoDoJogo;
    //     // painel.iniciarMusica(0); // Toca a música da fase
    // }

    // public void definirGuerreiro() {
    //     painel.jogador.carregarImagemPorClasse("guerreiro");

    //     // painel.jogador.vidaMaxima = 10;
    //     // painel.jogador.vida = 10;
    //     // painel.jogador.forca = 5;
    //     // painel.jogador.destreza = 2;
    //     // painel.jogador.manaMaxima = 2;
    //     // painel.jogador.mana = 2;

    //     // 3. Itens Iniciais (Certifique-se que você tem essas classes criadas em
    //     // objeto)
    //     painel.jogador.armaAtual = new ObjEspadaNormal(painel);
    //     // painel.jogador.escudoAtual = new ObjEscudoMadeira(painel);
    //     painel.jogador.inventario.add(painel.jogador.armaAtual);
    //     // painel.jogador.inventario.add(painel.jogador.escudoAtual);
    // }

    // public void definirLadrao() {
    //     painel.jogador.carregarImagemPorClasse("ladrao");

    //     // painel.jogador.vidaMaxima = 8;
    //     // painel.jogador.vida = 8;
    //     // painel.jogador.forca = 2;
    //     // painel.jogador.destreza = 6;
    //     // painel.jogador.manaMaxima = 4;
    //     // painel.jogador.mana = 4;

    //     painel.jogador.armaAtual = new ObjAdaga(painel);
    //     // painel.jogador.escudoAtual = new ObjEscudoMadeira(painel);
    //     painel.jogador.inventario.add(painel.jogador.armaAtual);
    //     // painel.jogador.inventario.add(painel.jogador.escudoAtual);
    //     // painel.jogador.armaAtual = new Obj_Adaga(painel);
    //     // painel.jogador.inventario.add(painel.jogador.armaAtual);
    // }

    // public void definirMago() {
    //     painel.jogador.carregarImagemPorClasse("mago");

    //     // painel.jogador.vidaMaxima = 6;
    //     // painel.jogador.vida = 6;
    //     // painel.jogador.forca = 1;
    //     // painel.jogador.destreza = 2;
    //     // painel.jogador.manaMaxima = 10;
    //     // painel.jogador.mana = 10;

    //     painel.jogador.armaAtual = new ObjCajadoNormal(painel);
    //     // painel.jogador.escudoAtual = new ObjEscudoMadeira(painel);
    //     painel.jogador.inventario.add(painel.jogador.armaAtual);
    //     // painel.jogador.inventario.add(painel.jogador.escudoAtual);
    //     // painel.jogador.armaAtual = new Obj_Cajado_Iniciante(painel);
    //     // painel.jogador.inventario.add(painel.jogador.armaAtual);
    //     // Adicionar magia inicial aqui se tiver
    // }

    // public void definirPiromante() {
    //     painel.jogador.carregarImagemPorClasse("piromante");

    //     // painel.jogador.vidaMaxima = 8;
    //     // painel.jogador.vida = 8;
    //     // painel.jogador.forca = 3;
    //     // painel.jogador.destreza = 3;
    //     // painel.jogador.manaMaxima = 6;
    //     // painel.jogador.mana = 6;

    //     painel.jogador.armaAtual = new ObjCatalisadorDeFogo(painel);
    //     // painel.jogador.escudoAtual = new ObjEscudoMadeira(painel);
    //     painel.jogador.inventario.add(painel.jogador.armaAtual);
    //     // painel.jogador.inventario.add(painel.jogador.escudoAtual);
    // }

    public BufferedImage carregarPreview(String nomeClasse) {
        BufferedImage imagem = null;
        String caminho = "/res/jogador-classe/" + nomeClasse + "/baixo1.png"; // Pega sempre o boneco de frente

        try {
            // Carrega a imagem original
            InputStream is = getClass().getResourceAsStream(caminho);
            if (is == null) {
                // Se não achar a imagem da classe, carrega o 'boy' padrão para não dar erro
                is = getClass().getResourceAsStream("/res/jogador/baixo1.png");
            }

            imagem = ImageIO.read(is);

            // Escala a imagem (Zoom) para ficar grande no menu
            // Vamos fazer ela ficar 3x maior que o tile normal para dar destaque
            int tamanhoPreview = painel.tamanhoDoTile * 3;

            BufferedImage imagemEscalada = new BufferedImage(tamanhoPreview, tamanhoPreview, imagem.getType());
            Graphics2D g2 = imagemEscalada.createGraphics();
            g2.drawImage(imagem, 0, 0, tamanhoPreview, tamanhoPreview, null);
            g2.dispose();

            return imagemEscalada;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // ---------------------------------------------------------------------
    
   
}
