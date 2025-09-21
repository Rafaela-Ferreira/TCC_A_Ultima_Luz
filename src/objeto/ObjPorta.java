package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPorta extends Entidade {
    
    PainelDoJogo painel;

    public ObjPorta(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;

        tipo = tipoObstaculo;
        nome = "Porta";
        baixo1 = setup("/img/itens/door", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        temColisao = true; // Define que a porta tem colisão

        areaSolida.x = 0;
        areaSolida.y = 16;
        areaSolida.width = 48;
        areaSolida.height = 32;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
    
    }

    public void interagir(){
        painel.estadoDoJogo = painel.estadoDoDialogo;
        painel.interfaceDoUsuario.dialogoAtual = "Você precisa de uma chave para abrir isto!";
    }
}
