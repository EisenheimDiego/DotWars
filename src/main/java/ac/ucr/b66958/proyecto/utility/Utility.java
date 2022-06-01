package ac.ucr.b66958.proyecto.utility;

import ac.ucr.b66958.proyecto.domain.Dot;
import ac.ucr.b66958.proyecto.domain.Player;
import ac.ucr.b66958.proyecto.domain.Square;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Utility {

    public static double squareSize;
    public static final String PATH = System.getProperty("user.dir");
    private static Square[][] squares;

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
            case 3: return (coordinate == coordinate2) || coordinate3 == coordinate4;
        }
        return false;
    }

    public static Square[][] initSquares(int n){
        int x = 0, y = 0;
        squares = new Square[n][n];

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

    public static ImageView dotWarsLogo(){
        InputStream stream = null;
        try {
            stream = new FileInputStream("src/main/java/ac/ucr/b66958/proyecto/images/weapon-gun.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);
        return new ImageView(image);
    }

    public static boolean moveUpY(int y, int position, Player p1, Player p2){
        if ((y - squareSize) < 0) {
            return false;
        }
        if (p1.getDots().containsKey(position)) {
            if (p1.getDots().get(position).getY() == (y - squareSize)) {
                return false;
            }
        }
        if (p2.getDots().containsKey(position)) {
            if (p2.getDots().get(position).getY() == (y - squareSize)) {
                return false;
            }
        }
        return true;
    }

    public static boolean moveDownY(int y, int position, Player p1, Player p2){
        if((y+squareSize) > squares[squares[0].length-1][squares[0].length-1].getY()){
            return false;
        }
        if (p1.getDots().containsKey(position)) {
            if (p1.getDots().get(position).getY() == (y + squareSize)) {
                return false;
            }
        }
        if (p2.getDots().containsKey(position)) {
            if (p2.getDots().get(position).getY() == (y + squareSize)) {
                return false;
            }
        }
        return true;
    }

    public static boolean moveLeftX(int x, int y, int position, Player p1, Player p2){
        if ((x - squareSize) < 0) {
            return false;
        }
        if (p1.getDots().containsKey(position-1) && p1.getDots().get(position-1).getY() == y) {
            if (p1.getDots().get(position-1).getX() == (x - squareSize)) {
                return false;
            }
        }
        if (p2.getDots().containsKey(position-1) && p2.getDots().get(position-1).getY() == y) {
            if (p2.getDots().get(position-1).getX() == (x - squareSize)) {
                return false;
            }
        }
        return true;
    }

    public static boolean moveRightX(int x, int y, int position, Player p1, Player p2){
        if((x+squareSize) > squares[0][squares[0].length-1].getX()){
            return false;
        }
        if (p1.getDots().containsKey(position+1) && p1.getDots().get(position+1).getY() == y) {
            if (p1.getDots().get(position+1).getX() == (x + squareSize)) {
                return false;
            }
        }
        if (p2.getDots().containsKey(position+1) && p2.getDots().get(position+1).getY() == y) {
            if (p2.getDots().get(position+1).getX() == (x + squareSize)) {
                return false;
            }
        }
        return true;
    }

    public static boolean attackDot(Dot attacker, Dot defender){
        if(attacker.getX() == defender.getX()){
            if(attacker.getX()/squareSize == 0){
                return true;
            }
        }
        return false;
    }

}
