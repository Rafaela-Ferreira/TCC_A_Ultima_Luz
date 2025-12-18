package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjTocha extends Entidade {
    
    public static final String objNome =  "Tocha";
    
    public ObjTocha(PainelDoJogo painel) {
        super(painel);

        tipo = tipoIliminacao;
        nome = objNome;
        baixo1 = setup("/res/fogueira/tocha0", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        descricao = "[Lanterna]\nIlumina os arredores.";
        preco = 500;
        raioDeLuz = 450;
    }
}

    
    

       

