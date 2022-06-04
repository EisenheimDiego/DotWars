package ac.ucr.b66958.proyecto.domain;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private String name;
    private Map<Integer, Dot> dots;
    private int dotsLost;

    public Player(String name) {
        this.name = name;
        this.dots = new HashMap<>();
        this.dotsLost = 0;
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

    public void newDotLost(){
        dotsLost++;
    }
}
