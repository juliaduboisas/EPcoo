import java.awt.Color;
import java.util.ArrayList;

public class Enemy1 extends Enemy{
    public Enemy1() {
        super(9, Game.INACTIVE);
    }

    public void updateState (long delta, long currentTime, Player player, ArrayList<Projectile> projectilesE1){
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
                    //int free = Game.findFreeIndex(projectiles);
                    int free = Game.findFreeIndex(projectilesE1);

                    //if (free < projectiles.length){
                    if (free < projectilesE1.size()){
                        //projectiles[free].setX(getX());
                        //projectiles[free].setY(getY());
                        //projectiles[free].setVx(Math.cos(getAngle())*0.45);
                        //projectiles[free].setVy(Math.sin(getAngle())*0.45*(-1.0));
                        //projectiles[free].setState(Game.ACTIVE);
                        projectilesE1.get(free).setX(getX());
                        projectilesE1.get(free).setY(getY());
                        projectilesE1.get(free).setVx(Math.cos(getAngle())*0.45);
                        projectilesE1.get(free).setVy(Math.sin(getAngle())*0.45*(-1.0));
                        projectilesE1.get(free).setState(Game.ACTIVE);
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
            GameLib.setColor(Color.CYAN);
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}
