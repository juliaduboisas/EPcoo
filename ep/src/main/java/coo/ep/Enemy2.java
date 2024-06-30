package coo.ep;

import java.awt.Color;
import java.util.ArrayList;

/********************************************************/
/*                     INIMIGO 2                        */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza o comportamento do inimigo 2  //
// de cor magenta, que apenas desce, faz uma curva e    //
// atira.                                               //
//                                                      //
// Inclui:                                              //
// - métodos                                            //
// 		- instanciação                                  //
//		- updateState                                   //
//		- renderização                                  //
//////////////////////////////////////////////////////////

public class Enemy2 extends Enemy{

	// instanciação
    public Enemy2() {
        super(12, Game.INACTIVE);
    }

    // atualização de estado
    public void updateState (long delta, long currentTime, Player player, ArrayList<Projectile> projectilesE2){
        if(getState() == Game.EXPLODING){											// checa se o inimigo foi atingido
            if(currentTime > getExplosionEnd()){
                setState(Game.INACTIVE);											// desativa o inimigo
            }
        }
        if (getState() == Game.ACTIVE){												// checa se o inimigo está ativo
            if (getX() < -10 || getX() > GameLib.WIDTH + 10){						// checa se o inimigo saiu da tela
                setState(Game.INACTIVE);											// desativa o inimigo
            }
            else {
                boolean shootNow = false;
                double previousY = getY();

                // movimentação
                setX(getX()+getV()*Math.cos(getAngle())*delta);
                setY(getY()+getV()*Math.sin(getAngle())*delta*-1.0);
                setAngle(getAngle() + getRv()*delta);

                double threshold = GameLib.HEIGHT * 0.30;
                if (previousY < threshold && getY() >= threshold){					// decide para que lado virar
                    if (getX() < GameLib.WIDTH / 2) setRv(0.003);
                    else setRv(-0.003);
                }

                if (getRv() > 0 && Math.abs(getAngle() - 3*Math.PI) < 0.05){
                    setRv(0.0);
                    setAngle(3*Math.PI);
                    shootNow = true;												// atira
                }

                if (getRv() < 0 && Math.abs(getAngle()) < 0.05){
                    setRv(0.0);
                    setAngle(0.0);
                    shootNow = true;												// atira
                }

                if (shootNow){														// determina a rota dos projéteis

                    double [] angles = {Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8};
                    int [] freeArray = Game.findFreeIndex(projectilesE2, angles.length);

                    for (int k = 0; k < freeArray.length; k++){

                        int free = freeArray[k];

                        if (free < projectilesE2.size()){

                            double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);

                            projectilesE2.get(free).setX(getX());
                            projectilesE2.get(free).setY(getY());
                            projectilesE2.get(free).setVx(vx*0.30);
                            projectilesE2.get(free).setVy(vy*0.30);
                            projectilesE2.get(free).setState(Game.ACTIVE);
                        }
                    }
                }            
            }
        }
    }

    // renderização
    public void render(long currentTime){
        if (getState() == Game.EXPLODING){											// checa se o inimigo deve explodir
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            GameLib.drawExplosion(getX(), getY(), alpha);
        }

        if (getState() == Game.ACTIVE){												// checa se o inimigo está ativo
            GameLib.setColor(Color.MAGENTA);										// renderiza o inimigo
            GameLib.drawDiamond(getX(), getY(), getRadius());
        }
    }


}
