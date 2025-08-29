package objeto;


import entidade.Entidade;
import main.PainelDoJogo;

public class ObjBau extends Entidade {
    

    public ObjBau(PainelDoJogo painel) {

        super(painel);

        nome = "Bau";
        baixo1 = setup("/img/itens/chest", painel.tamanhoDoTile, painel.tamanhoDoTile);
        

    }
    

}
