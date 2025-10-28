package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjFragmentoFlamejante extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Fragmento Flamejante";

    public ObjFragmentoFlamejante(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoFragmento;
        nome = objNome;
        valor = 1;
        baixo1 = setup("/img/objetos/coin_bronze", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "Fragmento banhado \nem chamas eternas.";
    }
    
    public boolean usar(Entidade entidade){
        painel.iniciarEfeitoSonoro(1);
        //painel.interfaceDoUsuario.adicionarMensagem("Fragmento da Espada +" + valor);
        painel.jogador.fragmentoDaEspada += valor; 

        return true;
    }
}