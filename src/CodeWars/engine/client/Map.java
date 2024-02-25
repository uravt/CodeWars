package CodeWars.engine.client;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Map
{
    public static final int DEFAULT_MAP_SIZE = 25;
    public static final int DEFAULT_MAP_SEED = 0;
    public static final float DEFAULT_DIFFICULTY = 0.4f;
    private Random rng;
    private int size;
    private Tile[][] world;
    private int seed;
    private JFrame frame;
    public JPanel containerPanel;
    public UI ui;

    public Map(UI ui)
    {
        this.frame = ui.frame;
        this.size = ui.size;
        this.ui = ui;
        rng = new Random(seed);
        world = new Tile[size][size];
        ui.setMap(this);

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
        for(int i = 0; i < world.length; i++)
        {
            for(int j = 0; j < world[i].length; j++)
            {
                world[i][j] = new Tile(xIncrement, currX,currY, j, world.length - i - 1, true, ui);
                containerPanel.add(world[i][j].tile);
                containerPanel.revalidate();
                containerPanel.repaint();
                currX += xIncrement;
            }
            currX = (int) startX;
            currY += yIncrement;
        }

        frame.add(containerPanel);
        frame.repaint();
    }

    public void loadFile(File loadFile) throws IOException
    {
        String[] fileLines = Utilities.readFile(loadFile);
        size = fileLines.length;
        ui.size = size;
        ui.sizeText.setText("" + size);
        int[][] newWorld = new int[size][size];
        for(int i = 0; i < fileLines.length; i++)
        {
            String[] split = fileLines[i].split(" ");
            for(int j = 0; j < split.length; j++)
            {
                newWorld[i][j] = Integer.parseInt(split[j]);
            }
        }

        frame.remove(containerPanel);
        containerPanel = new JPanel();
        containerPanel.setLayout(null);
        containerPanel.setSize(frame.getWidth(),frame.getHeight());

        world = new Tile[size][size];

        Dimension dimensions = frame.getSize();
        double startX = dimensions.getWidth() / 2f - dimensions.getHeight() / 2.23f;
        double endX = dimensions.getWidth() / 2f + dimensions.getHeight() / 2.23f;
        int currX = (int) startX;
        int currY = 0;
        int xIncrement = (int) (endX - startX) / size;
        int yIncrement = (int) (endX - startX) / size;
        for(int i = 0; i < newWorld.length; i++)
        {
            for(int j = 0; j < newWorld[i].length; j++)
            {
                world[i][j] = new Tile(xIncrement, currX,currY, j, world.length - i - 1,
                        newWorld[i][j] == 1, ui);
                containerPanel.add(world[i][j].tile);

                currX += xIncrement;
            }
            currX = (int) startX;
            currY += yIncrement;
        }
        frame.add(containerPanel);
        frame.repaint();
    }

    public void loadFile(String filePath) throws IOException
    {
        String[] fileLines = Utilities.readFile(filePath);
        size = fileLines.length;
        int[][] newWorld = new int[size][size];
        for(int i = 0; i < fileLines.length; i++)
        {
            String[] split = fileLines[i].split(" ");
            for(int j = 0; j < split.length; j++)
            {
                newWorld[i][j] = Integer.parseInt(split[j]);
            }
        }

        frame.remove(containerPanel);
        containerPanel = new JPanel();
        containerPanel.setLayout(null);
        containerPanel.setSize(frame.getWidth(),frame.getHeight());

        world = new Tile[size][size];

        Dimension dimensions = frame.getSize();
        double startX = dimensions.getWidth() / 2f - dimensions.getHeight() / 2.23f;
        double endX = dimensions.getWidth() / 2f + dimensions.getHeight() / 2.23f;
        int currX = (int) startX;
        int currY = 0;
        int xIncrement = (int) (endX - startX) / size;
        int yIncrement = (int) (endX - startX) / size;
        for(int i = 0; i < newWorld.length; i++)
        {
            for(int j = 0; j < newWorld[i].length; j++)
            {
                world[i][j] = new Tile(xIncrement, currX,currY, j, world.length - i - 1,
                        newWorld[i][j] == 1, ui);
                containerPanel.add(world[i][j].tile);

                currX += xIncrement;
            }
            currX = (int) startX;
            currY += yIncrement;
        }
        frame.add(containerPanel);
        frame.repaint();
    }

    public void regenMap()
    {
        size = ui.size;
        frame.remove(containerPanel);
        containerPanel = new JPanel();
        containerPanel.setLayout(null);
        containerPanel.setSize(frame.getWidth(),frame.getHeight());

        world = new Tile[size][size];

        Dimension dimensions = frame.getSize();
        double startX = dimensions.getWidth() / 2f - dimensions.getHeight() / 2.23f;
        double endX = dimensions.getWidth() / 2f + dimensions.getHeight() / 2.23f;
        int currX = (int) startX;
        int currY = 0;
        int xIncrement = (int) (endX - startX) / size;
        int yIncrement = (int) (endX - startX) / size;
        for(int i = 0; i < world.length; i++)
        {
            for(int j = 0; j < world[i].length; j++)
            {
                if(rng.nextFloat() > 1 - DEFAULT_DIFFICULTY)
                {
                    world[i][j] = new Tile(xIncrement, currX,currY, j, world.length - i - 1, false, ui);
                }
                else
                {
                    world[i][j] = new Tile(xIncrement, currX,currY, j, world.length - i - 1, true, ui);
                }
                containerPanel.add(world[i][j].tile);

                currX += xIncrement;
            }
            currX = (int) startX;
            currY += yIncrement;
        }

        frame.add(containerPanel);
        frame.repaint();
    }

    private Point arrayIndexToPoint(int row, int col)
    {
        return new Point(col, world.length - row - 1);
    }

    public boolean onTheMap(Point p)
    {
        return (p.x < size && p.x >= 0 && p.y < size && p.y >= 0);
    }

    public boolean isTraversable(Point p)
    {
        int[] indices = pointToArrayIndex(p);
        return world[indices[0]][indices[1]].traversable;
    }

    public int[] pointToArrayIndex(Point p)
    {
        return new int[] {world.length - p.y - 1, p.x};
    }

    public void highlightTile(Point p, Color c)
    {
        int[] indices = pointToArrayIndex(p);
        world[indices[0]][indices[1]].tile.setBackground(c);
    }

    public void highlightTiles(List<Point> points, Color c)
    {
        for(Point p : points)
        {
            highlightTile(p, c);
        }
        frame.repaint();
    }

    public void resetHighlights()
    {
        for(int i = 0; i < world.length; i++)
        {
            for (int j = 0; j < world[i].length; j++)
            {
                if(world[i][j].traversable)
                {
                    world[i][j].tile.setBackground(Color.white);
                }
                else
                {
                    world[i][j].tile.setBackground(Color.red);
                }
            }
        }
        frame.repaint();
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
        ui.sizeText.setText("" + size);
        ui.size = size;
    }


    public String saveString()
    {
        String str = "";
        for(int i = 0; i < world.length; i++)
        {
            for(int j = 0; j < world[i].length; j++)
            {
                if(world[i][j].traversable)
                {
                    str += "1 ";
                }
                else
                {
                    str += "0 ";
                }
            }
            str += "\n";
        }
        return str;
    }

}

