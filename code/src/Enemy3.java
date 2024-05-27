import java.awt.Color;
import java.util.ArrayList;

public class Enemy3 extends Enemy{
    public Enemy3() {
        super(9, Game.INACTIVE);
    }

    public void updateState (long delta, long currentTime, Player player, ArrayList<Projectile> projectilesE3){
        if(getState() == Game.EXPLODING){
            if(currentTime > getExplosionEnd()){
                setState(Game.INACTIVE);
            }
        }
        if (getState() == Game.ACTIVE){
            if (getY() > GameLib.HEIGHT + 10){
                setState(Game.INACTIVE);
            }
            else {
                setX(getX()+getV()*Math.cos(getAngle())*delta);
                setY(getY()+getV()*Math.sin(getAngle())*delta*-1.0);
                setAngle(getAngle() + getRv()*delta);
                if (currentTime > getNextShoot() && getY() < player.getY()){
                    int free = Game.findFreeIndex(projectilesE3);

                    if (free < projectilesE3.size()){
                        projectilesE3.get(free).setX(getX());
                        projectilesE3.get(free).setY(getY());
                        projectilesE3.get(free).setVx(Math.cos(getAngle())*0.45);
                        projectilesE3.get(free).setVy(Math.sin(getAngle())*0.45*(-1.0));
                        projectilesE3.get(free).setState(Game.ACTIVE);
                        setNextShoot((long)(currentTime + 200 + Math.random()*500));
                    }
                }
            }
        }
    }

    public void render(long currentTime){
        if (getState() == Game.EXPLODING){
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            GameLib.drawExplosion(getX(), getY(), alpha);
        }

        if (getState() == Game.ACTIVE){
            GameLib.setColor(Color.YELLOW);
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}
