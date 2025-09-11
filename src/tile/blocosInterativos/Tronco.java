package tile.blocosInterativos;

import main.PainelDoJogo;

public class Tronco extends BlocosInterativos{
    
    PainelDoJogo painel;

    public Tronco(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/tiles/interativos/trunk", painel.tamanhoDoTile, painel.tamanhoDoTile);
      
        areaSolida.x = 0;
        areaSolida.y = 0;
        areaSolida.width = 0;
        areaSolida.height = 0;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
    
    }
}
