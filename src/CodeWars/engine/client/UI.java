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
    public JLabel mousePosition;
    public int size;
    public UI(int size)
    {
        this.size = size;

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

        mousePosition = new JLabel("Mouse Position: ");
        buttonsPanel.add(mousePosition);

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

        }
    }

    class ResetAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    class RegenAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }
}
