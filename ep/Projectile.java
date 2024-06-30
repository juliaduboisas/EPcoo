/********************************************************/
/*                     PROJÉTEIS                        */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que define o comportamento dos projéteis      //
//                                                      //
// Incluí:                                              //
// - atributos                                          //
//      - velocidade no eixo x (vx)                     //
//      - velocidade no eixo y (vy)                     //
// - métodos                                            //
//      - instanciação                                  //
//      - getters e setters                             //
//          - x                                         //
//          - y                                         //
//          - raio                                      //
//          - estado                                    //
//      - isHit                                         //
//////////////////////////////////////////////////////////

package coo.ep;

import java.awt.Color;

public class Projectile extends GameElement {
	
    // ATRIBUTOS
    private double vx, vy;

    // MÉTODOS
    // instanciação
    public Projectile() {
        super(0, 0, 2.0, Game.INACTIVE);
        this.vx = 0;
        this.vy = 0;
    }

    // projéteis do jogador
    public void updateStateP(long delta) {
        if (getState() == Game.ACTIVE) {
            if (getY() < 0) {
                setState(Game.INACTIVE);
            } else {
                setX(getX() + vx * delta);
                setY(getY() + vy * delta);
            }
        }
    }

    // projéteis do inimigo
    public void updateStateE(long delta) {
        if (getState() == Game.ACTIVE) {
            if (getY() > GameLib.HEIGHT) {
                setState(Game.INACTIVE);
            } else {
                setX(getX() + vx * delta);
                setY(getY() + vy * delta);
            }
        }
    }

    // setters
    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    // renderizações
    	// projéteis do jogador
    public void renderP(String powerupEnabled) {
        if (getState() == Game.ACTIVE) {
            if (powerupEnabled == "powerup") {
                GameLib.setColor(Color.ORANGE);
            } else {
                GameLib.setColor(Color.GREEN);
            }
            GameLib.drawLine(getX(), getY() - 5, getX(), getY() + 5);
            GameLib.drawLine(getX() - 1, getY() - 3, getX() - 1, getY() + 3);
            GameLib.drawLine(getX() + 1, getY() - 3, getX() + 1, getY() + 3);
        }
    }
    
    	// projéteis do inimigo
    public void renderE() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.RED);
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}