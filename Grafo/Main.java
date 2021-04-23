public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.addVertice(1);
        grafo.addVertice(2);
        if(grafo.addAresta(3, 1, 2)){
            System.out.println("Aresta adicionada.");
        }
        else{
            System.out.println("Dado incorreto.");
        }
        
        System.out.println(grafo.grau(1));
    }
}
