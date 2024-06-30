package coo.ep;

import java.awt.Color;
import java.util.ArrayList;

/********************************************************/
/*                     INIMIGO 3                        */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza o comportamento do inimigo 3  //
// de cor amarela, que apenas desce em zigue-zague e    //
// atira dois projéteis em ângulo.                      //
//                                                      //
// Inclui:                                              //
// - atributo: direção                                  //
// - métodos                                            //
// 		- instanciação                                  //
//		- updateState                                   //
//		- renderização                                  //
//////////////////////////////////////////////////////////

public class Enemy3 extends Enemy{
	
	// ATRIBUTO
    private boolean direction = false; // TRUE vai para esquerda, FALSE vai para direita

    // MÉTODOS
    // instanciação
    public Enemy3() {
        super(9, Game.INACTIVE);
        direction = Math.random() > 0.5;
    }

    // atualização de estado
    public void updateState (long delta, long currentTime, Player player, ArrayList<Projectile> projectilesE3){
        if(getState() == Game.EXPLODING){														// checa se o inimigo foi atingido
            if(currentTime > getExplosionEnd()){
                setState(Game.INACTIVE);														// desativa o inimigo
            }
        }
        if (getState() == Game.ACTIVE){															// checa se o inimigo está ativo
            if (getY() > GameLib.HEIGHT + 10){													// checa se o inimigo saiu da tela
                setState(Game.INACTIVE);														// desativa o inimigo
            }
            else {

            	// movimentação
                double deltaX = (getV()*Math.sin(getAngle())*delta) * (direction ? -1 : 1);		// a variável direction decide para
                double newX;																	// qual lado o inimigo irá
                double newY = getY()+getV()*Math.sin(getAngle())*delta*-1.0;
                double newAngle = getAngle() + getRv()*delta;
                
                
                double thresholdLeft = 0.0;
                double thresholdRight = GameLib.WIDTH;

                if((getX()+deltaX)>thresholdRight) {
                	double sobra = thresholdRight - getX();
                	newX = thresholdRight - (deltaX - sobra);
                	direction = !direction;
                }
                else if((getX()+deltaX)<thresholdLeft) {
                	newX = -(deltaX) - getX();
                	direction = !direction;
                } else newX = getX() + deltaX;
                
                setX(newX); 
                setY(newY);
                setAngle(newAngle);
                
                // tiros
                if (currentTime > getNextShoot() && getY() < player.getY()){
                    double [] angles = {Math.PI/2 + Math.PI/8, Math.PI/2 - Math.PI/8};		// determina o ângulo dos projéteis
                    int [] freeArray = Game.findFreeIndex(projectilesE3, angles.length);
                    
                    for (int k = 0; k < freeArray.length; k++){

                        int free = freeArray[k];

                        if (free < projectilesE3.size()){

                            double a = angles[k];
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);    

                            projectilesE3.get(free).setX(getX());
                            projectilesE3.get(free).setY(getY());
                            projectilesE3.get(free).setVx(vx);
                            projectilesE3.get(free).setVy(vy);
                            projectilesE3.get(free).setState(Game.ACTIVE);
                            setNextShoot((long)(currentTime + Math.random()*500));
                        }
                    }
                }
            }
        }
    }

    // renderização
    public void render(long currentTime){
        if (getState() == Game.EXPLODING){													// checa se o inimigo deve explodir
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            GameLib.drawExplosion(getX(), getY(), alpha);
        }

        if (getState() == Game.ACTIVE){														// checa se o inimigo está ativo
            GameLib.setColor(Color.YELLOW);													// renderiza o inimigo
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}
