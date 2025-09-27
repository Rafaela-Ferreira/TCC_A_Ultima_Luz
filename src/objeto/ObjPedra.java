package objeto;

import java.awt.Color;

import entidade.Entidade;
import entidade.Projetil;
import main.PainelDoJogo;

public class ObjPedra extends Projetil{
    PainelDoJogo painel;
    public static final String objNome = "Pedra";

    public ObjPedra(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        nome = objNome;
        velocidade = 8;
        vidaMaxima = 80;
        vida = vidaMaxima;
        ataque = 2;
        usarConsumivel = 1; //quantidade de mana gasta
        vivo = false;
        //descricao = "Uma bola de fogo que incendeia tudo o que toca.";
        getImagem();
    }

    public void getImagem(){
        
        cima1 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/projetavel/rock_down_1",  painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/projetavel/rock_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }
    
    public boolean temRecursos(Entidade usar){
        boolean temRecursos = false;
        if(usar.municao >= usarConsumivel){
            temRecursos = true;
        }
        return temRecursos;
    }

    public void subtrairRecursos(Entidade usar){
        usar.municao -= usarConsumivel;
    }

     public Color getParticulaCor(){
        Color cor = new Color(40,50,0);
        return cor;
    }

    public int getParticulaTamanho(){
        int tamanho = 10; //10 pixels
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
