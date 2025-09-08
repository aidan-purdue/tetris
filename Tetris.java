import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
/**
 * Plays the Tetris Game.
 * 
 * Additional Featues:
 *  - A custome scoring system
 *  - A custom level and point system which increases in difficulty
 *  - Tetris music while the user plays
 *  - A game end function when the game is over
 *  - A hold function
 *  - A pause function
 *  - A restart function
 *  - A window to view the next tetrad
 *  
 *
 * @author Aidan Wang
 * @version February 21, 2023
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad tetrad;
    private boolean gameOver;
    private int score;
    private int points;
    private int level;
    private MyBoundedGrid<Block> next;
    private BlockDisplay nextDisplay;
    private Tetrad nextTetrad;
    private MyBoundedGrid<Block> hold;
    private BlockDisplay holdDisplay;
    private Tetrad holdTetrad;
    private boolean cPressed;
    private boolean paused;
    private MusicPlayer music;
    /**
     * Creates a tetris game with tetrads
     * it also creates a next tetrad window
     * and a hold tetrad window.
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        score = 0;
        points = 0;
        level=1;
        display.setTitle("Tetris", score, level);
        display.setArrowListener(this);
        tetrad = new Tetrad(grid);
        display.showBlocks();
        gameOver=false;
        next = new MyBoundedGrid<Block>(4, 4);
        nextDisplay = new BlockDisplay(next);
        nextDisplay.setTitle("next block");
        nextTetrad = new Tetrad(next);
        nextDisplay.showBlocks();
        //tetrad.translate(6, 5);
        hold= new MyBoundedGrid<Block>(4, 4);
        holdDisplay = new BlockDisplay(hold);
        holdDisplay.setTitle("held block");
        cPressed = false;
        holdDisplay.showBlocks();
        holdTetrad = new Tetrad(new MyBoundedGrid<Block>(4, 4));
        paused = false;
        music = new MusicPlayer();
    }
    
    /**
     * displays the tetris board.
     */
    private void displayBoard()
    {
        display.showBlocks();
    }
    
    /**
     * plays tetris music.
     */
    private void playMusic()
    {
        music.startPlaying("Tetris Theme Song EARRAPE.mp3");
    }
    
    /**
     * stops tetris music.
     */
    private void stopMusic()
    {
        music.stop();
    }
    
    /**
    * Oversees the tetris game
    * 
    * @param args  Information from the command line
    */
    public static void main(String[] args)
    {
        Tetris tet = new Tetris();
        tet.displayBoard();
        tet.stopMusic();
        tet.playMusic();
        System.out.println("Welcome to the tetris game");
        System.out.println("Press z to restart");
        System.out.println("Press x to pause");
        System.out.println("Press c to hold\n");
        int num = 0;
        while (!tet.getGameEnd())
        {
            //System.out.println("loop");
            tet.play();
        }
        tet.stopMusic();
        System.out.println("Game Over");
        System.out.println("Final Point Amount: " + tet.points);
        System.out.println("Final Level: " + tet.level);
    }
    
    /**
     * returns if the game is paused or not.
     * 
     * @return  True if the game is paused. otherwise;
     *          False
     */
    private boolean getPaused()
    {
        return paused;
    }
    
    /**
     * returns if the game is over or not
     * 
     * @return  True if the game is over. otherwise;
     *          False
     */
    public boolean getGameEnd()
    {
        return gameOver;
    }
    
    /**
     * rotates the tetrad when the up arrow key is pressed
     */
    @Override 
    public void upPressed()
    {
        if(getPaused())
        {
            return;
        }
        if(tetrad.rotate())
        {
            display.showBlocks();
        }
    }
    /**
     * shifts the tetrad down when the down arrow key is pressed
     */
    @Override 
    public void downPressed()
    {
        if(getPaused())
        {
            return;
        }
        if(tetrad.translate(1, 0))
        {
            display.showBlocks();
        }
    }
    /**
     * shifts the tetrad left when the left arrow key is pressed
     */
    @Override
    public void leftPressed()
    {
        if(getPaused())
        {
            return;
        }
        if(tetrad.translate(0, -1))
        {
            display.showBlocks();
        }
    }
    /**
     * shifts the tetrad right when the right arrow key is pressed
     */
    @Override
    public void rightPressed()
    {
        if(getPaused())
        {
            return;
        }
        if(tetrad.translate(0, 1))
        {
            display.showBlocks();
        }
    }
    /**
     * hard drops the tetrad when the space key is pressed
     * moves it down until it canot anymore
     */
    @Override
    public void spacePressed()
    {
        if(getPaused())
        {
            return;
        }
        for(int i=0; i<20; i++)
        {
            downPressed();
        }
        clearCompletedRows();
        tetrad = nextTetrad;
        tetrad.addToGrid(grid);
        nextTetrad = new Tetrad(next);
        nextTetrad.addToGrid(next);
        gameOver=tetrad.getGameOver();
        cPressed=false;
    }
    
    /**
     * plays a turn of the tetris game
     */
    private void play()
    {
        //System.out.println("play");
        try 
        {
            int sleep = 1000-100*Math.min(level-1, 10);
            //System.out.println(sleep);
            Thread.sleep(sleep);
            if(!getPaused())
            {
                if(!tetrad.translate(1, 0))
                {
                    clearCompletedRows();
                    tetrad = nextTetrad;
                    tetrad.addToGrid(grid);
                    nextTetrad = new Tetrad(next);
                    nextTetrad.addToGrid(next);
                    gameOver=tetrad.getGameOver();
                    cPressed=false;
                }
                display.showBlocks();
                nextDisplay.showBlocks();
            }
        }
        catch(InterruptedException e)
        {
            //ignore }
        }
    }
    
    /**
     * checks if a row is completed
     * 
     * @precondition    the row is in the range of [0, number of rows).
     * 
     * @param row   the row to check
     * @return  True if the row is completed. otherwise;
     *          False
     */
    private boolean isCompletedRow(int row)
    {
        for(int col=0; col<grid.getNumCols(); col++)
        {
            if(grid.get(new Location(row, col))==null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * clears a completed row
     * 
     * @precondition    the row is filled with blocks
     * @precondition    the row is in the range of [0, number of rows).
     * 
     * @param row   the given row to be cleared
     */
    private void clearRow(int row)
    {
        for(int col=0; col<grid.getNumCols(); col++)
        {
            Block blo = grid.get(new Location(row, col));
            blo.removeSelfFromGrid();
        }
        for(int row2 = row; row2>=1; row2--)
        {
            for(int col=0; col<grid.getNumCols(); col++)
            {
                Block blo = grid.get(new Location(row2-1, col));
                if(blo!=null)
                {
                    blo.moveTo(new Location(row2, col));
                }
            }
        }
    }
    
    /**
     * clears all completed rows in the game
     */
    private void clearCompletedRows()
    {
        int count = 0;
        for(int row=0; row<grid.getNumRows(); row++)
        {
            if(isCompletedRow(row))
            {
                clearRow(row);
                count++;
            }
        }
        score+=count;
        if(count==1)
        {
            points+=40*level;
        }
        else if(count==2)
        {
            points+=100*level;
        }
        else if(count==3)
        {
            points+=30*level;
        }
        else if(count==4)
        {
            points+=1200*level;
        }
        if(score>=10)
        {
            score-=10;
            level++;
        }
        display.setTitle("Tetris", points, level);
    }
    
    /**
     * holds the block in a hold grid when c is pressed.
     * Then puts the held block in the tetris game if there is one,
     * otherwise it will bring in the next block
     */
    @Override 
    public void cPressed()
    {
        if(getPaused())
        {
            return;
        }
        if(cPressed)
        {
            return;
        }
        else if(hold.getOccupiedLocations().size()==0)
        {
            holdTetrad=tetrad;
            holdTetrad.addToGrid(hold);
            tetrad = nextTetrad;
            tetrad.addToGrid(grid);
            nextTetrad = new Tetrad(next);
            nextTetrad.addToGrid(next);
        }
        else
        {
            MyBoundedGrid<Block> extra = new MyBoundedGrid<Block>(4, 4);
            Tetrad extraTetrad = holdTetrad;
            holdTetrad = tetrad;
            tetrad = extraTetrad;
            tetrad.addToGrid(extra);
            holdTetrad.addToGrid(hold);
            tetrad.addToGrid(grid);
        }
        holdDisplay.showBlocks();
        cPressed=true;
    }
    /**
     * pauses the game when x is pressed
     */
    @Override
    public void xPressed()
    {
        if(paused)
        {
            System.out.println("unpaused");
            paused = false;
        }
        else
        {
            System.out.println("paused");
            paused = true;
        }
    }
    
    /**
     * restarts the game when z is pressed
     */
    @Override
    public void zPressed()
    {
        System.out.println("game restarted");
        stopMusic();
        tetrad.addToGrid(new MyBoundedGrid<Block>(4, 4));
        nextTetrad.addToGrid(new MyBoundedGrid<Block>(4, 4));
        holdTetrad.addToGrid(new MyBoundedGrid<Block>(4, 4));
        //System.out.println("tetrads added to new grid");
        for(Location loc : grid.getOccupiedLocations())
        {
            grid.get(loc).removeSelfFromGrid();
        }
        //System.out.println("grid cleared");
        for(Location loc : hold.getOccupiedLocations())
        {
            hold.get(loc).removeSelfFromGrid();
        }
        //System.out.println("hold grid cleared");
        for(Location loc : next.getOccupiedLocations())
        {
            next.get(loc).removeSelfFromGrid();
        }
        //System.out.println("next tetrad grid cleared");
        score = 0;
        points = 0;
        level=1;
        //System.out.println("title set");
        display.setTitle("Tetris", score, level);
        tetrad = new Tetrad(grid);
        //System.out.println("new tetrad initialized");
        display.showBlocks();
        gameOver=false;
        nextTetrad = new Tetrad(next);
        nextDisplay.showBlocks();
        cPressed = false;
        //System.out.println("next tetrad initialized");
        holdTetrad = new Tetrad(new MyBoundedGrid<Block>(4, 4));
        holdDisplay.showBlocks();
        paused = false;
        //System.out.println("hold tetrad initialized");
        playMusic();
        //System.out.println("restarted");
    }
}
