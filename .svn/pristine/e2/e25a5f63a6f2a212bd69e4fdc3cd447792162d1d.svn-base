/*
 * TCSS 305 - Tetris
 */
package view;

import java.awt.EventQueue;

/**
 * Driver class for Tetris.
 * @author Brandon Gaetaniello
 * @version 12/9/16
 */
public final class TetrisMain
{
    /**
     * Private constructor to prevent instantiation.
     */
    private TetrisMain()
    {
        throw new IllegalStateException();
    }
    
    /**
     * Used to run the GUI.
     * @param theArgs is unused.
     */
    public static void main(final String... theArgs)
    {
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new TetrisGUI();
            }
        });
    }
}
