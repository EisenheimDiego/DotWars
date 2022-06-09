package ac.ucr.b66958.proyecto.domain;

public class Dot {

    private Integer life;
    private Integer strength;
    private Integer hitDistance;
    private Integer stepDistance;
    private Integer x;
    private Integer y;
    private Integer id;
    private Integer manaPoints;

    public Dot() {
    }

    public Dot(Integer x, Integer y, Integer id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.life = 3;
        this.strength = 1;
        this.hitDistance = 3;
        this.stepDistance = 3;
        this.manaPoints = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public void loseLife(Integer hit){
        this.life -= hit;
    }

    public void gainLife(Integer lifeGain){
        this.life += lifeGain;
        this.manaPoints-= lifeGain;
    }

    public void gainStrength(Integer strengthGain){
        this.strength += strengthGain;
        this.manaPoints-= strengthGain;
    }

    public void gainHitDistance(Integer hitDistanceGain){
        this.hitDistance += hitDistanceGain;
        this.manaPoints-= hitDistanceGain;
    }

    public void gainStepDistance(Integer stepDistanceGain){
        this.stepDistance += stepDistanceGain;
        this.manaPoints-= stepDistanceGain;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getHitDistance() {
        return hitDistance;
    }

    public void setHitDistance(Integer hitDistance) {
        this.hitDistance = hitDistance;
    }

    public Integer getStepDistance() {
        return stepDistance;
    }

    public void setStepDistance(Integer stepDistance) {
        this.stepDistance = stepDistance;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void movePositiveX(int step){
        this.x+=step;
    }

    public void moveNegativeX(int step){
        this.x-=step;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void movePositiveY(int step){
        this.y+=step;
    }

    public void moveNegativeY(int step){
        this.y-=step;
    }

    public Integer getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(Integer manaPoints) {
        this.manaPoints = manaPoints;
    }

    public void addManaPoint(){
        this.manaPoints++;
    }

    public void takeManaPoints(Integer amount){
        this.manaPoints -= amount;
    }

    @Override
    public String toString() {
        return "Dot at X: "+x+", Y: "+y+" | Life: "+life+
                " | Strength: "+strength+" | Hit Distance: "+hitDistance+
                " | Step Distance: "+stepDistance+" | Mana Points: "+manaPoints;
    }
}
