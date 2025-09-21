package main.inimigo;

import java.util.Random;

import entidade.Entidade;
import main.PainelDoJogo;
import objeto.ObjCoracao;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;
import objeto.ObjPedra;

public class lodoVerde extends Entidade{

    PainelDoJogo painel;

    public lodoVerde(PainelDoJogo painel) {
        super(painel);

        this.painel = painel;
        
        tipo = tipoInimigo;
        nome ="Lodo Verde";
        velocidadePadrao = 1;
        velocidade = velocidadePadrao;
        vidaMaxima = 4;
        vida = vidaMaxima;
        ataque = 5;
        defesa = 0;
        exp = 2;

        projetil = new ObjPedra(painel);

        areaSolida.x = 3;
        areaSolida.y = 18;
        areaSolida.width = 42;
        areaSolida.height = 30;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;

        getImagem();
    }
    
    public void getImagem(){
        cima1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/inimigo/greenslime_down_1", painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/inimigo/greenslime_down_2", painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void atualizar(){
        super.atualizar();

        int Xdistancia = Math.abs(mundoX - painel.jogador.mundoX);
        int Ydistancia = Math.abs(mundoY - painel.jogador.mundoY);
        int tamanhoDistancia = (Xdistancia + Ydistancia) / painel.tamanhoDoTile;

        if(pastaAtiva == false && tamanhoDistancia < 5){
            int i = new Random().nextInt(100)+1;
            if(i > 50){
                pastaAtiva = true;
            }
        }
        //desiste de perseguir o jogador
        if(pastaAtiva == true && tamanhoDistancia > 20){
            pastaAtiva = false;
        }
    }

    public void setAcao(){
        if(pastaAtiva == true){
            
            //para o inimigo seguir o jogador
            int metaColuna = (painel.jogador.mundoX + painel.jogador.areaSolida.x) / painel.tamanhoDoTile;
            int metaLinha = (painel.jogador.mundoY + painel.jogador.areaSolida.y) / painel.tamanhoDoTile;

            procurarCaminho(metaColuna, metaLinha);

            //começa a atirar pedra
            int i = new Random().nextInt(200)+1;

            if(i > 197 && projetil.vivo == false && contadorDeTiro == 30){
                
                projetil.setAcao(mundoX, mundoY, direcao, true, this);
                //painel.listaProjetil.add(projetil);

                //verificar vaga
                for(int ii = 0; ii < painel.projetavel[1].length; ii++){
                    if(painel.projetavel[painel.mapaAtual][ii] == null){
                        painel.projetavel[painel.mapaAtual][ii] = projetil;
                        break;
                    }
                }

                contadorDeTiro = 0;
            }

        }else{
            contadorDeBloqueioDeAcao++;

            //esperar 120 (2 segundos) para mudar de direção
            if(contadorDeBloqueioDeAcao == 120){
                Random random = new Random();
                int i = random.nextInt(100) + 1; //0 - 100

                if(i <= 25){
                    direcao = "cima";
                }
                if(i > 25 && i <= 50){
                    direcao = "baixo";
                }
                if(i > 50 && i <= 75){
                    direcao = "esquerda";
                }
                if(i > 75 && i <= 100){
                    direcao = "direita";
                }

                contadorDeBloqueioDeAcao = 0;
            }
        }

        
    }

    public void acaoAoDano(){
        contadorDeBloqueioDeAcao = 0;
        //direcao = painel.jogador.direcao;
        pastaAtiva = true;
    }


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
}
