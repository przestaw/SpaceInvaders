package spaceinvaders.model;

public class Player extends GameObject
{
    public Player(int sizeX, int sizeY)
    {
        super(sizeX*11/24, sizeY/24, sizeX/12, sizeX/24);

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
