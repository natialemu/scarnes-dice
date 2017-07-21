package com.natialemu.scarnesdice.Model;

import com.natialemu.scarnesdice.Model.State.ComputerState;
import com.natialemu.scarnesdice.Model.State.PlayerState;
import com.natialemu.scarnesdice.Model.State.ScarnesGameState;

/**
 * Created by Nathnael on 6/24/2017.
 */

public class ScarnesGameImpl implements ScarnesGame {

    private ScarnesGameState currentState;

    public ScarnesGameState getCurrentState() {
        return currentState;
    }

    public ScarnesGameState getPLAYER_STATE() {
        return PLAYER_STATE;
    }

    public ScarnesGameState getCOMPUTER_STATE() {
        return COMPUTER_STATE;
    }

    private ScarnesGameState PLAYER_STATE = new PlayerState(this);
    private ScarnesGameState COMPUTER_STATE = new ComputerState(this);

    private UiUpdateListner uiListner;


    public ScarnesGameImpl() {
        init();
    }


    @Override
    public void setuiListner(UiUpdateListner uiListner) {
        this.uiListner = uiListner;
    }


    @Override
    public UiUpdateListner getUiListner() {
        return uiListner;
    }


    @Override
    public void setGameState(ScarnesGameState state) {
        this.currentState = state;
    }

    @Override
    public Void toPlayersTurn() {
        setGameState(PLAYER_STATE);
        return null;
    }

    @Override
    public Void toComputerTurn() {
        setGameState(COMPUTER_STATE);
        return null;
    }

    @Override
    public Void updateDiceUi(int dice1, int dice2) {
        currentState.updateDice(dice1, dice2);
        return null;
    }

    @Override
    public Void updateScoreUi() {
        currentState.updateScore();
        return null;
    }

    @Override
    public Void updatePlayerInfoUi() {
        currentState.updatePlayerInfo();
        return null;
    }

    @Override
    public Void notificationTextViewUi(String s) {
        uiListner.updateTextViewUi(s);
        return null;
    }

    @Override
    public Void initUi() {
        uiListner.updatePlayerUi("Player");
        uiListner.updateTextViewUi("");
        uiListner.updateDiceUi(1, 1);
        uiListner.updateScoreUi(0, "computer");
        uiListner.updateScoreUi(0, "player");
        return null;
    }


    @Override
    public void roll(int dice1, int dice2) {
        currentState.roll(dice1, dice2);
    }

    @Override
    public void init() {
        setGameState(PLAYER_STATE);
    }

    @Override
    public void hold(int dice1, int dice2) {
        currentState.hold(dice1, dice2);
    }

    @Override
    public void reset() {
        getCOMPUTER_STATE().reset();
        getPLAYER_STATE().reset();
    }

}
