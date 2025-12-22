package entidade;

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

        podeReceberDano = true; 
        vivo = true;

        direcao = "baixo";
        velocidadePadrao = 1;
        velocidade = velocidadePadrao;

        areaSolida = new Rectangle(8, 16, 32, 32);
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        nivel = 1;
        vidaMaxima = 15;
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
        cima1 = setup("/res/jogador/boy_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/jogador/boy_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/jogador/boy_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/jogador/boy_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/jogador/boy_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/jogador/boy_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/jogador/boy_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/jogador/boy_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void getImagemDeAtaque(){
        ataqueCima1 = setup("/res/jogador/ataques/boy_attack_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueCima2 = setup("/res/jogador/ataques/boy_attack_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueBaixo1 = setup("/res/jogador/ataques/boy_attack_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueBaixo2 = setup("/res/jogador/ataques/boy_attack_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
        ataqueEsquerda1 = setup("/res/jogador/ataques/boy_attack_left_1", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
        ataqueEsquerda2 = setup("/res/jogador/ataques/boy_attack_left_2", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
        ataqueDireita1 = setup("/res/jogador/ataques/boy_attack_right_1", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
        ataqueDireita2 = setup("/res/jogador/ataques/boy_attack_right_2", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);
    }

    public void getImagemDeDefesa(){
        defesaCima = setup("/res/jogador/defesa/boy_guard_up", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaBaixo = setup("/res/jogador/defesa/boy_guard_down", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaEsquerda = setup("/res/jogador/defesa/boy_guard_left", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaDireita = setup("/res/jogador/defesa/boy_guard_right", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setPosicaoPadrao() {
        //painel.mapaAtual = 0;
        //mundoX = painel.tamanhoDoTile * 23;
        //mundoY = painel.tamanhoDoTile * 21;
        //direcao = "baixo";
    }

    public void atualizar() {

        if (!vivo) return;

        if (empurrao) {
            //atualizar Empurrao
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
        else if (atacar) {
            ataque();
        }
        else {
            setAcao();

            colisaoComBloco = false;
            painel.colisaoChecked.verificarColisao(this);

            int indiceInimigo = painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
            contatoComInimigo(indiceInimigo);

            if (!colisaoComBloco) {
                mover();
            }

           // animar Sprite
            contadorDeSprite++;
            if (contadorDeSprite > 12) {
                numeroDoSprite = (numeroDoSprite == 1) ? 2 : 1;
                contadorDeSprite = 0;
            }
        }

        //atualizarInvencibilidade
        if (invencivel) {
            invencivelContador++;
            if (invencivelContador > 60) {
                invencivel = false;
                transparente = false;
                invencivelContador = 0;
            }
        }

        // CONTROLE DO TEMPO DE DEFESA
        if (defender) {
            contadorDeGuarda++;
            if (contadorDeGuarda > 30) {
                defender = false;
                contadorDeGuarda = 0;
            }
        }

        if (vida > vidaMaxima) vida = vidaMaxima;

        if (vida <= 0 && vivo) {
            morrer();
        }
    }


    public void contatoComInimigo(int i){

        if (i != 999){

            if (!invencivel && !painel.inimigo[painel.mapaAtual][i].morrendo){

                // LEVANTA O ESCUDO ANTES DO DANO
                defender = true;
                contadorDeGuarda = 0;

                painel.iniciarEfeitoSonoro(6);

                int dano = painel.inimigo[painel.mapaAtual][i].ataque - defesa;

                // DEFESA ATIVA FUNCIONANDO
                if (defender) {
                    dano /= 2;
                    //System.out.println("DEFENDENDO: " + defender);
                }

                if (dano < 1){
                    dano = 1;
                }

                vida -= dano;

                //ATIVA BARRA DE VIDA
                barraDeVidaAtiva = true;
                contadorBarraDeVida = 0;

                invencivel = true;
                transparente = true;

                if (vida <= 0){
                    morrer();
                }
            }
        }
    }

    public int getAtaque() {
        return forca * armaAtual.valorAtaque;
    }

    public int getDefesa() {
        return destreza * escudoAtual.valorDefesa;
    }

    

    public void setAcao() {

        if (!vivo) return; 

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

        // ATIVA DEFESA AUTOMÁTICA
        defender = true;
        contadorDeGuarda = 0;
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

                int distancia = Math.abs(mundoX - e.mundoX) + Math.abs(mundoY - e.mundoY);

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

        // Se a diferença horizontal for maior que a vertical
        if (Math.abs(dx) > Math.abs(dy)) {

            if (dx < 0) { return "esquerda"; }  // Se o alvo está à esquerda
            else { return "direita"; }          // Se o alvo está à direita
        } 
        // Caso contrário, usamos a direção vertical
        else {
            if (dy < 0) { return "cima"; }  // Se o alvo está acima
            else { return "baixo"; }        // Se o alvo está abaixo
        }
    }

    private void mover() {
        switch (direcao) {
            case "cima"-> mundoY -= velocidade;
            case "baixo"-> mundoY += velocidade;
            case "esquerda"-> mundoX -= velocidade;
            case "direita"-> mundoX += velocidade;
        }
    }

    public void morrer() {
        vivo = false;
        desaparecer = true;  
        atacar = false;
        pastaAtiva = false;
        painel.interfaceDoUsuario.adicionarMensagem("O aliado caiu em batalha...");
        //painel.interfaceDoUsuario.adicionarMensagem(nome + " se sacrificou para te proteger!");
    }

    public void desenhar(Graphics2D g2){

        BufferedImage imagem = null;

        if (camera() == true) {

            int telaTemporariaX = getTelaX();
            int telaTemporariaY = getTelaY();

            switch (direcao) {

                case "cima":
                    if(!atacar){
                        imagem = (numeroDoSprite == 1) ? cima1 : cima2;
                    }
                    if(atacar){
                        telaTemporariaY = getTelaY() - painel.tamanhoDoTile;
                        imagem = (numeroDoSprite == 1) ? ataqueCima1 : ataqueCima2;
                    }
                    if(defender){
                        imagem = defesaCima;
                    }
                break;

                case "baixo":
                    if(!atacar){
                        imagem = (numeroDoSprite == 1) ? baixo1 : baixo2;
                    }
                    if(atacar){
                        imagem = (numeroDoSprite == 1) ? ataqueBaixo1 : ataqueBaixo2;
                    }
                    if(defender){
                        imagem = defesaBaixo;
                    }
                break;

                case "esquerda":
                    if(!atacar){
                        imagem = (numeroDoSprite == 1) ? esquerda1 : esquerda2;
                    }
                    if(atacar){
                        telaTemporariaX = getTelaX() - painel.tamanhoDoTile;
                        imagem = (numeroDoSprite == 1) ? ataqueEsquerda1 : ataqueEsquerda2;
                    }
                    if(defender){
                        imagem = defesaEsquerda;
                    }
                break;

                case "direita":
                    if(!atacar){
                        imagem = (numeroDoSprite == 1) ? direita1 : direita2;
                    }
                    if(atacar){
                        imagem = (numeroDoSprite == 1) ? ataqueDireita1 : ataqueDireita2;
                    }
                    if(defender){
                        imagem = defesaDireita;
                    }
                break;
            }

            // transparência (invencibilidade)
            if(invencivel){
                alterarAlfa(g2, 0.4f);
            }

            if(morrendo){
                animacaoMorrendo(g2);
            }

            g2.drawImage(imagem, telaTemporariaX, telaTemporariaY, null);
            alterarAlfa(g2, 1f);
        }
    }

    
}