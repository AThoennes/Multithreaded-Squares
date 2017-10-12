import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
        // create a canvas to be added to the frame
        canvas = new MyCanvas();

        // create a frame and set up it's properties
        JFrame frame = new JFrame("Multithreaded Moving Squares");
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add the canvas to the frame
        frame.getContentPane().add(canvas);

        // array of buttons that are displayed on the JOptionpane
        // static means you set the number of threads to draw squares and it can not change
        // dynamic means you can click the screen and add threads as you click
        //                      0          1
        String[] buttons = {"Static", "Dynamic"};

        // ask the user if they want static or dynamic threads
        int ans = JOptionPane.showOptionDialog(
                null,
                "Static means you set the number of " +
                        "threads to draw squares and it can not change\n" +
                        "Dynamic means you can click the screen and add threads as you click",
                "Which type of thread",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons);

        if (ans == 0)
        {
            // static threads
            staticThreads(canvas);

            // show the frame
            frame.setVisible(true);
        }
        else if (ans == 1)
        {
            // dynamic threads
            dyanmicThreads(canvas);

            // show the frame
            frame.setVisible(true);
        }
        else
        {
            // if you don't choose either option then
            // exit the program
            System.exit(0);
        }
    }

    /**
     * Creates and starts the static threads
     *
     * @param c
     */
    private static void staticThreads(MyCanvas c)
    {
        // prompt the user for however many threads they want
        int numOfThreads = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "How many threads?",
                "Multithreaded Moving Squares",
                1));

        // create a threads array that will hold all the threads running
        MyThread threads[] = createThreads(numOfThreads);

        // give the canvas a copy of the threads
        c.setThreads(threads);

        // now start running all the threads
        startThreads(threads);
    }

    /**
     * Creates and starts the dynamic threads
     *
     * @param c
     */
    private static void dyanmicThreads(MyCanvas c)
    {
        ArrayList<MyThread> threads = new ArrayList<>();

        // dynamic threads
        c.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // random object used to determine color of square
                Random rand = new Random();

                // when you click the screen, a new thread is created and the square moves around the canvas
                threads.add(new MyThread(new MyRectangle(e.getX(), e.getY(), SQUARE_SIZE,
                        SQUARE_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT, rand.nextInt(9))));

                // immediately start the thread
                threads.get(threads.size() - 1).start();

                // readjust the threads array list to accommodate the new thread and the old ones
                canvas.setThreads(threads);
            }

            //unused implemented methods
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
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
            threads[i] = new MyThread(new MyRectangle(x, y, SQUARE_SIZE, SQUARE_SIZE,
                    SCREEN_WIDTH, SCREEN_HEIGHT, rand.nextInt(9)));
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