 //OBS: Adicionado -----------------------------------------------------
package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjCajadoNormal extends Entidade{
    public static final String objNome = "Cajado Simples";

    public ObjCajadoNormal(PainelDoJogo painel) {
    super(painel);
        
        tipo = tipoCajado;
        nome = objNome;
        baixo1 = setup("/res/objeto/cajado_normal", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 0;
        areaAtaque.width = 32;
        areaAtaque.height = 32;
        descricao = "[" + nome + "]\n mas eficaz.";
        preco = 200;
        direcaoDoMovimento1 = 10;
        direcaoDoMovimento2 = 30;

        durabilidade = 100;
    }
}
//--------------------------------------------------------------------