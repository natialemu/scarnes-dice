package com.natialemu.scarnesdice.View;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.natialemu.scarnesdice.Model.ScarnesGame;
import com.natialemu.scarnesdice.Model.ScarnesGameImpl;
import com.natialemu.scarnesdice.Model.UiUpdateListner;
import com.natialemu.scarnesdice.R;

public class ScarnesActivity extends AppCompatActivity implements UiUpdateListner{
    ScarnesGame game;
    int dice1 = 1;
    int dice2 = 1;
    private LinearLayout layoutFabRoll;

    //Linear layout holding the Edit submenu
    private LinearLayout layoutFabHold;

    private LinearLayout layoutFabReset;
    private boolean fabExpanded = false;

    private FloatingActionButton fabMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarnes);
        Toolbar gameToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(gameToolbar);

        fabMain = (FloatingActionButton) findViewById(R.id.mainFab);

        layoutFabRoll = (LinearLayout) findViewById(R.id.layoutFabRoll);
        layoutFabHold  =(LinearLayout) findViewById(R.id.layoutFabHold);
        layoutFabReset = (LinearLayout) findViewById(R.id.layoutFabReset);

        fabMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(fabExpanded){
                    closeSubMenusFab();
                }else{
                    openSubMenusFab();
                }

            }
        });


        FloatingActionButton rollFab = (FloatingActionButton)findViewById(R.id.fabRoll);

        FloatingActionButton holdFab = (FloatingActionButton)findViewById(R.id.fabHold);

        FloatingActionButton resetFab = (FloatingActionButton)findViewById(R.id.fabReset);

        rollFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded){
                    onRollClick(view);
                }
            }
        });
        holdFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded){
                    onHoldClick(view);
                }
            }
        });

        resetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded){
                    onResetClick(view);
                }
            }


        });

        closeSubMenusFab();

        game = new ScarnesGameImpl();
        game.setuiListner(this);
        game.initUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.game_help){
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restore game state such as player and UI info

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the game instances such as player and UI info
    }



    @Override
    public boolean updatePlayerUi(String playerName) {
        TextView turnNotifier = (TextView)findViewById(R.id.turnNotifier);
        String notifierText = playerName + "'s turn";
        turnNotifier.setText(notifierText);
        return true;

    }

    @Override
    public boolean updateDiceUi(int dice1, int dice2) {
        ImageView firstDice = (ImageView) findViewById(R.id.firstDice);
        ImageView secondDice = (ImageView)findViewById(R.id.secondDice);

       if(dice1 == 1){
            firstDice.setImageResource(R.drawable.dice1);
        }else if(dice1 == 2){
            firstDice.setImageResource(R.drawable.dice2);
        }else if(dice1 == 3){
            firstDice.setImageResource(R.drawable.dice3);
        }else if(dice1 == 4){
            firstDice.setImageResource(R.drawable.dice4);
        }else if(dice1 == 5){
            firstDice.setImageResource(R.drawable.dice5);
        }else if(dice1 == 6){
            firstDice.setImageResource(R.drawable.dice6);
        }


        if(dice2 == 1){
            secondDice.setImageResource(R.drawable.dice1);
        }else if(dice2 == 2){
            secondDice.setImageResource(R.drawable.dice2);
        }else if(dice2 == 3){
            secondDice.setImageResource(R.drawable.dice3);
        }else if(dice2 == 4){
            secondDice.setImageResource(R.drawable.dice4);
        }else if(dice2 == 5){
            secondDice.setImageResource(R.drawable.dice5);
        }else if(dice2 == 6){
            secondDice.setImageResource(R.drawable.dice6);
        }

        return true;

    }

    @Override
    public boolean updateScoreUi(int score, String tag) {
        TextView view;
        String currentScore = new Integer(score).toString();
        if(tag.equals("player")){
            view = (TextView)findViewById(R.id.player1Score);

        } else {
            view = (TextView)findViewById(R.id.computerScore);

        }
        view.setText(currentScore);
        return true;

    }

    @Override
    public boolean updateTextViewUi(String message) {

        if (message.equals("")){
            return true;
        }
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

        return true;

    }

    public void onRollClick(View view) {

        dice1 = (int)(Math.random()*6) + 1;
        dice2 = (int)(Math.random()*6) + 1;
        game.roll(dice1,dice2);
        closeSubMenusFab();

    }

    public void onHoldClick(View view) {
        game.hold(dice1,dice2);
        closeSubMenusFab();
    }

    public void onResetClick(View view) {

        game.reset();
        closeSubMenusFab();

    }

    private void closeSubMenusFab(){
        layoutFabHold.setVisibility(View.INVISIBLE);
        layoutFabReset.setVisibility(View.INVISIBLE);
        layoutFabRoll.setVisibility(View.INVISIBLE);
        fabMain.setBackgroundResource(R.drawable.ic_add_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab() {
        layoutFabHold.setVisibility(View.VISIBLE);
        layoutFabRoll.setVisibility(View.VISIBLE);
        layoutFabReset.setVisibility(View.VISIBLE);
        fabMain.setBackgroundResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
    }
}
