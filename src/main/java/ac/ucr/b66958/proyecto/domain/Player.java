package ac.ucr.b66958.proyecto.domain;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private String name;
    private Map<Integer, Dot> dots;
    private int dotsLost;
    private int manaPoints;

    public Player(String name) {
        this.name = name;
        this.dots = new HashMap<>();
        this.dotsLost = 0;
        this.manaPoints = 0;
    }

    public Player() {
    }

    public void addDot(Dot dot){
        this.dots.put(dot.getId(), dot);
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Dot> getDots() {
        return dots;
    }

    public int getDotsLost() {
        return dotsLost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDots(Map<Integer, Dot> dots) {
        this.dots = dots;
    }

    public void setDotsLost(int dotsLost) {
        this.dotsLost = dotsLost;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public void addManaPoint(){
        this.manaPoints++;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public void newDotLost(){
        dotsLost++;
    }
}
