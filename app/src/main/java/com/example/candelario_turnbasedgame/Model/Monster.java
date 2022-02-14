package com.example.candelario_turnbasedgame.Model;

import com.example.candelario_turnbasedgame.Controller.combatRelated;

import java.util.List;

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
    static private float mons_hp_value = mons_base_hp;
    static float mons_mana_value = mons_base_mana;
    static float monsterMinDamage = mons_base_minDamage;
    static float monsterMaxDamage = mons_base_maxDamage;
    static byte mons_lvl_value = 1;
    static float monsDPS;
    static float prevMonsAtk;

    // getters of flexible variables
    public static float getMons_hp_value() { return mons_hp_value; }
    public static float getMons_mana_value() { return mons_mana_value; }
    public static float getMonsterMinDamage() { return monsterMinDamage; }
    public static float getMonsterMaxDamage() { return monsterMaxDamage; }
    public static byte getMons_lvl_value() { return mons_lvl_value; }
    public static float getMonsDPS() { combatRelated.monsDps(); return monsDPS;}
    public static float getPrevMonsAtk() { return prevMonsAtk; }

    // setters of flexible variables
    public static void setMons_hp_value(float hp) { mons_hp_value = hp; }
    public static void setMons_mana_value(float mana) { mons_mana_value = mana; }
    public static void setMonsterMinDamage(float monsMinDmg) { monsterMinDamage = monsMinDmg; }
    public static void setMonsterMaxDamage(float monsMaxDmg) { monsterMaxDamage = monsMaxDmg; }
    public static void setMons_lvl_value(byte monslvl) { mons_lvl_value = monslvl; }
    public static void setMonsDPS(float monsDps) { Monster.monsDPS = monsDps; }
    public static void setPrevMonsAtk(float prevMonsAtk) { Monster.prevMonsAtk = prevMonsAtk; }

    // default caller
    public Monster(){};
}
