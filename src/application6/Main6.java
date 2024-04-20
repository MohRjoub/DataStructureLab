package application6;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Main6 {
	public Tab lab6 = new Tab("Lab 6");
	private Button startBtn = new Button("Start");
	private TextArea area = new TextArea();
	private Label watTimeLabel = new Label();
	private VBox box = new VBox(startBtn,area,watTimeLabel);
		public Main6() {
			area.clear();
			box.setAlignment(Pos.CENTER);
			box.setSpacing(5);
			startBtn.setOnAction(e->{
				StringBuilder builder = new StringBuilder();
		    	 SQueue<Integer> queue = new SQueue<>();
		    	 SQueue<Integer> timrQueue = new SQueue<>();
         Random random = new Random();
         int prevTotal=0;
         int total = 0;
         int totalCustomers = 0;
         int totalWaitingTime = 0;
         for (int i = 0; i < 60; i++) {
             int in = random.nextInt(4);
             int out = 0;
             
             if (in == 1) {
                 queue.enqueue(1);
                 timrQueue .enqueue(i);
                 total++;
                 totalCustomers++;
             } else if (in == 2) {
                 queue.enqueue(1);
                 queue.enqueue(1);
                 timrQueue .enqueue(i);
                 timrQueue .enqueue(i);
                 total += 2;
                 totalCustomers += 2;
             } else {
            	in =0;
             }
             builder.append("Min: " + i + " , In: " + in + " , Total: " + total);

             if (!queue.isEmpty()) {
            	 int out1=timrQueue .dequeue();
            	 out=queue.dequeue();
            	 builder.append(" , out: "+prevTotal+"\n");
                totalWaitingTime += i - out1;
				prevTotal=out;
				total--;
			}else {
           	 builder.append(" , out: "+prevTotal+"\n");
				prevTotal = 0;
			}
         }
        	 area.setText(builder.toString());
         double averageWaitingTime = totalWaitingTime / (double) totalCustomers;
         watTimeLabel.setText("Average Waiting Time: " + String.format("%.4f", averageWaitingTime)+ "Minutes");
			});
			lab6.setContent(box);
    }
}
