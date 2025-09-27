package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPocaoVermelha  extends Entidade{
    PainelDoJogo painel;
    public static final String objNome = "pocao vermelha";
    
    public ObjPocaoVermelha(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 5;
        baixo1 = setup("/img/objetos/potion_red", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "[" + nome + "]\ncura sua vida em " + valor + ".";
        preco = 25;
        empilhavel = true;
        setDialogo();
    }

    public void setDialogo(){
        dialogo[0][0] =  "Voce bebeu uma " + nome + ".\nSua vida foi restaurada em " + valor + ".";
    }

    public boolean usar(Entidade entidade){
        iniciarDialogo(this, 0);
        entidade.vida += valor;
        painel.iniciarEfeitoSonoro(2);

        return true;
    }
    
}
