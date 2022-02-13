package com.example.candelario_turnbasedgame.View;

import static android.content.ContentValues.TAG;
import static com.example.candelario_turnbasedgame.R.id.btnNormAtk;
import static com.example.candelario_turnbasedgame.R.string.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.candelario_turnbasedgame.Controller.SkillwStatsR;
import com.example.candelario_turnbasedgame.Model.Monster;
import com.example.candelario_turnbasedgame.R;

import java.util.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mons_name,mons_hp,mons_lvl,mons_mana,hero_name,hero_hp,hero_lvl,hero_mana,combat_log;
    Button btnNormAtk;
    ImageButton skill1,skill2,skill3,skill4;

    String hero_level = "Lvl:\r";
    String mons_level = "Lvl:\r";

    Monster test= new Monster();
    // Global Variables for systems
    short turnNumber = 0;
    byte stuncounter = 0;
    byte burncounter = 0;
    byte sleepcounter = 0;
    double burn1 = 0.2;
    boolean disabledstatus = false;

    // Variables related to the skills
    byte skill1_cost=5;
    byte skill2_cost=9;
    byte skill3_cost=5;
    byte skill4_cost=10;
    byte cd1 = 0;
    byte cd2 = 0;
    byte cd3 = 0;
    byte cd4 = 0;

    // Monster Stats
    static float mons_base_hp = 20;
    static float mons_base_mana = 10;
    static float mons_base_minDamage = 3;
    static float mons_base_maxDamage = 5;
    float mons_hp_value = mons_base_hp;
    float mons_mana_value = mons_base_mana;
    float monsterMinDamage = mons_base_minDamage;
    float monsterMaxDamage = mons_base_maxDamage;
    byte mons_lvl_value = 1;

    // Hero Stats
    static float hero_base_hp =50;
    static float hero_base_mana = 10;
    static float hero_base_minDamage = 5;
    static float hero_base_maxDamage = 10;
    static byte hero_base_xpcounter = 0;
    float hero_hp_value = hero_base_hp;
    float hero_mana_value = hero_base_mana;
    float heroMinDamage =hero_base_minDamage;
    float heroMaxDamage =hero_base_maxDamage;
    byte hero_lvl_value = 1;
    byte hero_xpcounter= hero_base_xpcounter;

    // exp
    SkillwStatsR test1= new SkillwStatsR();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        enableFullscreen();

        // Calling TextViews
        mons_name=findViewById(R.id.mons_name);
        mons_hp=findViewById(R.id.mons_hp);
        mons_lvl=findViewById(R.id.mons_lvl);
        mons_mana=findViewById(R.id.mons_mana);
        hero_name=findViewById(R.id.hero_name);
        hero_hp=findViewById(R.id.hero_hp);
        hero_lvl=findViewById(R.id.hero_lvl);
        hero_mana=findViewById(R.id.hero_mana);
        combat_log=findViewById(R.id.combat_log);

        // Calling Buttons
        btnNormAtk=findViewById(R.id.btnNormAtk);
        skill1=findViewById(R.id.skill1);
        skill2=findViewById(R.id.skill2);
        skill3=findViewById(R.id.skill3);
        skill4=findViewById(R.id.skill4);

        // Replacing the placeholder texts
        mons_name.setText(R.string.mons_name);
        mons_hp.setText(String.valueOf(mons_hp_value));
        mons_mana.setText(String.valueOf(mons_mana_value));
        mons_lvl.setText((mons_level + mons_lvl_value));
        hero_name.setText(R.string.hero_name);
        hero_hp.setText(String.valueOf(hero_hp_value));
        hero_mana.setText(String.valueOf(hero_mana_value));
        hero_lvl.setText(hero_level + hero_lvl_value);
        combat_log.setText(empty_text);
        btnNormAtk.setText(start_game);

        // Replacing the placeholder image in the image Buttons
        skill1.setImageResource(R.drawable.btn_stunspore);
        skill2.setImageResource(R.drawable.btn_sacredfire);
        skill3.setImageResource(R.drawable.btn_flamethrower);
        skill4.setImageResource(R.drawable.btn_recover);

        // Onclick listeners
        btnNormAtk.setOnClickListener(this);
        skill1.setOnClickListener(this);
        skill2.setOnClickListener(this);
        skill3.setOnClickListener(this);
        skill4.setOnClickListener(this);

        // Disables skills before the game starts
        skill1.setEnabled(false);
        skill2.setEnabled(false);
        skill3.setEnabled(false);
        skill4.setEnabled(false);
    }

    public void onClick(View v) {

        // Placed these as local variables in order to have their values update as the monster/hero levels up
        int hero_minDamage = Math.round(heroMinDamage);
        int hero_maxDamage = Math.round(heroMaxDamage);
        int mons_minDamage = Math.round(monsterMinDamage);
        int mons_maxDamage = Math.round(monsterMaxDamage);

        // Called the randomizer for herodps and monsdps
        Random randomizer = new Random();
        int herodps = randomizer.nextInt(hero_maxDamage - hero_minDamage) + hero_minDamage;
        int monsdps = randomizer.nextInt(mons_maxDamage - mons_minDamage) + mons_minDamage;

        // if condition checking if it is my turn or enemy turn
        if(turnNumber % 2 != 1){
            skill1.setEnabled(false);
            skill2.setEnabled(false);
            skill3.setEnabled(false);
            skill4.setEnabled(false);
        }
        else if(turnNumber%2 == 1){
            skill1.setEnabled(true);
            skill2.setEnabled(true);
            skill3.setEnabled(true);
            skill4.setEnabled(true);
        }
        // if condition checking for skill1 cooldown
        if(cd1>0){
            skill1.setEnabled(false);
            cd1--;
        }
        else if(cd1==0){
            skill1.setEnabled(true);
        }
        // if condition checking for skill2 cooldown
        if(cd2>0){
            skill2.setEnabled(false);
            cd2--;
        }
        else if(cd2==0){
            skill2.setEnabled(true);
        }
        // if condition checking for skill3 cooldown
        if(cd3>0){
            skill3.setEnabled(false);
            cd3--;
        }
        else if(cd3==0){
            skill3.setEnabled(true);
        }
        // if condition checking for skill4 cooldown
        if(cd4>0){
            skill4.setEnabled(false);
            cd4--;
        }
        else if(cd4==0){
            skill4.setEnabled(true);
        }


        switch (v.getId()){
            case R.id.skill1:
                if (hero_mana_value-skill1_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                stuncounter=2;
                cd1=5;
                hero_mana_value= hero_mana_value - 5;
                hero_mana.setText(String.valueOf(hero_mana_value));
                skill1.setEnabled(false);
                Log.d(TAG, "skill 1 used");
                break;
            case R.id.skill2:
                if (hero_mana_value-skill2_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                mons_hp_value = mons_hp_value - 30;
                cd2=7;
                burncounter=3;
                hero_mana_value= hero_mana_value - 9;
                mons_hp.setText(String.valueOf(mons_hp_value));
                hero_mana.setText(String.valueOf(hero_mana_value));
                skill2.setEnabled(false);
                Log.d(TAG, "skill 2 used");
                break;
            case R.id.skill3:
                if (hero_mana_value-skill3_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                mons_hp_value = mons_hp_value - 10;
                cd3=3;
                burncounter=1;
                hero_mana_value= hero_mana_value - 5;
                mons_hp.setText(String.valueOf(mons_hp_value));
                hero_mana.setText(String.valueOf(hero_mana_value));
                skill3.setEnabled(false);
                Log.d(TAG, "skill 3 used");
                break;
            case R.id.skill4:
                if (hero_mana_value-skill4_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                hero_hp_value = hero_base_hp * hero_lvl_value;
                sleepcounter=4;
                cd4=9;
                hero_mana_value= hero_mana_value - 10;
                hero_hp.setText(String.valueOf(hero_hp_value));
                hero_mana.setText(String.valueOf(hero_mana_value));
                Log.d(TAG, "skill 4 used");
                skill4.setEnabled(false);
                break;
            case R.id.btnNormAtk:
                if(turnNumber % 2 == 1){ //odd
                    // if condition checking
                    if(sleepcounter>0){
                        combat_log.setText("The enemy is still stunned for "+String.valueOf(sleepcounter)+ "turns");
                        sleepcounter--;
                        turnNumber++;
                        hero_mana_value++;
                        hero_mana.setText(String.valueOf(hero_mana_value));
                        btnNormAtk.setText("Next Turn ("+ String.valueOf(turnNumber)+")");
                        // combat_log.setText(String.valueOf(cd1)+String.valueOf(cd2)+String.valueOf(cd3)+String.valueOf(cd4)); for debug purposes
                        break;
                    }
                    mons_hp_value = mons_hp_value - herodps;
                    turnNumber++;;
                    hero_mana_value++;
                    Log.d(TAG, "player attacked");
                    if (hero_mana_value>hero_base_mana * hero_lvl_value){
                        hero_mana_value = hero_base_mana * hero_lvl_value ;
                    }
                    mons_hp.setText(String.valueOf(mons_hp_value));
                    hero_mana.setText(String.valueOf(hero_mana_value));
                    btnNormAtk.setText("Next Turn ("+ String.valueOf(turnNumber)+")");

                    combat_log.setText("Our Hero Charizard dealt "+String.valueOf(herodps) + " damage to the enemy.");

                    if(mons_hp_value<= 0){ //checks if mons hp below 0
                        combat_log.setText("Our Hero Charizard dealt "+String.valueOf(herodps) + " damage to the enemy. The player is victorious!");
                        mons_lvl_value++;
                        hero_xpcounter++;
                        if(hero_xpcounter==hero_lvl_value){
                            hero_lvl_value++;
                            getherolevelRandomizer();
                            hero_xpcounter=0;
                        }
                        test1.getmonsterstatRandomizer(mons_lvl_value, mons_hp_value, mons_mana_value, monsterMinDamage, monsterMaxDamage);
                        setTextReset();
                        turnNumber= 0;
                        cd1=0;
                        cd2=0;
                        cd3=0;
                        cd4=0;
                        btnNormAtk.setText("Next Level");
                    }


                }
                else if(turnNumber%2 != 1){ //even

                    if(stuncounter>0){
                        combat_log.setText("The enemy is still stunned for "+String.valueOf(stuncounter)+ "turns");
                        stuncounter--;
                        turnNumber++;
                        btnNormAtk.setText("Next Turn ("+ String.valueOf(turnNumber)+")");
                    }
                    else if(burncounter>0){
                        mons_hp_value = mons_hp_value - burn(mons_hp_value, burn1);
                        mons_hp.setText(String.valueOf(mons_hp_value));
                        combat_log.setText("The enemy is has been burned for\r"+burncounter+"\rturns.");
                        burncounter--;
                        turnNumber++;
                        btnNormAtk.setText("Next Turn ("+ String.valueOf(turnNumber)+")");
                    }
                    else{
                        hero_hp_value = hero_hp_value - monsdps;
                        turnNumber++;
                        hero_hp.setText(String.valueOf(hero_hp_value));
                        btnNormAtk.setText("Next Turn ("+ String.valueOf(turnNumber)+")");
                        Log.d(TAG, "monster attacked");
                        combat_log.setText("The monster Arceus dealt "+String.valueOf(monsdps)+ " damage to the enemy.");

                        if(hero_hp_value<= 0){ //checks if hero hp below 0
                            combat_log.setText("The enemy Arceus dealt "+String.valueOf(monsdps) + " damage to the enemy. You lose");
                            cd1=0;
                            cd2=0;
                            cd3=0;
                            cd4=0;
                            hero_xpcounter= hero_base_xpcounter;
                            btnNormAtk.setText("Reset Game");
                            mons_lvl_value =1;
                            hero_lvl_value =1;
                            getmonsterlevelRandomizer();
                            getherolevelRandomizer();
                            setTextReset();
                        }
                    }
                }
                break;
        }
    }

    public static int burn(double mons_hp_value, double burn1){
        Log.d(TAG, "burn is working");
        return (int)Math.round(mons_hp_value * burn1);
    }
    // code for fullscreen
    private void enableFullscreen() {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
    }
    // stat randomizer for monster level up
    private List<Object> getmonsterlevelRandomizer(){
        Random randomizer = new Random();
        this.mons_hp_value = randomizer.nextInt(this.mons_lvl_value)* mons_base_hp;
        if (mons_hp_value==0){
            this.mons_hp_value= this.mons_lvl_value * mons_base_hp;
        }
        this.mons_mana_value = randomizer.nextInt(this.mons_lvl_value)* mons_base_mana;
        if (mons_mana_value==0){
            this.mons_mana_value= this.mons_lvl_value * mons_base_mana;
        }
        this.monsterMinDamage = randomizer.nextInt(this.mons_lvl_value)* this.monsterMinDamage;
        if (monsterMinDamage==0){
            this.monsterMinDamage= this.mons_lvl_value * mons_base_minDamage;
        }
        this.monsterMaxDamage = randomizer.nextInt(this.mons_lvl_value) * this.monsterMaxDamage;
        if (monsterMaxDamage==0){
            this.monsterMaxDamage= this.mons_lvl_value * mons_base_maxDamage;
        }
        if (monsterMinDamage>= monsterMaxDamage){
            this.monsterMinDamage= this.mons_lvl_value * mons_base_minDamage;
            this.monsterMaxDamage= this.mons_lvl_value * mons_base_maxDamage;
        }
        return Arrays.asList(this.mons_hp_value, this.mons_mana_value,this.monsterMinDamage,this.monsterMaxDamage);
    }
    // stat randomizer for hero level up
    private List<Object> getherolevelRandomizer(){
        Random randomizer = new Random();
        this.hero_hp_value = randomizer.nextInt(this.hero_lvl_value)* hero_base_hp;
        if (hero_hp_value<=hero_base_hp){
            this.hero_hp_value= this.hero_lvl_value * hero_base_hp;
        }
        this.hero_mana_value = randomizer.nextInt(this.hero_lvl_value)* hero_base_mana;
        if (hero_mana_value<=hero_base_mana){
            this.hero_mana_value= this.hero_lvl_value * hero_base_mana;
        }
        this.heroMinDamage = randomizer.nextInt(this.hero_lvl_value)* this.heroMinDamage;
        if (heroMinDamage==0){
            this.heroMinDamage= this.hero_lvl_value * hero_base_minDamage;
        }
        this.heroMaxDamage = randomizer.nextInt(this.hero_lvl_value) * this.heroMaxDamage;
        if (heroMaxDamage==0){
            this.heroMaxDamage= this.hero_lvl_value * hero_base_maxDamage;
        }
        if (heroMinDamage>= heroMaxDamage){
            this.heroMinDamage= this.hero_lvl_value * hero_base_minDamage;
            this.heroMaxDamage= this.hero_lvl_value * hero_base_maxDamage;
        }
        return Arrays.asList(this.hero_hp_value, this.hero_mana_value,this.heroMinDamage,this.heroMaxDamage);
    }
    private void setTextReset(){
        mons_name.setText(R.string.mons_name);
        mons_hp.setText(String.valueOf(mons_hp_value));
        mons_mana.setText(String.valueOf(mons_mana_value));
        hero_name.setText(R.string.hero_name);
        hero_hp.setText(String.valueOf(hero_hp_value));
        hero_mana.setText(String.valueOf(hero_mana_value));
        mons_lvl.setText((mons_level + mons_lvl_value));
        hero_lvl.setText((mons_level + hero_lvl_value));
    }
}