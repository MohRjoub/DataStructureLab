package application9;

public class Driver {
	public static void main(String[] args) {
		SHash<Integer> shash = new SHash<>(10);
		shash.add(10);
		shash.print();
		OHash<Integer>hash = new OHash<>(10);
		hash.add(10);
		hash.add(11);
		hash.add(13);
	}
}
