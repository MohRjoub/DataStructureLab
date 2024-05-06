package application8;

import application7.TNode;

public class AVLTree<T extends Comparable<T>> {
	private TNode<T> root;

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			TNode<T> rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	}

	private boolean isEmpty() {
		return root == null;
	}

	public void addEntry(T data, TNode<T> rootNode) {
		assert rootNode != null;
		if (data.compareTo((T) rootNode.getData()) < 0) { // right into left subtree
			if (rootNode.hasLeft()) {
				TNode<T> leftChild = rootNode.getLeft();
				addEntry(data, leftChild);
				rootNode.setLeft(rebalance(leftChild));
				;
			} else
				rootNode.setLeft(new TNode<T>(data));
			;
		} else { // right into right subtree
			if (rootNode.hasRight()) {
				TNode<T> rightChild = rootNode.getRight();
				addEntry(data, rightChild);
				rootNode.setRight(rebalance(rightChild));
				;
			} else
				rootNode.setRight(new TNode<T>(data));
			;
		}
	}

	private TNode<T> rebalance(TNode<T> nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.getLeft()) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.getRight()) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	private int getHeightDifference(TNode<T> nodeN) {
		return height(nodeN.getLeft()) - height(nodeN.getRight());
	}

	private TNode<T> rotateRightLeft(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.getRight();
		nodeN.setRight(rotateRight(nodeC));
		return rotateLeft(nodeN);
	}

	private TNode<T> rotateLeft(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.getRight();
		nodeN.setRight(nodeC.getLeft());
		nodeC.setLeft(nodeN);
		return nodeC;
	}

	private TNode<T> rotateLeftRight(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.getLeft();
		nodeN.setLeft(rotateLeft(nodeC));
		return rotateRight(nodeN);
	}

	private TNode<T> rotateRight(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.getLeft();
		nodeN.setLeft(nodeC.getRight());
		nodeC.setRight(nodeN);
		return nodeC;
	}

	public int height() {
		return height(root);
	}

	private int height(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.getLeft());
		if (node.hasRight())
			right = height(node.getRight());
		return (left > right) ? (left + 1) : (right + 1);
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	public TNode<T> find(T data) {
		return find(data, root);
	}

	public TNode<T> find(T data, TNode<T> node) {
		if (node != null) {
			int comp = node.getData().compareTo(data);
			if (comp == 0)
				return node;
			else if (comp > 0 && node.hasLeft())
				return find(data, node.getLeft());
			else
				return find(data, node.getRight());
		}
		return null;
	}

	public TNode<T> delete(T data) {
		TNode<T> temp = superDelete(data);
		if (temp != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	private TNode<T> superDelete(T value) {
		if (isEmpty())
			return null;
		TNode<T> parent = root;
		TNode<T> current = root;
		boolean isLeftChild = false;

		while (current != null && current.getData().compareTo(value) != 0) {
			parent = current;
			if (value.compareTo(current.getData()) < 0) {
				current = current.getLeft();
				isLeftChild = true;
			} else {
				current = current.getRight();
				isLeftChild = false;
			}
		}

		if (current == null)
			return null;

		if (current.isLeaf()) { // is a leaf
			if (current == root) {
				root = null;
			} else {
				if (isLeftChild)
					parent.setLeft(null);
				else
					parent.setRight(null);
			}
		} else if (current.hasLeft() && !current.hasRight()) { // is a node with  one child
			if (current == root) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		} else if (current.hasRight() && !current.hasLeft()) { // is a node with one child
			if (current == root) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
		} else { // is a node with two children
			TNode<T> successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}

			successor.setLeft(current.getLeft());
		}

		return current;
	}

	private TNode<T> getSuccessor(TNode<T> node) {
		TNode<T> parentOfSuccessor = node;
		TNode<T> successor = node;
		TNode<T> current = node.getRight();
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getLeft();
		}

		if (successor != node.getRight()) { // fix successor connections
			parentOfSuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}

		return successor;
	}

	private void traverseInOrder(TNode<T> node) {
		if (node != null) {
			traverseInOrder(node.getLeft());
			System.out.print(node + " ");
			traverseInOrder(node.getRight());
		}
	}
	
	@Override
	public String toString() {
	    return toString(root);
	}

	private String toString(TNode<T> node) {
	    String string = "";
	    if (node != null) {
	        string += toString(node.getLeft()); 
	        string += node.getData() + " ";
	        string += toString(node.getRight()); 
	    }
	    return string;
	}
}
