package CodeWars.engine;

import CodeWars.engine.client.Client;

public class Main
{
    public static void main(String[] args)
    {
        Runner runner = new Runner();
        Client client = new Client(runner);
        /*while(runner.active()){
            runner.update();
        }*/
    }
}
