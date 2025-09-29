package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
    ArrayList<String> nomeDoArquivos = new ArrayList<>();
    ArrayList<String> estatosColisao = new ArrayList<>();
    

    //OBS: tileManager
    public GerenciadorDeBlocos(PainelDoJogo painel) {
        this.painel = painel;

        //ler arquivo de dados do bloco
        InputStream is = getClass().getResourceAsStream("/mapas/tileData.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //obtendo informações de blocos e colisão do arquivo
        String linha;

        try {
            while((linha = br.readLine()) != null){
                nomeDoArquivos.add(linha);
                estatosColisao.add(br.readLine());
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //inicializar o arquivo tile com base no tamanho dos nomes dos arquivos
        blocos = new Bloco[nomeDoArquivos.size()]; 
        carregarImagensDosBlocos();

        //get the maxmundoCol e row
        is = getClass().getResourceAsStream("/mapas/sample.txt");//mapas de 50x50, 100x100, ou 250x250...
        br = new BufferedReader(new InputStreamReader(is));

        try{
            String linha2 = br.readLine();
            String maximoDeBlocos[] = linha2.split(" ");

            painel.maxColunasMundo = maximoDeBlocos.length;
            painel.maxLinhasMundo = maximoDeBlocos.length;
            numerosDoMapa = new int [painel.maxMapa][painel.maxColunasMundo][painel.maxLinhasMundo]; //matriz tridimencional

            br.close();

        }catch(IOException e){
            System.out.println("Exceção! Mapa não encontrado!");
        }

        carregarMapa("/mapas/sample.txt", 0);
        carregarMapa("/mapas/interior02.txt", 1);
        carregarMapa("/mapas/masmorra01.txt", 2);
        carregarMapa("/mapas/masmorra02.txt", 3);
        
        //carregarMapa("/mapas/mapaV3.txt", 0);
        //carregarMapa("/mapas/interior01.txt", 1);
    }

    public void carregarImagensDosBlocos() {

        for(int i = 0; i < nomeDoArquivos.size(); i++){
            String nomeDoArquivo;
            boolean colisao;

            //get a file name
            nomeDoArquivo = nomeDoArquivos.get(i);

            if(estatosColisao.get(i).equals("true")){
                colisao = true;
            }
            else{
                colisao = false;
            }

            setup(i, nomeDoArquivo, colisao);

        }

    }

    public void setup(int indice, String nomeDaImagem, boolean temColisao) {
        
        CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
        
        try {
            blocos[indice] = new Bloco();
            blocos[indice].imagem = ImageIO.read(getClass().getResourceAsStream("/img/num/" + nomeDaImagem));
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
