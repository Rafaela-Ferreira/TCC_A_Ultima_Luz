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
    int contadorMorrendo = 0;
    int contadorBarraDeVida = 0;
    
    //Atributos do jogador
    public int tipo; //0 -> jogador, 1 -> npc, 2 -> inimigo....
    public String nome;
    public int velocidade;
    public int vidaMaxima;
    public int vida;
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

    //atributos dos Itens
    public int valorAtaque;
    public int valorDefesa;
    public String descricao = "";



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

    public void atualizar(){
        setAcao();

        colisaoComBloco = false;
        painel.colisaoChecked.verificarColisao(this);
        painel.colisaoChecked.verificarObjeto(this, false);
        painel.colisaoChecked.verificarEntidade(this, painel.npc);
        painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
        boolean contatoComJogador = painel.colisaoChecked.verificarJogador(this);

        if(this.tipo == 2 && contatoComJogador == true){
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

            g2.drawImage(imagem, telaX, telaY, painel.tamanhoDoTile, painel.tamanhoDoTile, null);

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
            morrendo = false;
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
            imagem = ferramentas.escalaImage(imagem, altura, largura);

        }catch(IOException e){
            e.printStackTrace();
        }

        return imagem;
    }
}
