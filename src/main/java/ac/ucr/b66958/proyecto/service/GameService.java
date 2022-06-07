package ac.ucr.b66958.proyecto.service;


import ac.ucr.b66958.proyecto.domain.Memento;
import ac.ucr.b66958.proyecto.persistence.FilePersistence;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class GameService {

    private String path;
    private FileChooser fileChooser;
    private Stage stage;
    private File file;

    public GameService(Stage stage) {
        this.stage = stage;
    }

    private void initChooser(){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose the name and directory");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
    }

    public void saveGame(Memento memento){
        initChooser();
        file = fileChooser.showSaveDialog(stage);
        path = file.getPath();
        FilePersistence filePersistence = new FilePersistence(path);
        filePersistence.saveFile(memento);
        System.out.println(path);
    }

    public Memento loadGame(){
        initChooser();
        file = fileChooser.showOpenDialog(stage);
        path = file.getPath();
        System.out.println(path);
        FilePersistence filePersistence = new FilePersistence(path);
        return filePersistence.readFile();
    }
}
