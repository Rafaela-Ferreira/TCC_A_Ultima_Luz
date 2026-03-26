package entidade;

import main.PainelDoJogo;

public class NpcSacerdotizaCega extends Entidade{
    
    public NpcSacerdotizaCega(PainelDoJogo painel){
        super(painel);

        direcao = "baixo";
        velocidade = 1;

        getImagem();
        setDialogo();
        setItens();
    }

    public void getImagem(){
        
        cima1 = setup("/res/npc/sarcedotisa_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/sarcedotisa_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/sarcedotisa_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/sarcedotisa_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/sarcedotisa_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/sarcedotisa_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/sarcedotisa_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/sarcedotisa_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        dialogo[0][0] = "Saudações, viajante.\nEntregue-me suas almas...\ne eu guiarei sua ascensão.";
        dialogo[0][1] = "Eu não vejo seu corpo…\napenas a chama que o sustenta.\nEla pode crescer... se você permitir.";
        
        dialogo[1][0] = "Sua chama arde mais intensa agora…\nQue ela não consuma o que resta de você.";
        
        dialogo[2][0] = "...Não.\nAs almas que carrega são insuficientes.";
        dialogo[2][1] = "Retorne quando sua chama estiver pronta\npara ser elevada.";

    }
    public void setItens(){ }

    public void falar(){

        faceJogador();
        painel.interfaceDoUsuario.npc = this;
        painel.estadoDoJogo = painel.trocaDeEstado;
        
    }

    /* Calcula o custo dinâmico em almas para subir de nível */
    public int calcularCustoParaSubirNivel() {
        return (int) (Math.pow(painel.jogador.nivel, 2)) + 10;
    }


    public void tentarSubirNivel() {
        int custo = calcularCustoParaSubirNivel();

        if (painel.jogador.alma >= custo) {
            painel.jogador.alma -= custo;
            painel.jogador.nivel++;
            painel.jogador.forca += 2;
            painel.jogador.vidaMaxima += 5;
            painel.jogador.vida = painel.jogador.vidaMaxima;

            painel.interfaceDoUsuario.adicionarMensagem("Você subiu para o nível " + painel.jogador.nivel + "!");
            iniciarDialogo(this, 1); 
        } else {
            iniciarDialogo(this, 2);
        }
    }
}