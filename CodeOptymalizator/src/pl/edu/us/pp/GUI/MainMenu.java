package pl.edu.us.pp.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Adrian on 2016-05-20.
 */
public class MainMenu extends JFrame{
    private JPanel panel1;
    private JEditorPane textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JButton wczytajButton;
    private JButton analizujButton;
    private JProgressBar progressBar1;
    private int value;

    public MainMenu() throws HeadlessException, IOException {
        //BufferedImage gauge = ImageIO.read(getClass().getResourceAsStream("/resources/gauge.png"));
        setContentPane(panel1);
        setLocation(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        comboBox1.addItem("C++");
        comboBox1.addItem("Java");
        value = 0;
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        progressBar1.setForeground(Color.GREEN);
        analizujButton.addActionListener(e -> {
            value += 10;
            progressBar1.setValue(value);
            if(value > 80){
                progressBar1.setForeground(Color.RED);
            }
            if(value >= 100){
                try {
                    ExplosionFrame explosionFrame = new ExplosionFrame("/resources/Fireworks.4.gif", 1200, 300);
                    explosionFrame.setVisible(true);

                    ExplosionFrame explosionFrame2 = new ExplosionFrame("/resources/giphy.gif", 400, 600);
                    explosionFrame2.setVisible(true);

                    ExplosionFrame explosionFrame3 = new ExplosionFrame("/resources/giphy2.gif", 800, 500);
                    explosionFrame3.setVisible(true);

                    ExplosionFrame explosionFrame4 = new ExplosionFrame("/resources/giphy2.gif", 200, 200);
                    explosionFrame4.setVisible(true);

                    Winner winner = new Winner();
                    winner.setVisible(true);


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        wczytajButton.addActionListener(e -> {
            try {
                textField1.setText(new Scanner(new File(textField2.getText())).useDelimiter("\\Z").next());
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(this, "Zła ścieżka do pliku!","File reader error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
