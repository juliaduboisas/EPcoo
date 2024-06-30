package coo.ep;

import java.awt.Color;

/********************************************************/
/*                  PLANOS DE FUNDO                     */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza as estrelas que formam        //
// os PLANOS DE FUNDO, por meio de duas renderizações   //
//                                                      //
// Inclui:                                              //
// - atributos para velocidade, quantidade, localização //
//   e tamanho                                          //
// - método de instânciação                             //
// - método de renderização                             //
//////////////////////////////////////////////////////////

public class Background {
	
    // ATRIBUTOS
    private double speed;
    private double count; 
    private double[] X;
    private double[] Y;
    private int size;
    private int w;
    private Color color;

    // MÉTODOS

    // método de iniciação de instância
    public Background(long count, double speed, int size, int w, Color color) {
        this.count = count;
        this.speed = speed;
        this.size = size;
        this.w = w;
        X = new double[size];
        Y = new double[size];
        this.color = color;
        for (int i = 0; i < size; i++) { 
            X[i] = Math.random() * GameLib.WIDTH;
            Y[i] = Math.random() * GameLib.HEIGHT;
        }
    }

    // método de renderização, recebe os paramêtros delta e a cor que dece ser usada
    public void render(long delta) {

        if(this.color == Color.DARK_GRAY){
            GameLib.setColor(Color.DARK_GRAY);
            count += speed * delta;
    
            for (int i = 0; i < size; i++) {
                double newY = (Y[i] + count) % GameLib.HEIGHT;
                
                GameLib.fillRect(X[i], newY, w, w); 
            }    
        }

        if(this.color == Color.GRAY){
            GameLib.setColor(Color.GRAY);
            count = count + speed * delta;
    
            for (int i = 0; i < size; i++) {
                double newY = (Y[i] + count) % GameLib.HEIGHT;
                GameLib.fillRect(X[i], newY, w, w); 
            }
        }
    }

}
