package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPocaoVermelha  extends Entidade{
    PainelDoJogo painel;
    int valorDeCura = 5;

    public ObjPocaoVermelha(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        tipo = tipoConsumivel;
        nome = "pocao vermelha";
        baixo1 = setup("/img/objetos/potion_red", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "[" + nome + "]\ncura sua vida em " + valorDeCura + ".";
    }
    public void usar(Entidade entidade){
        painel.estadoDoJogo = painel.estadoDoDialogo;
        painel.interfaceDoUsuario.dialogoAtual = "Voce bebeu uma " + nome + ".\nSua vida foi restaurada em " + valorDeCura + ".";
        entidade.vida += valorDeCura;
        if(painel.jogador.vida > painel.jogador.vidaMaxima){
            painel.jogador.vida = painel.jogador.vidaMaxima;

        }
        painel.iniciarEfeitoSonoro(2);
    }
    
}
