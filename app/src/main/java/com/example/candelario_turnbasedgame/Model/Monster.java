package com.example.candelario_turnbasedgame.Model;

public class Monster {
    // base_stats
    private static float mons_base_hp = 20;
    private static float mons_base_mana = 10;
    private static float mons_base_minDamage = 3;
    private static float mons_base_maxDamage = 5;

    // getters of base_stats

    public static float getMons_base_hp() { return mons_base_hp; }
    public static float getMons_base_mana() { return mons_base_mana; }
    public static float getMons_base_maxDamage() { return mons_base_maxDamage; }
    public static float getMons_base_minDamage() { return mons_base_minDamage; }

    // flexible variables
    private float mons_hp_value = mons_base_hp;
    float mons_mana_value = mons_base_mana;
    float monsterMinDamage = mons_base_minDamage;
    float monsterMaxDamage = mons_base_maxDamage;
    byte mons_lvl_value = 1;

    // getters of flexible variables
    public float getMons_hp_value() { return mons_hp_value; }
    public float getMons_mana_value() { return mons_mana_value; }
    public float getMonsterMinDamage() { return monsterMinDamage; }
    public float getMonsterMaxDamage() { return monsterMaxDamage; }
    public byte getMons_lvl_value() { return mons_lvl_value; }

    // setters of flexible variables
    public void setMons_hp_value(float mons_hp_value) { this.mons_hp_value = mons_hp_value; }
    public void setMons_mana_value(float mons_mana_value) { this.mons_mana_value = mons_mana_value; }
    public void setMonsterMinDamage(float monsterMinDamage) { this.monsterMinDamage = monsterMinDamage; }
    public void setMonsterMaxDamage(float monsterMaxDamage) { this.monsterMaxDamage = monsterMaxDamage; }
    public void setMons_lvl_value(byte mons_lvl_value) { this.mons_lvl_value = mons_lvl_value; }

    // default caller
    public Monster(){};
}
