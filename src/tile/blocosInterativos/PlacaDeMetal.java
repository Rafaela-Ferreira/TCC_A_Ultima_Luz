package tile.blocosInterativos;

import main.PainelDoJogo;

public class PlacaDeMetal extends BlocosInterativos{

    PainelDoJogo painel;
    public static final String nomeBlocoI = "Placa de Metal";

    public PlacaDeMetal(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        nome = nomeBlocoI;
        baixo1 = setup("/img/tiles/interativos/metalplate", painel.tamanhoDoTile, painel.tamanhoDoTile);
      
        areaSolida.x = 0;
        areaSolida.y = 0;
        areaSolida.width = 0;
        areaSolida.height = 0;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
    
    }
    
}
