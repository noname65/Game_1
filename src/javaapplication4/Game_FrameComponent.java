package javaapplication4;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
public abstract class Game_FrameComponent {
    public volatile transient int x;
    public volatile transient int y;
    //public volatile transient ArrayList<Game_FrameComponent> subclass=new ArrayList<>();
    public volatile transient KeyListener keyListener;
    public volatile transient MouseAdapter mouseAdapter;
    public volatile transient MouseListener mouseListener;
    public BufferedImage getImage(){
        return null;
    }
    public void main(){
    }
    public void start(){
    }
    public void end(){
    }
    public void activity(boolean isActivity){
        if(isActivity){
            if(keyListener!=null)Game_System.mainwindow.addKeyListener(keyListener);
            if(mouseAdapter!=null)Game_System.mainwindow.addMouseMotionListener(mouseAdapter);
            if(mouseListener!=null)Game_System.mainwindow.addMouseListener(mouseListener);
        }else{
            if(keyListener!=null)Game_System.mainwindow.removeKeyListener(keyListener);
            if(mouseAdapter!=null)Game_System.mainwindow.removeMouseMotionListener(mouseAdapter);
            if(mouseListener!=null)Game_System.mainwindow.removeMouseListener(mouseListener);
        }
    }
}
