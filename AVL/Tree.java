import java.util.ArrayList;

public class Tree {
	Node root;
	int count;
	public ArrayList<Node> nodes = new ArrayList<>();

	public Node getRoot() {
		return root;
	}

	public boolean insert(int key) {
		if (key == 0){
			System.out.println(key);
			return false;
		}
		if(root == null || key != root.getKey()){
			root = insert(root, key);
			count++;
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
			left.setParent(node);
			node.setLeft(left);
		}
		else{
			Node right = insert(node.getRight(), key);
			right.setParent(node);
			node.setRight(right);
		}
		update(node);
		return balance(node);
	}
	
	public void update(Node node){
		int leftHeight = -1, rightHeight = -1;
		Node left = node.getLeft(), right = node.getRight();
		if(left != null){
			leftHeight = left.getHeight();
		}
		if(right != null){
			rightHeight = right.getHeight();
		}
		node.setHeight(1 + Math.max(leftHeight, rightHeight));
		node.setBF(rightHeight - leftHeight);
	}

	public Node balance(Node node){
		if (node.getBF() == -2){
			if(node.getLeft().getBF() <= 0){
				return rightRotate(node);
			}
			else{
				return doubleRightRotate(node);
			}
		}
		else if (node.getBF() == +2){
			if(node.getRight().getBF() >= 0){
				return leftRotate(node);
			}
			else{
				return doubleLeftRotate(node);
			}
		}
		return node;
	}

	public Node rightRotate(Node a){
		Node b = a.getLeft();
		a.setLeft(b.getRight());
		b.setRight(a);
		update(a);
		update(b);
		return b;
	}

	public Node leftRotate(Node a){
		Node b = a.getRight();
		a.setRight(b.getLeft());
		b.setLeft(a);
		update(a);
		update(b);
		return b;
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
		return Math.max(lh, rh);
	}

	public int depth(Node node) {
		if (node == root) {
			return 0;
		} else {
			return depth(node.getParent());
		}
	}
}
