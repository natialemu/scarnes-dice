package com.natialemu.scarnesdice.Model;

/**
 * Created by Nathnael on 6/24/2017.
 */

public interface ScarnesGame {
    void setuiListner(UiUpdateListner uiListner);
    void setPlayer(Player player);
    void roll(int dice1, int dice2);
    void init();
    void hold(int dice1, int dice2);
    void reset();
}
