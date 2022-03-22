package com.example.candelario_turnbasedgame.Model;

import com.example.candelario_turnbasedgame.Controller.combatRelated;

public class Hero {
    static float base_hp =50;
    static float base_mana = 10;
    static float base_minDamage = 5;
    static float base_maxDamage = 10;
    static byte base_xpcounter = 0;
    float hp = base_hp;
    float mana = base_mana;
    float MinDamage = base_minDamage;
    float MaxDamage = base_maxDamage;
    byte lvl = 1;
    byte xpcounter = base_xpcounter;
    float DPS;
    float prevDmg;

    public Hero(){}

    public float getHp() { return hp; }
    public void setHp(float hp) { this.hp = hp; }
    public float getMana() { return mana; }
    public void setMana(float mana) { this.mana = mana; }
    public float getMinDamage() { return MinDamage; }
    public void setMinDamage(float minDamage) { MinDamage = minDamage; }
    public float getMaxDamage() { return MaxDamage; }
    public void setMaxDamage(float maxDamage) { MaxDamage = maxDamage; }
    public byte getLvl() { return lvl; }
    public void setLvl(byte lvl) { this.lvl = lvl; }
    public byte getxpcounter() { return xpcounter; }
    public void setxpcounter(byte xpcounter) { this.xpcounter = xpcounter; }

    public float getDPS(Hero hero) { combatRelated.HeroDps(hero); return DPS; }
    public void setDPS(float DPS) { this.DPS = DPS; }
    public float getPrevDmg() { return prevDmg; }
    public void setPrevDmg(float prevDmg) { this.prevDmg = prevDmg; }

    public static float getBase_hp() { return base_hp; }
    public static float getBase_mana() { return base_mana; }
    public static float getBase_minDamage() { return base_minDamage; }
    public static float getBase_maxDamage() { return base_maxDamage; }
    public static byte getBase_xpcounter() { return base_xpcounter; }
}
