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
        somURL[0] = getClass().getResource("/res/Som/ApocalypticEchoes.wav");
        somURL[1] = getClass().getResource("/res/Som/coin.wav");
        somURL[2] = getClass().getResource("/res/Som/powerup.wav");
        somURL[3] = getClass().getResource("/res/Som/unlock.wav");
        somURL[4] = getClass().getResource("/res/Som/fanfare.wav");
        somURL[5] = getClass().getResource("/res/Som/hitmonster.wav");
        somURL[6] = getClass().getResource("/res/Som/receivedamage.wav");
        somURL[7] = getClass().getResource("/res/Som/blocked.wav");
        somURL[8] = getClass().getResource("/res/Som/levelup.wav");
        somURL[9] = getClass().getResource("/res/Som/cursor.wav");
        somURL[10] = getClass().getResource("/res/Som/burning.wav");
        somURL[11] = getClass().getResource("/res/Som/cuttree.wav");
        somURL[12] = getClass().getResource("/res/Som/gameover.wav");
        somURL[13] = getClass().getResource("/res/Som/stairs.wav");
        somURL[14] = getClass().getResource("/res/Som/sleep.wav");
        somURL[15] = getClass().getResource("/res/Som/blocked.wav");
        somURL[16] = getClass().getResource("/res/Som/parry.wav");
        somURL[17] = getClass().getResource("/res/Som/speak.wav");
        somURL[18] = getClass().getResource("/res/Som/Merchant.wav");
        somURL[19] = getClass().getResource("/res/Som/Dungeon.wav");
        somURL[19] = getClass().getResource("/res/Som/Dungeon.wav");
        somURL[20] = getClass().getResource("/res/Som/chipwall.wav");
        somURL[20] = getClass().getResource("/res/Som/chipwall.wav");
        somURL[21] = getClass().getResource("/res/Som/dooropen.wav");
        somURL[22] = getClass().getResource("/res/Som/FinalBattle.wav");
        
        somURL[23] = getClass().getResource("/res/Som/correr.wav");
        somURL[24] = getClass().getResource("/res/Som/chuva.wav");
        somURL[25] = getClass().getResource("/res/Som/trovao.wav");
        somURL[25] = getClass().getResource("/res/Som/vento.wav");

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
