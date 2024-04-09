package application5;

public interface Stackable <T extends Comparable<T>>{
	 void push(T data);
	public T pop();
	public T peek();
	public void clear();
	public boolean isEmpty();

}