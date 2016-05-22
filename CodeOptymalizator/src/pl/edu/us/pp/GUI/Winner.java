package pl.edu.us.pp.GUI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adrian on 2016-05-21.
 */
public class Winner extends JFrame{
    private JPanel panel1;
    private Clip clip;

    public Winner() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        setContentPane(panel1);
        setLocation(700,500);
        pack();
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("/resources/Ode_to_Joy.wav").getFile())));
        clip.start();
    }

}
