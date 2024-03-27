package application4;

public class CursorList<T extends Comparable<T>> {
	Node<T>[] cursorArray;

	public CursorList(int size) {
		cursorArray = new Node[size];
		initialization();
	}

	public int initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++)
			cursorArray[i] = new Node<>(null, i + 1);
		cursorArray[cursorArray.length - 1] = new Node<>(null, 0);
		return 0;
	}

	public int malloc() {
		int p = cursorArray[0].next;
		cursorArray[0].next = cursorArray[p].next;
		return p;
	}

	public void free(int p) {
		cursorArray[p] = new Node<T>(null, cursorArray[0].next);
		cursorArray[0].next = p;
	}

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	public boolean isEmpty(int l) {
		return cursorArray[l].next == 0;
	}

	public boolean isLast(int p) {
		return cursorArray[p].next == 0;
	}

	public int createList() {
		int l = malloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new Node("-", 0);
		return l;
	}

	public void insert(T data, int l) {
		int p = malloc();
		if (p != 0) {
			Node<T> newNode = new Node<T>(data, cursorArray[l].next);
			if (isEmpty(l)) {
				cursorArray[p] = newNode;
				cursorArray[l].next = p;
			} else {
				int prev = 0;
				int current = cursorArray[l].getNext(); // Start from the head.next

				while (current != 0 && cursorArray[current].getData().compareTo(newNode.getData()) < 0) {
					prev = current;
					current = cursorArray[current].next;
				}
				if (prev == 0) {
					cursorArray[p] = newNode;
					cursorArray[l].next = p;
				} else {
					cursorArray[p] = newNode;
					cursorArray[prev].setNext(p);
					newNode.setNext(current);
				}
			}
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void insertAtHead(T data, int l) {
		if (isNull(l))
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new Node<T>(data, cursorArray[l].next);
			cursorArray[l].next = p;
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void insertAtLast(T data, int l) {
		if (isNull(l))
			return;
		int p = malloc();
		if (p != 0) {
			while (!isNull(l) && !isEmpty(l)) {
				l = cursorArray[l].next;
			}
			cursorArray[p] = new Node<T>(data, cursorArray[l].next);
			cursorArray[l].next = p;
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void traversList(int l) {
		System.out.print("list_" + l + "-->");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			System.out.print(cursorArray[l] + "-->");
		}
		System.out.println("null");
	}

	public int find(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			if (cursorArray[l].getData().equals(data))
				return l;
		}
		return -1;
	}

	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].next].getData().equals(data))
				return l;
			l = cursorArray[l].next;
		}
		return -1;
	}

	public Node<T> delete(T data, int l) {
		int p = findPrevious(data, l);
		if (p != -1) {
			int c = cursorArray[p].next;
			Node<T> temp = cursorArray[c];
			cursorArray[p].next = temp.next;
			free(c);
		}
		return null;
	}

	public void deleteList(int l) {
		free(l);
	}

	public void deleteListRec(int l) {
		if (!isNull(l) || !isEmpty(l)) {
			free(l);
		} else {
			int c = cursorArray[l].next;
			Node<T> temp = cursorArray[c];
			cursorArray[l].next = temp.next;
			free(c);
			deleteListRec(l);
		}
	}

	public Node<T> deleteFirst(int l) {
		if (!isNull(l) && !isEmpty(l)) {
			int c = cursorArray[l].next;
			Node<T> temp = cursorArray[c];
			cursorArray[l].next = temp.next;
			free(c);
			return temp;
		}
		return null;
	}

	public Node<T> deleteLast(int l) {
		if (!isNull(l) && !isEmpty(l)) {
			int prev = 0;
			while (!isLast(l)) {
				prev = l;
				l = cursorArray[l].next;
			}
			if (prev == 0) {
				return deleteFirst(l);
			} else {
				Node<T> temp = cursorArray[l];
				cursorArray[prev].next = 0;
				free(l);
				return temp;
			}
		}
		return null;
	}
	
	public void merge(int l1, int l2) {
		for (int i = 0; i < length(l2)+1; i++) {
			insert(deleteLast(l2).getData(), l1);
		}
		free(l2);
	}

	public int length(int l) {
		int c = 0;
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			c++;
		}
		return c;
	}

	public int lengthRec(int l) {
		if (isNull(l) || isEmpty(l)) {
			return 0;
		}
		return 1 + lengthRec(cursorArray[l].next);
	}

	public String print(int l) {
		String string ="[";
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			string += cursorArray[l].getData() + ",";
		}		return string.substring(0,string.length()-1) + "]";
	}
}

class Node<T extends Comparable<T>> {
	private T data;
	int next;

	public Node(T data, int next) {
		this.data = data;
		this.next = next;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public String toString() {
		return "[" + data + " , " + next + "]";
	}
}