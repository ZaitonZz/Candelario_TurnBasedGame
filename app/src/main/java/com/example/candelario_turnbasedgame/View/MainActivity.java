package com.example.candelario_turnbasedgame.View;

import static android.content.ContentValues.TAG;
import com.example.candelario_turnbasedgame.Controller.combatRelated;
import static com.example.candelario_turnbasedgame.R.string.empty_text;
import static com.example.candelario_turnbasedgame.R.string.not_enough_mana;
import static com.example.candelario_turnbasedgame.R.string.start_game;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.candelario_turnbasedgame.Model.Hero;
import com.example.candelario_turnbasedgame.Model.Monster;
import com.example.candelario_turnbasedgame.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //TODO: add text beside buttons for clarifications
    //TODO: add background and music
    //TODO: add an intro screen with a different music there
    //TODO: add sound effects with every button click
    TextView mons_name,mons_hp,mons_lvl,mons_mana,hero_name,hero_hp,hero_lvl,hero_mana,combat_log;
    Button btnNormAtk;
    ImageButton skill1,skill2,skill3,skill4;

    String hero_level = "Lvl:\r";
    String mons_level = "Lvl:\r";


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

    // Arceus Stats
    Monster Arceus;
    byte monslvl = 1;

    // Hero Stats
    Hero Charizard;

    // exp
    combatRelated test1= new combatRelated();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        enableFullscreen();

        new combatRelated();
        Arceus = new Monster();
        Charizard = new Hero();

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
        mons_hp.setText(String.valueOf(Arceus.getBase_hp()));
        mons_mana.setText(String.valueOf(Arceus.getBase_mana()));
        mons_lvl.setText((mons_level + String.valueOf(Arceus.getLvl())));
        hero_name.setText(R.string.hero_name);
        hero_hp.setText(String.valueOf(Charizard.getHp()));
        hero_mana.setText(String.valueOf(Charizard.getMana()));
        hero_lvl.setText(hero_level + Charizard.getLvl());
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

        if(cd1>0){
            skill1.setEnabled(false);
            cd1--;
        }
        else if(cd1==0){ skill1.setEnabled(true); }
        if(cd2>0){
            skill2.setEnabled(false);
            cd2--;
        }
        else if(cd2==0){ skill2.setEnabled(true); }
        if(cd3>0){
            skill3.setEnabled(false);
            cd3--;
        }
        else if(cd3==0){ skill3.setEnabled(true); }
        if(cd4>0){
            skill4.setEnabled(false);
            cd4--;
        }
        else if(cd4==0){ skill4.setEnabled(true); }


        switch (v.getId()){
            case R.id.skill1:
                if (Charizard.getMana()-skill1_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                stuncounter=2;
                cd1=5;
                Charizard.setMana(Charizard.getMana()-5);
                hero_mana.setText(String.valueOf(Charizard.getMana()));
                combat_log.setText("Charizard used Stun Spore!");
                disableSkills();
                checkDeadMons();
                turnNumber++;
                btnNormAtk.setText("Enemy move!");
                Log.d(TAG, "skill 1 used");
                break;
            case R.id.skill2:
                if (Charizard.getMana()-skill2_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                Arceus.setHp(Arceus.getHp()-30);
                cd2=7;
                burncounter=3;
                Charizard.setMana(Charizard.getMana()-9);
                mons_hp.setText(String.valueOf(Arceus.getHp()));
                hero_mana.setText(String.valueOf(Charizard.getMana()));
                combat_log.setText("Charizard used Fire Blast!");
                disableSkills();
                checkDeadMons();
                turnNumber++;
                btnNormAtk.setText("Enemy move!");
                Log.d(TAG, "skill 2 used");
                break;
            case R.id.skill3:
                if (Charizard.getMana()-skill3_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                Arceus.setHp(Arceus.getHp()-10);
                cd3=3;
                burncounter=1;
                Charizard.setMana(Charizard.getMana()-5);
                mons_hp.setText(String.valueOf(Arceus.getHp()));
                hero_mana.setText(String.valueOf(Charizard.getMana()));
                combat_log.setText("Charizard used Flamethrower!");
                disableSkills();
                checkDeadMons();
                turnNumber++;
                btnNormAtk.setText("Enemy move!");
                Log.d(TAG, "skill 3 used");
                break;
            case R.id.skill4:
                if (Charizard.getMana()-skill4_cost<0) {
                    combat_log.setText(not_enough_mana);
                    break;
                }
                Charizard.setHp(Hero.getBase_hp() * Charizard.getLvl());
                sleepcounter=4;
                cd4=9;
                Charizard.setMana(Charizard.getMana() - 10);
                combat_log.setText("Charizard used Rest!");
                hero_hp.setText(String.valueOf(Charizard.getHp()));
                hero_mana.setText(String.valueOf(Charizard.getMana()));
                Log.d(TAG, "skill 4 used");
                checkDeadMons();
                disableSkills();
                turnNumber++;
                btnNormAtk.setText("Enemy move!");
                break;
            case R.id.btnNormAtk:
                if(turnNumber % 2 == 1){ //odd
                    // if condition checking
                    if(sleepcounter>0){
                        combat_log.setText("Charizard is still asleep for "+String.valueOf(sleepcounter)+ "turns!");
                        sleepcounter--;
                        turnNumber++;
                        Charizard.setMana(Charizard.getMana() + 1);
                        hero_mana.setText(String.valueOf(Charizard.getMana()));
                        enemyturn();
                        disableSkills();
                        break;
                    }
                    Arceus.setHp(Arceus.getHp()-Charizard.getDPS(Charizard));
                    turnNumber++;;
                    Charizard.setMana(Charizard.getMana() + 1);
                    Log.d(TAG, "player attacked");
                    if (Charizard.getMana()> Hero.getBase_mana() * Charizard.getLvl()){
                        Charizard.setMana( Hero.getBase_mana() * Charizard.getLvl() );
                    }
                    mons_hp.setText(String.valueOf(Arceus.getHp()));
                    hero_mana.setText(String.valueOf(Charizard.getMana()));
                    enemyturn();
                    combat_log.setText("Our Hero Charizard dealt "+Charizard.getPrevDmg() + " damage to the enemy!");
                    checkDeadMons();
                    disableSkills();
                }
                else if(turnNumber%2 != 1){ //Mons

                    if(stuncounter>0){
                        combat_log.setText("The enemy is still stunned for "+stuncounter+ "turns!");
                        stuncounter--;
                        turnNumber++;
                        heroturn();
                    }
                    else if(burncounter>0){
                        Arceus.setHp(burn(Arceus.getHp(), burn1));
                        mons_hp.setText(String.valueOf(Arceus.getHp()));
                        combat_log.setText("The enemy is has been burned for\r"+burncounter+"\rturns!");
                        burncounter--;
                        turnNumber++;
                        heroturn();
                        checkDeadMons();
                    }
                    else{
                        Charizard.setHp(Charizard.getHp() - Arceus.getDPS(Arceus));
                        turnNumber++;
                        hero_hp.setText(String.valueOf(Charizard.getHp()));
                        Log.d(TAG, "Arceus attacked");
                        heroturn();
                        combat_log.setText("The Arceus dealt "+String.valueOf(Arceus.getPrevDmg())+ " damage to the enemy!");
                        checkDeadHero();
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
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );

    }
    private void setTextReset(){
        mons_name.setText(R.string.mons_name);
        mons_hp.setText(String.valueOf(Arceus.getHp()));
        mons_mana.setText(String.valueOf(Arceus.getHp()));
        hero_name.setText(R.string.hero_name);
        hero_hp.setText(String.valueOf(Charizard.getHp()));
        hero_mana.setText(String.valueOf(Charizard.getMana()));
        mons_lvl.setText((mons_level + monslvl));
        hero_lvl.setText((mons_level + Charizard.getLvl()));
    }
    private void resetSkillsCD(){
        cd1=0;
        cd2=0;
        cd3=0;
        cd4=0;
    }
    private void checkDeadHero(){
        if(Charizard.getHp()<= 0) { //checks if hero hp below 0
            combat_log.setText("The enemy Arceus dealt " + String.valueOf(Arceus.getPrevDmg()) + " damage to the enemy. You lose!");
            resetSkillsCD();
            Charizard.setxpcounter(Charizard.getBase_xpcounter());
            btnNormAtk.setText("Reset Game!");
            monslvl = 1;
            Charizard.setLvl((byte) 1);
            combatRelated.getmonsterstatRandomizer(monslvl, Arceus);
            combatRelated.getherolevelRandomizer(Charizard);
            setTextReset();
        }
    }
    private void checkDeadMons(){
        if(Arceus.getHp()<= 0){ //checks if mons hp below 0
            combat_log.setText("Our Hero Charizard dealt "+String.valueOf(Charizard.getPrevDmg()) + " damage to the enemy. The player is victorious!");
            monslvl++;
            Charizard.setxpcounter((byte) (Charizard.getxpcounter() + 1));
            if(Charizard.getxpcounter()==Charizard.getLvl()){
                Charizard.setLvl((byte) (Charizard.getLvl() +1));
                combatRelated.getherolevelRandomizer(Charizard);
                Charizard.setxpcounter((byte)0);
            }
            test1.getmonsterstatRandomizer(monslvl, Arceus);
            setTextReset();
            turnNumber= 0;
            resetSkillsCD();
            btnNormAtk.setText("Next Encounter!");
        }
    }
    private void disableSkills(){
        skill1.setEnabled(false);
        skill2.setEnabled(false);
        skill3.setEnabled(false);
        skill4.setEnabled(false);
    }
    private void enemyturn(){
        btnNormAtk.setText("Enemy move!");
        btnNormAtk.setBackgroundColor(getResources().getColor(R.color.hpdefault));
        btnNormAtk.setTextColor(getResources().getColor(R.color.white));
    }
    private void heroturn(){
        btnNormAtk.setText("Your move!");
        btnNormAtk.setTextColor(getResources().getColor(R.color.black));
        btnNormAtk.setBackgroundColor(getResources().getColor(R.color.player_turn));
    }
}