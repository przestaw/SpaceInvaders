package spaceinvaders.model;

//TODO : make 3 types of enemy

public class Enemy extends GameObject implements Shootable {
    private double moveCount;

    public enum EnemyType{angry, bad, evil}
    private EnemyType enemyType;

    public Enemy() {
        super(0,0,1,1);
        moveCount = -13;
        this.enemyType = EnemyType.angry;
    }

    public Enemy(double size, double posX, double posY) {
        super(posX, posY, size, size);
        moveCount = -11;
        this.enemyType = EnemyType.angry;
    }

    public Enemy(double size, double posX, double posY, EnemyType enemyType) {
        super(posX, posY, size, size);
        moveCount = -11;
        this.enemyType = enemyType;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
    /**
     * Method used to fire a bullet from enemy
     * @return shoot bullet
     */
    public Bullet shoot() { return new Bullet(super.getSizeX()/2.0, super.getPosX(), super.getPosY(), Bullet.Origin.ENEMY); }
    /**
     * Method used to move enemy
     * @param steps how many steps to move
     */
    @Override
    public void move(double steps) {
        if(moveCount < 0) {
            setDirection(Direction.right);
            moveCount++;
        }else if(moveCount == 0){
            setDirection(Direction.down);
            moveCount++;
        }else if(moveCount < 14){
            setDirection(Direction.left);
            moveCount++;
        }else {
            setDirection(Direction.down);
            moveCount= -13;
        }
        super.move(steps);
    }
    /**
     * Method used to move enemy by 1 step
     */
    @Override
    public void move() {
        this.move(1);
    }
}
