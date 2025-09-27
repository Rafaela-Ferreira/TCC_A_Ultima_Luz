package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjMoedaBronze extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Moeda de Bronze";

    public ObjMoedaBronze(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/img/objetos/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Moeda +" + valor);
        painel.jogador.moeda += valor;

        return true;
    }
}
