import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Geometry {
    private Pane root = new Pane();
    private Random random = new Random();

    public Geometry(Stage primaryStage){
        drawInterface(primaryStage);
    }

    private void drawInterface(Stage primaryStage){
        Button multyThreads = new Button();
        multyThreads.setTranslateX(10);
        multyThreads.setTranslateY(10);
        multyThreads.setText("Threads");
        root.getChildren().addAll(multyThreads);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        multyThreads.setOnMouseClicked(event -> {
            drawRectangles();
        });
    }

    private void drawRectangles(){

        // the number of rectangles is rented
        int count = random.nextInt(5) + 3;
        MyRectangle[] myRectangles = new MyRectangle[count];
        for (MyRectangle myRectangle: myRectangles){
            // Draw a rectangle
            drawRectangle();
        }
    }

    private void drawRectangle(){
        double x = random.nextInt((int) GeometryInMotion1.WIDTH/2);
        double y = random.nextInt((int) GeometryInMotion1.HEIGHT/2);
        double width = random.nextInt((int) GeometryInMotion1.WIDTH/2);
        double height = random.nextInt((int) GeometryInMotion1.HEIGHT/2);
        int typeXint = random.nextInt(2)+1;
        int typeYint = random.nextInt(2)+1;
        boolean typeX = (typeXint == 1);
        boolean typeY = (typeYint == 1);

        // launching a new rectangle stream
        new Thread(new MyRectangle(root,x,y,width,height,getColor(),typeX,typeY)).start();

    }

    private Color getColor(){
        Color color = Color.color(random.nextDouble(),
                random.nextDouble(),
                random.nextDouble(),
                0.6f);
        return color;
    }
}
