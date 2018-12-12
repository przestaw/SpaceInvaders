package spaceinvaders.model;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SpaceGame extends GameBoard {

    private Enemy[] enemyArray; //12x5
    private Player player;
    private List<Bullet> bullets;

    private int moveCount;
    /**
     * Class Constructor
     * @param sizeX - height
     * @param sizeY - width
     */
    public SpaceGame(int sizeX, int sizeY)
    {
        super(sizeX, sizeY);
        moveCount = 0;
        player = new Player(sizeX, sizeY);
        enemyArray= new Enemy[60];
        createEnemy();
    }

    public void restart()
    {
        super.start();
        createEnemy();
        resetPlayer();
        super.setScore(0);
        moveCount = 0;
    }

    @Override
    public void gameover()
    {
        super.gameover();
        player.setDead();
    }

    public void scoreUp()
    {
        super.setScore(super.getScore() + 5);
    }

    private void resetPlayer()
    {
        player.setAlive();
        player.setPosX(sizeX*11/24);
        player.setSizeX(sizeY/24);
    }

    private void createEnemy()
    {
        for(int i = 0; i < 12; ++i)
        {
            enemyArray[i] = new Enemy(sizeX/24, i*sizeX/12, sizeY/2);
        }
    }

    public Enemy[] getEnemy()
    {
        return enemyArray;
    }

    public Iterator<Bullet> getBulletList()
    {
        return bullets.iterator();
    }

    public void update()
    {
        //Check if anybody is dead
        for (Bullet bullet: bullets) {
            bullet.move();
            switch(bullet.getOrigin())
            {
                case PLAYER:
                    for (Enemy enemy: enemyArray) {
                        if(enemy.isAlive()
                                && enemy.getPosX() == bullet.getPosX()
                                && enemy.getPosY() == bullet.getSizeY()
                           )
                        {
                            enemy.setDead();
                            bullet.setDead();
                            scoreUp();
                        }
                    }
                    break;
                case ENEMY:
                    if(player.getPosX() == bullet.getPosX() && player.getPosY() == bullet.getSizeY())
                    {
                        player.setDead();
                        bullet.setDead();
                        this.gameover();
                    }
                    break;
            }
        }
        //TODO :: make out of range bullets dead in ^ cond above ^

        //Enemy shoots
        for (Enemy enemy: enemyArray) {
            if(moveCount%2 == 1 && Math.random() < 0.3)
            {
                bullets.add(enemy.shoot());
            }
        }
        //Enemy moves
        if(moveCount < 0)
        {
            for (Enemy enemy: enemyArray) {
                enemy.moveRight();
            }
        }else if(moveCount == 0)
        {
            for (Enemy enemy: enemyArray) {
                enemy.moveDown();
            }
        }else if(moveCount < 12)
        {
            for (Enemy enemy: enemyArray) {
                enemy.moveDown();
            }
        }else
        {
            for (Enemy enemy: enemyArray) {
                enemy.moveDown();
            }
            moveCount = -11;
        }
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
        Predicate<Bullet> deadBullet = b -> !(b.isAlive());
        bullets.removeIf(deadBullet);
    }

    public int getPlayerX() {
        return player.getPosX();
    }

    public int getPlayerY() {
        return player.getPosY();
    }
}
