package tile.blocosInterativos;

import main.PainelDoJogo;

public class Fogueira extends BlocosInterativos{
    
    PainelDoJogo painel;

    public Fogueira(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/fogueira/tocha0", painel.tamanhoDoTile, painel.tamanhoDoTile);
        //destruir = true;
        //vida = 3;

        //implementar algo para interagir
    }

    
}
