package application5;

import application4.CursorList;

public class CursorStack <T extends Comparable<T>> implements Stackable<T>{
		private CursorList<T>cursorList ;
		private int n=0;
		public CursorStack(int size) {
			cursorList = new CursorList<>(size+2);
			 n = cursorList.createList();
		}
		@Override
		public void push(T data) {
			cursorList.insertAtHead(data, n);
		}
		@Override
		public T pop() {
			return cursorList.deleteFirst(n).getData();
		}
		@Override
		public T peek() {
			T data = pop();
			push(data);
			return data;
		}
		@Override
		public void clear() {
			cursorList.deleteList(n);

		}
		@Override
		public boolean isEmpty() {
			return cursorList.isEmpty(n);
		}


	}
