package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//usada para adicionar funções convenientes que podem ser usadas em todo o jogo
public class CaixaDeFerramentas {

    public BufferedImage escalaImage(BufferedImage original, int largura, int altura) {
        BufferedImage escalaImage = new BufferedImage(largura, altura, original.getType() );
        Graphics2D g2 = escalaImage.createGraphics();
        g2.drawImage(original, 0, 0, largura, altura, null);
        g2.dispose();

        return escalaImage;
    }
}
