package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjMachado extends Entidade{

    public static final String objNome = "Machado";

    public ObjMachado(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoMachado;
        nome = objNome;
        baixo1 = setup("/img/objetos/axe", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 2;
        areaAtaque.width = 30;
        areaAtaque.height = 30;
        descricao = "[" + nome + "]\nAumenta o ataque em " + valorAtaque + ".";
        preco = 75;
        poderDoEmpurrao = 10;
        direcaoDoMovimento1 = 20;
        direcaoDoMovimento2 = 40;

        durabilidade = 200;
    }
    
}
