package spaceinvaders.model;

import java.util.ArrayList;

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
    /**
     * Method used to handle restart
     */
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
    /**
     * Method used to handle gameover
     */
    @Override
    public void gameover() {
        super.gameover();
        bullets.clear();
        player.setDead();
        movCount = 0;
        lastPlayerShoot = 0;
    }
    /**
     * Method that resets game state to play
     */
    public void unPause(){
        super.start();
    }
    /**
     * Updates the score depending on the killed enemy type
     * @param type - killed enemy
     */
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
    /**
     * Utility function to check if two gameObjects overlap
     * @param one - first gameObject
     * @param two - second gameObject
     * @return if 2 objects overlap
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
    /**
     * Sets player to beginning state
     */
    private void resetPlayer() {
        player.setAlive();
        lastPlayerShoot = 0;
        player.setPosX(super.getSizeX()*11/24);
    }
    /**
     * Creates all enemies
     */
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
    /**
     * Creates all rocks
     */
    private void createRocks(){
        for(int j = 0; j < 6; ++j) {
            for(int i = 0; i < 3; ++i) {
                for(int k = 0; k < 2; ++k) {
                    rocks.add(new Rock(super.getSizeX()/39, super.getSizeX()*(8+j*12+i*2)/80,super.getSizeY()*(33 + k)/40));
                }
            }
        }
    }
    /**
     * Method used to update all game elements
     */
    public synchronized void update() {
        boolean gameover = false;
        //move player
        if(player.getPosY() > 0 || player.getPosY() < super.getSizeY()){
            player.move(super.getSizeX()/(60.0 + enemies.size() * 4.0));
        }
        if(super.isWon()) {
            return; //if we won the game there is nothing to do
        }
        //shoot by player - with shrinking intervals[if pressed]
        if(player.isFireOn() && (movCount - lastPlayerShoot > enemies.size()/2 + 15)){
            bullets.add(player.shoot());
            lastPlayerShoot = movCount;
        }

        //Check if anybody is dead
        for (Bullet bullet: bullets) {
            bullet.move(super.getSizeX()/(90.0 + enemies.size()*1.5));
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
            if(enemy.isAlive() && movCount%(2*enemies.size()) == 0 && Math.random() < 0.04) {
                bullets.add(enemy.shoot());
            }
        }
        //Enemy moves
        if(movCount%(enemies.size() + 10) == 0) {
            for (Enemy enemy : enemies) {
                enemy.move(super.getSizeX()/40.0);
            }
        }
        movCount++;
    }
    /**
     * Getter for the Player
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    /**
     * Used to set button left off
     */
    public void setLeftOff(){player.setLeftOff();}
    /**
     * Used to set button right off
     */
    public void setRightOff(){player.setRightOff();}
    /**
     * Used to set button fire off
     */
    public void setFireOff(){player.setFireOff();}
    /**
     * Used to set button left on
     */
    public void setLeftOn(){player.setLeftOn();}
    /**
     * Used to set button right on
     */
    public void setRightOn(){player.setRightOn();}
    /**
     * Used to set button fire on
     */
    public void setFireOn(){player.setFireOn();}
    /**
     * Getter for all rocks
     * @return rocks arrayList
     */
    public ArrayList<Rock>  getRocks() { return rocks; }
    /**
     * Getter for all rocks
     * @return rocks arrayList
     */
    public ArrayList<Enemy> getEnemies() { return enemies; }
    /**
     * Getter for all bullets
     * @return bullets arrayList
     */
    public ArrayList<Bullet> getBullets() { return bullets; }
}


