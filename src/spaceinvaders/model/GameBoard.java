package spaceinvaders.model;
/**
 * Class used to store and modify whole game state
 */

public abstract class GameBoard {

    private int score;

    protected final int sizeX;
    protected final int sizeY;

    private boolean play;
    private boolean gameover;
    private boolean won;
	/**
	 * Class Constructor
	 * @param sizeX - height
	 * @param sizeY - width
	 */
    public GameBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.score = 0;

        this.play = false;
        this.gameover = false;
        this.won = false;
    }
	/** Getter for height of GameBoard
	 * @return  height of GameBoard
	 */
    public int getSizeX() {
        return sizeX;
    }
	/** Getter for width of GameBoard
	 * @return width of GameBoard
	 */
    public int getSizeY() {
        return sizeY;
    }
	/** Getter for Game Score
	 * @return Game Score
	 */
    public int getScore() {
        return score;
    }
	/** Getter for gameover flag
	 * @return gameover flag
	 */
    public boolean isGameover() {
        return gameover;
    }
	/** Getter for play flag
	 * @return play flag
	 */
    public boolean isPlay() {
        return play;
    }
	/** Getter for won flag
	 * @return won flag
	 */
    public boolean isWon() {
        return won;
    }
    /**
     * Setter for variable score
     * @param score new score value
     */
    public void setScore(int score) {
        this.score = score;
    }
	/**
	 * Method used to set the game state to gameover
	 */
    public void gameover() {
        gameover = true;
        play = false;
    }
	/**
	 * Method used to set the game state to play
	 */
    public void start() {
        play = true;
        gameover = false;
    }
	/**
	 * Method used to set the game state to pause
	 */
    public void pause() {
        play = !play;
    }

}
