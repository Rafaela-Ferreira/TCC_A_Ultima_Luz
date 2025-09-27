package dados;

import java.io.Serializable;
import java.util.ArrayList;

public class ArmazenamentoDeDados implements Serializable{
    //status do jogador
    int nivel;
    int vidaMaxima;
    int vida;
    int manaMaxima;
    int mana;
    int forca;
    int destreza;
    int exp;
    int proximoNivelExp;
    int moeda;


    //inventario do jogador
    ArrayList<String> nomeDoItem = new ArrayList<>();
    ArrayList<Integer> quantidadeDoItem = new ArrayList<>();
    int espacoArmaAtual;
    int espacoEscudoAtual;

    //salvar os objetos do mapa
    String nomeDosObjDoMapa[][];
    int objDoMapaMundoX[][];
    int objDoMapaMundoY[][];
    String nomeDosObjDeSaque[][];
    boolean objDoMapaBauAberto[][]; //mapObjectOpened
}
