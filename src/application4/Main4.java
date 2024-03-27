package application4;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main4 {
	public Tab lab4 = new Tab("Lab 4");
	private CursorList<Integer> cursorList = new CursorList<>(20);
	private Label orginalList = new Label();
	private Button createList = new Button("Create List");
	private Label createListLabel = new Label();
	private HBox createListHBox = new HBox(createList, createListLabel);
	private Button addBtn = new Button("Insert");
	private TextField dataTF = new TextField();
	private Label addRes = new Label();
	private HBox addBox = new HBox(addBtn, dataTF, addRes);
	private Button deleteLastBtn = new Button("Delete Last");
	private Button deleteFirstBtn = new Button("Delete First");
	private Button deleteListBtn = new Button("Delete List");
	private TextField nameTF = new TextField();
	private Label res = new Label();
	private HBox deleteBox = new HBox(deleteLastBtn, deleteFirstBtn, deleteListBtn, nameTF, res);
	private Button findBtn = new Button("Search");
	private TextField findNameTF = new TextField();
	private Label findRes = new Label();
	private HBox findBox = new HBox(findBtn, findNameTF, findRes);
	private Button mergeBtn = new Button("Mrege");
	private TextField mergeTF = new TextField();
	private HBox mergeHBox = new HBox(mergeBtn, mergeTF);
	private VBox finalBox = new VBox(orginalList, createListHBox, addBox, deleteBox, findBox, mergeHBox);

	public Main4() {
		createListHBox.setAlignment(Pos.CENTER);
		createListHBox.setSpacing(5);
		addBox.setAlignment(Pos.CENTER);
		addBox.setSpacing(6);
		deleteBox.setAlignment(Pos.CENTER);
		deleteBox.setSpacing(2);
		findBox.setAlignment(Pos.CENTER);
		findBox.setSpacing(7);
		mergeHBox.setAlignment(Pos.CENTER);
		mergeHBox.setSpacing(5);
		nameTF.setPromptText("Enter List Number");
		mergeTF.setPromptText("Enter List Number");
		dataTF.setPromptText("Enter Value,List Number");
		findNameTF.setPromptText("Enter Value,List Number");
		mergeTF.setPromptText("Enter List1 Number,List2 Number");
		finalBox.setAlignment(Pos.CENTER);
		finalBox.setSpacing(2);
		lab4.setContent(finalBox);

		createList.setOnAction(e -> {
			createListLabel.setText("Number of List Created " + cursorList.createList());
		});

		addBtn.setOnAction(e -> {
			try {
				addRes.setText("");
				String[] data = dataTF.getText().trim().split(",");
				cursorList.insert(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
				addRes.setText("Added Successfuly");
				orginalList.setText(cursorList.print(Integer.parseInt(data[1])));
			} catch (Exception e1) {
				addRes.setText("An Error Occurd");
			}

		});

		deleteLastBtn.setOnAction(e -> {
			res.setText("");
			int list = Integer.parseInt(nameTF.getText().trim());
			Node<Integer> resultNode = cursorList.deleteLast(list);
			res.setText("Deleted Successfuly" + resultNode);
			orginalList.setText(cursorList.print(list));
		});
		deleteFirstBtn.setOnAction(e -> {
			res.setText("");
			int list = Integer.parseInt(nameTF.getText().trim());
			Node<Integer> resultNode = cursorList.deleteFirst(list);
			res.setText("Deleted Successfuly" + resultNode);
			orginalList.setText(cursorList.print(list));
		});
		deleteListBtn.setOnAction(e -> {
			try {
				res.setText("");
				int list = Integer.parseInt(nameTF.getText().trim());
				cursorList.deleteList(list);
				res.setText("Deleted Successfuly");
				orginalList.setText("");
				
			} catch (Exception e2) {
				orginalList.setText("An Error Occurd");
			}
		});

		findBtn.setOnAction(e -> {
			try {
				findRes.setText("");
				String[] data = findNameTF.getText().trim().split(",");
				findRes.setText(cursorList.find(Integer.parseInt(data[0]), Integer.parseInt(data[1])) + "");
			} catch (Exception e2) {
				findRes.setText("An Error Occurd");

			}
		});

		mergeBtn.setOnAction(e -> {
			try {
				String[] data = mergeTF.getText().trim().split(",");
				cursorList.merge(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
				orginalList.setText(cursorList.print(Integer.parseInt(data[0])));
			} catch (Exception e2) {
				orginalList.setText(e2+"");
			}
		});

	}
}
