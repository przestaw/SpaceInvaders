package spaceinvaders.model;

import java.util.ArrayList;

//TODO : Make Player move methods to avoid going out of range xd

public class SpaceGame extends GameBoard {

    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Rock> rocks;
    private long movCount;
    /**
     * Class Constructor
     * @param sizeX - height
     * @param sizeY - width
     */
    public SpaceGame(int sizeX, int sizeY) {
        super(sizeX, sizeY);
        player = new Player(sizeX, sizeY);
        enemies = new ArrayList<Enemy>();
        createEnemy();
        rocks = new ArrayList<Rock>();
        createRocks();
        bullets = new ArrayList<Bullet>();
    }

    public void restart() {
        super.start();
        movCount = 0;
        bullets.clear();
        rocks.clear();
        createRocks();
        enemies.clear();
        createEnemy();
        resetPlayer();
        super.setScore(0);
    }

    @Override
    public void gameover() {
        super.gameover();
        bullets.clear();
        player.setDead();
        movCount = 0;
    }

    private void scoreUp()
    {
        super.setScore(super.getScore() + 5);
    }

    private void resetPlayer() {
        player.setAlive();
        player.setPosX(sizeX*11/24);
    }

    private void createEnemy() {
        for(int j = 0; j < 4; ++j) {
            for(int i = 0; i < 10; ++i) {
                enemies.add(new Enemy(sizeX/30, (i+1)*sizeX/14, (1+j)*sizeY/14));
            }
        }
    }

    private void createRocks(){
        for(int j = 0; j < 6; ++j) {
            for(int i = 0; i < 3; ++i) {
                for(int k = 0; k < 2; ++k) {
                    rocks.add(new Rock(sizeX/130, sizeX*(7+j*8+i*2)/60,sizeY*(23 + k)/30));
                }
            }
        }
    }

    public void update() {
        //Check if anybody is dead
        for (Bullet bullet: bullets) {
            bullet.move();
            for (Rock rock: rocks) {
                if(rock.isAlive()
                        && isInRange(bullet, rock)){
                    rock.hit();
                    bullet.setDead();
                }
            }

            if(bullet.isAlive()) {
                switch(bullet.getOrigin()) {
                    case PLAYER:
                        for (Enemy enemy: enemies) {
                            if(enemy.isAlive()
                                    && isInRange(bullet, enemy)
                            )
                            {
                                enemy.setDead();
                                bullet.setDead();
                                scoreUp();
                            }
                        }
                        break;
                    case ENEMY:
                        if(isInRange(bullet, player)) {
                            //player.setDead();
                            //this.pause();
                            bullet.setDead();
                            //this.gameover();
                            System.out.println("Gameover");
                        }
                        break;
                }
            }
        }
        //Remove used bullets and rocks
        bullets.removeIf((Bullet b) -> !(b.isAlive()));
        rocks.removeIf((Rock r) -> !(r.isAlive()));
        enemies.removeIf((Enemy e) -> !(e.isAlive()));
        //TODO :: make out of range bullets dead in ^ cond above ^

        bullets.removeIf((Bullet b) -> (b.getPosX() > sizeX));
        bullets.removeIf((Bullet b) -> (b.getPosX() < 0));

        bullets.removeIf((Bullet b) -> (b.getPosY() > sizeY));
        bullets.removeIf((Bullet b) -> (b.getPosY() < 0));

        //Enemy shoots
        for (Enemy enemy : enemies){
            if(enemy.isAlive() && movCount%(2*enemies.size()) == 0 && Math.random() < 0) {
                bullets.add(enemy.shoot());
            }
        }
        //Enemy moves
        System.out.println(movCount);
        if(movCount%(enemies.size() + 15) == 0) {
            for (Enemy enemy : enemies) {
                enemy.move();
            }
        }
        movCount++;
/*
        for(Iterator<Bullet> iter = bullets.iterator(); iter.hasNext();){
            Bullet test = iter.next();
            if(!test.isAlive()){
                iter.remove();
            }
        }
*/
    }

    public Player getPlayer(){
        return player;
    }

    public void playerShoot() {
        bullets.add(player.shoot());
    }

    public ArrayList<Rock>  getRocks() { return rocks; }

    public ArrayList<Enemy> getEnemies() { return enemies; }

    public ArrayList<Bullet> getBullets() { return bullets; }
/*
    private boolean isInRange(GameObject one, GameObject two) {
        return((  (one.getPosX()-one.getSizeX()/2 <= two.getPosX()+two.getSizeX()/2)
                &&(one.getPosX()+one.getSizeX()/2 >= two.getPosX()-two.getSizeX()/2))
               &&((one.getPosY()-one.getSizeY()/2 <= two.getPosY()+two.getSizeY()/2)
                &&(one.getPosY()+one.getSizeY()/2 >= two.getPosY()-two.getSizeY()/2)));
    }
*/
    private boolean isInRange(GameObject one, GameObject two) {
        if(   (one.getPosX()-one.getSizeX()/2 > two.getPosX()+two.getSizeX()/2)
            ||(one.getPosX()+one.getSizeX()/2 < two.getPosX()-two.getSizeX()/2)){
            return false;
        } else if((one.getPosY()-one.getSizeY()/2 > two.getPosY()+two.getSizeY()/2)
                ||(one.getPosY()+one.getSizeY()/2 < two.getPosY()-two.getSizeY()/2)){
            return false;
        } else{
            return true;
        }
    }
}


