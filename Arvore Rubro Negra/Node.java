public class Node {
	private int key, color;
	private Node parent, left, right;

	public Node(int key) {
		this.key = key;
	}

	public Node(int key, int color){
		this.key = key;
		this.color = color;
	}

	public Node(int key, Node left, Node right, Node parent, int color){
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.key = key;
		this.color = color;
	}

	public int getColor(){
		return color;
	}

	public int getKey() {
		return key;
	}

	public Node getParent() {
		return parent;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public void setColor(int color){
		this.color = color;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
