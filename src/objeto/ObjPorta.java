package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPorta extends Entidade {
    

    public ObjPorta(PainelDoJogo painel) {

        super(painel);
        
        nome = "Porta";
        baixo1 = setup("/img/itens/door", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        temColisao = true; // Define que a porta tem colisão

        areaSolida.x = 0;
        areaSolida.y = 16;
        areaSolida.width = 48;
        areaSolida.height = 32;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
    
    }
}
