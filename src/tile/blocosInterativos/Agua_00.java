package tile.blocosInterativos;

import main.PainelDoJogo;

public class Agua_00 extends BlocosInterativos{
    
    PainelDoJogo painel;

    public Agua_00(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/objetos/w00_0", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/objetos/w00_0", painel.tamanhoDoTile, painel.tamanhoDoTile);
        destruir = true;
        
    }

}