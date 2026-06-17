package ambiente;

import java.awt.Graphics2D;

import main.PainelDoJogo;

public class GerenciadorDeAmbientes {
    PainelDoJogo painel;
    public Iluminacao iluminacao;

    public GerenciadorDeAmbientes(PainelDoJogo painel){
        this.painel = painel;
    }

    public void setup(){
        iluminacao = new Iluminacao(painel); //576 tamanho maxido da tela - não utrapassar sem alterar isso
    }

    public void atualizar(){
        iluminacao.atualizar();
    }
    public void desenhar(Graphics2D g2){
        iluminacao.desenhar(g2);
    }
}
