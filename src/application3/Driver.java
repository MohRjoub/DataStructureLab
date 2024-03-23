package application3;

public class Driver {
	public static void main(String[] args) {
		DoublyLinkedList<Integer>doublyLinkedList=new DoublyLinkedList<>();
		doublyLinkedList.removeDublecateItrative();
		doublyLinkedList.insert(10);
		doublyLinkedList.insert(20);
		doublyLinkedList.insert(30);
		doublyLinkedList.insert(40);
		doublyLinkedList.insert(50);
		doublyLinkedList.traverse();
		System.out.println();
		doublyLinkedList.reverseRecursion();
		System.out.println();
		doublyLinkedList.traverse();




		

	}
}
