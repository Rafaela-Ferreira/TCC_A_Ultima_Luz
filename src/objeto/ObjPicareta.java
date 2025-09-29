package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPicareta extends Entidade{

  
    public static final String objNome = "Picareta";

    public ObjPicareta(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoPicareta;
        nome = objNome;
        baixo1 = setup("/img/objetos/pickaxe", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 6;
        areaAtaque.width = 30;
        areaAtaque.height = 30;
        descricao = "[" + nome + "]\nAumenta o ataque em " + valorAtaque + ".";
        preco = 75;
        poderDoEmpurrao = 10;
        direcaoDoMovimento1 = 10;
        direcaoDoMovimento2 = 20;

        durabilidade = 400;
    }
    
}
