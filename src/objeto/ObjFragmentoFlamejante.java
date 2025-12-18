package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoFlamejante extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Flamejante";

    public ObjFragmentoFlamejante(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/res/objeto/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "Fragmento banhado \nem chamas eternas.";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.jogador.fragmentoDaEspada += valor; 

        return true;
    }
}