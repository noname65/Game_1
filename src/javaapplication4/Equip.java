package javaapplication4;
class Equip {
    
    public String[] equip_name = new String[9999];
    public int[] equip_atk = new int[9999];
    public int[] equip_shield = new int[9999];

    void initialize() {
        set_Equip(0, "", 1, 0);
    }

    private void set_Equip(int id, String name, int atk, int shield) {
        equip_name[id] = name;
        equip_atk[id] = atk;
        equip_shield[id] = shield;
    }

    private String get_name(int id) {
        return equip_name[id];
    }

    private int get_atk(int id) {
        return equip_atk[id];
    }

    private int get_shield(int id) {
        return equip_shield[id];
    }
}
