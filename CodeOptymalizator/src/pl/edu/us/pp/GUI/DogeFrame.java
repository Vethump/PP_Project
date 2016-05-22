package pl.edu.us.pp.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adrian on 2016-05-22.
 */
public class DogeFrame extends JFrame{
    private JPanel panel1;
    private JLabel imageLabel;
    private int x;
    private int yPos;
    private int count;
    private Timer timer;

    public DogeFrame(String resource, int x, int y) {
        this.x = x;
        this.yPos = y +550;
        count = 0;
        setContentPane(imageLabel);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));
        setSize(550,550);
        setLocation(x,this.yPos);
        imageLabel.setIcon(new ImageIcon(getClass().getResource(resource)));

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count <= 550) {
                    DogeFrame.this.setLocation(x, yPos - count);
                    count += 5;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();

    }

}
