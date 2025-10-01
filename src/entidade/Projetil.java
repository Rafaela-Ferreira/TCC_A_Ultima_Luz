package entidade;

import main.PainelDoJogo;

public class Projetil extends Entidade{
    Entidade usuario;

    public Projetil(PainelDoJogo painel) {
        super(painel);
        
    }

    public void setAcao(int mundoX, int mundoY, String direcao, boolean vivo, Entidade usuario){
        this.mundoX = mundoX;
        this.mundoY = mundoY;
        this.direcao = direcao;
        this.vivo = vivo;
        this.usuario = usuario;
        this.vida = this.vidaMaxima;
        
    }

    public void atualizar(){
        if(usuario == painel.jogador){
            //verificar de bola de fogo colidiu com inimigo
            int indiceInimigo = painel.colisaoChecked.verificarEntidade(this, painel.inimigo);

            if(indiceInimigo != 999){
                //quanto mais forte você fica maior é o dano do ataque!
                painel.jogador.danoDoInimigo(indiceInimigo, this, ataque*(painel.jogador.nivel/2), poderDoEmpurrao);
                geradorParticula(usuario.projetil, painel.inimigo[painel.mapaAtual][indiceInimigo]);
                vivo = false;
            }
        }

        if(usuario != painel.jogador){
            
            boolean contatoComJogador = painel.colisaoChecked.verificarJogador(this);
            
            if(painel.jogador.invencivel == false && contatoComJogador == true){
                danoJogador(ataque);
                geradorParticula(usuario.projetil, usuario.projetil);
                vivo = false;
            }

        }

        switch (direcao) {
            case "cima": mundoY -= velocidade; break;
            case "baixo": mundoY += velocidade; break;
            case "esquerda": mundoX -= velocidade; break;
            case "direita": mundoX += velocidade; break; 
        }
        
        vida--;
        if(vida <= 0){
            vivo = false;
        }

        contadorDeSprite++;
        if(contadorDeSprite > 12){
            if(numeroDoSprite == 1){
                numeroDoSprite = 2;
            }else if(numeroDoSprite == 2){
                numeroDoSprite = 1;
            }
            contadorDeSprite = 0;
        }
    }

    public boolean temRecursos(Entidade usar){
        boolean temRecursos = false;
        
        return temRecursos;
    }
    public void subtrairRecursos(Entidade usar){
        
    }
    
}
