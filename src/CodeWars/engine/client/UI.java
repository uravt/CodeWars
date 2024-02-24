package CodeWars.engine.client;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
//870896112
public class UI
{
    public JFrame frame;
    public JPanel buttonsPanel;
    public Map map;
    public JTextField sizeText;
    public JTextField seedText;
    public JLabel mousePosition;
    public int size;
    public int seed;
    public UI()
    {
        frame = new JFrame("PathfindingLab");
        frame.setSize(1000,800);
        frame.setLayout(null);
        //frame.setResizable(false);

        JMenu menu;
        JMenuItem load = new JMenuItem(new LoadAction());
        JMenuItem save = new JMenuItem(new SaveAction());
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("File");
        menu.add(load);
        menu.add(save);
        mb.add(menu);
        frame.setJMenuBar(mb);


        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setBounds(0,0, 141, 800);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartAction());
        buttonsPanel.add(startButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetAction());
        buttonsPanel.add(resetButton);

        JButton regenButton = new JButton("Regenerate");
        regenButton.addActionListener(new RegenAction());
        buttonsPanel.add(regenButton);

        sizeText = new JTextField("" + Map.DEFAULT_MAP_SIZE);
        sizeText.setPreferredSize( new Dimension(200,50));
        sizeText.setMaximumSize(sizeText.getPreferredSize());
        buttonsPanel.add(sizeText);

        seedText = new JTextField("" + Map.DEFAULT_MAP_SEED);
        seedText.setPreferredSize( new Dimension(200,30));
        seedText.setMaximumSize(sizeText.getPreferredSize());
        buttonsPanel.add(seedText);

        mousePosition = new JLabel("Mouse Position: ");
        buttonsPanel.add(mousePosition);

        size = Integer.parseInt(sizeText.getText());
        seed = Integer.parseInt(seedText.getText());

        frame.add(buttonsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setMap(Map map)
    {
        this.map = map;
    }

    class LoadAction extends AbstractAction
    {
        public LoadAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser j = new JFileChooser();
            j.setFileFilter(new FileNameExtensionFilter("*.plmap", "plmap"));
            int result = j.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = j.getSelectedFile();
                try
                {
                    map.loadFile(selectedFile);
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    class SaveAction extends AbstractAction
    {
        public SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser j = new JFileChooser();
            j.setFileFilter(new FileNameExtensionFilter("*.plmap", "plmap"));
            int result = j.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                try
                {
                    FileWriter fw = new FileWriter(j.getSelectedFile().getName() + ".plmap");
                    fw.write(map.saveString());
                    fw.close();
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    class StartAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            map.resetHighlights();
            List<Point> points = PathfindingDriver.shortestPath(map, new Point(0,0), new Point(map.getSize() - 1, map.getSize() - 1));
            map.highlightTiles(points, new Color(0,255,255));
        }
    }

    class ResetAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            map.resetHighlights();
        }
    }

    class RegenAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String tempSizeText = sizeText.getText();
            String tempSeedText = seedText.getText();

            try
            {
                size = Integer.parseInt(tempSizeText);
                size = Math.min(Math.max(size, 10), 50);

                sizeText.setText("" + size);

                if(Integer.parseInt(tempSeedText) == seed)
                {
                    seed = (int) ((Math.random() * ((long) Integer.MAX_VALUE - Integer.MIN_VALUE)) + Integer.MIN_VALUE);
                    seedText.setText("" + seed);
                }
                else
                {
                    seed = Integer.parseInt(tempSeedText);
                }

                map.setSize(size);
                map.setSeed(seed);

                map.regenMap();
            }
            catch(Exception exception)
            {
               throw new RuntimeException("Size or Seed cannot be parsed!");
            }
        }
    }
}
