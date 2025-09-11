package entidade;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.CaixaDeFerramentas;
import main.PainelDoJogo;

//Essa é uma classe abstrata, não tem instacia - sempre instaciamos como classe jogador ou npc ou monstro.

public class Entidade {
    //classe pai para a classe player, inimigo, npcs, etc.
    PainelDoJogo painel;
    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;
    public BufferedImage ataqueCima1, ataqueCima2, ataqueBaixo1, ataqueBaixo2, ataqueEsquerda1, ataqueEsquerda2, ataqueDireita1, ataqueDireita2;
    public BufferedImage imagem, imagem2, imagem3;
    public Rectangle areaSolida = new Rectangle(0,0,48,48); // Área sólida padrão para todas as entidades
    public Rectangle areaAtaque = new Rectangle(0,0,0,0);
    public int areaSolidaPadraoX, areaSolidaPadraoY;
    public boolean colisaoComBloco = false; // Variável para verificar colisão com blocos
    String dialogo[] = new String[20];
    
    
    //Estado
    public int mundoX, mundoY;
    public String direcao = "baixo";
    public int numeroDoSprite = 1;
    int indiceDoDialogo = 0;
    public boolean temColisao = false;
    public boolean invencivel = false;
    boolean atacar = false;
    public boolean vivo = true;
    public boolean morrendo = false;
    boolean barraDeVidaAtiva = false;

    //contador
    public int contadorDeSprite = 0;
    public int contadorDeBloqueioDeAcao = 0;
    public int invencivelContador = 0;
    public int contadorDeTiro = 0;
    int contadorMorrendo = 0;
    int contadorBarraDeVida = 0;
    
    //Atributos do jogador
    public String nome;
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
    public Entidade armaAtual;
    public Entidade EscudoAtual;
    public Projetil projetil;

    //atributos dos Itens
    public int valor;
    public int valorAtaque;
    public int valorDefesa;
    public String descricao = "";
    public int usarConsumivel;

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


    public Entidade(PainelDoJogo painel){
        this.painel = painel;
    }
    public void setAcao(){ }

    public void acaoAoDano(){ }

    public void falar(){
        if(dialogo[indiceDoDialogo] == null){
            indiceDoDialogo = 0;//se não houver dialogos, volta ao indece zero.
        }
        
        painel.interfaceDoUsuario.dialogoAtual = dialogo[indiceDoDialogo];
        indiceDoDialogo++;
        
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
    public void usar(Entidade entidade){}

    public void verificarDrop(){}

    public void droparItem(Entidade droparItem){
        for(int i = 0; i < painel.Obj.length; i++){
            if(painel.Obj[i] == null){
                painel.Obj[i] = droparItem;
                painel.Obj[i].mundoX = mundoX;
                painel.Obj[i].mundoY = mundoY;
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

    public void atualizar(){
        setAcao();

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
            
            // Controle de animação do sprite
            contadorDeSprite++;
            if(contadorDeSprite  > 12) { // A cada 10 atualizações, troca o sprite
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


    }

    public void danoJogador(int ataque){
        if(painel.jogador.invencivel == false){
            painel.iniciarEfeitoSonoro(6);
            
            int dano = ataque - painel.jogador.defesa;
            if(dano < 0){
                dano = 0;

            }
            painel.jogador.vida -= dano;
            painel.jogador.invencivel = true;
        }
    }

    public void desenhar(Graphics2D g2){
        
        BufferedImage imagem = null;

        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

        // Verifica se o bloco está dentro da área visível do jogador
        if (mundoX + painel.tamanhoDoTile > painel.jogador.mundoX - painel.jogador.telaX && 
            mundoX - painel.tamanhoDoTile < painel.jogador.mundoX + painel.jogador.telaX &&
            mundoY + painel.tamanhoDoTile > painel.jogador.mundoY - painel.jogador.telaY &&
            mundoY - painel.tamanhoDoTile < painel.jogador.mundoY + painel.jogador.telaY) {

            switch (direcao) {
                case "cima":
                    if(numeroDoSprite  == 1){ imagem = cima1; }
                    if(numeroDoSprite  == 2){ imagem = cima2; } break;
                case "baixo":
                    if(numeroDoSprite  == 1){ imagem = baixo1; }
                    if(numeroDoSprite  == 2){ imagem = baixo2; } break;
                case "esquerda":
                    if(numeroDoSprite  == 1){ imagem = esquerda1;}
                    if(numeroDoSprite  == 2){ imagem = esquerda2; } break;
                case "direita":
                    if(numeroDoSprite  == 1){ imagem = direita1;}
                    if(numeroDoSprite  == 2){ imagem = direita2; } break;
            }
            //desenhar a barra de vida do inimigo
            if(tipo == 2 && barraDeVidaAtiva == true){
                double umaEscala = (double)painel.tamanhoDoTile/vidaMaxima;
                double valorBarraDeVida = umaEscala*vida; 

                g2.setColor(new Color(35,35,35));
                g2.fillRect(telaX-1, telaY - 16, painel.tamanhoDoTile+2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(telaX, telaY - 15, (int)valorBarraDeVida, 10);


                contadorBarraDeVida++;

                if(contadorBarraDeVida > 600){
                    contadorBarraDeVida=0;
                    barraDeVidaAtiva = false;
                }
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

            g2.drawImage(imagem, telaX, telaY, null);

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
}
