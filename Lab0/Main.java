package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	TabPane root = new TabPane();
	myList<Name> names = new myList<>(10);
	@Override
	public void start(Stage primaryStage) {
		try {
			Tab lab0 = new Tab("Lab 0");
			Button loadBtn = new Button("Load");
			Button addBtn = new Button("Add");
			TextField dataTF = new TextField();
			dataTF.setPromptText("Name,Gender,Frequency");
			Label addRes = new Label();
			HBox addBox = new HBox(addBtn,dataTF,addRes);
			addBox.setAlignment(Pos.CENTER);
			addBox.setSpacing(6);
			Button deleteBtn = new Button("Delete");
			TextField nameTF = new TextField();
			nameTF.setPromptText("Name");
			Label res = new Label();
			HBox deleteBox = new HBox(deleteBtn,nameTF,res);
			deleteBox.setAlignment(Pos.CENTER);
			deleteBox.setSpacing(2);
			Button findBtn = new Button("Find");
			TextField findNameTF = new TextField();
			findNameTF.setPromptText("Name");
			Label findRes = new Label();
			HBox findBox = new HBox(findBtn,findNameTF,findRes);
			findBox.setAlignment(Pos.CENTER);
			findBox.setSpacing(7);
			VBox finalBox = new VBox(loadBtn,addBox,deleteBox,findBox);
			finalBox.setAlignment(Pos.CENTER);
			finalBox.setSpacing(5);
			lab0.setContent(finalBox);
			root.getTabs().add(lab0);
			loadBtn.setOnAction(e->{
				Scanner in;
				try {
					in = new Scanner(new File("names.txt"));
					while (in.hasNext()) {
						String line = in.nextLine();
						names.Add( new Name(line.split(",")[0],line.split(",")[1].trim().charAt(0),Integer.parseInt(line.split(",")[2].trim())));
						
					}
					Name name = new Name(names.get(0).getName(), names.get(0).getGender(), names.get(0).getFrequency());
					System.out.println(name);
					System.out.println(names.find(name));
				} catch (FileNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
			});
			
			addBtn.setOnAction(e->{
				addRes.setText("");
				String[] data = dataTF.getText().split(",");
				if (data.length==3) {
					try {
						names.Add(new Name(data[0], data[1].trim().charAt(0), Integer.parseInt(data[2].trim())));
						addRes.setText("Added Successfuly");
					}catch (Exception e1) {
						addRes.setText("Added UnSuccessfuly");
						System.out.println("An Error Occurd");
					}
				}else {
					addRes.setText("Added UnSuccessfuly");
				}
			});
			
			deleteBtn.setOnAction(e->{
				res.setText("");
				String name = nameTF.getText();
				if (name!="") {
					for (int i = 0; i < names.count; i++) {
						if(names.get(i).getName().equals(name)) {
							names.delete(names.get(i));
							res.setText("Deleted Successfuly");
						}
					}
				}
				else {					
					res.setText("Deleted UnSuccessfuly");
				}
			});
			
			findBtn.setOnAction(e->{
				String name = findNameTF.getText().trim();
				if (!"".equals(name)) {
					for (int i = 0; i < names.count; i++) {
						if(names.get(i).getName().equals(name)) {
							int index = names.find(names.get(i));
							if (index!=-1) {								
								findRes.setText(names.get(index).toString());
							}
						}
					}
				}
			});
			
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
