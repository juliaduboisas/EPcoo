
import java.awt.Color;
public class Enemy extends GameElement {
    private double vx, vy, angle, rv;
    private double explosionStart, explosionEnd;
    private long nextShoot;

    public Enemy(int type) {
        super(0, 0, type == 1 ? 9.0 : 12.0, Game.INACTIVE);
    }

    public void updateState(long delta, long currentTime, Player player, Projectile[] projectiles) {
        if (getState() == Game.EXPLODING) {
            if (currentTime > explosionEnd) {
                setState(Game.INACTIVE);
            }
        } else if (getState() == Game.ACTIVE) {
            setX(getX() + vx * Math.cos(angle) * delta);
            setY(getY() + vy * Math.sin(angle) * delta * -1.0);
            angle += rv * delta;

            if (currentTime > nextShoot && getY() < player.getY()) {
                // Lógica para disparar projétil
                nextShoot = currentTime + 500;
            }
        }
    }

    public void reset(int type, long currentTime) {
        setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
        setY(-10.0);
        vx = type == 1 ? 0.20 + Math.random() * 0.15 : 0.30;
        vy = 0.20 + Math.random() * 0.15;
        angle = 0;
        rv = type == 1 ? 0.0 : Math.random() * 0.2 - 0.1;
        setRadius(type == 1 ? 9.0 : 12.0);
        setState(Game.ACTIVE);
        explosionStart = currentTime;
        explosionEnd = explosionStart + 500;
    }

    public void render() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.CYAN);
            GameLib.drawCircle(getX(), getY(), getRadius());
        } else if (getState() == Game.EXPLODING) {
            GameLib.setColor(Color.RED);
            GameLib.drawExplosion(getX(), getY(), getRadius());
        }
    }
}