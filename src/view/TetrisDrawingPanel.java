/*
 * TCSS 305 - Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * Draws the Tetris pieces and game based on the board's internal state.
 * @author Brandon Gaetaniello
 * @version 12/9/16
 */
public class TetrisDrawingPanel extends JPanel implements Observer
{
    /**
     * The timers default delay.
     */
    protected static final int TIME_DELAY = 1000;
    
    /**
     * The serialVersionUID for this class.
     */
    private static final long serialVersionUID = 1338312898084283302L;
    
    /**
     * Constant used for spacing shapes.
     */
    private static final int SPACING = 15;
    
    /**
     * Constant for spacing the drawing panel.
     */
    private static final int PANEL_SPACING = 55;
    
    /**
     * Constant to space the Game Over string.
     */
    private static final int GAME_OVER_SPACING_DIVIDER = 3;
    
    /**
     * Font size for drawing strings.
     */
    private static final int FONT_SIZE = 15;
    
    /**
     * Constant for finding the top of the Board's game based on the string.
     */
    private static final int TOP_OF_GAME_MULTIPLIER = 4;
    
    /**
     * Constant for finding the top of the Board's game based on the string.
     */
    private static final int TOP_OF_GAME_SPACING = 10;
    
    /**
     * The default size of the JPanel.
     */
    private static final Dimension DEFAULT_PANEL_SIZE = new Dimension(215, 350);
    
    /**
     * A Rectangle2D object that shows the default boundaries of the game's area.
     */
    private static final Rectangle2D DEFAULT_GAME_AREA = 
                    new Rectangle2D.Double(30, 30, 150, 300);
    
    /**
     * A transparent gray used for a game over screen. 
     */
    private static final Color TRANSPARENT_GRAY = new Color(211, 211, 211, 50);
    
    /**
     * Boolean designating if the game is paused or not.
     */
    protected Boolean myGamePaused = false;
    
    /**
     * Boolean that holds the value of whether or not the game has started.
     */
    protected Boolean myGameStarted = false;
    
    /**
     * Boolean saying whether or not the game is over.
     */
    protected Boolean myGameOver = false;
    
    /**
     * A map holding the different types of blocks characters and their colors.
     */
    private final Map<Character, Color> myBlocks;
    
    /**
     * The Timer object used for the game.
     */
    private final Timer myTimer;
    
    /**
     * The KeyListener for drawing the game.
     */
    private final KeyBoardListener myKeyListener;
    
    /**
     * A integer that holds where the top of the game is for the Board.
     */
    private int myTopOfGame;
    
    /**
     * The board object holding the game's internal board.
     */
    private Board myBoard;
    
    /**
     * The panel's dimensions.
     */
    private Dimension myPanelSize;
    
    /**
     * The area of the game, designated by a rectangle.
     */
    private Rectangle2D myGameArea;
    
    /**
     * The graphics2D object for drawing the game.
     */
    private Graphics2D myG2D;
      
    /**
     * The toString of the board. 
     */
    private String myBoardString;
    
    /**
     * Constructor for initializing fields.
     * @param theBlocks is a Map holding the different Tetris blocks.
     * @param theBoard is the Board object.
     */
    public TetrisDrawingPanel(final Map<Character, Color> theBlocks, final Board theBoard)
    {
        super();
        myGameArea = DEFAULT_GAME_AREA;
        myPanelSize = DEFAULT_PANEL_SIZE;
        myBlocks = theBlocks;
        myBoard = theBoard;
        myKeyListener = new KeyBoardListener(myBoard, this);
        
        // Each iteration of the delay, the Board is told to do a down movement.
        myTimer = new Timer(TIME_DELAY, new ActionListener() 
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent) 
            {
                if (myGameStarted && !myGamePaused)
                {
                    myBoard.down();
                }                
            }
        });
        
        myBoardString = myBoard.toString();
        setup();     
    }
    
    /**
     * Returns the controls from the KeyListener.
     * @return a Map that holds the controls and their keystrokes.
     */
    public Map<String, Integer> getControls()
    {
        return myKeyListener.myControls;
    }
    /**
     * Returns the timer for the game.
     * @return the game's Timer. 
     */
    public Timer getTimer()
    {
        return myTimer;
    }
    
    /**
     * This tells the KeyListener if the game is stopped or not
     * (Paused / Game Over / Game hasn't started).
     * @return a Boolean of which the timer is running or not.
     */
    public Boolean isRunning()
    {
        return myTimer.isRunning() && myGameStarted;
    }
    
    /**
     * If the user wants a different grid size, the board needs to be reset 
     * and other fields need to be adjusted.
     * @param theBoard is the new Board to be set.
     */
    public void setBoard(final Board theBoard)
    {
        myBoard = theBoard;
        myBoard.addObserver(this);
        
        // The KeyListener also needs the board to be reset.
        myKeyListener.setBoard(myBoard);
        
        // Adjusts the game area and panel size to the new Board.
        myGameArea = new Rectangle2D.Double(DEFAULT_GAME_AREA.getX(), DEFAULT_GAME_AREA.getY(),
                                  myBoard.getWidth() * SPACING, myBoard.getHeight() * SPACING);
        
        myPanelSize = new Dimension((int) myGameArea.getWidth() + PANEL_SPACING,
                                    (int) myGameArea.getHeight() + PANEL_SPACING);
        
        setSize(myPanelSize);
        setPreferredSize(myPanelSize);
        repaint();
    }

    /**
     * Method for drawing the Pause screen and stopping the timer.
     */
    private void pauseGame()
    {
        myTimer.stop();
        myG2D.setColor(Color.GREEN);
        myG2D.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE + 2));
        myG2D.drawString("PAUSED", (int) myGameArea.getWidth() / 2,
                       getHeight() / 2);
        myG2D.draw(myGameArea);  
    }
    
    /**
     * Method for drawing the Game Over screen and stopping the timer.
     */
    private void gameOver()
    {
        myTimer.stop();
        myG2D.setColor(TRANSPARENT_GRAY);
        myG2D.fill(myGameArea);     
        myG2D.setColor(Color.RED);
        myG2D.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
        myG2D.drawString("GAME OVER", (int) myGameArea.getWidth() 
                               / GAME_OVER_SPACING_DIVIDER, SPACING);
        myG2D.setColor(Color.DARK_GRAY);
        myG2D.draw(myGameArea);
    }
    
    /**
     * Sets up the JPanel and the timer.
     */
    private void setup()
    {
        addKeyListener(myKeyListener);
        setPreferredSize(myPanelSize);
        setBackground(Color.BLACK);
        addFocusListener(new FocusCheck());
        setFocusable(true);        
        myTimer.setDelay(TIME_DELAY);
        myTimer.start();       
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics)
    {
        int yValue = 0;
        int xValue = 0;
        super.paintComponent(theGraphics);
        myG2D = (Graphics2D) theGraphics;
        myG2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
        
        // First, the game's boundaries are drawn.
        myG2D.setColor(Color.GRAY);
        myG2D.draw(myGameArea);
        
        /**
         *  Then, the game iterates through the board's toString. i is set to
         *  the TOP_OF_GAME constant so that it starts at the top of the game,
         *  rather than the top of the string.
         */
        for (int i = myTopOfGame; i < myBoardString.length(); i++)
        {
            xValue += SPACING;
            
            /**
             * Whenever there is a new line character, the y value 
             * is incremented and the x value is set to zero.
             */
            if (myBoardString.charAt(i) == '\n')
            {
                yValue += SPACING;
                xValue = 0;
            }
            
            /**
             * If the character is in the blocks map, a Tetris block will be drawn.
             */
            else if (myBlocks.containsKey(myBoardString.charAt(i)))
            {
                myG2D.setColor(myBlocks.get(myBoardString.charAt(i)));                         
                myG2D.fill(new Rectangle2D.Double(xValue, yValue, SPACING, SPACING));
                myG2D.setColor(Color.BLACK);
                myG2D.draw(new Rectangle2D.Double(xValue, yValue, SPACING, SPACING));
            }
        }
        
        /**
         *  This checks to see if the game is over, if it is, 
         *  it will draw a game over screen accordingly.
         */
        if (myGameOver)
        {
            gameOver();
        }
        
        /**
         *  If the game isn't over, and the game's paused, 
         *  the paintComponent will draw a pause screen.
         */
        else if (myGamePaused)
        {
            pauseGame();
        }
        
        /**
         * If the game isn't paused, and the timer isn't running, 
         * this will start the timer again.
         */
        else if (!myTimer.isRunning())
        {
            myTimer.start();
        }
    }
    
    @Override
    public void update(final Observable theObs, final Object theObj)
    {
        // This is when the Board class sends the drawing panel the toString.
        if (theObj instanceof String)
        {
            myBoardString = (String) theObj;
            myTopOfGame = (myBoard.getWidth() * TOP_OF_GAME_MULTIPLIER) 
                            + TOP_OF_GAME_SPACING;
        }
        
        // When the game is over, the Board class notifies the drawing panel.
        if (theObj instanceof Boolean)
        {
            myGameOver = (Boolean) theObj;
        }
        
        // After each update, the panel is repainted.
        repaint();
    }
    
    /**
     * This class stops the timer if the program isn't in focus,
     * and when the focus is regained, the timer is started again.
     */
    private class FocusCheck implements FocusListener
    {

        @Override
        public void focusGained(final FocusEvent theEvent)
        {
            myTimer.start();            
        }

        @Override
        public void focusLost(final FocusEvent theEvent)
        {
            myTimer.stop();            
        }
        
    }
}
