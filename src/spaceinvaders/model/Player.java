package spaceinvaders.model;

public class Player extends GameObject
{
    public Player(int sizeX, int sizeY)
    {
        super(sizeX*11/24, sizeY*23/24, sizeX/12, sizeY/24);

    }

    public Bullet shoot()
    {
        return new Bullet(super.getSizeX()/10, super.getPosX(), super.getPosY(), Bullet.Origin.PLAYER);
    }

    public void moveLeft()
    {
        super.modifyPosX(-5);
    }

    public void moveRight()
    {
        super.modifyPosX(5);
    }

}
