package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Som {
    Clip clip; //abre um arquivo de musica
    URL somURL[] = new URL[30];
    FloatControl fc;

    int escalaDoVolume = 3;
    float volume;

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
        somURL[10] = getClass().getResource("/Sons/burning.wav");
        somURL[11] = getClass().getResource("/Sons/cuttree.wav");
        somURL[12] = getClass().getResource("/Sons/gameover.wav");
        somURL[13] = getClass().getResource("/Sons/stairs.wav");
        somURL[14] = getClass().getResource("/Sons/sleep.wav");
        somURL[15] = getClass().getResource("/Sons/blocked.wav");
        somURL[16] = getClass().getResource("/Sons/parry.wav");
        somURL[17] = getClass().getResource("/Sons/speak.wav");
        somURL[18] = getClass().getResource("/Sons/Merchant.wav");
        somURL[19] = getClass().getResource("/Sons/Dungeon.wav");
        somURL[20] = getClass().getResource("/Sons/chipwall.wav");
        somURL[21] = getClass().getResource("/Sons/dooropen.wav");
        somURL[22] = getClass().getResource("/Sons/FinalBattle.wav");
    }

    public void setArquivo(int indice){
        try {
            AudioInputStream entradaSaídaAudio = AudioSystem.getAudioInputStream(somURL[indice]);
            clip = AudioSystem.getClip();
            clip.open(entradaSaídaAudio);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            VerificarVolume();

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

    public void VerificarVolume(){
        switch (escalaDoVolume) {
            case 0: volume = -80f; break;
            case 1:  volume = -20f; break;
            case 2:  volume = -12f; break;
            case 3:  volume = -5f; break;
            case 4:  volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
