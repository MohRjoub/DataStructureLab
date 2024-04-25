package application7;

import java.io.File;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main7{
	private BSTree<District> records = new BSTree<>();
	public Tab lab7 = new Tab("Lab 7");


	
	 public Main7(Stage primaryStage) {
		try {
		FileChooser fileChooser = new FileChooser();
		Label label = new Label("No files selected");
		Button open = new Button("Open File");
		VBox openFileBox = new VBox(30, label, open);
		openFileBox.setAlignment(Pos.CENTER);
		TextArea area = new TextArea();
		Label label2 = new Label("Districts Name");
		BorderPane finalPane = new BorderPane();
		finalPane.setTop(label2);
		finalPane.setCenter(area);
		Scene scene = new Scene(finalPane,400, 400);
		open.setOnAction(new EventHandler<ActionEvent>() {// handle opening file using File Chooser
			public void handle(ActionEvent e) {
				fileChooser.setInitialDirectory(new File("C:\\Users\\ACTC\\Downloads"));
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null && (file.getName().equals("data (1).csv") || file.getName().equals("sample.csv"))) {// check the right file
					label.setText(file.getName() + "  selected");
					Scanner in;
					try {
						in = new Scanner(file);
						in.nextLine();
						while (in.hasNext()) {// read data from the file and save it to the linked list
							String string = in.nextLine().trim();
							Scanner scanner = new Scanner(string);
							scanner.useDelimiter(",");						
							String Name = scanner.next();
							String Date = scanner.next();
							String Age = scanner.next();
							String Location = scanner.next();
							String District = scanner.next();
							String Gender = scanner.next();
							Martyr martyr = new Martyr(Name, Date, Age, Location, District, Gender.charAt(0));//create new Martyr object
							TNode<District> node = records.find(new District(District));//check if the District already exist
							if (node != null) {
								District district = node.getData();
								TNode<Location>node1 = district.getLocations().find(new Location(Location));
								if (node1 != null) {
									Location location = node1.getData();//check if the Location already exist
										location.getMartyrs().enqueue(martyr);//if not add it 
								} else {//if the Location not exist add it
									Location tempLocation = new Location(Location);
									tempLocation.insertMartyr(martyr);
									district.insertLocation(tempLocation);
								}
							} else {//if the District not exist add it
								Location tempLocation = new Location(Location);
								tempLocation.insertMartyr(martyr);
								District tempDistrict = new District(District);//create new District object
								tempDistrict.insertLocation(tempLocation);
								records.insert(tempDistrict);
							}
						}
						area.setText(records.toString());
						lab7.setContent(finalPane);
						primaryStage.setScene(scene);
						primaryStage.show();
						
					} catch (Exception e1) {
						e1.printStackTrace();
						System.out.println(e1.getMessage());
					}
				} else {
					try {
						label.setText("Not the correct file");
					} catch (Exception e2) {
					}
				}
			}
		});
		lab7.setContent(openFileBox);

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
}
}
