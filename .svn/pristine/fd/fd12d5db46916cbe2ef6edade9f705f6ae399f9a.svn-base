/*
 * TCSS 305 - Tetris
 */
package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import model.Board;

/**
 * Class that listens to specific keystrokes for calling movement methods of the Board.
 * @author Brandon Gaetaniello
 * @version 12/9/16
 */
public class KeyBoardListener extends KeyAdapter
{
    /**
     * String representation for down.
     */
    private static final String DOWN = "Down";
    
    /**
     * String representation for left.
     */
    private static final String LEFT = "Left";
    
    /**
     * String representation for right.
     */
    private static final String RIGHT = "Right";
    
    /**
     * String representation for drop.
     */
    private static final String DROP = "Drop";
    
    /**
     * String representation for rotate.
     */
    private static final String ROTATE = "Rotate";
    
    /**
     * String representation for pausing.
     */
    private static final String PAUSE = "Pause";
    
    /**
     * Map that holds the controls, with their specific key bindings.
     */
    protected Map<String, Integer> myControls;
    
    /**
     * The drawing panel object for drawing the game.
     */
    private final TetrisDrawingPanel myPanel;
    
    /**
     * The board object holding the game's internal board.
     */
    private Board myBoard;
    
    /**
     * Constructor for the listener class.
     * @param theBoard is the board object.
     * @param thePanel is the drawing panel.
     */
    public KeyBoardListener(final Board theBoard, final TetrisDrawingPanel thePanel)
    {
        super();
        myControls = new HashMap<String, Integer>();
        myBoard = theBoard;
        myPanel = thePanel;
        setDefaultControls();
    }
    
    /**
     * If the user wants a different grid size, the board needs to be reset.
     * @param theBoard is the new Board to be set
     */
    public void setBoard(final Board theBoard)
    {
        myBoard = theBoard;
    }
    
    /**
     * Sets the control map to default controls.
     */
    private void setDefaultControls()
    {
        myControls.put(DOWN, KeyEvent.VK_DOWN);
        myControls.put(LEFT, KeyEvent.VK_LEFT);
        myControls.put(RIGHT, KeyEvent.VK_RIGHT);
        myControls.put(DROP, KeyEvent.VK_SPACE);
        myControls.put(ROTATE, KeyEvent.VK_R);
        myControls.put(PAUSE, KeyEvent.VK_P);
    }
    
    /**
     * Gets the movement that needs to be made.
     * @param theKeyCode is the key that was pressed.
     * @return a string for the movement that needs to be made.
     */
    private String getMovement(final int theKeyCode)
    {
        String movement = "";
        // Iterates through the controls for the specific control options.
        for (final String key : myControls.keySet())
        {
            /**
             *  If the keystroke associated with the control option 
             *  is theEvent, the option need to be performed.
             */
            if (myControls.get(key) == theKeyCode)
            {
                movement = key;
            }
        }
        return movement;
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent)
    {
        theEvent.consume();
        final String movement = getMovement(theEvent.getKeyCode());
        /**
         *  This checks to see if the game is paused / game is over, if it isn't, 
         *  then it will move the Tetris pieces based on the key movement.
         */
        if (myPanel.isRunning())
        {
         // Calls the specific methods according to the action name.
            
            if (ROTATE.equals(movement))
            {
                myBoard.rotate();
            }  
            else if (DOWN.equals(movement))
            {
                myBoard.down();
            }
            else if (LEFT.equals(movement))
            {
                myBoard.left();
            }
            else if (RIGHT.equals(movement))
            {
                myBoard.right();
            }
            else if (DROP.equals(movement))
            {
                myBoard.drop();
            }
        }
        if (PAUSE.equals(movement))
        {
            myPanel.myGamePaused = !myPanel.myGamePaused;
            myPanel.repaint();
        }
    }
}
