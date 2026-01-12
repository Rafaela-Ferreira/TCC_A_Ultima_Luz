package objeto;

import entidade.Entidade;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import main.PainelDoJogo;

public class ObjAlma extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Alma";

    public ObjAlma(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        valor = calcularValorAleatorio();
        baixo1 = setup("/res/objeto/alma", painel.tamanhoDoTile, painel.tamanhoDoTile);
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
        
        g2.setColor(new Color(0, 140, 180, 40));
        g2.fillOval(telaX - 8, telaY - 8, painel.tamanhoDoTile + 16,  painel.tamanhoDoTile + 16);


    }

}