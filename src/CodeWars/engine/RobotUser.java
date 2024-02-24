package CodeWars.engine;

public class RobotUser
{
    private RobotInfo robotInfo;
    protected RobotInfo spawned;
    GameWorld world;

    protected RobotUser(RobotInfo assignedRobotInfo, GameWorld gw){
        robotInfo = assignedRobotInfo;
        spawned = null;
        world = gw;
    }

    //returns if a robot can move
    public boolean canMove(Direction dir){return false;}
    //tries to move, returns true if successful, returns false if fails (should throw an error, you need to check!)
    public boolean move(Direction dir) {return false;}
    //returns if the robot can attack a point
    public boolean canAttack(Point p) {return false;}
    //tries to attack, returns whether successful
    public boolean attack(Point p) {return false;}
    //returns whether you can mine a point
    public boolean canMine(Point p) {return false;}
    //tries to mine a point, returns whether successful
    public boolean mine(Point p) {return false;}
    //returns whether you can spawn a given robot type
    public boolean canSpawn(int robotIndex, Point p) {return false;}
    //tries to spawn a robot, returns whether successful
    public boolean spawn(int robotIndex, Point p) {return false;}

    private boolean onMap(Point p){
        return p.x >= 0 && p.x < world.sizeX && p.y >= 0 && p.y < world.sizeY;
    }

}
