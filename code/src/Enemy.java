

public abstract class Enemy extends GameElement {
    private double v, angle, rv;
    private double explosionStart, explosionEnd;
    private long nextShoot;

    public Enemy(double radius, int state) {
        super(0, 0, radius, state);
    }

    public void setExplosionStart(double explosionStart) {
        this.explosionStart = explosionStart;
    }

    public void setExplosionEnd(double explosionEnd) {
        this.explosionEnd = explosionEnd;
    }

    public double getExplosionStart() {
        return explosionStart;
    }

    public double getExplosionEnd() {
        return explosionEnd;
    }

    public double getV() {
        return v;
    }

    public double getAngle() {
        return angle;
    }

    public double getRv() {
        return rv;
    }

    public double setAngle(double angle) {
        return this.angle = angle;
    }

    public void setRv(double rv) {
        this.rv = rv;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setNextShoot(long nextShoot) {
        this.nextShoot = nextShoot;
    }

    public long getNextShoot() {
        return nextShoot;
    }


 
}