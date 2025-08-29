package main;

import entidade.Entidade;

public class ColisaoChecked {
    PainelDoJogo painel;


    public ColisaoChecked(PainelDoJogo painel) {
        this.painel = painel;
    }

    public void verificarColisao(Entidade entidade){
        int entidadeEsquerdaMundoX = entidade.mundoX + entidade.areaSolida.x;
        int entidadeDireitaMundoX = entidade.mundoX + entidade.areaSolida.x + entidade.areaSolida.width;
        int entidadeTopoMundoY = entidade.mundoY + entidade.areaSolida.y;
        int entidadeBaseMundoY = entidade.mundoY + entidade.areaSolida.y + entidade.areaSolida.height;

        int entidadeEsquerdaColuna = entidadeEsquerdaMundoX / painel.tamanhoDoTile;
        int entidadeDireitaColuna = entidadeDireitaMundoX / painel.tamanhoDoTile;
        int entidadeTopoLinha = entidadeTopoMundoY / painel.tamanhoDoTile;
        int entidadeBaseLinha = entidadeBaseMundoY / painel.tamanhoDoTile;

        int numeroDoBloco1, numeroDoBloco2;

        switch (entidade.direcao) {
            case "cima":
                entidadeTopoLinha = (entidadeTopoMundoY - entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeEsquerdaColuna][entidadeTopoLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeDireitaColuna][entidadeTopoLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                } 

                break;
            case "baixo":
                entidadeBaseLinha = (entidadeBaseMundoY + entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeEsquerdaColuna][entidadeBaseLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeDireitaColuna][entidadeBaseLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                }
                break;
            case "esquerda":
                entidadeEsquerdaColuna = (entidadeEsquerdaMundoX - entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeEsquerdaColuna][entidadeTopoLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeEsquerdaColuna][entidadeBaseLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                }
                break;
            case "direita":
                entidadeDireitaColuna = (entidadeDireitaMundoX + entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeDireitaColuna][entidadeTopoLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[entidadeDireitaColuna][entidadeBaseLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                }
                break;
            default:
                return; // Se a direção não for válida, sai do método
        }

    }


    public int verificarObjeto(Entidade entidade, boolean jogador) {
        
        int indice = 999;

        for (int i = 0; i < painel.Obj.length; i++) {
            if (painel.Obj[i] != null) {

                // Lógica de colisão
                entidade.areaSolida.x = entidade.mundoX + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoY + entidade.areaSolida.y;

                painel.Obj[i].areaSolida.x = painel.Obj[i].mundoX + painel.Obj[i].areaSolida.x;
                painel.Obj[i].areaSolida.y = painel.Obj[i].mundoY + painel.Obj[i].areaSolida.y;

                switch (entidade.direcao) {
                    case "cima": entidade.areaSolida.y -= entidade.velocidade; break;
                    case "baixo": entidade.areaSolida.y += entidade.velocidade; break;
                    case "esquerda": entidade.areaSolida.x -= entidade.velocidade; break;
                    case "direita": entidade.areaSolida.x += entidade.velocidade; break;
                }

                if (entidade.areaSolida.intersects(painel.Obj[i].areaSolida)) {
                    if (painel.Obj[i].temColisao) entidade.colisaoComBloco = true;
                    if (jogador) indice = i;
                }

                //Reset seguro
                entidade.areaSolida.x = entidade.areaSolidaPadraoX; 
                entidade.areaSolida.y = entidade.areaSolidaPadraoY;
                painel.Obj[i].areaSolida.x = painel.Obj[i].areaSolidaPadraoX;
                painel.Obj[i].areaSolida.y = painel.Obj[i].areaSolidaPadraoY;
            }
        }
        return indice; // Retorna o índice do objeto se houver colisão, ou 999 se não houver
    }

    //verificar colisão de NPC ou mostros
    public int verificarEntidade(Entidade entidade, Entidade[] target){
        int indice = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                // Lógica de colisão
                entidade.areaSolida.x = entidade.mundoX + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoY + entidade.areaSolida.y;

                target[i].areaSolida.x = target[i].mundoX + target[i].areaSolida.x;
                target[i].areaSolida.y = target[i].mundoY + target[i].areaSolida.y;

                switch (entidade.direcao) {
                    case "cima":
                        entidade.areaSolida.y -= entidade.velocidade; 
                        break;
                    case "baixo":
                        entidade.areaSolida.y += entidade.velocidade;
                        break;
                    case "esquerda":
                        entidade.areaSolida.x -= entidade.velocidade;
                        break;
                    case "direita":
                        entidade.areaSolida.x += entidade.velocidade;

                        break;
                }

                if (entidade.areaSolida.intersects(target[i].areaSolida)) {
                    if(target[i] != entidade){
                        entidade.colisaoComBloco = true;
                        indice = i;
                    }

                }

                //Reset seguro
                entidade.areaSolida.x = entidade.areaSolidaPadraoX; 
                entidade.areaSolida.y = entidade.areaSolidaPadraoY;
                target[i].areaSolida.x = target[i].areaSolidaPadraoX;
                target[i].areaSolida.y = target[i].areaSolidaPadraoY;
            }
        }
        return indice; // Retorna o índice do objeto se houver colisão, ou 999 se não houver
    }

    public boolean verificarJogador(Entidade entidade){

        boolean contadoComjogador = false;

        // Lógica de colisão
        entidade.areaSolida.x = entidade.mundoX + entidade.areaSolida.x;
        entidade.areaSolida.y = entidade.mundoY + entidade.areaSolida.y;

        painel.jogador.areaSolida.x = painel.jogador.mundoX + painel.jogador.areaSolida.x;
        painel.jogador.areaSolida.y = painel.jogador.mundoY + painel.jogador.areaSolida.y;

        switch (entidade.direcao) {
            case "cima": entidade.areaSolida.y -= entidade.velocidade; break;
            case "baixo": entidade.areaSolida.y += entidade.velocidade; break;
            case "esquerda": entidade.areaSolida.x -= entidade.velocidade; break;
            case "direita": entidade.areaSolida.x += entidade.velocidade; break;
        }

        if (entidade.areaSolida.intersects(painel.jogador.areaSolida)) {
            entidade.colisaoComBloco = true;
            contadoComjogador= true;
        }

        //Reset seguro
        entidade.areaSolida.x = entidade.areaSolidaPadraoX; 
        entidade.areaSolida.y = entidade.areaSolidaPadraoY;
        painel.jogador.areaSolida.x = painel.jogador.areaSolidaPadraoX;
        painel.jogador.areaSolida.y = painel.jogador.areaSolidaPadraoY;

        return contadoComjogador;
    }


}
