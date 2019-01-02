package spaceinvaders.model;

public class Rock extends GameObject{
    private boolean damaged;
    /**
     * Rock class constructor
     * @param size - size of rock
     * @param posX - position of rock
     * @param posY - position of rock
     */
    Rock(int size,int posX, int posY) {
        super(posX,posY,size,size);
        damaged = false;
    }
    /**
     * Used instead of setDead because rock can make through 2 shoots
     */
    void hit(){ //Package Private
        if(damaged){
            super.setDead();
        }else{
            damaged = true;
        }
    }
    /**
     * Used to obtain health state from rock
     * @return if rock was hit
     */
    public boolean isDamaged(){
        return damaged;
    }
}
