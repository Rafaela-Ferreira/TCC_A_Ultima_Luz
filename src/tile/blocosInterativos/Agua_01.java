package tile.blocosInterativos;

import main.PainelDoJogo;

public class Agua_01 extends BlocosInterativos{
    
    PainelDoJogo painel;

    public Agua_01(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/objetos/w01_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/objetos/w01_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        destruir = true;
        
    }

}