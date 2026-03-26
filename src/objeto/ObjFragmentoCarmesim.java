package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoCarmesim extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Carmesim";

    public ObjFragmentoCarmesim(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/res/objeto/frag4", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "Fragmento \nnecessário para \nrestaurar a espada.";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.interfaceDoUsuario.adicionarMensagem("Fragmento da Espada +" + valor);
        painel.jogador.fragmentoDaEspada += valor; 
        return true;
    }
}