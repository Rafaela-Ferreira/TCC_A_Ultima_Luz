package objeto;

import entidade.Entidade;
import main.PainelDoJogo;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class ObjChuva extends Entidade {
    PainelDoJogo painel;
    public static final String objNome = "Chuva";

    private int[] gotasX;
    private int[] gotasY;
    private int quantidade; // quantidade de gotas
    private Random random;

    private boolean ativa = false;

    // Controle do tempo
    private long inicioChuva;
    private long duracaoChuva = 30 * 1000; // 30 segundos de chuva
    private long tempoEspera; // tempo aleatório para a próxima chuva

    private int inclinacao = 5;
    private int comprimento = 15;
    private int larguraGota = 1;

    private float intensidadeEscuro = 0f; // 0 = claro, 1 = bem escuro
    private float velocidadeEscuro = 0.01f; // velocidade da transição

    public ObjChuva(PainelDoJogo painel) {
        super(painel);
        this.painel = painel;
        this.random = new Random();

        definirIntensidadeAleatoria();
        resetarChuva();

        // Pelo menos 30s (30000ms), até no máximo 36s (36000ms)
        tempoEspera = 30000 + random.nextInt(6000);
        inicioChuva = System.currentTimeMillis() - duracaoChuva; // para iniciar após espera
    }

    public void atualizar() {

        // Só atualiza a chuva se estivermos na área "fora"
        if (painel.areaAtual != painel.fora) {
            ativa = false; // garante que pare de chover
            inicioChuva = System.currentTimeMillis(); // reinicia contador para a próxima chuva
            return;
        }
    
        long agora = System.currentTimeMillis();

        if (ativa) {
            // Se a chuva já durou tempo suficiente, para
            if (agora - inicioChuva >= duracaoChuva) {
                ativa = false;
                // define tempo de espera para próxima chuva
                tempoEspera = 30000 + random.nextInt(6000); 
                inicioChuva = agora; // reinicia contador
                return;
            }

            // Atualiza posição das gotas
            for (int i = 0; i < quantidade; i++) {
                gotasY[i] += 5;                 // velocidade da chuva
                gotasX[i] += inclinacao;        // inclinada

                // se sair da tela, reinicia no topo
                if (gotasY[i] > painel.alturaTela) {
                    gotasY[i] = -random.nextInt(50); // reaparece acima da tela
                    int larguraExtra = painel.alturaTela / inclinacao * 20; // espaço extra para compensar inclinação
                    gotasX[i] = random.nextInt(painel.larguraTela + larguraExtra) - larguraExtra;
                }
            }
        } else {
            // Se não está chovendo, espera o tempoEspera para iniciar novamente
            if (agora - inicioChuva >= tempoEspera) {
                setAtiva(true);
            }
        }

        // --- Transição suave ---
        if (ativa && intensidadeEscuro < 1f) {
            intensidadeEscuro += velocidadeEscuro;
            if (intensidadeEscuro > 1f) intensidadeEscuro = 1f;
        } else if (!ativa && intensidadeEscuro > 0f) {
            intensidadeEscuro -= velocidadeEscuro;
            if (intensidadeEscuro < 0f) intensidadeEscuro = 0f;
        }
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
        if (ativa) {
            definirIntensidadeAleatoria();
            resetarChuva();
            inicioChuva = System.currentTimeMillis();
        }
    }

    private void resetarChuva() {
        gotasX = new int[quantidade];
        gotasY = new int[quantidade];

        int larguraExtra = painel.alturaTela / inclinacao*20; // espaço extra para compensar inclinação

        for (int i = 0; i < quantidade; i++) {
            // gotas podem começar antes da tela para cobrir toda a área
            gotasX[i] = random.nextInt(painel.larguraTela + larguraExtra) - larguraExtra;
            gotasY[i] = random.nextInt(painel.alturaTela);
        }
    }

    private void definirIntensidadeAleatoria() {
        quantidade = 100 + random.nextInt(60); // 40 a 100 gotas
        larguraGota = 1 + random.nextInt(3); // 1 a 3px
        comprimento = 45 + random.nextInt(10); // 10 a 20px
    }

    public void desenhar(Graphics2D g2) {
        // filtro escuro apenas se houver intensidade e estiver na área "fora"
        if (intensidadeEscuro > 0f && painel.areaAtual == painel.fora) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, intensidadeEscuro * 0.4f));
            g2.setColor(new Color(0, 0, 50));
            g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        
        if (!ativa) return;

        g2.setColor(new Color(180, 180, 255, 120));
        g2.setStroke(new java.awt.BasicStroke(larguraGota));

        for (int i = 0; i < quantidade; i++) {
            g2.drawLine(gotasX[i], gotasY[i], gotasX[i] + inclinacao, gotasY[i] + comprimento);
        }
    
    }
}
