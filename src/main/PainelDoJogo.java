package main;
import javax.swing.JPanel;

import IA.LocalizarCaminhos;
import ambiente.GerenciadorDeAmbientes;
import dados.SalvarE_Carregar;
import entidade.Entidade;
import entidade.Jogador;
import tile.GerenciadorDeBlocos;
import tile.Mapa;
import tile.blocosInterativos.BlocosInterativos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PainelDoJogo extends JPanel implements Runnable {
    // Tela do jogo

    final int tamanhoOriginalDoTile = 16; // Tile de 16x16 pixels
    final int escala = 3; 
    
    public final int tamanhoDoTile = tamanhoOriginalDoTile * escala; // Tile de 48x48 pixels
    public final int maxColunasTela = 20;
    public final int maxLinhasTela = 12;

    public final int larguraTela = tamanhoDoTile * maxColunasTela; // 960 pixels
    public final int alturaTela = tamanhoDoTile * maxLinhasTela; // 576 pixels

    //world settings
    public  int maxColunasMundo; // Número máximo de colunas no mundo
    public  int maxLinhasMundo; // Número máximo de linhas no mundo
    public final int maxMapa = 10; //qtd de mapas - ajustar conforme necessidade
    public int mapaAtual = 0;
   
    //tela cheia
    int larguraTela2 = larguraTela;
    int alturaTela2 = alturaTela;
    BufferedImage telaTemporaria;
    Graphics2D g2;
    public boolean telaCheiaAtiva = false;

    //FPS
    int FPS= 60;

    public GerenciadorDeBlocos gerenciadorDeBlocos = new GerenciadorDeBlocos(this);

    public Teclado teclado = new Teclado(this); 
    Som musica = new Som(); 
    Som efeitoSonoro = new Som();

    public ColisaoChecked colisaoChecked = new ColisaoChecked(this);
    public CriarObjetos criarObjetos = new CriarObjetos(this);
    public InterfaceDoUsuario interfaceDoUsuario = new InterfaceDoUsuario(this);
    public ManipuladorDeEventos mEventos = new ManipuladorDeEventos(this);
    Config config = new Config(this);
    public LocalizarCaminhos localizarCaminhos = new LocalizarCaminhos(this);
    GerenciadorDeAmbientes gerenciadorDeAmbientes = new GerenciadorDeAmbientes(this);
    Mapa mapa = new Mapa(this);
    SalvarE_Carregar salvarE_Carregar = new SalvarE_Carregar(this);
    public GeradorDeEntidade geradorDeEntidade = new GeradorDeEntidade(this);
    public GerenciadorDeCutscene gerenciadorDeCutscene = new GerenciadorDeCutscene(this);
    Thread threadDoJogo; // Necessário implementar Runnable para usar thread

    //Entidades e objetos do jogo
    public Jogador jogador = new Jogador(this, teclado); 
    public Entidade Obj[][] = new Entidade[maxMapa][20]; //20 objs ao mesmo tempo
    public Entidade npc[][] = new Entidade[maxMapa][10]; //10 objs ao mesmo tempo
    public Entidade inimigo[][] = new Entidade[maxMapa][20]; //20 inimigo ao mesmo tempo
    public BlocosInterativos blocosI[][] = new BlocosInterativos[maxMapa][50];
    public Entidade projetavel[][] = new Entidade[maxMapa][20];
    //public ArrayList<Entidade> listaProjetil = new ArrayList<>();
    public ArrayList<Entidade> listaParticula = new ArrayList<>();
    ArrayList<Entidade> listaEntidade = new ArrayList<>(); 


    //Estado do jogo
    public int estadoDoJogo;
    public final int tituloEstado = 0;
    public final int iniciarEstadoDoJogo = 1;
    public final int pausarEstadoDoJogo = 2; 
    public final int estadoDoDialogo = 3;
    public final int estadoPersonagem = 4; 
    public final int estadoOpcoes = 5; 
    public final int estadoGameOver = 6;
    public final int estadoDeTransicao = 7;
    public final int trocaDeEstado = 8;
    public final int estadoDormir = 9;
    public final int estadoMapa = 10;
    public final int estadoCutscene = 11;
    
    //outros estados
    public boolean batalhaComChefeAtiva = false;

    //IA
    public boolean desenharCaminho;


    //AREA
    public int areaAtual;
    public int proximaArea;
    public final int fora = 50; 
    public final int interior = 51;
    public final int masmorra = 52;



    public PainelDoJogo(){
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Melhora a performance de renderização
        this.addKeyListener(teclado); // Adiciona o KeyListener para capturar eventos de teclado
        this.setFocusable(true); // Permite que o painel receba foco para capturar eventos de teclado
        
    }

    //setupGame
    public void setarObjetos() {
        criarObjetos.setarObjetos(); 
        criarObjetos.setNpc();
        criarObjetos.setInimigos();
        criarObjetos.setBlocosInterativos();
        gerenciadorDeAmbientes.setup();

        //iniciarMusica(0);// Inicia a música de fundo
        //pararMusica();
        estadoDoJogo = tituloEstado;
        areaAtual = fora;

        telaTemporaria = new BufferedImage(larguraTela, alturaTela, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)telaTemporaria.getGraphics();

        if(telaCheiaAtiva == true){
            setTelaCheia();
        }
        
    }
    public void  reiniciarJogo(boolean reiniciar){
        pararMusica();
        areaAtual = fora; // não queremos retornar a posição padrão, queremos retornar no ultimo ponto salvo
        removerTempDaEntidade();
        batalhaComChefeAtiva = false;
        jogador.setPosicaoPadrao();
        jogador.restaltarStatus();
        jogador.reiniciarContador();
        criarObjetos.setNpc();
        criarObjetos.setInimigos();

        if(reiniciar == true){
            jogador.setDefaultValues();
            criarObjetos.setarObjetos(); 
            criarObjetos.setBlocosInterativos();
            gerenciadorDeAmbientes.iluminacao.reiniciarDia();
        }
        
    }

    public void setTelaCheia(){
        //pegar a tela do monitor
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Principal.janela);

        //pegar altura e largura da tela
        larguraTela2 = Principal.janela.getWidth();
        alturaTela2 = Principal.janela.getHeight();

    }
    
    public void iniciarThreadDoJogo() {
        threadDoJogo = new Thread(this); // Passa o próprio objeto (PainelDoJogo) para a thread
        threadDoJogo.start(); // Chama automaticamente o método run
    }

    /*@Override
    public void run() {
        // Ao implementar a interface Runnable (como o PainelDoJogo), é necessário sobrescrever o método run
        // Aqui é onde o jogo irá rodar - o loop principal do jogo

        double intervaloDeAtualizacao = 1000000000 / FPS; // Intervalo de atualização em nanosegundos -> 0.01666 segundos
        double tempoDaProximaAtualizacao = System.nanoTime() + intervaloDeAtualizacao; // Marca o tempo da próxima atualização

        while(threadDoJogo != null) {
            // Loop do jogo
            
            //long tempoInicial = System.nanoTime(); // Marca o tempo inicial do loop
            //System.out.println("Loop do jogo rodando..."+ tempoInicial);

            // 1. Atualizar o estado do jogo
            atualizarJogo();
            // 2. Desenhar/renderizar o jogo

            repaint(); // Chama o método paintComponent para desenhar o jogo

            // 3. Controlar o tempo de atualização
            try {
                double tempoRestante = tempoDaProximaAtualizacao - System.nanoTime(); // Calcula o tempo restante até a próxima atualização
                tempoRestante = tempoRestante / 1000000; // Converte de nanosegundos para milissegundos
                
                if(tempoRestante < 0) tempoRestante = 0; // Se o tempo restante for negativo, define como 0
                
                
                Thread.sleep((long) tempoRestante); // Converte de nanosegundos para milissegundos e pausa a thread
            
                tempoDaProximaAtualizacao += intervaloDeAtualizacao; // Atualiza o tempo da próxima atualização
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        }
    }*/

    @Override
    public void run() {
        double intervaloAtualizacao = 1000000000 / FPS; // Intervalo de atualização em nanosegundos
        double delta = 0; // Variável para controlar o tempo acumulado
        long tempoAnterior = System.nanoTime(); // Marca o tempo inicial em nanosegundos
        long tempoAtual;
        long cronometro = 0;
        int quadrosDesenhados = 0;

        while (threadDoJogo != null) {
            tempoAtual = System.nanoTime(); 

            delta += (tempoAtual - tempoAnterior) / intervaloAtualizacao;
            cronometro += (tempoAtual - tempoAnterior); 
            tempoAnterior = tempoAtual; 

            if (delta >= 1) {
                atualizarJogo(); 
                //repaint();
                desenharTelaTemporaria();
                desenharTela();

                delta--; 
                quadrosDesenhados++;
            }

            if (cronometro >= 1000000000) { 
                System.out.println("FPS: " + quadrosDesenhados);
                quadrosDesenhados = 0;
                cronometro = 0; 
            }
        }
    }

    public void desenharTela(){
        Graphics g = getGraphics();
        g.drawImage(telaTemporaria, 0,0, larguraTela2, alturaTela2, null);
        g.dispose();
    }

    public void atualizarJogo() {
        // Lógica de atualização do jogo (ex: movimentação, colisões, etc.)
        
        if(estadoDoJogo == iniciarEstadoDoJogo){
            // Atualiza o estado do jogador
            jogador.atualizar(); 

            // Atualiza o estado do NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[mapaAtual][i] != null){
                    npc[mapaAtual][i].atualizar();
                }
            }

            //atualizar o estado do inimigo
            for(int i = 0; i < inimigo[1].length; i++){
                if(inimigo[mapaAtual][i] != null){
                    if(inimigo[mapaAtual][i].vivo == true && inimigo[mapaAtual][i].morrendo== false){
                        inimigo[mapaAtual][i].atualizar();
                    }
                    if(inimigo[mapaAtual][i].vivo == false){
                        inimigo[mapaAtual][i].verificarDrop();  //dropar item
                        inimigo[mapaAtual][i] = null;
                    }
                    
                }
            }

            //atualizar o estado do projetil - bola de fogo
            for(int i = 0; i < projetavel[1].length; i++){
                if(projetavel[mapaAtual][i] != null){
                    if(projetavel[mapaAtual][i].vivo == true){
                        projetavel[mapaAtual][i].atualizar();
                    }
                    if(projetavel[mapaAtual][i].vivo == false){
                        projetavel[mapaAtual][i] = null;
                    }
                    
                }
            }

            
            for(int i = 0; i < listaParticula.size(); i++){
                if(listaParticula.get(i) != null){
                    if(listaParticula.get(i).vivo == true){
                        listaParticula.get(i).atualizar();
                    }
                    if(listaParticula.get(i).vivo == false){
                        listaParticula.remove(i);
                    }
                    
                }
            }

            for(int i = 0; i < blocosI[1].length; i++){
                if(blocosI[mapaAtual][i] != null){
                    blocosI[mapaAtual][i].atualizar();
                }
            }
            gerenciadorDeAmbientes.atualizar();


        }
        if(estadoDoJogo == pausarEstadoDoJogo){

        }
        
    }

    public void desenharTelaTemporaria(){

        //debug
        long desenhoInicio = 0;
        if(teclado.mostrarTextoDebug == true){
            desenhoInicio = System.nanoTime();
        }

        //TITULO
        if(estadoDoJogo == tituloEstado){
            interfaceDoUsuario.desenhar(g2);
        
        }
        //tela do mapa
        else if(estadoDoJogo == estadoMapa){
            mapa.desenharMapaCompleto(g2);
        }
        else{
            // Desenha os tiles-blocos de imagem
            gerenciadorDeBlocos.desenhar(g2);
            
            // Desenha os tiles-blocos interativos
            for(int i = 0; i < blocosI[1].length; i++){
                if(blocosI[mapaAtual][i] != null){
                    blocosI[mapaAtual][i].desenhar(g2);
                }
            }

            listaEntidade.add(jogador);

            for(int i = 0; i < npc[1].length; i++){
                if(npc[mapaAtual][i] != null){
                    listaEntidade.add(npc[mapaAtual][i]);
                }
            }

            for(int i = 0; i < Obj[1].length; i++){
                if(Obj[mapaAtual][i] != null){
                    listaEntidade.add(Obj[mapaAtual][i]);
                }
            }

            for(int i = 0; i < inimigo[1].length; i++){
                if(inimigo[mapaAtual][i] != null){
                    listaEntidade.add(inimigo[mapaAtual][i]);
                }
            }

            for(int i = 0; i < projetavel[1].length; i++){
                if(projetavel[mapaAtual][i] != null){
                    listaEntidade.add(projetavel[mapaAtual][i]);
                }
            }

            for(int i = 0; i < listaParticula.size(); i++){
                if(listaParticula.get(i) != null){
                    listaEntidade.add(listaParticula.get(i));
                }
            }

            //organizar
            Collections.sort(listaEntidade, new Comparator<Entidade>(){

                @Override
                public int compare(Entidade e1, Entidade e2) {
                    
                    int resultado = Integer.compare(e1.mundoY, e2.mundoY);
                    return resultado;
                }

            });
            
            //desenhar entidades
            for(int i = 0; i < listaEntidade.size(); i++){
                listaEntidade.get(i).desenhar(g2);
            }
            //remover da lista de entidades
            listaEntidade.clear();
        
            //Ambiente de iliminação
            gerenciadorDeAmbientes.desenhar(g2);

            //mini mapa
            mapa.desenharMiniMapa(g2);

            //Cutscene
            gerenciadorDeCutscene.desenhar(g2);

            // Desenha a interface do usuário (UI) - depois dos tiles para não ficar escondida
            interfaceDoUsuario.desenhar(g2);

            //DEBUG
            if(teclado.mostrarTextoDebug == true){
                long desenhoFinal = System.nanoTime();
                long tempoDeDesenho = desenhoFinal - desenhoInicio;
                g2.setFont(new Font("Arial", Font.PLAIN, 26));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int linhaAltura = 20;
                g2.drawString("mundoX" + jogador.mundoX, x, y); y += linhaAltura;
                g2.drawString("mundoY" + jogador.mundoY, x, y); y += linhaAltura;
                g2.drawString("Coluna" + (jogador.mundoX + jogador.areaSolida.x) / tamanhoDoTile, x, y); y += linhaAltura;
                g2.drawString("Linha" + (jogador.mundoY + jogador.areaSolida.y) / tamanhoDoTile, x, y); y += linhaAltura;
                g2.drawString("Tempo de desenho: " + tempoDeDesenho, x, y); y += linhaAltura;
                g2.drawString("Modo de DeBug: " + teclado.modoDebugAtivo, x, y);
                

            }

        
        }
        
    }

    /*
    public void paintComponent(Graphics g) {
        // Método padrão do Java para desenhar no JPanel
        super.paintComponent(g); // Chama o método da superclasse (JPanel), já que PainelDoJogo é uma subclasse
        
        Graphics2D g2 = (Graphics2D) g;

        //debug
        long desenhoInicio = 0;
        if(teclado.mostrarTextoDebug == true){
            desenhoInicio = System.nanoTime();
        }

        //TITULO
        if(estadoDoJogo == tituloEstado){
            interfaceDoUsuario.desenhar(g2);
        }else{
            // Desenha os tiles-blocos de imagem
            gerenciadorDeBlocos.desenhar(g2);
            
            // Desenha os tiles-blocos interativos
            for(int i = 0; i < blocosI.length; i++){
                if(blocosI[i] != null){
                    blocosI[i].desenhar(g2);
                }
            }

            listaEntidade.add(jogador);

            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    listaEntidade.add(npc[i]);
                }
            }

            for(int i = 0; i < Obj.length; i++){
                if(Obj[i] != null){
                    listaEntidade.add(Obj[i]);
                }
            }

            for(int i = 0; i < inimigo.length; i++){
                if(inimigo[i] != null){
                    listaEntidade.add(inimigo[i]);
                }
            }

            for(int i = 0; i < listaProjetil.size(); i++){
                if(listaProjetil.get(i) != null){
                    listaEntidade.add(listaProjetil.get(i));
                }
            }

            for(int i = 0; i < listaParticula.size(); i++){
                if(listaParticula.get(i) != null){
                    listaEntidade.add(listaParticula.get(i));
                }
            }

            //organizar
            Collections.sort(listaEntidade, new Comparator<Entidade>(){

                @Override
                public int compare(Entidade e1, Entidade e2) {
                    
                    int resultado = Integer.compare(e1.mundoY, e2.mundoY);
                    return resultado;
                }

            });
            
            //desenhar entidades
            for(int i = 0; i < listaEntidade.size(); i++){
                listaEntidade.get(i).desenhar(g2);
            }
            //remover da lista de entidades
            listaEntidade.clear();
        

            // Desenha a interface do usuário (UI) - depois dos tiles para não ficar escondida
            interfaceDoUsuario.desenhar(g2);

            //DEBUG
            if(teclado.mostrarTextoDebug == true){
                long desenhoFinal = System.nanoTime();
                long tempoDeDesenho = desenhoFinal - desenhoInicio;
                g2.setFont(new Font("Arial", Font.PLAIN, 26));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int linhaAltura = 20;
                g2.drawString("mundoX" + jogador.mundoX, x, y); y += linhaAltura;
                g2.drawString("mundoY" + jogador.mundoY, x, y); y += linhaAltura;
                g2.drawString("Coluna" + (jogador.mundoX + jogador.areaSolida.x) / tamanhoDoTile, x, y); y += linhaAltura;
                g2.drawString("Linha" + (jogador.mundoY + jogador.areaSolida.y) / tamanhoDoTile, x, y); y += linhaAltura;

                g2.drawString("Tempo de desenho: " + tempoDeDesenho, x, y);
                
            }
        
        }
        g2.dispose(); // Desenha um retângulo branco cobrindo toda a tela
        
    } */


    public void iniciarMusica( int i){
        musica.setArquivo(i);
        musica.iniciar();
        musica.repetir();
    }

    public void pararMusica() {
        musica.parar();
    }

    public void iniciarEfeitoSonoro(int i){
        efeitoSonoro.setArquivo(i); 
        efeitoSonoro.iniciar(); 
    }

    public void alterarArea(){

        if(proximaArea != areaAtual){
            pararMusica();

            if(proximaArea == fora){
                iniciarMusica(0);
            }

            if(proximaArea == interior){
                iniciarMusica(18);
            }
            
            if(proximaArea == masmorra){
                iniciarMusica(19);
            }

            criarObjetos.setNpc();
        }
        
        areaAtual = proximaArea;
        criarObjetos.setInimigos(); //aSetter- criarObjetos

    }

    public void removerTempDaEntidade(){

        for(int numeroMapa = 0; numeroMapa < maxMapa; numeroMapa++){
            
            for(int i =0; i < Obj[1].length; i++){
                if(Obj[numeroMapa][i] != null && Obj[numeroMapa][i].temp == true){
                    Obj[numeroMapa][i] = null;
                }
            }
        }
    }

}