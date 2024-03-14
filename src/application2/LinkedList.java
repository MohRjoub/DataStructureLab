package application2;

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;

	public void insert(T element) {
		Node<T> data = new Node(element);
		if (head == null) {
			head = data;
		} else {
			Node<T> current;
			Node<T> prev = null;
			for (current = head; current != null
					&& current.getData().compareTo(data.getData()) < 0; prev = current, current = current.getNext())
				;
			if (prev == null) {
				data.setNext(head);
				head = data;
			} else if (current == null) {
				prev.setNext(data);
			} else {
				prev.setNext(data);
				data.setNext(current);
			}
		}
	}

	public void traverse() {
		Node<T> current = head;
		System.out.println("Head -->");
		while (current != null) {
			System.out.println(current.getData() + " -->");
			current = current.getNext();
		}
		System.out.println("Null");
	}

	public String toString() {
		String string = "[";
		Node<T> current = head;
		while (current != null) {
			string+= current.getData() + ",";
			current = current.getNext();
			}
		string = string.substring(0,string.length()-1) + "]";
		return string;
	}

	public T search(T data) {
		Node<T> current = head;
		for (; current != null && current.getData().compareTo(data) <= 0; current = current.getNext()) {
			if (current.getData().compareTo(data) == 0) {
				return current.getData();
			}
		}
		return null;
	}

	public void delete(T data) {
		if (head != null) {
			Node<T> current = head;
			Node<T> prev = null;
			for (; current != null && current.getData().compareTo(data) < 0; prev = current, current = current.getNext());
			if (prev == null) {
				head = current.getNext();
			} else if (current == null) {
				prev.setNext(null);
			} else {
				prev.setNext(current.getNext());
			}
		}
	}
	
	public int length() {
		return length(head);
	}
	
	private int length(Node<T>head) {
		Node<T>current = head;
		if (current == null) {
			return 0;
		}
		return 1 + length(current.getNext());
	}
	
	public void reverseItr() {
	    Node<T> curr = head;
	    Node<T> prev = null;
	    Node<T> next = null;
	    while (curr != null) {
	        next = curr.getNext();
	        curr.setNext(prev);
	        prev = curr;
	        curr = next;
	    }
	    head = prev;
	}
	public void reverseRec() {
	    head = reverseRec(head, null);
	}

	private Node<T> reverseRec(Node<T> curr, Node<T> prev) {
	    if (curr == null) {
	        return prev;
	    }
	    Node<T> next = curr.getNext();
	    curr.setNext(prev);
	    return reverseRec(next, curr);
	}

}
