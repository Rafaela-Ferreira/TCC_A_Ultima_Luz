package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjEspadaNormal  extends Entidade{

    public ObjEspadaNormal(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoEspada;
        nome = "espada simples";
        baixo1 = setup("/img/objetos/sword_normal", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 1;
        areaAtaque.width = 36;
        areaAtaque.height = 36;
        descricao = "[" + nome + "]\n mas eficaz.";
    }
    
}
