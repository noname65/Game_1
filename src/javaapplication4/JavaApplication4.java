package javaapplication4;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.Image;

public class JavaApplication4 {
    public static void main(String[] args) {
        Game_System.init();
        //Window_Selectable ws = new Window_Selectable(100,100,200,200,new String[]{"asd","qwe","123","fgh"});
        //Game_System.mainwindow.addKeyListener(ws.keyListener);
        Graphics2D g=(Graphics2D)Game_System.mainwindow.panel.getGraphics();
        while(true){
            try {
                g.drawImage(Game_System.scene.getImage(), 0, 0,null);
                //g.drawImage(ImageIO.getImageReaders(ImageIO.createImageInputStream(new File(Game_System.icons + "104-05Weapon.PNG"))).next().read(0), 0, 0,null);
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
