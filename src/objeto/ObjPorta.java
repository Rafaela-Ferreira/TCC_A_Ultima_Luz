package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjPorta extends Entidade {
    
    PainelDoJogo painel;
    public static final String objNome = "Porta";

    public ObjPorta(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;

        tipo = tipoObstaculo;
        nome = objNome;
        baixo1 = setup("/res/objeto/door", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        temColisao = true; // Define que a porta tem colisão

        areaSolida.x = 0;
        areaSolida.y = 16;
        areaSolida.width = 48;
        areaSolida.height = 32;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        setDialogo();
    
    }
    public void setDialogo(){
        dialogo[0][0] = "Você precisa de uma chave para abrir isto!";
    }

    public void interagir(){
       iniciarDialogo(this, 0);
    }
}
