package spaceinvaders.model;

//TODO : make 3 types of enemy

public class Enemy extends GameObject implements Playable{

    private int moveCount;

    public Enemy() {
        super(0,0,1,1);
        moveCount = -11;
    }

    public Enemy(int size, int posX, int posY) {
        super(posX, posY, size, size);
        moveCount = -11;
    }

    public Bullet shoot() {
        return new Bullet(super.getSizeX()/2, super.getPosX(), super.getPosY(), Bullet.Origin.ENEMY);
    }

    public void moveLeft()
    {
        super.modifyPosX(-10);
    }

    public void moveRight()
    {
        super.modifyPosX(10);
    }

    public void moveDown()
    {
        super.modifyPosY(5);
    }

    public void move() {
        if(moveCount < 0) {
            moveRight();
            moveCount++;
        }else if(moveCount == 0){
            moveDown();
            moveCount++;
        }else if(moveCount < 12){
            moveLeft();
            moveCount++;
        }else {
            moveDown();
            moveCount= -11;
        }
    }
}
