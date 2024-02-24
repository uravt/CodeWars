package CodeWars.engine;

public class GameWorld
{
    protected int sizeX;
    protected int sizeY;

    protected MapTile[][] gameWorld;

    protected GameWorld(int sizeX, int sizeY)
    {
        gameWorld = new MapTile[sizeY][sizeX];
    }

    protected GameWorld(GameWorld other)
    {
        MapTile[][] gameWorld =  new MapTile[other.sizeY][other.sizeX];
        MapTile[][] otherGameWorld = other.getGameWorld();
        for(int i = 0; i < gameWorld.length; i++)
        {
            for(int j = 0; j < gameWorld[j].length;j++)
            {
                gameWorld[i][j] = new MapTile(otherGameWorld[i][j]);
            }
        }
        this.sizeX = other.sizeX;
        this.sizeY = other.sizeY;
        this.gameWorld = gameWorld;
    }

    protected MapTile[][] getGameWorld()
    {
        return gameWorld;
    }
}
