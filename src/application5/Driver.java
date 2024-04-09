package application5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Driver{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("C:\\Users\\ACTC\\eclipse-workspace\\DataStructureLab\\src\\application5\\LinkedStack.java"));
		StringBuilder builder = new StringBuilder();
		while (in.hasNext()) {
			String string =in.nextLine();
			
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (c == '(' || c == '[' || c == '{' || c == ')' || c == ']' || c == '}'){
					builder.append(c);
				}
			}
		}
		System.out.println(isBalancd(builder.toString()));
	}

	public static boolean isBalancd(String string) {
		Stack<Character>stack = new Stack<>();
		boolean isBalanceeed = true;
		for (int i = 0; isBalanceeed && i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '(' || c == '[' || c == '{')
				stack.push(c);
			else if (c == ')' || c == ']' || c == '}') {
					if (stack.isEmpty()) {
						return false;
					}
					char p = stack.pop();
					if (!((c=='}' && p == '{') || (c==']' && p=='[') || (c==')' && p=='('))) {
						return false;
					}
			}
		}
		return stack.isEmpty();

	}
}
