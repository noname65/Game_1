package javaapplication4;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
class Window_Selectable extends Window {
    public volatile transient int indexX;
    public volatile transient int indexY;
    public volatile transient int textX;
    public volatile transient int textMoveX;
    public volatile transient String[][] item;
    public volatile transient Cursor_Rect cr;
    public volatile transient KeyListener keyListener;
    public volatile transient MouseAdapter mouseAdapter;
    public volatile transient MouseListener mouseListener;
    
    Window_Selectable(int w,int h,String[] item,boolean isTransverse) {
        this(w,h,isTransverse?new Object(){
            public String[][] a(){
                String[][] I=new String[item.length][1];
                for(int i=0;i<item.length;i++)I[i][0]=item[i];
                return I;
            }
        }.a():new String[][]{item});
    }
    Window_Selectable(int w,int h,String[][] item) {
        //System.out.println(Arrays.toString(item[0]));
        x = 0;
        y = 0;
        textX=100;
        textMoveX=0;
        width = w;
        height = h;
        windowskin = Game_System.windowskin_name;
        window_opacity = 1.00f;
        skin_opacity = 1.00f;
        this.item=item;
        indexX=0;
        indexY=0;
        cr=new Cursor_Rect(textX-16,32);
        cr.y=indexY*32+10;
        cr.goalY=indexY*32+10;
        mouseAdapter=new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p=e.getPoint();
                //System.out.println(p.toString());
                if (p.x>=x&&p.x<=x+width&&p.y>=y&&p.y<=y+height) {
                    int ix,iy;
                    ix=(p.x-x)/textX;
                    iy=(p.y-y)/32-1;
                    if(ix>=0&&ix<item.length &&iy>=0&& iy<item[ix].length){
                        if(indexX!=ix||indexY!=iy)textMoveX=0;
                        indexX=ix;
                        indexY=iy;
                        cr.goalY=indexY*32+10;
                        cr.goalX=indexX*textX;
                    }
                }
            }
        };
        mouseListener=new MouseListener(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    keyListener.keyPressed(new KeyEvent(Game_System.mainwindow,KeyEvent.KEY_PRESSED,0,KeyEvent.VK_ENTER,KeyEvent.VK_ENTER,KeyEvent.CHAR_UNDEFINED,KeyEvent.KEY_LOCATION_STANDARD));
                }
                //keyListener.keyPressed(new KeyEvent(Game_System.mainwindow,KeyEvent.KEY_PRESSED,0,KeyEvent.VK_ENTER,KeyEvent.VK_ENTER,KeyEvent.CHAR_UNDEFINED,KeyEvent.KEY_LOCATION_STANDARD));
                //keyListener.keyPressed(new KeyEvent(null,0,10,0,KeyEvent.VK_ENTER));//.VK_ENTER);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        keyListener=new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_DOWN -> {
                        if(++indexY>=item[indexX].length){
                            indexY=0;
                            if(++indexX>=item.length)indexX=0;
                        }
                        cr.goalY=indexY*32+10;
                        cr.goalX=indexX*textX;
                        textMoveX=0;
                    }
                    case KeyEvent.VK_UP -> {
                        if(--indexY<0){
                            indexY=item[indexX].length-1;
                            if(--indexX<0)indexX=item.length-1;
                        }
                        cr.goalY=indexY*32+10;
                        cr.goalX=indexX*textX;
                        textMoveX=0;
                    }
                    case KeyEvent.VK_LEFT -> {
                        if(--indexX<0){
                            indexX=item.length-1;
                            if(--indexY<0)indexY=item[indexX].length-1;
                        }
                        cr.goalY=indexY*32+10;
                        cr.goalX=indexX*textX;
                        textMoveX=0;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        if(++indexX>=item.length)
                            indexX=0;
                            if(++indexY>=item[indexX].length)indexY=0;
                        cr.goalY=indexY*32+10;
                        cr.goalX=indexX*textX;
                        textMoveX=0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        clickEnter();
                        //System.out.println("asd");
                    }
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(getwindow(), 0, 0, null);
        Game_Text gt=new Game_Text(textX, 32);
        gt.omit=true;
        gt.isSingleRow=true;
        for (int i=0;i<item.length;i++)for(int j=0;j<item[i].length;j++){
            if(i!=indexX||j!=indexY||gt.getTextWidth(item[i][j])<=textX){
                g2d.drawImage(gt.getImage(item[i][j]), i*textX, j*32, null);
            }else{
                BufferedImage tempbi=new BufferedImage(textX, 40, BufferedImage.TYPE_INT_ARGB);
                gt.omit=false;
                tempbi.getGraphics().drawImage(gt.getImage(item[i][j]), -textMoveX,0, null);
                if(textMoveX>gt.getTextWidth(item[i][j])+30-textX)tempbi.getGraphics().drawImage(gt.getImage(item[i][j]), gt.getTextWidth(item[i][j])+30-textMoveX,0, null);
                g2d.drawImage(tempbi, i*textX, j*32, null);
                textMoveX+=2;
                if(textMoveX>gt.getTextWidth(item[i][j])+30)textMoveX-=gt.getTextWidth(item[i][j])+30;
                gt.omit=true;
            }
        }
        g2d.drawImage(cr.getImage(), cr.x+8, cr.y, null);
        g2d.dispose();
        return bufferedImage;
    }
    public void clickEnter(){}
    @Override
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
