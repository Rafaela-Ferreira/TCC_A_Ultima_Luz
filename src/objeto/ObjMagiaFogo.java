//OBS: Adicionado -----------------------------------------------------
package objeto;

import entidade.Entidade;
import entidade.Projetil;
import main.PainelDoJogo;

public class ObjMagiaFogo extends Projetil{
    public static final String objNome = "Magia de Fogo";

    PainelDoJogo painel;
    public ObjMagiaFogo(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;

        tipo = tipoMagia;
        nome = objNome;
        valorAtaque = 1;
        areaAtaque.width = 36;
        areaAtaque.height = 36;
        direcaoDoMovimento1 = 20;
        direcaoDoMovimento2 = 60;
        velocidade = 4;   
        vidaMaxima = 25;   
        vida = vidaMaxima;
        ataque = 1;
        usarConsumivel = 1;
        vivo = false;
        
        getImagem();
    }

    public void getImagem() {
        cima1 = setup("/res/projetavel/fogo_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/res/projetavel/fogo_up_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/res/projetavel/fogo_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/res/projetavel/fogo_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        
        esquerda1 = setup("/res/projetavel/fogo_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/res/projetavel/fogo_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/res/projetavel/fogo_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/res/projetavel/fogo_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public boolean temRecursos(Entidade usar){
        return true;
    }

    public java.awt.Color getParticulaCor(){
        java.awt.Color cor = new java.awt.Color(240, 50, 0);
        return cor;
    }

    public int getParticulaTamanho(){
        int tamanho = 10;
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
//--------------------------------------------------------------------
