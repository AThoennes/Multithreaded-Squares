import javax.swing.*;
import java.util.Random;

public class Main
{
    public static MyCanvas canvas;

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int SQUARE_SIZE = 10;

    public static void main(String [] args)
    {
        canvas = new MyCanvas();
        JFrame frame = new JFrame("Multithreaded Moving Squares");
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);

        MyThread threads[] = createThreads(30);
        canvas.setThreads(threads);
        startThreads(threads);
    }

    private static MyThread[] createThreads(int num)
    {
        Random rand = new Random();
        MyThread threads[] = new MyThread[num];

        for (int i = 0; i < num; i ++)
        {
            int x = rand.nextInt(801);
            int y = rand.nextInt(801);
            threads[i] = new MyThread(new MyRectangle(x, y, SQUARE_SIZE, SQUARE_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT));
        }

        return threads;
    }

    private static void startThreads(MyThread[] threads)
    {
        for (int i = 0; i < threads.length; i ++)
        {
            threads[i].start();
        }
    }
}
