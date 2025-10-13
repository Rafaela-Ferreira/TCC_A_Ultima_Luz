package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjTocha extends Entidade {
    
    public static final String objNome =  "Tocha";
    
    public ObjTocha(PainelDoJogo painel) {
        super(painel);

        nome = objNome;
        baixo1 = setup("/img/fogueira/tocha0", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
    }

    //implementar algum tipo de iluminação


       
}
