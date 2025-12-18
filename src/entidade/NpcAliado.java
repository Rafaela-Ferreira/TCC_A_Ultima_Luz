package entidade;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.PainelDoJogo;
import objeto.ObjEscudoMadeira;
import objeto.ObjEspadaNormal;

public class NpcAliado extends Entidade {

    // IA
    private int contadorAtaque = 0;
    private final int intervaloAtaque = 40;


    public NpcAliado(PainelDoJogo painel) {
        super(painel);

        tipo = tipoNpcAliado;
        nome = "NPC Aliado";

        direcao = "baixo";
        velocidadePadrao = 1;
        velocidade = velocidadePadrao;

        areaSolida = new Rectangle(8, 16, 32, 32);
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        nivel = 1;
        vidaMaxima = 12;
        vida = vidaMaxima;
        forca = 1;
        destreza = 1;

        armaAtual = new ObjEspadaNormal(painel);
        escudoAtual = new ObjEscudoMadeira(painel);

        areaAtaque = armaAtual.areaAtaque;
        direcaoDoMovimento1 = armaAtual.direcaoDoMovimento1;
        direcaoDoMovimento2 = armaAtual.direcaoDoMovimento2;

        ataque = getAtaque();
        defesa = getDefesa();

        getImagem();
        getImagemDeAtaque();
        getImagemDeDefesa();
    }

 

    public void getImagem(){
        cima1 = setup("/jogador/boy_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/jogador/boy_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/jogador/boy_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/jogador/boy_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/jogador/boy_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/jogador/boy_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/jogador/boy_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/jogador/boy_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void getImagemDeAtaque(){
        ataqueCima1 = setup("/jogador/ataques/boy_attack_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueCima2 = setup("/jogador/ataques/boy_attack_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueBaixo1 = setup("/jogador/ataques/boy_attack_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueBaixo2 = setup("/jogador/ataques/boy_attack_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueEsquerda1 = setup("/jogador/ataques/boy_attack_left_1", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
        ataqueEsquerda2 = setup("/jogador/ataques/boy_attack_left_2", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
        ataqueDireita1 = setup("/jogador/ataques/boy_attack_right_1", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
        ataqueDireita2 = setup("/jogador/ataques/boy_attack_right_2", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
    }

    public void getImagemDeDefesa(){
        defesaCima = setup("/jogador/defesa/boy_guard_up", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaBaixo = setup("/jogador/defesa/boy_guard_down", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaEsquerda = setup("/jogador/defesa/boy_guard_left", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaDireita = setup("/jogador/defesa/boy_guard_right", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setPosicaoPadrao() {
        //painel.mapaAtual = 0;
        //mundoX = painel.tamanhoDoTile * 23;
        //mundoY = painel.tamanhoDoTile * 21;
        //direcao = "baixo";
    }

    public int getAtaque() {
        return forca * armaAtual.valorAtaque;
    }

    public int getDefesa() {
        return destreza * escudoAtual.valorDefesa;
    }

    

    public void setAcao() {

        int inimigo = procurarInimigoProximo();

        if (inimigo != 999) {
            atacarOuPerseguir(inimigo);
        } else {
            seguirJogador();
        }
    }

    public void acaoAoDano() {
        contadorDeBloqueioDeAcao = 0;
        pastaAtiva = true;
    }

    public void seguirJogador() {
        int col = (painel.jogador.mundoX + painel.jogador.areaSolida.x) / painel.tamanhoDoTile;
        int lin = (painel.jogador.mundoY + painel.jogador.areaSolida.y) / painel.tamanhoDoTile;
        procurarCaminho(col, lin);
    }


    //INIMIGO MAIS PRÓXIMO
    public int procurarInimigoProximo() {

        int indice = 999;
        int menorDistancia = 9999;

        for (int i = 0; i < painel.inimigo[painel.mapaAtual].length; i++) {

            Entidade e = painel.inimigo[painel.mapaAtual][i];

            if (e != null && !e.morrendo) {

                int distancia = Math.abs(mundoX - e.mundoX)
                              + Math.abs(mundoY - e.mundoY);

                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    indice = i;
                }
            }
        }
        return indice;
    }

    public void atacarOuPerseguir(int i) {

        Entidade inimigo = painel.inimigo[painel.mapaAtual][i];

        int distancia = Math.abs(mundoX - inimigo.mundoX) + Math.abs(mundoY - inimigo.mundoY);

        if (distancia < painel.tamanhoDoTile * 2) {

            direcao = getDirecaoPara(inimigo);

            contadorAtaque++;
            if (contadorAtaque >= intervaloAtaque) {
                atacar = true;
                contadorDeSprite = 0;
                contadorAtaque = 0;
            }
        } else {

            int col = (inimigo.mundoX + inimigo.areaSolida.x) / painel.tamanhoDoTile;
            int lin = (inimigo.mundoY + inimigo.areaSolida.y) / painel.tamanhoDoTile;
            procurarCaminho(col, lin);
        }
    }

    private String getDirecaoPara(Entidade alvo) {

        int dx = alvo.mundoX - mundoX;
        int dy = alvo.mundoY - mundoY;

        if (Math.abs(dx) > Math.abs(dy)) {
            return dx < 0 ? "esquerda" : "direita";
        } else {
            return dy < 0 ? "cima" : "baixo";
        }
    }

    private void mover() {
        switch (direcao) {
            case "cima" -> mundoY -= velocidade;
            case "baixo" -> mundoY += velocidade;
            case "esquerda" -> mundoX -= velocidade;
            case "direita" -> mundoX += velocidade;
        }
    }

    private void animarSprite() {
        contadorDeSprite++;
        if (contadorDeSprite > 12) {
            numeroDoSprite = (numeroDoSprite == 1) ? 2 : 1;
            contadorDeSprite = 0;
        }
    }

    private void atualizarInvencibilidade() {
        if (invencivel) {
            invencivelContador++;
            if (invencivelContador > 60) {
                invencivel = false;
                transparente = false;
                invencivelContador = 0;
            }
        }
    }

    private void atualizarEmpurrao() {

        colisaoComBloco = false;
        painel.colisaoChecked.verificarColisao(this);

        if (!colisaoComBloco) {
            switch (direcaoDoempurrao) {
                case "cima" -> mundoY -= velocidade;
                case "baixo" -> mundoY += velocidade;
                case "esquerda" -> mundoX -= velocidade;
                case "direita" -> mundoX += velocidade;
            }
        }

        contadoEmpurrao++;
        if (contadoEmpurrao > 10) {
            contadoEmpurrao = 0;
            empurrao = false;
            velocidade = velocidadePadrao;
        }
    }

    

    

    
}