package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

public class ObjDiamente extends Entidade{
    PainelDoJogo painel;
    public static final String objNome = "Diamante";

    public ObjDiamente(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        tipo = tipoRetirada;
        nome = objNome;
        baixo1 = setup("/img/objetos/blueheart", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        setDialogo();

        
    }

    public void setDialogo(){
        dialogo[0][0] = "Você pega uma linda pedra azul.";
        dialogo[0][1] = "Você encontra o coração azul, o tesouro lendário!";

    }

    public boolean usar(Entidade entidade){

        painel.estadoDoJogo = painel.estadoCutscene;
        painel.gerenciadorDeCutscene.numeroDaCena = painel.gerenciadorDeCutscene.cenaFinal;
        return true;
    }
    
}
