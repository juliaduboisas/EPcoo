/********************************************************/
/*                INIMIGOS - ABSTRATO                   */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que abstrai os inimigos                       //
//                                                      //
// Incluí:                                              //
// - atributos                                          //
//      - velocidade                                    //
//      - angulo                                        //
//      - rotação                                       //
//      - explosionStart e explosionEnd                 //
//      - nextShoot                                     //
// - métodos                                            //
//      - instanciação                                  //
//      - getters e setters                             //
//          - explosionStart                            //
//          - explosionEnd                              //
//          - V                                         //
//          - Angle                                     //
//          - Rv                                        //
//          - nextShoot                                 //
//////////////////////////////////////////////////////////


public abstract class Enemy extends GameElement {

    // ATRIBUTOS
    private double v, angle, rv;                                // v = velocidade; angle = angulo; rv = velocidade de rotação
    private double explosionStart, explosionEnd;                // momento de início e fim das explosões
    private long nextShoot;                                     // momento do próximo tiro


    // MÉTODOS
    // instanciação
    public Enemy(double radius, int state) {
        super(0, 0, radius, state);
    }

    // setters
    public void setExplosionStart(double explosionStart) {
        this.explosionStart = explosionStart;
    }

    public void setExplosionEnd(double explosionEnd) {
        this.explosionEnd = explosionEnd;
    }

    public double setAngle(double angle) {
        return this.angle = angle;
    }

    public void setRv(double rv) {
        this.rv = rv;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setNextShoot(long nextShoot) {
        this.nextShoot = nextShoot;
    }

    // getters
    public double getExplosionStart() {
        return explosionStart;
    }

    public double getExplosionEnd() {
        return explosionEnd;
    }

    public double getV() {
        return v;
    }

    public double getAngle() {
        return angle;
    }

    public double getRv() {
        return rv;
    }

    public long getNextShoot() {
        return nextShoot;
    }

}