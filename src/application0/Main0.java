package application0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main0 {
	myList<Name> names = new myList<>(10);
	public Tab lab0 = new Tab("Lab 0");
	public Main0() {
		try {
			//I will add another tabs when we take the another labs
			Button loadBtn = new Button("Load");
			Button addBtn = new Button("Add");
			TextField dataTF = new TextField();
			dataTF.setPromptText("Name,Gender,Frequency");
			Label addRes = new Label();
			HBox addBox = new HBox(addBtn, dataTF, addRes);
			addBox.setAlignment(Pos.CENTER);
			addBox.setSpacing(6);
			Button deleteBtn = new Button("Delete");
			TextField nameTF = new TextField();
			nameTF.setPromptText("Name");
			Label res = new Label();
			HBox deleteBox = new HBox(deleteBtn, nameTF, res);
			deleteBox.setAlignment(Pos.CENTER);
			deleteBox.setSpacing(2);
			Button findBtn = new Button("Find");
			TextField findNameTF = new TextField();
			findNameTF.setPromptText("Name");
			Label findRes = new Label();
			HBox findBox = new HBox(findBtn, findNameTF, findRes);
			findBox.setAlignment(Pos.CENTER);
			findBox.setSpacing(7);
			VBox finalBox = new VBox(loadBtn, addBox, deleteBox, findBox);
			finalBox.setAlignment(Pos.CENTER);
			finalBox.setSpacing(5);
			lab0.setContent(finalBox);
			loadBtn.setOnAction(e -> {
				Scanner in;
				try {
					in = new Scanner(new File("names.txt"));
					while (in.hasNext()) {
						String line = in.nextLine();
						names.Add(new Name(line.split(",")[0], line.split(",")[1].trim().charAt(0),
								Integer.parseInt(line.split(",")[2].trim())));

					}
				} catch (FileNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
			});

			addBtn.setOnAction(e -> {
				addRes.setText("");
				String[] data = dataTF.getText().split(",");
				if (data.length == 3) {
					try {
						boolean found = false;
						Name name = new Name(data[0], data[1].trim().charAt(0), Integer.parseInt(data[2].trim()));
						for (int i = 0; i < names.count; i++) {
							if (name.compareTo(names.get(i)) == 0 && name.getGender() == names.get(i).getGender()) {
								found = true;
							}
						}
						if (!found) {
							names.Add(name);
							addRes.setText("Added Successfuly");
						} else {
							addRes.setText("Martyr Already Exist");
							System.out.print("Martyr Already Exist");
						}
					} catch (Exception e1) {
						addRes.setText("Added UnSuccessfuly");
						System.out.println("An Error Occurd");
					}
				} else {
					addRes.setText("Added UnSuccessfuly");
				}
			});

			deleteBtn.setOnAction(e -> {
				res.setText("");
				String name = nameTF.getText();
				if (name != "") {
					Name pers = new Name(name, 'M', 0);
					int index = names.find(pers);
					System.out.println(index);
					if (index != -1) {
							names.delete(pers);
							res.setText("Deleted Successfuly");
					} else
						res.setText("Not Found");
						
					
				} else {
					res.setText("Deleted UnSuccessfuly");
				}
			});

			findBtn.setOnAction(e -> {
				findRes.setText("");
				String name = findNameTF.getText().trim();
				if (!"".equals(name)) {
					int index = names.find(new Name(name, 'M', 0));
					System.out.println(index);
					if (index != -1) {
						findRes.setText(names.get(index).toString());
					} else
						findRes.setText("Not Found");
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
