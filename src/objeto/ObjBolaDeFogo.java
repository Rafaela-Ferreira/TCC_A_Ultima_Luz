package objeto;

import entidade.Projetil;
import main.PainelDoJogo;


public class ObjBolaDeFogo extends Projetil{

    PainelDoJogo painel;

    public ObjBolaDeFogo(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;

        nome = "Bola de Fogo";
        velocidade = 5;
        vidaMaxima = 80;
        vida = vidaMaxima;
        ataque = 2;
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
    
}
