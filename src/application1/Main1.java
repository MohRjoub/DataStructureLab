package application1;

import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main1 {
    public Tab lab1 = new Tab("Lab 1");
	private int[] arr = { 1, 2, 3, 4 };

	public Main1() {
		Button reverseButton = new Button("Reverse");
		TextField reverseField = new TextField();
		reverseField.setPromptText("Enter String to Reverse");
		Label reverseLabel = new Label();
		HBox reverseHBox = new HBox(reverseField, reverseButton, reverseLabel);
		reverseHBox.setAlignment(Pos.CENTER);
		reverseHBox.setSpacing(5);
		Button searchButton = new Button("Search");
		TextField searchField = new TextField();
		searchField.setPromptText("Enter value to search for it");
		Label searchLabel = new Label();
		HBox searcHBox = new HBox(searchField, searchButton, searchLabel);
		searcHBox.setAlignment(Pos.CENTER);
		searcHBox.setSpacing(5);
		Button sumButton = new Button("Find Sum");
		TextField sumField = new TextField();
		sumField.setPromptText("Enter number to find its digit sum");
		Label sumLabel = new Label();
		HBox sumHBox = new HBox(sumField, sumButton, sumLabel);
		sumHBox.setAlignment(Pos.CENTER);
		sumHBox.setSpacing(5);
		Label nameLabel = new Label("Made By Mohammad Rjoub");
		nameLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20; -fx-font-weight: bold;");
		VBox finalBox = new VBox(nameLabel,reverseHBox, sumHBox,  searcHBox ,new Label("Available Values is: "+Arrays.toString(arr)));
		finalBox.setAlignment(Pos.CENTER);
		finalBox.setSpacing(5);
		lab1.setContent(finalBox);
		reverseButton.setOnAction(e -> {
			String s = reverseField.getText().trim();
			reverseLabel.setText(reverseString(s));
		});

		searchButton.setOnAction(e -> {
			try {
				if (!searchField.getText().trim().equals("")) {
				int value = Integer.parseInt(searchField.getText().trim());
				searchLabel.setText(binarySearch(arr, value)+"");
				}else {
					searchLabel.setText("Enter A value");
				}
			} catch (Exception e2) {
				searchLabel.setText(e2.getMessage());
			}
		});
		
		sumButton.setOnAction(e->{
			try {
				if (!sumField.getText().trim().equals("")) {
				int value = Integer.parseInt(sumField.getText().trim());
				sumLabel.setText(sumDigits(value)+"");
				}else {
					sumLabel.setText("Enter A value");
				}
			} catch (Exception e3) {
				sumLabel.setText(e3.getMessage());
			}
		});
		
	}

	public static String reverseChars(String s) {
		if (s.length() == 0) {
			return s;
		}
		return reverseChars(s.substring(1)) + s.charAt(0);
	}

	public static String reverseString(String s) {
		String[] s1 = s.split(" ");
		if (s1.length == 1 || s1.length == 0)
			return reverseChars(s);
		return reverseChars(s1[s1.length - 1]) + " "
				+ reverseString(s.substring(0, (s.length() - 1 - s1[s1.length - 1].length())));
	}

	private static int countChar(String s, char c) {
		return countChar(0, s, c);
	}

	private static int countChar(int i, String s, char c) {
		if (i == s.length())
			return 0;
		if (s.charAt(i) == c)
			return 1 + countChar(++i, s, c);
		return countChar(++i, s, c);
	}

	public static int sumDigits(int n) {
		if (n < 10)
			return n;
		return sumDigits(n % 10 + sumDigits(n / 10));
	}

	public static int binarySearch(int[] a, int key) {
		Arrays.sort(a);
		return binarySearch(a, key, 0, a.length - 1);
	}

	public static int binarySearch(int[] arr, int key, int i, int j) {
		if (i > j)
			return -1;

		int mid = (j + i) / 2;
		if (arr[mid] == key)
			return mid;
		if (arr[mid] > key)
			return binarySearch(arr, key, i, mid - 1);
		return binarySearch(arr, key, mid + 1, j);

	}

}
