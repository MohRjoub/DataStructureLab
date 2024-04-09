package application2;

public class Driver {
	public static void main(String[] args) {
		LinkedList<Integer>linkedList = new LinkedList<>();
		linkedList.insert(5);
		linkedList.insert(10);
		linkedList.insert(1);
		linkedList.insert(-9);
		//linkedList.traverse();
		System.out.println(linkedList.toString());
		System.out.println(linkedList.search(-9));
		System.out.println(linkedList.length());
//		linkedList.delete(1);
//		linkedList.delete(5);
//		linkedList.delete(-9);
//		linkedList.delete(10);
		System.out.println(linkedList.length());
		//linkedList.traverse();
		System.out.println(linkedList.toString());

	}
	
}
