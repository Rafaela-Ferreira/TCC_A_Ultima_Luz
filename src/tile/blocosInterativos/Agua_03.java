package tile.blocosInterativos;

import main.PainelDoJogo;

public class Agua_03  extends BlocosInterativos{
    
    PainelDoJogo painel;

    public Agua_03(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/objetos/w03_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/objetos/w03_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        destruir = true;
        
    }
}
