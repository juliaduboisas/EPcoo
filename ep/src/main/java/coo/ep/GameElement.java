package coo.ep;

/********************************************************/
/*            ELEMENTO DO JOGO - ABSTRATO               */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que abstrai os elementos do jogo              //
//                                                      //
// Incluí:                                              //
// - atributos                                          //
//      - tamanho x                                     //
//      - tamanho y                                     //
//      - raio                                          //
//      - estado                                        //
// - métodos                                            //
//      - instanciação                                  //
//      - getters e setters                             //
//          - x                                         //
//          - y                                         //
//          - raio                                      //
//          - estado                                    //
//      - isHit                                         //
//////////////////////////////////////////////////////////


public abstract class GameElement {
	
    // ATRIBUTOS
    private double x, y, radius;
    private int state;

   // MÉTODOS
   // instanciação
   public GameElement(double x, double y, double radius, int state) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.state = state;
    }

   // getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    // setters
    public int getState() {
        return state;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    public void setState(int state) {
        this.state = state;
    }
}
