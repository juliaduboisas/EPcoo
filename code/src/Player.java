import java.awt.Color;

public class Player extends GameElement {
    private double vx, vy;
    private double explosionStart, explosionEnd;
    private long nextShot;

    public Player() {
        super(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 12.0, Game.ACTIVE);
        vx = 0.25;
        vy = 0.25;
        nextShot = System.currentTimeMillis();
    }

    public void updateState(long delta, long currentTime) {
        if (getState() == Game.EXPLODING) {
            if (currentTime > explosionEnd) {
                setState(Game.ACTIVE);
            }
        }
    }

    public void processInput(long delta, long currentTime) {
        if (getState() == Game.ACTIVE) {
            if (GameLib.iskeyPressed(GameLib.KEY_UP)) setY(getY() - delta * vy);
            if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) setY(getY() + delta * vy);
            if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) setX(getX() - delta * vx);
            if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) setX(getX() + delta * vx);
            if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
                if (currentTime > nextShot) {
                    // Lógica para disparar projétil
                    nextShot = currentTime + 200;
                }
            }
        }
    }

    public void checkCollisions(Projectile[] projectiles, Enemy[] enemies1, Enemy[] enemies2, long currentTime) {
        // Lógica de verificação de colisões
    }

    public void render() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.BLUE);
            GameLib.drawPlayer(getX(), getY(), getRadius());
        } else if (getState() == Game.EXPLODING) {
            GameLib.setColor(Color.RED);
            GameLib.drawExplosion(getX(), getY(), getRadius());
        }
    }
}