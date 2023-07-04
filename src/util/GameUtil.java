package util;

import java.awt.*;

/**
 * @author ZY
 * @create 16:59
 */
public class GameUtil {
    public static Image LoadBufferedImg(String imPath){
        Image image = Toolkit.getDefaultToolkit().createImage(imPath);
        return image;
    }
}
