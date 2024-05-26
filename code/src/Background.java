import java.awt.Color;

public class Background {
    private double speed;
    private double count; 
    private double[] X;
    private double[] Y;
    private int size;
    private int w;

    public Background(long count, double speed, int size, int w) {
        this.count = count;
        this.speed = speed;
        this.size = size;
        this.w = w;
        X = new double[size];
        Y = new double[size];
        for (int i = 0; i < size; i++) { 
            X[i] = Math.random() * GameLib.WIDTH;
            Y[i] = Math.random() * GameLib.HEIGHT;
        }
    }

    public void render1(long delta) {

        GameLib.setColor(Color.DARK_GRAY);
        count += speed * delta;

        for (int i = 0; i < size; i++) {
            double newY = (Y[i] + count) % GameLib.HEIGHT;
            
            GameLib.fillRect(X[i], newY, w, w); 
        
        }
    }
    public void render2(long delta) {

        GameLib.setColor(Color.GRAY);
        count = count + speed * delta;

        for (int i = 0; i < size; i++) {
            double newY = (Y[i] + count) % GameLib.HEIGHT;
            GameLib.fillRect(X[i], newY, w, w); 
        
        }
    }

}
