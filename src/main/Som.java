package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    Clip clip; //abre um arquivo de musica
    URL somURL[] = new URL[30];

    public Som(){
        somURL[0] = getClass().getResource("/Sons/BlueBoyAdventure.wav");
        somURL[1] = getClass().getResource("/Sons/coin.wav");
        somURL[2] = getClass().getResource("/Sons/powerup.wav");
        somURL[3] = getClass().getResource("/Sons/unlock.wav");
        somURL[4] = getClass().getResource("/Sons/fanfare.wav");
        somURL[5] = getClass().getResource("/Sons/hitmonster.wav");
        somURL[6] = getClass().getResource("/Sons/receivedamage.wav");
        somURL[7] = getClass().getResource("/Sons/blocked.wav");
        somURL[8] = getClass().getResource("/Sons/levelup.wav");
        somURL[9] = getClass().getResource("/Sons/cursor.wav");
        
    }

    public void setArquivo(int indice){
        try {
            AudioInputStream entradaSaídaAudio = AudioSystem.getAudioInputStream(somURL[indice]);
            clip = AudioSystem.getClip();
            clip.open(entradaSaídaAudio);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    public void iniciar(){
        clip.start();
    }

    public void repetir(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void parar(){
        clip.stop();
    }
}
