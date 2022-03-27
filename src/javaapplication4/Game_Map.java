package javaapplication4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game_Map extends Game_FrameComponent {
    public volatile transient int map_id = 0;
    public volatile transient int map_max_x;
    public volatile transient int map_max_y;
    public volatile transient int screen_x = 0;
    public volatile transient int screen_y = 0;
    public volatile transient int moveingX = 0;
    public volatile transient int moveingY = 0;
    public volatile transient int[][][][] current_map = new int[5][999][999][8];
    public volatile transient BufferedImage[] layer = new BufferedImage[8];
    public volatile transient BufferedImage substrate;
    public volatile transient BufferedImage superstratum;
    public volatile transient JPanel Map1;
    public volatile transient JPanel Map2;
    public volatile transient KeyListener keyListener;
    public volatile transient MouseAdapter mouseAdapter;
    public volatile transient MouseListener mouseListener;
    Game_Map(){
        keyListener=new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_DOWN:
                        if (Game_System.CommonEvent.Move_complete) {
                            Game_System.CommonEvent.Move_complete = false;
                            Game_System.timer[0] = new Timer("move0", 0, 0, 1);
                            Game_System.timer[0].start();
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (Game_System.CommonEvent.Move_complete) {
                            Game_System.CommonEvent.Move_complete = false;
                            Game_System.timer[0] = new Timer("move0", 0, 0, 2);
                            Game_System.timer[0].start();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (Game_System.CommonEvent.Move_complete) {
                            Game_System.CommonEvent.Move_complete = false;
                            Game_System.timer[0] = new Timer("move0", 0, 0, 3);
                            Game_System.timer[0].start();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (Game_System.CommonEvent.Move_complete) {
                            Game_System.CommonEvent.Move_complete = false;
                            Game_System.timer[0] = new Timer("move0", 0, 0, 4);
                            Game_System.timer[0].start();
                        }
                        break;
                    case KeyEvent.VK_R:
                        //createMapLayer();
                        Game_System.CommonEvent.updateScreen();
                        //Map2paint();
                        break;
                    case KeyEvent.VK_ENTER:
                        if (Game_System.Window_Message.Visible) {
                            Game_System.Window_Message.Visible = false;
                        } else {
                            if (Game_System.Event.hasEvent(map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y)) {
                                int eventid = Game_System.Event.getEventID(map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y);
                                if (Game_System.event[map_id][eventid].trigger_mode == 0) {
                                    Game_System.event[map_id][eventid].start();
                                }
                            } else {
                                switch (Game_System.actor[Game_System.actor_id].orientation) {
                                    case 0:
                                        if (Game_System.Event.hasEvent(map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y + 1)) {
                                            int eventid = Game_System.Event.getEventID(map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y + 1);
                                            if (Game_System.event[map_id][eventid].trigger_mode == 0) {
                                                Game_System.event[map_id][eventid].start();
                                            }
                                        }
                                        break;
                                    case 1:
                                        if (Game_System.Event.hasEvent(map_id, Game_System.actor[Game_System.actor_id].x - 1, Game_System.actor[Game_System.actor_id].y)) {
                                            int eventid = Game_System.Event.getEventID(map_id, Game_System.actor[Game_System.actor_id].x - 1, Game_System.actor[Game_System.actor_id].y);
                                            if (Game_System.event[map_id][eventid].trigger_mode == 0) {
                                                Game_System.event[map_id][eventid].start();
                                            }
                                        }
                                        break;
                                    case 2:
                                        if (Game_System.Event.hasEvent(map_id, Game_System.actor[Game_System.actor_id].x + 1, Game_System.actor[Game_System.actor_id].y)) {
                                            int eventid = Game_System.Event.getEventID(map_id, Game_System.actor[Game_System.actor_id].x + 1, Game_System.actor[Game_System.actor_id].y);
                                            if (Game_System.event[map_id][eventid].trigger_mode == 0) {
                                                Game_System.event[map_id][eventid].start();
                                            }
                                        }
                                        break;
                                    case 3:
                                        if (Game_System.Event.hasEvent(map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y - 1)) {
                                            int eventid = Game_System.Event.getEventID(map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y - 1);
                                            if (Game_System.event[map_id][eventid].trigger_mode == 0) {
                                                Game_System.event[map_id][eventid].start();
                                            }
                                        }
                                        break;
                                }
                            }
                            break;
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
        Game_System.CommonEvent.Move_complete = true;
        loadMapElements();
        createMapLayer();
    }
    public void start(){
        if(keyListener!=null)Game_System.mainwindow.addKeyListener(keyListener);
        if(mouseAdapter!=null)Game_System.mainwindow.addMouseMotionListener(mouseAdapter);
        if(mouseListener!=null)Game_System.mainwindow.addMouseListener(mouseListener);
    }
    @Override
    public void main(){
    }
    public BufferedImage getImage(){
        BufferedImage bufferedImage = new BufferedImage(map_max_x * 32, map_max_y * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(map_max_x * 32, map_max_y * 32, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        BufferedImage bufferedImage1 = new BufferedImage(map_max_x * 32, map_max_y * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d1 = bufferedImage1.createGraphics();
        bufferedImage1 = g2d1.getDeviceConfiguration().createCompatibleImage(map_max_x * 32, map_max_y * 32, Transparency.TRANSLUCENT);
        g2d1 = bufferedImage1.createGraphics();
        g2d.drawImage(substrate, 0, 0, null);
        for (int i = 0; i < Game_System.event[map_id].length; i++) {
            ImageIcon a = new ImageIcon(Game_System.characters + Game_System.event[map_id][i].icon);
            int w = a.getIconWidth() / 4;
            int h = a.getIconHeight() / 4;
            g2d.drawImage(getEventImage(map_id, i), Game_System.event[map_id][i].x * 32 + 32 - w, Game_System.event[map_id][i].y * 32 + 32 - h, null);
        }
        g2d.drawImage(createActorLocation(), 0, 0, null);
        g2d.drawImage(superstratum, 0, 0, null);
        g2d.dispose();
        g2d1.drawImage(bufferedImage, screen_x, screen_y, null);
        if (Game_System.Window_Message.Visible) {
            g2d1.drawImage(Game_System.Window_Message.getImage(), Game_System.Window_Message.x, Game_System.Window_Message.y, null);
        }
        g2d1.dispose();
        return bufferedImage1;
    }
        private BufferedImage createActorLocation() {
        ImageIcon a = new ImageIcon(Game_System.characters + Game_System.actor[Game_System.actor_id].icon);
        int w = a.getIconWidth() / 4;
        int h = a.getIconHeight() / 4;
        BufferedImage bufferedImage = new BufferedImage(map_max_x * 32, map_max_y * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(map_max_x * 32, map_max_y * 32, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(getActorImage(Game_System.actor_id), Game_System.actor[Game_System.actor_id].x * 32 + moveingX + 32 - w, Game_System.actor[Game_System.actor_id].y * 32 + moveingY + 32 - h, Map1);
        g2d.dispose();
        return bufferedImage;
    }

    private BufferedImage getActorImage(int actorid) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(new File(Game_System.characters + Game_System.actor[actorid].icon));
            ImageIcon a = new ImageIcon(Game_System.characters + Game_System.actor[actorid].icon);
            int w = a.getIconWidth() / 4;
            int h = a.getIconHeight() / 4;
            Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
            BufferedImage bi = null;
            if (it.hasNext()) {
                ImageReader r = it.next();
                r.setInput(iis, true);
                ImageReadParam param = r.getDefaultReadParam();
                Rectangle rect = new Rectangle(Game_System.actor[actorid].state * w, Game_System.actor[actorid].orientation * h, w, h);
                param.setSourceRegion(rect);
                bi = r.read(0, param);
            }
            return bi;
        } catch (IOException ex) {
        }
        return new BufferedImage(1, 2, 3);
    }

    private void createMapLayer() {
        BufferedImage[] b;
        BufferedImage bufferedImage = new BufferedImage(map_max_x * 32, map_max_y * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(map_max_x * 32, map_max_y * 32, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        BufferedImage bufferedImage1 = new BufferedImage(map_max_x * 32, map_max_y * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d1 = bufferedImage1.createGraphics();
        bufferedImage1 = g2d1.getDeviceConfiguration().createCompatibleImage(map_max_x * 32, map_max_y * 32, Transparency.TRANSLUCENT);
        g2d1 = bufferedImage1.createGraphics();
        for (int h = 0; h < 4; h++) {
            for (int x = 0; x < map_max_x; x++) {
                for (int y = 0; y < map_max_y; y++) {
                    if (current_map[h][y][x][2] != 0) {
                        if (current_map[h][y][x][3] == 0) {
                            g2d.drawImage(getMapImage(h, x, y), x * 32, y * 32, 32, 32, null);
                        } else {
                            g2d1.drawImage(getMapImage(h, x, y), x * 32, y * 32, 32, 32, null);
                        }
                        /*
                        if (b[x] == null) {
                        try {
                        b[x] = getMapImage(h, x, y);
                        } catch (IOException ex) {
                        Logger.getLogger(Game1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        } else {
                        try {
                        b[x] = mergeImage(b[x], getMapImage(h, x, y), false);
                        } catch (IOException ex) {
                        Logger.getLogger(Game1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                         */
                    } /* else {
                    if (b[x] == null) {
                    b[x] = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
                    } else {
                    b[x] = mergeImage(b[x], new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB), false);
                    }
                    }*/
                }
            }
            /*
            for (int x = 0; x < map_max_x; x++) {
            if (x == 0) {
            layer[h] = b[x];
            } else {
            layer[h] = mergeImage(layer[h], b[x], true);
            }
            }
             */
            //g2d.dispose();
            //layer[h]=bufferedImage;
        }
        g2d.dispose();
        substrate = bufferedImage;
        superstratum = bufferedImage1;
    }

    private BufferedImage getEventImage(int mapid, int eventid) {
        if (!"none".equals(Game_System.event[mapid][eventid].icon)) {
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(new File(Game_System.characters + Game_System.event[mapid][eventid].icon));
                ImageIcon a = new ImageIcon(Game_System.characters + Game_System.event[mapid][eventid].icon);
                int w = a.getIconWidth() / 4;
                int h = a.getIconHeight() / 4;
                Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
                BufferedImage bi = null;
                if (it.hasNext()) {
                    ImageReader r = it.next();
                    r.setInput(iis, true);
                    ImageReadParam param = r.getDefaultReadParam();
                    Rectangle rect = new Rectangle(Game_System.event[mapid][eventid].state * w, Game_System.event[mapid][eventid].orientation * h, w, h);
                    param.setSourceRegion(rect);
                    bi = r.read(0, param);
                }
                return bi;
            } catch (IOException ex) {
            }
        }
        BufferedImage bufferedImage = new BufferedImage(map_max_x * 32, map_max_y * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(map_max_x * 32, map_max_y * 32, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        g2d.dispose();
        return bufferedImage;
    }

    private BufferedImage getMapImage(int hierarchy, int x, int y) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(new File(Game_System.tilesets + current_map[hierarchy][y][x][2] + ".png"));
            Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
            BufferedImage bi = null;
            if (it.hasNext()) {
                ImageReader r = it.next();
                r.setInput(iis, true);
                ImageReadParam param = r.getDefaultReadParam();
                Rectangle rect = new Rectangle(current_map[hierarchy][y][x][0] * 32, current_map[hierarchy][y][x][1] * 32, 32, 32);
                param.setSourceRegion(rect);
                bi = r.read(0, param);
            }
            return bi;
        } catch (IOException ex) {
        }
        return new BufferedImage(1, 2, 3);
    }

    public void loadMapElements() {
        current_map = new int[5][999][999][8];
        Scanner a = null;
        String[] b = new String[99];
        for (int i = 0; i < 99; i++) {
            b[i] = "";
        }
        int c;
        int h = 0;
        int d = 0;
        int e;
        int f;
        int eventsmax = -1;
        int eventid = -1;
        int eventpagemax = -1;
        int eventpage = -1;
        int pagerow = -1;
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(Game_System.data + "Map" + map_id + ".txt"), "UTF-8");
        } catch (FileNotFoundException|UnsupportedEncodingException ex) {
        }
        BufferedReader read = new BufferedReader(in);
        try {
            for (int i = 0; (b[0] = read.readLine()) != null; i++) {
                c = 0;
                e = 0;
                f = 0;
                b[1] = "";
                if (d > 0) {
                    for (int j = 0; f < map_max_x * 3; j++) {
                        if (",".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][c] = Integer.parseInt(b[1].trim());
                            c++;
                            if (c == 3) {
                                c = 0;
                                e++;
                            }
                            b[1] = "";
                            f++;
                        } else if ("d".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][4] = 0;
                        } else if ("l".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][5] = 0;
                        } else if ("r".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][6] = 0;
                        } else if ("u".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][7] = 0;
                        } else if ("p".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][3] = 0;
                        } else if ("D".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][4] = 1;
                        } else if ("L".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][5] = 1;
                        } else if ("R".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][6] = 1;
                        } else if ("U".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][7] = 1;
                        } else if ("P".equals(b[0].substring(j, j + 1))) {
                            current_map[h][map_max_y - d][e][3] = 1;
                        } else {
                            b[1] += b[0].substring(j, j + 1);
                        }
                    }
                    d--;
                } else if (i == 0) {
                    for (int j = 0; j < b[0].length(); j++) {
                        if (",".equals(b[0].substring(j, j + 1))) {
                            c++;
                        } else {
                            b[c + 1] += b[0].substring(j, j + 1);
                        }
                    }
                    map_max_x = Integer.parseInt(b[1]);
                    map_max_y = Integer.parseInt(b[2]);
                } else if (b[0].length() > 5 ? "layer".equals(b[0].substring(0, 5)) : false) {
                    h = (Integer.parseInt(b[0].substring(5, 6)) - 1);
                    d = map_max_y;
                } else if (eventsmax > -1) {
                    if (b[0].length() > 3 ? "ID:".equals(b[0].substring(0, 3)) : false) {
                        eventid = Integer.parseInt(b[0].substring(3));
                    } else if (eventid > -1) {
                        if (b[0].length() > 5 ? "name:".equals(b[0].substring(0, 5)) : false) {
                            Game_System.event[map_id][eventid].name = b[0].substring(5);
                        } else if (b[0].length() > 2 ? "x:".equals(b[0].substring(0, 2)) : false) {
                            Game_System.event[map_id][eventid].x = Integer.parseInt(b[0].substring(2));
                        } else if (b[0].length() > 2 ? "y:".equals(b[0].substring(0, 2)) : false) {
                            Game_System.event[map_id][eventid].y = Integer.parseInt(b[0].substring(2));
                        } else if (b[0].length() == 13 ? "passable:".equals(b[0].substring(0, 9)) : false) {
                            Game_System.event[map_id][eventid].passable[0] = "d".equals(b[0].substring(9, 10));
                            Game_System.event[map_id][eventid].passable[1] = "l".equals(b[0].substring(10, 11));
                            Game_System.event[map_id][eventid].passable[2] = "r".equals(b[0].substring(11, 12));
                            Game_System.event[map_id][eventid].passable[3] = "u".equals(b[0].substring(12, 13));
                        } else if (b[0].length() > 5 ? "icon:".equals(b[0].substring(0, 5)) : false) {
                            Game_System.event[map_id][eventid].icon = b[0].substring(5);
                        } else if (b[0].length() > 12 ? "triggerMode:".equals(b[0].substring(0, 12)) : false) {
                            Game_System.event[map_id][eventid].trigger_mode = Integer.parseInt(b[0].substring(12));
                        } else if (b[0].length() > 6 ? "state:".equals(b[0].substring(0, 6)) : false) {
                            Game_System.event[map_id][eventid].state = Integer.parseInt(b[0].substring(6));
                        } else if (b[0].length() > 12 ? "orientation:".equals(b[0].substring(0, 12)) : false) {
                            Game_System.event[map_id][eventid].orientation = Integer.parseInt(b[0].substring(12, 13));
                        } else if (b[0].length() > 5 ? "Page:".equals(b[0].substring(0, 5)) : false) {
                            eventpagemax = (Integer.parseInt(b[0].substring(5)) - 1);
                            Game_System.event[map_id][eventid].page = new String[eventpagemax + 1][999][999];
                        } else if (eventpagemax > -1) {
                            if (b[0].length() > 1 ? "P".equals(b[0].substring(0, 1)) : false) {
                                eventpage = (Integer.parseInt(b[0].substring(1)) - 1);
                                pagerow = 4;
                            } else if (pagerow > -1) {
                                if (b[0].length() > 11 ? "condition".equals(b[0].substring(0, 9)) && ":".equals(b[0].substring(10, 11)) : false) {
                                    int condition = Integer.parseInt(b[0].substring(9, 10));
                                    for (int j = 11; j < b[0].length(); j++) {
                                        if (",".equals(b[0].substring(j, j + 1))) {
                                            Game_System.event[map_id][eventid].page[eventpage][condition][c] = b[1];
                                            c++;
                                            b[1] = "";
                                        } else {
                                            b[1] += b[0].substring(j, j + 1);
                                        }
                                    }
                                } else if (b[0].length() > 1 ? "E".equals(b[0].substring(0, 1)) : false) {
                                    for (int j = 1; true; j++) {
                                        if (j >= b[0].length()) {
                                            pagerow++;
                                            break;
                                        }
                                        if (",".equals(b[0].substring(j, j + 1))) {
                                            Game_System.event[map_id][eventid].page[eventpage][pagerow][c] = b[1];
                                            c++;
                                            b[1] = "";
                                        } else {
                                            b[1] += b[0].substring(j, j + 1);
                                        }
                                    }
                                } else if ("endPage".equals(b[0])) {
                                    eventpage = -1;
                                    pagerow = -1;
                                    eventpagemax--;
                                }
                            }
                        } else if ("endEvent".equals(b[0])) {
                            eventid = -1;
                            eventsmax--;
                        }
                    }
                } else if (b[0].length() > 6 ? "Event:".equals(b[0].substring(0, 6)) : false) {
                    eventsmax = (Integer.parseInt(b[0].substring(6)) - 1);
                    Game_System.event[map_id] = new Event[eventsmax + 1];
                    for (int j = 0; j <= eventsmax; j++) {
                        Game_System.event[map_id][j] = new Event();
                    }
                }
            }
        } catch (IOException ex) {
        }
    }
}
