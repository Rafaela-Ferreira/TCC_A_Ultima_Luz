package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoFaminto extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Faminto";

    public ObjFragmentoFaminto(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/img/objetos/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "Fragmento corrompido \npela fome eterna.";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.jogador.fragmentoDaEspada += valor; 

        return true;
    }
}