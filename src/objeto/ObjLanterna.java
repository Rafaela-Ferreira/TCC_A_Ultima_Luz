package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjLanterna  extends Entidade{

    public ObjLanterna(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoIliminacao;
        nome = "Lanterna";
        baixo1 = setup("/img/objetos/lantern", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "[Lanterna]\nIlumina os arredores.";
        preco = 200;
        raioDeLuz = 100;
    }
    

}
