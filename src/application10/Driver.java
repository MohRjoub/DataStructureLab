package application10;

public class Driver {
	public static void main(String[] args) {
		MaxHeap<Integer>heap = new MaxHeap<>(10);
		heap.insert(10);
		heap.insert(100);
		heap.insert(50);
		heap.insert(20);
		heap.print();
	}
}
