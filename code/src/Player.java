import java.awt.Color;
import java.util.ArrayList;

public class Player extends GameElement {
    private double vx, vy;
    private double explosionStart, explosionEnd;
    private long nextShot;

    public Player() {
        super(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 12.0, Game.ACTIVE);
        this.vx = 0.25;
        this.vy = 0.25;
        this.nextShot = System.currentTimeMillis();
        this.explosionStart = 0;
        this.explosionEnd = 0;

    }
    
    public void checkCollisions(ArrayList<Projectile> projectilesP, 
                            ArrayList<Projectile> projectilesE1, 
                            ArrayList<Projectile> projectilesE2, 
                            ArrayList<Enemy1> enemies1, 
                            ArrayList<Enemy2> enemies2, 
                            long currentTime) {
        // Lógica de verificação de colisões
        //player vs projetil inimigo1
        if (getState() == Game.ACTIVE) {
            
            //for (int i = 0; i < projectileE1.length; i++) {
            for (int i = 0; i < projectilesE1.size(); i++) {
                //double dx = projectileE1[i].getX() - getX();
                double dx = projectilesE1.get(i).getX() - getX();
                //double dy = projectileE1[i].getY() - getY();
                double dy = projectilesE1.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                //if (distance < (projectileE1[i].getRadius() + getRadius()) * 0.8) {
                if (distance < (projectilesE1.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    //projectileE1[i].setState(Game.INACTIVE); 
                }
            }
            //player vs projetil inimigo2
            for (int i = 0; i < projectilesE2.size(); i++) {
                //double dx = projectileE1[i].getX() - getX();
                double dx = projectilesE2.get(i).getX() - getX();
                //double dy = projectileE1[i].getY() - getY();
                double dy = projectilesE2.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                //if (distance < (projectileE1[i].getRadius() + getRadius()) * 0.8) {
                if (distance < (projectilesE2.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    //projectileE1[i].setState(Game.INACTIVE); 
                }
            }
            //player vs inimigo1
            //for (int i = 0; i < enemies1.length; i++) {
            for (int i = 0; i < enemies1.size(); i++) {
                //double dx = enemies1[i].getX() - getX();
                double dx = enemies1.get(i).getX() - getX();
                //double dy = enemies1[i].getY() - getY();
                double dy = enemies1.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                //if (distance < (enemies1[i].getRadius() + getRadius()) * 0.8) {
                if (distance < (enemies1.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    //enemies1[i].setState(Game.INACTIVE);
                }
            }
            //player vs inimigo2
            //for (int i = 0; i < enemies2.length; i++) {
            for (int i = 0; i < enemies2.size(); i++) {
                //double dx = enemies2[i].getX() - getX();
                double dx = enemies2.get(i).getX() - getX();
                //double dy = enemies2[i].getY() - getY();
                double dy = enemies2.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                //if (distance < (enemies2[i].getRadius() + getRadius()) * 0.8) {
                if (distance < (enemies2.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    //enemies2[i].setState(Game.INACTIVE);
                }
            }
            //projetil player vs inimigo 1
            //for (int i = 0; i < projectileP.length; i++) {
            for (int i = 0; i < projectilesP.size(); i++) {
                //for (int j = 0; j < enemies1.length; j++) {
                for (int j = 0; j < enemies1.size(); j++) {
                    //if (enemies1[j].getState() == Game.ACTIVE) {
                    if (enemies1.get(j).getState() == Game.ACTIVE) {
                        //double dx = projectileP[i].getX() - enemies1[j].getX();
                        double dx = projectilesP.get(i).getX() - enemies1.get(j).getX();
                        //double dy = projectileP[i].getY() - enemies1[j].getY();
                        double dy = projectilesP.get(i).getY() - enemies1.get(j).getY();
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        //if (distance < enemies1[j].getRadius()) {
                        if (distance < enemies1.get(j).getRadius()) {
                            //projectileP[i].setState(Game.INACTIVE);
                            //enemies1[j].setState(Game.EXPLODING);
                            enemies1.get(j).setState(Game.EXPLODING);
                            //enemies1[j].setExplosionStart(currentTime);
                            enemies1.get(j).setExplosionStart(currentTime);
                            //enemies1[j].setExplosionEnd(currentTime + 500);
                            enemies1.get(j).setExplosionEnd(currentTime + 500);
                        }
                    }
                }
                //projetil player vs inimigo 2
                //for (int j = 0; j < enemies2.length; j++) {
                for (int j = 0; j < enemies2.size(); j++) {
                    //if (enemies2[j].getState() == Game.ACTIVE) {
                    if (enemies2.get(j).getState() == Game.ACTIVE) {
                        //double dx = projectileP[i].getX() - enemies2[j].getX();
                        double dx = projectilesP.get(i).getX() - enemies2.get(j).getX();
                        //double dy = projectileP[i].getY() - enemies2[j].getY();
                        double dy = projectilesP.get(i).getY() - enemies2.get(j).getY();
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        //if (distance < enemies2[j].getRadius()) {
                        if (distance < enemies2.get(j).getRadius()) {
                            //projectileP[i].setState(Game.INACTIVE);
                            //enemies2[j].setState(Game.EXPLODING);
                            enemies2.get(j).setState(Game.EXPLODING);
                            //enemies2[j].setExplosionStart(currentTime);
                            enemies2.get(j).setExplosionStart(currentTime);
                            //enemies2[j].setExplosionEnd(currentTime + 500);
                            enemies2.get(j).setExplosionEnd(currentTime + 500);
                        }
                    }
                }
            }
        }
    }

    public void updateState(long currentTime) {
        if (getState() == Game.EXPLODING) {
            if (currentTime > explosionEnd) {
                setState(Game.ACTIVE);
            }
        }
    }

    public double getVX() {
        return vx;
    }

    public double getVY() {
        return vy;
    }   

    public long getNextShot() {
        return nextShot;
    }

    public void setNextShot(long nextShot) {
        this.nextShot = nextShot;
    }

    public void render(long currentTime) {

        if (getState() == Game.EXPLODING) {
            double alpha = (currentTime - explosionStart) / (explosionEnd - explosionStart);
            GameLib.drawExplosion(getX(), getY(), alpha);
        }
        else {
            GameLib.setColor(Color.BLUE);
            GameLib.drawPlayer(getX(), getY(), getRadius());
        }
    }
}