import java.awt.Color;
/**
 * a Tetrad piece in the game of Tetris which can translate and rotate.
 * Tetrads come in seven varieties in the shapes of I, T, O, L, J, S, and Z
 * and are either red, gray, cyan, yellow, magenta, blue, and green
 * respectively.
 *
 * @author Aidan Wang
 * @version February 23, 2023
 */
public class Tetrad
{
    private MyBoundedGrid<Block> gr;
    private Block[] blocks;
    private boolean gameOver;
    private int rand;
    private boolean isO;
    /**
     * Constructs a Tetrad in the game of Tetris which can translate and rotate.
     * the constructed tetrad is a random one of 7 types and 
     * is put at the middle column in the top row of the grid.
     * 
     * @param grid  the grid the tetrad is in
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        gr = grid;
        rand = (int)(Math.random()*7);
        Color col;
        isO=false;
        Location[] locs = new Location[4];
        int numCol = gr.getNumCols()/2;
        if(rand == 0)
        {
            col = Color.RED;
            locs[0]=new Location(1, numCol);
            locs[1]=new Location(0, numCol);
            locs[2]=new Location(2, numCol);
            locs[3]=new Location(3, numCol);
        }
        else if(rand == 1)
        {
            col = Color.GRAY;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(0, numCol-1);
            locs[2]=new Location(0, numCol+1);
            locs[3]=new Location(1, numCol);
        }
        else if(rand == 2)
        {
            col = Color.CYAN;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(0, numCol-1);
            locs[2]=new Location(1, numCol);
            locs[3]=new Location(1, numCol-1);
            isO=true;
        }
        else if(rand == 3)
        {
            col = Color.YELLOW;
            locs[0]=new Location(1, numCol-1);
            locs[1]=new Location(0, numCol-1);
            locs[2]=new Location(2, numCol-1);
            locs[3]=new Location(2, numCol);
        }
        else if(rand == 4)
        {
            col = Color.MAGENTA;
            locs[0]=new Location(1, numCol);
            locs[1]=new Location(0, numCol);
            locs[2]=new Location(2, numCol);
            locs[3]=new Location(2, numCol-1);
        }
        else if(rand == 5)
        {
            col = Color.BLUE;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(1, numCol);
            locs[2]=new Location(0, numCol+1);
            locs[3]=new Location(1, numCol-1);
        }
        else
        {
            col = Color.GREEN;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(1, numCol);
            locs[2]=new Location(0, numCol-1);
            locs[3]=new Location(1, numCol+1);
        }
        blocks = new Block[4];
        for(int i=0; i<4; i++)
        {
            blocks[i]=new Block();
            blocks[i].setColor(col);
        }
        if(areEmpty(gr, locs))
        {
            addToLocations(gr, locs);
            gameOver=false;
        }
        else
        {
            gameOver=true;
        }
    }
    
    /**
     * Adds this tetrad to a given grid at the 
     * middle column in the top row of the grid.
     * 
     * @param grid  the grid this tetris will be added to
     */
    public void addToGrid(MyBoundedGrid<Block>grid)
    {
        removeBlocks();
        gr = grid;
        Color col;
        isO=false;
        Location[] locs = new Location[4];
        int numCol = gr.getNumCols()/2;
        if(rand == 0)
        {
            col = Color.RED;
            locs[0]=new Location(1, numCol);
            locs[1]=new Location(0, numCol);
            locs[2]=new Location(2, numCol);
            locs[3]=new Location(3, numCol);
        }
        else if(rand == 1)
        {
            col = Color.GRAY;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(0, numCol-1);
            locs[2]=new Location(0, numCol+1);
            locs[3]=new Location(1, numCol);
        }
        else if(rand == 2)
        {
            col = Color.CYAN;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(0, numCol-1);
            locs[2]=new Location(1, numCol);
            locs[3]=new Location(1, numCol-1);
            isO=true;
        }
        else if(rand == 3)
        {
            col = Color.YELLOW;
            locs[0]=new Location(1, numCol-1);
            locs[1]=new Location(0, numCol-1);
            locs[2]=new Location(2, numCol-1);
            locs[3]=new Location(2, numCol);
        }
        else if(rand == 4)
        {
            col = Color.MAGENTA;
            locs[0]=new Location(1, numCol);
            locs[1]=new Location(0, numCol);
            locs[2]=new Location(2, numCol);
            locs[3]=new Location(2, numCol-1);
        }
        else if(rand == 5)
        {
            col = Color.BLUE;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(1, numCol);
            locs[2]=new Location(0, numCol+1);
            locs[3]=new Location(1, numCol-1);
        }
        else
        {
            col = Color.GREEN;
            locs[0]=new Location(0, numCol);
            locs[1]=new Location(1, numCol);
            locs[2]=new Location(0, numCol-1);
            locs[3]=new Location(1, numCol+1);
        }
        blocks = new Block[4];
        for(int i=0; i<4; i++)
        {
            blocks[i]=new Block();
            blocks[i].setColor(col);
        }
        if(areEmpty(gr, locs))
        {
            addToLocations(gr, locs);
            gameOver=false;
        }
        else
        {
            gameOver=true;
        }
    }
    
    /**
     * returns if the game is over
     * 
     * @return  True if the game is over. otherwise;
     *          False
     */
    public boolean getGameOver()
    {
        return gameOver;
    }
    
    /**
     * Adds the tetrad to a given grid at a given Location
     * 
     * @param grid  the grid the tetrad will be added to
     * @param locs  the locations the blocks will be added to
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        gr = grid;
        for(int i=0; i<4; i++)
        {
            blocks[i].putSelfInGrid(gr, locs[i]);
        }
    }
    
    /**
     * Removes this tetrad's blocks from the grid
     * 
     * @return locs Array of the locations of the removed blocks
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for(int i=0; i<4; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;
    }
    
    /**
     * returns if the given locations are empty and valid on a given grid
     * 
     * @param locs  the given locations
     * @param grid  the given grid
     * @return  True if the locations are valid and empty. otherwise;
     *          False
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for(int i=0; i<4; i++)
        {
            if(!grid.isValid(locs[i])||grid.get(locs[i])!=null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * translates the tetrad by moving it a given amount of columns and rows
     * 
     * @param deltaRow  the number of rows to move it by
     * @param deltaCol  the number of columns to move it by
     * @return  True if the block could translate to the new location. otherwise;
     *          False
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] locs = removeBlocks();
        Location[] newLocs = new Location[4];
        for(int i=0; i<4; i++)
        {
            newLocs[i] = new Location(locs[i].getRow()+deltaRow, locs[i].getCol()+deltaCol);
        }
        if(areEmpty(gr, newLocs))
        {
            addToLocations(gr, newLocs);
            return true;
        }
        addToLocations(gr, locs);
        return false;
    }
    
    /**
     * rotates the tetrad
     * 
     * @return  True if the block was rotated. otherwise;
     *          False
     */
    public boolean rotate()
    {
        if(isO)
        {
            return true;
        }
        Location[] locs = removeBlocks();
        Location[] newLocs = new Location[4];
        Location center = locs[0];
        for(int i=0; i<4; i++)
        {
            newLocs[i] = new Location(center.getRow()-center.getCol()+locs[i].getCol(),
                                      center.getCol()+center.getRow()-locs[i].getRow());
        }
        if(areEmpty(gr, newLocs))
        {
            addToLocations(gr, newLocs);
            return true;
        }
        addToLocations(gr, locs);
        return false;
    }
}
