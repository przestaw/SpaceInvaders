package spaceinvaders.model;

public class Player extends GameObject implements Shootable {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean fireOn;

    /**
     * Player class constructor
     * @param sizeX - size of the players's game
     * @param sizeY - size of the players's game
     */
    public Player(double sizeX, double sizeY) {
        super(sizeX * 26 / 50, sizeY * 17 / 18, sizeX / 25, sizeY / 45);
        leftPressed = false;
        rightPressed = false;
    }
    /**
     * Method used to fire a bullet from playe
     * @return shoot bullet
     */
    public Bullet shoot(){ return new Bullet(super.getSizeX()/3, super.getPosX(), super.getPosY(), Bullet.Origin.PLAYER); }

    public void setLeftOff(){leftPressed = false;}
    
    public void setRightOff(){rightPressed = false;}
    
    public void setFireOff(){fireOn = false;}

    public void setLeftOn(){leftPressed = true;}

    public void setRightOn(){rightPressed = true;}

    public void setFireOn(){fireOn = true;}

    public boolean isFireOn(){return fireOn;}
    /**
     * Method used to update the player direction depending on pressed buttons
     */
    private void updateDirection(){
        if(leftPressed == rightPressed){
            super.setDirection(Direction.none);
        }else if(leftPressed){
            super.setDirection(Direction.left);
        }else{
            super.setDirection(Direction.right);
        }
    }
    /**
     * Method used to move player
     * @param steps how many steps to move
     */
    @Override
    public void move(double steps){
        updateDirection();
        super.move(steps);
    }
    /**
     * Method used to move player by 1 step
     */
    @Override
    public void move(){
        updateDirection();
        super.move();
    }
}
