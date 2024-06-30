package coo.ep;

import java.awt.Color;
import java.util.ArrayList;

/********************************************************/
/*                      JOGADOR                         */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza o comportamento do jogador.   //
//                                                      //
// Inclui:                                              //
// - atributos                                          //
//		- velocidade x e y                              //
//		- inicio e fim da explosão                      //
//		- próximo tiro									//
//		- ativação do powerup							//
//		- momento da última ativação do powerup			//
// - métodos                                            //
// 		- instanciação 									//
//		- setters										//
//			- setPowerupEnabled							//
//			- resetLastPowerupStartTime					//
//			- setNextShot                               //
//		- updateState                                   //
//		- renderização                                  //
//////////////////////////////////////////////////////////


public class Player extends GameElement {
	
	// ATRIBUTOS
    private double vx, vy;
    private double explosionStart, explosionEnd;
    private long nextShot;
    private String powerupEnabled;
    private long lastPowerupStartTime;

    // MÉTODOS
    // instanciação
    public Player() {
        super(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 12.0, Game.ACTIVE);
        this.vx = 0.25;
        this.vy = 0.25;
        this.nextShot = System.currentTimeMillis();
        this.explosionStart = 0;
        this.explosionEnd = 0;
        this.powerupEnabled = "false";
        this.lastPowerupStartTime = System.currentTimeMillis();

    }

    // setters
    public void setPowerupEnabled(String powerupEnabled) {

        this.powerupEnabled = powerupEnabled;
    }

    public void resetLastPowerupStartTime() {
        this.lastPowerupStartTime = System.currentTimeMillis();
    }
    
    public void setNextShot(long nextShot) {
        this.nextShot = nextShot;
    }
    
    // getters
    public String getPowerupEnabled() {
        return this.powerupEnabled;
    }

    public long getLastPowerupStartTime() {
        return lastPowerupStartTime;
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

    // checador de colisões
    public String checkCollisions(ArrayList<Projectile> projectilesP,
            ArrayList<Projectile> projectilesE1,
            ArrayList<Projectile> projectilesE2,
            ArrayList<Projectile> projectilesE3,
            ArrayList<Enemy1> enemies1,
            ArrayList<Enemy2> enemies2,
            ArrayList<Enemy3> enemies3,
            Powerup powerup,
            long currentTime) {
        // Lógica de verificação de colisões
        // player vs projetil inimigo1
        if (getState() == Game.ACTIVE) {

            for (int i = 0; i < projectilesE1.size(); i++) {
                double dx = projectilesE1.get(i).getX() - getX();
                double dy = projectilesE1.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (projectilesE1.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    return "hit";
                }
            }
            // player vs projetil inimigo2
            for (int i = 0; i < projectilesE2.size(); i++) {
                double dx = projectilesE2.get(i).getX() - getX();
                double dy = projectilesE2.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (projectilesE2.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    return "hit";
                }
            }
            // player vs projetil inimigo3
            for (int i = 0; i < projectilesE3.size(); i++) {
                double dx = projectilesE3.get(i).getX() - getX();
                double dy = projectilesE3.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (projectilesE3.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    return "hit";
                }
            }
            

            // player vs inimigo1
            for (int i = 0; i < enemies1.size(); i++) {
                double dx = enemies1.get(i).getX() - getX();
                double dy = enemies1.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (enemies1.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    return "hit";
                }
            }
            // player vs inimigo2
            for (int i = 0; i < enemies2.size(); i++) {
                double dx = enemies2.get(i).getX() - getX();
                double dy = enemies2.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (enemies2.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    return "hit";
                }
            }
            // Verifica se o powerup foi coletado
            if (powerup.getState() == Game.ACTIVE) {
                double dx = powerup.getX() - getX();
                double dy = powerup.getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (powerup.getRadius() + getRadius()) * 0.8) {
                    return "powerup";
                }
            }
            // player vs inimigo3
            for (int i = 0; i < enemies3.size(); i++) {
                double dx = enemies3.get(i).getX() - getX();
                double dy = enemies3.get(i).getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < (enemies3.get(i).getRadius() + getRadius()) * 0.8) {
                    setState(Game.EXPLODING);
                    this.explosionStart = currentTime;
                    this.explosionEnd = explosionStart + 2000;
                    return "hit";
                }
            }

            // projetil player vs inimigos
            for (int i = 0; i < projectilesP.size(); i++) {
                // projetil player vs inimigo 1
                for (int j = 0; j < enemies1.size(); j++) {
                    if (enemies1.get(j).getState() == Game.ACTIVE) {
                        double dx = projectilesP.get(i).getX() - enemies1.get(j).getX();
                        double dy = projectilesP.get(i).getY() - enemies1.get(j).getY();
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        if (distance < enemies1.get(j).getRadius()) {
                            enemies1.get(j).setState(Game.EXPLODING);
                            enemies1.get(j).setExplosionStart(currentTime);
                            enemies1.get(j).setExplosionEnd(currentTime + 500);
                        }
                    }
                }
                // projetil player vs inimigo 2
                for (int j = 0; j < enemies2.size(); j++) {
                    if (enemies2.get(j).getState() == Game.ACTIVE) {
                        double dx = projectilesP.get(i).getX() - enemies2.get(j).getX();
                        double dy = projectilesP.get(i).getY() - enemies2.get(j).getY();
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        if (distance < enemies2.get(j).getRadius()) {
                            enemies2.get(j).setState(Game.EXPLODING);
                            enemies2.get(j).setExplosionStart(currentTime);
                            enemies2.get(j).setExplosionEnd(currentTime + 500);
                        }
                    }
                }
                // projetil player vs inimigo 3
                for (int j = 0; j < enemies3.size(); j++) {
                    if (enemies3.get(j).getState() == Game.ACTIVE) {
                        double dx = projectilesP.get(i).getX() - enemies3.get(j).getX();
                        double dy = projectilesP.get(i).getY() - enemies3.get(j).getY();
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        if (distance < enemies3.get(j).getRadius()) {
                            enemies3.get(j).setState(Game.EXPLODING);
                            enemies3.get(j).setExplosionStart(currentTime);
                            enemies3.get(j).setExplosionEnd(currentTime + 500);
                        }
                    }
                }
            }
        }
        return "none";
    }

    // atualização de estado
    public void updateState(long currentTime) {
        if (getState() == Game.EXPLODING) {
            if (currentTime > explosionEnd) {
                setState(Game.ACTIVE);
            }
        }
    }

    // renderização
    public void render(long currentTime, HP hpBar) {
    	if (getState() == Game.EXPLODING && hpBar.getHp() > 0){									// checa se o player foi atingido
            double alpha = (currentTime - explosionStart) / (explosionEnd - explosionStart);	// e como piscar
            if ((int)(alpha * 10) % 2 == 0){
                GameLib.setColor(Color.WHITE);
            } else {
                GameLib.setColor(Color.BLUE);
            }
            GameLib.drawPlayer(getX(), getY(), getRadius());
            
        } else if (getState() == Game.EXPLODING && hpBar.getHp() == 0){							// checa a última vida
            double alpha = (currentTime - explosionStart) / (explosionEnd - explosionStart);
            GameLib.drawExplosion(getX(), getY(), alpha);
        }
        else {																					// renderiza o jogador
            GameLib.setColor(Color.BLUE);
            GameLib.drawPlayer(getX(), getY(), getRadius());
        }
    }
}