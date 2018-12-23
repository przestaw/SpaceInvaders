package spaceinvaders.model;

public class Player extends GameObject
{
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean fireOn;
    private int shootCounter;

    public Player(int sizeX, int sizeY)
    {
        super(sizeX*11/24, sizeY*17/18, sizeX/20, sizeY/30);
        leftPressed = false;
        rightPressed = false;

        shootCounter = 0;
    }
    
    public Bullet shoot(){ return new Bullet(super.getSizeX()/3, super.getPosX(), super.getPosY(), Bullet.Origin.PLAYER); }

    public void setLeftOff(){leftPressed = false;}
    
    public void setRightOff(){rightPressed = false;}
    
    public void setFireOff(){fireOn = false;}

    public void setLeftOn(){leftPressed = true;}

    public void setRightOn(){rightPressed = true;}

    public void setFireOn(){fireOn = true;}

    public boolean isFireOn(){return fireOn;}

    public void resetShootCounter(){shootCounter = 0;}

    public void incrementShootCounter(){shootCounter = shootCounter +1;}

    public int getShootCounter(){return shootCounter;}

    private void updateDirection(){
        if(leftPressed == rightPressed){
            super.setDirection(Direction.none);
        }else if(leftPressed){
            super.setDirection(Direction.left);
        }else{
            super.setDirection(Direction.right);
        }
    }

    @Override
    public void move(int steps){
        updateDirection();
        super.move(steps);
    }

    @Override
    public void move(){
        updateDirection();
        super.move();
    }
}
