package com.natialemu.scarnesdice.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.natialemu.scarnesdice.Model.UiUpdateListner;
import com.natialemu.scarnesdice.R;

public class ScarnesActivity extends AppCompatActivity implements UiUpdateListner{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarnes);
    }

    @Override
    public void updatePlayerUi(int points, String playerName) {

    }

    @Override
    public void updateDiceUi(int dice1, int dice2) {

    }

    public void onRollClick(View view) {
    }

    public void onHoldClick(View view) {
    }

    public void onResetClick(View view) {

    }
}
