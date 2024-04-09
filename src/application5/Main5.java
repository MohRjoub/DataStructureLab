package application5;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main5 {
	public Tab lab5 = new Tab("Lab 5");
	private TextField infixTF = new TextField();
	private Button evaluateBtn = new Button("Evaluate");
	private Label postfixLabel = new Label();
	private Label prefixLabel = new Label();
	private Label res = new Label();
	private HBox box = new HBox(infixTF,postfixLabel,res);
	private VBox finalBox = new VBox(box,prefixLabel,evaluateBtn);

	public Main5() {
		try {
			
		infixTF.setPromptText("Enter Infix expresion");
		box.setAlignment(Pos.CENTER);
		box.setSpacing(5);
		finalBox.setAlignment(Pos.CENTER);
		finalBox.setSpacing(2);
		lab5.setContent(finalBox);
		evaluateBtn.setOnAction(e->{
			String infix = infixTF.getText().trim();
			postfixLabel.setText("-->Postfix: "+infixToPostfix(infix));
			prefixLabel.setText("-->Prefix: "+infixToPrefix(infix));
			res.setText("    ,Value is: "+evaluateInfix(infix));
		});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private int evaluateInfix(String str) {
		LinkedStack<Integer> value = new LinkedStack<>();
		LinkedStack<Character> operators = new LinkedStack<>();

		char[] ch = str.toCharArray();

		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];

			if (Character.isDigit(c)) {
				int num = 0;
				while (i < ch.length && Character.isDigit(ch[i])) {
					num = num * 10 + (ch[i] - '0');
					i++;
				}
				i--;
				value.push(num);
			} else if (c == '(') {
				operators.push(c);
			} else if (c == ')') {
				while (!operators.isEmpty() && operators.peek().getData() != '(') {
					Integer result = calculate(value, operators);
					if (result != null) {
						value.push(result);
					}
				}
				operators.pop();
			} else if (isOperator(c)) {
				while (!operators.isEmpty() && priority(c) <= priority(operators.peek().getData())) {
					Integer result = calculate(value, operators);
					if (result != null) {
						value.push(result);
					}
				}
				operators.push(c);
			}
		}

		while (!operators.isEmpty()) {
			Integer result = calculate(value, operators);
			if (result != null) {
				value.push(result);
			}
		}

		Integer finalResult = value.pop().getData();
		return finalResult != null ? finalResult : 0;
	}

	public static String infixToPostfix(String str) {
		LinkedStack<Character> stack = new LinkedStack<>();
		StringBuilder postfix = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == ' ')
				continue;
			if (Character.isDigit(ch)) {
				postfix.append(ch);
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				while (!stack.isEmpty() && stack.peek().getData() != '(') {
					postfix.append(stack.pop().getData());
				}
				stack.pop();
			} else {
				while (!stack.isEmpty() && priority(ch) <= priority(stack.peek().getData())) {
					postfix.append(stack.pop().getData());
				}
				stack.push(ch);
			}
		}
		while (!stack.isEmpty()) {
			postfix.append(stack.pop().getData());
		}
		return postfix.toString();
	}

	public static String infixToPrefix(String str) {
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i = str.length() - 1; i >= 0; i--) {
	        char ch = str.charAt(i);
	        if (ch == '(') {
	            sb.append(')');
	        } else if (ch == ')') {
	            sb.append('(');
	        } else {
	            sb.append(ch);
	        }
	    }
	    String reversedStr = sb.toString();

	     
	    String postfix = infixToPostfix(reversedStr);

	    
	    sb.setLength(0);
	    for (int i = postfix.length() - 1; i >= 0; i--) {
	        sb.append(postfix.charAt(i));
	    }

	    return sb.toString();
	}

	private int calculate(LinkedStack<Integer> value, LinkedStack<Character> operators) {
		int b = value.pop().getData();
		int a = value.pop().getData();
		char operator = operators.pop().getData();

		switch (operator) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '^':
			return (int) Math.pow(a, b);
		case '/':
			if (b == 0) {
				throw new RuntimeException("Cannot divide by zero");
			}
			return a / b;
		}
		return 0;
	}

	private boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '/' || c == '*' || c == '^');
	}

	private static int priority(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}

}
