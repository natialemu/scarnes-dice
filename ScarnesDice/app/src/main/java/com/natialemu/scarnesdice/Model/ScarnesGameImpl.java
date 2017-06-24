package com.natialemu.scarnesdice.Model;

/**
 * Created by Nathnael on 6/24/2017.
 */

public class ScarnesGameImpl implements ScarnesGame{
    UiUpdateListner uiListner;
    Player player;


    public void setuIListner(UiUpdateListner uiListner){
        this.uiListner = uiListner;
    }

    @Override
    public void setuiListner(UiUpdateListner uiListner) {

    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public void roll(int dice1, int dice2) {

    }

    @Override
    public void init() {

    }

    @Override
    public void hold(int dice1, int dice2) {

    }

    @Override
    public void reset() {

    }

}
