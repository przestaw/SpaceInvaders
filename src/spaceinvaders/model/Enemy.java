package spaceinvaders.model;

public class Enemy extends GameObject {

    public Enemy()
    {
        super(1,1,1,1);
    }

    public Enemy(int size, int posX, int posY)
    {
        super(posX, posY, size, size);
    }

    public Bullet shoot()
    {
        return new Bullet(super.getSizeX()/10, super.getPosX(), super.getPosY(), Bullet.Origin.ENEMY);
    }

    public void moveLeft()
    {
        super.modifyPosX(-5);
    }

    public void moveRight()
    {
        super.modifyPosX(5);
    }

    public void moveDown()
    {
        super.modifyPosY(5);
    }

}
