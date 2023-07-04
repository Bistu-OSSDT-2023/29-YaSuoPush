package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * @author ZY
 * @create 21:59
 */
public class Music {
    private static AudioClip fly;
    public static void load(){
        try {
            fly = Applet.newAudioClip(new File("music/mc.wav").toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static void playFly(){
        fly.loop();
    }
}
