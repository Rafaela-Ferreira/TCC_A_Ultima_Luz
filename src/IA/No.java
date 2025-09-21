package IA;

public class No {
    No pai; //parent
    public int coluna;
    public int linha;
    int gCusto;
    int hCusto;
    int fCusto;
    boolean solida;
    boolean abrir;
    boolean verificar;

    public No(int coluna, int linha){
        this.coluna = coluna;
        this.linha = linha;
        
    }
    
}
