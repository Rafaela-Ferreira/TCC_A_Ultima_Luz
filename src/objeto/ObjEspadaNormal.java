package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjEspadaNormal  extends Entidade{

    public static final String objNome = "espada simples";

    public ObjEspadaNormal(PainelDoJogo painel) {
        super(painel);
        
        tipo = tipoEspada;
        nome = objNome;
        baixo1 = setup("/img/objetos/sword_normal", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 1;
        areaAtaque.width = 36;
        areaAtaque.height = 36;
        descricao = "[" + nome + "]\n mas eficaz.";
        preco = 20;
        direcaoDoMovimento1 = 2;
        direcaoDoMovimento2 = 10;

        durabilidade = 100;

        //ataque rapido
        //direcaoDoMovimento1 = 2;
        //direcaoDoMovimento2 = 10;
    }
    
}
