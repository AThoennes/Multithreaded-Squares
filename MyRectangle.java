/**
 * Created Oct. 8, 2017
 *
 * This is a basic rectangle class. All a rectangle is able to do
 * is check if it hit the edge of the screen and modify it's current
 * values. Each thread is given an instance of this class which it then
 * moves around the screen.
 *
 * @author Alex Thoennes
 */

public class MyRectangle
{
    // all variables needed to create a rectangle
    int x;
    int y;
    int width;
    int height;
    int screenWidth;
    int screenHeight;


    /**
     * This constructor creates a basic rectangle. Each rectangle consists of an x and y coordinate,
     * a width and height attribute, and each rectangle knows the size of the screen. The screen size
     * is used in the hitEdge method.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param screenWidth
     * @param screenHeight
     */
    MyRectangle (int x, int y, int width, int height, int screenWidth, int screenHeight)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     *  Method that takes in the new coordinates where the rectangle
     *  will move to and returns if it will either hit or go beyond
     *  the edge of the screen.
     *
     * @param newX
     * @param newY
     * @return
     */
    public boolean hitEdge(int newX, int newY)
    {
        return (newX > 0 && newX < screenWidth-width && newY > 0 && newY < screenHeight-height);
    }

    /**
     * get the X value of this rectangle
     *
     * @return
     */
    public int getX()
    {
        return x;
    }

    /**
     * set the X value of this rectangle
     *
     * @param x
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * get the Y value of this rectangle
     *
     * @return
     */
    public int getY()
    {
        return y;
    }


    /**
     * set the Y value of this rectangle
     *
     * @param y
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * get the width of this rectangle
     *
     * @return
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * get the height of this rectangle
     *
     * @return
     */
    public int getHeight()
    {
        return height;
    }
}