package objeto;

import entidade.Entidade;
import main.PainelDoJogo;
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
}