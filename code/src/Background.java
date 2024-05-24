import java.awt.Color;
public class Background {
    private double speed;
    private double offset;
    private int count;
    private double[][] positions;

    public Background(int count, double speed) {
        this.count = count;
        this.speed = speed;
        this.offset = 0.0;
        positions = new double[count][2];
        for (int i = 0; i < count; i++) {
            positions[i][0] = Math.random() * GameLib.WIDTH;
            positions[i][1] = Math.random() * GameLib.HEIGHT;
        }
    }

    public void render() {
        GameLib.setColor(Color.DARK_GRAY);
        for (int i = 0; i < count; i++) {
            GameLib.drawCircle(positions[i][0], (positions[i][1] + offset) % GameLib.HEIGHT, 2);
        }
        offset += speed;
        if (offset > GameLib.HEIGHT) {
            offset -= GameLib.HEIGHT;
        }
    }
}