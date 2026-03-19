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
        baixo1 = setup("/res/objeto/blueheart", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        setDialogo();

        
    }

    public void setDialogo(){
        dialogo[0][0] = "Ninguém escapa do ciclo eterno entre \n"
                     + "a morte e o renacimento.";
        dialogo[0][1] = "Mas aquele que carregava o poder sabia que, um dia,\n"
                     + "alguém ouviria seus sussuros.";

    }

    public boolean usar(Entidade entidade){

        painel.estadoDoJogo = painel.estadoCutscene;
        painel.gerenciadorDeCutscene.numeroDaCena = painel.gerenciadorDeCutscene.cenaFinal;
        return true;
    }
    
}
