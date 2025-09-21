package objeto;


import entidade.Entidade;
import main.PainelDoJogo;

public class ObjBau extends Entidade {
    
    PainelDoJogo painel;
    Entidade saque;
    boolean aberto = false;

    public ObjBau(PainelDoJogo painel, Entidade saque) {

        super(painel);
        this.painel = painel;
        this.saque = saque;


        tipo = tipoObstaculo;
        nome = "Bau";
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
    
    public void interagir(){
        painel.estadoDoJogo = painel.estadoDoDialogo;

        if(aberto == false){
            painel.iniciarEfeitoSonoro(3);

            StringBuffer sb = new StringBuffer();
            sb.append("Você abre o baú e encontra um " + saque.nome + "1");

            if(painel.jogador.podeObterItem(saque) == false){
                sb.append("\n...Mas você não pode carregar mais nada!");

            }else{
                sb.append("\nVocê obteve um " + saque.nome + "!");
                baixo1 = imagem2;
                aberto = true;
            }
            painel.interfaceDoUsuario.dialogoAtual = sb.toString();
        }
        else{
            painel.interfaceDoUsuario.dialogoAtual = "Está vazio";
        }
    }

}
