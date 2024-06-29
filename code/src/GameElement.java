public abstract class GameElement {
    private double x, y, radius;
    private int state;

    public GameElement(double x, double y, double radius, int state) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.state = state;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isHit(GameElement other){
        
    }
}
