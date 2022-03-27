package javaapplication4;
class Event {

    public void set_x(int map_id, int id, int a) {
        //x = (a < 0 || a > Game_System.Map.map_max_x - 1) ? x : a;
    }

    public void set_y(int map_id, int id, int a) {
        //y = (a < 0 || a > Game_System.Map.map_max_y - 1) ? y : a;
    }

    public boolean hasEvent(int map_id, int x, int y) {
        for (int i = 0; i < Game_System.event[map_id].length; i++) {
            if (Game_System.event[map_id][i].x == x && Game_System.event[map_id][i].y == y) {
                return true;
            }
        }
        return false;
    }

    public int getEventID(int map_id, int x, int y) {
        for (int i = 0; i < Game_System.event[map_id].length; i++) {
            if (Game_System.event[map_id][i].x == x && Game_System.event[map_id][i].y == y) {
                return i;
            }
        }
        return -1;
    }

    public int getEventID(Event e) {
        /*for (int i = 0; i < Game_System.event[Game_System.Map.map_id].length; i++) {
            if (Game_System.event[Game_System.Map.map_id][i] == e) {
                return i;
            }
        }*/
        return -1;
    }

    public void start() {
        for (int row = 4; row < 999; row++) {
            Game_System.CommonEvent.doCommon(page[current_page][row], getEventID(this));
        }
    }

    public boolean startConditions() {
        switch (trigger_mode) {
            case 3:
                return true;
            case 4:
                return true;
        }
        return false;
    }

    public void refreshPage() {
        int[] record;
        boolean[] a;
        for (int i = 0; i < page.length; i++) {
            a = new boolean[]{false, false, false, false};
            for (int j = 0; j < 4; j++) {
                if (page[i][j][0] != null) {
                    int b = Integer.parseInt(page[i][j][1]);
                    int c = Integer.parseInt(page[i][j][2] == null ? "-1" : page[i][j][2]);
                    switch (page[i][j][0]) {
                        case "V==v":
                            a[j] = (Game_System.Variables[b] == variables[c]);
                            break;
                        case "v==V":
                            a[j] = (variables[b] == Game_System.Variables[c]);
                            break;
                        case "V!=v":
                            a[j] = (Game_System.Variables[b] != variables[c]);
                            break;
                        case "v!=V":
                            a[j] = (variables[b] != Game_System.Variables[c]);
                            break;
                        case "v==v":
                            a[j] = (variables[b] == variables[c]);
                            break;
                        case "v!=v":
                            a[j] = (variables[b] != variables[c]);
                            break;
                        case "V==V":
                            a[j] = (Game_System.Variables[b] == Game_System.Variables[c]);
                            break;
                        case "V!=V":
                            a[j] = (Game_System.Variables[b] != Game_System.Variables[c]);
                            break;
                        case "V>v":
                            a[j] = ((float) Game_System.Variables[b] > (float) variables[c]);
                            break;
                        case "V>=v":
                            a[j] = ((float) Game_System.Variables[b] >= (float) variables[c]);
                            break;
                        case "V<v":
                            a[j] = ((float) Game_System.Variables[b] < (float) variables[c]);
                            break;
                        case "V<=v":
                            a[j] = ((float) Game_System.Variables[b] <= (float) variables[c]);
                            break;
                        case "v>V":
                            a[j] = ((float) variables[b] > (float) Game_System.Variables[c]);
                            break;
                        case "v>=V":
                            a[j] = ((float) variables[b] >= (float) Game_System.Variables[c]);
                            break;
                        case "v<V":
                            a[j] = ((float) variables[b] < (float) Game_System.Variables[c]);
                            break;
                        case "v<=V":
                            a[j] = ((float) variables[b] <= (float) Game_System.Variables[c]);
                            break;
                        case "v>v":
                            a[j] = ((float) variables[b] > (float) variables[c]);
                            break;
                        case "v>=v":
                            a[j] = ((float) variables[b] >= (float) variables[c]);
                            break;
                        case "v<v":
                            a[j] = ((float) variables[b] < (float) variables[c]);
                            break;
                        case "v<=v":
                            a[j] = ((float) variables[b] <= (float) variables[c]);
                            break;
                        case "V>V":
                            a[j] = ((float) Game_System.Variables[b] > (float) Game_System.Variables[c]);
                            break;
                        case "V>=V":
                            a[j] = ((float) Game_System.Variables[b] >= (float) Game_System.Variables[c]);
                            break;
                        case "V<V":
                            a[j] = ((float) Game_System.Variables[b] < (float) Game_System.Variables[c]);
                            break;
                        case "V<=V":
                            a[j] = ((float) Game_System.Variables[b] <= (float) Game_System.Variables[c]);
                            break;
                        case "S":
                            a[j] = Game_System.Switches[b];
                            break;
                        case "s":
                            a[j] = switches[b];
                            break;
                    }
                } else {
                    a[j] = true;
                }
            }
        }
    }
    public volatile transient int x;
    public volatile transient int y;
    public volatile transient int orientation;
    public volatile transient int state;
    public volatile transient int current_page = 0;
    public volatile transient int trigger_mode;
    public volatile transient int[][] action = new int[999][99];
    public volatile transient String name;
    public volatile transient String icon;
    public volatile transient String[][][] page;
    public transient volatile boolean[] switches = new boolean[99];
    public volatile transient boolean[] passable = {false, false, false, false};
    public transient volatile Object[] variables = new Object[99];
}
