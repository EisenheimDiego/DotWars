package ac.ucr.b66958.proyecto;

import ac.ucr.b66958.proyecto.ui.InitWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        InitWindow window = new InitWindow();
        Scene scene = new Scene(window, 1300, 700);
        stage.setTitle("DotWars");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
