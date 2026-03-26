package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoSombrio extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Sombrio";

    public ObjFragmentoSombrio(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/res/objeto/frag3", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "Fragmento corrompido \npor ilusões e reflexos.";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.jogador.fragmentoDaEspada += valor; 

        return true;
    }
}