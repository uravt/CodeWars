package CodeWars.engine.client;
import javax.swing.*;

public class Client
{
    TileMap map = new TileMap();
    JFrame frame = new JFrame();

    public Client()
    {
        frame = new JFrame();
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

}
