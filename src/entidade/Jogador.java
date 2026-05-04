package entidade;

import dados.Progresso;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.PainelDoJogo;
import main.Teclado;
import objeto.ObjAdaga;
import objeto.ObjAlma;
import objeto.ObjBolaDeFogo;
import objeto.ObjCajadoNormal;
import objeto.ObjEscudoMadeira;
import objeto.ObjEspadaEnferrujada;
import objeto.ObjCatalisadorDeFogo;
import objeto.ObjMagiaCurta;
import objeto.ObjMagiaFogo;

public class Jogador extends Entidade {
    // Atributos específicos do Jogador

    // OBS: Adicionado -----------------------------------------------------
    // Guarda o nome da pasta da classe atual (ex: "guerreiro", "mago")
    public String nomeClasseAtual = "guerreiro";
    // ---------------------------------------------------------------------

    Teclado teclado; // keyH

    public final int telaX;
    public final int telaY;
    int auxCounter = 0;

    public boolean cancelarAtaque = false;
    public boolean equiparLanterna = false;

    // Último ponto salvo (checkpoint)
    public int mundoXSalvo;
    public int mundoYSalvo;
    public int mapaSalvo;
    public String direcaoSalva;

    // SISTEMA DE ALMAS
    public int totalAlmas = 0;

    public boolean morto = false;

    public Jogador(PainelDoJogo painel, Teclado teclado) {
        super(painel); // estamos chamando o construtor da superClass desta class (Entidade)

        this.teclado = teclado;

        telaX = painel.larguraTela / 2 - (painel.tamanhoDoTile / 2); // Centraliza na tela
        telaY = painel.alturaTela / 2 - (painel.tamanhoDoTile / 2); // Centraliza na tela

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;

        setDefaultValues();

    }

    // OBS: Adicionado -----------------------------------------------------
    // public void resetarAtributos() {
    // // Limpa o inventário para não misturar itens de classes diferentes
    // inventario.clear();

    // // Reseta status de combate
    // ataque = 0;
    // defesa = 0;

    // // Limpa os equipamentos atuais
    // armaAtual = null;
    // escudoAtual = null;
    // projetil = null;
    // }

    public void carregarImagemPorClasse(String nomeClasse) {
        this.nomeClasseAtual = nomeClasse;
        getImagem();
        getImagemDeAtaque();
        getImagemDeDefesa();
    }
    // ---------------------------------------------------------------------

    // Salva o ponto atual do jogador
    public void salvarPonto() {
        mapaSalvo = painel.mapaAtual;
        mundoXSalvo = mundoX;
        mundoYSalvo = mundoY;
        direcaoSalva = direcao;
    }

    // Renasce no último ponto salvo
    public void renascerNoUltimoPonto() {

        if (mundoXSalvo != 0 && mundoYSalvo != 0) {
            painel.mapaAtual = mapaSalvo;
            mundoX = mundoXSalvo;
            mundoY = mundoYSalvo;
            direcao = direcaoSalva;
        } else {
            // Caso não tenha nenhum ponto salvo, usa a posição padrão
            setPosicaoPadrao();
        }
        restaltarStatus(); // recupera vida/mana
    }

    public void setDefaultValues() {
        mundoX = painel.tamanhoDoTile * 23;
        mundoY = painel.tamanhoDoTile * 21;
        // painel.mapaAtual = 0; //debug

        velocidadePadrao = 4;
        velocidade = velocidadePadrao;
        direcao = "baixo"; // Direção inicial do jogador

        // estado do jogador
        nivel = 1;
        vidaMaxima = 6;
        vida = vidaMaxima;
        manaMaxima = 4;
        mana = manaMaxima;
        resistenciaMaxima = 100;
        resistencia = resistenciaMaxima;
        municao = 10;
        forca = 1; // quanto mais força ele tem, mais dano ele dá.
        destreza = 1; // quanto mais destreza ele tem, menos dano ele recebe.
        fragmentoDaEspada = 0;

        // exp = 0;
        // proximoNivelExp = 1;
        // proximoNivelExp = proximoNivelExp+nivel;

        // Debug: adicionar almas aqui!
        if (!almasNoChao) {
            alma = 0;
        }

        armaAtual = new ObjEspadaEnferrujada(painel);
        // armaAtual = new ObjEspadaNormal(painel);
        // armaAtual = new ObjMachado(painel);
        escudoAtual = new ObjEscudoMadeira(painel);
        luzAtual = null;
        projetil = new ObjBolaDeFogo(painel);
        // projetil = new ObjPedra(painel); //municao

        getImagem();
        getImagemDeAtaque();
        getImagemDeDefesa();
        setItens(nomeClasseAtual);

        ataque = getAtaque();
        defesa = getDefesa();

        setDialogo();
    }

    public void setPosicaoPadrao() {
        painel.mapaAtual = 0;
        mundoX = painel.tamanhoDoTile * 23;
        mundoY = painel.tamanhoDoTile * 21;
        direcao = "baixo";
    }

    public void setDialogo() {
        // dialogo[0][0] = "Você subiu para o nível " + nivel + "!";
    }

    public void restaltarStatus() {
        vida = vidaMaxima;
        mana = manaMaxima;
        resistencia = resistenciaMaxima;
        velocidade = velocidadePadrao;
        invencivel = false;
        transparente = false;
        atacar = false;
        defender = false;
        empurrao = false;
        equiparLanterna = true; // atualizar a luz
    }

    public void setItens(String nomeClasse) {
        inventario.clear(); // limpar o inventario

        // inventario.add(escudoAtual);
        // inventario.add(armaAtual);

        if (escudoAtual != null) {
            inventario.add(escudoAtual);
        }

        if (nomeClasse.equals("guerreiro")) {
            armaAtual = new ObjEspadaEnferrujada(painel);
            projetil = new ObjBolaDeFogo(painel);
        } else if (nomeClasse.equals("ladrao")) {
            armaAtual = new ObjAdaga(painel);
            projetil = new ObjBolaDeFogo(painel);
        } else if (nomeClasse.equals("mago")) {
            armaAtual = new ObjCajadoNormal(painel);
            // projetil = new ObjMagiaCurta(painel);
            projetil = new ObjBolaDeFogo(painel);
        } else if (nomeClasse.equals("piromante")) {
            armaAtual = new ObjCatalisadorDeFogo(painel);
            // projetil = new ObjMagiaFogo(painel);
            projetil = new ObjBolaDeFogo(painel);
        }

        // Garante que a arma recém criada vá para o inventário
        if (armaAtual != null) {
            inventario.add(armaAtual);
        }

        // inventario.add(new ObjTocha(painel));
        // inventario.add(new ObjChave(painel));
        ataque = getAtaque();

    }

    public int getAtaque() {
        areaAtaque = armaAtual.areaAtaque;
        direcaoDoMovimento1 = armaAtual.direcaoDoMovimento1;
        direcaoDoMovimento2 = armaAtual.direcaoDoMovimento2;
        return ataque = forca * armaAtual.valorAtaque;
    }

    public int getDefesa() {
        return defesa = destreza * escudoAtual.valorDefesa;
    }

    public int getEspacoArmaAtual() {
        int espacoArmaAtual = 0;
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i) == armaAtual) {
                espacoArmaAtual = i;
            }
        }
        return espacoArmaAtual;
    }

    public int getEspacoEscudoAtual() {
        int espacoEscudoAtual = 0;
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i) == escudoAtual) {
                espacoEscudoAtual = i;
            }
        }
        return espacoEscudoAtual;
    }

    // OBS: Adicionado/alterado/comentado -------------------
    public void getImagem() {
        String caminho = "/res/jogador-classe/" + nomeClasseAtual + "/";

        try {
            // cima1 = setup("/res/jogador/boy_up_1" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // cima2 = setup("/res/jogador/boy_up_2" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // baixo1 = setup("/res/jogador/boy_down_1" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // baixo2 = setup("/res/jogador/boy_down_2" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // esquerda1 = setup("/res/jogador/boy_left_1" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // esquerda2 = setup("/res/jogador/boy_left_2" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // direita1 = setup("/res/jogador/boy_right_1" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);
            // direita2 = setup("/res/jogador/boy_right_2" ,painel.tamanhoDoTile,
            // painel.tamanhoDoTile);

            cima1 = setup(caminho + "cima1", painel.tamanhoDoTile, painel.tamanhoDoTile);
            cima2 = setup(caminho + "cima2", painel.tamanhoDoTile, painel.tamanhoDoTile);
            baixo1 = setup(caminho + "baixo1", painel.tamanhoDoTile, painel.tamanhoDoTile);
            baixo2 = setup(caminho + "baixo2", painel.tamanhoDoTile, painel.tamanhoDoTile);
            esquerda1 = setup(caminho + "esquerda1", painel.tamanhoDoTile, painel.tamanhoDoTile);
            esquerda2 = setup(caminho + "esquerda2", painel.tamanhoDoTile, painel.tamanhoDoTile);
            direita1 = setup(caminho + "direita1", painel.tamanhoDoTile, painel.tamanhoDoTile);
            direita2 = setup(caminho + "direita2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        } catch (Exception e) {
            System.out.println("Erro ao carregar sprites para: " + nomeClasseAtual);
        }
    }
    // -----------------------------------------------------

    public void getImagemDormindo(BufferedImage imagem) {
        cima1 = imagem;
        cima2 = imagem;
        baixo1 = imagem;
        baixo2 = imagem;
        esquerda1 = imagem;
        esquerda2 = imagem;
        direita1 = imagem;
        direita2 = imagem;
    }

    public void getImagemDeAtaque() {
        // OBS: Adicionado/modificado
        // -----------------------------------------------------
        if (armaAtual == null)
            return;
        String caminho = "/res/jogador-classe/" + nomeClasseAtual + "/ataques/";
        System.out.println("Nome da classe atual: " + nomeClasseAtual);

        if (armaAtual.tipo == tipoEspada || armaAtual.tipo == tipoCajado || armaAtual.tipo == tipoAdaga
                || armaAtual.tipo == tipoChama) {
            ataqueCima1 = setup(caminho + "attack_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueCima2 = setup(caminho + "attack_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueBaixo1 = setup(caminho + "attack_down_1", painel.tamanhoDoTile,
                    painel.tamanhoDoTile * 2);
            ataqueBaixo2 = setup(caminho + "attack_down_2", painel.tamanhoDoTile,
                    painel.tamanhoDoTile * 2);
            ataqueEsquerda1 = setup(caminho + "attack_left_1", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueEsquerda2 = setup(caminho + "attack_left_2", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueDireita1 = setup(caminho + "attack_right_1", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueDireita2 = setup(caminho + "attack_right_2", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
        }

        if (armaAtual.tipo == tipoMachado) {
            ataqueCima1 = setup(caminho + "axe_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueCima1 = setup(caminho + "axe_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueCima2 = setup(caminho + "axe_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueBaixo1 = setup(caminho + "axe_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueBaixo2 = setup(caminho + "axe_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueEsquerda1 = setup(caminho + "axe_left_1", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueEsquerda2 = setup(caminho + "axe_left_2", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueDireita1 = setup(caminho + "axe_right_1", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueDireita2 = setup(caminho + "axe_right_2", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);

        }

        if (armaAtual.tipo == tipoPicareta) {
            ataqueCima1 = setup(caminho + "pick_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueCima2 = setup(caminho + "pick_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile * 2);
            ataqueBaixo1 = setup(caminho + "pick_down_1", painel.tamanhoDoTile,
                    painel.tamanhoDoTile * 2);
            ataqueBaixo2 = setup(caminho + "pick_down_2", painel.tamanhoDoTile,
                    painel.tamanhoDoTile * 2);
            ataqueEsquerda1 = setup(caminho + "pick_left_1", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueEsquerda2 = setup(caminho + "pick_left_2", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueDireita1 = setup(caminho + "pick_right_1", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);
            ataqueDireita2 = setup(caminho + "pick_right_2", painel.tamanhoDoTile * 2,
                    painel.tamanhoDoTile);

        }

        // if(armaAtual.tipo == tipoEspada){
        // ataqueCima1 = setup("/res/jogador/ataques/boy_attack_up_1"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueCima2 = setup("/res/jogador/ataques/boy_attack_up_2"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueBaixo1 = setup("/res/jogador/ataques/boy_attack_down_1"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueBaixo2 = setup("/res/jogador/ataques/boy_attack_down_2"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueEsquerda1 = setup("/res/jogador/ataques/boy_attack_left_1"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueEsquerda2 = setup("/res/jogador/ataques/boy_attack_left_2"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueDireita1 = setup("/res/jogador/ataques/boy_attack_right_1"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueDireita2 = setup("/res/jogador/ataques/boy_attack_right_2"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);

        // }

        // if(armaAtual.tipo == tipoMachado){
        // ataqueCima1 = setup("/res/jogador/ataques/boy_axe_up_1"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueCima2 = setup("/res/jogador/ataques/boy_axe_up_2"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueBaixo1 = setup("/res/jogador/ataques/boy_axe_down_1"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueBaixo2 = setup("/res/jogador/ataques/boy_axe_down_2"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueEsquerda1 = setup("/res/jogador/ataques/boy_axe_left_1"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueEsquerda2 = setup("/res/jogador/ataques/boy_axe_left_2"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueDireita1 = setup("/res/jogador/ataques/boy_axe_right_1"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueDireita2 = setup("/res/jogador/ataques/boy_axe_right_2"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);

        // }

        // if(armaAtual.tipo == tipoPicareta){
        // ataqueCima1 = setup("/res/jogador/ataques/boy_pick_up_1"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueCima2 = setup("/res/jogador/ataques/boy_pick_up_2"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueBaixo1 = setup("/res/jogador/ataques/boy_pick_down_1"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueBaixo2 = setup("/res/jogador/ataques/boy_pick_down_2"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        // ataqueEsquerda1 = setup("/res/jogador/ataques/boy_pick_left_1"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueEsquerda2 = setup("/res/jogador/ataques/boy_pick_left_2"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueDireita1 = setup("/res/jogador/ataques/boy_pick_right_1"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        // ataqueDireita2 = setup("/res/jogador/ataques/boy_pick_right_2"
        // ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);

        // }

    }

    public void getImagemDeDefesa() {
        if (armaAtual == null)
            return;

        String caminho = "/res/jogador-classe/" + nomeClasseAtual + "/defesa/";

        defesaCima = setup(caminho + "up", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaBaixo = setup(caminho + "down", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaEsquerda = setup(caminho + "left", painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaDireita = setup(caminho + "right", painel.tamanhoDoTile, painel.tamanhoDoTile);

        // defesaCima = setup("/res/jogador/defesa/boy_guard_up" ,painel.tamanhoDoTile,
        // painel.tamanhoDoTile);
        // defesaBaixo = setup("/res/jogador/defesa/boy_guard_down"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // defesaEsquerda = setup("/res/jogador/defesa/boy_guard_left"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        // defesaDireita = setup("/res/jogador/defesa/boy_guard_right"
        // ,painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    // ---------------------------------------------------------------------

    public void atualizar() {

        // OBS: DEBUG para correr mais rapido -----------------------------------------------------
        // if (teclado.modoDebugAtivo == true) {
        //     velocidade = 10; // Aumento da velocidade
        // } else {
        //     if (empurrao == false) {
        //         velocidade = velocidadePadrao;
        //     }
        // }
        // ---------------------------------------------------------------------

        if (morto && teclado.modoDebugAtivo == false) {
            return;
        }

        // EMPURRÃO
        if (empurrao == true) {
            colisaoComBloco = false;
            painel.colisaoChecked.verificarColisao(this);
            painel.colisaoChecked.verificarObjeto(this, true);
            painel.colisaoChecked.verificarEntidade(this, painel.npc);
            painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
            painel.colisaoChecked.verificarEntidade(this, painel.blocosI);

            if (colisaoComBloco == true) {
                contadoEmpurrao = 0;
                empurrao = false;
                velocidade = velocidadePadrao;
            } else {
                switch (direcaoDoempurrao) {
                    case "cima":
                        mundoY -= velocidade;
                        break;
                    case "baixo":
                        mundoY += velocidade;
                        break;
                    case "esquerda":
                        mundoX -= velocidade;
                        break;
                    case "direita":
                        mundoX += velocidade;
                        break;
                }
            }

            contadoEmpurrao++;
            if (contadoEmpurrao == 10) {
                contadoEmpurrao = 0;
                empurrao = false;
                velocidade = velocidadePadrao;
            }
        }

        // ATAQUE
        else if (atacar == true) {
            ataque();
        }

        // DEFESA
        else if (teclado.precionarEspaco) {
            defender = true;
            contadorDeGuarda++;
        }

        // MOVIMENTO
        else if (teclado.precionarCima || teclado.precionarBaixo ||
                teclado.precionarEsquerda || teclado.precionarDireita || teclado.precionarEnter) {

            if (teclado.precionarCima)
                direcao = "cima";
            else if (teclado.precionarBaixo)
                direcao = "baixo";
            else if (teclado.precionarEsquerda)
                direcao = "esquerda";
            else if (teclado.precionarDireita)
                direcao = "direita";

            colisaoComBloco = false;
            painel.colisaoChecked.verificarColisao(this);

            int objIndex = painel.colisaoChecked.verificarObjeto(this, true);
            pegarObjeto(objIndex);

            int npcIndice = painel.colisaoChecked.verificarEntidade(this, painel.npc);
            interacaoComNpc(npcIndice);

            int indiceInimigo = painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
            contatoComInimigo(indiceInimigo);

            painel.colisaoChecked.verificarEntidade(this, painel.blocosI);
            painel.mEventos.verificarEvento();

            if (colisaoComBloco == false && teclado.precionarEnter == false) {
                switch (direcao) {
                    case "cima":
                        mundoY -= velocidade;
                        break;
                    case "baixo":
                        mundoY += velocidade;
                        break;
                    case "esquerda":
                        mundoX -= velocidade;
                        break;
                    case "direita":
                        mundoX += velocidade;
                        break;
                }
            } else {
                numeroDoSprite = 1;
            }

            // ATAQUE COM STAMINA
            // OBS: Comentado e Alterado
            // -----------------------------------------------------

            // if (teclado.precionarEnter && cancelarAtaque == false && resistencia >= 10) {
            // painel.iniciarEfeitoSonoro(7);
            // atacar = true;
            // contadorDeSprite = 0;

            // resistencia -= 10;
            // delayRecuperacaoResistencia = 0;

            // if (resistencia < 10) {
            // cancelarAtaque = true;
            // }

            // armaAtual.durabilidade--;
            // }
            if (teclado.precionarEnter && atacar == false && cancelarAtaque == false && resistencia >= 10) {
                painel.iniciarEfeitoSonoro(7);
                atacar = true;
                contadorDeSprite = 0;

                resistencia -= 10;
                delayRecuperacaoResistencia = 0;

                if (resistencia < 10) {
                    cancelarAtaque = true;
                }

                armaAtual.durabilidade--;
            }
            // ---------------------------------------------------------------------

            cancelarAtaque = false;
            painel.teclado.precionarEnter = false;
            defender = false;
            contadorDeGuarda = 0;

            contadorDeSprite++;
            if (contadorDeSprite > 12) {
                numeroDoSprite = (numeroDoSprite == 1) ? 2 : 1;
                contadorDeSprite = 0;
            }
        } else {
            auxCounter++;
            if (auxCounter == 20) {
                numeroDoSprite = 1;
                auxCounter = 0;
            }
            defender = false;
            contadorDeGuarda = 0;
        }

        // PROJÉTIL
        if (painel.teclado.teclaDeTiroPressionada && !projetil.vivo
                && contadorDeTiro == 30 && projetil.temRecursos(this)) {

            projetil.setAcao(mundoX, mundoY, direcao, true, this);
            projetil.subtrairRecursos(this);

            for (int i = 0; i < painel.projetavel[1].length; i++) {
                if (painel.projetavel[painel.mapaAtual][i] == null) {
                    painel.projetavel[painel.mapaAtual][i] = projetil;
                    break;
                }
            }

            contadorDeTiro = 0;
            painel.iniciarEfeitoSonoro(10);
        }

        // INVENCIBILIDADE
        if (invencivel) {
            invencivelContador++;
            if (invencivelContador > 60) {
                invencivel = false;
                transparente = false;
                invencivelContador = 0;
            }
        }

        if (contadorDeTiro < 30) {
            contadorDeTiro++;
        }

        if (vida > vidaMaxima)
            vida = vidaMaxima;
        if (mana > manaMaxima)
            mana = manaMaxima;

        // RECUPERAÇÃO DE STAMINA
        if (!atacar) {
            delayRecuperacaoResistencia++;

            if (delayRecuperacaoResistencia > 60) {
                if (resistencia < resistenciaMaxima) {
                    resistencia += 2;
                }
            }
        } else {
            delayRecuperacaoResistencia = 0;
        }

        // EXAUSTÃO
        if (resistencia <= 0) {
            resistencia = 0;
            exausto = true;
        }

        if (resistencia > 10) {
            exausto = false;
            velocidade = velocidadePadrao;
        }

        // MORTE
        if (vida <= 0) {

            if (teclado.modoDebugAtivo) {
                // No debug o jogador não morre
                vida = 1; // mantém vivo
                morto = false;
            } else {
                vida = 0;
                morto = true;

                morrer();

                painel.estadoDoJogo = painel.estadoGameOver;
                painel.interfaceDoUsuario.resetarGameOver();
                painel.pararMusica();
                painel.iniciarEfeitoSonoro(12);
            }
        }
    }

    /*
     * para abrir a porta é necessário ter uma chave
     * se tiver, a porta é removida do array de objetos
     * se não tiver, nada acontece
     * se tiver, a quantidade de chaves diminui
     */
    public void pegarObjeto(int indice) {
        if (indice != 999) {

            // itens somente para retirada
            if (painel.Obj[painel.mapaAtual][indice].tipo == tipoRetirada) {
                painel.Obj[painel.mapaAtual][indice].usar(this);
                painel.Obj[painel.mapaAtual][indice] = null;
            }

            // Obstaculo
            else if (painel.Obj[painel.mapaAtual][indice].tipo == tipoObstaculo) {
                if (teclado.precionarEnter == true) {
                    cancelarAtaque = true;
                    painel.Obj[painel.mapaAtual][indice].interagir();
                }
            }

            // itens do invetario
            else {
                String texto;

                if (podeObterItem(painel.Obj[painel.mapaAtual][indice]) == true) {
                    painel.iniciarEfeitoSonoro(1);
                    texto = "Item coletado " + painel.Obj[painel.mapaAtual][indice].nome + "!";
                } else {
                    texto = "você não pode carregar mais nada!";
                }
                painel.interfaceDoUsuario.adicionarMensagem(texto);
                painel.Obj[painel.mapaAtual][indice] = null; // remover o objeto do mapa
            }

        }
    }

    public void interacaoComNpc(int indice) {

        if (indice != 999) {

            if (painel.teclado.precionarEnter == true) {
                cancelarAtaque = true;
                painel.npc[painel.mapaAtual][indice].falar();

            }

            /*
             * else{
             * painel.iniciarEfeitoSonoro(7);
             * atacar = true;
             * }
             */

            painel.npc[painel.mapaAtual][indice].mover(direcao);
        }

    }

    public void selecionarItem() {
        int indeceItem = painel.interfaceDoUsuario.pegarItemSelecionado(painel.interfaceDoUsuario.jogadorEspacoColuna,
                painel.interfaceDoUsuario.jogadorEspacoLinha);

        if (indeceItem < inventario.size()) {

            Entidade itemSelecionado = inventario.get(indeceItem);

            // // OBS: Adicionado -----------------------------------------------------
            // if (itemSelecionado.tipo == tipoCajado) {
            // armaAtual = itemSelecionado;
            // ataque = getAtaque();
            // getImagemDeAtaque();
            // }
            // // --------------------------------------------------------------------

            if (itemSelecionado.tipo == tipoEspada || itemSelecionado.tipo == tipoMachado
                    || itemSelecionado.tipo == tipoPicareta || itemSelecionado.tipo == tipoAdaga
                    || itemSelecionado.tipo == tipoCajado || itemSelecionado.tipo == tipoChama) {
                armaAtual = itemSelecionado;
                ataque = getAtaque();
                getImagemDeAtaque();

            }
            if (itemSelecionado.tipo == tipoEscudo) {
                escudoAtual = itemSelecionado;
                defesa = getDefesa();
            }
            if (itemSelecionado.tipo == tipoIliminacao) {
                if (luzAtual == itemSelecionado) {
                    luzAtual = null;
                } else {
                    luzAtual = itemSelecionado;
                }
                equiparLanterna = true;
            }

            if (itemSelecionado.tipo == tipoConsumivel) {
                if (itemSelecionado.usar(this) == true) {
                    if (itemSelecionado.quantidade > 1) {
                        itemSelecionado.quantidade--;
                    } else {
                        inventario.remove(indeceItem);
                    }

                }

            }

        }
        painel.teclado.precionarEnter = false;
    }

    public void contatoComInimigo(int i) {
        if (i != 999) {

            if (invencivel == false && painel.inimigo[painel.mapaAtual][i].morrendo == false) {
                painel.iniciarEfeitoSonoro(6);

                int dano = painel.inimigo[painel.mapaAtual][i].ataque - defesa;
                if (dano < 1) {
                    dano = 1;

                }
                vida -= dano; // dano do inimigo
                invencivel = true;
                transparente = true;

                // Verificação de morte
                if (vida <= 0) {
                    if (teclado.modoDebugAtivo) {
                        vida = 1; // impede morte
                        morto = false; // garante que não entra em estado morto
                    } else {
                        morrer();
                    }
                }
            }

        }
    }

    public void morrer() {

        // BLOQUEIO TOTAL NO DEBUG
        if (teclado.modoDebugAtivo) {
            vida = 1;
            morto = false;
            return;
        }

        // Se já existiam almas no chão, elas são perdidas
        if (almasNoChao) {

            for (int i = 0; i < painel.Obj[painel.mapaAtual].length; i++) {
                if (painel.Obj[painel.mapaAtual][i] != null &&
                        painel.Obj[painel.mapaAtual][i].nome.equals(ObjAlma.objNome)) {

                    painel.Obj[painel.mapaAtual][i] = null;
                }
            }

            almasPerdidas = 0;
            almasNoChao = false;
        }

        int almasDeixadas = alma;
        alma = 0;

        ObjAlma almaDrop = new ObjAlma(painel);
        almaDrop.mundoX = mundoX;
        almaDrop.mundoY = mundoY;
        almaDrop.valor = almasDeixadas;

        for (int i = 0; i < painel.Obj[painel.mapaAtual].length; i++) {
            if (painel.Obj[painel.mapaAtual][i] == null) {
                painel.Obj[painel.mapaAtual][i] = almaDrop;
                break;
            }
        }

        almasNoChao = true;
        almasPerdidas = almasDeixadas;
        almaX = mundoX;
        almaY = mundoY;

        painel.interfaceDoUsuario.adicionarMensagem("Você deixou " + almasDeixadas + " almas no chão!");
        painel.estadoDoJogo = painel.estadoGameOver;
        painel.interfaceDoUsuario.numeroDoComando = -1;

        // libera a invasão para acontecer novamente
        Progresso.invasaoMapa1Ativa = false;

    }

    public void danoDoInimigo(int indice, Entidade atacante, int ataque, int poderDoEmpurrao) {
        if (indice != 999) {

            if (painel.inimigo[painel.mapaAtual][indice].invencivel == false) {
                painel.iniciarEfeitoSonoro(5);

                if (poderDoEmpurrao > 0) {
                    setEmpurrao(painel.inimigo[painel.mapaAtual][indice], atacante, poderDoEmpurrao);
                }

                if (painel.inimigo[painel.mapaAtual][indice].equilibrioDesativar == true) {
                    ataque *= 5;
                }

                int dano = ataque - painel.inimigo[painel.mapaAtual][indice].defesa;

                // OBS: adicionado/comentado ----------------------------------------
                // OBS: MODIFICADO PQ SENÃO O ATAQUE PARA O MAGO E PIROMANTE NÃO FUNCIONAVA,
                // POIS O ATAQUE DO MAGO E PIROMANTE É BASEADO EM FORÇA, ENTÃO O ATAQUE FICAVA
                // MUITO BAIXO, CHEGANDO A DAR 0 DE DANO
                // if(dano < 0){
                // dano = 0;
                // }
                if (dano < 1) {
                    dano = 1;
                }
                // -----------------------------------------------------

                painel.inimigo[painel.mapaAtual][indice].vida -= dano;
                // painel.interfaceDoUsuario.adicionarMensagem(dano + " de dano!");
                painel.inimigo[painel.mapaAtual][indice].invencivel = true;
                painel.inimigo[painel.mapaAtual][indice].acaoAoDano();

                if (painel.inimigo[painel.mapaAtual][indice].vida <= 0) {
                    painel.inimigo[painel.mapaAtual][indice].morrendo = true;
                    painel.interfaceDoUsuario
                            .adicionarMensagem("Você derrotou " + painel.inimigo[painel.mapaAtual][indice].nome + "!");
                    // painel.interfaceDoUsuario.adicionarMensagem("Você ganhou " +
                    // painel.inimigo[painel.mapaAtual][indice].exp + " de experiência!");
                    exp += painel.inimigo[painel.mapaAtual][indice].exp;
                    // verificarNivelAcima();
                }
            }

        }
    }

    public void danoNoBlocoInterativo(int i) {
        if (i != 999 && painel.blocosI[painel.mapaAtual][i].destruir == true
                && painel.blocosI[painel.mapaAtual][i].itemCorreto(this) == true
                && painel.blocosI[painel.mapaAtual][i].invencivel == false) {

            painel.blocosI[painel.mapaAtual][i].iniciarEfeitoSonoro();
            painel.blocosI[painel.mapaAtual][i].vida--;
            painel.blocosI[painel.mapaAtual][i].invencivel = true;

            // gerar particula
            geradorParticula(painel.blocosI[painel.mapaAtual][i], painel.blocosI[painel.mapaAtual][i]);

            if (painel.blocosI[painel.mapaAtual][i].vida == 0) {
                // painel.blocosI[painel.mapaAtual][i].verificarDrop(); caso queira dropar itens
                // das paredes
                painel.blocosI[painel.mapaAtual][i] = painel.blocosI[painel.mapaAtual][i].getDestruir();
            }

        }
    }

    public void danoDoProjetavel(int i) {
        if (i != 999) {
            Entidade projetavel = painel.projetavel[painel.mapaAtual][i];
            projetavel.vivo = false;
            geradorParticula(projetavel, projetavel);
        }
    }

    public int procurarItemNoInventario(String nomeDoItem) {

        int indiceItem = 999;

        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).nome.equals(nomeDoItem)) {
                indiceItem = i;
                break;
            }
        }
        return indiceItem;
    }

    public boolean podeObterItem(Entidade item) {

        boolean podeObter = false;

        Entidade novoItem = painel.geradorDeEntidade.getObjeto(item.nome);

        // Verificar se é empilhavel
        if (novoItem.empilhavel == true) {

            int indice = procurarItemNoInventario(novoItem.nome);

            if (indice != 999) {
                inventario.get(indice).quantidade++;
                podeObter = true;
            } else {
                if (inventario.size() != tamanhoMaximoInventario) {
                    inventario.add(novoItem);
                    podeObter = true;
                }
            }
        }
        // não empilhável, então verifique a vaga
        else {
            if (inventario.size() != tamanhoMaximoInventario) {
                inventario.add(novoItem);
                podeObter = true;
            }
        }
        return podeObter;
    }

    public void desenhar(Graphics2D g2) {
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, painel.tamanhoDoTile, painel.tamanhoDoTile);

        BufferedImage imagem = null;
        int telaTemporariaX = telaX;
        int telaTemporariaY = telaY;

        switch (direcao) {
            case "cima":
                if (atacar == false) {
                    if (numeroDoSprite == 1) {
                        imagem = cima1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = cima2;
                    }
                }
                if (atacar == true) {
                    telaTemporariaY = telaY - painel.tamanhoDoTile;
                    if (numeroDoSprite == 1) {
                        imagem = ataqueCima1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = ataqueCima2;
                    }
                }
                if (defender == true) {
                    imagem = defesaCima;
                }

                break;
            case "baixo":
                if (atacar == false) {
                    if (numeroDoSprite == 1) {
                        imagem = baixo1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = baixo2;
                    }
                }
                if (atacar == true) {
                    if (numeroDoSprite == 1) {
                        imagem = ataqueBaixo1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = ataqueBaixo2;
                    }
                }
                if (defender == true) {
                    imagem = defesaBaixo;
                }
                break;
            case "esquerda":
                if (atacar == false) {
                    if (numeroDoSprite == 1) {
                        imagem = esquerda1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = esquerda2;
                    }
                }
                if (atacar == true) {
                    telaTemporariaX = telaX - painel.tamanhoDoTile;
                    if (numeroDoSprite == 1) {
                        imagem = ataqueEsquerda1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = ataqueEsquerda2;
                    }
                }
                if (defender == true) {
                    imagem = defesaEsquerda;
                }

                break;
            case "direita":
                if (atacar == false) {
                    if (numeroDoSprite == 1) {
                        imagem = direita1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = direita2;
                    }
                }
                if (atacar == true) {
                    if (numeroDoSprite == 1) {
                        imagem = ataqueDireita1;
                    }
                    if (numeroDoSprite == 2) {
                        imagem = ataqueDireita2;
                    }
                }
                if (defender == true) {
                    imagem = defesaDireita;
                }
                break;
        }

        if (transparente == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if (desenho == true) {
            g2.drawImage(imagem, telaTemporariaX, telaTemporariaY, null);
        }

        // verificar colisão com blocos
        // g2.setColor(Color.red);
        // g2.drawRect(telaX + areaSolida.x, telaY + areaSolida.y, areaSolida.width,
        // areaSolida.height);

        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // debug
        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("Invencivel: "+invencivelContador, 10, 400);
    }

}
