package application3;

import application2.Node;

public class DoublyLinkedList<T extends Comparable<T>> {
	private DNode<T> head;

	public DoublyLinkedList() {
		this.head = null;

	}

	public void insert(T data) {
		DNode<T> newNode = new DNode<>(data);
		if (head == null) {
			head = newNode;
		} else if (head.getData().compareTo(newNode.getData()) >= 0) {
			newNode.setNext(head);
			newNode.getNext().setPrev(newNode);
			head = newNode;
		} else {
			DNode<T> current = head;
			while (current.getNext() != null && current.getNext().getData().compareTo(newNode.getData()) < 0) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			if (current.getNext() != null) {
				newNode.getNext().setPrev(newNode);
			}
			current.setNext(newNode);
			newNode.setPrev(current);
		}
	}

	public void traverse() {
		DNode<T> current = head;
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
	}

	public void backTraverse() {
		DNode<T> current = head;
		if (current != null) {
			while (current.getNext() != null) {
				current = current.getNext();
			}
			while (current.getPrev() != null) {
				System.out.print(current.getData() + " ");
				current = current.getPrev();
			}
			System.out.print(current.getData() + " ");
		}
	}

	public void backTraverseRec() {
		backTraverseRec(head);

	}

	public void backTraverseRec(DNode<T> curr) {
		if (curr != null) {
			backTraverseRec(curr.getNext());
			System.out.print(curr.getData() + " ");
		}
	}

	public void removeDublecateItrative() {
		DNode<T> current = head;
		if (current==null) {
			return;
		}
		while (current.getNext() != null && current.getData().compareTo(current.getNext().getData()) <= 0) {
			if (current.getData().compareTo(current.getNext().getData()) == 0) {
				delete(current.getNext().getData());
			}
			current = current.getNext();
		}
	}
	
	public void removeDuplecateRecursion() {
		removeDuplecateRec(head);
	}
	
	private void removeDuplecateRec(DNode<T> current) {
		if ( current==null || current.getNext()==null) {
			return;
		}
			if (current.getData().compareTo(current.getNext().getData()) == 0) {
				delete(current.getNext().getData());
		}
			removeDuplecateRec(current.getNext());
	}
	public DNode<T> find(T data) {
		DNode<T> current = head;
		while (current != null && current.getData().compareTo(data) <= 0) {
			if (current.getData().compareTo(data) == 0) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

	public int length() {
		return length(head);
	}

	private int length(DNode<T> head) {
		DNode<T> current = head;
		if (current == null) {
			return 0;
		}
		return 1 + length(current.getNext());
	}

	public DNode<T> delete(T data) {
		DNode<T> curr = head;
		while (curr != null && curr.getData().compareTo(data) <= 0) {
			if (curr.getData().compareTo(data) == 0)
				break;
			curr = curr.getNext();
		}
		if (curr != null && curr.getData().compareTo(data) == 0) { // found
			if (curr.getPrev() == null && curr.getNext() == null) // case 0: delete one item
				head = null;
			else if (curr.getPrev() == null) { // case 1: delete 1st item
				curr.getNext().setPrev(null);
				head = curr.getNext();
			} else if (curr.getNext() == null) { // case 3: delete last item
				curr.getPrev().setNext(null);
			} else { // case 2: delete between
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
			}
			return curr;
		}
		return null;
	}

	public T getMiddle() {
		int lenthg = length(head);
		DNode<T> current = head;
		for (int i = 0; current != null && i < lenthg / 2; current = current.getNext(), i++);
		return current.getData();
	}

	public void reverse() {
		DNode<T> temp = null;
		DNode<T> current = head;
		while (current != null) {
			temp = current.getPrev();
			current.setPrev(current.getNext());
			current.setNext(temp);
			current = current.getPrev();
		}
		if (temp != null) {
			head = temp.getPrev();
		}
	}
	
	
	public void reverseRecursion() {
		reverseRecursion(head);
	}

	private void reverseRecursion(DNode<T> current) {
	    if (current == null) {
	        return;
	    } else {
	        DNode<T> temp = current.getPrev();
	        current.setPrev(current.getNext());
	        current.setNext(temp);
	        if (current.getPrev() == null) {
	            head = current;
	        }
	        reverseRecursion(current.getPrev());
	    }
	}
	public String toString() {
		String string = "[";
		DNode<T> current = head;
		while (current != null) {
			string+= current.getData() + ",";
			current = current.getNext();
			}
		string = string.substring(0,string.length()-1) + "]";
		return string;
	}
}
