package javaapplication4;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.stream.ImageInputStream;
import java.awt.AlphaComposite;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import net.sf.json.JSONObject;
public class MainWindow extends JFrame {
    public volatile transient JPanel panel;
    MainWindow(){
        //JSONObject a=new JSONObject("{'Asd':[1,2,3]}");
        //System.out.println(a.get("Asd"));
        //System.exit(0);
        setSize(Game_System.drawX + 16, Game_System.drawY + 39); //设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗口关闭程序就停止
        setLocationRelativeTo(null); //设置居中
        setResizable(false); //设置窗口不可拉伸改变大小
        setBackground(null);
        setLayout(null);
        panel=new JPanel();
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        //getContentPane().setLayout(layout);
        /*try {
            ImageInputStream iis=ImageIO.createImageInputStream(new File(Game_System.icons + "104-05Weapon.PNG"));
            ImageReader ir=ImageIO.getImageReaders(iis).next();
            ir.setInput(iis);
            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ir.read(0) , new Point(0, 8), "img"));
        } catch (IOException ex) {
        }*/
        setVisible(true);
        /*
        Graphics2D g=(Graphics2D)getGraphics();
        float o=-0.50f;
        while(true){
            try {
                BufferedImage bi=new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2=bi.createGraphics();
                g2.drawImage(new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB), 0, 0,null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1-Math.abs(o)));
                g2.drawImage(new Cursor_Rect(0,0,120,120).getImage(), 40, 40,null);
                g2.drawImage(new Cursor_Rect(120,120,50,50).getImage(), 40, 40,null);
                g2.drawImage(new Cursor_Rect(250,200,100,32).getImage(), 40, 40,null);
                g2.dispose();
                g.drawImage(bi, 8, 32,null);
                o+=0.05f;
                if(o>=0.5)o=-0.5f;
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        /*Window_Selectable ws = new Window_Selectable(100,100,200,200,new String[]{"asd","qwe","123","fgh"});
        addKeyListener(ws.keyListener);
        Graphics2D g=(Graphics2D)getGraphics();
        while(true){
            try {
                g.drawImage(ws.getImage(), 8, 32,null);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
}
