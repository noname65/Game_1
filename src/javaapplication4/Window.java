package javaapplication4;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;
class Window extends Game_FrameComponent {

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        windowskin = Game_System.windowskin_name;
        window_opacity = 1.00f;
        skin_opacity = 1.00f;
        content_opacity = 1.00f;
    }

    Window() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        windowskin = Game_System.windowskin_name;
        window_opacity = 1.00f;
        skin_opacity = 1.00f;
        content_opacity = 1.00f;
    }

    private BufferedImage getWindowSkin(int type) {
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        switch (type) {
            case 0:
                x = 0;
                y = 0;
                w = 128;
                h = 128;
                break;
            case 1:
                x = 176;
                y = 0;
                w = 16;
                h = 16;
                break;
            case 2:
                x = 128;
                y = 0;
                w = 16;
                h = 16;
                break;
            case 3:
                x = 128;
                y = 48;
                w = 16;
                h = 16;
                break;
            case 4:
                x = 176;
                y = 48;
                w = 16;
                h = 16;
                break;
            case 5:
                x = 144;
                y = 0;
                w = 32;
                h = 16;
                break;
            case 6:
                x = 128;
                y = 16;
                w = 16;
                h = 32;
                break;
            case 7:
                x = 144;
                y = 48;
                w = 32;
                h = 16;
                break;
            case 8:
                x = 176;
                y = 16;
                w = 16;
                h = 32;
                break;
        }
        /*try {
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
        }*/
        return Game_System.preload.windowskin.getSubimage(x, y, w, h);//BufferedImage(1, 2, 3);
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(640, 480, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        g2d.dispose();
        return bufferedImage;
    }

    public BufferedImage getwindow() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(640, 480, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        //BufferedImage bufferedImage1 = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        //Graphics2D g2d1 = bufferedImage1.createGraphics();
        //bufferedImage1 = g2d1.getDeviceConfiguration().createCompatibleImage(640, 480, Transparency.TRANSLUCENT);
        //g2d1 = bufferedImage1.createGraphics();
        //BufferedImage bufferedImage2 = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        //Graphics2D g2d2 = bufferedImage2.createGraphics();
        //bufferedImage2 = g2d2.getDeviceConfiguration().createCompatibleImage(640, 480, Transparency.TRANSLUCENT);
        //g2d2 = bufferedImage2.createGraphics();
        g2d.setComposite(AlphaComposite.SrcOver.derive(window_opacity));
        //g2d1.setComposite(AlphaComposite.SrcOver.derive(skin_opacity));
        //g2d2.setComposite(AlphaComposite.SrcOver.derive(content_opacity));
        g2d.drawImage(getWindowSkin(0), 0, 0, width, height, null);
        g2d.drawImage(getWindowSkin(1), width - 16, 0, null);
        g2d.drawImage(getWindowSkin(2), 0, 0, null);
        g2d.drawImage(getWindowSkin(3), 0, height - 16, null);
        g2d.drawImage(getWindowSkin(4), width - 16, height - 16, null);
        g2d.drawImage(getWindowSkin(5), 16, 0, width - 32, 16, null);
        g2d.drawImage(getWindowSkin(6), 0, 16, 16, height - 32, null);
        g2d.drawImage(getWindowSkin(7), 16, height - 16, width - 32, 16, null);
        g2d.drawImage(getWindowSkin(8), width - 16, 16, 16, height - 32, null);
        //g2d.drawImage(getImage(), x, y, null);
        g2d.dispose();
        //g2d2.dispose();
        //g2d.drawImage(bufferedImage1, 0, 0, null);
        //g2d.drawImage(bufferedImage2, 0, 0, null);
        //g2d.dispose();
        return bufferedImage;
    }
    public volatile transient int x;
    public volatile transient int y;
    public volatile transient int width;
    public volatile transient int height;
    public volatile transient String windowskin;
    public volatile transient float window_opacity = 0.00f;
    public volatile transient float skin_opacity = 0.00f;
    public volatile transient float content_opacity = 0.00f;
}
