package com.example.candelario_turnbasedgame.Controller;

import static com.example.candelario_turnbasedgame.Model.Monster.*;

import com.example.candelario_turnbasedgame.Model.Monster;
import com.example.candelario_turnbasedgame.View.MainActivity;

import java.util.Random;

public class combatRelated {
    static byte seed;
    static Random randomizer = new Random();

    public combatRelated(){}

    // stat randomizer for monster level up
    public static void getmonsterstatRandomizer(byte monslvl){
        Random randomizer = new Random();

        seed= (byte) randomizer.nextInt(monslvl);
        if (seed == 0){seed = monslvl;}
        float monsHP = seed * getMons_base_hp();
        float monsMana = seed * getMons_base_mana();
        float monsMinDamage = seed * getMons_base_minDamage();
        float monsMaxDamage = seed * getMons_base_maxDamage();
        Monster.setMons_hp_value(monsHP);
        Monster.setMons_mana_value(monsMana);
        Monster.setMonsterMinDamage(monsMinDamage);
        Monster.setMonsterMaxDamage(monsMaxDamage);
    }
 public static void monsDps(){
        int mMinDmg = (int) Monster.getMonsterMinDamage();
        int mMaxDmg = (int) Monster.getMonsterMaxDamage();
        float x = (float) randomizer.nextInt(mMaxDmg-mMinDmg) + mMinDmg;
        Monster.setMonsDPS(x);
        Monster.setPrevMonsAtk(x);
 }

}
