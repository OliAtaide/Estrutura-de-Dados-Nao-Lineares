public class Main {
	public static void printTree(Tree t) {
		int h = t.getRootHeight()+1;
		t.inOrder(t.getRoot());
		int w = t.nodes.size();
		int[][] vector = new int[h][w];
		for(int i = 0; i < t.nodes.size(); i++){
		/*	Node node = t.nodes.get(i);
			System.out.println("====================");
			System.out.println("Key: " + node.getKey());
			System.out.println("BF: " + node.getBF());
			System.out.println("height: " + node.getHeight());
			System.out.println("====================");
			*/
			Node node = t.nodes.get(i);
			System.out.print(node.getKey() + " ");
		}
		System.out.println("");
		for (int i = 0; i < w; i++) {
			Node n = t.nodes.get(i);
			int d = h - n.getHeight() - 1;
			System.out.print(n.getKey() + " - Altura: " + d + " - ");
			if(n.getLeft() != null){
				System.out.print("FIlhos: L - " + n.getLeft().getKey() + ", ");
			}
			if(n.getRight() != null){
				System.out.print("FIlhos: R - " + n.getRight().getKey() + ", ");
			}
			System.out.println("");
			vector[d][i] = n.getKey();
		}
		for (int l = 0; l < h; l++) {
			for (int c = 0; c < w; c++) {
				System.out.print((vector[l][c] != 0 ? vector[l][c] : "") + "\t");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		Tree t = new Tree();

		t.insert(13);
		t.insert(6);
		t.insert(24);
		t.insert(5);
		t.insert(41);
		t.insert(7);
		t.insert(12);
		t.insert(27);
		t.insert(57);
		t.insert(44);

		printTree(t);

	}

}
