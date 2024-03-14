package application2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main2 {
	public Tab lab2 = new Tab("Lab 2");
	private LinkedList<Integer> linkedList = new LinkedList<>();
	private Label orginalList = new Label();
	private Button recReverse = new Button("Reverse Using Recursion");
	private Label recReverseLabel = new Label();
	private HBox recReverseHBox = new HBox(recReverse, recReverseLabel);
	private Button itrReverse = new Button("Reverse Using Iterative");
	private Label itrReverseLabel = new Label();
	private HBox itrReverseHBox = new HBox(itrReverse, itrReverseLabel);
	private Button addBtn = new Button("Add");
	private TextField dataTF = new TextField();
	private Label addRes = new Label();
	private HBox addBox = new HBox(addBtn, dataTF, addRes);
	private Button deleteBtn = new Button("Delete");
	private TextField nameTF = new TextField();
	private Label res = new Label();
	private HBox deleteBox = new HBox(deleteBtn, nameTF, res);
	private Button findBtn = new Button("Search");
	private TextField findNameTF = new TextField();
	private Label findRes = new Label();
	private HBox findBox = new HBox(findBtn, findNameTF, findRes);
	private VBox finalBox = new VBox(orginalList, addBox, deleteBox, findBox, recReverseHBox, itrReverseHBox);

	public Main2() {
		linkedList.insert(5);
		linkedList.insert(1);
		linkedList.insert(6);
		linkedList.insert(8);
		linkedList.insert(4);
		orginalList.setText("List Befor Reverse: " + linkedList.toString());
		recReverseHBox.setAlignment(Pos.CENTER);
		recReverseHBox.setSpacing(5);
		itrReverseHBox.setAlignment(Pos.CENTER);
		itrReverseHBox.setSpacing(5);
		addBox.setAlignment(Pos.CENTER);
		addBox.setSpacing(6);
		deleteBox.setAlignment(Pos.CENTER);
		deleteBox.setSpacing(2);
		findBox.setAlignment(Pos.CENTER);
		findBox.setSpacing(7);
		finalBox.setAlignment(Pos.CENTER);
		finalBox.setSpacing(5);
		lab2.setContent(finalBox);

		addBtn.setOnAction(e -> {
			try {
				addRes.setText("");
				String data = dataTF.getText().trim();
				linkedList.insert(Integer.parseInt(data));
				addRes.setText("Added Successfuly");
				orginalList.setText(linkedList.toString());
			} catch (Exception e1) {
				addRes.setText("An Error Occurd");
			}

		});
		
		deleteBtn.setOnAction(e -> {
			try {
				res.setText("");
				String data = nameTF.getText().trim();
				Integer found = linkedList.search(Integer.parseInt(data));
				if (found!=null) {
					linkedList.delete(Integer.parseInt(data));
					orginalList.setText(linkedList.toString());
					res.setText("Deleted Successfuly");
				}else {
					res.setText("Not Found");
				}
			} catch (Exception e2) {
				res.setText("An Error Occurd");
			}
		});
		
		
		findBtn.setOnAction(e -> {
			try {
				findRes.setText("");
				String data = findNameTF.getText().trim();
				findRes.setText(linkedList.search(Integer.parseInt(data))+"");
			} catch (Exception e2) {
				findRes.setText("An Error Occurd");

			}
		});


		recReverse.setOnAction(e -> {
			try {
				recReverseLabel.setText("");
				linkedList.reverseRec();
				recReverseLabel.setText(linkedList.toString());
			} catch (Exception e2) {
			}
		});

		itrReverse.setOnAction(e -> {
			try {
				itrReverseLabel.setText("");
				linkedList.reverseItr();
				itrReverseLabel.setText(linkedList.toString());
			} catch (Exception e2) {
			}
		});
	}

}
