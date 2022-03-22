package com.example.candelario_turnbasedgame.Controller;

import static com.example.candelario_turnbasedgame.Model.Monster.*;

import com.example.candelario_turnbasedgame.Model.Hero;
import com.example.candelario_turnbasedgame.Model.Monster;

import java.util.Random;

public class combatRelated {
    static byte seed;
    static Random randomizer = new Random();

    public combatRelated(){}

    // stat randomizer for monster level up
    public static void getmonsterstatRandomizer(byte monslvl, Monster mons){
        Random randomizer = new Random();

        seed= (byte) randomizer.nextInt(monslvl);
        if (seed == 0){seed = monslvl;}
        float monsHP = seed * mons.getBase_hp();
        float monsMana = seed * mons.getBase_mana();
        float monsMinDamage = seed * mons.getBase_minDamage();
        float monsMaxDamage = seed * mons.getBase_maxDamage();
        mons.setHp(monsHP);
        mons.setMana(monsMana);
        mons.setMinDamage(monsMinDamage);
        mons.setMaxDamage(monsMaxDamage);
    }

    public static void monsDps(Monster mons){
        int mMinDmg = (int) mons.getMinDamage();
        int mMaxDmg = (int) mons.getMaxDamage();
        float x = (float) randomizer.nextInt(mMaxDmg-mMinDmg) + mMinDmg;
        mons.setDPS(x);
        mons.setPrevDmg(x);
 }

    public static void getherolevelRandomizer(Hero Charizard){
        Random randomizer = new Random();
        Charizard.setHp(randomizer.nextInt(Charizard.getLvl())* Charizard.getBase_hp());
        if (Charizard.getHp()<=Charizard.getBase_hp()){
            Charizard.setHp( Charizard.getLvl() * Charizard.getBase_hp());
        }
        Charizard.setMana( randomizer.nextInt(Charizard.getLvl())* Charizard.getBase_mana());
        if (Charizard.getMana()<=Charizard.getBase_mana()){
            Charizard.setMana(Charizard.getLvl() * Charizard.getBase_mana());
        }
        Charizard.setMinDamage( randomizer.nextInt(Charizard.getLvl())* Charizard.getBase_minDamage());
        if (Charizard.getMinDamage()==0){
            Charizard.setMinDamage(Charizard.getLvl() * Charizard.getBase_minDamage());
        }
        Charizard.setMaxDamage(randomizer.nextInt(Charizard.getLvl()) * Charizard.getBase_maxDamage());
        if (Charizard.getMaxDamage()==0){
            Charizard.setMaxDamage(Charizard.getLvl() * Charizard.getBase_maxDamage());
        }
        if (Charizard.getMinDamage()>= Charizard.getMaxDamage()){
            Charizard.setMinDamage(Charizard.getLvl() * Charizard.getBase_minDamage());
            Charizard.setMaxDamage( Charizard.getLvl() * Charizard.getBase_maxDamage());
        }
    }

    public static void HeroDps(Hero mons){
        int mMinDmg = (int) mons.getMinDamage();
        int mMaxDmg = (int) mons.getMaxDamage();
        float x = (float) randomizer.nextInt(mMaxDmg-mMinDmg) + mMinDmg;
        mons.setDPS(x);
        mons.setPrevDmg(x);
    }
}
