/********************************************************/
/*                        HP                            */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que define o comportamento da barra de HP     //
//                                                      //
// Incluí:                                              //
// - atributos                                          //
//      - int hp                                        //
// - métodos                                            //
//      - instanciação                                  //
//      - getter e setter                               //
//      - reduceHP                                      //
//      - renderHP                                      //
//////////////////////////////////////////////////////////


package coo.ep;

import java.awt.Color;

public class HP extends GameElement {
	
	// ATRIBUTOS
    private int hp;

    // MÉTODOS
    // instanciação
    public HP(int hp) {
        super(440, 680, 0, Game.ACTIVE);
        this.hp = hp;
    }

    // redução de HP
    public void reduceHP() {
        if (this.hp == 0) {
            System.exit(0);
        } else {
            this.hp = this.hp - 1;
        }

    }

    // getter
    public int getHp() {
        return hp;
    }

    // setter
    public void setHp(int hp) {
        this.hp = hp;
    }

    // renderização
    public void renderHP() {
        if (this.hp > 0) {
            GameLib.setColor(Color.RED);
            GameLib.drawHeart(90, 30);

        }
        if (this.hp > 1) {
            GameLib.setColor(Color.YELLOW);
            GameLib.drawHeart(60, 30);

        }
        if (this.hp > 2) {
            GameLib.setColor(Color.GREEN);
            GameLib.drawHeart(30, 30);

        }
    }
}
