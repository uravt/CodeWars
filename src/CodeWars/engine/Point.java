package CodeWars.engine;

public class Point
{
    public int x;
    public int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Direction directionTo(Point other)
    {
        int dx = other.x - this.x;
        int dy = other.y - this.y;

        if(dx == 0 && dy > 0)
        {
            return Direction.NORTH;
        }
        else if (dx > 0 && dy > 0)
        {
            return Direction.NORTHEAST;
        }
        else if (dx > 0 && dy == 0)
        {
            return Direction.EAST;
        }
        else if (dx > 0 && dy < 0)
        {
            return Direction.SOUTHEAST;
        }
        else if (dx == 0 && dy < 0)
        {
            return Direction.SOUTH;
        }
        else if (dx < 0 && dy < 0)
        {
            return Direction.SOUTHWEST;
        }
        else if (dx < 0 && dy == 0)
        {
            return Direction.WEST;
        }
        else if (dx < 0 && dy > 0)
        {
            return Direction.NORTHWEST;
        }
        return Direction.NONE;
    }
}
