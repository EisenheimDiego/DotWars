package ac.ucr.b66958.proyecto.domain;

public class Memento {

    private int n;
    private double squareSize;
    private Player p1, p2;
    private Square[][] mana;

    public Memento(int n, double squareSize, Player p1, Player p2, Square[][] mana) {
        this.n = n;
        this.squareSize = squareSize;
        this.p1 = p1;
        this.p2 = p2;
        this.mana = mana;
    }

    public Memento() {
    }

    public Square[][] getMana() {
        return mana;
    }

    public void setMana(Square[][] mana) {
        this.mana = mana;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(double squareSize) {
        this.squareSize = squareSize;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }
}
