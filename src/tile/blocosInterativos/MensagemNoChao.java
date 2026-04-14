package tile.blocosInterativos;

import java.awt.Graphics2D;
import main.PainelDoJogo;

public class MensagemNoChao extends BlocosInterativos {

    PainelDoJogo painel;
    public String mensagem;
    public boolean jogadorPerto = false;
    private boolean mensagemMostrada = false;

    public MensagemNoChao(PainelDoJogo painel, int coluna, int linha, String mensagem) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        this.mensagem = mensagem;

        baixo1 = setup("/res/objeto/Letter", painel.tamanhoDoTile,  painel.tamanhoDoTile);

        destruir = false;
    }


    public void atualizar() {

        int distanciaX = Math.abs(painel.jogador.mundoX - mundoX);
        int distanciaY = Math.abs(painel.jogador.mundoY - mundoY);

        boolean pertoAgora = distanciaX < painel.tamanhoDoTile && distanciaY < painel.tamanhoDoTile;

        // entrou na área, mostra a mensagem
        if (pertoAgora && !jogadorPerto) {
            painel.interfaceDoUsuario.textoMensagemPreDefinida = mensagem;
            painel.interfaceDoUsuario.mensagemAtiva = true;
            painel.iniciarEfeitoSonoro(10);
        }

        // saiu da área, fecha a mensagem
        if (!pertoAgora && jogadorPerto) {
            painel.interfaceDoUsuario.mensagemAtiva = false;
            painel.interfaceDoUsuario.textoMensagemPreDefinida = "";
        }

        jogadorPerto = pertoAgora;
    }
   

    public void desenhar(Graphics2D g2) {

        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

        g2.drawImage(baixo1, telaX, telaY, null);
    }
}
