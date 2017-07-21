package com.natialemu.scarnesdice.Model.State;

/**
 * Created by Nathnael on 6/25/2017.
 */

public interface ScarnesGameState {
    void roll(int dice1, int dice2);
    boolean hold(int dice1, int dice2);
    void reset();
    void updateDice(int dice1, int dice2);
    void updateScore();
    void updatePlayerInfo();
}
