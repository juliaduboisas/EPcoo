import java.awt.Color;

public class HP extends GameElement {
    private int hp;

    public HP(int hp) {
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
            GameLib.fillRect(80, 7.5, 160, 15);

        }
        if (this.hp > 1) {
            GameLib.setColor(Color.YELLOW);
            GameLib.fillRect(240, 7.5, 160, 15);

        }
        if (this.hp > 2) {
            GameLib.setColor(Color.GREEN);
            GameLib.fillRect(400, 7.5, 160, 15);

        }
    }
}
