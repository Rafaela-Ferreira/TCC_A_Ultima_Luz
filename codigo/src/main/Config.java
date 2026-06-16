package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {

    PainelDoJogo painel;

    public Config(PainelDoJogo painel){
        this.painel = painel;
    }

    public void salvarConfiguracoes(){

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //salvar a conf de tela
            if(painel.telaCheiaAtiva == true){
                bw.write("ligada");
            }
            if(painel.telaCheiaAtiva == false){
                bw.write("desligada");
            }
            bw.newLine();

            // salvar o volume da musica
            bw.write(String.valueOf(painel.musica.escalaDoVolume));
            bw.newLine();

            // salvar o volume do efeito
            bw.write(String.valueOf(painel.efeitoSonoro.escalaDoVolume));
            bw.newLine();

            bw.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    public void carregarConfiguracoes(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt")); 
            String s = br.readLine();

            //tela cheia
            if(s.equals("ligada")){
                painel.telaCheiaAtiva = true;
            }
            if(s.equals("desligada")){
                painel.telaCheiaAtiva = false;
            }
            // volume da musica
            s = br.readLine();
            painel.musica.escalaDoVolume = Integer.parseInt(s);

            // volume do efeito
            s = br.readLine();
            painel.efeitoSonoro.escalaDoVolume = Integer.parseInt(s);

            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}