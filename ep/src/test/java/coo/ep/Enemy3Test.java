package coo.ep;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class Enemy3Test {

	@Test
	void testEnemy3() {
		Player p = new Player();
		Enemy3 e = new Enemy3();
		ArrayList<Projectile> projectiles = new ArrayList<>();
		e.setState(Game.ACTIVE);
		e.setV(0.20 + Math.random() * 0.15);
		assertNotNull(e);
		assertEquals(0.0, e.getX());
		assertEquals(0.0, e.getAngle());
		
		e.updateState(500, 0, p, projectiles);
		e.updateState(500, 0, p, projectiles);
		e.updateState(500, 0, p, projectiles);
		e.updateState(500, 0, p, projectiles);
		assertNotEquals(e.getX(), 0);
	}

}
