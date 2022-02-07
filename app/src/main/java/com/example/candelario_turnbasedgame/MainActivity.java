package com.example.candelario_turnbasedgame;

import static com.example.candelario_turnbasedgame.R.id.btnNormAtk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mons_name,mons_hp,mons_lvl,mons_mana,hero_name,hero_hp,hero_lvl,hero_mana;
    Button btnNormAtk;
    ImageButton skill1,skill2,skill3,skill4;

    // Global Variables for systems
    short turnNumber = 0;
    byte stuncounter = 0;
    byte cd1 = 0;
    byte cd2 = 0;
    byte cd3 = 0;
    byte cd4 = 0;
    boolean disabledstatus = false;

    // Monster Stats
    double mons_hp_value = 20;
    float mons_mana_value = 10;
    float monsterMinDamage = 3;
    float monsterMaxDamage = 5;

    // Hero Stats
    double hero_hp_value = 50;
    float hero_mana_value = 10;
    float heroMaxDamage =5;
    float heroMinDamage =10;

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
        hero_name.setText(R.string.hero_name);
        hero_hp.setText(String.valueOf(hero_hp_value));
        hero_mana.setText(String.valueOf(hero_mana_value));

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
    }

    public void onClick(View v) {

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
        }
        else if(cd1==0){
            skill1.setEnabled(true);
        }
        // if condition checking for skill2 cooldown
        if(cd2>0){
            skill2.setEnabled(false);
        }
        else if(cd2==0){
            skill2.setEnabled(true);
        }
        // if condition checking for skill3 cooldown
        if(cd3>0){
            skill3.setEnabled(false);
        }
        else if(cd3==0){
            skill3.setEnabled(true);
        }
        // if condition checking for skill4 cooldown
        if(cd4>0){
            skill4.setEnabled(false);
        }
        else if(cd4==0){
            skill4.setEnabled(true);
        }

        switch (v.getId()){
            case R.id.skill1:
                break;
            case R.id.skill2:
                break;
            case R.id.skill3:
                break;
            case R.id.skill4:
                break;
            case R.id.btnNormAtk:

                break;
        }
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
}