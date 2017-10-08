import javax.swing.*;
import java.util.Random;

/**
 * Created Oct. 8, 2017
 *
 * This program is an attempt to illustrate multithreading through the use of
 * "moving" squares. This is done by creating as many threads as the user desires
 * and then having each control their own square. Each thread will randomly generate
 * both a direction and a new coordinate for the square to move to. This continues
 * forever until the user clicks the 'x' to close the frame.
 *
 * @author Alex Thoennes
 */

public class Main
{
    // canvas which the threads will draw their squares on
    public static MyCanvas canvas;

    // width of screen
    static final int SCREEN_WIDTH = 800;

    // height of screen
    static final int SCREEN_HEIGHT = 800;

    // size of one square (width and height are both 10)
    static final int SQUARE_SIZE = 10;

    public static void main(String [] args)
    {
        // create a canvas to be added the frame
        canvas = new MyCanvas();

        // prompt the user for however many threads they want
        int numOfThreads = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "How many threads?",
                "Multithreaded Moving Squares",
                1));

        // create a frame and set up it's properties
        JFrame frame = new JFrame("Multithreaded Moving Squares");
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add the canvas to the frame
        frame.getContentPane().add(canvas);

        // show the frame
        frame.setVisible(true);

        // create a threads array that will hold all the threads running
        MyThread threads[] = createThreads(numOfThreads);

        // give the canvas a copy of the threads
        canvas.setThreads(threads);

        // now start running all the threads
        startThreads(threads);
    }

    /**
     * This method accepts a number and creates that many
     * threads which are stored into an array. The array
     * of threads is then returned
     *
     * @param num
     * @return threads
     */
    private static MyThread[] createThreads(int num)
    {
        // random object
        Random rand = new Random();

        // initialize an empty array of threads
        MyThread threads[] = new MyThread[num];

        // now go through the array initialize each thread
        for (int i = 0; i < num; i ++)
        {
            // generate a random starting coordinate for the square associated with
            // current thread
            int x = rand.nextInt(SCREEN_WIDTH);
            int y = rand.nextInt(SCREEN_HEIGHT);
            threads[i] = new MyThread(new MyRectangle(x, y, SQUARE_SIZE, SQUARE_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT, rand.nextInt(9)));
        }

        // return the finalized array
        return threads;
    }

    /**
     * Method that goes through the threads
     * array and starts each thread's process
     *
     * @param threads
     */
    private static void startThreads(MyThread[] threads)
    {
        // go through the array and start each thread so the squares can move
        for (int i = 0; i < threads.length; i ++)
        {
            threads[i].start();
        }
    }
}