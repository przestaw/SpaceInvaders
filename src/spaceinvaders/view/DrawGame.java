package spaceinvaders.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spaceinvaders.model.*;

public class DrawGame {

    SpaceGame myGame;

    DrawGame(SpaceGame myGame) {
        this.myGame = myGame;
    }
    /**
     * Redraws whole game context on given Canvas
     * @param canvas - where to draw game
     */
    public void redrawGame(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#000020"));
        gc.fillRect(0,0,myGame.getSizeX(),myGame.getSizeY());
        //Redraw all bullets
        for (Bullet bullet: myGame.getBullets()) {
            redrawBullet(gc, bullet);
        }
        //Redraw all rocks
        for (Rock rock: myGame.getRocks()) {
            redrawRock(gc, rock);
        }
        //Redraw all enemies
        for (Enemy enemy: myGame.getEnemies()) {
            redrawEnemy(gc, enemy);
        }
        //Redraw player
        redrawPlayer(gc, myGame.getPlayer());
    }
    /**
     * Redraws given enemy on given context
     * @param gc - where to draw enemy
     * @param enemy - enemy to draw
     */
    private void redrawEnemy(GraphicsContext gc, Enemy enemy) {
        switch (enemy.getEnemyType()){
            case angry:
                gc.setStroke(Color.GREEN);
                gc.setFill(Color.LIME);
                break;
            case bad:
                gc.setStroke(Color.DARKGOLDENROD);
                gc.setFill(Color.YELLOW);
                break;
            case evil:
                gc.setStroke(Color.ORANGERED);
                gc.setFill(Color.ORANGE);
                break;
        }
        gc.fillRect(enemy.getPosX()-enemy.getSizeX()/2.0, enemy.getPosY()-enemy.getSizeY()/2.0, enemy.getSizeX(), enemy.getSizeY());
        gc.setLineWidth(myGame.getSizeX()/200.0);
        gc.strokeRoundRect(enemy.getPosX()-enemy.getSizeX()/2.0, enemy.getPosY()-enemy.getSizeY()/2.0, enemy.getSizeX(), enemy.getSizeY(), 10, 10);
    }
    /**
     * Redraws given player on given context
     * @param gc - where to draw
     * @param player - player to draw
     */
    private void redrawPlayer(GraphicsContext gc, Player player) {
        gc.setFill(Color.GHOSTWHITE);

        gc.fillRect(player.getPosX()-player.getSizeX()/2.0, player.getPosY()+player.getSizeY()/2.0, player.getSizeX(), player.getSizeY());
        gc.fillRect(player.getPosX()-player.getSizeX()/160.0-player.getSizeX()/6.0, player.getPosY()+player.getSizeY()/80.0, player.getSizeX()/3.0, player.getSizeY());
    }
    /**
     * Redraws given bullet on given context
     * @param gc - where to draw
     * @param bullet - bullet to draw
     */
    private void redrawBullet(GraphicsContext gc, Bullet bullet) {
        gc.setFill(Color.DARKRED);
        if(bullet.isAlive())
        {gc.setFill(Color.PINK);}
        gc.fillRoundRect(bullet.getPosX()-bullet.getSizeX()/2.0, bullet.getPosY()+bullet.getSizeY()/2.0, bullet.getSizeX(), bullet.getSizeY(), 15, 15);
    }
    /**
     * Redraws given rock on given context
     * @param gc - where to draw
     * @param rock - rock to draw
     */
    private void redrawRock(GraphicsContext gc, Rock rock){
        if(rock.isDamaged()) {
            gc.setFill(Color.ORANGE);
        }else{
            gc.setFill(Color.RED);
        }
        gc.fillRect(rock.getPosX()-rock.getSizeX()/2.0, rock.getPosY()+rock.getSizeY()/2.0, rock.getSizeX(), rock.getSizeY());
    }
}
