package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPocaoVermelha  extends Entidade{
    PainelDoJogo painel;
    
    public ObjPocaoVermelha(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        tipo = tipoConsumivel;
        nome = "pocao vermelha";
        valor = 5;
        baixo1 = setup("/img/objetos/potion_red", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "[" + nome + "]\ncura sua vida em " + valor + ".";
        preco = 25;
        empilhavel = true;
        
    }

    public boolean usar(Entidade entidade){
        painel.estadoDoJogo = painel.estadoDoDialogo;
        painel.interfaceDoUsuario.dialogoAtual = "Voce bebeu uma " + nome + ".\nSua vida foi restaurada em " + valor + ".";
        entidade.vida += valor;
        painel.iniciarEfeitoSonoro(2);

        return true;
    }
    
}
