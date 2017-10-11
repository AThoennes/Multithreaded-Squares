import javax.swing.*;
import java.awt.*;

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

    /**
     * empty constructor
     */
    public MyCanvas() {}

    /**
     * This paints all the squares that the threads are changing.
     *
     * @param g
     */
    public synchronized void paint(Graphics g)
    {
        for (int i = 0; i < threads.length; i ++)
        {
            MyRectangle tmp = threads[i].getRect();
            g.setColor(tmp.getColor());
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
}