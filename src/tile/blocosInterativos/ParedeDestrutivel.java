package tile.blocosInterativos;

import java.awt.Color;

import entidade.Entidade;
import main.PainelDoJogo;

//passagens segretas
public class ParedeDestrutivel extends BlocosInterativos{

    PainelDoJogo painel;

    public ParedeDestrutivel(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        baixo1 = setup("/img/tiles/interativos/destructiblewall", painel.tamanhoDoTile, painel.tamanhoDoTile);
        destruir = true;
        vida = 3;
    }
    public boolean itemCorreto(Entidade entidade){
        boolean itemCorreto = false;

        //é necessario usar um machado para cortar uma árvore
        if(entidade.armaAtual.tipo == tipoPicareta){
            itemCorreto = true;
        }

        return itemCorreto;
    }
    public void iniciarEfeitoSonoro(){
        painel.iniciarEfeitoSonoro(20);
    }
    public BlocosInterativos getDestruir(){
        BlocosInterativos bloco = null;
        return bloco;
    }
    public Color getParticulaCor(){
        Color cor = new Color(65,65,65);
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

    /* OPCIONAL
    public void verificarDrop(){
        //lançar um dado
        int i = new Random().nextInt(100)+1;

        //definir o drop do inimigo 
        if(i < 50){
            droparItem(new ObjMoedaBronze(painel));
        }
        if(i >= 50 && i < 75){
            droparItem(new ObjCoracao(painel));
        }
        if(i >= 75 && i < 100){
            droparItem(new ObjMana(painel));
        }
    }
    */
    
}
