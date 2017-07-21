package com.natialemu.scarnesdice.View;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.natialemu.scarnesdice.Model.ScarnesGame;
import com.natialemu.scarnesdice.Model.ScarnesGameImpl;
import com.natialemu.scarnesdice.Model.UiUpdateListner;
import com.natialemu.scarnesdice.R;

public class ScarnesActivity extends AppCompatActivity implements UiUpdateListner{
    ScarnesGame game;
    int dice1 = 1;
    int dice2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarnes);
        game = new ScarnesGameImpl();
        game.setuiListner(this);
        game.initUi();
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
        TextView view = (TextView)findViewById(R.id.notification);

        view.setText(message);

        return true;

    }

    public void onRollClick(View view) {
        //get random rolls
        //pass the random rolls to rol
        dice1 = (int)(Math.random()*6) + 1;
        dice2 = (int)(Math.random()*6) + 1;
        game.roll(dice1,dice2);


    }

    public void onHoldClick(View view) {
        game.hold(dice1,dice2);
    }

    public void onResetClick(View view) {
        game.reset();

    }
}
