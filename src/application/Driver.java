package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import application0.*;
import application1.Main1;
import application2.Main2;
import application3.Main3;


public class Driver extends Application {
	TabPane root = new TabPane();
	Main0 main0 = new Main0();
	Main1 main1 = new Main1();
	Main2 main2 = new Main2();
	Main3 main3 = new Main3();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root.getTabs().addAll(main0.lab0,main1.lab1,main2.lab2,main3.lab3);
			Scene scene = new Scene(root, 600, 500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
