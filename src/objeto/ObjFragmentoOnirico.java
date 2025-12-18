package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoOnirico extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Onírico";

    public ObjFragmentoOnirico(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/res/objeto/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "Fragmento envolto \nem energia dos sonhos.";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.jogador.fragmentoDaEspada += valor; 

        return true;
    }
}