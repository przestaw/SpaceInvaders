package spaceinvaders.model;

//TODO : make 3 types of enemy

public class Enemy extends GameObject implements Shootable {
    private int moveCount;

    public enum EnemyType{angry, bad, evil}
    private EnemyType enemyType;

    public Enemy() {
        super(0,0,1,1);
        moveCount = -13;
        this.enemyType = EnemyType.angry;
    }

    public Enemy(int size, int posX, int posY) {
        super(posX, posY, size, size);
        moveCount = -11;
        this.enemyType = EnemyType.angry;
    }

    public Enemy(int size, int posX, int posY, EnemyType enemyType) {
        super(posX, posY, size, size);
        moveCount = -11;
        this.enemyType = enemyType;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public Bullet shoot() {
        return new Bullet(super.getSizeX()/2, super.getPosX(), super.getPosY(), Bullet.Origin.ENEMY);
    }

    public void moveLeft()
    {
        super.modifyPosX(-20);
    }

    public void moveRight()
    {
        super.modifyPosX(20);
    }

    public void moveDown()
    {
        super.modifyPosY(20);
    }

    public void move() {
        if(moveCount < 0) {
            moveRight();
            moveCount++;
        }else if(moveCount == 0){
            moveDown();
            moveCount++;
        }else if(moveCount < 13){
            moveLeft();
            moveCount++;
        }else {
            moveDown();
            moveCount= -13;
        }
    }
}
