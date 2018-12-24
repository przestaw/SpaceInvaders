package spaceinvaders.model;

import java.util.ArrayList;

//TODO : Make class below a monitor
//TODO : make out of bond for player
//TODO : make 3 types of enemy

public class SpaceGame extends GameBoard implements Updatable{

    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Rock> rocks;
    private long movCount;
    private long lastPlayerShoot;
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
        lastPlayerShoot = 0;
    }

    public void unPause(){
        super.start();
    }

    private void scoreUp(Enemy.EnemyType type)
    {
        int add = 0;
        switch (type){
            case angry:
                add = 5;
                break;
            case bad:
                add = 10;
                break;
            case evil:
                add = 20;
                break;
        }
        super.setScore(super.getScore() + add);
    }

    private void resetPlayer() {
        player.setAlive();
        lastPlayerShoot = 0;
        player.setPosX(super.getSizeX()*11/24);
    }

    private void createEnemy() {
        Enemy.EnemyType type;
        for(int j = 0; j < 5; ++j) {
            switch (j){
                default:
                case 0:
                    type = Enemy.EnemyType.evil;
                    break;
                case 1:
                case 2:
                    type = Enemy.EnemyType.bad;
                    break;
                case 3:
                case 4:
                    type = Enemy.EnemyType.angry;
                    break;
            }
            for(int i = 0; i < 10; ++i) {
                enemies.add(new Enemy(super.getSizeX()/30, (i+1)*super.getSizeX()/16, (1+j)*super.getSizeY()/16, type));
            }
        }
    }

    private void createRocks(){
        for(int j = 0; j < 6; ++j) {
            for(int i = 0; i < 3; ++i) {
                for(int k = 0; k < 2; ++k) {
                    rocks.add(new Rock(super.getSizeX()/39, super.getSizeX()*(8+j*12+i*2)/80,super.getSizeY()*(33 + k)/40));
                }
            }
        }
    }

    public synchronized void update() {
        Boolean gameover = false;
        //move player
        player.move(super.getSizeX()/400);

        if(super.isWon()) {
            return; //if we won the game there is nothing to do
        }
        //shoot by player - with shrinking intervals[if pressed]
        if(player.isFireOn() && (movCount - lastPlayerShoot > enemies.size()/2 + 20)){
            bullets.add(player.shoot());
            lastPlayerShoot = movCount;
        }

        //Check if anybody is dead
        for (Bullet bullet: bullets) {
            bullet.move(super.getSizeX()/400);
            for (Rock rock: rocks) {
                if(rock.isAlive()
                        && isInRange(bullet, rock))
                {
                    rock.hit();
                    bullet.setDead();
                }
            }
            if(bullet.isAlive()) {
                switch(bullet.getOrigin()) {
                    case PLAYER:
                        for (Enemy enemy: enemies) {
                            if(enemy.isAlive()
                                    && isInRange(bullet, enemy))
                            {
                                enemy.setDead();
                                bullet.setDead();
                                scoreUp(enemy.getEnemyType());
                            }
                        }
                        break;
                    case ENEMY:
                        if(isInRange(bullet, player)) {
                            player.setDead();
                            this.pause();
                            bullet.setDead();
                            gameover = true;
                        }
                        break;
                }
            }
        }
        if(enemies.size() == 0){
            this.won();
        }
        if(gameover){
            this.gameover(); //to avoid cocurrent modification and
            return;
        }
        //Remove used bullets and rocks
        bullets.removeIf((Bullet b) -> !(b.isAlive()));
        rocks.removeIf((Rock r) -> !(r.isAlive()));
        enemies.removeIf((Enemy e) -> !(e.isAlive()));

        //remove out of range bullets
        bullets.removeIf((Bullet b) -> (b.getPosX() > super.getSizeX()));
        bullets.removeIf((Bullet b) -> (b.getPosX() < 0));

        bullets.removeIf((Bullet b) -> (b.getPosY() > super.getSizeY()));
        bullets.removeIf((Bullet b) -> (b.getPosY() < 0));

        //Enemy shoots
        for (Enemy enemy : enemies){
            if(enemy.isAlive() && movCount%(2*enemies.size()) == 0 && Math.random() < 0.05) {
                bullets.add(enemy.shoot());
            }
        }
        //Enemy moves
        if(movCount%(enemies.size() + 15) == 0) {
            for (Enemy enemy : enemies) {
                enemy.move();
            }
        }
        movCount++;
    }

    public Player getPlayer(){
        return player;
    }

    public void setLeftOff(){player.setLeftOff();}

    public void setRightOff(){player.setRightOff();}

    public void setFireOff(){player.setFireOff();}

    public void setLeftOn(){player.setLeftOn();}

    public void setRightOn(){player.setRightOn();}

    public void setFireOn(){player.setFireOn();}

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


