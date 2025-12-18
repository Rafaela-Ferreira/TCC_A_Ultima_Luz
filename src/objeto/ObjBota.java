package objeto;



import entidade.Entidade;
import main.PainelDoJogo;

public class ObjBota extends Entidade {

    public static final String objNome =  "Bota";

    public ObjBota(PainelDoJogo painel) {

        super(painel);
        
        nome = objNome;
        baixo1 = setup("/res/item/boots", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        
    }
    
}
