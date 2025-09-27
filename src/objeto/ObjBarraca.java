package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

//possivel fogueira

public class ObjBarraca  extends Entidade{
    PainelDoJogo painel;

    public static final String objNome = "Barraca";

    public ObjBarraca(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;


        tipo = tipoConsumivel;
        nome = objNome;
        baixo1 = setup("/img/objetos/tent", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "[Barraca]\nVocê pode dormir até\na manhã seguinte.";
        preco = 300;
        empilhavel = true;
    }

    public boolean usar(Entidade entidade){

        painel.estadoDoJogo = painel.estadoDormir;
        painel.iniciarEfeitoSonoro(14);
        painel.jogador.vida = painel.jogador.vidaMaxima; //restaura a vida ao dormir
        painel.jogador.mana = painel.jogador.manaMaxima; //restaura a mana ao dormir
        painel.jogador.getImagemDormindo(baixo1);
        return true;
    }
    
}
