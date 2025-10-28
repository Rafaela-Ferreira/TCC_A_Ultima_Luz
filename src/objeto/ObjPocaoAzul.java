package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPocaoAzul extends Entidade{
    PainelDoJogo painel;
    public static final String objNome = "Pocao azul";
    
    public ObjPocaoAzul(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        valor = 5;
        baixo1 = setup("/img/objetos/potion_red", painel.tamanhoDoTile, painel.tamanhoDoTile);
        descricao = "[" + nome + "]\nRecupera mana em " + valor + ".";
        //preco = 25; - item nao e vendido, apenas encontrado
        empilhavel = true;
        setDialogo();
    }

    public void setDialogo(){
        dialogo[0][0] =  "Voce bebeu uma " + nome + ".\nSua mana foi restaurada em " + valor + ".";
    }

    public boolean usar(Entidade entidade){
        iniciarDialogo(this, 0);
        entidade.mana += valor;
        painel.iniciarEfeitoSonoro(2);

        return true;
    }
    
}
