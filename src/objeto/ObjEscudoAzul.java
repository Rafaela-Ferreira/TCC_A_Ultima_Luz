package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjEscudoAzul extends Entidade{

    public static final String objNome = "Escudo azul";

    public ObjEscudoAzul(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoEscudo;
        nome = objNome;
        baixo1 = setup("/res/objeto/shield_blue", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorDefesa = 2;
        descricao = "[" + nome + "]\nFabricado de aço.";
        preco = 580;
        poderDoEmpurrao = 7;

        
    }
    
}
