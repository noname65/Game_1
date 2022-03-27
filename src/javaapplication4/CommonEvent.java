package javaapplication4;
import java.util.logging.Level;
import java.util.logging.Logger;
class CommonEvent {
    public volatile transient boolean Move_complete;
    public void judgeCommon(String[] common, int id) {
    }

    private int judgeType(String a) {
        boolean b = false;
        boolean c = false;
        int d = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < 10; j++) {
                if (a.substring(i, i + 1) == null ? new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}[j] == null : a.substring(i, i + 1).equals(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}[j])) {
                    b = true;
                    break;
                } else if (".".equals(a.substring(i, i + 1))) {
                    b = false;
                    if (d < 1) {
                        c = true;
                    } else {
                        c = false;
                    }
                    d++;
                    break;
                } else if (!(i == 0 && "-".equals(a.substring(i, i + 1)))) {
                    b = false;
                    c = false;
                }
            }
        }
        if (b) {
            return 1;
        } else if (c) {
            return 2;
        } else {
            return 3;
        }
    }

    public Object[] doCommon(String[] common, int id) {
        Object[] a = {};
        int b;
        int c;
        Object[] d = {};
        int t1;
        int t2;
        boolean[] e;
        float[] f;
        if (null != common[0]) {
            switch (common[0]) {
                case "if":
                    a = new Object[1];
                    if (common[1] != null) {
                        switch (Integer.parseInt(common[1])) {
                            case 0:
                                b = Integer.parseInt(common[3]);
                                c = Integer.parseInt(common[4] == null ? "-1" : common[4]);
                                /*switch (common[2]) {
                                    case "V==v":
                                        a[0] = (Game_System.Variables[b] == Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v==V":
                                        a[0] = (Game_System.event[Game_System.Game_Map.map_id][id].variables[b] == Game_System.Variables[c]);
                                        break;
                                    case "V!=v":
                                        a[0] = (Game_System.Variables[b] != Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v!=V":
                                        a[0] = (Game_System.event[Game_System.Game_Map.map_id][id].variables[b] != Game_System.Variables[c]);
                                        break;
                                    case "v==v":
                                        a[0] = (Game_System.event[Game_System.Game_Map.map_id][id].variables[b] == Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v!=v":
                                        a[0] = (Game_System.event[Game_System.Game_Map.map_id][id].variables[b] != Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "V==V":
                                        a[0] = (Game_System.Variables[b] == Game_System.Variables[c]);
                                        break;
                                    case "V!=V":
                                        a[0] = (Game_System.Variables[b] != Game_System.Variables[c]);
                                        break;
                                    case "V>v":
                                        a[0] = ((float) Game_System.Variables[b] > (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "V>=v":
                                        a[0] = ((float) Game_System.Variables[b] >= (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "V<v":
                                        a[0] = ((float) Game_System.Variables[b] < (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "V<=v":
                                        a[0] = ((float) Game_System.Variables[b] <= (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v>V":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] > (float) Game_System.Variables[c]);
                                        break;
                                    case "v>=V":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] >= (float) Game_System.Variables[c]);
                                        break;
                                    case "v<V":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] < (float) Game_System.Variables[c]);
                                        break;
                                    case "v<=V":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] <= (float) Game_System.Variables[c]);
                                        break;
                                    case "v>v":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] > (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v>=v":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] >= (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v<v":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] < (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "v<=v":
                                        a[0] = ((float) Game_System.event[Game_System.Game_Map.map_id][id].variables[b] <= (float) Game_System.event[Game_System.Game_Map.map_id][id].variables[c]);
                                        break;
                                    case "V>V":
                                        a[0] = ((float) Game_System.Variables[b] > (float) Game_System.Variables[c]);
                                        break;
                                    case "V>=V":
                                        a[0] = ((float) Game_System.Variables[b] >= (float) Game_System.Variables[c]);
                                        break;
                                    case "V<V":
                                        a[0] = ((float) Game_System.Variables[b] < (float) Game_System.Variables[c]);
                                        break;
                                    case "V<=V":
                                        a[0] = ((float) Game_System.Variables[b] <= (float) Game_System.Variables[c]);
                                        break;
                                    case "S":
                                        a[0] = Game_System.Switches[b];
                                        break;
                                    case "s":
                                        a[0] = Game_System.event[Game_System.Game_Map.map_id][id].switches[b];
                                        break;
                                }*/
                                break;
                            case 1:
                                b = Integer.parseInt(common[3]);
                                c = Integer.parseInt(common[4] == null ? "-1" : common[4]);
                                switch (common[2]) {
                                    case "V==V":
                                        a[0] = (Game_System.Variables[b] == Game_System.Variables[c]);
                                        break;
                                    case "V!=V":
                                        a[0] = (Game_System.Variables[b] != Game_System.Variables[c]);
                                        break;
                                    case "V>V":
                                        a[0] = ((float) Game_System.Variables[b] > (float) Game_System.Variables[c]);
                                        break;
                                    case "V>=V":
                                        a[0] = ((float) Game_System.Variables[b] >= (float) Game_System.Variables[c]);
                                        break;
                                    case "V<V":
                                        a[0] = ((float) Game_System.Variables[b] < (float) Game_System.Variables[c]);
                                        break;
                                    case "V<=V":
                                        a[0] = ((float) Game_System.Variables[b] <= (float) Game_System.Variables[c]);
                                        break;
                                    case "S":
                                        a[0] = Game_System.Switches[b];
                                        break;
                                }
                                break;
                        }
                    } else {
                        a[0] = false;
                    }
                    break;
                case "for":
                    break;
                case "while":
                    break;
                case "end":
                    break;
                case "break":
                    break;
                case "v":
                    if (common[1] != null) {
                        switch (Integer.parseInt(common[1])) {
                            case 0:
                                if (common[2] != null) {
                                    switch (common[2]) {
                                        case "=":
                                            d = new Object[]{};
                                            b = Integer.parseInt(common[3]);
                                            c = Integer.parseInt(common[4] == null ? "-1" : common[4]);
                                            Game_System.event[Game_System.Game_Map.map_id][id].variables[b] = c;
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case "V":
                    if (common[1] != null) {
                        b = Integer.parseInt(common[2]);
                        t1 = judgeType(common[2]);
                        t2 = judgeType(common[3]);
                        switch (common[1]) {
                            case "+=c":
                                if (t2 == 1) {
                                    c = Integer.parseInt(common[3] == null ? "0" : common[3]);
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] + c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] + (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{Float.parseFloat(common[3] == null ? "0" : common[3])};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] + f[0];
                                } else if (t2 == 3) {
                                    Game_System.Variables[b] = Game_System.Variables[b].toString() + common[3];
                                }
                                break;
                            case "-=c":
                                if (t2 == 1) {
                                    c = Integer.parseInt(common[3] == null ? "0" : common[3]);
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] - c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] - (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{Float.parseFloat(common[3] == null ? "0" : common[3])};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] - f[0];
                                }
                                break;
                            case "*=c":
                                if (t2 == 1) {
                                    c = Integer.parseInt(common[3] == null ? "1" : common[3]);
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] * c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] * (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{Float.parseFloat(common[3] == null ? "1" : common[3])};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] * f[0];
                                }
                                break;
                            case "/=c":
                                if (t2 == 1) {
                                    c = Integer.parseInt(common[3] == null ? "1" : common[3]);
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] / c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] / (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{Float.parseFloat(common[3] == null ? "1" : common[3])};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] / f[0];
                                }
                                break;
                            case "+=V":
                                if (t2 == 1) {
                                    c = (int) Game_System.Variables[Integer.parseInt(common[3] == null ? "0" : common[3])];
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] + c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] + (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{(float) Game_System.Variables[Integer.parseInt(common[3] == null ? "0" : common[3])]};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] + f[0];
                                } else if (t2 == 3) {
                                    Game_System.Variables[b] = Game_System.Variables[b].toString() + common[3];
                                }
                                break;
                            case "-=V":
                                if (t2 == 1) {
                                    c = (int) Game_System.Variables[Integer.parseInt(common[3] == null ? "0" : common[3])];
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] - c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] - (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{(float) Game_System.Variables[Integer.parseInt(common[3] == null ? "0" : common[3])]};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] - f[0];
                                }
                                break;
                            case "*=V":
                                if (t2 == 1) {
                                    c = (int) Game_System.Variables[Integer.parseInt(common[3] == null ? "1" : common[3])];
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] * c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] * (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{(float) Game_System.Variables[Integer.parseInt(common[3] == null ? "1" : common[3])]};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] * f[0];
                                }
                                break;
                            case "/=V":
                                if (t2 == 1) {
                                    c = (int) Game_System.Variables[Integer.parseInt(common[3] == null ? "1" : common[3])];
                                    if (t1 == 1) {
                                        Game_System.Variables[b] = (int) Game_System.Variables[b] / c;
                                    } else if (t1 == 2) {
                                        Game_System.Variables[b] = (float) Game_System.Variables[b] / (float) c;
                                    }
                                } else if (t2 == 2) {
                                    f = new float[]{(float) Game_System.Variables[Integer.parseInt(common[3] == null ? "1" : common[3])]};
                                    Game_System.Variables[b] = (float) Game_System.Variables[b] / f[0];
                                }
                                break;
                        }
                    }
                    break;
                case "s":
                    break;
                case "S":
                    break;
                case "Message":
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                    }
                    Game_System.Window_Message.message = common[1];
                    Game_System.Window_Message.Visible = true;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                    }
                    break;
                case "wait":
                    try {
                        Thread.sleep(Integer.parseInt(common[1]));
                    } catch (InterruptedException ex) {
                    }
                    break;
                case "switchMap":
                    switchMap(Integer.parseInt(common[1]), Integer.parseInt(common[2]), Integer.parseInt(common[3]));
                    break;
                default:
                    break;
            }
        }
        return a;
    }

    private void switchMap(int mapid, int x, int y) {
        Game_System.Game_Map.map_id = mapid;
        Game_System.actor[Game_System.actor_id].x = x;
        Game_System.actor[Game_System.actor_id].y = y;
        Game_System.Game_Map.moveingX = 0;
        Game_System.Game_Map.moveingY = 0;
        Game_System.Game_Map.screen_x = 0;
        Game_System.Game_Map.screen_y = 0;
        Game_System.Game_Map.loadMapElements();
        updateScreen();
    }

    private void ActorMove(int direction) {
        if (Game_System.timer[1].end) {
            switch (direction) {
                case 0:
                    Game_System.actor[Game_System.actor_id].orientation = 0;
                    if (Game_System.actor[Game_System.actor_id].y + 1 >= 0 && Game_System.actor[Game_System.actor_id].y + 1 <= Game_System.Game_Map.map_max_y - 1 && passable(Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y + 1)) {
                        for (int i = 0; i < 32 / Game_System.speed - 1; i++) {
                            if (i % (32 / (Game_System.speed * 2)) == 0) {
                                if (Game_System.actor[Game_System.actor_id].state < 3) {
                                    Game_System.actor[Game_System.actor_id].state++;
                                } else {
                                    Game_System.actor[Game_System.actor_id].state = 0;
                                }
                            }
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                            }
                            Game_System.Game_Map.moveingY += Game_System.speed;
                            updateScreen();
                        }
                        Game_System.actor[Game_System.actor_id].y++;
                        Game_System.Game_Map.moveingY = 0;
                        Game_System.timer[1] = new Timer("move1", 1, 90 - Game_System.frequency * 10, 0);
                        Game_System.timer[1].start();
                    } else if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y + 1)) {
                        int id = Game_System.Event.getEventID(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y + 1);
                        if (Game_System.event[Game_System.Game_Map.map_id][id].trigger_mode == 1) {
                            Game_System.event[Game_System.Game_Map.map_id][id].start();
                        }
                    }
                    break;
                case 1:
                    Game_System.actor[Game_System.actor_id].orientation = 1;
                    if (Game_System.actor[Game_System.actor_id].x - 1 >= 0 && Game_System.actor[Game_System.actor_id].x - 1 <= Game_System.Game_Map.map_max_x - 1 && passable(Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y, Game_System.actor[Game_System.actor_id].x - 1, Game_System.actor[Game_System.actor_id].y)) {
                        for (int i = 0; i < 32 / Game_System.speed - 1; i++) {
                            if (i % (32 / (Game_System.speed * 2)) == 0) {
                                if (Game_System.actor[Game_System.actor_id].state < 3) {
                                    Game_System.actor[Game_System.actor_id].state++;
                                } else {
                                    Game_System.actor[Game_System.actor_id].state = 0;
                                }
                            }
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                            }
                            Game_System.Game_Map.moveingX -= Game_System.speed;
                            updateScreen();
                        }
                        Game_System.actor[Game_System.actor_id].x--;
                        Game_System.Game_Map.moveingX = 0;
                        Game_System.timer[1] = new Timer("move1", 0, 90 - Game_System.frequency * 10, 0);
                        Game_System.timer[1].start();
                    } else if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x - 1, Game_System.actor[Game_System.actor_id].y)) {
                        int id = Game_System.Event.getEventID(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x - 1, Game_System.actor[Game_System.actor_id].y);
                        if (Game_System.event[Game_System.Game_Map.map_id][id].trigger_mode == 1) {
                            Game_System.event[Game_System.Game_Map.map_id][id].start();
                        }
                    }
                    break;
                case 2:
                    Game_System.actor[Game_System.actor_id].orientation = 2;
                    if (Game_System.actor[Game_System.actor_id].x + 1 >= 0 && Game_System.actor[Game_System.actor_id].x + 1 <= Game_System.Game_Map.map_max_x - 1 && passable(Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y, Game_System.actor[Game_System.actor_id].x + 1, Game_System.actor[Game_System.actor_id].y)) {
                        for (int i = 0; i < 32 / Game_System.speed - 1; i++) {
                            if (i % (32 / (Game_System.speed * 2)) == 0) {
                                if (Game_System.actor[Game_System.actor_id].state < 3) {
                                    Game_System.actor[Game_System.actor_id].state++;
                                } else {
                                    Game_System.actor[Game_System.actor_id].state = 0;
                                }
                            }
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                            }
                            Game_System.Game_Map.moveingX += Game_System.speed;
                            updateScreen();
                        }
                        Game_System.actor[Game_System.actor_id].x++;
                        Game_System.Game_Map.moveingX = 0;
                        Game_System.timer[1] = new Timer("move1", 0, 90 - Game_System.frequency * 10, 0);
                        Game_System.timer[1].start();
                    } else if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x + 1, Game_System.actor[Game_System.actor_id].y)) {
                        int id = Game_System.Event.getEventID(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x + 1, Game_System.actor[Game_System.actor_id].y);
                        if (Game_System.event[Game_System.Game_Map.map_id][id].trigger_mode == 1) {
                            Game_System.event[Game_System.Game_Map.map_id][id].start();
                        }
                    }
                    break;
                case 3:
                    Game_System.actor[Game_System.actor_id].orientation = 3;
                    if (Game_System.actor[Game_System.actor_id].y - 1 >= 0 && Game_System.actor[Game_System.actor_id].y - 1 <= Game_System.Game_Map.map_max_y - 1 && passable(Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y - 1)) {
                        for (int i = 0; i < (32 / Game_System.speed) - 1; i++) {
                            if (i % (32 / (Game_System.speed * 2)) == 0) {
                                if (Game_System.actor[Game_System.actor_id].state < 3) {
                                    Game_System.actor[Game_System.actor_id].state++;
                                } else {
                                    Game_System.actor[Game_System.actor_id].state = 0;
                                }
                            }
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                            }
                            Game_System.Game_Map.moveingY -= Game_System.speed;
                            updateScreen();
                        }
                        Game_System.actor[Game_System.actor_id].y--;
                        Game_System.Game_Map.moveingY = 0;
                        Game_System.timer[1] = new Timer("move1", 0, 90 - Game_System.frequency * 10, 0);
                        Game_System.timer[1].start();
                    } else if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y - 1)) {
                        int id = Game_System.Event.getEventID(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y - 1);
                        if (Game_System.event[Game_System.Game_Map.map_id][id].trigger_mode == 1) {
                            Game_System.event[Game_System.Game_Map.map_id][id].start();
                        }
                    }
                    break;
            }
        }
        Game_System.actor[Game_System.actor_id].state = Game_System.actor[Game_System.actor_id].state == 1 ? 2 : Game_System.actor[Game_System.actor_id].state == 3 ? 0 : Game_System.actor[Game_System.actor_id].state;
        updateScreen();
        if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y)) {
            int id = Game_System.Event.getEventID(Game_System.Game_Map.map_id, Game_System.actor[Game_System.actor_id].x, Game_System.actor[Game_System.actor_id].y);
            if (Game_System.event[Game_System.Game_Map.map_id][id].trigger_mode == 1) {
                Game_System.event[Game_System.Game_Map.map_id][id].start();
            }
        }
        Move_complete = true;
    }

    private boolean passable(int x1, int y1, int x2, int y2) {
        boolean a = true;
        for (int i = 0; i < 4; i++) {
            if (x1 == x2) {
                if (y1 - y2 == 1) {
                    if (Game_System.Game_Map.current_map[i][y2][x2][3] == 0 && Game_System.Game_Map.current_map[i][y2][x2][2] != 0) {
                        a = Game_System.Game_Map.current_map[i][y2][x2][4] == 0;
                    }
                    if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, x2, y2)) {
                        a = Game_System.event[Game_System.Game_Map.map_id][Game_System.Event.getEventID(Game_System.Game_Map.map_id, x2, y2)].passable[0];
                    }
                } else if (y1 - y2 == -1) {
                    if (Game_System.Game_Map.current_map[i][y2][x2][3] == 0 && Game_System.Game_Map.current_map[i][y2][x2][2] != 0) {
                        a = Game_System.Game_Map.current_map[i][y2][x2][7] == 0;
                    }
                    if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, x2, y2)) {
                        a = Game_System.event[Game_System.Game_Map.map_id][Game_System.Event.getEventID(Game_System.Game_Map.map_id, x2, y2)].passable[3];
                    }
                }
            } else if (x1 - x2 == 1 && y1 == y2) {
                if (Game_System.Game_Map.current_map[i][y2][x2][3] == 0 && Game_System.Game_Map.current_map[i][y2][x2][2] != 0) {
                    a = Game_System.Game_Map.current_map[i][y2][x2][6] == 0;
                }
                if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, x2, y2)) {
                    a = Game_System.event[Game_System.Game_Map.map_id][Game_System.Event.getEventID(Game_System.Game_Map.map_id, x2, y2)].passable[2];
                }
            } else if (x1 - x2 == -1 && y1 == y2) {
                if (Game_System.Game_Map.current_map[i][y2][x2][3] == 0 && Game_System.Game_Map.current_map[i][y2][x2][2] != 0) {
                    a = Game_System.Game_Map.current_map[i][y2][x2][5] == 0;
                }
                if (Game_System.Event.hasEvent(Game_System.Game_Map.map_id, x2, y2)) {
                    a = Game_System.event[Game_System.Game_Map.map_id][Game_System.Event.getEventID(Game_System.Game_Map.map_id, x2, y2)].passable[1];
                }
            }
        }
        return a;
    }

    public void updateScreen() {
        if (Game_System.Game_Map.map_max_x > 20 && Game_System.actor[Game_System.actor_id].x >= 9 && Game_System.actor[Game_System.actor_id].x <= Game_System.Game_Map.map_max_x - 10) {
            if (Game_System.actor[Game_System.actor_id].x == Game_System.Game_Map.map_max_x - 10 || Game_System.actor[Game_System.actor_id].x == 9) {
                Game_System.Game_Map.screen_x = -(Game_System.actor[Game_System.actor_id].x == Game_System.Game_Map.map_max_x - 10 ? ((Game_System.actor[Game_System.actor_id].x - 9) < 0 ? 0 : Game_System.actor[Game_System.actor_id].x - 9) * 32 - 32 : ((Game_System.actor[Game_System.actor_id].x - 9) < 0 ? 0 : Game_System.actor[Game_System.actor_id].x - 9) * 32);
            } else {
                Game_System.Game_Map.screen_x = -(((Game_System.actor[Game_System.actor_id].x - 9) < 0 ? 0 : Game_System.actor[Game_System.actor_id].x - 9) * 32 - 16);
            }
            if ((Game_System.actor[Game_System.actor_id].x > 10 && Game_System.actor[Game_System.actor_id].x < Game_System.Game_Map.map_max_x - 11) || (Game_System.actor[Game_System.actor_id].x == 10 && Game_System.Game_Map.moveingX > 0) || (Game_System.actor[Game_System.actor_id].x == Game_System.Game_Map.map_max_x - 11 && Game_System.Game_Map.moveingX < 0)) {
                Game_System.Game_Map.screen_x += -Game_System.Game_Map.moveingX;
            } else if (Game_System.actor[Game_System.actor_id].x == 9 && Game_System.Game_Map.moveingX > 0) {
                Game_System.Game_Map.screen_x += -(Game_System.Game_Map.moveingX < 16 ? 0 : Game_System.Game_Map.moveingX - 16);
            } else if (Game_System.actor[Game_System.actor_id].x == 10 && Game_System.Game_Map.moveingX < 0) {
                Game_System.Game_Map.screen_x += -(Game_System.Game_Map.moveingX > -16 ? Game_System.Game_Map.moveingX : -16);
            } else if (Game_System.actor[Game_System.actor_id].x == Game_System.Game_Map.map_max_x - 10 && Game_System.Game_Map.moveingX < 0) {
                Game_System.Game_Map.screen_x += -(Game_System.Game_Map.moveingX > -16 ? 0 : Game_System.Game_Map.moveingX + 16);
            } else if (Game_System.actor[Game_System.actor_id].x == Game_System.Game_Map.map_max_x - 11 && Game_System.Game_Map.moveingX > 0) {
                Game_System.Game_Map.screen_x += -(Game_System.Game_Map.moveingX > 16 ? 16 : Game_System.Game_Map.moveingX);
            }
        }
        if (Game_System.Game_Map.map_max_y > 15 && Game_System.actor[Game_System.actor_id].y >= 7 && Game_System.actor[Game_System.actor_id].y < Game_System.Game_Map.map_max_y - 7) {
            if (Game_System.actor[Game_System.actor_id].y > 7) {
                Game_System.Game_Map.screen_y = -((Game_System.actor[Game_System.actor_id].y - 7) < 0 ? 0 : Game_System.actor[Game_System.actor_id].y - 7) * 32;
            } else {
                Game_System.Game_Map.screen_y = 0;
            }
            if ((Game_System.actor[Game_System.actor_id].y > 7 && Game_System.actor[Game_System.actor_id].y < Game_System.Game_Map.map_max_y - 8) || (Game_System.actor[Game_System.actor_id].y == Game_System.Game_Map.map_max_y - 8 && Game_System.Game_Map.moveingY < 0) || (Game_System.actor[Game_System.actor_id].y == 7 && Game_System.Game_Map.moveingY > 0)) {
                Game_System.Game_Map.screen_y += -Game_System.Game_Map.moveingY;
            }
        }
    }

    public void TimerEnd(int id) {
        switch (id) {
            case 1:
                Game_System.CommonEvent.ActorMove(0);
                break;
            case 2:
                Game_System.CommonEvent.ActorMove(1);
                break;
            case 3:
                Game_System.CommonEvent.ActorMove(2);
                break;
            case 4:
                Game_System.CommonEvent.ActorMove(3);
                break;
            case 5:
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                    }
                }
            case 6:
                break;
        }
    }
}
