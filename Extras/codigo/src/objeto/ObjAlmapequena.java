package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjAlmapequena extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Alma Pequena";

    public ObjAlmapequena(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        valor = 10;
        baixo1 = setup("/res/objeto/alma", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Alma +" + valor);
        painel.jogador.alma += valor;

        return true;
    }
}
