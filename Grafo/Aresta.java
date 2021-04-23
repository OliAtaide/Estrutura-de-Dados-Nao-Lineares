public class Aresta {
    private int valor;
    private Vertice inicio, fim;

    public Aresta(int valor, Vertice inicio, Vertice fim){
        this.valor = valor;
        this.inicio = inicio;
        this.fim = fim;
    }

    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public Vertice getInicio(){
        return inicio;
    }

    public Vertice getFim(){
        return fim;
    }

    public void setInicio(Vertice inicio){
        this.inicio = inicio;
    }

    public void setFim(Vertice fim){
        this.fim = fim;
    }
}
