import java.awt.Color;
import java.util.ArrayList;

public class hp extends GameElement {
    private int hp;

    public hp(int hp) {
        super(440, 680, 0, Game.ACTIVE);
        this.hp = hp;
    }

    public void reduceHP() {
        if (this.hp == 0) {
            System.exit(0);
        } else {
            this.hp = this.hp - 1;
        }

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void renderHP() {
        if (this.hp > 0) {
            GameLib.setColor(Color.RED);
            GameLib.fillRect(100, 100, 100, 30);

        }
        if (this.hp > 1) {
            GameLib.setColor(Color.YELLOW);
            GameLib.fillRect(220, 100, 100, 30);

        }
        if (this.hp > 2) {
            GameLib.setColor(Color.GREEN);
            GameLib.fillRect(340, 100, 100, 30);

        }
    }
}
