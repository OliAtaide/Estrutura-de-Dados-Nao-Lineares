import java.util.ArrayList;

public class Tree {
	Node root = null;
	int count = 0;
	public ArrayList<Node> nodes = new ArrayList<>();

	public Node getRoot() {
		return root;
	}

	public int getRootHeight(){
		if(root == null){
			return 0;
		}
		return root.getHeight();
	}

	public boolean insert(int key) {
		if (key == 0){
			return false;
		}
		Node r = insert(root, key);
		if(r != null){
			count++;
			root = r;
			return true;
		}
		return false;
	}  

	public Node insert(Node node, int key){
		if (node == null){
			return new Node(key);
		}
		if(key < node.getKey()){
			Node left = insert(node.getLeft(), key);
			if(left == null) {
				return null;
			}
			node.setLeft(left);
			left.setParent(node);
		}
		else if(key > node.getKey()){
			Node right = insert(node.getRight(), key);
			if(right == null){
				return null;
			}
			node.setRight(right);
			right.setParent(node);
		}else{
			return null;
		}
		update(node);
		return balance(node);
	}
	
	public void update(Node node){
		int lh = 0, rh = 0;
		if (node.getLeft() != null) {
			lh = node.getLeft().getHeight();
		}
		if (node.getRight() != null) {
			rh = node.getRight().getHeight();
		}
		node.setHeight(1 + Math.max(lh, rh));
		node.setBF(lh - rh);
		System.out.println(node.getKey() + " - " + lh + ", " + rh + " - " + node.getBF());
	}

	public Node balance(Node node){
		if (node.getBF() == -2){
			if(node.getRight().getBF() <= 0){
				return leftRotate(node);
			}
			else{
				return doubleLeftRotate(node);
			}
		}
		else if (node.getBF() == 2){
			if(node.getLeft().getBF() >= 0){
				return rightRotate(node);
			}
			else{
				return doubleRightRotate(node);
			}
		}
		return node;
	}

	public Node leftRotate(Node a){
		Node b = a.getRight();
		a.setRight(b.getLeft());
		b.getLeft().setParent(a);
		b.setLeft(a);
		a.setParent(b);
		updateAfterRotation(a, b);
		return b;
	}

	public Node rightRotate(Node a){
		Node b = a.getLeft();
		a.setLeft(b.getRight());
		b.getRight().setParent(a);
		b.setRight(a);
		a.setParent(b);
		updateAfterRotation(a, b);
		return b;
	}

	public void updateAfterRotation(Node a, Node b){
		int b_bf = b.getBF();
		int a_bf = a.getBF();

		b.setBF(b_bf + 1 - Math.min(a_bf, 0));
		a.setBF(a_bf + 1 + Math.max(b.getBF(), 0));
	}

	public Node doubleRightRotate(Node node){
		Node left = node.getLeft();
		left = leftRotate(left);
		return rightRotate(node);
	}

	public Node doubleLeftRotate(Node node){
		Node right = node.getRight();
		right = rightRotate(right);
		return leftRotate(node);
	}

	public void inOrder(Node node) {
		if (node != null) {
			inOrder(node.getLeft());
			nodes.add(node);
			inOrder(node.getRight());
		}
	}

	public int height(Node node) {
		int lh = 0, rh = 0;
		if (node.getLeft() != null) {
			lh = height(node.getLeft());
		}
		if (node.getRight() != null) {
			rh = height(node.getRight());
		}
		node.setHeight(1 + Math.max(lh, rh));
		return node.getHeight();
	}

	public int depth(Node node) {
		if (node == root) {
			return 0;
		} else {
			return depth(node.getParent());
		}
	}
}
