package application9;

public class OHash<T extends Comparable<T>> {
	private HNode<T>[] table;
	private int m;

	public OHash(int n) {
		m = n * 2;
		table = new HNode[m];
		for (int i = 0; i < m; i++) {
			table[i] = new HNode<T>(null);
		}
	}
	
	public void add(T data) {
		int hash0 = Math.abs(data.hashCode()) % m ;
		if (table[hash0].getFlag() == 'E') {
			table[hash0].setDataT(data);
			table[hash0].setFlag('F');
		}
			while (table[hash0 = ++hash0 % m].getFlag() == 'F') ;
			table[hash0].setDataT(data);
	}
	
	public void delete(T data) {
		int hash0 = Math.abs(data.hashCode()) % m ;
		if (table[hash0].getFlag() == 'E') {
			table[hash0].setDataT(data);
			table[hash0].setFlag('F');
		}
			while (table[hash0 = ++hash0 % m].getFlag() == 'F') ;
			table[hash0].setDataT(data);
	}
}
