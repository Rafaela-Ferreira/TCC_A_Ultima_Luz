package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjMoedaBronze extends Entidade{

    PainelDoJogo painel;

    public ObjMoedaBronze(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = "Moeda de Bronze";
        valor = 1;
        baixo1 = setup("/img/objetos/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
    public void usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Moeda +" + valor);
        painel.jogador.moeda += valor;
    }
}
