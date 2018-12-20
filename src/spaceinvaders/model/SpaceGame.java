package spaceinvaders.model;

import java.util.ArrayList;
import java.util.Iterator;

//TODO : Make Player move methods to avoid going out of range xd

public class SpaceGame extends GameBoard {

    private Enemy[] enemyArray; //12x5
    private Player player;
    private ArrayList<Bullet> bullets;

    private int movEnemy;
    private long movCount;
    /**
     * Class Constructor
     * @param sizeX - height
     * @param sizeY - width
     */
    public SpaceGame(int sizeX, int sizeY) {
        super(sizeX, sizeY);
        player = new Player(sizeX, sizeY);
        enemyArray= new Enemy[40];
        createEnemy();
        bullets = new ArrayList<Bullet>();
        movEnemy = 0;
    }

    public void restart() {
        super.start();
        createEnemy();
        resetPlayer();
        super.setScore(0);
    }

    @Override
    public void gameover() {
        super.gameover();
        player.setDead();
        movCount = 0;
    }

    public void scoreUp()
    {
        super.setScore(super.getScore() + 5);
    }

    private void resetPlayer() {
        player.setAlive();
        player.setPosX(sizeX*11/24);
        player.setSizeX(sizeY/24);
    }

    private void createEnemy() {
        for(int j = 0; j < 4; ++j) {
            for(int i = 0; i < 10; ++i) {
                enemyArray[i+j*10] = new Enemy(sizeX/30, (i+1)*sizeX/14, (1+j)*sizeY/14);
            }
        }
    }

    public Enemy getEnemy(int i) {
        return enemyArray[i];
    }

    public Iterator<Bullet> getBulletList(){
        return bullets.iterator();
    }

    public void update() {
        //Check if anybody is dead
        for (Bullet bullet: bullets) {
            bullet.move();
            switch(bullet.getOrigin()) {
                case PLAYER:
                    for (Enemy enemy: enemyArray) {
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
                        player.setDead();
                        this.pause();
                        //bullet.setDead();
                        //this.gameover();
                        System.out.println("Gameover");
                    }
                    break;
            }
        }
        //TODO :: make out of range bullets dead in ^ cond above ^

        //Enemy shoots
        for (int i = 0; i < 8; ++i){
            if(movCount%(enemyArray.length / 2) == 0 && Math.random() < 0.1) {
                bullets.add(enemyArray[i].shoot());
            }
        }
        //Enemy moves
        if(movCount%enemyArray.length == 0)
        {
            for (Enemy enemy:enemyArray) {
                enemy.move();
            }
        }
        movCount++;

        /*
        for(Iterator<Bullet> iter = bullets.iterator(); iter.hasNext();)
        {
            Bullet test = iter.next();
            if(!test.isAlive())
            {
                iter.remove();
            }
        }
        */
        //Remove used bullets
        bullets.removeIf((Bullet b) -> !(b.isAlive()));

    }

    private void moveEnemies() {

    }

    public Player getPlayer(){
        return player;
    }

    public void playerShoot() {
        bullets.add(player.shoot());
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private boolean isInRange(GameObject one, GameObject two) {
        return((  (one.getPosX() < two.getPosX()+two.getSizeX()/2)
                &&(one.getPosX() > two.getPosX()-two.getSizeX()/2))
               &&((one.getPosY() < two.getPosY()+two.getSizeY()/2)
                &&(one.getPosY() > two.getPosY()-two.getSizeY()/2)));
    }
}
