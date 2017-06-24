package com.natialemu.scarnesdice.Model;

/**
 * Created by Nathnael on 6/24/2017.
 */

public interface UiUpdateListner {
    void updatePlayerUi(int points, String playerName);
    void updateDiceUi(int dice1, int dice2);
}
