package CodeWars.engine;
import CodeWars.Player.*;
import CodeWars.engine.client.Client;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Runner
{
    Client client = new Client();
    //constants
    static final protected int FINAL_TURN = 2000;

    //current turn number
    protected int turn;

    GameWorld world;

    
    //list of all of the robots
    ArrayList<RobotPlayer> robotPlayers;
    //robots created during the turn
    ArrayList<RobotPlayer> toAdd;
    //robots who died during the turn
    ArrayList<RobotPlayer> toRemove;

    //instantiates a runner object
    protected Runner(){
        world = new GameWorld(30, 30);
        robotPlayers = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        client = new Client();
    }

    //increments one turn
    protected void update(){
        for(RobotPlayer robotPlayer : robotPlayers){
            if(robotPlayer.alive()){
                RobotUser tempRobotUser = new RobotUser(robotPlayer.getRobot(), world);
                robotPlayer.run(tempRobotUser);
                if(tempRobotUser.spawned != null){
                    switch(robotPlayer.getPlayerID()){
                        case 1:
                            toAdd.add(new RobotPlayer(tempRobotUser.spawned, new Player1(1)));
                            break;
                        case 2:
                            toAdd.add(new RobotPlayer(tempRobotUser.spawned, new Player2(2)));
                            break;
                        default:
                            System.out.println("Something is wrong");
                            break;
                    }
                    tempRobotUser.spawned = null;
                }
            }
            else{
                toRemove.add(robotPlayer);
            }
        }
        //these should already be removed from the map
        for(RobotPlayer robotPlayer : toRemove){
            robotPlayers.remove(robotPlayer);
        }
        //these should already be added to the map
        robotPlayers.addAll(toAdd);
        turn++;
    }

    //returns whether the current game is active
    protected boolean active(){
        System.out.println(turn);
        return turn < FINAL_TURN;
    }
}

//pairs a robot and a player, so that each robot can have its own instance of player 1 or player 2
class RobotPlayer{
    private RobotInfo robot;
    private Player player;
    public RobotPlayer(RobotInfo r, Player p){
        robot = r;
        player = p;
    }
    protected boolean alive(){
        return robot.health > 0;
    }
    protected RobotInfo getRobot(){
        return robot;
    }
    protected int getPlayerID(){
        return player.getID();
    }
    protected void run(RobotUser r){
        player.run(r);
    }

}
