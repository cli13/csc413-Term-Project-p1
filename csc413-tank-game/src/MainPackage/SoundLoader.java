/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Canran
 */
public class SoundLoader {

    private Clip clip;

    public SoundLoader(String path) {
        try {
//            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(path));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play() {
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.close();
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
