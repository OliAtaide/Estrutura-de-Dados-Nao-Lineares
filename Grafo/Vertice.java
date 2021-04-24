import java.util.ArrayList;

public class Vertice {
    private int valor;
    private ArrayList<Aresta> entrada, saida;

    public Vertice(int valor){
        this.valor = valor;
        entrada = new ArrayList<>();
        saida = new ArrayList<>();
    }

    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public void addEntrada(Aresta aresta){
        entrada.add(aresta);
    }

    public void addSaida(Aresta aresta){
        saida.add(aresta);
    }

    public ArrayList<Aresta> getEntrada(){
        return entrada;
    }

    public ArrayList<Aresta> getSaida(){
        return saida;
    }
}
