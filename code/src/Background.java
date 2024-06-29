/********************************************************/
/*                  PLANOS DE FUNDO                     */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza as estrelas que formam        //
// os PLANOS DE FUNDO, por meio de duas renderizações   //
//                                                      //
// Incluí:                                              //
// - atributos para velocidade, quantidade, localização //
//   e tamanho                                          //
// - método de instânciação                             //
// - método de renderização                             //
//////////////////////////////////////////////////////////

import java.awt.Color;

public class Background {
    
    // ATRIBUTOS
    private double speed;
    private double count; 
    private double[] X;
    private double[] Y;
    private int size;
    private int w;

    // MÉTODOS

    // método de iniciação de instância
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

    // método de renderização, recebe os paramêtros delta e a cor que dece ser usada
    public void render(long delta, Color color) {

        GameLib.setColor(color);
        count += speed * delta;

        for (int i = 0; i < size; i++) {
            double newY = (Y[i] + count) % GameLib.HEIGHT;
            
            GameLib.fillRect(X[i], newY, w, w); 
        
        }
    }
}
