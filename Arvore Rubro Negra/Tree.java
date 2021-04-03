import java.util.ArrayList;

public class Tree {
	public Node nil, root;
	int count = 0;

	public ArrayList<Node> nodes = new ArrayList<>();

	public Tree(){
		nil = new Node(0, 1);
		nil.setParent(nil);
		nil.setLeft(nil);
		nil.setRight(nil);

		root = nil;
	}
	
	public Node getRoot() {
		return root;
	}

	public boolean insert(int key){
		if (key == 0){
			return false;
		}

		Node x = root, y = nil;
		
		while(x != nil){
			y = x;

			if(x.getKey() > key){
				x = x.getLeft();
			}
			else if (x.getKey() < key){
				x = x.getRight();
			}
			else{
				return false;
			}
		}

		Node z = new Node(key, nil, nil, y, 0);

		if (y == nil) {
			root = z;
		}
		else if(z.getKey() < y.getKey()){
			y.setLeft(z);
		}
		else{
			y.setRight(z);
		}
		insert(z);
		count++;
		return true;
	}

	private void insert(Node n){
		Node y;
		Node parent = n.getParent();
		Node parentParent = parent.getParent();
		while(parent.getColor() == 0){
			if(parent == parentParent.getLeft()){
				y = parentParent.getRight();
				if(y.getColor() == 0){
					parent.setColor(1);
					y.setColor(1);
					parentParent.setColor(0);
					n = parentParent;
				}
				else{
					if(n == parent.getRight()){
						n = parent;
						leftRotate(n);
					}
					parent.setColor(1);
					parentParent.setColor(0);
					rightRotate(parentParent);
				}
			}
			else{
				y = parentParent.getLeft();
				if(y.getColor() == 0){
					parent.setColor(1);
					y.setColor(1);
					parentParent.setColor(0);
					n = parentParent;
				}
				else{
					if(n == parent.getLeft()){
						n = parent;
						rightRotate(n);
					}
					parent.setColor(1);
					parentParent.setColor(0);
					leftRotate(parentParent);
				}
			}
		}
		root.setColor(1);
		nil.setParent(null);
	}

	private void leftRotate(Node n){
		Node r = n.getRight();
		n.setRight(r.getLeft());
		if(r.getLeft() != nil){
			r.getLeft().setParent(n);
		}
		r.setParent(n.getParent());
		if(n.getParent() == nil){
			root = r;
		}
		if(n == n.getParent().getLeft()){
			n.getParent().setLeft(r);
		}
		else{
			n.getParent().setRight(r);
		}
		r.setLeft(n);
		n.setParent(r);
	}

	private void rightRotate(Node n){
		Node l = n.getLeft();
		n.setLeft(l.getRight());
		if(l.getRight() != nil){
			n.getRight().setParent(l);
		}
		l.setParent(n.getParent());
		if(l.getParent() == nil){
			root = l;
		}
		if(n == n.getParent().getLeft()){
			n.getParent().setLeft(l);
		}
		else{
			n.getParent().setRight(l);
		}
		l.setRight(n);
		n.setParent(l);
	}

	private Node search(int key, Node n){
		if (n == nil){
			return nil;
		}
		else if(n.getKey() == key){
			return n;
		}
		else if(n.getKey() < key){
			return search(key, n.getRight());
		}
		else{
			return search(key, n.getLeft());
		}
	}

	private void transplant(Node a, Node b){
		if(a.getParent() == nil){
			root = b;
		}
		else if (a == a.getParent().getLeft()){
			a.getParent().setLeft(b);
		}
		else{
			a.getParent().setRight(b);
		}
		b.setParent(a.getParent());
	}

	private Node successor(Node root){
		if(root == nil || root.getLeft() == nil){
			return root;
		}
		return successor(root.getLeft());
	}

	public boolean remove(int key){
		Node z = search(key, root);
		if (key == 0 || z == nil){
			return false;
		}
		Node x, y = z;
		int yColor = y.getColor();

		if(z.getLeft() == nil){
			x = z.getRight();
			transplant(z, z.getRight());
		}
		else if(z.getRight() == nil){
			x = z.getLeft();
			transplant(z, z.getLeft());
		}
		else{
			y = successor(z.getRight());
			yColor = y.getColor();
			x = y.getRight();
			if(y.getParent() == z){
				x.setParent(x);
			}
			else{
				transplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			transplant(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
		}
		if(yColor == 1){
			remove(x);
		}
		count--;
		return true;
	}

	private void remove(Node a){
		while(a != root && a.getColor() == 1){
			Node a_parent = a.getParent();
			if(a == a_parent.getLeft()) {
				Node b = a_parent.getRight();
				if(b.getColor() == 0){
					b.setColor(1);
					a_parent.setColor(0);
					leftRotate(a_parent);
					b = a_parent.getRight();
				}
				Node b_left = b.getLeft();
				Node b_right = b.getRight();
				if(b_left.getColor() == 1
				&& b_right.getColor() == 1){
					b.setColor(0);
					a = a_parent;
					continue;
				}
				else if(b_right.getColor() == 1){
					b_left.setColor(1);
					b.setColor(0);
					rightRotate(b);
					b = a_parent.getRight();
				}
				if(b_right.getColor() == 0){
					b.setColor(a_parent.getColor());
					a_parent.setColor(1);
					b_right.setColor(1);
					leftRotate(a_parent);
					a = root;
				}
			}
			else{
				Node b = (a_parent.getLeft());
				if(b.getColor() == 0){
					b.setColor(1);
					a_parent.setColor(0);
					rightRotate(a_parent);
					b = a_parent.getLeft();
				}
				Node b_left = b.getLeft();
				Node b_right = b.getRight();
				if(b_right.getColor() == 1
				&& b_left.getColor() == 1){
					b.setColor(0);
					b = b.getParent();
					continue;
				}
				else if(b_left.getColor() == 1){
					b_right.setColor(1);
					b.setColor(0);
					leftRotate(b);
					b = a_parent.getLeft();
				}
				if(b_left.getColor() == 0){
					b.setColor(a_parent.getColor());
					a_parent.setColor(1);
					b_left.setColor(1);
					rightRotate(a_parent);
					a = root;
				}
			}
		}
		a.setColor(1);
	}

	public Node replace(Node rNode) {
		Node rParent = rNode;
		Node r = rNode;

		Node node = rNode.getRight();

		while (node != null) {
			rParent = r;
			r = node;
			node = node.getLeft();
		}
		if (r != rNode.getRight()) {
			rParent.setLeft(r.getRight());
			r.setRight(rNode.getRight());
		}
		return r;
	}

	public Node search(int key) {
		Node node = root;
		while (node.getKey() != key) {
			if (key < node.getKey()) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
			if (node == null) {
				return null;
			}
		}
		return node;
	}

	public void inOrder(Node node) {
		if (node != nil) {
			inOrder(node.getLeft());
			nodes.add(node);
			inOrder(node.getRight());
		}
	}

	public int height(Node node) {
		int lh = 0, rh = 0;
		if (node.getLeft() != nil) {
			lh = height(node.getLeft());
		}
		if (node.getRight() != nil) {
			rh = height(node.getRight());
		}
		return 1 + Math.max(lh, rh);
	}

	public int depth(Node node) {
		if (node == root) {
			return 0;
		} else {
			return 1 + depth(node.getParent());
		}
	}

}
