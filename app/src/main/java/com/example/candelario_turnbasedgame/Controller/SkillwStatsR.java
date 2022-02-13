package com.example.candelario_turnbasedgame.Controller;

import static com.example.candelario_turnbasedgame.Model.Monster.*;

import com.example.candelario_turnbasedgame.Model.Monster;
import java.util.Random;

public class SkillwStatsR {
    byte seed;

    public SkillwStatsR(){};

    // stat randomizer for monster level up
    public void getmonsterstatRandomizer(byte mons_lvl_value, float mons_hp_value, float mons_mana_value,float monsterMinDamage, float monsterMaxDamage){
        Random randomizer = new Random();

        this.seed= (byte) randomizer.nextInt(mons_lvl_value);
        if (seed==0){
            seed = mons_lvl_value;
        }
        mons_hp_value = seed * getMons_base_hp();
        mons_mana_value = seed * getMons_base_mana();
        monsterMinDamage = seed * getMons_base_maxDamage();
        monsterMaxDamage = seed * getMons_base_minDamage();
        test.setMons_hp_value( mons_hp_value);
    }


}
