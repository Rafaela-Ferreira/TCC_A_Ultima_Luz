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
        InputStream is = getClass().getResourceAsStream("/res/mapa/Banco.txt");
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
        is = getClass().getResourceAsStream("/res/mapa/1_1PracaDoDespertar.txt");//mapas de 50x50, 100x100, ou 250x250...
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

        //Área 1 - Inicio do Jogo
        carregarMapa("/res/mapa/1_1PracaDoDespertar.txt", 0);      // 1 Praça do despertar
        carregarMapa("/res/mapa/1_3Arena.txt", 1);                 // 1_2 Arena Chefe Tutorial

        //Área 2 
        carregarMapa("/res/mapa/2_1CapelaDaLuzInterior.txt", 2);   // 2_1 Capela Da Luz Interior - Bifurcação para Gula e Avareza

        //Área 3 - Caminhos para o Boss Gula
        carregarMapa("/res/mapa/3_1SalaoDoBanqueteEterno.txt", 3); // 3.1 Salão Do Banquete Eterno
        carregarMapa("/res/mapa/3_2Arena.txt", 4);                 // 3.2 Arena - Chefe Gula
        
        //Área 4 - Caminhos para o Boss Avareza
        carregarMapa("/res/mapa/4_1PortaDeFerro.txt", 5);         // 4.1 Porta De Ferro
        carregarMapa("/res/mapa/4_2CamaraDasCorrentes.txt", 6);   // 4.2 Câmara Das Correntes
        carregarMapa("/res/mapa/4_3Arena.txt", 7);                // 4.3 Arena Chefe Avareza
        


        //Área 5 - Caminhos para o Boss Inveja
        carregarMapa("/res/mapa/5_1JardimDasSombras.txt", 8);     // 5.1 Jardim Das Sombras
        carregarMapa("/res/mapa/5_2Arena.txt", 9);                // 5.2 Arena Chefe Inveja

        //Área 6 - Caminhos para o Boss Luxúria
        carregarMapa("/res/mapa/6_1AuroraPartida.txt", 10);        // 6.1 Aurora Partida
        carregarMapa("/res/mapa/6_2Arena.txt", 11);                // 6.2 Arena Chefe Luxúria

        //Área 7 - Caminhos para o Boss Orgulho
        carregarMapa("/res/mapa/7_1TronoDaLuz.txt", 12);           // 7.1 Trono Da Luz
        carregarMapa("/res/mapa/7_2TronoCaido.txt", 13);           // 7.2 Trono Caído
        carregarMapa("/res/mapa/7_3ArenaFinal.txt", 14);           // 7.3 Arena Final


        //Área 8 - Caminhos para o Boss Preguiça
        carregarMapa("/res/mapa/8_1SalaoDoSonoEterno.txt", 15);    // 8.1 Salão Do Sono Eterno
        carregarMapa("/res/mapa/8_2LanternaDosSonhos.txt", 16);    // 8.2 Lanterna Dos Sonhos
        carregarMapa("/res/mapa/8_3Arena.txt", 17);                // 8.3 Arena Chefe Preguiça

        //Área 9 - Caminhos para o Boss Ira
        carregarMapa("/res/mapa/9_1LaminasSanguinarias.txt", 18);   // 9.1 Lâminas Sanginárias
        carregarMapa("/res/mapa/9_2OFogoRubro.txt", 19);           // 9.2 O Fogo Rubro
        carregarMapa("/res/mapa/9_3Arena.txt", 20);                // 9.3 Arena Chefe Ira
        
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
            blocos[indice].imagem = ImageIO.read(getClass().getResourceAsStream("/res/blocos/" + nomeDaImagem));
            //String caminho = "/img/num/" + nomeDaImagem;
            //System.out.println("Carregando: " + caminho);
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

        if (entrada == null) {
            System.err.println("Erro: Arquivo de mapa não encontrado " + caminhoDoArquivo);
            return;
        }

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
