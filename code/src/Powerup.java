import java.awt.Color;

public class Powerup extends GameElement {
    public Powerup() {
        super(0, 0, 15, Game.INACTIVE);
    }

    public void render() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.WHITE);
            GameLib.drawDiamond(getX(), getY(), getRadius());
        }

    }

    public void place(long currentTime) {
        if (this.getState() != Game.ACTIVE) {

            this.setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
            this.setY((Math.random() * (GameLib.HEIGHT / 2)) + GameLib.HEIGHT / 2);
            this.setState(Game.ACTIVE);

        }
    }
}
