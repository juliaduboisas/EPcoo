import java.awt.Color;

public class hp extends GameElement{
    private int hp;
    
    public hp(int hp) {
        super(440, 680, 0, Game.ACTIVE);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
 
    public void renderHP() {
        GameLib.setColor(Color.RED);
        GameLib.fillRect(100, 100, 100, 30);
        GameLib.setColor(Color.YELLOW);
        GameLib.fillRect(220, 100, 100, 30);
        GameLib.setColor(Color.GREEN);
        GameLib.fillRect(340, 100, 100, 30);
    }
}
