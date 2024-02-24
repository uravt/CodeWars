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

    protected GameWorld deepCopy(GameWorld other)
    {
        for(int i = 0; i < gameWorld.length; i++)
        {
            for(int j = 0; j < gameWorld[j].length;j++)
            {

            }
        }
        this.gameWorld = gameWorld;
        return null;
    }

    protected MapTile[][] getGameWorld()
    {
        return gameWorld;
    }
}
