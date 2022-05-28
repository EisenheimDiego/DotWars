package ac.ucr.b66958.proyecto.domain;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private String name;
    private Map<Integer, Dot> dots;

    public Player(String name) {
        this.name = name;
        this.dots = new HashMap<>();
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
}
