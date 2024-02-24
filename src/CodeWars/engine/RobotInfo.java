package CodeWars.engine;

public class RobotInfo
{
    protected int robotType;
    protected GameWorld gameWorld;
    protected double health;
    protected int attack;
    protected int siliconCost;
    protected int ironCost;
    protected Point position;
    protected int cooldownMove;
    protected int cooldownAttack;
    protected int cooldownMine;
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
        this.cooldownAttack = GameConstants.COOLDOWN_ATTACK[robotType];
        this.cooldownMine = GameConstants.COOLDOWN_MINE[robotType];
        this.visionRadius = GameConstants.VISION_RADIUS[robotType];
        this.isBuilding = isBuilding;
        this.gameWorld = gameWorld;
    }

    public RobotInfo(RobotInfo other)
    {
        this.robotType = other.robotType;
        this.health = GameConstants.HEALTH[robotType];
        this.attack = GameConstants.ATTACK[robotType];
        this.siliconCost = GameConstants.SILICON_COST[robotType];
        this.ironCost = GameConstants.IRON_COST[robotType];
        this.position = new Point(other.position);
        this.cooldownMove = GameConstants.COOLDOWN_MOVE[robotType];
        this.cooldownAttack = GameConstants.COOLDOWN_ATTACK[robotType];
        this.cooldownMine = GameConstants.COOLDOWN_MINE[robotType];
        this.visionRadius = GameConstants.VISION_RADIUS[robotType];
        this.isBuilding = isBuilding;
    }
}
