package CodeWars.engine.client;
public class Tile
{
    public TilePanel tile;
    public int x;
    public int y;
    public boolean traversable;

    public Tile(int size, int xPos, int yPos, int xCoord, int yCoord, boolean traversable, UI ui)
    {
        this.tile = new TilePanel(size, xPos, yPos, traversable, this, ui);
        this.x = xCoord;
        this.y = yCoord;
        this.traversable = traversable;
    }
}
