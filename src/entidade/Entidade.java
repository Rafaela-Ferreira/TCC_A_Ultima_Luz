package entidade;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.CaixaDeFerramentas;
import main.PainelDoJogo;

//Essa é uma classe abstrata, não tem instacia - sempre instaciamos como classe jogador ou npc ou inimigo.

public class Entidade {
    //classe pai para a classe player, inimigo, npcs, etc.
    PainelDoJogo painel;
    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;
    public BufferedImage ataqueCima1, ataqueCima2, ataqueBaixo1, ataqueBaixo2, ataqueEsquerda1, 
                         ataqueEsquerda2, ataqueDireita1, ataqueDireita2,
                         defesaCima, defesaBaixo, defesaEsquerda, defesaDireita;
    public BufferedImage imagem, imagem2, imagem3;
    public Rectangle areaSolida = new Rectangle(0,0,48,48); // Área sólida padrão para todas as entidades
    public Rectangle areaAtaque = new Rectangle(0,0,0,0);
    public int areaSolidaPadraoX, areaSolidaPadraoY;
    public boolean colisaoComBloco = false; // Variável para verificar colisão com blocos
    public String dialogo[][] = new String[20][20];
    public Entidade atacante;
    public Entidade entidadeVinculada; //Ex: vincular a pedraGrande a uma PlacaDeMetal
    public boolean temp = false;
    
    
    //Estado
    public int mundoX, mundoY;
    public String direcao = "baixo";
    public int numeroDoSprite = 1;
    public int setDialogo = 0;
    public int indiceDoDialogo = 0;
    public boolean temColisao = false;
    public boolean invencivel = false;
    public boolean atacar = false;
    public boolean vivo = true;
    public boolean morrendo = false;
    public boolean barraDeVidaAtiva = false;
    public boolean empurrao = false;
    public String direcaoDoempurrao; //knockBackDirection
    public boolean defender = false;
    public boolean transparente = false;
    public boolean equilibrioDesativar = false; // offBalance
    //saquear bau
    public Entidade saque;
    public boolean aberto = false;
    public boolean furia = false;
    public boolean dormir = false; //cutsine
    public boolean desenho = true;

    //contador
    public int contadorDeSprite = 0;
    public int contadorDeBloqueioDeAcao = 0;
    public int invencivelContador = 0;
    public int contadorDeTiro = 0;
    int contadorMorrendo = 0;
    public int contadorBarraDeVida = 0;
    int contadoEmpurrao = 0;
    public int contadorDeGuarda = 0; //guardCounter
    int contadorDeEquilibrioDesativar = 0; //offBalanceCounter

    //IA
    public boolean pastaAtiva = false;
    
    //Atributos do jogador/personagens
    public String nome;
    public int velocidadePadrao;
    public int velocidade;
    public int vidaMaxima;
    public int vida;
    public int manaMaxima;
    public int mana;
    public int municao;
    public int nivel;
    public int forca;
    public int destreza;
    public int ataque;
    public int defesa;
    public int exp;
    public int proximoNivelExp;
    public int moeda;
    public int direcaoDoMovimento1; //orc - ataque padrão
    public int direcaoDoMovimento2; //orc - ataque pesado
    public Entidade armaAtual;
    public Entidade escudoAtual;
    public Entidade luzAtual;
    public Projetil projetil;
    public boolean chefe;

    //atributos dos Itens
    //inventario
    public ArrayList<Entidade> inventario = new ArrayList<>();
    public final int tamanhoMaximoInventario = 20;
    public int valor;
    public int valorAtaque;
    public int valorDefesa;
    public String descricao = "";
    public int usarConsumivel;
    public int preco;
    public int poderDoEmpurrao = 0;
    public boolean empilhavel = false;
    public int quantidade = 1;
    public int raioDeLuz;
    public int durabilidade; //implementar condições para ser necessario melhorar a arma.

    //Tipo de entidade
    public int tipo; //0 -> jogador, 1 -> npc, 2 -> inimigo....
    public final int tipoJogador = 0;
    public final int tipoNpc = 1;
    public final int tipoInimigo = 2;
    public final int tipoEspada = 3; 
    public final int tipoMachado = 4;
    public final int tipoEscudo = 5;
    public final int tipoConsumivel = 6;
    public final int tipoRetirada = 7; //type_pickupOnly
    public final int tipoObstaculo = 8;
    public final int tipoIliminacao = 9;
    public final int tipoPicareta = 10;



    public Entidade(PainelDoJogo painel){
        this.painel = painel;
    }

    public int getTelaX(){
        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        return telaX;
    }

    public int getTelaY(){
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;
        return telaY;
    }

    public int getEsquerdaX(){
        return mundoX + areaSolida.x;
    }

    public int getDireitaX(){
        return mundoX + areaSolida.x + areaSolida.width;
    }

    public int getCimaY(){
        return mundoY + areaSolida.y;
    }

    public int getBaixoY(){
        return mundoY + areaSolida.y + areaSolida.height;
    }

    public int getColuna(){
        return (mundoX + areaSolida.x) / painel.tamanhoDoTile;
    }

    public int getLinha(){
        return (mundoY + areaSolida.y) / painel.tamanhoDoTile;
    }
    public int getCentroX(){
        int centroX = mundoX + esquerda1.getWidth()/2;
        return centroX;
    }
    public int getCentroY(){
        int centroY = mundoY + cima1.getHeight()/2;
        return centroY;
    }

    public int getDistaciaX(Entidade alvo){
        int Xdistancia = Math.abs(getCentroX() - alvo.getCentroX());
        return Xdistancia;
    }

    public int getDistaciaY(Entidade alvo){
        int Ydistancia = Math.abs(getCentroY() - alvo.getCentroY());
        return Ydistancia;   
    }

    public int getDistaciaDoBloco(Entidade alvo){
        int distaciaDoBloco = (getDistaciaX(alvo) + getDistaciaY(alvo)) / painel.tamanhoDoTile;
        return distaciaDoBloco;
        
    }
    public int getColunaAtual(Entidade alvo){
        int colunaAtual = (alvo.mundoX + alvo.areaSolida.x) / painel.tamanhoDoTile;
        return colunaAtual;
    }

    public int getLinhaAtual(Entidade alvo){
        int linhaAtual = (alvo.mundoY + alvo.areaSolida.y) / painel.tamanhoDoTile;
        return linhaAtual;
    }

    public void reiniciarContador(){
        contadorDeSprite = 0;
        contadorDeBloqueioDeAcao = 0;
        invencivelContador = 0;
        contadorDeTiro = 0;
        contadorMorrendo = 0;
        contadorBarraDeVida = 0;
        contadoEmpurrao = 0;
        contadorDeGuarda = 0; //guardCounter
        contadorDeEquilibrioDesativar = 0; //offBalanceCounter
    }

    public void setSaque(Entidade saque){ }

    public void setAcao(){ }

    public void mover(String direcao){ }

    public void acaoAoDano(){ }

    public void falar(){   }

    public void faceJogador(){
        switch (painel.jogador.direcao) {
            case "cima":
                direcao = "baixo";
                break;
            case "baixo":
                direcao = "cima";
                break;
            case "esquerda":
                direcao = "direita";
                break;
            case "direita":
                direcao = "esquerda";
                break;
        }
    }
    public void iniciarDialogo(Entidade entidade, int setNum){
        painel.estadoDoJogo = painel.estadoDoDialogo;
        painel.interfaceDoUsuario.npc = entidade;
        setDialogo = setNum;
    }

    public void interagir(){

    }


    public boolean usar(Entidade entidade){ return false; }

    public void verificarDrop(){}

    public void droparItem(Entidade droparItem){
        for(int i = 0; i < painel.Obj[1].length; i++){
            if(painel.Obj[painel.mapaAtual][i] == null){
                painel.Obj[painel.mapaAtual][i] = droparItem;
                painel.Obj[painel.mapaAtual][i].mundoX = mundoX;
                painel.Obj[painel.mapaAtual][i].mundoY = mundoY;
                break;
            }
        }
    }
    public Color getParticulaCor(){
        Color cor = null;
        return cor;
    }

    public int getParticulaTamanho(){
        int tamanho = 0; //6 pixels
        return tamanho;
    }
    public int getParticulaVelocidade(){
        int velocidade = 0;
        return velocidade;
    } 

    public int getParticulaVidaMaxima(){
        int vidaMaxima = 0;
        return vidaMaxima;
    }

    public void geradorParticula(Entidade gerador, Entidade alvo){
        Color cor = gerador.getParticulaCor();
        int tamanho = gerador.getParticulaTamanho();
        int velocidade = gerador.getParticulaVelocidade();
        int vidaMaxima = gerador.getParticulaVidaMaxima();

        Particula p1 = new Particula( painel, alvo, cor, tamanho, velocidade, vidaMaxima, -2, -1);
        Particula p2 = new Particula( painel, alvo, cor, tamanho, velocidade, vidaMaxima, 2, -1);
        Particula p3 = new Particula( painel, alvo, cor, tamanho, velocidade, vidaMaxima, -2, 1);
        Particula p4 = new Particula( painel, alvo, cor, tamanho, velocidade, vidaMaxima, 2, 1);
        painel.listaParticula.add(p1);
        painel.listaParticula.add(p2);
        painel.listaParticula.add(p3);
        painel.listaParticula.add(p4);
    }

    public void verificarColisao(){
        
        colisaoComBloco = false;
        painel.colisaoChecked.verificarColisao(this);
        painel.colisaoChecked.verificarObjeto(this, false);
        painel.colisaoChecked.verificarEntidade(this, painel.npc);
        painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
        painel.colisaoChecked.verificarEntidade(this, painel.blocosI);

        boolean contatoComJogador = painel.colisaoChecked.verificarJogador(this);

        if(this.tipo == tipoInimigo && contatoComJogador == true){
            danoJogador(ataque);
        }
    }



    public void atualizar(){

        if(dormir == false){

        
        
            if(empurrao == true){

                verificarColisao();

                if(colisaoComBloco == true){
                    contadoEmpurrao = 0;
                    empurrao = false;
                    velocidade = velocidadePadrao;
                }
                else if(colisaoComBloco == false){
                    switch (direcaoDoempurrao) {
                        case "cima": mundoY -= velocidade; break;
                        case "baixo": mundoY += velocidade;break;
                        case "esquerda": mundoX -= velocidade; break;
                        case "direita": mundoX += velocidade; break;
                    }
                }

                contadoEmpurrao++;
                if(contadoEmpurrao == 10){
                    contadoEmpurrao = 0;
                    empurrao = false;
                    velocidade = velocidadePadrao;
                }

            }
            else if(atacar == true){
                ataque();
            }
            else{
                setAcao();

                verificarColisao();

                //se colição for false,
                if(colisaoComBloco == false) {
                    // Atualiza a posição do jogador
                    switch (direcao) {
                        case "cima":
                            mundoY -= velocidade; break;
                        case "baixo":
                            mundoY += velocidade;break;
                        case "esquerda":
                            mundoX -= velocidade; break;
                        case "direita":
                            mundoX += velocidade; break;
                    }  
                    
                }

                // Controle de animação do sprite
                contadorDeSprite++;
                if(contadorDeSprite  > 24) { // A cada 10 atualizações, troca o sprite
                    if(numeroDoSprite  == 1) {
                        numeroDoSprite  = 2; // Alterna para o segundo sprite
                    } else if(numeroDoSprite  == 2) {
                        numeroDoSprite  = 1; // Alterna para o primeiro sprite
                    }
                    contadorDeSprite  = 0; // Reseta o contador de sprites
                }
            }
            
            if(invencivel == true){
                invencivelContador++;
                if(invencivelContador > 40){
                    invencivel = false;
                    invencivelContador = 0;
                }
            }

            if(contadorDeTiro < 30){
                contadorDeTiro++;
            }

            if(equilibrioDesativar == true){
                contadorDeEquilibrioDesativar++;

                if(contadorDeEquilibrioDesativar > 60){
                    equilibrioDesativar = false;
                    contadorDeEquilibrioDesativar = 0;
                }
            }
        }


    }

    public void verificarSeAtirou_ou_nao(int taxa, int intervaloDeTiro){
        //verifique se ele atira um projétil
        //começa a atirar pedra
        int i = new Random().nextInt(taxa);

        if(i == 0 && projetil.vivo == false && contadorDeTiro == intervaloDeTiro){
            
            projetil.setAcao(mundoX, mundoY, direcao, true, this);
            //painel.listaProjetil.add(projetil); 

            //verificar vaga
            for(int ii = 0; ii < painel.projetavel[1].length; ii++){
                if(painel.projetavel[painel.mapaAtual][ii] == null){
                    painel.projetavel[painel.mapaAtual][ii] = projetil;
                    break;
                }
            }

            contadorDeTiro = 0;
        }
    }

    public void verificarSeAtacou_ou_nao(int taxa, int direta, int horizontal){
        boolean alcanceDoAlvo = false;
        int Xdistancia = getDistaciaX(painel.jogador);
        int Ydistancia = getDistaciaY(painel.jogador);

        switch (direcao) {
            case "cima": 
                if(painel.jogador.getCentroY() < getCentroY() && Ydistancia < direta && Xdistancia < horizontal){
                    alcanceDoAlvo = true;
                } 
            break;

            case "baixo": 
                if(painel.jogador.getCentroY() > getCentroY() && Ydistancia < direta && Xdistancia < horizontal){
                    alcanceDoAlvo = true;
                } 
            break;

            case "esquerda": 
                if(painel.jogador.getCentroX() < getCentroX() && Xdistancia < direta && Ydistancia < horizontal){
                    alcanceDoAlvo = true;
                } 
            break;

            case "direita": 
                if(painel.jogador.getCentroX() > getCentroX() && Xdistancia < direta && Ydistancia < horizontal){
                    alcanceDoAlvo = true;
                } 
            break;
        }
        
        if(alcanceDoAlvo == true){
            //check if it initiates an aattack
            int i = new Random().nextInt(taxa);
            if(i == 0){
                atacar = true;
                numeroDoSprite = 1;
                contadorDeSprite = 0;
                contadorDeTiro = 0;
            }
        }
    }

    public void verificarSeComecouAPerseguir_ou_nao(Entidade alvo, int distancia, int taxa){
        if(getDistaciaDoBloco(alvo) < distancia){
            int i = new Random().nextInt(taxa);
            if(i == 0){
                pastaAtiva = true;//caminho
            }
        }
    }

    public void verificarSeParouDePerseguir_ou_nao(Entidade alvo, int distancia, int taxa){
        if(getDistaciaDoBloco(alvo) > distancia){
            int i = new Random().nextInt(taxa);
            if(i == 0){
                pastaAtiva = false;//caminho
            }
        }
    }

    public void getDirecaoAleatoria(int intervalo){
        //obter uma direção aleatória
            contadorDeBloqueioDeAcao++;

            //esperar 120 (2 segundos) para mudar de direção
            if(contadorDeBloqueioDeAcao > intervalo){
                Random random = new Random();
                int i = random.nextInt(100) + 1; //0 - 100

                if(i <= 25){ direcao = "cima"; }
                if(i > 25 && i <= 50){ direcao = "baixo";}
                if(i > 50 && i <= 75){ direcao = "esquerda"; }
                if(i > 75 && i <= 100){ direcao = "direita"; }

                contadorDeBloqueioDeAcao = 0;
            }
    }

    public void moverEmDirecaoAoJogador(int intervalo){
        contadorDeBloqueioDeAcao++;

        if(contadorDeBloqueioDeAcao > intervalo){
            if(getDistaciaX(painel.jogador) > getDistaciaY(painel.jogador)){
                
                if(painel.jogador.getCentroX() < getCentroX()){
                    direcao = "esquerda";
                }
                else{
                    direcao = "direita";
                }
            }
            else if(getDistaciaX(painel.jogador) < getDistaciaY(painel.jogador)){
                if(painel.jogador.getCentroY() < getCentroY()){
                    direcao = "cima";
                }
                else{
                    direcao = "baixo";
                }
            }

            contadorDeBloqueioDeAcao = 0;

        }

    }

    public String getDirecaoOposta(String direcao){
        String direcaoOposta = "";

        switch (direcao) {
            case "cima": direcaoOposta = "baixo"; break;
            case "baixo": direcaoOposta = "cima"; break;
            case "esquerda": direcaoOposta = "direita"; break;
            case "direita": direcaoOposta = "esquerda"; break;
        }

        return direcaoOposta;
    }


    public void ataque(){
        contadorDeSprite++;

        if(contadorDeSprite <= direcaoDoMovimento1){
            numeroDoSprite = 1;
        }
        if(contadorDeSprite > direcaoDoMovimento1 && contadorDeSprite <= direcaoDoMovimento2){
            numeroDoSprite = 2;

            //Salvar posição de mundoX, mundoY, areaSolida
            int AtualMundoX = mundoX;
            int AtualMundoY = mundoY;
            int areaSolidaLargura = areaSolida.width;
            int areaSolidaAltura = areaSolida.height;

            // Ajustar área de mundoX/Y e ataque 
            switch (direcao) {
                case "cima": mundoY -= areaAtaque.height; break;
                case "baixo": mundoY += areaAtaque.height; break;
                case "esquerda": mundoX -= areaAtaque.width; break;
                case "direita": mundoX += areaAtaque.width; break;
            }

            areaSolida.width = areaAtaque.width;
            areaSolida.height = areaAtaque.height;

            if(tipo == tipoInimigo){
                if(painel.colisaoChecked.verificarJogador(this) == true){
                    danoJogador(ataque);
                }
            }else{
                //jogador
                //verifique a colisão do monstro com o mundo atualizado mundoX, mundoY e área sólida
                int indiceInimigo = painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
                painel.jogador.danoDoInimigo(indiceInimigo, this, ataque, armaAtual.poderDoEmpurrao);

                int indiceBlocosI = painel.colisaoChecked.verificarEntidade(this, painel.blocosI);
                painel.jogador.danoNoBlocoInterativo(indiceBlocosI);

                int indiceProjetavel = painel.colisaoChecked.verificarEntidade(this, painel.projetavel);
                painel.jogador.danoDoProjetavel(indiceProjetavel);
            }

            mundoX = AtualMundoX;
            mundoY = AtualMundoY;
            areaSolida.width = areaSolidaLargura;
            areaSolida.height = areaSolidaAltura;

        }
        if(contadorDeSprite > direcaoDoMovimento2){
            numeroDoSprite = 1;
            contadorDeSprite = 0;
            atacar = false;
        }
    }

    public void danoJogador(int ataque){

        if(painel.jogador.invencivel == false){

            int dano = ataque - painel.jogador.defesa;

            //Direção que o jogador pode defender
            String podeProtegerA_Direcao = getDirecaoOposta(direcao);

            if(painel.jogador.defender == true && painel.jogador.direcao.equals(podeProtegerA_Direcao)){
                
                //parry - desviar/quebrar guarda
                if(contadorDeGuarda < 10){
                    dano = 0;
                    painel.iniciarEfeitoSonoro(6);

                    setEmpurrao(this, painel.jogador, poderDoEmpurrao);
                    equilibrioDesativar = true;
                    
                    //efeito de atordoamento
                    contadorDeSprite =- 60; 
                    this.invencivel = false;
                    
                }
                else{
                    
                    dano /= 3;
                    painel.iniciarEfeitoSonoro(15);
                }
            }
            else{
                //não protegeu
                painel.iniciarEfeitoSonoro(6);
                if(dano < 1){
                    dano = 1;
                }
            }
            
            if(dano != 0){
                painel.jogador.transparente = true;
                setEmpurrao(painel.jogador , this, poderDoEmpurrao);
            }

            painel.jogador.vida -= dano;
            painel.jogador.invencivel = true;
        }
    }


    public void setEmpurrao(Entidade alvo, Entidade atacante, int poderDoEmpurrao){
        this.atacante = atacante;
        alvo.direcaoDoempurrao = atacante.direcao;
        alvo.velocidade += poderDoEmpurrao;
        alvo.empurrao = true;
    }

    public boolean camera(){
        boolean camera = false;

        // Verifica se o bloco está dentro da área visível do jogador
        if (mundoX + painel.tamanhoDoTile*5 > painel.jogador.mundoX - painel.jogador.telaX && 
            mundoX - painel.tamanhoDoTile < painel.jogador.mundoX + painel.jogador.telaX &&
            mundoY + painel.tamanhoDoTile*5 > painel.jogador.mundoY - painel.jogador.telaY &&
            mundoY - painel.tamanhoDoTile < painel.jogador.mundoY + painel.jogador.telaY) {

            camera = true;
        }

        return camera;
    }

    public void desenhar(Graphics2D g2){
        
        BufferedImage imagem = null;

        // Verifica se o bloco está dentro da área visível do jogador
        if (camera() == true) {

            int telaTemporariaX = getTelaX();
            int telaTemporariaY = getTelaY();
            
            switch (direcao) {
                case "cima":
                    if(atacar == false){
                        if(numeroDoSprite  == 1){ imagem = cima1; }
                        if(numeroDoSprite  == 2){ imagem = cima2; }
                    }
                    if(atacar == true){
                        telaTemporariaY = getTelaY() - cima1.getHeight();
                        if(numeroDoSprite  == 1){ imagem = ataqueCima1; }
                        if(numeroDoSprite  == 2){ imagem = ataqueCima2; }
                    }
                    
                break;
                case "baixo":
                    if(atacar == false){
                        if(numeroDoSprite  == 1){ imagem = baixo1; }
                        if(numeroDoSprite  == 2){ imagem = baixo2; }
                    }
                    if(atacar == true){
                        if(numeroDoSprite  == 1){ imagem = ataqueBaixo1; }
                        if(numeroDoSprite  == 2){ imagem = ataqueBaixo2; }
                    }
                break;
                case "esquerda":
                    if(atacar == false){
                        if(numeroDoSprite  == 1){ imagem = esquerda1; }
                        if(numeroDoSprite  == 2){ imagem = esquerda2; }
                    }
                    if(atacar == true){
                        telaTemporariaX = getTelaX() - esquerda1.getWidth();
                        if(numeroDoSprite  == 1){ imagem = ataqueEsquerda1; }
                        if(numeroDoSprite  == 2){ imagem = ataqueEsquerda2; }
                    }
                    
                break;
                case "direita":
                if(atacar == false){
                    if(numeroDoSprite  == 1){ imagem = direita1; }
                    if(numeroDoSprite  == 2){ imagem = direita2; }
                }
                if(atacar == true){
                    if(numeroDoSprite  == 1){ imagem = ataqueDireita1; }
                    if(numeroDoSprite  == 2){ imagem = ataqueDireita2; }
                }
                break;
            }

            
            //deixa transparente
            if(invencivel == true){
                barraDeVidaAtiva = true;
                contadorBarraDeVida =0;
                alterarAlfa(g2, 0.4f);
            }
            if(morrendo == true){
                animacaoMorrendo(g2);
            }

            g2.drawImage(imagem, telaTemporariaX, telaTemporariaY, null);

            alterarAlfa(g2, 1f);
            
        }
        
    }

    public void animacaoMorrendo(Graphics2D g2){
        contadorMorrendo++;
        int i = 5;

        if(contadorMorrendo <= i) alterarAlfa(g2, 0f);
        if(contadorMorrendo > i*2 && contadorMorrendo <= i*2) alterarAlfa(g2, 1f);
        if(contadorMorrendo > i*3 && contadorMorrendo <= i*3) alterarAlfa(g2, 0f);
        if(contadorMorrendo > i*4 && contadorMorrendo <= i*4) alterarAlfa(g2, 1f);
        if(contadorMorrendo > i*5 && contadorMorrendo <= i*5) alterarAlfa(g2, 0f);
        if(contadorMorrendo > i*6 && contadorMorrendo <= i*6) alterarAlfa(g2, 1f);
        if(contadorMorrendo > i*7 && contadorMorrendo <= i*7) alterarAlfa(g2, 0f);
        if(contadorMorrendo > i*8 && contadorMorrendo <= i*8) alterarAlfa(g2, 1f);
        if(contadorMorrendo > i*8){
            vivo = false;
        }
    }

    public void alterarAlfa(Graphics2D g2, float valorAlfa){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, valorAlfa));
    }

    public BufferedImage setup(String caminho, int altura, int largura){
        
        CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
        BufferedImage imagem = null;

        try{
            imagem = ImageIO.read(getClass().getResourceAsStream(caminho + ".png"));
            //System.out.println(imagem == null ? "Imagem NÃO encontrada" : "Imagem encontrada");
            imagem = ferramentas.escalaImage(imagem, altura, largura);

        }catch(IOException e){
            e.printStackTrace();
        }

        return imagem;
    }

    public void procurarCaminho(int metaColuna, int metaLinha){

        int iniciarColuna = (mundoX + areaSolida.x) / painel.tamanhoDoTile;
        int iniciarLinha = (mundoY + areaSolida.y) / painel.tamanhoDoTile;

        painel.localizarCaminhos.setNos(iniciarColuna, iniciarLinha, metaColuna, metaLinha, this);

        if(painel.localizarCaminhos.procurar() == true){
            
            //next worldX e worldY
            int proximoX = painel.localizarCaminhos.listaCaminho.get(0).coluna * painel.tamanhoDoTile;
            int proximoY = painel.localizarCaminhos.listaCaminho.get(0).linha * painel.tamanhoDoTile;

            //entity's solidArea position
            int entidadeEsquerdaX = mundoX + areaSolida.x;
            int entidadeDireitaX = mundoX + areaSolida.x + areaSolida.width;

            int entidadeCimaY = mundoY + areaSolida.y;
            int entidadeBaixoY = mundoY + areaSolida.y + areaSolida.height;

            if (entidadeCimaY > proximoY && entidadeEsquerdaX >= proximoX && entidadeDireitaX < proximoX + painel.tamanhoDoTile){
                direcao  = "cima";
            }

            else if (entidadeCimaY < proximoY && entidadeEsquerdaX >= proximoX && entidadeDireitaX < proximoX + painel.tamanhoDoTile){
                direcao  = "baixo";
            }
            else if(entidadeCimaY >= proximoY && entidadeBaixoY < proximoY + painel.tamanhoDoTile){
                //esquerda ou direita
                if(entidadeEsquerdaX > proximoX){
                    direcao  = "esquerda";
                }
                if(entidadeEsquerdaX < proximoX){
                    direcao  = "direita";
                }
            }
            else if(entidadeCimaY > proximoY && entidadeEsquerdaX > proximoX){
                // cima ou esquerda
                direcao = "cima";
                verificarColisao();
                if(colisaoComBloco == true){
                    direcao = "esquerda";
                }
            }
            else if(entidadeCimaY > proximoY && entidadeEsquerdaX < proximoX){
                //cima ou direita
                direcao = "cima";
                verificarColisao();
                if(colisaoComBloco == true){
                    direcao = "direita";
                }
            }
            else if(entidadeCimaY < proximoY && entidadeEsquerdaX > proximoX){
                //baixo ou esquerda
                direcao = "baixo";
                verificarColisao();
                if(colisaoComBloco == true){
                    direcao = "esquerda";
                }
            }

            else if(entidadeCimaY < proximoY && entidadeEsquerdaX < proximoX){
                //baixo ou direita
                direcao = "baixo";
                verificarColisao();
                if(colisaoComBloco == true){
                    direcao = "direita";
                }
            }

            //Se o jogador for o objetivo - desabilitar/comentar esse trecho
            //if reaches the goal, stop the search
            /*
            int proximaColuna = painel.localizarCaminhos.listaCaminho.get(0).coluna;
            int proximaLinha = painel.localizarCaminhos.listaCaminho.get(0).linha;
            if(proximaColuna == metaColuna && proximaLinha == metaLinha){
                pastaAtiva = false;
            } 
            */
            
        }

    }

    public int getDetectar(Entidade usar, Entidade alvo[][], String nomeDoAlvo){
        int indice = 999;

        //verifique o objeto ao redor
    
        int prixmoMundoX = usar.getEsquerdaX();
        int prixmoMundoY = usar.getCimaY();
        
        switch (usar.direcao) {
            case "cima": prixmoMundoY = usar.getCimaY() -painel.jogador.velocidade; break;
            case "baixo": prixmoMundoY = usar.getBaixoY() +painel.jogador.velocidade; break;
            case "esquerda": prixmoMundoX = usar.getEsquerdaX() -painel.jogador.velocidade; break;
            case "direita": prixmoMundoX = usar.getDireitaX() +painel.jogador.velocidade; break;

        }
        int coluna = prixmoMundoX/painel.tamanhoDoTile;
        int linha = prixmoMundoY/ painel.tamanhoDoTile;


        for(int i = 0; i < alvo[1].length; i++){
            if(alvo[painel.mapaAtual][i] != null){
                if(alvo[painel.mapaAtual][i].getColuna() == coluna && 
                        alvo[painel.mapaAtual][i].getLinha() == linha &&
                        alvo[painel.mapaAtual][i].nome.equals(nomeDoAlvo)){
                    
                        indice = i;
                        break;
                }
            }
        }
        return indice;

    }
}
