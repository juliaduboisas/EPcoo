

public class Game {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;
    
    private Player player;
    private Projectile[] projectiles;
    private Enemy[] enemies1;
    private Enemy[] enemies2;
    private Background background1;
    private Background background2;

    private long currentTime;
    private long nextEnemy1;
    private long nextEnemy2;
    private int enemy2Count;
    private double enemy2SpawnX;

    public Game() {
        player = new Player();
        projectiles = new Projectile[10];
        for (int i = 0; i < projectiles.length; i++) {
            projectiles[i] = new Projectile();
        }
        
        enemies1 = new Enemy[10];
        for (int i = 0; i < enemies1.length; i++) {
            enemies1[i] = new Enemy(1);
        }
        
        enemies2 = new Enemy[10];
        for (int i = 0; i < enemies2.length; i++) {
            enemies2[i] = new Enemy(2);
        }

        background1 = new Background(20, 0.070);
        background2 = new Background(50, 0.045);

        currentTime = System.currentTimeMillis();
        nextEnemy1 = currentTime + 2000;
        nextEnemy2 = currentTime + 7000;
        enemy2Count = 0;
        enemy2SpawnX = GameLib.WIDTH * 0.20;
    }

    public static void busyWait(long time){
        while (System.currentTimeMillis() < time) Thread.yield();
    }

    public void gameLoop() {
        boolean running = true;

        while (running) {
            long delta = System.currentTimeMillis() - currentTime;
            currentTime = System.currentTimeMillis();

            // Verificação de colisões
            player.checkCollisions(projectiles, enemies1, enemies2, currentTime);

            // Atualizações de estados
            player.updateState(delta, currentTime);
            for (Projectile projectile : projectiles) {
                projectile.updateState(delta);
            }
            for (Enemy enemy : enemies1) {
                enemy.updateState(delta, currentTime, player, projectiles);
            }
            for (Enemy enemy : enemies2) {
                enemy.updateState(delta, currentTime, player, projectiles);
            }

            // Lançamento de novos inimigos
            launchNewEnemies(currentTime);

            // Verificando entrada do usuário (teclado)
            player.processInput(delta, currentTime);

            // Desenha a cena
            render();
            
            // Espera para manter o loop constante
            busyWait(currentTime + 17);
        }
    }

    private void launchNewEnemies(long currentTime) {
        if (currentTime > nextEnemy1) {
            int free = findFreeIndex(enemies1);
            if (free < enemies1.length) {
                enemies1[free].reset(1, currentTime);
                nextEnemy1 = currentTime + 500;
            }
        }
        
        if (currentTime > nextEnemy2) {
            int free = findFreeIndex(enemies2);
            if (free < enemies2.length) {
                enemies2[free].reset(2, currentTime);
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

    private void render() {
        // Desenha o fundo
        background1.render();
        background2.render();

        // Desenha projéteis
        for (Projectile projectile : projectiles) {
            projectile.render();
        }

        // Desenha inimigos
        for (Enemy enemy : enemies1) {
            enemy.render();
        }
        for (Enemy enemy : enemies2) {
            enemy.render();
        }

        // Desenha o player
        player.render();

        // Mostra a tela atualizada
        GameLib.display();
    }

    public static int findFreeIndex(GameElement[] elements) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getState() == INACTIVE) {
                return i;
            }
        }
        return elements.length;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameLoop();
    }
}