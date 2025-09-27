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
        baixo1 = setup("/img/itens/key", painel.tamanhoDoTile, painel.tamanhoDoTile);

        descricao = "[" + nome + "]\npara abrir uma porta.";
        preco = 100;
        empilhavel = true;
    
    }

    public void setDialogo(){
        dialogo[0][0] = "You use the " + nome + "and open the door";

        dialogo[1][0] = "What are you doing?";
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
