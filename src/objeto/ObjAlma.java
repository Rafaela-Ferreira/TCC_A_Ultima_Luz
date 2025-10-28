package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class ObjAlma extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Alma";

    public ObjAlma(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        valor = calcularValorAleatorio();
        baixo1 = setup("/img/objetos/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    private int calcularValorAleatorio() {
        Random random = new Random();
        int min = 5;
        int max = 25;
        return min + random.nextInt(max - min + 1);
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Almas +" + valor);
        painel.jogador.alma += valor;

        return true;
    }
    
    public void desenhar(Graphics2D g2) {
        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

        if (baixo1 != null) {
            g2.drawImage(baixo1, telaX, telaY, painel.tamanhoDoTile, painel.tamanhoDoTile, null);
        }

        //brilho leve ao redor da alma
        g2.setColor(new Color(0, 255, 255, 80));
        g2.fillOval(telaX - 4, telaY - 4, painel.tamanhoDoTile + 8, painel.tamanhoDoTile + 8);
    }

}