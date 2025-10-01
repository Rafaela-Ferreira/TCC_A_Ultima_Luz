package objeto;

import java.awt.Color;

import entidade.Entidade;
import entidade.Projetil;
import main.PainelDoJogo;


public class ObjBolaDeFogo extends Projetil{

    PainelDoJogo painel;
    public static final String objNome = "Bola de Fogo";

    public ObjBolaDeFogo(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        nome = objNome;
        velocidade = 5;
        vidaMaxima = 80;
        vida = vidaMaxima;
        ataque = 1;
        poderDoEmpurrao = 5;
        usarConsumivel = 1; //quantidade de mana gasta
        vivo = false;
        //descricao = "Uma bola de fogo que incendeia tudo o que toca.";
        getImagem();
    }

    public void getImagem(){
        
        cima1 = setup("/img/projetavel/fireball_up_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/projetavel/fireball_up_2",  painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/projetavel/fireball_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/projetavel/fireball_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/projetavel/fireball_left_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/projetavel/fireball_left_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/projetavel/fireball_right_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/projetavel/fireball_right_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public boolean temRecursos(Entidade usar){
        boolean temRecursos = false;
        if(usar.mana >= usarConsumivel){
            temRecursos = true;
        }
        return temRecursos;
    }

    public void subtrairRecursos(Entidade usar){
        usar.mana -= usarConsumivel;
    }

    public Color getParticulaCor(){
        Color cor = new Color(240,50,0);
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
