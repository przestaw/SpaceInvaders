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
        player.setPosX(super.getSizeX()*11/24);
    }

    private void createEnemy() {
        for(int j = 0; j < 4; ++j) {
            for(int i = 0; i < 10; ++i) {
                enemies.add(new Enemy(super.getSizeX()/30, (i+1)*super.getSizeX()/14, (1+j)*super.getSizeY()/14));
            }
        }
    }

    private void createRocks(){
        for(int j = 0; j < 6; ++j) {
            for(int i = 0; i < 3; ++i) {
                for(int k = 0; k < 2; ++k) {
                    rocks.add(new Rock(super.getSizeX()/135, super.getSizeX()*(7+j*8+i*2)/60,super.getSizeY()*(23 + k)/30));
                }
            }
        }
    }

    public void update() {
        //move player
        player.move(super.getSizeX()/400);
        //shoot by player - with shrinking intervals[if pressed]
        if(player.isFireOn()){
            player.incrementShootCounter();
            if(player.getShootCounter() == 1){
                bullets.add(player.shoot());
            }else{
                if(player.getShootCounter()%25 == 0)
                    player.resetShootCounter();
            }
        }else{
            player.resetShootCounter();
        }
        //Check if anybody is dead
        for (Bullet bullet: bullets) {
            bullet.move();
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
                                scoreUp();
                            }
                        }
                        break;
                    case ENEMY:
                        if(isInRange(bullet, player)) {
                            player.setDead();
                            this.pause();
                            bullet.setDead();
                            this.gameover();
                        }
                        break;
                }
            }
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
            if(enemy.isAlive() && movCount%(2*enemies.size()) == 0 && Math.random() < 0) {
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

    private boolean isInRange(GameObject one, GameObject two) {
        return((  (one.getPosX()-one.getSizeX()/2 <= two.getPosX()+two.getSizeX()/2)
                &&(one.getPosX()+one.getSizeX()/2 >= two.getPosX()-two.getSizeX()/2))
               &&((one.getPosY()-one.getSizeY()/2 <= two.getPosY()+two.getSizeY()/2)
                &&(one.getPosY()+one.getSizeY()/2 >= two.getPosY()-two.getSizeY()/2)));
    }
/*
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
    */
}


