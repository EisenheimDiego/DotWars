package ac.ucr.b66958.proyecto.ui;

import ac.ucr.b66958.proyecto.domain.Dot;
import ac.ucr.b66958.proyecto.domain.Player;
import ac.ucr.b66958.proyecto.domain.Square;
import ac.ucr.b66958.proyecto.utility.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Map;

public class InitWindow extends Pane {
    private final Canvas canvas;
    private Player turn;

    private Button begin;
    private Label size;
    private TextField sizeText;

    private Label player1, player2;
    private TextField player1Name, player2Name;

    private HBox menu;

    private Button quit, restart;
    private Button save, load;

    private Button move;

    private Label showTurn;

    ComboBox<String> defaultDimensions;

    GraphicsContext g;
    Square[][] squares;
    Player p1, p2;
    int dotPosition;
    int dotPlayer;

    public InitWindow() {

        this.canvas = new Canvas(700, 700);
        this.dotPosition = 0;
        this.dotPlayer = -1;

        initComponents();
        setCoordinates();

        this.getChildren().addAll(this.canvas, this.size, this.sizeText, this.begin,
                this.player1, this.player2, this.player1Name, this.player2Name, this.showTurn,
                this.defaultDimensions, this.menu);

        this.menu.getChildren().addAll(this.quit, this.save, this.load, this.restart, this.move);

        events();
        drawBorderedBoard();
    }

    private void initComponents() {

        this.g = this.canvas.getGraphicsContext2D();

        this.begin = new Button("Begin new game");
        this.size = new Label("Board size:");
        this.sizeText = new TextField();

        this.showTurn = new Label("Player's turn: ");
        this.showTurn.setVisible(false);

        this.player1 = new Label("Player 1:");
        this.player1Name = new TextField();

        this.player2 = new Label("Player 2:");
        this.player2Name = new TextField();

        this.size = new Label("Board size:");
        this.sizeText = new TextField();

        this.menu = new HBox();
        this.menu.setPrefHeight(50);
        this.menu.setPrefWidth(500);
        this.menu.setStyle("-fx-background-color: #0c0d2b");
        this.menu.setPadding(new Insets(10));
        this.menu.setSpacing(10);

        this.quit = new Button("Quit");
        this.quit.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

        this.restart = new Button("Restart");
        this.restart.setStyle("-fx-background-color: #02d63e;");

        this.save = new Button("Save");
        this.load = new Button("Load");

        this.move = new Button("Move");

        this.defaultDimensions = Utility.initCombo();
        this.defaultDimensions.getSelectionModel().select(0);
    }

    private void setCoordinates() {

        this.size.setLayoutX(720);
        this.size.setLayoutY(50);

        this.defaultDimensions.setLayoutX(790);
        this.defaultDimensions.setLayoutY(50);

        this.sizeText.setLayoutX(920);
        this.sizeText.setLayoutY(50);
        this.sizeText.setPrefWidth(100);

        this.begin.setLayoutX(1040);
        this.begin.setLayoutY(50);

        this.player1.setLayoutX(720);
        this.player1.setLayoutY(10);

        this.player1Name.setLayoutX(790);
        this.player1Name.setLayoutY(10);

        this.player2.setLayoutX(950);
        this.player2.setLayoutY(10);

        this.player2Name.setLayoutX(1020);
        this.player2Name.setLayoutY(10);

        this.showTurn.setLayoutX(720);
        this.showTurn.setLayoutY(90);

        this.menu.setLayoutX(720);
        this.menu.setLayoutY(120);

        //this.quit.setLayoutX(720);
        //this.quit.setLayoutY(120);

        //this.save.setLayoutX(770);
        //this.save.setLayoutY(120);

        //this.load.setLayoutX(820);
        //this.load.setLayoutY(120);

        //this.move.setLayoutX(820);
        //this.move.setLayoutY(150);
    }

    private void events() {
        this.begin.setOnAction(actionEvent -> beginBoard());
        this.quit.setOnAction(actionEvent -> quitGame());
        this.save.setOnAction(actionEvent -> saveGame());
        this.load.setOnAction(actionEvent -> loadGame());
        //this.move.setOnAction(actionEvent -> moveDot());
        this.defaultDimensions.setOnAction(actionEvent -> comboChanged());
        this.setOnMouseClicked(this::squareClicked);
        this.setOnKeyReleased(this::moveKey);
    }

    private void assignTurn() {
        if (turn.getName().equals(p1.getName()))
            turn = p2;
        else
            turn = p1;
        if (!showTurn.isVisible())
            this.showTurn.setVisible(true);
        this.showTurn.setText("Player's turn: " + turn.getName());
    }

    private void attack() {
        //TODO ATTACKING METHOD FOR A DOT
    }

    private void moveKey(KeyEvent event) {
        if(dotPosition != -1){
            switch (event.getCode()) {
                case UP:
                    moveDot(4);
                    break;
                case DOWN:
                    moveDot(3);
                    break;
                case LEFT:
                    moveDot(2);
                    break;
                case RIGHT:
                    moveDot(1);
                    break;
            }
        }
    }

    private void movement(int direction, Dot dot, Player p) {
        Dot newDot;
        switch (direction) {
            case 1:
                dot.setX(dot.getX() + (int) Utility.squareSize);
                dot.setId(dotPosition + 1);
                newDot = p.getDots().get(dotPosition);
                newDot.setId(dotPosition + 1);
                p.getDots().remove(dotPosition);
                p.addDot(newDot);
                break;
            case 2:
                dot.setX(dot.getX() - (int) Utility.squareSize);
                dot.setId(dotPosition - 1);
                newDot = p.getDots().get(dotPosition);
                newDot.setId(dotPosition - 1);
                p.getDots().remove(dotPosition);
                p.addDot(newDot);
                break;
            case 3:
                dot.setY(dot.getY() + (int) Utility.squareSize);
                break;
            case 4:
                dot.setY(dot.getY() - (int) Utility.squareSize);
                break;
        }
    }

    private void moveDot(int direction) {
        if (this.dotPlayer == 1) {
            movement(direction, p1.getDots().get(dotPosition), p1);
        } else {
            movement(direction, p2.getDots().get(dotPosition), p2);
        }
        dotPosition = -1;
        assignTurn();
        repaint();
    }

    private void repaint() {
        g.setFill(Color.LIGHTGRAY);
        g.clearRect(0, 10, 700, 700);
        drawBoard();
        drawDots();
    }

    private void beginBoard() {
        String message = "";
        try {
            int n = Integer.parseInt(sizeText.getText());
            if (n < 5 || (n % 5 != 0)) {
                message = "Value must be a multiple of 5. Min value: 5";
            } else {
                if (player1Name.getText().isEmpty() || player1Name.getText().equals("")
                        || player2Name.getText().isEmpty() || player2Name.getText().equals("")) {
                    message = "You must type both of the players names";
                } else {
                    p1 = new Player(player1Name.getText());
                    p2 = new Player(player2Name.getText());
                    turn = p2;
                    squares = Utility.initSquares(n);
                    drawBoard();
                    assignDots();
                    assignTurn();
                }
            }
        } catch (NumberFormatException nfe) {
            message = "Board size value must be numeric";
        } finally {
            Utility.showMessage(message, 1);
        }
    }

    private void squareClicked(MouseEvent event) {
        if (event.getX() > 700 || event.getY() > 700 || squares == null) {
            return;
        }
        repaint();
        g.setStroke(Color.WHITE);
        for (int i = 0; i < squares[0].length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                if (Utility.compareCoordinates(squares[i][j].getX(), event.getX(),
                        squares[i][j].getY(), event.getY(), 1)) {
                    if (turn == p1) {
                        if (p1.getDots().containsKey(j)) {
                            if (Utility.compareCoordinates(p1.getDots().get(j).getX(), squares[i][j].getX(),
                                    p1.getDots().get(j).getY(), squares[i][j].getY(), 2)) {
                                System.out.println(p1.getDots().get(j).getId() + " of player 1");
                                this.dotPlayer = 1;
                                g.strokeRect(p1.getDots().get(j).getX(), p1.getDots().get(j).getY(),
                                        Utility.squareSize, Utility.squareSize);
                            }
                        }
                    } else {
                        if (p2.getDots().containsKey(j)) {
                            if (Utility.compareCoordinates(p2.getDots().get(j).getX(), squares[i][j].getX(),
                                    p2.getDots().get(j).getY(), squares[i][j].getY(), 2)) {
                                System.out.println(p2.getDots().get(j).getId() + " of player 2");
                                this.dotPlayer = 2;
                                g.strokeRect(p2.getDots().get(j).getX(), p2.getDots().get(j).getY(),
                                        Utility.squareSize, Utility.squareSize);
                            }
                        }
                    }
                    this.dotPosition = j;
                }
            }
        }
    }

    private void drawBoard() {
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBorderedBoard();

        g.setStroke(Color.BLACK);
        for (int i = 0; i < squares[0].length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                g.strokeRect(squares[i][j].getX(), squares[i][j].getY(), Utility.squareSize, Utility.squareSize);
                //EACH SQUARE OF THE BOARD
            }
        }
    }

    private void drawBorderedBoard() {
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 10, 700, 700); //BACKGROUND
        g.setFill(Color.BLACK);
        g.setLineWidth(2);
        g.setStroke(Color.BLACK);
        g.strokeRect(0, 10, 700, 700);
    }

    private void assignDots() {
        for (int i = 0; i < squares[0].length; i++) {
            if (i != 0) {
                if (i % 5 == 0) {

                    Dot dotP1 = new Dot(squares[0][i].getX(), squares[0][i].getY(), i);
                    Dot dotP2 = new Dot(squares[squares[0].length - 1][i].getX(),
                            squares[squares[0].length - 1][i].getY(), i);

                    p1.addDot(dotP1);
                    p2.addDot(dotP2);
                }
            }
        }
        drawDots();
    }

    private void drawRect(int x, int y) {
        g.fillRect(x, y, Utility.squareSize, Utility.squareSize);
    }

    private void drawDots() {
        //this.g.setTransform(this.affine);
        Map<Integer, Dot> dots = p1.getDots();
        g.setFill(Color.GREEN);
        dots.forEach((k, v) -> drawRect(v.getX(), v.getY()));
        dots = p2.getDots();
        g.setFill(Color.BLUE);
        dots.forEach((k, v) -> drawRect(v.getX(), v.getY()));
    }

    private void comboChanged() {
        this.sizeText.setEditable(defaultDimensions.getSelectionModel().getSelectedIndex() == 0);
        switch (defaultDimensions.getSelectionModel().getSelectedIndex()) {
            case 0:
                this.sizeText.clear();
                break;
            case 1:
                this.sizeText.setText("5");
                break;
            case 2:
                this.sizeText.setText("10");
                break;
            case 3:
                this.sizeText.setText("15");
                break;
            case 4:
                this.sizeText.setText("20");
                break;
            case 5:
                this.sizeText.setText("25");
                break;
        }
    }

    private void loadGame() {
        //TODO calling the load of the file
    }

    private void saveGame() {
        //TODO save the actual state of the game into a file
    }

    private void quitGame() {
        if (turn.getName().equals(p1.getName())) {
            Utility.showMessage(p2.getName() + " has won the game!", 2);
        } else {
            Utility.showMessage(p1.getName() + " has won the game!", 2);
        }
    }


}
