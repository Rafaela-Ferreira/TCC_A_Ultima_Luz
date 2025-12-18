package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjMana extends Entidade{

    //pagar esse objeto - usar poção de mana
    PainelDoJogo painel;
    public static final String objNome = "Mana";

    public ObjMana(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome= objNome;
        valor = 1;
        baixo1 = setup("/res/objeto/manacrystal_full", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem = setup("/res/objeto/manacrystal_full", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem2 = setup("/res/objeto/manacrystal_blank", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
    }
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(2);
        painel.interfaceDoUsuario.adicionarMensagem("Mana +" + valor);
        entidade.mana += valor;

        return true;
    }
    
}
