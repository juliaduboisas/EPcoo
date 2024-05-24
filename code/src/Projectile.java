import java.awt.Color;

public class Projectile extends GameElement {
    private double vx, vy;

    public Projectile() {
        super(0, 0, 2.0, Game.INACTIVE);
    }

    public void updateState(long delta) {
        if (getState() == Game.ACTIVE) {
            setX(getX() + vx * delta);
            setY(getY() + vy * delta);
            if (getY() < 0) {
                setState(Game.INACTIVE);
            }
        }
    }

    public void render() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.WHITE);
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}