package spaceinvaders.model;

public class Rock extends GameObject{

    private boolean damaged;

    Rock(int size,int posX, int posY) {
        super(posX,posY,size,size);
        damaged = false;
    }

    void hit(){ //Package Private
        if(damaged){
            super.setDead();
        }else{
            damaged = true;
        }
    }

    public boolean isDamaged(){
        return damaged;
    }
}
