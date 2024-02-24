package CodeWars.engine.client;
import javax.swing.*;
import java.awt.*;

public class TileMap
{
    public static final int DEFAULT_MAP_SIZE = 25;

    private int size;
    private MapTilePanel[][] world;
    private JFrame frame;
    private JPanel containerPanel;

    public TileMap()
    {
        frame = new JFrame("Code Wars");
        size = 25;
        world = new MapTilePanel[25][25]; //randomize later
        containerPanel = new JPanel();
        containerPanel.setLayout(null);
        containerPanel.setSize(frame.getWidth(),frame.getHeight());
        Dimension dimensions = frame.getSize();
        double startX = dimensions.getWidth() / 2f - dimensions.getHeight() / 2.23f;
        double endX = dimensions.getWidth() / 2f + dimensions.getHeight() / 2.23f;
        int currX = (int) startX;
        int currY = 0;
        int xIncrement = (int) (endX - startX) / size;
        int yIncrement = (int) (endX - startX) / size;
        for(int i = 0; i<size; i++)
        {
            for(int l = 0; l<size; l++)
            {
                world[i][l] = new MapTilePanel(xIncrement, l, world.length - i - 1, false);
                containerPanel.add(world[i][l]);
                containerPanel.repaint();
                currX += xIncrement;
            }
            currX = (int) startX;
            currY += yIncrement;
            }
        frame.add(containerPanel);
        frame.repaint();
    }
}
