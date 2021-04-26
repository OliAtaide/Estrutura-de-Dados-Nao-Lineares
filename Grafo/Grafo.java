import java.util.ArrayList;

public class Grafo {
  private ArrayList<Aresta> arestas;
  private ArrayList<Vertice> vertices;

  public Grafo() {
    arestas = new ArrayList<>();
    vertices = new ArrayList<>();
  }

  public ArrayList<Aresta> getArestas() {
    return arestas;
  }

  public ArrayList<Vertice> getVertices() {
    return vertices;
  }

  public void addVertice(int valor) {
    vertices.add(new Vertice(valor));
  }

  public boolean addAresta(int valor, int valorInicio, int valorFim) {
    Vertice inicio = getVertice(valorInicio), fim = getVertice(valorFim);
    if (inicio != null && fim != null) {
      Aresta aresta = new Aresta(valor, inicio, fim);
      inicio.addSaida(aresta);
      fim.addEntrada(aresta);
      arestas.add(aresta);
      return true;
    }
    return false;
  }

  public boolean rmAresta(int valor) {
    Aresta aresta = getAresta(valor);
    if (aresta != null) {
      Vertice inicio = aresta.getInicio(), fim = aresta.getFim();
      inicio.getSaida().remove(aresta);
      fim.getEntrada().remove(aresta);
      arestas.remove(aresta);
      return true;
    }
    return false;
  }

  public boolean rmVertice(int valor) {
    Vertice vertice = getVertice(valor);
    if (vertice != null) {
      for(int e = 0; e < vertice.getEntrada().size(); e++){
        Aresta entrada = vertice.getEntrada().get(e);
        if(entrada != null){
          rmAresta(entrada.getValor());
        }
      }
      for(int s = 0; s < vertice.getSaida().size(); s++){
        Aresta saida = vertice.getSaida().get(s);
        if(saida != null){
          rmAresta(saida.getValor());
        }
      }
      vertices.remove(vertice);
      return true;
    }
    return false;
  }

  public Vertice getVertice(int valor) {
    for (int i = 0; i < vertices.size(); i++) {
      if (vertices.get(i).getValor() == valor) {
        return vertices.get(i);
      }
    }
    return null;
  }

  public Aresta getAresta(int valor) {
    for (int i = 0; i < arestas.size(); i++) {
      if (arestas.get(i).getValor() == valor) {
        return arestas.get(i);
      }
    }
    return null;
  }

  public boolean substituirVertice(int valorVertice, int valor) {
    Vertice vertice = getVertice(valor);
    if (vertice != null) {
      vertice.setValor(valor);
      return true;
    }
    return false;
  }

  public boolean substituirAresta(int valorAresta, int valor) {
    Aresta aresta = getAresta(valor);
    if (aresta != null) {
      aresta.setValor(valor);
      return true;
    }
    return false;
  }
  
  public int grau(int valor) {
    int grau = 0;
    Vertice vertice = getVertice(valor);
    if (vertices.contains(vertice)) {
      for (int i = 0; i < arestas.size(); i++) {
        if (arestas.get(i).getInicio() == vertice || arestas.get(i).getFim() == vertice) {
          grau++;
        }
      }
      return grau;
    } else {
      return -1;
    }
  }

  public int[][] matrizAdjacencia() {
    int[][] matriz = new int[vertices.size()][vertices.size()];

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        for (int a = 0; a < arestas.size(); a++) {
          Aresta aresta = arestas.get(a);
          boolean caso = aresta.getInicio() == vertices.get(i)
                          && aresta.getFim() == vertices.get(j);
          if (caso) {
            matriz[i][j]++;
          }
        }
      }
    }
    return matriz;
  }

  public boolean eAdjacente(int v, int w){
    Vertice vertice = getVertice(v), vizinho = getVertice(w);
    if(vertice != null && vizinho != null){
      for(int i = 0; i < arestas.size(); i++){
        Vertice inicio = arestas.get(i).getInicio();
        Vertice fim = arestas.get(i).getFim();
        
        boolean caso1 = vertice == inicio && vizinho == fim;
        boolean caso2 = vizinho == inicio && vertice == fim;

        if(caso1 || caso2){
          return true;
        }
      }
    }
    return false;
  }

  public ArrayList<Vertice> finalVertices(int valor){
    Aresta aresta = getAresta(valor);
    if (aresta != null){
      ArrayList<Vertice> vertices = new ArrayList<>();
      if(aresta.getInicio() != null){
        vertices.add(aresta.getInicio());
      }
      if(aresta.getFim() != null){
        vertices.add(aresta.getFim());
      }
      return vertices;
    }
    return null;
  }

  public Vertice oposto(int v, int a){
    Vertice vertice = getVertice(v);
    Aresta aresta = getAresta(a);
    if(vertice != null && aresta != null){
      if(aresta.getInicio() == vertice || aresta.getFim() == vertice){
        if(aresta.getInicio() != vertice){
          return aresta.getInicio();
        }
        else if(aresta.getFim() != vertice){
          return aresta.getFim();
        }
      }
    }
    return null;
  }
}
