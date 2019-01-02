package spaceinvaders.model;

/**
 * Abstract Class to unify behavior of all game objects
 */ 

public abstract class GameObject {
    private double sizeX;
    private double sizeY;

    private double posX;
    private double posY;

	private boolean alive;

	/**
	 * Type uset to specify the direction GameObject is looking at
	 */
	public enum Direction{up, down, left, right, none, upRight, upLeft, downRight, downLeft};
	private Direction direction;
	/**
	 * Class constructor
	 * Assumes that size of object is 1 by 1
 	 * @param posX - position on X axis
	 * @param posY - position on Y axis
	 */
    GameObject(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;

        this.sizeX = 1;
        this.sizeY = 1;

        this.direction = Direction.none;
        this.alive = true;
    }
	/**
	 * Class constructor
 	 * @param posX - position on X axis
	 * @param posY - position on Y axis
	 * @param sizeX - width of object
	 * @param sizeY - height of object
	 */
    GameObject(double posX, double posY, double sizeX, double sizeY) {
        this.posX = posX;
        this.posY = posY;

        this.sizeX = sizeX;
        this.sizeY = sizeY;

		this.direction = Direction.none;
        this.alive = true;
    }

	/**
	 * Method used to move object on each game refresh
	 * @param steps
	 */
	void move(double steps){
		switch (direction){
			case up:
				posY = posY - steps;
				break;
			case down:
				posY = posY + steps;
				break;
			case left:
				posX = posX - steps;
				break;
			case right:
				posX = posX + steps;
				break;
			case upLeft:
				posX = posX- steps;
				posY = posY - steps;
				break;
			case upRight:
				posX = posX + steps;
				posY = posY - steps;
				break;
			case downLeft:
				posX = posX - steps;
				posY = posY + steps;
				break;
			case downRight:
				posX = posX + steps;
				posY = posY + steps;
				break;
			case none:
			default:
				break;
		}
	}
	/**
	 * Method used to move object on each game refresh
	 */
	void move(){
		this.move(1);
	}
	/** Getter for variable direction
	 * @return direction the object is podoubleing at
	 */
	public Direction getDirection() {
		return direction;
	}

	/** Setter for variable direction
	 * @param direction - direction the object is podoubleing at
	 */
	void setDirection(Direction direction) {
		this.direction = direction;
	}
	/** Getter for position on X axis
	 * @return position on X axis
	 */
    public final double getPosX()
    {
        return posX;
    }
	/** Getter for position on Y axis
	 * @return position on Y axis
	 */
    public final double getPosY()
    {
        return posY;
    }
	/** Getter for width of object
	 * @return width of object
	 */
    public final double getSizeX()
    {
        return sizeX;
    }
	/** Getter for height of object
	 * @return height of object
	 */
    public final double getSizeY()
    {
        return sizeY;
    }
	/**
	 * Setter for position on X axis
	 * @param posX position on X axis
	 */
    public void setPosX(double posX) {
        if(posX > 0)
            this.posX = posX;
    }
	/**
	 * Setter for position on Y axis
	 * @param posY position on Y axis
	 */
    public void setPosY(double posY) {
        if(posY > 0)
            this.posY = posY;
    }
	/** Setter for width of object
	 * @param sizeX width of object
	 */
    public void setSizeX(double sizeX) {
        if(sizeX > 0)
            this.sizeX = sizeX;
    }
	/** Setter for height of object
	 * @param sizeY height of object
	 */
    public void setSizeY(double sizeY) {
        if(sizeY > 0)
            this.sizeY = sizeY;
    }
	/**
	 * Method used to move object in X axis
	 * @param X how many steps in direction of X axis
	 * @return position on X axis
	 */
    public double modifyPosX(double X) {
        this.posX += X;
		return this.posX;
    }
	/**
	 * Method used to move object in Y axis
	 * @param Y how many steps in direction of Y axis
	 * @return position on Y axis
	 */
    public double modifyPosY(double Y) {
        this.posY += Y;
		return this.posY;
    }
	/**
	 * Method used to modify object size in Y axis
	 * @param X how many units in direction of X axis
	 * @return size on X axis or -1 if size would be less than 0
	 */
    public double modifySizeX(double X) {
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
	 * @param Y how many units in direction of Y axis
	 * @return size on Y axis or -1 if size would be less than 0
	 */
    public double modifySizeY(double Y) {
		if(Y<this.sizeY) {
			this.sizeY += Y;
			return this.sizeY;
		}else {
			return -1;
		}
    }
	/**
	 * Method checking if Object is alive
	 * @return alive bool
	 */
	public boolean isAlive() {
		return alive;
	}
	/**
	 * Setter that makes Object Dead
	 */
	public void setDead() {
		this.alive = false;
	}
	/**
	 * Setter that makes Object Alive
	 */
	public void setAlive() {
		this.alive = true;
	}
}