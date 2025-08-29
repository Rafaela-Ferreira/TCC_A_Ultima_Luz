package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjEscudoMadeira  extends Entidade{

    public ObjEscudoMadeira(PainelDoJogo painel) {
        super(painel);
        nome = "escudo madeira";
        baixo1 = setup("/img/objetos/shield_wood", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorDefesa = 1;
        descricao = "[" + nome + "]\nFabricado de madeira.";
        
    }
    
}
