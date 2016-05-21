package pl.edu.us.pp.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Adrian on 2016-05-21.
 */
public class ExplosionFrame extends JFrame{
    private JPanel imagePanel;
    private JLabel imageLabel;

    public ExplosionFrame(String resource, int x, int y) throws IOException {
        setContentPane(imagePanel);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));
        setSize(550,550);
        setLocation(x,y);
        imageLabel.setIcon(new ImageIcon(getClass().getResource(resource)));

    }
}
