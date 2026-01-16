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
        
        cima1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/npc/merchant_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/npc/merchant_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void setDialogo(){
        //seria legal exibir esse dialogo antes de desenhar a tela para subir de nivel
        dialogo[0][0] = "Saudações, viajante.\nTraga-me almas e eu elevarei seu poder.";
        
        dialogo[1][0] = "Seu espírito está mais forte agora... use-o \ncom sabedoria.";
        dialogo[2][0] = "Você não possui almas suficientes.";
        dialogo[2][1] = "Retorne quando estiver pronto para \nsubir de nível.";

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