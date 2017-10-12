import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created Oct. 8, 2017
 *
 * This class acts as a canvas which the squares are drawn on.
 * It also contains an array of all threads in the program.
 * This array is used to repaint the canvas.
 *
 * @author Alex Thoennes
 */

public class MyCanvas extends JComponent
{
    // array to hold threads
    MyThread threads[];
    ArrayList<MyThread> threadsList;

    /**
     * This paints all the squares that the threads are changing.
     *
     * @param g
     */
    public synchronized void paint(Graphics g)
    {
        // check to see if threads was declared
        if (threads != null)
        {
            staticThreads(g);
        } // if it wasn't declared then try to use threadsList
        else if (threadsList != null)
        {
            dynamicThreads(g);
        }
    }

    /**
     * This method uses the array called threads to draw the threads it contains
     * to the canvas. You can not add new threads to this array.
     *
     * @param g
     */
    private void staticThreads(Graphics g)
    {
        for (int i = 0; i < threads.length; i ++)
        {
            // get the rect object from the current thread
            MyRectangle tmp = threads[i].getRect();

            // get the color that thread contains
            g.setColor(tmp.getColor());

            // now draw the rectangle
            g.fillRect(tmp.getX(), tmp.getY(), tmp.getWidth(), tmp.getHeight());
        }
    }

    /**
     * This method uses the array called threadsList to draw the threads it contains.
     * You are able to add threads to this list simply by clicking on the screen where
     * you want the thread to appear
     *
     * @param g
     */
    private void dynamicThreads(Graphics g)
    {
        for (int i = 0; i < threadsList.size(); i ++)
        {
            // get the rect object from the current thread
            MyRectangle tmp = threadsList.get(i).getRect();

            // get the color that thread contains
            g.setColor(tmp.getColor());

            // now draw the rectangle
            g.fillRect(tmp.getX(), tmp.getY(), tmp.getWidth(), tmp.getHeight());
        }
    }

    /**
     * This method sets the global threads array
     * to the passed in threads array.
     *
     * @param threads
     */
    public void setThreads(MyThread[] threads)
    {
        this.threads = threads;
    }

    /**
     * This method sets the thread list every
     * time the user clicks the screen
     *
     * @param threadsList
     */
    public void setThreads(ArrayList<MyThread> threadsList)
    {
        this.threadsList = threadsList;
    }
}