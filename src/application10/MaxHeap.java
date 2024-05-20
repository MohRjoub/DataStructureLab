package application10;

public class MaxHeap<T extends Comparable<T>> {
	private T[] Heap;
	private int size;
	private int maxsize;

	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = (T[]) new Comparable[this.maxsize];
		;
	}

	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos) + 1;
	}

	private int rightChild(int pos) {
		return (2 * pos) + 2;
	}

	private boolean isLeaf(int pos) {
		if (pos > (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos) {
		T tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	private void maxHeapify(int pos) {
		if (isLeaf(pos))
			return;

		if (Heap[pos].compareTo(Heap[leftChild(pos)]) < 0 || Heap[pos].compareTo(Heap[rightChild(pos)]) < 0) {
			if (Heap[leftChild(pos)].compareTo(Heap[rightChild(pos)]) > 0) {
				swap(pos, leftChild(pos));
				maxHeapify(leftChild(pos));
			} else {
				swap(pos, rightChild(pos));
				maxHeapify(rightChild(pos));
			}
		}
	}

	public void insert(T element) {
		Heap[size] = element;
		int current = size;
		while (Heap[current].compareTo(Heap[parent(current)]) > 0) {
			swap(current, parent(current));
			current = parent(current);
		}
		size++;
	}

	public void print() {
		for (int i = 0; i < size / 2; i++) {
			System.out.print("Parent Node : " + Heap[i]);
			if (leftChild(i) < size)
				System.out.print(" Left Child Node: " + Heap[leftChild(i)]);
			if (rightChild(i) < size)
				System.out.print(" Right Child Node: " + Heap[rightChild(i)]);
			System.out.println();
		}
	}

	public T getMax() {
		T popped = Heap[0];
		Heap[0] = Heap[--size];
		maxHeapify(0);
		return popped;
	}
}
