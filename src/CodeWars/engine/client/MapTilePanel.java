package CodeWars.engine.client;
import CodeWars.engine.*;
import javax.swing.*;
import java.awt.*;

public class MapTilePanel extends JPanel
{
    public MapTile tile1;
    public boolean traversable;

    public MapTilePanel(int size, int xPos, int yPos, boolean traversable)
    {
        super();
        setLocation(xPos, yPos);
        setSize(size, size);
        this.traversable = traversable;
        setBorder(BorderFactory.createLineBorder(Color.black));
        if(traversable)
        {
            setBackground(Color.WHITE);
        }
        else
        {
            setBackground(Color.RED);
        }

    }
}
