package CodeWars.engine.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TilePanel extends JPanel
{
    public boolean traversable;
    public Tile parentTile;
    public TilePanel(int size, int xPos, int yPos, boolean traversable, Tile parentTile, UI ui)
    {
        super();
        setLocation(xPos, yPos);
        setSize(size,size);
        setBorder(BorderFactory.createLineBorder(Color.black));
        //setMinimumSize(new Dimension(size, size));

        this.traversable = traversable;
        this.parentTile = parentTile;

        if(traversable)
        {
            setBackground(Color.WHITE);
        }
        else
        {
            setBackground(Color.RED);
        }

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                setTraversable(!getTraversable());
                updateParentTile();
                if(getTraversable())
                {
                    setBackground(Color.WHITE);
                }
                else
                {
                    setBackground(Color.RED);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                ui.mousePosition.setText("Position: " + parentTile.x + ", " + parentTile.y);
            }
        });
    }

    public void setTraversable(boolean traversable)
    {
        this.traversable = traversable;
    }

    public boolean getTraversable()
    {
        return traversable;
    }

    public void updateParentTile()
    {
        parentTile.traversable = traversable;
    }
}
