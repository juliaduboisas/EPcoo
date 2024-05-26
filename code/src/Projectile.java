import java.awt.Color;

public class Projectile extends GameElement {
    private double vx, vy;

    public Projectile() {
        super(0, 0, 2.0, Game.INACTIVE);
        this.vx = 0;
        this.vy = 0;
    }
    //estado player
    public void updateStateP(long delta) {
        if (getState() == Game.ACTIVE) {
            if (getY() < 0){
                setState(Game.INACTIVE);
            } else {
                setX(getX() + vx * delta);
                setY(getY() + vy * delta);
            }
        }
    }

    //estado inimigo
    public void updateStateE(long delta) {
        if (getState() == Game.ACTIVE) {
            if (getY() > GameLib.HEIGHT){
                setState(Game.INACTIVE);
            } else {
                setX(getX() + vx * delta);
                setY(getY() + vy * delta);
            }
        }
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void renderP() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.GREEN);
            GameLib.drawLine(getX(), getY()-5, getX(), getY()+5);
            GameLib.drawLine(getX()-1, getY()-3, getX()-1, getY()+3);
            GameLib.drawLine(getX()+1, getY()-3, getX()+1, getY()+3);
        }
    }

    public void renderE() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.RED);
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}