package application9;

public class HNode<T extends Comparable<T> > {
	private T dataT;
	private char flag;
	
	public HNode(T data) {
		setDataT(data);
		setFlag('E');
	}

	public T getDataT() {
		return dataT;
	}

	public void setDataT(T dataT) {
		this.dataT = dataT;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char falge) {
		this.flag = falge;
	}
	
	
}
