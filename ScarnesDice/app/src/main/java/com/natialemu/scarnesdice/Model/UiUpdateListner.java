package com.natialemu.scarnesdice.Model;

/**
 * Created by Nathnael on 6/24/2017.
 */

public interface UiUpdateListner {
    void updatePlayerUi(String playerName);
    void updateDiceUi(int dice1, int dice2);
    void updateScoreUi(int score, String tag);
    void updateTextViewUi(String message);
}
