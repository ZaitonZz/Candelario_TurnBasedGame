package com.example.candelario_turnbasedgame.Controller;

import static com.example.candelario_turnbasedgame.Model.Monster.getMons_base_hp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.example.candelario_turnbasedgame.Model.Monster;

public class SkillwStatsR {
    byte seed;
    // stat randomizer for monster level up
    public List<Object> getmonsterlevelRandomizer(byte mons_lvl_value, float mons_hp_value, float mons_mana_value,float monsterMinDamage, float monsterMaxDamage){
        Random randomizer = new Random();
        getMons_base_hp();
        this.seed= (byte) randomizer.nextInt(mons_lvl_value);
        if (seed==0){
            seed = mons_lvl_value;
        }
        mons_hp_value = seed * mons_base_hp;
        mons_mana_value = seed * mons_base_mana;
        monsterMinDamage = seed * mons_base_minDamage;
        monsterMaxDamage = seed * mons_base_maxDamage;
        return Arrays.asList(mons_hp_value, mons_mana_value,monsterMinDamage,monsterMaxDamage);
    }

}
