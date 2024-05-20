package application9;

import application2.LinkedList;

public class SHash<T extends Comparable<T>> {
	private int m;
	private LinkedList<T>[] hash;

	public SHash(int n) {
		m = n / 5;
		hash = new LinkedList[m];
		for (int i = 0; i < hash.length; i++) {
			hash[i] = new LinkedList<>();
		}
	}

	public void add(T data) {
		hash[Math.abs(data.hashCode() % m)].insert(data);
	}

	public void delete(T data) {
		hash[Math.abs(data.hashCode() % m)].delete(data);
	}

	public void find(T data) {
		hash[Math.abs(data.hashCode() % m)].search(data);
	}

	public void print() {
		for (int i = 0; i < hash.length; i++) {
			hash[i].traverse();
		}
	}
}
