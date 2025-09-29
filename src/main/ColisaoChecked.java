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

        //use uma direção tamporal quando estiver sendo empurrado para trás
        String direcao = entidade.direcao;
        if(entidade.empurrao == true){
         direcao = entidade.direcaoDoempurrao;

        }


        switch (direcao) {
            case "cima":
                entidadeTopoLinha = (entidadeTopoMundoY - entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeEsquerdaColuna][entidadeTopoLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeDireitaColuna][entidadeTopoLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                } 

                break;
            case "baixo":
                entidadeBaseLinha = (entidadeBaseMundoY + entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeEsquerdaColuna][entidadeBaseLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeDireitaColuna][entidadeBaseLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                }
                break;
            case "esquerda":
                entidadeEsquerdaColuna = (entidadeEsquerdaMundoX - entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeEsquerdaColuna][entidadeTopoLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeEsquerdaColuna][entidadeBaseLinha];
                
                if(painel.gerenciadorDeBlocos.blocos[numeroDoBloco1].temColisao == true ||
                    painel.gerenciadorDeBlocos.blocos[numeroDoBloco2].temColisao == true) {
                    entidade.colisaoComBloco = true; // Define que houve colisão
                }
                break;
            case "direita":
                entidadeDireitaColuna = (entidadeDireitaMundoX + entidade.velocidade) / painel.tamanhoDoTile;
                numeroDoBloco1 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeDireitaColuna][entidadeTopoLinha];
                numeroDoBloco2 = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][entidadeDireitaColuna][entidadeBaseLinha];
                
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

        //use uma direção tamporal quando estiver sendo empurrado para trás
        String direcao = entidade.direcao;
        if(entidade.empurrao == true){
         direcao = entidade.direcaoDoempurrao;

        }

        for (int i = 0; i < painel.Obj[1].length; i++) {
            if (painel.Obj[painel.mapaAtual][i] != null) {

                // Lógica de colisão
                entidade.areaSolida.x = entidade.mundoX + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoY + entidade.areaSolida.y;

                painel.Obj[painel.mapaAtual][i].areaSolida.x = painel.Obj[painel.mapaAtual][i].mundoX + painel.Obj[painel.mapaAtual][i].areaSolida.x;
                painel.Obj[painel.mapaAtual][i].areaSolida.y = painel.Obj[painel.mapaAtual][i].mundoY + painel.Obj[painel.mapaAtual][i].areaSolida.y;

                switch (direcao) {
                    case "cima": entidade.areaSolida.y -= entidade.velocidade; break;
                    case "baixo": entidade.areaSolida.y += entidade.velocidade; break;
                    case "esquerda": entidade.areaSolida.x -= entidade.velocidade; break;
                    case "direita": entidade.areaSolida.x += entidade.velocidade; break;
                }

                if (entidade.areaSolida.intersects(painel.Obj[painel.mapaAtual][i].areaSolida)) {
                    if (painel.Obj[painel.mapaAtual][i].temColisao) entidade.colisaoComBloco = true;
                    if (jogador) indice = i;
                }

                //Reset seguro
                entidade.areaSolida.x = entidade.areaSolidaPadraoX; 
                entidade.areaSolida.y = entidade.areaSolidaPadraoY;
                painel.Obj[painel.mapaAtual][i].areaSolida.x = painel.Obj[painel.mapaAtual][i].areaSolidaPadraoX;
                painel.Obj[painel.mapaAtual][i].areaSolida.y = painel.Obj[painel.mapaAtual][i].areaSolidaPadraoY;
            }
        }
        return indice; // Retorna o índice do objeto se houver colisão, ou 999 se não houver
    }

    //verificar colisão de NPC ou mostros
    public int verificarEntidade(Entidade entidade, Entidade[][] alvo){
        int indice = 999;

        //use uma direção tamporal quando estiver sendo empurrado para trás
        String direcao = entidade.direcao;
        if(entidade.empurrao == true){
         direcao = entidade.direcaoDoempurrao;

        }

        for (int i = 0; i < alvo[1].length; i++) {
            if (alvo[painel.mapaAtual][i] != null) {

                // Lógica de colisão
                entidade.areaSolida.x = entidade.mundoX + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoY + entidade.areaSolida.y;

                alvo[painel.mapaAtual][i].areaSolida.x = alvo[painel.mapaAtual][i].mundoX + alvo[painel.mapaAtual][i].areaSolida.x;
                alvo[painel.mapaAtual][i].areaSolida.y = alvo[painel.mapaAtual][i].mundoY + alvo[painel.mapaAtual][i].areaSolida.y;

                switch (direcao) {
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

                if (entidade.areaSolida.intersects(alvo[painel.mapaAtual][i].areaSolida)) {
                    if(alvo[painel.mapaAtual][i] != entidade){
                        entidade.colisaoComBloco = true;
                        indice = i;
                    }

                }

                //Reset seguro
                entidade.areaSolida.x = entidade.areaSolidaPadraoX; 
                entidade.areaSolida.y = entidade.areaSolidaPadraoY;
                alvo[painel.mapaAtual][i].areaSolida.x = alvo[painel.mapaAtual][i].areaSolidaPadraoX;
                alvo[painel.mapaAtual][i].areaSolida.y = alvo[painel.mapaAtual][i].areaSolidaPadraoY;
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
