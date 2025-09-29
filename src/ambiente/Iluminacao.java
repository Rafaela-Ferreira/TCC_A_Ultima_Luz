package ambiente;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.PainelDoJogo;

public class Iluminacao {
    PainelDoJogo painel;
    BufferedImage filtroDeEscuridao;

    public int contadorDia;
    public float filtroAlpha = 0f;

    //estados do dia
    public final int dia = 0;
    public final int anoitecer = 1; //dusk
    public final int noite = 2;
    public final int amanhecer = 3; //dawn
    public int estadoDia = dia;

    public boolean atualizarLuz = false;


    public Iluminacao(PainelDoJogo painel){
        this.painel = painel;
        setFonteDeLuz();

        
    }

    public void setFonteDeLuz(){
        //criar uma imagem em buffer
        filtroDeEscuridao = new BufferedImage(painel.larguraTela, painel.alturaTela, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)filtroDeEscuridao.getGraphics();


        if(painel.jogador.luzAtual == null){
            g2.setColor(new Color(0, 0, 0.1f, 0.90f)); //escuridão
        }
        else{
            //obtenha o centro x e y do círculo de luz
            int centroX = painel.jogador.telaX + (painel.tamanhoDoTile) / 2;
            int centroY = painel.jogador.telaY + (painel.tamanhoDoTile) / 2;

            


            //crie um efeito de gradação dentro do círculo de luz
            Color cor[] = new Color[12];
            float fracao[] = new float[12];

            cor[0] = new Color(0, 0, 0.1f, 0.1f);
            cor[1] = new Color(0, 0, 0.1f, 0.42f);
            cor[2] = new Color(0, 0, 0.1f, 0.52f);
            cor[3] = new Color(0, 0, 0.1f, 0.61f);
            cor[4] = new Color(0, 0, 0.1f, 0.69f);
            cor[5] = new Color(0, 0, 0.1f, 0.76f);
            cor[6] = new Color(0, 0, 0.1f, 0.82f);
            cor[7] = new Color(0, 0, 0.1f, 0.87f);
            cor[8] = new Color(0, 0, 0.1f, 0.91f);
            cor[9] = new Color(0, 0, 0.1f, 0.92f);
            cor[10] = new Color(0, 0, 0.1f, 0.93f);
            cor[11] = new Color(0, 0, 0.1f, 0.94f);

            fracao[0] = 0f;
            fracao[1] = 0.4f;
            fracao[2] = 0.5f;
            fracao[3] = 0.6f;
            fracao[4] = 0.65f;
            fracao[5] = 0.7f;
            fracao[6] = 0.75f;
            fracao[7] = 0.8f;
            fracao[8] = 0.85f;
            fracao[9] = 0.9f;
            fracao[10] = 0.95f;
            fracao[11] = 1f;

            //crie uma configuração de pintura de gradação para o círculo de luz
            RadialGradientPaint gradiente = new RadialGradientPaint(centroX, centroY, painel.jogador.luzAtual.raioDeLuz, fracao, cor);

            //defina os dados de gradiente em g2
            g2.setPaint(gradiente);
        }

        g2.fillRect(0, 0, painel.larguraTela, painel.alturaTela);

        g2.dispose();
    }


    public void reiniciarDia(){
        estadoDia = dia;
        filtroAlpha = 0f;
    }

    public void atualizar(){
        if(painel.jogador.equiparLanterna == true){
            setFonteDeLuz();
            painel.jogador.equiparLanterna = false;
        }

        //verificar o estados do dia
        if(estadoDia == dia){
            contadorDia++;

            //36.000 = 10 minutos para anoitecer
            if(contadorDia > 600){
                estadoDia = anoitecer;
                contadorDia= 0;
            }
        }
        if(estadoDia == anoitecer){
            filtroAlpha += 0.001f;

            if(filtroAlpha > 1f){
                filtroAlpha = 1f;
                estadoDia = noite;
            }
        }
        if(estadoDia == noite){
            contadorDia++;

            //36.000 = 10 minutos para amanhecer
            if(contadorDia > 600){
                estadoDia = amanhecer;
                contadorDia = 0;
            }
        }
        if(estadoDia == amanhecer){
            filtroAlpha -= 0.001f;

            if(filtroAlpha < 0){
                filtroAlpha = 0;
                estadoDia = dia;
            }
        }
    }
    public void desenhar(Graphics2D g2){

        if(painel.areaAtual == painel.fora){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filtroAlpha));
        }
        
        if(painel.areaAtual == painel.fora || painel.areaAtual == painel.masmorra){
            g2.drawImage(filtroDeEscuridao, 0,0, null);
        }


        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        //debgu
        String situacao = "";

        switch (estadoDia) {
            case dia: situacao = "Dia"; break;
            case anoitecer: situacao = "Anoitecer"; break;
            case noite: situacao = "Noite"; break;
            case amanhecer: situacao = "Amanhecer"; break;
 
        }
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(situacao, 700, 500);
    }
}
