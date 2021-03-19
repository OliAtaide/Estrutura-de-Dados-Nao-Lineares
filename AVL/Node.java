public class Node {
	private int bf;
	private int key;
	private Node parent, left, right;

	public Node(){

	}

	public Node(int key) {
		this.key = key;
		this.bf = 0;
	}

	public int getBF(){
		return bf;
	}

	public void setBF(int bf){
		this.bf = bf;
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
