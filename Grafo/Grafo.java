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

  public boolean rmVertice(int valor) {
    Vertice vertice = getVertice(valor);
    if (vertice != null) {
      // codigo
      return true;
    }
    return false;
  }

  public boolean rmAresta(int valor) {
    Aresta aresta = getAresta(valor);
    if (aresta != null) {
      // codigo
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

  public ArrayList<Aresta> arestasIncidentes(int valor) {
    Vertice vertice = getVertice(valor);
    if (vertice != null) {
      ArrayList<Aresta> incidentes = new ArrayList<>();
      incidentes.addAll(vertice.getEntrada());
      incidentes.addAll(vertice.getSaida());
      return incidentes;
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
}
