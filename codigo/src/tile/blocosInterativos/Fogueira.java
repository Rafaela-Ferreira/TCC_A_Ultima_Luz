package tile.blocosInterativos;

import java.awt.Graphics2D;

import main.PainelDoJogo;



public class Fogueira extends BlocosInterativos{
    
    PainelDoJogo painel;

    public Fogueira(PainelDoJogo painel, int coluna, int linha) {
        super(painel, coluna, linha);
        this.painel = painel;

        this.mundoX = painel.tamanhoDoTile * coluna;
        this.mundoY = painel.tamanhoDoTile * linha;

        int i = 3;
        baixo1 = setup("/res/fogueira/fogueira1", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        baixo2 = setup("/res/fogueira/fogueira2", painel.tamanhoDoTile*i, painel.tamanhoDoTile*i);
        destruir = true;

      

        areaSolida.x = 0;
        areaSolida.y = 0;
        areaSolida.width = 0;
        areaSolida.height = 0;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        
    }


    public void atualizar(){
        spriteContador++;

        if(spriteContador > 15){ // velocidade da animação
            if(spriteNum == 1){
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }
            spriteContador = 0;
        }
       
       
        if(invencivel == true){
            invencivelContador++;
            
            if(invencivel == true){
                invencivelContador++;
                
                if(invencivelContador > 20){
                    invencivel = false;
                    invencivelContador = 0;
                }
            }
        }
     }
    
    
    public void desenhar(Graphics2D g2){
        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

        // Verifica se o bloco está dentro da área visível do jogador
        if (mundoX + painel.tamanhoDoTile > painel.jogador.mundoX - painel.jogador.telaX && 
            mundoX - painel.tamanhoDoTile < painel.jogador.mundoX + painel.jogador.telaX &&
            mundoY + painel.tamanhoDoTile > painel.jogador.mundoY - painel.jogador.telaY &&
            mundoY - painel.tamanhoDoTile < painel.jogador.mundoY + painel.jogador.telaY) {

            //g2.drawImage(baixo1, telaX, telaY, null);

            if(spriteNum == 1){
                g2.drawImage(baixo1, telaX, telaY, null);
            }
            if(spriteNum == 2){
                g2.drawImage(baixo2, telaX, telaY, null);
            }

           
        }
    }

}
