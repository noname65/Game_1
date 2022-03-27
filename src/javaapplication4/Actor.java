package javaapplication4;
class Actor {
    public volatile transient int x = 0;
    public volatile transient int y = 0;
    public volatile transient int orientation = 0;
    public volatile transient int state = 0;
    public volatile transient String name;
    public volatile transient String icon;
    public volatile transient int str;
    public volatile transient int def;
    public volatile transient int hp;
    public volatile transient int maxhp;
    public volatile transient int mp;
    public volatile transient int maxmp;
    public volatile transient double str_rate = 1.00;
    public volatile transient double bdef_rate = 1.00;
    public volatile transient double maxhp_rate = 1.00;
    public volatile transient double maxmp_rate = 1.00;
    public volatile transient double atk_speed = 1.00;
}
