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

  public Vertice getVertice(int valor) {
    for (int i = 0; i < vertices.size(); i++) {
      if (vertices.get(i).getValor() == valor) {
        return vertices.get(i);
      }
    }
    return null;
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
}
