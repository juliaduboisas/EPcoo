import java.awt.Color;
import java.util.ArrayList;

public class Enemy2 extends Enemy{

    public Enemy2() {
        super(12, Game.INACTIVE);
    }

    public void updateState (long delta, long currentTime, Player player, ArrayList<Projectile> projectilesE2){
        if(getState() == Game.EXPLODING){
            if(currentTime > getExplosionEnd()){
                setState(Game.INACTIVE);
            }
        }
        if (getState() == Game.ACTIVE){
            if (getX() < -10 || getX() > GameLib.WIDTH + 10){
                setState(Game.INACTIVE);
            }
            else {
                boolean shootNow = false;
                double previousY = getY();

                setX(getX()+getV()*Math.cos(getAngle())*delta);
                setY(getY()+getV()*Math.sin(getAngle())*delta*-1.0);
                setAngle(getAngle() + getRv()*delta);

                double threshold = GameLib.HEIGHT * 0.30;
                if (previousY < threshold && getY() >= threshold){
                    if (getX() < GameLib.WIDTH / 2) setRv(0.003);
                    else setRv(-0.003);
                }

                if (getRv() > 0 && Math.abs(getAngle() - 3*Math.PI) < 0.05){
                    setRv(0.0);
                    setAngle(3*Math.PI);
                    shootNow = true;
                }

                if (getRv() < 0 && Math.abs(getAngle()) < 0.05){
                    setRv(0.0);
                    setAngle(0.0);
                    shootNow = true;
                }

                if (shootNow){

                    double [] angles = {Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8};
                    //int [] freeArray = Game.findFreeIndex(projectiles, angles.length);
                    int [] freeArray = Game.findFreeIndex(projectilesE2, angles.length);

                    for (int k = 0; k < freeArray.length; k++){

                        int free = freeArray[k];

                        //if (freeArray[k] < projectiles.length){
                        if (free < projectilesE2.size()){

                            double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);

                            //projectiles[free].setX(getX());
                            projectilesE2.get(free).setX(getX());
                            //projectiles[free].setY(getY());
                            projectilesE2.get(free).setY(getY());
                            //projectiles[free].setVx(vx*0.30);
                            projectilesE2.get(free).setVx(vx*0.30);
                            //projectiles[free].setVy(vy*0.30);
                            projectilesE2.get(free).setVy(vy*0.30);
                            //projectiles[free].setState(Game.ACTIVE);
                            projectilesE2.get(free).setState(Game.ACTIVE);

                        }
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
            GameLib.setColor(Color.MAGENTA);
            GameLib.drawDiamond(getX(), getY(), getRadius());
        }
    }


}
