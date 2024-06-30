package coo.ep;

import java.awt.Color;

/********************************************************/
/*                      POWERUP                         */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza o comportamento do powerup.   //
//                                                      //
// Inclui:                                              //
// - métodos                                            //
// 		- instanciação 									//
//		- renderização 									//
//		- place											//
//////////////////////////////////////////////////////////


public class Powerup extends GameElement {
	
	// instanciação
    public Powerup() {
        super(0, 0, 15, Game.INACTIVE);
    }

    // renderização
    public void render() {
        if (getState() == Game.ACTIVE) {
            GameLib.setColor(Color.WHITE);
            GameLib.drawDiamond(getX(), getY(), getRadius());
        }

    }

    // localização e momento de renderização
    public void place(long currentTime) {
        if (this.getState() != Game.ACTIVE) {

            this.setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
            this.setY((Math.random() * (GameLib.HEIGHT / 2)) + GameLib.HEIGHT / 2);
            this.setState(Game.ACTIVE);

        }
    }
}
