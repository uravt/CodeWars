package CodeWars.engine;

public class RobotUser
{
    private RobotInfo robotInfo;
    protected RobotInfo spawned;
    GameWorld world;
    public int health;
    public int moveCooldown;
    public int actionCooldown;
    public int robotType;
    public Point position;

    protected RobotUser(RobotInfo assignedRobotInfo, GameWorld gw){
        robotInfo = assignedRobotInfo;
        spawned = null;
        world = gw;
        health = robotInfo.health;
        moveCooldown = robotInfo.cooldownMove;
        actionCooldown = robotInfo.cooldownAction;
        robotType = robotInfo.robotType;
        position = robotInfo.position;
    }

    //returns if a robot can move
    public boolean canMove(Direction dir){
        if(robotType == GameConstants.HQ) return false;
        Point destination = position.pointInDirection(dir);
        if(destination == null || !onMap(destination)) return false;
        MapTile destinationTile = destination.pointAsMapTile(world);
        if(destinationTile == null || destinationTile.robotInfoOnTile != null || !destinationTile.passable) return false;
        return moveCooldown < 10;
    }
    //tries to move, returns true if successful, returns false if fails (should throw an error, you need to check!)
    public boolean move(Direction dir) {
        if(robotType == GameConstants.HQ) return false;
        Point destination = position.pointInDirection(dir);
        if(destination == null || !onMap(destination)) return false;
        MapTile destinationTile = destination.pointAsMapTile(world);
        if(destinationTile == null || destinationTile.robotInfoOnTile != null || !destinationTile.passable) return false;
        if(moveCooldown < 10) return false;
        robotInfo.cooldownMove += GameConstants.COOLDOWN_MOVE[robotType];
        moveCooldown = robotInfo.cooldownMove;
        MapTile curTile = position.pointAsMapTile(world);
        curTile.robotInfoOnTile = null;
        destinationTile.robotInfoOnTile = robotInfo;
        robotInfo.position = destination;
        position = robotInfo.position;
        return true;
    }
    //returns if the robot can attack a point
    public boolean canAttack(Point p) {
        if(robotType == GameConstants.HQ) return false;
        if(p == null || !onMap(p)) return false;
        if(position.distanceSquaredTo(p) > GameConstants.VISION_RADIUS[robotType]) return false;
        MapTile destinationTile = p.pointAsMapTile(world);
        if(destinationTile == null || destinationTile.robotInfoOnTile == null || destinationTile.robotInfoOnTile.playerOwner == robotInfo.playerOwner || robotInfo.cooldownAction > 10) return false;
        return true;
    }
    //tries to attack, returns whether successful
    public boolean attack(Point p) {
        if(robotType == GameConstants.HQ) return false;
        if(p == null || !onMap(p)) return false;
        if(position.distanceSquaredTo(p) > GameConstants.VISION_RADIUS[robotType]) return false;
        MapTile destinationTile = p.pointAsMapTile(world);
        if(destinationTile == null || destinationTile.robotInfoOnTile == null || destinationTile.robotInfoOnTile.playerOwner == robotInfo.playerOwner) return false;
        robotInfo.cooldownAction += GameConstants.COOLDOWN_ATTACK[robotType];
        actionCooldown = robotInfo.cooldownAction;
        destinationTile.robotInfoOnTile.health -= GameConstants.ATTACK[robotType];
        if(destinationTile.robotInfoOnTile.health <= 0){
            destinationTile.robotInfoOnTile = null;
        }
        return true;
    }
    //returns whether you can mine a point
    public boolean canMine(Point p) {
        if(robotType == GameConstants.HQ) return false;
        if(p == null || !onMap(p)) return false;
        if(!p.isAdjacent(position)) return false;
        MapTile destinationTile = p.pointAsMapTile(world);
        if(destinationTile == null || destinationTile.robotInfoOnTile != null || (destinationTile.numIron == 0 && destinationTile.numSilicon == 0)) return false;
        if(actionCooldown <= 10) return false;
        return true;
    }
    //tries to mine a point, returns whether successful
    public boolean mine(Point p) {
        if(robotType == GameConstants.HQ) return false;
        if(p == null || !onMap(p)) return false;
        if(!p.isAdjacent(position)) return false;
        MapTile destinationTile = p.pointAsMapTile(world);
        if(destinationTile == null || destinationTile.robotInfoOnTile != null || (destinationTile.numIron == 0 && destinationTile.numSilicon == 0)) return false;
        if(actionCooldown <= 10) return false;
        actionCooldown += GameConstants.COOLDOWN_MINE[robotType];
        robotInfo.cooldownAction += GameConstants.COOLDOWN_MINE[robotType];
        if(destinationTile.numSilicon > 0){
            if(destinationTile.numSilicon >= 5){
                destinationTile.numSilicon -= 5;
                if(robotInfo.playerOwner == 1) world.teamA.setSilicon(world.teamA.getSilicon() + 5);
                else world.teamB.setSilicon(world.teamB.getSilicon() + 5);
            }
            else{
                if(robotInfo.playerOwner == 1) world.teamA.setSilicon(world.teamA.getSilicon() + destinationTile.numSilicon);
                else world.teamB.setSilicon(world.teamB.getSilicon() + destinationTile.numSilicon);
                destinationTile.numSilicon = 0;
            }
        }
        else{
            if(destinationTile.numIron >= 5){
                destinationTile.numIron -= 5;
                if(robotInfo.playerOwner == 1) world.teamA.setIron(world.teamA.getIron() + 5);
                else world.teamB.setIron(world.teamB.getIron() + 5);
            }
            else{
                if(robotInfo.playerOwner == 1) world.teamA.setIron(world.teamA.getIron() + destinationTile.numIron);
                else world.teamB.setIron(world.teamB.getIron() + destinationTile.numIron);
                destinationTile.numIron = 0;
            }
        }
        robotInfo.cooldownAction += GameConstants.COOLDOWN_MINE[robotType];
        actionCooldown = robotInfo.cooldownAction;
        return true;
    }
    //returns whether you can spawn a given robot type
    public boolean canSpawn(int robotIndex, Point p) {
        if(robotType != GameConstants.HQ) return false;
        if( p == null || !p.isAdjacent(p) || !onMap(p)) return false;
        MapTile destinationTile = p.pointAsMapTile(world);
        if(!destinationTile.passable || destinationTile.robotInfoOnTile != null) return false;
        int curIron;
        int curSilicon;
        int reqIron = GameConstants.IRON_COST[robotIndex];
        int reqSilicon = GameConstants.SILICON_COST[robotIndex];
        switch(robotInfo.playerOwner){
            case 1:
                curIron = world.teamA.getIron();
                curSilicon = world.teamA.getSilicon();
                break;
            case 2:
                curIron = world.teamB.getIron();
                curSilicon = world.teamB.getSilicon();
                break;
            default:
                return false;
        }
        if(curIron < reqIron) return false;
        if(curSilicon < reqSilicon) return false;
        return true;
    }
    //tries to spawn a robot, returns whether successful
    public boolean spawn(int robotIndex, Point p) {
        if(robotType != GameConstants.HQ) return false;
        if( p == null || !p.isAdjacent(p) || !onMap(p)) return false;
        MapTile destinationTile = p.pointAsMapTile(world);
        if(!destinationTile.passable || destinationTile.robotInfoOnTile != null) return false;
        int curIron;
        int curSilicon;
        int reqIron = GameConstants.IRON_COST[robotIndex];
        int reqSilicon = GameConstants.SILICON_COST[robotIndex];
        switch(robotInfo.playerOwner){
            case 1:
                curIron = world.teamA.getIron();
                curSilicon = world.teamA.getSilicon();
                break;
            case 2:
                curIron = world.teamB.getIron();
                curSilicon = world.teamB.getSilicon();
                break;
            default:
                return false;
        }
        if(curIron < reqIron) return false;
        if(curSilicon < reqSilicon) return false;
        switch(robotInfo.playerOwner){
            case 1:
                world.teamA.setIron(curIron - reqIron);
                world.teamA.setSilicon(curSilicon - reqSilicon);
                break;
            case 2:
                world.teamB.setIron(curIron - reqIron);
                world.teamA.setSilicon(curSilicon - reqSilicon);
                break;
            default:
                return false;
        }
        spawned = new RobotInfo(robotIndex, p, false, world);
        destinationTile.robotInfoOnTile = spawned;
        actionCooldown += 10;
        robotInfo.cooldownAction = actionCooldown;
        return true;
    }

    private boolean onMap(Point p){
        return p.x >= 0 && p.x < world.sizeX && p.y >= 0 && p.y < world.sizeY;
    }

}
