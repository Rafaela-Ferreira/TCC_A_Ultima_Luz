package tile.blocosInterativos;

import java.awt.Graphics2D;

import entidade.Entidade;
import main.PainelDoJogo;

public class BlocosInterativos extends Entidade{

    PainelDoJogo painel;
    public boolean destruir = false;

    public BlocosInterativos(PainelDoJogo painel, int coluna, int linha) {
        super(painel);
        this.painel= painel;


    }
    public boolean itemCorreto(Entidade entidade){
        boolean itemCorreto = false;
        return itemCorreto;
    }
    public void iniciarEfeitoSonoro(){

    }
    public BlocosInterativos getDestruir(){
        BlocosInterativos bloco = null;
        return bloco;
    }

    public void atualizar(){
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

            g2.drawImage(baixo1, telaX, telaY, null);

           
        }
    }   
    
}
