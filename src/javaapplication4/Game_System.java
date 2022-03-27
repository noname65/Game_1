package javaapplication4;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
class Game_System {
    public transient static MainWindow mainwindow;
    public transient static float Cursor_Rect = 1.00f;
    public transient static String language = "en_us";
    public transient static int actor_id = 0;
    public transient static int speed = 3;
    public transient static int frequency = 9;
    public transient static int frameRate=30;
    public transient static int frameRateTimer=0;
    public transient static int frameRateTimerMax=1000/frameRate;
    public transient static int operationTick=100;
    public transient static int operationTickTimer=0;
    public transient static int operationTickTimerMax=1000/operationTick;
    public transient static String directory = new File("").getAbsolutePath();
    public transient static String graphics = directory + "/Graphics/";
    public transient static String translations = directory + "/Translations/";
    public transient static String icons = graphics + "Icons/";
    public transient static String panoramas = graphics + "Panoramas";
    public transient static String tilesets = graphics + "Tilesets/";
    public transient static String characters = graphics + "Characters/";
    public transient static String data = directory + "/Data/";
    public transient static String windowskin_name = "001-Blue01.png";
    public transient static String back = "";
    public transient static String windowskins = graphics + "Windowskins/";
    public transient static Map text;
    public transient static Color normal_color = new Color(255, 255, 255, 255);
    public transient static Font font = new Font("Microsoft YaHei", Font.PLAIN, 24);
    public transient static JLabel jLabel;
    public transient static int drawX=640;
    public transient static int drawY=480;
    public transient static Game_FrameComponent scene;
    public transient static Game_Map Game_Map;
    public transient static Actor[] actor = new Actor[1];
    public transient static Event Event = new Event();
    public transient static Event[][] event = new Event[999][99];
    public transient static boolean[] Switches = new boolean[9999];
    public transient static Object[] Variables = new Object[9999];
    public transient static CommonEvent CommonEvent = new CommonEvent();
    public transient static Timer[] timer = new Timer[999];
    public transient static Window_Message Window_Message = new Window_Message(null);
    public transient static Preload preload=new Preload();
    public static void init() {
        //new Equip().initialize();
        reload_text();
        mainwindow=new MainWindow();
        Game_Map=new Game_Map();
        Game_System.scene=new Title();
        
        Game_System.actor[0] = new Actor();
        Game_System.actor[0].icon = "001-Fighter01.png";
        Game_System.timer[1] = new Timer("move1", 0, 0, 0);
        Game_System.timer[1].start();
        CommonEvent.updateScreen();
        for (int i = 0; i < Game_System.Variables.length; i++) {
            Game_System.Variables[i] = 0;
        }
        Game_System.Window_Message.setWindowType(0);
    }
    public static String got_text(String key){
        if(Arrays.binarySearch(text.keySet().toArray(), key)!=-1){
            return text.get(key).toString();
        };
        return key;
    }
    public static void reload_text() {
        text=new HashMap();
        try{
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(new File(translations+language+".txt")), "UTF-16LE"));
            String[] l;
            String ll;
            for (int i = 0; (ll = read.readLine()) != null ? (l=ll.substring(1, ll.length()-1).split("\":\"")).length==2:false; i++)text.put(l[0],l[1]);
        } catch (FileNotFoundException|UnsupportedEncodingException ex) {
        } catch (IOException ex) {
        }/*
        return;
        int a = 0;
        String[][] text1 = new String[3][18];
        text1[1][a] = "标题";
        text1[2][a] = "Title";
        a = 1;
        text1[1][a] = "开始";
        text1[2][a] = "Start";
        a = 2;
        text1[1][a] = "读档";
        text1[2][a] = "Load";
        a = 3;
        text1[1][a] = "设置";
        text1[2][a] = "Settings";
        a = 4;
        text1[1][a] = "退出";
        text1[2][a] = "Exit";
        a = 5;
        text1[1][a] = "确定";
        text1[2][a] = "Determine";
        a = 6;
        text1[1][a] = "返回";
        text1[2][a] = "Back";
        a = 7;
        text1[1][a] = "刷新";
        text1[2][a] = "Refresh";
        a = 8;
        text1[1][a] = "没有存档";
        text1[2][a] = "No file";
        a = 9;
        text1[1][a] = "\n现在是星期几？\n";
        text1[2][a] = "\nWhat day is it now?\n";
        a = 10;
        //text1[1][a] = "\n二十三天后是星期" + text[0] + "。\n";
        //text1[2][a] = "\nIt's " + text[1] + " in 23 days.\n";
        a = 11;
        text1[1][a] = "\n砍掉他们的脑袋！\n";
        text1[2][a] = "\nOff with their head!\n";
        a = 12;
        text1[1][a] = "\n请输入一个数字：\n";
        text1[2][a] = "\nPlease enter a number:\n";
        a = 13;
        text1[1][a] = "\n这个数字";
        text1[2][a] = "\nThis number is ";
        a = 14;
        text1[1][a] = "\n输出随机数：" + (int) Math.ceil(Math.random() * 10) + "\n";
        text1[2][a] = "\nOutput random number:" + (int) Math.ceil(Math.random() * 10) + "\n";
        a = 15;
        text1[1][a] = "\n你是了不起的。\n";
        text1[2][a] = "\nYou are amazing.\n";
        a = 16;
        text1[1][a] = "\n请输入数字或输入完成(done)计算结果。\n";
        text1[2][a] = "\nPlease enter Numbers or enter done(完成) results.\n";
        a = 17;
        text1[1][a] = "\n你输入的不是已知命令。\n";
        text1[2][a] = "\nYou're not typing a known command.\n";*/
    }
    public static void setScene(Game_FrameComponent s){
        scene.end();
        scene=s;
        scene.start();
    }
}
