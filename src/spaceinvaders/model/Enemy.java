package spaceinvaders.model;

public class Enemy extends GameObject {

    private int moveCount;

    public Enemy()
    {
        super(1,1,1,1);
        moveCount = -11;
    }

    public Enemy(int size, int posX, int posY)
    {
        super(posX, posY, size, size);
        moveCount = -11;
    }

    public Bullet shoot()
    {
        return new Bullet(super.getSizeX()/3, super.getPosX(), super.getPosY(), Bullet.Origin.ENEMY);
    }

    public void moveLeft()
    {
        super.modifyPosX(-7);
    }

    public void moveRight()
    {
        super.modifyPosX(7);
    }

    public void moveDown()
    {
        super.modifyPosY(3);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void move()
    {
        if(moveCount < 0) {
            moveRight();
            moveCount++;
        } else if(moveCount == 0) {
            moveDown();
            moveCount++;
        } else if(moveCount < 12){
            moveLeft();
            moveCount++;
        }else {
            moveDown();
            moveCount= -11;
        }
    }
}
