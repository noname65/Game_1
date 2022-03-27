package javaapplication4;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class Preload {
    public transient volatile BufferedImage windowskin;
    Preload(){
        try {
            windowskin=ImageIO.read(new File(Game_System.windowskins+Game_System.windowskin_name));
        } catch (IOException ex) {
            Logger.getLogger(Preload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
