package com.natialemu.scarnesdice.Model;

/**
 * Created by Nathnael on 6/24/2017.
 */

public interface UiUpdateListner {
    boolean updatePlayerUi(String playerName);
    boolean updateDiceUi(int dice1, int dice2);
    boolean updateScoreUi(int score, String tag);
    boolean updateTextViewUi(String message);
}
