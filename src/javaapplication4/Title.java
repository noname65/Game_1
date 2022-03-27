package javaapplication4;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
public class Title extends Game_FrameComponent {
    Window_Selectable ws;
    Title(){
        x=0;
        y=0;
        ws = new Window_Selectable(200,200,new String[]{Game_System.got_text("window.title.start"),Game_System.got_text("generic.load"),Game_System.got_text("generic.settings"),Game_System.got_text("window.title.exit")},false){
            @Override
            public void clickEnter(){
                System.out.println(indexX+indexY*item.length);
                switch(indexX+indexY*item.length){
                    case 0 -> {
                        Game_System.setScene(Game_System.Game_Map);
                    }
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                }
            }
        };
        ws.x=(Game_System.drawX-ws.width)/2;
        ws.y=Game_System.drawY-ws.height-20;
        ws.activity(true);
    }
    @Override
    public void main(){
    }
    @Override
    public void start(){
        ws.activity(true);
    }
    @Override
    public void end(){
        ws.activity(false);
    }
    @Override
    public BufferedImage getImage(){
        BufferedImage bufferedImage = new BufferedImage(Game_System.drawX, Game_System.drawY, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(ws.getImage(), ws.x, ws.y,null);
        g2d.dispose();
        return bufferedImage;
    }
}
