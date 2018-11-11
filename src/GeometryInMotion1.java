import javafx.application.Application;
import javafx.stage.Stage;

public class GeometryInMotion1 extends Application {
    public static final int WIDTH = 650;
    public static final int HEIGHT = 650;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setTitle("Thread");



        Geometry geometry = new Geometry(primaryStage);
    }
}
