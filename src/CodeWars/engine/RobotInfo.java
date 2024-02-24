package CodeWars.engine;

public class RobotInfo
{
    protected int robotType;
    protected GameWorld gameWorld;
    protected int health;
    protected int attack;
    protected int siliconCost;
    protected int ironCost;
    protected Point position;
    protected int cooldownMove;
    protected int cooldownAction;
    protected int visionRadius;
    protected int playerOwner;
    protected boolean isBuilding;


    protected RobotInfo(int robotType, Point position, boolean isBuilding, GameWorld gameWorld)
    {
        this.robotType = robotType;
        this.health = GameConstants.HEALTH[robotType];
        this.attack = GameConstants.ATTACK[robotType];
        this.siliconCost = GameConstants.SILICON_COST[robotType];
        this.ironCost = GameConstants.IRON_COST[robotType];
        this.position = position;
        this.cooldownMove = GameConstants.COOLDOWN_MOVE[robotType];
        this.visionRadius = GameConstants.VISION_RADIUS[robotType];
        this.isBuilding = isBuilding;
        this.gameWorld = gameWorld;
    }

    public RobotInfo(RobotInfo other)
    {
        this.robotType = other.robotType;
        this.health = other.health;
        this.attack = other.attack;
        this.siliconCost = GameConstants.SILICON_COST[robotType];
        this.ironCost = GameConstants.IRON_COST[robotType];
        this.position = new Point(other.position);
        this.cooldownMove = other.cooldownMove;
        this.cooldownAction = other.cooldownAction;
        this.visionRadius = GameConstants.VISION_RADIUS[robotType];
        this.isBuilding = isBuilding;
    }
}
