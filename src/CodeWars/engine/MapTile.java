package CodeWars.engine;

public class MapTile
{
    boolean hasMetalDeposit;
    boolean hasSiliconDeposit;
    boolean isPassable;
    //This value is null if no Robot is present on the tile
    RobotInfo robotInfoOnTile;
    //location of the tile
    Point mapLocation;

    public MapTile(boolean hasMetalDeposit, boolean hasSiliconDeposit, boolean isPassable, RobotInfo robotInfoOnTile, Point mapLocation)
    {
        this.hasMetalDeposit = hasMetalDeposit;
        this.hasSiliconDeposit = hasSiliconDeposit;
        this.isPassable = isPassable;
        this.robotInfoOnTile = robotInfoOnTile;
        this.mapLocation = mapLocation;
    }
}
