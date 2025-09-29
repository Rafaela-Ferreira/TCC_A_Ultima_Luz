package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

//para abrir essa porta é necessario fazer algo especifico!
public class ObjPortaDeFerro extends Entidade{

    PainelDoJogo painel;
    public static final String objNome = "Porta de Ferro";

    public ObjPortaDeFerro(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;

        tipo = tipoObstaculo;
        nome = objNome;
        baixo1 = setup("/img/objetos/door_iron", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
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
        dialogo[0][0] = "Item necessario!";
    }

    public void interagir(){
       iniciarDialogo(this, 0);
    }
    
}
