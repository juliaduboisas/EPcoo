package coo.ep;

import java.awt.Color;
import java.util.ArrayList;

/********************************************************/
/*                     INIMIGO 1                        */
/********************************************************/

//////////////////////////////////////////////////////////
// Classe que caracteriza o comportamento do inimigo 1  //
// de cor ciano, que apenas desce e atira na vertical   //
// para baixo.                                          //
//                                                      //
// Inclui:                                              //
// - métodos                                            //
// 		- instanciação                                  //
//		- updateState                                   //
//		- renderização                                  //
//////////////////////////////////////////////////////////

public class Enemy1 extends Enemy{
	
	// instanciação
    public Enemy1() {
        super(9, Game.INACTIVE);
    }

    // updateState
    public void updateState (long delta, long currentTime, Player player, ArrayList<Projectile> projectilesE1){
        if(getState() == Game.EXPLODING){												// checa se o inimigo deve estar explodindo
            if(currentTime > getExplosionEnd()){									
                setState(Game.INACTIVE);												// desativa o inimigo
            }
        }
        if (getState() == Game.ACTIVE){													// checa se o inimigo está ativo
            if (getY() > GameLib.HEIGHT + 10){											// checa se o inimigo saiu da tela
                setState(Game.INACTIVE);												// desativa o inimigo
            }
            else {
                setX(getX()+getV()*Math.cos(getAngle())*delta);							// movimentação no eixo x
                setY(getY()+getV()*Math.sin(getAngle())*delta*-1.0);					// movimentação no eixo y
                setAngle(getAngle() + getRv()*delta);									// ângulo
                if (currentTime > getNextShoot() && getY() < player.getY()){			// checa se é o momento de atirar
                    int free = Game.findFreeIndex(projectilesE1);

                    if (free < projectilesE1.size()){
                        projectilesE1.get(free).setX(getX());
                        projectilesE1.get(free).setY(getY());
                        projectilesE1.get(free).setVx(Math.cos(getAngle())*0.45);
                        projectilesE1.get(free).setVy(Math.sin(getAngle())*0.45*(-1.0));
                        projectilesE1.get(free).setState(Game.ACTIVE);					// cria novo projétil
                        setNextShoot((long)(currentTime + 200 + Math.random()*500));	// determina o momento do próximo tiro
                    }
                }
            }
        }
    }

    // renderização
    public void render(long currentTime){
        if (getState() == Game.EXPLODING){												// checa se o inimigo deve explodir
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            GameLib.drawExplosion(getX(), getY(), alpha);
        }

        if (getState() == Game.ACTIVE){													// checa se o inimigo está ativo
            GameLib.setColor(Color.CYAN);												// renderiza o inimigo
            GameLib.drawCircle(getX(), getY(), getRadius());
        }
    }
}