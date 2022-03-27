package javaapplication4;

import java.util.logging.Level;
import java.util.logging.Logger;
class Timer implements Runnable {
    
    public volatile transient Thread t;
    public volatile transient String threadName;
    public volatile transient int time;
    public volatile transient int type;
    public volatile transient int time1;
    public volatile transient int endid;
    public volatile transient boolean end;
    Timer(String name, int type, int time, int endid) {
        threadName = name;
        this.type = type;
        this.endid = endid;
        end = false;
        t = null;
        if (type == 0) {
            time1 = time;
            this.time = 0;
        } else if (type == 1) {
            this.time = time;
        }
    }

    public void run() {
        while (!this.end) {
            if (type == 0) {
                if (time >= time1) {
                    end = true;
                    Game_System.CommonEvent.TimerEnd(endid);
                } else {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                    }
                    time++;
                }
            } else if (type == 1) {
                if (time <= 0) {
                    end = true;
                    Game_System.CommonEvent.TimerEnd(endid);
                } else {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                    }
                    time--;
                }
            }
        }
    }
    public void ring(){
        Game_System.CommonEvent.TimerEnd(endid);
    }
    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
    
}
