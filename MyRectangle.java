public class MyRectangle
{
    int x;
    int y;
    int width;
    int height;
    int screenWidth;
    int screenHeight;

    MyRectangle (int x, int y, int width, int height, int screenWidth, int screenHeight)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public boolean hitEdge(int newX, int newY)
    {
        if (newX > 0)
        {
            return true;
        }
        else if (newX < 800)
        {
            return true;
        }
        else if (newY > 0)
        {
            return true;
        }
        else if (newY < 800)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
