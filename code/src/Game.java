
import java.util.ArrayList;

public class Game {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    private Player player;
    // arrays antigos
    // private Projectile[] projectilesP;
    // private Projectile[] projectilesE1;
    // private Projectile[] projectilesE2;
    // private Enemy1[] enemies1;
    // private Enemy2[] enemies2;
    private Background background1;
    private Background background2;

    // arrays novos
    private ArrayList<Projectile> projectilesP;
    private ArrayList<Projectile> projectilesE1;
    private ArrayList<Projectile> projectilesE2;
    private ArrayList<Enemy1> enemies1;
    private ArrayList<Enemy2> enemies2;

    private long currentTime;
    private long nextEnemy1;
    private long nextEnemy2;
    private int enemy2Count;
    private double enemy2SpawnX;
    private hp hpBar;

    public Game() {
        player = new Player();
        projectilesP = new ArrayList<Projectile>();
        for (int i = 0; i < 10; i++) {
            projectilesP.add(new Projectile());
        }
        projectilesE1 = new ArrayList<Projectile>();
        for (int i = 0; i < 200; i++) {
            projectilesE1.add(new Projectile());
        }
        projectilesE2 = new ArrayList<Projectile>();
        for (int i = 0; i < 200; i++) {
            projectilesE2.add(new Projectile());
        }
        enemies1 = new ArrayList<Enemy1>();
        for (int i = 0; i < 10; i++) {
            enemies1.add(new Enemy1());
        }

        enemies2 = new ArrayList<Enemy2>();
        for (int i = 0; i < 10; i++) {
            enemies2.add(new Enemy2());
        }

        currentTime = System.currentTimeMillis();
        nextEnemy1 = currentTime + 2000;
        nextEnemy2 = currentTime + 7000;
        enemy2Count = 0;
        enemy2SpawnX = GameLib.WIDTH * 0.20;

        background1 = new Background(0, 0.070, 20, 2);
        background2 = new Background(0, 0.045, 50, 3);
        hpBar = new hp(3);
    }

    public static void busyWait(long time) {
        while (System.currentTimeMillis() < time)
            Thread.yield();
    }

    public void gameLoop() {
        boolean running = true;
        long delta;
        currentTime = System.currentTimeMillis();
        GameLib.initGraphics();
        while (running) {
            delta = System.currentTimeMillis() - currentTime;
            currentTime = System.currentTimeMillis();

            // Verificação de colisões
            String collisionStatus = player.checkCollisions(projectilesP, projectilesE1, projectilesE2, enemies1,
                    enemies2, currentTime);
            if (collisionStatus == "hit") {
                // diminui vida
                hpBar.reduceHP();
                hpBar.renderHP();
            }
            if (collisionStatus == "powerup") {
                // ativa powerup
            }

            // Atualizações de estados
            for (Projectile projectile : projectilesP) {
                projectile.updateStateP(delta);
            }
            for (Projectile projectile : projectilesE1) {
                projectile.updateStateE(delta);
            }
            for (Projectile projectile : projectilesE2) {
                projectile.updateStateE(delta);
            }
            for (Enemy1 enemy : enemies1) {
                enemy.updateState(delta, currentTime, player, projectilesE1);
            }
            for (Enemy2 enemy : enemies2) {
                enemy.updateState(delta, currentTime, player, projectilesE2);
            }

            // Lançamento de novos inimigos
            launchNewEnemies(currentTime);

            // atualizar player
            player.updateState(currentTime);

            // Verificando entrada do usuário (teclado)
            processInput(delta, currentTime, player);
            if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE))
                running = false;

            // Desenha a cena
            render(delta, currentTime);

            // Espera para manter o loop constante
            busyWait(currentTime + 5);
        }
    }

    private void launchNewEnemies(long currentTime) {
        // lançando enemy1
        if (currentTime > nextEnemy1) {
            int free = findFreeIndex(enemies1);
            if (free < 10) {
                // enemies1[free].setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
                // enemies1[free].setY(-10.0);
                // enemies1[free].setV(0.20 + Math.random() * 0.15);
                // enemies1[free].setAngle(3 * Math.PI / 2);
                // enemies1[free].setRv(0.0);
                // enemies1[free].setState(ACTIVE);
                // enemies1[free].setNextShoot(currentTime + 500);
                enemies1.get(free).setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
                enemies1.get(free).setY(-10.0);
                enemies1.get(free).setV(0.20 + Math.random() * 0.15);
                enemies1.get(free).setAngle(3 * Math.PI / 2);
                enemies1.get(free).setRv(0.0);
                enemies1.get(free).setState(ACTIVE);
                enemies1.get(free).setNextShoot(currentTime + 500);
                nextEnemy1 = currentTime + 500;
            }
        }
        // lançando enemy2
        if (currentTime > nextEnemy2) {

            int free = findFreeIndex(enemies2);

            // if (free < enemies2.length) {
            if (free < enemies2.size() && free < 10) {

                // enemies2[free].setX(enemy2SpawnX);
                // enemies2[free].setY(-10.0);
                // enemies2[free].setV(0.42);
                // enemies2[free].setAngle(3 * Math.PI / 2);
                // enemies2[free].setRv(0.0);
                // enemies2[free].setState(ACTIVE);
                enemies2.get(free).setX(enemy2SpawnX);
                enemies2.get(free).setY(-10.0);
                enemies2.get(free).setV(0.42);
                enemies2.get(free).setAngle(3 * Math.PI / 2);
                enemies2.get(free).setRv(0.0);
                enemies2.get(free).setState(ACTIVE);

                enemy2Count++;

                if (enemy2Count < 10) {

                    nextEnemy2 = currentTime + 120;
                } else {

                    enemy2Count = 0;
                    enemy2SpawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
                    nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
                }
            }
        }
    }

    private void render(long delta, long currentTime) {
        // Desenha o fundo

        background1.render1(delta);
        background2.render2(delta);

        // Desenha o player
        player.render(currentTime);

        // Desenha projéteis Player
        for (Projectile projectile : projectilesP) {
            projectile.renderP();
        }

        // desenha projéteis inimigos 1
        for (Projectile projectile : projectilesE1) {
            projectile.renderE();
        }

        // desenha projéteis inimigos 2
        for (Projectile projectile : projectilesE2) {
            projectile.renderE();
        }

        // Desenha inimigos
        for (Enemy1 enemy : enemies1) {
            enemy.render(currentTime);
        }
        for (Enemy2 enemy : enemies2) {
            enemy.render(currentTime);
        }

        // Desenha barra de vida
        hpBar.renderHP();
        // Mostra a tela atualizada
        GameLib.display();
    }

    public void processInput(long delta, long currentTime, Player player) {
        if (player.getState() == ACTIVE) {
            if (GameLib.iskeyPressed(GameLib.KEY_UP))
                player.setY(player.getY() - delta * player.getVY());
            if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
                player.setY(player.getY() + delta * player.getVY());
            if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
                player.setX(player.getX() - delta * player.getVX());
            if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
                player.setX(player.getX() + delta * player.getVX());
            if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
                if (currentTime > player.getNextShot()) {
                    int free = findFreeIndex(projectilesP);
                    // if (free < projectilesP.length) {
                    if (free < projectilesP.size()) {
                        // projectilesP[free].setX(player.getX());
                        // projectilesP[free].setY(player.getY() - 2 * player.getRadius());
                        // projectilesP[free].setY(player.getY() - 2 * player.getRadius());
                        // projectilesP[free].setVx(0.0);
                        // projectilesP[free].setVy(-1.0);
                        // projectilesP[free].setState(ACTIVE);
                        projectilesP.get(free).setX(player.getX());
                        projectilesP.get(free).setY(player.getY() - 2 * player.getRadius());
                        projectilesP.get(free).setVx(0.0);
                        projectilesP.get(free).setVy(-1.0);
                        projectilesP.get(free).setState(ACTIVE);
                        player.setNextShot(currentTime + 100);
                    }
                }
            }
        }

        if (player.getX() < 0.0)
            player.setX(0.0);
        if (player.getX() >= GameLib.WIDTH)
            player.setX(GameLib.WIDTH - 1);
        if (player.getY() < 25.0)
            player.setY(25.0);
        if (player.getY() >= GameLib.HEIGHT)
            player.setY(GameLib.HEIGHT - 1);
    }

    public static int findFreeIndex(ArrayList<? extends GameElement> elements) {
        int i;
        // for (i = 0; i < elements.length; i++) {
        for (i = 0; i < elements.size(); i++) {
            // if (elements[i].getState() == INACTIVE) break;
            if (elements.get(i).getState() == INACTIVE)
                break;
        }
        return i;
    }

    public static int[] findFreeIndex(ArrayList<? extends GameElement> elements, int amount) {
        int i, k;
        // int [] freeArray = {elements.length, elements.length, elements.length};
        int[] freeArray = { elements.size(), elements.size(), elements.size() };
        // for (i = 0, k = 0; i < elements.length && k < amount; i++){
        for (i = 0, k = 0; i < elements.size() && k < amount; i++) {
            // if (elements[i].getState() == INACTIVE){
            if (elements.get(i).getState() == INACTIVE) {
                freeArray[k] = i;
                k++;
            }
        }
        return freeArray;
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.gameLoop();
    }
}