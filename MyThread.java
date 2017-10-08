import java.util.Random;

/**
 * Created Oct. 8, 2017
 *
 * This thread class extends thread so that I can make my own run method. Thr run method is where the
 * thread moves the square across the screen.
 *
 * @author Alex Thoennes
 */

public class MyThread extends Thread
{
    // rect object that this thread can move
    MyRectangle rect;

    /**
     * Constructor that sets the rect object in this class
     *
     * @param rect
     */
    public MyThread (MyRectangle rect)
    {
        this.rect = rect;
    }

    @Override
    /**
     * Main method in each thread. This method will continuously move the
     * square around the screen. I will only move the square though if
     * you don't go out of the screen bounds.
     */
    public void run()
    {
        super.run();
        // grab the canvas from the main class
        MyCanvas canvas = Main.canvas;

        // reandom ibect used for direction and coordinates
        Random rand = new Random();

        // run until the user stops the process
        while (true)
        {
            // generate a new x and y coordinate for the square (the +1 is to
            // ensure you don't generate a zero)
            int tempX = rand.nextInt(rect.width) + 1;
            int tempY = rand.nextInt(rect.height) + 1;

            // there are only two directions you can move in. 0 is negative and 1 is positive
            int dirX = rand.nextInt(2);
            int dirY = rand.nextInt(2);

            // then calculate where the square would move to for each direction
            int negX = this.rect.getX() - tempX;
            int negY = this.rect.getY() - tempY;
            int posX = this.rect.getX() + tempX;
            int posY = this.rect.getY() + tempY;

            // now figure out which condition is satisfied so the square can move properly
            if (dirX == 0 && dirY == 0 && rect.hitEdge(negX, negY))
            {
                this.rect.setX(negX);
                this.rect.setY(negY);
            }
            else if (dirX == 1 && dirY == 1 && rect.hitEdge(posX, posY))
            {
                this.rect.setX(posX);
                this.rect.setY(posY);
            }
            else if (dirX == 1 && dirY == 0 && rect.hitEdge(posX, negY))
            {
                this.rect.setX(posX);
                this.rect.setY(negY);
            }
            else if (dirX == 0 && dirY == 1 && rect.hitEdge(negX, posY))
            {
                this.rect.setX(negX);
                this.rect.setY(posY);
            }

            // delay the repaint
            delay();

            // then display the moved squares
            canvas.repaint();
        }
    }

    /**
     * This method simply delays the process so the squares
     * don't appear to be bouncing around the screen
     */
    public void delay()
    {
        try
        {
            this.sleep(100); //1000 milliseconds is one second.
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * set the rect object
     * @return
     */
    public MyRectangle getRect() {
        return rect;
    }
}