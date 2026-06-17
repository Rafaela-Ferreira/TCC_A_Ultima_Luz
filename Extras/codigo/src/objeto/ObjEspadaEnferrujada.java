package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjEspadaEnferrujada  extends Entidade{

    public static final String objNome = "Espada Enferrujada";

    public ObjEspadaEnferrujada(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoEspada;
        nome = objNome;
        baixo1 = setup("/res/objeto/espada_enferrujada", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 1;
        areaAtaque.width = 36;
        areaAtaque.height = 36;
        descricao = "[" + nome + "]\nsem corte.";
        preco = 200;
        direcaoDoMovimento1 = 10;
        direcaoDoMovimento2 = 30;

        durabilidade = 0;
    }
    
}
