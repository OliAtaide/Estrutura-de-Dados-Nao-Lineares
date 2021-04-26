import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Declarando o grafo
        Grafo grafo = new Grafo();

        // Adicionando vértices
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addVertice(4);
        grafo.addVertice(5);

        // Adicionando arestas
        grafo.addAresta(1, 1, 1);
        grafo.addAresta(2, 1, 2);
        grafo.addAresta(3, 1, 4);
        grafo.addAresta(4, 4, 3);
        grafo.addAresta(5, 3, 1);
        grafo.addAresta(6, 3, 2);
        grafo.addAresta(7, 4, 3);
        
        // Imprimindo Arestas e vértices finais
        System.out.println("Arestas adicionadas:");
        for(int a = 0; a < grafo.getArestas().size(); a++){
            Aresta aresta = grafo.getArestas().get(a);
            ArrayList<Vertice> vertices = grafo.finalVertices(aresta.getValor());
            System.out.print("Valor: " + aresta.getValor() + ", ");
            System.out.print("Vértices: ");
            for(int v = 0; v < vertices.size(); v++){
                System.out.print(vertices.get(v).getValor() + ", ");
            }
            System.out.println("");
        }

        // Imprimindo Vértices e graus
        System.out.println("Vértices adicionados: ");
        for(int v = 0; v < grafo.getVertices().size(); v++){
            int valor = grafo.getVertices().get(v).getValor();
            int grau = grafo.grau(valor);
            System.out.println(valor + ", " + "Grau: " + grau + ";");
            
        }
        System.out.println("");

        System.out.println(grafo.grau(1));

        int[][] matriz = grafo.matrizAdjacencia();

        for(int i = 0; i < matriz.length; i++){
            System.out.print("|");
            for(int j = 0; j < matriz[i].length; j++){
                System.out.print(" " + matriz[i][j] + " |");
            }
            System.out.println("");
        }
    }
}
