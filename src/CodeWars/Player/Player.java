package CodeWars.Player;

import CodeWars.engine.RobotUser;

public abstract class Player
{
    int ID;
    Player(int playerID){
        ID = playerID;
    }
    public abstract void run(RobotUser user);
    public abstract int getID();
}
