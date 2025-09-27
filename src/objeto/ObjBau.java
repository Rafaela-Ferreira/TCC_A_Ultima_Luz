package objeto;


import entidade.Entidade;
import main.PainelDoJogo;

public class ObjBau extends Entidade {
    
    PainelDoJogo painel;

    public static final String objNome = "Bau";

    public ObjBau(PainelDoJogo painel) {

        super(painel);
        this.painel = painel;


        tipo = tipoObstaculo;
        nome = objNome;
        imagem = setup("/img/itens/chest", painel.tamanhoDoTile, painel.tamanhoDoTile);
        imagem2 = setup("/img/objetos/chest_opened", painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = imagem;
        colisaoComBloco = true;


        areaSolida.x = 4;
        areaSolida.y = 16;
        areaSolida.width = 40;
        areaSolida.height = 32;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        

    }
    public void setSaque(Entidade saque){
        this.saque = saque;
        
        setDialogo();
    }

    public void setDialogo(){
        dialogo[0][0] = "Você abre o baú e encontra um " + saque.nome + "\n...Mas você não pode carregar mais nada!";
        dialogo[1][0] = "Você abre o baú e encontra um " + saque.nome + "\nVocê obteve um " + saque.nome + "!";
        dialogo[2][0] = "Está vazio";
    
    }
    
    public void interagir(){

        if(aberto == false){
            painel.iniciarEfeitoSonoro(3);

            if(painel.jogador.podeObterItem(saque) == false){
                iniciarDialogo(this, 0);

            }else{
                iniciarDialogo(this, 1);
                baixo1 = imagem2;
                aberto = true;
            }

        }
        else{
            iniciarDialogo(this, 2);
        }
    }

}
