package objeto;

import main.PainelDoJogo;

import java.awt.Graphics2D;

import dados.Progresso;
import entidade.Entidade;

public class ObjNevoaDensa extends Entidade {

    PainelDoJogo painel;
    public static final String objNome = "Nevoa Densa";

    public ObjNevoaDensa(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;

        tipo = tipoObstaculo;
        nome = objNome;
        baixo1 = setup("/res/objeto/nevoa_densa", painel.tamanhoDoTile * 2, painel.tamanhoDoTile);

        temColisao = true;

        areaSolida.x = 32;
        areaSolida.y = 16;
        areaSolida.width = 32;
        areaSolida.height = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        setDialogo();

    }

    public void setDialogo() {
        dialogo[0][0] = "Derrote todos inimigos para dissipar a névoa!";
    }

    public void interagir() {
        iniciarDialogo(this, 0);
    }

    @Override
    public void atualizar() {

        if (Progresso.invasaoMapa1Ativa) {
            temColisao = true;
        } else {
            temColisao = false;
        }
    }

    @Override
    public void desenhar(Graphics2D g2) {

        if (!Progresso.invasaoMapa1Ativa) {
            return;
        }

        super.desenhar(g2);
    }

}
