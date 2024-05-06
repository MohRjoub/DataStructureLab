package application8;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Main8 {
	private AVLTree<Integer> avlTree = new AVLTree<>();
	public Tab lab8 = new Tab("Lab 8");

	public Main8() {
		try {
			TextField addField = new TextField();
 			Button add = new Button("Add");
 			TextField deletefield = new TextField();
 			Button delete = new Button("Delete");
 			Button traverse = new Button("Print");
 			TextArea area = new TextArea();
			VBox finalBox = new VBox(addField, add, deletefield, delete, traverse, area);
			add.setOnAction(e->{
				Integer integer = Integer.parseInt(addField.getText());
				if (integer != null) {
					avlTree.insert(integer);
				}
			});
			
			delete.setOnAction(e->{
				Integer integer = Integer.parseInt(addField.getText());
				if (integer != null) 
				avlTree.delete(integer);
			});
			
			traverse.setOnAction(e->{
				area.setText(avlTree.toString());
			});
			finalBox.setAlignment(Pos.CENTER);
			finalBox.setSpacing(5);
			lab8.setContent(finalBox);
		} catch (Exception e2) {
		}
	}
}
