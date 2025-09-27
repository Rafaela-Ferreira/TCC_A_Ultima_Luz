package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjMana extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Mana";

    public ObjMana(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoRetirada;
        nome= objNome;
        valor = 1;
        baixo1 = setup("/img/objetos/manacrystal_full", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem = setup("/img/objetos/manacrystal_full", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem2 = setup("/img/objetos/manacrystal_blank", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
    }
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(2);
        painel.interfaceDoUsuario.adicionarMensagem("Mana +" + valor);
        entidade.mana += valor;

        return true;
    }
    
}
