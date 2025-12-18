package main.inimigo;

import dados.Progresso;
import entidade.Entidade;
import main.PainelDoJogo;
import objeto.ObjMoedaBronze;



public class InimigoInvasor extends Entidade{
    
    PainelDoJogo painel;

    public InimigoInvasor(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        tipo = tipoInimigo;
        nome = "Invasor Sombrio";

        velocidadePadrao = 2;
        velocidade = velocidadePadrao;

        vidaMaxima = 5;
        vida = vidaMaxima;

        ataque = 12;
        defesa = 4;
        exp = 30;

        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolida.width = 32;
        areaSolida.height = 32;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        getImagem();
        getImagemAtaque();
    }

    public void getImagem(){
        cima1 = setup("/inimigo/orc_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/inimigo/orc_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/inimigo/orc_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/inimigo/orc_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/inimigo/orc_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/inimigo/orc_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/inimigo/orc_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/inimigo/orc_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void getImagemAtaque(){
        ataqueCima1 = setup("/inimigo/orc_attack_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueCima2 = setup("/inimigo/orc_attack_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueBaixo1 = setup("/inimigo/orc_attack_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueBaixo2 = setup("/inimigo/orc_attack_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile*2);
        ataqueEsquerda1 = setup("/inimigo/orc_attack_left_1", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        ataqueEsquerda2 = setup("/inimigo/orc_attack_left_2", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        ataqueDireita1 = setup("/inimigo/orc_attack_right_1", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
        ataqueDireita2 = setup("/inimigo/orc_attack_right_2", painel.tamanhoDoTile*2, painel.tamanhoDoTile);
    }


    public void setAcao() {

        // Se não tem alvo ou o alvo morreu, volta para o jogador
        if (alvo == null || alvo.vivo == false) {
            alvo = painel.jogador;
        }

        if (pastaAtiva) {

            // PERSEGUE O ALVO ATUAL (jogador OU npc aliado)
            procurarCaminho(
                getColunaAtual(alvo),
                getLinhaAtual(alvo)
            );

            // tenta atacar
            verificarSeAtacou_ou_nao(60, 48, 36);
        }
        else {
            // começa a perseguir SE QUALQUER ALVO estiver perto
            verificarSeComecouAPerseguir_ou_nao(alvo, 8, 100);
            getDirecaoAleatoria(20);
        }
    }

    public void acaoAoDano() {
        contadorDeBloqueioDeAcao = 0;
        pastaAtiva = true; // ficou agressivo ao ser atingido
    }

    public void morrer() {

        Progresso.invasorMapa1Derrotado = true;
        Progresso.invasaoMapa1Ativa = false;

        painel.batalhaComChefeAtiva = false;
        painel.interfaceDoUsuario.adicionarMensagem("O espírito hostil foi dissipado.");
    }


    public void verificarDrop() {

        Progresso.invasorMapa1Derrotado = true;
        Progresso.invasaoMapa1Ativa = false;

        painel.batalhaComChefeAtiva = false;

        droparItem(new ObjMoedaBronze(painel));
    }
}
