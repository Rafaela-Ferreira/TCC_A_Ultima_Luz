package tile.blocosInterativos;

import entidade.Entidade;
import java.awt.Color;
import main.PainelDoJogo;

public class ArvoreSeca2 extends BlocosInterativos{

    PainelDoJogo painel;

    public ArvoreSeca2(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/res/blocosInterativos/arvore2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        destruir = true;
        vida = 3;
    }
    
    public boolean itemCorreto(Entidade entidade){
        boolean itemCorreto = false;

        //qualquer arma pode cortar uma árvore
        if(entidade.armaAtual != null && (entidade.armaAtual.tipo == tipoPicareta || entidade.armaAtual.tipo == tipoMachado || entidade.armaAtual.tipo == tipoEspada || entidade.armaAtual.tipo == tipoAdaga || entidade.armaAtual.tipo == tipoCajado || entidade.armaAtual.tipo == tipoChama)){
            itemCorreto = true;
        }

        return itemCorreto;
    }

    public void iniciarEfeitoSonoro(){
        painel.iniciarEfeitoSonoro(11);
    }

    public BlocosInterativos getDestruir(){
        BlocosInterativos bloco = new Tronco(painel, mundoX/painel.tamanhoDoTile, mundoY/painel.tamanhoDoTile);
        return bloco;
    }
    public Color getParticulaCor(){
        Color cor = new Color(65,50,30);
        return cor;
    }

    public int getParticulaTamanho(){
        int tamanho = 6; //6 pixels
        return tamanho;
    }
    public int getParticulaVelocidade(){
        int velocidade = 1;
        return velocidade;
    } 

    public int getParticulaVidaMaxima(){
        int vidaMaxima = 20;
        return vidaMaxima;
    }
    
}
