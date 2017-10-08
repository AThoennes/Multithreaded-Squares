import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JComponent
{
    MyThread threads[];

    public MyCanvas() {}

    public void paint(Graphics g)
    {
        for (int i = 0; i < threads.length; i ++)
        {
            MyRectangle tmp = threads[i].getRect();
            g.fillRect(tmp.getX(), tmp.getY(), tmp.getWidth(), tmp.getHeight());
        }
    }

    public void setThreads(MyThread[] threads)
    {
        this.threads = threads;
    }
}
