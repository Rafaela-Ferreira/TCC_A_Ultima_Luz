package tile.blocosInterativos;

import java.awt.Color;

import entidade.Entidade;
import main.PainelDoJogo;

public class ArvoreSeca extends BlocosInterativos{

    PainelDoJogo painel;

    public ArvoreSeca(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/tiles/interativos/drytree", painel.tamanhoDoTile, painel.tamanhoDoTile);
        destruir = true;
        vida = 3;
    }
    public boolean itemCorreto(Entidade entidade){
        boolean itemCorreto = false;

        //é necessario usar um machado para cortar uma árvore
        if(entidade.armaAtual.tipo == tipoMachado){
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
