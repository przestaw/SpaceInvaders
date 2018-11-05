package spaceinvaders.model;

/**
 * Abstract Class to unify behavior of all game objects
 */ 

public abstract class GameObject {
    private int sizeX;
    private int sizeY;

    private int posX;
    private int posY;
	/**
	 * Type uset to specify the direction GameObject is looking at
	 */
    public enum Direction{up, down, left, right};
	
	/**
	 * Class constructor
	 * Assumes that size of object is 1 by 1
 	 * @param posX - position on X axis
	 * @param posY - position on Y axis
	 */
    GameObject(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;

        this.sizeX = 1;
        this.sizeY = 1;
    }
	/**
	 * Class constructor
 	 * @param posX - position on X axis
	 * @param posY - position on Y axis
	 * @param sizeX - width of object
	 * @param sizeY - height of object
	 */
    GameObject(int posX, int posY, int sizeX, int sizeY)
    {
        this.posX = posX;
        this.posY = posY;

        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
	/** Getter for position on X axis
	 * @return position on X axis
	 */
    public final int getPosX()
    {
        return posX;
    }
	/** Getter for position on Y axis
	 * @return position on Y axis
	 */
    public final int getPosY()
    {
        return posY;
    }
	/** Getter for width of object
	 * @return width of object
	 */
    public final int getSizeX()
    {
        return sizeX;
    }
	/** Getter for height of object
	 * @return height of object
	 */
    public final int getSizeY()
    {
        return sizeY;
    }
	/**
	 * Setter for position on X axis
	 * @param position on X axis
	 */
    public void setPosX(int posX)
    {
        if(posX > 0)
            this.posX = posX;
    }
	/**
	 * Setter for position on Y axis
	 * @param position on Y axis
	 */
    public void setPosY(int posY)
    {
        if(posY > 0)
            this.posY = posY;
    }
	/** Setter for width of object
	 * @param width of object
	 */
    public void setSizeX(int sizeX)
    {
        if(sizeX > 0)
            this.sizeX = sizeX;
    }
	/** Setter for height of object
	 * @param height of object
	 */
    public void setSizeY(int sizeY)
    {
        if(sizeY > 0)
            this.sizeY = sizeY;
    }
	/**
	 * Method used to move object in X axis
	 * @param how many steps in direction of X axis
	 * @return position on X axis
	 */
    public int modifyPosX(int X)
    {
        this.posX += X;
		return this.posX;
    }
	/**
	 * Method used to move object in Y axis
	 * @param how many steps in direction of Y axis
	 * @return position on Y axis
	 */
    public int modifyPosY(int Y)
    {
        this.posY += Y;
		return this.posY;
    }
	/**
	 * Method used to modify object size in Y axis
	 * @param how many units in direction of Y axis
	 * @return size on Y axis or -1 if size would be less than 0
	 */
    public int modifySizeX(int X)
    {
		if(X<this.sizeX)
		{
			this.sizeX += X;
			return this.sizeX;
		}else
		{
			return -1;
		}
    }

    /**
	 * Method used to modify object size in Y axis
	 * @param how many units in direction of Y axis
	 * @return size on Y axis or -1 if size would be less than 0
	 */
    public int modifySizeY(int Y)
    {
		if(Y<this.sizeY)
		{
			this.sizeY += Y;
			return this.sizeY;
		}else
		{
			return -1;
		}
    }

}