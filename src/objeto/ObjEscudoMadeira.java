package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjEscudoMadeira  extends Entidade{

    public static final String objNome = "escudo madeira";

    public ObjEscudoMadeira(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoEscudo;
        nome = objNome;
        baixo1 = setup("/img/objetos/shield_wood", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorDefesa = 1;
        descricao = "[" + nome + "]\nFabricado de madeira.";
        preco = 100;
        poderDoEmpurrao = 2;

        durabilidade = 80;
        
    }
    
}
