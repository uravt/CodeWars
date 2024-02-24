package CodeWars.engine;

import CodeWars.engine.Runner;

public class Main
{
    public static void main(String[] args)
    {
        Runner runner = new Runner();
        while(runner.active()){
            runner.update();
        }
    }
}
