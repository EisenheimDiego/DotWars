package ac.ucr.b66958.proyecto.ui;

import ac.ucr.b66958.proyecto.domain.Dot;
import ac.ucr.b66958.proyecto.domain.Memento;
import ac.ucr.b66958.proyecto.domain.Player;
import ac.ucr.b66958.proyecto.domain.Square;
import ac.ucr.b66958.proyecto.service.GameService;
import ac.ucr.b66958.proyecto.utility.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Map;

public class InitWindow extends Pane {
    private final Canvas canvas;
    private ImageView logo;
    private Player turn;

    private Button begin;
    private Label size;
    private TextField sizeText;

    private Label player1, player2;
    private TextField player1Name, player2Name;

    private HBox menu;

    private Button quit, restart;
    private Button save, load;

    private Button move, pass, attack;

    private Label showTurn, showMessage, dotsLost;

    private ComboBox<String> defaultDimensions;

    private ObservableList<Dot> dotsView;
    private ListView<Dot> dotsListView;

    private Memento memento;
    private final GameService gameService;

    private GraphicsContext g;
    private Square[][] squares;
    private Player p1, p2;
    private int dotPosition;
    private int dotPlayer;
    private int attMov;
    private Dot chosen, enemy;
    private int step;
    private int movements;
    private boolean choosingEnemy;

    public InitWindow(Stage stage) {

        this.canvas = new Canvas(700, 700);
        this.step = 0;
        this.dotPlayer = 0;
        this.movements = 0;
        this.gameService = new GameService(stage);
        initComponents();
        setCoordinates();
        initAttributes();

        this.getChildren().addAll(this.canvas, this.logo, this.size, this.sizeText, this.begin,
                this.player1, this.player2, this.player1Name, this.player2Name, this.showTurn,
                this.defaultDimensions, this.menu, this.dotsListView, this.dotsLost);

        this.menu.getChildren().addAll(this.quit, this.save, this.load, this.restart, this.move, this.pass,
                this.attack, this.showMessage);

        events();
        drawBorderedBoard();
    }

    private void initComponents() {

        this.g = this.canvas.getGraphicsContext2D();

        this.logo = Utility.dotWarsLogo();

        this.begin = new Button("Begin new game");
        this.size = new Label("Board size:");
        this.sizeText = new TextField();

        this.showTurn = new Label("Player's turn: ");
        this.showTurn.setVisible(false);

        this.dotsLost = new Label("");
        this.dotsLost.setVisible(false);

        this.player1 = new Label("Player 1:");
        this.player1Name = new TextField();

        this.player2 = new Label("Player 2:");
        this.player2Name = new TextField();

        this.size = new Label("Board size:");
        this.sizeText = new TextField();

        this.menu = new HBox();
        this.menu.setPrefHeight(50);
        this.menu.setPrefWidth(600);
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
        this.pass = new Button("Pass");
        this.attack = new Button("Attack!");

        this.showMessage = new Label("");
        this.showMessage.setStyle("-fx-text-fill: #ffffff");

        this.defaultDimensions = Utility.initCombo();
        this.defaultDimensions.setPrefWidth(110);
        this.defaultDimensions.getSelectionModel().select(0);

        this.dotsView = FXCollections.observableArrayList();
        this.dotsListView = new ListView<>(dotsView);
        this.dotsListView.setPrefSize(500, 200);

        disablePlayButtons();
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

        this.logo.setLayoutX(1170);
        this.logo.setLayoutY(8);
        this.logo.setFitWidth(120);
        this.logo.setPreserveRatio(true);

        this.showTurn.setLayoutX(720);
        this.showTurn.setLayoutY(90);

        this.dotsLost.setLayoutX(950);
        this.dotsLost.setLayoutY(90);

        this.menu.setLayoutX(720);
        this.menu.setLayoutY(120);

        this.dotsListView.setLayoutX(720);
        this.dotsListView.setLayoutY(170);
    }

    private void initAttributes(){
        this.dotPosition = -1;
        this.attMov = 0;
        this.chosen = null;
        this.enemy = null;
        this.choosingEnemy = false;
        this.step = 0;
    }

    private void events() {
        this.begin.setOnAction(actionEvent -> beginBoard());
        this.quit.setOnAction(actionEvent -> quitGame());
        this.restart.setOnAction(actionEvent -> restartGame(this.memento));
        this.save.setOnAction(actionEvent -> saveGame());
        this.load.setOnAction(actionEvent -> loadGame());
        this.move.setOnAction(actionEvent -> infoUnlockBoard(1));
        this.defaultDimensions.setOnAction(actionEvent -> comboChanged());
        this.setOnMouseClicked(this::squareClicked);
        this.setOnKeyReleased(this::moveKey);
        this.pass.setOnAction(actionEvent -> newAction());
        this.attack.setOnAction(actionEvent -> infoUnlockBoard(2));
    }

    private void newAction(){
        movements++;
        if(movements == 1){
            messageInfo("Choose your action: 2/2");
            initAttributes();
        }else if(movements == 2){
            movements = 0;
            assignTurn();
        }
    }

    private void assignTurn() {
        if (turn.getName().equals(p1.getName()))
            turn = p2;
        else turn = p1;

        this.showTurn.setText("Player's turn: " + turn.getName());
        if (!showTurn.isVisible())
            this.showTurn.setVisible(true);

        this.dotsLost.setText("Dots lost: "+turn.getDotsLost());
        if (!dotsLost.isVisible())
            this.dotsLost.setVisible(true);

        messageInfo("Choose your action: 1/2");
        step = 0;
        updateDotsList();
    }

    private void moveKey(KeyEvent event) {
        if (attMov == 0 || attMov == 2) {
            return;
        }
        if (dotPosition == -1) {
            return;
        }
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
                dotPosition++;
                break;
            case 2:
                dot.setX(dot.getX() - (int) Utility.squareSize);
                dot.setId(dotPosition - 1);
                newDot = p.getDots().get(dotPosition);
                newDot.setId(dotPosition - 1);
                p.getDots().remove(dotPosition);
                p.addDot(newDot);
                dotPosition--;
                break;
            case 3:
                dot.setY(dot.getY() + (int) Utility.squareSize);
                break;
            case 4:
                dot.setY(dot.getY() - (int) Utility.squareSize);
                break;
        }
    }

    private void infoUnlockBoard(int info){
        disablePlayButtons();
        if (info == 1) {
            messageInfo("Choose a dot and move it");
            action(1);
        }
        if (info == 2) {
            messageInfo("Choose your attacker");
            action(2);
        }
    }

    private void messageInfo(String message){
        if(!this.showMessage.isVisible())
            this.showMessage.setVisible(true);
        this.showMessage.setText(message);
    }

    private void action(int action) {
        if (action == 1) {
            attMov = 1;
            chosen = null;
            step = 0;
        }
        if (action == 2) {
            attMov = 2;
            chosen = null;
            step = 0;
        }
        if(action == 3){
            choosingEnemy = true;
            this.showMessage.setText("Choose your enemy");
        }
    }

    private void moveDot(int direction) {
        boolean flag = true;
        if (this.dotPlayer == 1) {
            if (validateMovement(direction, p1))
                movement(direction, p1.getDots().get(dotPosition), p1);
            else flag = false;
        } else {
            if (validateMovement(direction, p2))
                movement(direction, p2.getDots().get(dotPosition), p2);
            else flag = false;
        }
        if (flag) dotMoved();
    }

    private void dotMoved(){
        repaint();
        step++;
        if (step == chosen.getStepDistance()) {
            attMov = 0;
            enablePlayButtons();
            newAction();
        }else
        messageInfo(chosen.getStepDistance() - step + " movements left");
    }

    private boolean validateMovement(int direction, Player p) {
        Integer positionY = p.getDots().get(dotPosition).getY();
        Integer positionX = p.getDots().get(dotPosition).getX();
        if (direction == 4) {
            return Utility.moveUpY(positionY, dotPosition, p1, p2);
        }
        if (direction == 3) {
            return Utility.moveDownY(positionY, dotPosition, p1, p2);
        }
        if (direction == 2) {
            return Utility.moveLeftX(positionX, positionY, dotPosition, p1, p2);
        }
        if (direction == 1) {
            return Utility.moveRightX(positionX, positionY, dotPosition, p1, p2);
        }
        return true;
    }

    private void repaint() {
        clearBoard();
        drawBoard();
        drawDots();
    }

    private void clearBoard(){
        g.setFill(Color.LIGHTGRAY);
        g.clearRect(0, 10, 700, 700);
    }

    private void updateDotsList() {
        this.dotsView.clear();
        this.dotsView.addAll(this.turn.getDots().values());
    }

    private void restartGame(Memento memento){
        obtainMementoData(memento);
        clearBoard();
        drawBoard();
        assignDots();
        assignTurn();
        disableInitButtons();
        enablePlayButtons();
        Utility.showMessage("Game restarted", 2);
    }

    private void obtainMementoData(Memento memento){
        int nMem = memento.getN();
        squares = Utility.initSquares(nMem);
        p1 = memento.getP1();
        p2 = memento.getP2();
        turn = p2;
        this.player1Name.setText(p1.getName());
        this.player2Name.setText(p2.getName());
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
                    disableInitButtons();
                    enablePlayButtons();
                    this.memento = new Memento(n, Utility.squareSize,
                            new Player(player1Name.getText()), new Player(player2Name.getText()));
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
        if(attMov == 0){
            return ;
        }
        if(choosingEnemy){
            enemyClicked(event);
        }else{
            dotClicked(event);
        }
    }

    private void dotClicked(MouseEvent event){
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
                                this.chosen = p1.getDots().get(j);
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
                                this.chosen = p2.getDots().get(j);
                            }
                        }
                    }
                    if(this.chosen != null){
                        this.dotPosition = j;
                        if(attMov == 2){
                            action(3);
                        }
                    }
                }
            }
        }
    }

    private void enemyClicked(MouseEvent event){
        g.setStroke(Color.WHITE);
        for (int i = 0; i < squares[0].length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                if (Utility.compareCoordinates(squares[i][j].getX(), event.getX(),
                        squares[i][j].getY(), event.getY(), 1)) {
                    if (turn == p1) {
                        if (p2.getDots().containsKey(j)) {
                            if (Utility.compareCoordinates(p2.getDots().get(j).getX(), squares[i][j].getX(),
                                    p2.getDots().get(j).getY(), squares[i][j].getY(), 2)) {
                                System.out.println(p2.getDots().get(j).getId() + " of player 2");
                                g.strokeRect(p2.getDots().get(j).getX(), p2.getDots().get(j).getY(),
                                        Utility.squareSize, Utility.squareSize);
                                this.enemy = p2.getDots().get(j);
                            }
                        }
                    } else {
                        if (p1.getDots().containsKey(j)) {
                            if (Utility.compareCoordinates(p1.getDots().get(j).getX(), squares[i][j].getX(),
                                    p1.getDots().get(j).getY(), squares[i][j].getY(), 2)) {
                                System.out.println(p1.getDots().get(j).getId() + " of player 1");
                                g.strokeRect(p1.getDots().get(j).getX(), p1.getDots().get(j).getY(),
                                        Utility.squareSize, Utility.squareSize);
                                this.enemy = p1.getDots().get(j);
                            }
                        }
                    }
                }
            }
        }
        if(this.enemy != null){
            boolean attackPossible = Utility.attackDot(chosen, enemy);
            infoAttack(attackPossible);
            choosingEnemy = false;
        }
    }

    private void infoAttack(boolean result){
        if(result){
            Utility.showMessage("You've attacked", 2);
            attack();
            step++;
        }else{
            Utility.showMessage("You're enemy is too far", 1);
            newAction();
        }
        enablePlayButtons();
        this.chosen = null;
        this.enemy = null;
        attMov = 0;
    }

    private void attack() {
        if(turn == p1){
            p2.getDots().get(enemy.getId())
                    .setLife(p2.getDots().get(enemy.getId()).getLife()
                    -chosen.getStrength());
            deadDots(p2);
        }else{
            p1.getDots().get(enemy.getId())
                    .setLife(p1.getDots().get(enemy.getId()).getLife()
                            -chosen.getStrength());
            deadDots(p1);
        }
        repaint();
        newAction();
        checkLoser();
    }

    private void deadDots(Player toCheck){
        Iterator<Map.Entry<Integer, Dot>> dots = toCheck.getDots().entrySet().iterator();
        int posDead = -1;
        while(dots.hasNext()){
            Dot temp = dots.next().getValue();
            if(temp.getLife() <= 0){
                posDead = temp.getId();
            }
        }
        if(posDead > -1){
            toCheck.getDots().remove(posDead);
            toCheck.newDotLost();
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
        g.fillRect(0, 0, 700, 700); //BACKGROUND
        g.setFill(Color.BLACK);
        g.setLineWidth(2);
        g.setStroke(Color.BLACK);
        g.strokeRect(0, 0, 700, 700);
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
        Memento loaded = this.gameService.loadGame();
        restartGame(loaded);
    }

    private void saveGame() {
        this.gameService.saveGame(new Memento(squares[0].length, Utility.squareSize, p1, p2));
    }

    private void quitGame() {
        clearGame();
        if (turn.getName().equals(p1.getName())) {
            Utility.showMessage(p2.getName() + " has won the game!", 2);
        } else {
            Utility.showMessage(p1.getName() + " has won the game!", 2);
        }
    }

    private void clearGame(){
        initAttributes();
        enableInitButtons();
        disablePlayButtons();
        showTurn.setText("");
        dotsLost.setText("");
        player1Name.clear();
        player2Name.clear();
        dotsView.clear();
        clearBoard();
        drawBorderedBoard();
        messageInfo("The game was finished");
    }

    private void checkLoser(){
        if(p1.getDots().size() == 0){
            turn = p2;
            quitGame();
        }else if(p2.getDots().size() == 0){
            turn = p1;
            quitGame();
        }
    }

    private void disableInitButtons() {
        this.defaultDimensions.setDisable(true);
        this.begin.setDisable(true);
        this.size.setDisable(true);
        this.player1Name.setDisable(true);
        this.player2Name.setDisable(true);
    }

    private void enableInitButtons() {
        this.defaultDimensions.setDisable(false);
        this.begin.setDisable(false);
        this.size.setDisable(false);
        this.defaultDimensions.getSelectionModel().select(0);
        this.player1Name.setDisable(false);
        this.player2Name.setDisable(false);
    }

    private void disablePlayButtons() {
        this.quit.setDisable(true);
        this.restart.setDisable(true);
        this.save.setDisable(true);
        this.load.setDisable(true);
        this.move.setDisable(true);
        this.pass.setDisable(true);
        this.attack.setDisable(true);
    }

    private void enablePlayButtons() {
        this.quit.setDisable(false);
        this.restart.setDisable(false);
        this.save.setDisable(false);
        this.load.setDisable(false);
        this.move.setDisable(false);
        this.pass.setDisable(false);
        this.attack.setDisable(false);
    }
}
