package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoCoracaoDaLamina extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Coração da Lâmina";

    public ObjFragmentoCoracaoDaLamina(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/img/objetos/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "O núcleo sagrado e \ncorrompido da espada\nperdida";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        painel.jogador.fragmentoDaEspada += valor; 
       
        return true;
    }
}