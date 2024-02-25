package CodeWars.engine.client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TilePanel extends JPanel
{
    public boolean traversable;
    public Tile parentTile;
    private Image image;
    public TilePanel(int size, int xPos, int yPos, boolean traversable, Tile parentTile, UI ui)
    {
        super();
        setLocation(xPos, yPos);
        setSize(size,size);
        this.setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));

        this.traversable = traversable;
        this.parentTile = parentTile;

        if(traversable)
        {
            JLabel label = new JLabel("\uD83E\uDE96");
            this.add(label);
            this.repaint();
        }
        else
        {
            setBackground(Color.RED);
        }

        addMouseListener(new MouseAdapter()
        {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }


}


