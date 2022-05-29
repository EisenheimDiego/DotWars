package ac.ucr.b66958.proyecto.utility;

import ac.ucr.b66958.proyecto.domain.Square;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class Utility {

    public static double squareSize;

    public static void showMessage(String message, int flag){
        Alert alert;
        if(flag == 1)
            alert = new Alert(Alert.AlertType.ERROR);
        else
            alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error");
        //alert.setHeaderText("Error");
        alert.setContentText(message);
        if(!alert.getContentText().isEmpty())
            alert.showAndWait();
    }

    public static boolean compareCoordinates(int coordinate, double coordinate2,
                                       int coordinate3, double coordinate4, int flag){
        switch (flag){
            case 1: return (coordinate <= coordinate2 && coordinate2 <= coordinate+squareSize) &&
                    (coordinate3 <= coordinate4 && coordinate4 <= coordinate3+squareSize);
            case 2: return (coordinate == coordinate2 && coordinate3 == coordinate4);
        }
        return false;
    }

    public static Square[][] initSquares(int n){
        int x = 0, y = 10;
        Square[][] squares = new Square[n][n];

        Utility.squareSize = Math.floor(700/n);
        Utility.squareSize = 5*(Math.floor(Math.abs(squareSize/5)));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                squares[i][j] = new Square(x , y);
                x+=squareSize;
            }
            x = 0;
            y+=squareSize;
        }
        return squares;
    }

    public static ComboBox<String> initCombo(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Personalized","5x5", "10x10", "15x15", "20x20", "25x25");
        return new ComboBox<>(items);
    }

}
