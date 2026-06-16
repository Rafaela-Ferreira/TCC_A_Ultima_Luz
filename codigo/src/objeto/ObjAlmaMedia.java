package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjAlmaMedia extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Alma Media";

    public ObjAlmaMedia(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        valor = 30;
        baixo1 = setup("/res/objeto/alma2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Alma +" + valor);
        painel.jogador.alma += valor;

        return true;
    }
}
