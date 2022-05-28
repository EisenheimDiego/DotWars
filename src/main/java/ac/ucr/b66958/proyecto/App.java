package ac.ucr.b66958.proyecto;

import ac.ucr.b66958.proyecto.ui.InitWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        InitWindow window = new InitWindow();
        Scene scene = new Scene(window, 1280, 720);
        stage.setTitle("My Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
