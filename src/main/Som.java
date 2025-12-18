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
        somURL[0] = getClass().getResource("/Som/ApocalypticEchoes.wav");
        somURL[1] = getClass().getResource("/Som/coin.wav");
        somURL[2] = getClass().getResource("/Som/powerup.wav");
        somURL[3] = getClass().getResource("/Som/unlock.wav");
        somURL[4] = getClass().getResource("/Som/fanfare.wav");
        somURL[5] = getClass().getResource("/Som/hitmonster.wav");
        somURL[6] = getClass().getResource("/Som/receivedamage.wav");
        somURL[7] = getClass().getResource("/Som/blocked.wav");
        somURL[8] = getClass().getResource("/Som/levelup.wav");
        somURL[9] = getClass().getResource("/Som/cursor.wav");
        somURL[10] = getClass().getResource("/Som/burning.wav");
        somURL[11] = getClass().getResource("/Som/cuttree.wav");
        somURL[12] = getClass().getResource("/Som/gameover.wav");
        somURL[13] = getClass().getResource("/Som/stairs.wav");
        somURL[14] = getClass().getResource("/Som/sleep.wav");
        somURL[15] = getClass().getResource("/Som/blocked.wav");
        somURL[16] = getClass().getResource("/Som/parry.wav");
        somURL[17] = getClass().getResource("/Som/speak.wav");
        somURL[18] = getClass().getResource("/Som/Merchant.wav");
        somURL[19] = getClass().getResource("/Som/Dungeon.wav");
        somURL[20] = getClass().getResource("/Som/chipwall.wav");
        somURL[21] = getClass().getResource("/Som/dooropen.wav");
        somURL[22] = getClass().getResource("/Som/FinalBattle.wav");
        
        somURL[23] = getClass().getResource("/Som/correr.wav");
        somURL[24] = getClass().getResource("/Som/chuva.wav");
        somURL[25] = getClass().getResource("/Som/trovao.wav");
        somURL[25] = getClass().getResource("/Som/vento.wav");

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
