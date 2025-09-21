package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.CaixaDeFerramentas;
import main.PainelDoJogo;

public class GerenciadorDeBlocos {
    PainelDoJogo painel;//gp
    public Bloco[] blocos; // Array de tiles

    //caregar o artivo de txt
    //primeira dimensão da matriz para armazenar o numero de mapas
    public int numerosDoMapa[][][]; //usado tbm na verificação de colisão
    public int[][][] numeroBloco;
    boolean desenharCaminho = true;
    

    //OBS: tileManager
    public GerenciadorDeBlocos(PainelDoJogo painel) {
        this.painel = painel;

        blocos = new Bloco[50]; // Inicializa o array de blocos com tamanho 50
        numerosDoMapa = new int [painel.maxMapa][painel.maxColunasMundo][painel.maxLinhasMundo]; //matriz tridimencional
        
        carregarImagensDosBlocos();
        carregarMapa("/mapas/mapaV3.txt", 0);
        carregarMapa("/mapas/interior01.txt", 1);
    }

    public void carregarImagensDosBlocos() {

        //deixar esses tiles para não ocorrer erro de NullPointerException
        setup(0, "grass", false);
        setup(1, "grass", false);
        setup(2, "grass", false);
        setup(3, "grass", false);
        setup(4, "grass", false);
        setup(5, "grass", false);
        setup(6, "grass", false);
        setup(7, "grass", false);
        setup(8, "grass", false);
        setup(9, "grass", false);
        setup(10, "grass", false);
        //deixar esses tiles a cima para não ocorrer erro de NullPointerException
        
        //Utilizar dois digitos na matriz facilita a visualização
        //Os tiles a baixo é o que estão sendo utilizados
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road11", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
        setup(42, "hut", false);
        setup(43, "floor01", false);
        setup(44, "table01", true);
       
    }

    public void setup(int indice, String nomeDaImagem, boolean temColisao) {
        
        CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
        
        try {
            blocos[indice] = new Bloco();
            blocos[indice].imagem = ImageIO.read(getClass().getResourceAsStream("/img/tiles/newVersion/" + nomeDaImagem + ".png"));
            blocos[indice].imagem = ferramentas.escalaImage(blocos[indice].imagem, painel.tamanhoDoTile, painel.tamanhoDoTile); // Define se o bloco tem colisão
             blocos[indice].temColisao = temColisao; 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void carregarMapa( String caminhoDoArquivo, int mapa) {
        // Método para carregar o mapa a partir de um arquivo de texto
        // Aqui você pode implementar a lógica para ler o arquivo e preencher o mapaTileNum
        // Exemplo: cada número no arquivo representa um tipo de tile

        try {
        InputStream entrada = getClass().getResourceAsStream(caminhoDoArquivo);
        BufferedReader leitor  = new BufferedReader(new InputStreamReader(entrada));

        int linha = 0, coluna = 0;

        while (linha < painel.maxLinhasMundo) {
            String linhaMapa = leitor .readLine();
            if (linhaMapa == null) break;

            String[] numeros = linhaMapa.trim().split(" ");

            for (coluna = 0; coluna < painel.maxColunasMundo; coluna++) {
                if (coluna < numeros.length) {
                    int num = Integer.parseInt(numeros[coluna]);
                    numerosDoMapa[mapa][coluna][linha] = num;
                } else {
                    numerosDoMapa[mapa][coluna][linha] = 0; // fallback
                }
            }

            linha++;
        }

        leitor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desenhar(Graphics2D g2) {
        // Método para desenhar os tiles no painel
        int colunaDoMundo = 0, linhaDoMundo = 0;

        while (colunaDoMundo < painel.maxColunasMundo && linhaDoMundo < painel.maxLinhasMundo) {
        
            int numeroDoBloco = numerosDoMapa[painel.mapaAtual][colunaDoMundo][linhaDoMundo];

            int mundoX = colunaDoMundo * painel.tamanhoDoTile; 
            int mundoY = linhaDoMundo * painel.tamanhoDoTile; 
            int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
            int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

            // Verifica se o bloco está dentro da área visível do jogador
            if (mundoX + painel.tamanhoDoTile > painel.jogador.mundoX - painel.jogador.telaX && 
                mundoX - painel.tamanhoDoTile < painel.jogador.mundoX + painel.jogador.telaX &&
                mundoY + painel.tamanhoDoTile > painel.jogador.mundoY - painel.jogador.telaY &&
                mundoY - painel.tamanhoDoTile < painel.jogador.mundoY + painel.jogador.telaY) {
                
                g2.drawImage(blocos[numeroDoBloco].imagem, telaX, telaY,  null);
            }

            colunaDoMundo++;

            if (colunaDoMundo == painel.maxColunasMundo) {
                colunaDoMundo = 0;
                linhaDoMundo++;
            }
        }

        if(desenharCaminho == true){
            g2.setColor(new Color(255,0,0,70));

            for(int i = 0; i < painel.localizarCaminhos.listaCaminho.size(); i++){

                int mundoX = painel.localizarCaminhos.listaCaminho.get(i).coluna * painel.tamanhoDoTile;
                int mundoY = painel.localizarCaminhos.listaCaminho.get(i).linha * painel.tamanhoDoTile; 
                int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
                int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;

                g2.fillRect(telaX, telaY, painel.tamanhoDoTile, painel.tamanhoDoTile);
            }
        }
        
    }
}
