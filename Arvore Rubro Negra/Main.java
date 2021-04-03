public class Main {
	public static void printTree(Tree t) {
		int h = t.height(t.getRoot());
		t.inOrder(t.getRoot());
		int w = t.nodes.size();
		int[][] vector = new int[h][w];
		for (int i = 0; i < w; i++) {
			Node n = t.nodes.get(i);
			vector[t.depth(n)][i] = n.getKey();
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
