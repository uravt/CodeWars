package CodeWars.engine;

public class Team
{
    public int team;
    private int numberIron;
    private int numberSilicon;

    public Team(int team)
    {
        this.team = team;
        this.numberIron = 0;
        this.numberSilicon = 0;
    }

    public Team(Team other)
    {
        this.team = other.team;
        this.numberIron = other.numberIron;
        this.numberSilicon = other.numberSilicon;
    }

    protected int getIron()
    {
        return numberIron;
    }
    protected int getSilicon()
    {
        return numberSilicon;
    }
}
