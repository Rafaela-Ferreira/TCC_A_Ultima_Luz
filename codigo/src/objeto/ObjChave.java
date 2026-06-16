package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjChave  extends Entidade {

    PainelDoJogo painel;
    public static final String objNome =  "Chave";

    public ObjChave(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = objNome;
        baixo1 = setup("/res/item/key", painel.tamanhoDoTile, painel.tamanhoDoTile);

        descricao = "[" + nome + "]\npara abrir uma porta.";
        preco = 1000;
        empilhavel = true;
    
    }

    public void setDialogo(){
        dialogo[0][0] = "Você usou uma " + nome + "para abriu uma porta";

        dialogo[1][0] = "O que você está fazendo?";
    }

    public boolean usar(Entidade entidade){

        int indiceObjeto = getDetectar(entidade, painel.Obj, "Porta");

        if(indiceObjeto != 999){
            iniciarDialogo(this, 0);
            painel.iniciarEfeitoSonoro(3);
            painel.Obj[painel.mapaAtual][indiceObjeto] = null;
            return true; // se usar a chave
        }
        else{
            iniciarDialogo(this, 1);
            return false;
        }
    }

}
