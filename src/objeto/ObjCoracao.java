package objeto;



import entidade.Entidade;
import main.PainelDoJogo;

public class ObjCoracao extends Entidade{
    
    PainelDoJogo painel;

    public ObjCoracao(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;
        
        tipo = tipoRetirada;
        nome = "Coracao";
        valor = 2;
        baixo1 = setup("/img/vida/vidaCompleta", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem = setup("/img/vida/vidaCompleta", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem2 = setup("/img/vida/vidaMeio", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem3 = setup("/img/vida/vidaBranco", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }  
    
    public void usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(2);
        painel.interfaceDoUsuario.adicionarMensagem("Vida +" + valor);
        entidade.vida += valor;
    }
}
