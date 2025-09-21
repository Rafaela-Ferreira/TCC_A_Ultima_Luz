package IA;

import java.util.ArrayList;

import entidade.Entidade;
import main.PainelDoJogo;

public class LocalizarCaminhos {

    PainelDoJogo painel;
    No[][] no;
    ArrayList<No> abrirLista = new ArrayList<>();
    public ArrayList<No> listaCaminho = new ArrayList<>();
    No noInicio, noMeta, noAtual;
    boolean metaAlcancada = false;
    int etapa = 0;

    public LocalizarCaminhos( PainelDoJogo painel){
        this.painel = painel;
        instanciarNo();
    }

    public void instanciarNo(){
        no = new No[painel.maxColunasMundo][painel.maxLinhasMundo];

        int coluna = 0;
        int linha = 0;

        while(coluna < painel.maxColunasMundo && linha < painel.maxLinhasMundo){
            no[coluna][linha] = new No(coluna, linha);

            coluna++;
            if(coluna == painel.maxColunasMundo){
                coluna = 0;
                linha++;
            }
        }
    }
    public void reiniciarNos(){
        int coluna = 0;
        int linha = 0;

        while(coluna < painel.maxColunasMundo && linha < painel.maxLinhasMundo){
            //redefinir abrir, verificado e em estado sólido
            no[coluna][linha].abrir = false;
            no[coluna][linha].verificar = false;
            no[coluna][linha].solida = false;

            coluna++;
            if(coluna == painel.maxColunasMundo){
                coluna = 0;
                linha++;
            }
        }
        //redefinir outra configuração
        abrirLista.clear();
        listaCaminho.clear();
        metaAlcancada = false;
        etapa = 0;

    }

    public void setNos(int iniciarColuna, int iniciarLinha, int colunaMeta, int linhaMeta, Entidade entidade){
        
        reiniciarNos();

        //set start and goal node
        noInicio = no[iniciarColuna][iniciarLinha];
        noAtual = noInicio;
        noMeta = no[colunaMeta][linhaMeta];
        abrirLista.add(noAtual);

        int coluna = 0;
        int linha = 0;

        while (coluna < painel.maxColunasMundo && linha < painel.maxLinhasMundo){
            //set solid node
            //check tiles
            int numeroBloco = painel.gerenciadorDeBlocos.numerosDoMapa[painel.mapaAtual][coluna][linha];
            
            if(painel.gerenciadorDeBlocos.blocos[numeroBloco].temColisao == true){
                no[coluna][linha].solida = true;
            }
            //verificar interativide tile
            for(int i = 0; i < painel.blocosI[1].length; i++){
                if(painel.blocosI[painel.mapaAtual][i] != null && painel.blocosI[painel.mapaAtual][i].destruir == true){
                    int interativoColuna = painel.blocosI[painel.mapaAtual][i].mundoX/painel.tamanhoDoTile;
                    int interativoLinha = painel.blocosI[painel.mapaAtual][i].mundoY/painel.tamanhoDoTile;
                    no[interativoColuna][interativoLinha].solida = true;
                }
            }

            //set cost
            getCusto(no[coluna][linha]);

            coluna++;
            if(coluna == painel.maxColunasMundo){
                coluna = 0;
                linha++;


            }
        }

    }

    public void getCusto(No no){
        //G Custo
        int xDistancia = Math.abs(no.coluna - noInicio.coluna);
        int yDistancia = Math.abs(no.linha - noInicio.linha);
        no.gCusto = xDistancia + yDistancia;

        //H Custo
        xDistancia = Math.abs(no.coluna - noMeta.coluna);
        yDistancia = Math.abs(no.linha - noMeta.linha);
        no.hCusto = xDistancia + yDistancia;

        //F Custo
        no.fCusto = no.gCusto + no.hCusto;
        
    }

    public boolean procurar(){

        while(metaAlcancada == false && etapa < 500){
            int coluna = noAtual.coluna;
            int linha = noAtual.linha;

            //verificar o no atual

            noAtual.verificar = true;
            abrirLista.remove(noAtual);

            //open the up node
            if(linha - 1 >= 0){
                abrirNo(no[coluna][linha-1]);
            }
            //open the left node
            if(coluna -1 >= 0){
                abrirNo(no[coluna-1][linha]);
            }
            //open the down node
            if(linha + 1 < painel.maxLinhasMundo){
                abrirNo(no[coluna][linha+1]);
            }

            if(coluna + 1 < painel.maxColunasMundo){
                abrirNo(no[coluna+1][linha]);
            }

            //find the best node
            int melhorIndiceDoNo = 0;
            int melhorIndiceFCusto = 999;

            for(int i = 0; i < abrirLista.size(); i++){
                //check if node's F cost is better
                if(abrirLista.get(i).fCusto < melhorIndiceFCusto){
                    melhorIndiceDoNo = i;
                    melhorIndiceFCusto = abrirLista.get(i).fCusto;
                }
                //if F cost is equal, check the G cost
                else if(abrirLista.get(i).fCusto == melhorIndiceFCusto){
                    if(abrirLista.get(i).gCusto < abrirLista.get(melhorIndiceDoNo).gCusto){
                        melhorIndiceDoNo = i;
                    }
                }
            }

            //if there is no node in the openlist, end the loop

            if(abrirLista.size() == 0){
                break;
            }

            //After the loop, openlist[bestnodeIndex] is the next step (= currentNode)
            noAtual = abrirLista.get(melhorIndiceDoNo);

            if(noAtual == noMeta){
                metaAlcancada = true;
                acompanharOCaminho();
            }
            etapa++;

        }
        return metaAlcancada;
    }

    public void abrirNo(No no){
        if(no.abrir == false && no.verificar == false && no.solida == false){
            no.abrir = true;
            no.pai = noAtual;
            abrirLista.add(no);
        }
    }

    public void acompanharOCaminho(){
        No atual = noAtual;

        while(atual != noInicio){
            listaCaminho.add(0, atual);
            atual = atual.pai;
        }
    }
}
