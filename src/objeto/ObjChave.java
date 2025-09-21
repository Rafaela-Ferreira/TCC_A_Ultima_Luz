package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjChave  extends Entidade {

    PainelDoJogo painel;

    public ObjChave(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;

        tipo = tipoConsumivel;
        nome = "Chave";
        baixo1 = setup("/img/itens/key", painel.tamanhoDoTile, painel.tamanhoDoTile);

        descricao = "[" + nome + "]\npara abrir uma porta.";
        preco = 100;
        empilhavel = true;
    
    }

    public boolean usar(Entidade entidade){
        painel.estadoDoJogo = painel.estadoDoDialogo;

        int indiceObjeto = getDetectar(entidade, painel.Obj, "Porta");

        if(indiceObjeto != 999){
            painel.interfaceDoUsuario.dialogoAtual = "You use the " + nome + "and open the door";
            painel.iniciarEfeitoSonoro(3);
            painel.Obj[painel.mapaAtual][indiceObjeto] = null;
            return true; // se usar a chave
        }
        else{
            painel.interfaceDoUsuario.dialogoAtual = "What are you doing?";
        }

        return false;
    }

}
