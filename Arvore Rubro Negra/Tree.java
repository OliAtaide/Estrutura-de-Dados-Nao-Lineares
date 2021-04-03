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


	/*public void insert(int key) {
		Node n = new Node(key);
		if (root == null) {
			root = n;
		} else {
			Node node = root;
			Node parent;
			while (true) {
				parent = node;
				if (key < node.getKey()) {
					node = node.getLeft();
					if (node == null) {
						parent.setLeft(n);
						n.setParent(parent);
						return;
					}
				} else {
					node = node.getRight();
					if (node == null) {
						parent.setRight(n);
						n.setParent(parent);
						return;
					}
				}
			}
		}
	}

	public boolean remove(int key) {
		Node node = root;
		Node parent = root;
		boolean ItsLeft = true;

		while (node.getKey() != key) {
			parent = node;
			if (key < node.getKey()) {
				ItsLeft = true;
				node = node.getLeft();
				node.setParent(parent);
			} else {
				ItsLeft = false;
				node = node.getRight();
				node.setParent(parent);
			}
			if (node == null) {
				return false;
			}
		}
		if (node.getLeft() == null && node.getRight() == null) {
			if (node == root) {
				root = null;
			} else if (ItsLeft) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else if (node.getRight() == null) {
			if (node == root) {
				root = node.getLeft();
				root.setParent(null);
			} else if (ItsLeft) {
				parent.setLeft(node.getLeft());
				node.getLeft().setParent(parent);
			} else {
				parent.setRight(node.getLeft());
				node.getLeft().setParent(parent);
			}
		} else if (node.getLeft() == null) {
			if (node == root) {
				root = node.getRight();
				root.setParent(null);
			} else if (ItsLeft) {
				parent.setLeft(node.getRight());
				node.getRight().setParent(parent);
			} else {
				parent.setRight(node.getRight());
				node.getRight().setParent(parent);
			}
		} else {
			Node replace = replace(node);
			if (node == root) {
				root = replace;
			} else if (ItsLeft) {
				parent.setLeft(replace);
				replace.setParent(parent);
			} else {
				parent.setRight(replace);
				replace.setParent(parent);
			}
			replace.setLeft(node.getLeft());

		}
		return true;
	}*/

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
