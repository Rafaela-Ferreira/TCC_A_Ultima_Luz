//OBS: Adicionado -----------------------------------------------------
package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjAdaga extends Entidade{
    public static final String objNome = "Adaga Simples";

    public ObjAdaga(PainelDoJogo painel) {
    super(painel);
        
        tipo = tipoAdaga;
        nome = objNome;
        baixo1 = setup("/res/objeto/adaga_normal", painel.tamanhoDoTile, painel.tamanhoDoTile);
        valorAtaque = 1;
        areaAtaque.width = 36;
        areaAtaque.height = 36;
        descricao = "[" + nome + "]\n mas eficaz.";
        preco = 200;
        direcaoDoMovimento1 = 10;
        direcaoDoMovimento2 = 30;

        durabilidade = 100;
    }
}
//--------------------------------------------------------------------