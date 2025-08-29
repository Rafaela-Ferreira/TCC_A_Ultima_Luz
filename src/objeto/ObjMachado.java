package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjMachado extends Entidade{

    public ObjMachado(PainelDoJogo painel) {
        super(painel);
        nome = "Machado";
        baixo1 = setup("/img/objetos/axe", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 2;
        areaAtaque.width = 30;
        areaAtaque.height = 30;
        descricao = "[" + nome + "]\nAumenta o ataque em " + valorAtaque + ".";
        
    }
    
}
