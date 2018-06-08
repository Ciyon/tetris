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
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Point;

/**
 * The panel for scoring information.
 * @author Brandon Gaetaniello
 * @version 12/9/16
 */
public class TetrisScoringPanel extends JPanel implements Observer
{
    /**
     * The serialVersionUID for this class.
     */
    private static final long serialVersionUID = 7541990530765544079L;

    /**
     * A constant that is one second in milliseconds.
     */
    private static final int ONE_SECOND = 1000;
    
    /**
     * Constant for the font size of the title.
     */
    private static final int TITLE_FONT_SIZE = 20;
    
    /**
     * Constant for the font size of larger strings.
     */
    private static final int LARGER_STRING_FONT_SIZE = 15;
    
    /**
     * Constant for the font size of smaller strings.
     */
    private static final int SMALL_STRING_FONT_SIZE = 13;
    
    /**
     * The maximum level that can be reached.
     */
    private static final int MAXIMUM_LEVEL = 5;
    
    /**
     * The amount of points received for clearing a line.
     */
    private static final int SCORING_MULTIPLIER = 100;
    
    /**
     * The delay to be decremented by each time a new level is reached.
     */
    private static final int LEVEL_DELAY_INCREASE = 175;
    
    /**
     * The amount of points needed to proceed to the next level.
     */
    private static final int LEVEL_POINTS = 500;
    
    /**
     * The size of the JPanel.
     */
    private static final Dimension PANEL_SIZE = new Dimension(200, 175);
    
    /**
     * A Point holding the value where the string "Score" needs to be drawn.
     */
    private static final Point TITLE_LOCATION = 
                    new Point(70, 20);
    
    /**
     * A Point holding the value where the string "Level:" needs to be drawn.
     */
    private static final Point LEVEL_STRING_LOCATION = 
                    new Point(5, 50);
    
    /**
     * A Point holding the value where the string "Points:" needs to be drawn.
     */
    private static final Point POINTS_STRING_LOCATION = 
                    new Point(5, 80);
     
    /**
     * A Point holding the value where the string "Lines Cleared:" needs to be drawn.
     */
    private static final Point LINES_CLEARED_STRING_LOCATION = 
                    new Point(5, 110);
    
    /**
     * A Point holding the value where the string "Current Level:" needs to be drawn.
     */
    private static final Point POINTS_NEXT_LEVEL_STRING_LOCATION = 
                    new Point(5, 140);
    
    /**
     * A Point holding the value where the string "Speed:" needs to be drawn.
     */
    private static final Point SPEED_STRING_LOCATION = 
                    new Point(5, 165);
    
    /**
     * A Point holding the value where the level needs to be drawn.
     */
    private static final Point LEVEL_LOCATION = 
                    new Point(50, 50);
    
    /**
     * A Point holding the value where the points needs to be drawn.
     */
    private static final Point POINTS_LOCATION = 
                    new Point(55, 80);
  
    /**
     * A Point holding the value where the amount of lines cleared needs to be drawn.
     */
    private static final Point LINES_CLEARED_LOCATION = 
                    new Point(105, 110);
    
    /**
     * A Point holding the value where the amount of points to proceed 
     * to the next level needs to be drawn.
     */
    private static final Point POINTS_NEXT_LEVEL_LOCATION = 
                    new Point(155, 140);
    /**
     * A Point holding the value where the current speed in seconds needs to be drawn.
     */
    private static final Point SPEED_LOCATION = 
                    new Point(55, 165);
    
    /**
     * The drawing panel object.
     */
    private final TetrisDrawingPanel myDrawingPanel;
       
    /**
     * An array holding the amount of lines cleared and which lines were cleared.
     */
    private Integer[] myScoringArray;
      
    /**
     * The current amount of points.
     */
    private int myPoints;
    
    /**
     * The current level.
     */
    private int myLevel;
    
    /**
     * The amount of lines that have been cleared.
     */
    private int myLinesCleared;
    
    /**
     * The amount of points to proceed to the next level.
     */
    private int myNextLevel;
    
    /**
     * Constructor for initializing fields.
     * @param theDrawingPanel is the game's drawing panel.
     */
    public TetrisScoringPanel(final TetrisDrawingPanel theDrawingPanel)
    {
        super();
        myDrawingPanel = theDrawingPanel;
        myPoints = 0;
        myLevel = 1;
        myLinesCleared = 0;
        myNextLevel = LEVEL_POINTS;
        
        setBackground(Color.BLACK);
        setPreferredSize(PANEL_SIZE);
    }
    
    /**
     * Method to reset the panel to default values.
     */
    public void reset()
    {
        myPoints = 0;
        myLevel = 1;
        myLinesCleared = 0;
        myNextLevel = LEVEL_POINTS;
    }
    
    /**
     * Method to compute the amount of points received.
     */
    private void computePoints()
    {
        if (myScoringArray != null)
        {
            /**
             *  If the scoring array is greater than 0, (line has been cleared), 
             *  then points can be added.
             */
            if (myScoringArray.length > 0)
            {
                myLinesCleared += myScoringArray.length;
                myPoints += myScoringArray.length * SCORING_MULTIPLIER; 
                
                // Resets the scoring array.
                myScoringArray = new Integer[0];             
            }
            
            /**
             *  If the amount of points received is enough to advance to the next level, 
             *  and it's currently not the max level, it advances the level and 
             *  increases the delay.
             */
            if ((myPoints / myLevel) >= LEVEL_POINTS && myLevel != MAXIMUM_LEVEL)
            {
                myLevel++;
                myDrawingPanel.getTimer().setDelay(myDrawingPanel.getTimer().getDelay()
                                                   - LEVEL_DELAY_INCREASE);
                
            }   
            // Sets the amount of points
            else if (myLevel == MAXIMUM_LEVEL)
            {
                myNextLevel = 0;
            }
            
            myNextLevel = (LEVEL_POINTS * myLevel) - myPoints;
        }       
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font(Font.SERIF, Font.BOLD, TITLE_FONT_SIZE));
        g2d.drawString("Score", TITLE_LOCATION.getX(), TITLE_LOCATION.getY());
        
        g2d.setFont(new Font(Font.SERIF, Font.BOLD, LARGER_STRING_FONT_SIZE));
        
        
        // Draws the title's for each of the scoring necessities.
        
        g2d.drawString("Level: ", LEVEL_STRING_LOCATION.getX(),
                       LEVEL_STRING_LOCATION.getY());
        
        g2d.drawString("Points: ", POINTS_STRING_LOCATION.getX(),
                       POINTS_STRING_LOCATION.getY());
        
        g2d.drawString("Lines Cleared: ", LINES_CLEARED_STRING_LOCATION.getX(),
                       LINES_CLEARED_STRING_LOCATION.getY());
        
        g2d.drawString("Points To Next Level: ", POINTS_NEXT_LEVEL_STRING_LOCATION.getX(),
                       POINTS_NEXT_LEVEL_STRING_LOCATION.getY());
        
        g2d.drawString("Speed :", SPEED_STRING_LOCATION.getX(), SPEED_STRING_LOCATION.getY());
        
        // If the game is running, the following code will execute.
        if (myDrawingPanel.isRunning())
        {
            computePoints();
            g2d.setFont(new Font(Font.SERIF, Font.BOLD, SMALL_STRING_FONT_SIZE));
            
            // Draws the scoring information
            g2d.drawString(Integer.toString(myPoints), POINTS_LOCATION.getX(),
                           POINTS_LOCATION.getY());
            
            g2d.drawString(Integer.toString(myLinesCleared), LINES_CLEARED_LOCATION.getX(),
                           LINES_CLEARED_LOCATION.getY());
            
            final double speed = (double) myDrawingPanel.getTimer().getDelay() / ONE_SECOND;
            final DecimalFormat df = new DecimalFormat("#.000");
            
            g2d.drawString(df.format(speed) + " seconds",
                           SPEED_LOCATION.getX(), SPEED_LOCATION.getY());
                       
            if (myLevel == MAXIMUM_LEVEL)
            {
                g2d.drawString(Integer.toString(myLevel) + " (Max Level)",
                               LEVEL_LOCATION.getX(),
                               LEVEL_LOCATION.getY());
                
                g2d.drawString("0", POINTS_NEXT_LEVEL_LOCATION.getX(),
                               POINTS_NEXT_LEVEL_LOCATION.getY());
            }           
            else
            {                        
                g2d.drawString(Integer.toString(myLevel), LEVEL_LOCATION.getX(),
                               LEVEL_LOCATION.getY());
                
                g2d.drawString(Integer.toString(myNextLevel), 
                               POINTS_NEXT_LEVEL_LOCATION.getX(),
                               POINTS_NEXT_LEVEL_LOCATION.getY());
            }
        }
    }
    
    @Override
    public void update(final Observable theObs, final Object theObj)
    {
        /**
         *  If a line is cleared, the Board class will tell the drawing panel
         *  which lines and how many were cleared
         */
        if (theObj instanceof Integer[])
        {
            myScoringArray = (Integer[]) theObj;  
        }
        repaint();
    }
}
