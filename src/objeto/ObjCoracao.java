package objeto;



import entidade.Entidade;
import main.PainelDoJogo;

public class ObjCoracao extends Entidade{


    public ObjCoracao(PainelDoJogo painel) {

        super(painel);
        
        nome = "Coracao";
        imagem = setup("/img/vida/vidaCompleta", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem2 = setup("/img/vida/vidaMeio", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem3 = setup("/img/vida/vidaBranco", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        
    
    }   
}
