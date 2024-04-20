package application6;

public interface Queueable <T extends Comparable<T>>{
	 T dequeue();
	 T getFront();
	 void enqueue(T data);
	 boolean isEmpty();
	 void clear();
}
