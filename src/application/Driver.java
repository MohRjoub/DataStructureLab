package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;

import application0.*;
import application1.Main1;
import application2.Main2;
import application3.Main3;
import application4.Main4;
import application5.Main5;
import application6.Main6;
import application7.Main7;
import application8.Main8;


public class Driver extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = new TabPane();
			Main0 main0 = new Main0();
			Main1 main1 = new Main1();
			Main2 main2 = new Main2();
			Main3 main3 = new Main3();
			Main4 main4 = new Main4();
			Main5 main5 = new Main5();
			Main6 main6 = new Main6();
			Main7 main7 = new Main7(primaryStage);
			Main8 main8 = new Main8();
			root.getTabs().addAll(main0.lab0,main1.lab1,main2.lab2,main3.lab3,main4.lab4,main5.lab5,main6.lab6,main7.lab7,main8.lab8);
			Scene scene = new Scene(root, 600, 500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
