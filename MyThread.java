import java.util.Random;

public class MyThread extends Thread
{
    MyRectangle rect;

    public MyThread (MyRectangle rect)
    {
        this.rect = rect;
    }

    @Override
    public void run()
    {
        super.run();
        MyCanvas canvas = Main.canvas;
        Random rand = new Random();

        while (true)
        {
            int tempX = rand.nextInt(21) + 1;
            int tempY = rand.nextInt(21) + 1;

            int dirX = rand.nextInt(2);
            int dirY = rand.nextInt(2);

            int negX = this.rect.getX() - tempX;
            int negY = this.rect.getY() - tempY;
            int posX = this.rect.getX() + tempX;
            int posY = this.rect.getY() + tempY;

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

            delay();

            canvas.repaint();
        }
    }

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

    public MyRectangle getRect() {
        return rect;
    }
}
