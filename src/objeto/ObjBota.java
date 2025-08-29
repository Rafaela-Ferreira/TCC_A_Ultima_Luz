package objeto;



import entidade.Entidade;
import main.PainelDoJogo;

public class ObjBota extends Entidade {
    
    
    public ObjBota(PainelDoJogo painel) {

        super(painel);
        
        nome = "Bota";
        baixo1 = setup("/img/itens/boots", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        
    }
    
}
