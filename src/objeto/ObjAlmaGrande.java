package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjAlmaGrande extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Alma Grande";

    public ObjAlmaGrande(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        valor = 150;
        baixo1 = setup("/res/objeto/alma3", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Alma +" + valor);
        painel.jogador.alma += valor;

        return true;
    }
}

