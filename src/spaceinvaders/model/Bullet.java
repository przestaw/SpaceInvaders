package spaceinvaders.model;

public class Bullet extends GameObject
{
    public enum Origin {ENEMY, PLAYER};

    private Origin origin;

    public Bullet(int size, int posX, int posY, Origin origin)
    {
        super(posX, posY, size/2, size);
        this.origin = origin;
        switch(origin){
            case ENEMY:
                super.setDirection(Direction.down);
                break;
            case PLAYER:
                super.setDirection(Direction.up);
                break;
        }
        move();
    }

    public Origin getOrigin()
    {
        return origin;
    }
}
