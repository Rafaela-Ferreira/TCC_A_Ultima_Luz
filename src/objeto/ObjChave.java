package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjChave  extends Entidade {

    
    public ObjChave(PainelDoJogo painel) {

        super(painel);

        nome = "Chave";
        baixo1 = setup("/img/itens/key", painel.tamanhoDoTile, painel.tamanhoDoTile);

        descricao = "[" + nome + "]\npara abrir uma porta.";
    
    
    }

}
