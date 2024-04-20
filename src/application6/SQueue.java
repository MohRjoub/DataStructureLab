package application6;

import application2.Node;
import application5.LinkedStack;

public class SQueue<T extends Comparable<T>> implements Queueable<T> {
	private LinkedStack<T> stack = new LinkedStack<>();

	@Override
	public T dequeue() {
		T data = null;
		if (!stack.isEmpty()) {
			 data = stack.pop().getData();
		}
		return data;
	}

	@Override
	public T getFront() {
		T data = null;
		if (!stack.isEmpty()) {
			 data = stack.peek().getData();
		}
		return data;
	}

	@Override
	public void enqueue(T data) {
		LinkedStack<T> tempStack = new LinkedStack<>();
		while (!stack.isEmpty()) {
			tempStack.push(stack.pop().getData());
		}
		stack.push(data);
		while (!tempStack.isEmpty()) {
			stack.push(tempStack.pop().getData());
		}
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		if (!stack.isEmpty()) {
			stack.clear();
		}

	}
	
	@Override
	public String toString() {
		String string = "";
		LinkedStack<T> tempStack = new LinkedStack<>();
		while (!stack.isEmpty()) {
			tempStack.push(stack.pop().getData());
		}
		while (!tempStack.isEmpty()) {
			Node<T>node=tempStack.pop();
			stack.push(node.getData());
			string += node.getData()+" ";
		}

		return string;
	}
}
