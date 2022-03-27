package javaapplication4;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;


class Cursor_Rect extends Game_FrameComponent{
    public volatile transient int x;
    public volatile transient int y;
    //public volatile transient int accelerationY;
    public volatile transient int goalX;
    public volatile transient int goalY;
    public volatile transient int width;
    public volatile transient int height;
    public volatile transient String windowskin;
    public volatile transient float transparency;

    Cursor_Rect(int width, int height) {
        x=0;
        y=0;
        this.width = width;
        this.height = height;
        windowskin = Game_System.windowskin_name;
        transparency=0;
    }
    private BufferedImage getRectSkin(int type) {
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        switch (type) {
            case 0 -> {
                x = 128;
                y = 64;
                w = 8;
                h = 8;
            }
            case 1 -> {
                x = 136;
                y = 64;
                w = 16;
                h = 8;
            }
            case 2 -> {
                x = 152;
                y = 64;
                w = 8;
                h = 8;
            }
            case 3 -> {
                x = 128;
                y = 72;
                w = 8;
                h = 16;
            }
            case 4 -> {
                x = 136;
                y = 72;
                w = 16;
                h = 16;
            }
            case 5 -> {
                x = 152;
                y = 72;
                w = 8;
                h = 16;
            }
            case 6 -> {
                x = 128;
                y = 88;
                w = 8;
                h = 8;
            }
            case 7 -> {
                x = 136;
                y = 88;
                w = 16;
                h = 8;
            }
            case 8 -> {
                x = 152;
                y = 88;
                w = 8;
                h = 8;
            }
        }
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(new File(Game_System.windowskins + windowskin));
            Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
            BufferedImage bi = null;
            if (it.hasNext()) {
                ImageReader r = it.next();
                r.setInput(iis, true);
                ImageReadParam param = r.getDefaultReadParam();
                Rectangle rect = new Rectangle(x, y, w, h);
                param.setSourceRegion(rect);
                bi = r.read(0, param);
            }
            return bi;
        } catch (IOException ex) {
        }
        return new BufferedImage(1, 2, 3);
    }
    @Override
    public BufferedImage getImage(){
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1-Math.abs(transparency)));
        g2d.drawImage(getRectSkin(0), 0, 0, null);
        g2d.drawImage(getRectSkin(1), 8, 0,width-16,8, null);
        g2d.drawImage(getRectSkin(2), width-8, 0, null);
        g2d.drawImage(getRectSkin(3), 0, 8,8,height-16, null);
        g2d.drawImage(getRectSkin(4), 8, 8,width-16,height-16, null);
        g2d.drawImage(getRectSkin(5), width-8, 8,8,height-16, null);
        g2d.drawImage(getRectSkin(6), 0, height-8, null);
        g2d.drawImage(getRectSkin(7), 8, height-8,width-16,8, null);
        g2d.drawImage(getRectSkin(8), width-8, height-8, null);
        g2d.dispose();
        transparency+=0.05f;
        if(transparency>=0.5)transparency=-0.5f;
        if(y!=goalY)y+=goalY<y?Math.min((goalY-y)/4,-1):Math.max((goalY-y)/4,1);
        if(x!=goalX)x+=goalX<x?Math.min((goalX-x)/4,-1):Math.max((goalX-x)/4,1);
        //if(y!=goalY){if(accelerationY<0?y+accelerationY>goalY:y+accelerationY<goalY)y+=accelerationY;else y=goalY;}
        return bi;
    }
    
}
