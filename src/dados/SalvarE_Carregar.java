package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.PainelDoJogo;


public class SalvarE_Carregar {
    PainelDoJogo painel;

    public SalvarE_Carregar(PainelDoJogo painel){
        this.painel = painel;
    }


    public void salvar(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("salvar.dat")));
        
            ArmazenamentoDeDados dados = new ArmazenamentoDeDados();

            dados.nivel = painel.jogador.nivel;
            dados.vidaMaxima = painel.jogador.vidaMaxima;
            dados.vida = painel.jogador.vida;
            dados.manaMaxima = painel.jogador.manaMaxima;
            dados.mana = painel.jogador.mana;
            dados.forca = painel.jogador.forca;
            dados.destreza = painel.jogador.destreza;
            dados.exp = painel.jogador.exp;
            dados.proximoNivelExp = painel.jogador.proximoNivelExp;
            dados.moeda = painel.jogador.moeda;

            //inventario do jogador
            for(int i =0; i < painel.jogador.inventario.size(); i++){
                dados.nomeDoItem.add(painel.jogador.inventario.get(i).nome);
                dados.quantidadeDoItem.add(painel.jogador.inventario.get(i).quantidade);
            }            

            //salvar os equipamentos atuais
            dados.espacoArmaAtual = painel.jogador.getEspacoArmaAtual();
            dados.espacoEscudoAtual = painel.jogador.getEspacoEscudoAtual();

            //salvar os objetos do mapa
            dados.nomeDosObjDoMapa = new String[painel.maxMapa][painel.Obj[1].length];
            dados.objDoMapaMundoX = new int[painel.maxMapa][painel.Obj[1].length];
            dados.objDoMapaMundoY = new int[painel.maxMapa][painel.Obj[1].length];
            dados.nomeDosObjDeSaque = new String[painel.maxMapa][painel.Obj[1].length];
            dados.objDoMapaBauAberto = new boolean[painel.maxMapa][painel.Obj[1].length];

            for(int numMapa = 0; numMapa < painel.maxMapa; numMapa++){

                for(int i = 0; i < painel.Obj[1].length; i++){
                    
                    if(painel.Obj[numMapa][i] == null){
                        dados.nomeDosObjDoMapa[numMapa][i] = "NA";

                    }else{
                        dados.nomeDosObjDoMapa[numMapa][i] = painel.Obj[numMapa][i].nome;
                        dados.objDoMapaMundoX[numMapa][i] = painel.Obj[numMapa][i].mundoX;
                        dados.objDoMapaMundoY[numMapa][i] = painel.Obj[numMapa][i].mundoY;
                        
                        if(painel.Obj[numMapa][i].saque != null){
                            dados.nomeDosObjDeSaque[numMapa][i] = painel.Obj[numMapa][i].saque.nome;
                        }
                        dados.objDoMapaBauAberto[numMapa][i] = painel.Obj[numMapa][i].aberto;
                    }
                }
                
            }

            //objeto em braco de armazenamento de dados
            oos.writeObject(dados);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception: Erro ao salvar");
        }
        
    }

    public void carregar(){

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("salvar.dat")));
            
            //ler o objeto de armazenamento de dados
            ArmazenamentoDeDados dados = (ArmazenamentoDeDados)ois.readObject();

            painel.jogador.nivel = dados.nivel;
            painel.jogador.vidaMaxima = dados.vidaMaxima;
            painel.jogador.vida = dados.vida;
            painel.jogador.manaMaxima = dados.manaMaxima;
            painel.jogador.mana = dados.mana;
            painel.jogador.forca = dados.forca;
            painel.jogador.destreza = dados.destreza;
            painel.jogador.exp = dados.exp;
            painel.jogador.proximoNivelExp = dados.proximoNivelExp;
            painel.jogador.moeda = dados.moeda;

            //inventario do jogador
            painel.jogador.inventario.clear();
            for(int i =0; i < dados.nomeDoItem.size(); i++){
                painel.jogador.inventario.add(painel.geradorDeEntidade.getObjeto(dados.nomeDoItem.get(i)));
                painel.jogador.inventario.get(i).quantidade = dados.quantidadeDoItem.get(i);
            }

            //salvar os equipamentos atuais
            painel.jogador.armaAtual = painel.jogador.inventario.get(dados.espacoArmaAtual);
            painel.jogador.escudoAtual = painel.jogador.inventario.get(dados.espacoEscudoAtual);
            painel.jogador.getDefesa();
            painel.jogador.getImagemDeAtaque();

            //salvar os Objetos do mapa

            for(int numMapa = 0; numMapa < painel.maxMapa; numMapa++){
                
                for(int i = 0; i < painel.Obj[1].length; i++){

                    if(dados.nomeDosObjDoMapa[numMapa][i].equals("NA")){
                        painel.Obj[numMapa][i] = null;
                    }
                    else{
                        painel.Obj[numMapa][i] = painel.geradorDeEntidade.getObjeto(dados.nomeDosObjDoMapa[numMapa][i]);
                        painel.Obj[numMapa][i].mundoX = dados.objDoMapaMundoX[numMapa][i];
                        painel.Obj[numMapa][i].mundoY = dados.objDoMapaMundoY[numMapa][i];
                        
                        if(dados.nomeDosObjDeSaque[numMapa][i] != null) {
                            painel.Obj[numMapa][i].setSaque(painel.geradorDeEntidade.getObjeto(dados.nomeDosObjDeSaque[numMapa][i])); 
                        }

                        painel.Obj[numMapa][i].aberto = dados.objDoMapaBauAberto[numMapa][i];
                        
                        if(painel.Obj[numMapa][i].aberto == true){
                           painel.Obj[numMapa][i].baixo1 = painel.Obj[numMapa][i].imagem2;
                        }

                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Exception: Erro ao carregar");
        }
    }

}
