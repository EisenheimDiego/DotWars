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
import java.util.ArrayList;
import java.util.Random;

public class Utility {

    public static double squareSize;
    public static final String PATH = System.getProperty("user.dir");
    public static String player1Path = "file:src/main/java/ac/ucr/b66958/proyecto/images/player1.png";
    public static String player2Path = "file:src/main/java/ac/ucr/b66958/proyecto/images/player2.png";
    public static String mana = "file:src/main/java/ac/ucr/b66958/proyecto/images/manaSquare.png";
    private static Square[][] squares;
    private static int n;
    private static ArrayList<Integer> posX = new ArrayList<>();
    private static ArrayList<Integer> posY = new ArrayList<>();
    private static Random random = new Random();

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
        Utility.n = n;

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
        items.addAll("Personalized", "10x10", "15x15", "20x20", "25x25");
        return new ComboBox<>(items);
    }

    public static ImageView dotWarsLogo(){
        InputStream stream = null;
        try {
            stream = new FileInputStream("src/main/java/ac/ucr/b66958/proyecto/images/logo.png");
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
        int distanceX = attacker.getX()-defender.getX();
        int distanceY = attacker.getY()-defender.getY();
        int absoluteDistance = Math.abs((distanceX+distanceY));
        return absoluteDistance/squareSize <=attacker.getHitDistance();
    }

    public static Square[][] initMana(int columns, int dotsQuantity){
        Square[][] manaDots = new Square[2][dotsQuantity];

        System.out.println("Vamos a crear: "+dotsQuantity+" dots de mana");
        System.out.println("En: "+columns+" columnas x filas");

        for (int i = 0; i < dotsQuantity; i++) {
            System.out.println(i+1);
            if(i+1 <= (dotsQuantity/2)){
                System.out.println("Es menor a "+dotsQuantity/2);
                if((i+1) % 2 == 0){ //FIRST QUARTER
                    System.out.println("Es par");
                    manaDots[0][i] =
                            new Square((int)(randomPosX((int)(Math.floor((double)columns/2)),0)*squareSize),
                            (int)(randomPosY((int)(Math.floor((double)columns/2)),1)*squareSize));
                    manaDots[1][i] = new Square((int)(randomPosX((columns/2),0)*squareSize),
                            (int)(randomPosY(columns-1,(int)(Math.floor((double)columns/2)))*squareSize));
                }else{ //SECOND QUARTER
                    System.out.println("Es impar");
                    manaDots[0][i] =
                            new Square((int)(randomPosX(columns-1,
                                    (int)(Math.floor((double)columns/2)))*squareSize),
                            (int)(randomPosY((int)(Math.floor((double)columns/2)),1)*squareSize));
                    manaDots[1][i] =
                            new Square((int)(randomPosX(columns-1,
                                    (int)(Math.floor((double)columns/2)))*squareSize),
                            (int)(randomPosY(columns-1,(int)(Math.floor((double)columns/2)))*squareSize));
                }
            }
            else{
                System.out.println("Es mayor a "+dotsQuantity/2);
                if((i+1) % 2 == 0){ //THIRD QUARTER
                    System.out.println("Es par");
                    manaDots[0][i] =
                            new Square((int)(randomPosX((int)(Math.floor((double)columns/2)),0)*squareSize),
                            (int)(randomPosY((int)(Math.floor((double)columns/2)),1)*squareSize));
                    manaDots[1][i] =
                            new Square((int)(randomPosX((int)(Math.floor((double)columns/2)),0)*squareSize),
                            (int)(randomPosY(columns-1,(int)(Math.floor((double)columns/2)))*squareSize));
                }else{ //FOURTH QUARTER
                    System.out.println("Es impar");
                    manaDots[0][i] =
                            new Square((int)(randomPosX(columns-1,
                                    (int)(Math.floor((double)columns/2)))*squareSize),
                            (int)(randomPosY((int)(Math.floor((double)columns/2)),1)*squareSize));
                    manaDots[1][i] =
                            new Square((int)(randomPosX(columns-1,
                                    (int)(Math.floor((double)columns/2)))*squareSize),
                            (int)(randomPosY(columns-1,(int)(Math.floor((double)columns/2)))*squareSize));
                }
            }
        }
        posX.clear();
        posY.clear();
        return manaDots;
    }

    private static Integer randomPosX(Integer top, Integer bottom){
        Integer result = randomPos(bottom, top);
        if(posX.contains(result)) randomPosX(top, bottom);
        else posX.add(result);
        return result;
    }

    private static Integer randomPosY(Integer top, Integer bottom){
        Integer result = randomPos(bottom, top);
        if(posY.contains(result)) randomPosY(top, bottom);
        else posY.add(result);
        return result;
    }

    private static Integer randomPos(Integer floor, Integer ceil){
        return (int)Math.floor(Math.random()*(ceil-floor+1)+floor);
    }

}
