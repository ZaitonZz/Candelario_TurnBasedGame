package com.example.candelario_turnbasedgame.Model;

import com.example.candelario_turnbasedgame.Controller.combatRelated;

public class Monster {
    // base_stats
    private float base_hp = 20;
    private float base_mana = 10;
    private float base_minDamage = 3;
    private float base_maxDamage = 5;

    // getters of base_stats

    public float getBase_hp() { return base_hp; }
    public float getBase_mana() { return base_mana; }
    public float getBase_maxDamage() { return base_maxDamage; }
    public float getBase_minDamage() { return base_minDamage; }

    // flexible variables
    private float hp = base_hp;
    float mana = base_mana;
    float minDamage = base_minDamage;
    float maxDamage = base_maxDamage;
    byte lvl = 1;
    float DPS;
    float prevDmg;

    // getters of flexible variables
    public float getHp() { return hp; }
    public float getMana() { return mana; }
    public float getMinDamage() { return minDamage; }
    public float getMaxDamage() { return maxDamage; }
    public byte getLvl() { return lvl; }
    public float getDPS(Monster mons) { combatRelated.monsDps(mons); return DPS;}
    public float getPrevDmg() { return prevDmg; }

    // setters of flexible variables
    public void setHp(float hp) { this.hp = hp; }
    public void setMana(float mana) { this.mana = mana; }
    public void setMinDamage(float monsMinDmg) { minDamage = monsMinDmg; }
    public void setMaxDamage(float monsMaxDmg) { maxDamage = monsMaxDmg; }
    public void setLvl(byte monslvl) { lvl = monslvl; }
    public void setDPS(float monsDps) { this.DPS = monsDps; }
    public void setPrevDmg(float prevDmg) { this.prevDmg = prevDmg; }

    // default caller
    public Monster(){};
}
