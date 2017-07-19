package com.natialemu.scarnesdice.Model;

import com.natialemu.scarnesdice.Model.State.ScarnesGameState;

/**
 * Created by Nathnael on 6/24/2017.
 */

public interface ScarnesGame {
    void setuiListner(UiUpdateListner uiListner);


    void roll(int dice1, int dice2);

    void init();

    void hold(int dice1, int dice2);

    void reset();

    UiUpdateListner getUiListner();


    void setGameState(ScarnesGameState state);

    void toPlayersTurn();

    void toComputerTurn();

    void updateDiceUi(int dice1, int dice2);
    void updateScoreUi();
    void updatePlayerInfoUi();

    void notificationTextViewUi(String s);

    void initUi();
}
