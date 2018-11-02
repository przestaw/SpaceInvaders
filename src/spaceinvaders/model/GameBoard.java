package spaceinvaders.model;

public class GameBoard {
    private int score;

    private static int sizeX;
    private static int sizeY;

    private boolean play;
    private boolean gameover;
    private boolean won;

    public GameBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.score = 0;

        this.play = false;
        this.gameover = false;
        this.won = false;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameover() {
        return gameover;
    }

    public boolean isPlay() {
        return play;
    }

    public boolean isWon() {
        return won;
    }

    public void gameover() {
        gameover = true;
        play = false;
    }

    public void start() {
        play = true;
        gameover = false;
    }

    public void pause() {
        play = false;
    }

}
