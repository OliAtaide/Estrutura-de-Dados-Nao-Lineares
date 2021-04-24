public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addVertice(4);
        grafo.addAresta(1, 1, 1);
        grafo.addAresta(2, 1, 2);
        grafo.addAresta(3, 1, 4);
        grafo.addAresta(4, 4, 3);
        grafo.addAresta(5, 3, 1);
        grafo.addAresta(6, 3, 2);
        grafo.addAresta(7, 4, 3);

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
