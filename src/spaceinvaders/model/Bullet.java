package spaceinvaders.model;

public class Bullet extends GameObject
{
    public enum Origin {ENEMY, PLAYER};

    private Origin origin;

    public Bullet(int size, int posX, int posY, Origin origin)
    {
        super(posX, posY, size/2, size);
        this.origin = origin;
        move();
    }

    public Origin getOrigin()
    {
        return origin;
    }

    public void move()
    {
        switch(origin)
        {
            case PLAYER:
                super.modifyPosY(-1);
                break;
            case ENEMY:
                super.modifyPosY(1);
                break;
        }
    }
}
