package com.natialemu.scarnesdice.Model;

import com.natialemu.scarnesdice.Model.State.ComputerState;
import com.natialemu.scarnesdice.Model.State.PlayerState;
import com.natialemu.scarnesdice.Model.State.ScarnesGameState;

/**
 * Created by Nathnael on 6/24/2017.
 */

public class ScarnesGameImpl implements ScarnesGame{
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

    //int dice1 = 1;
    //int dice2 = 1;


    public ScarnesGameImpl(){
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
    public void toPlayersTurn() {
        setGameState(PLAYER_STATE);
    }

    @Override
    public void toComputerTurn() {
        setGameState(COMPUTER_STATE);

    }

    @Override
    public void updateDiceUi(int dice1, int dice2) {
        currentState.updateDice(dice1,dice2);

    }

    @Override
    public void updateScoreUi() {
        currentState.updateScore();

    }

    @Override
    public void updatePlayerInfoUi() {
        currentState.updatePlayerInfo();

    }

    @Override
    public void notificationTextViewUi(String s) {
        uiListner.updateTextViewUi(s);

    }

    @Override
    public void initUi() {
        uiListner.updatePlayerUi("Player");
        uiListner.updateTextViewUi("");
        uiListner.updateDiceUi(1,1);
        uiListner.updateScoreUi(0,"computer");
        uiListner.updateScoreUi(0,"player");
    }


    @Override
    public void roll(int dice1, int dice2) {
        currentState.roll(dice1,dice2);
        //uiListner.updateDiceUi(dice1,dice2);
        /*if(dice1 == 1 && dice2 == 1){
            if(getPlayer().getName().equals("Player 1")){
                this.setPlayer(computer);
                uiListner.updatePlayerUi(computer.getPoints(),computer.getName());
            }else{
                setPlayer(player);
                uiListner.updatePlayerUi(player.getPoints(),player.getName());
            }


            //this.setPlayer(computer);

        }else{
            if(getPlayer().getName().equals("Player 1")){
                player.setPoints(player.getPoints()+dice1+dice2);
            }else{
                computer.setPoints(computer.getPoints()+dice1+dice2);
            }
        }*/

    }

    @Override
    public void init() {
        setGameState(PLAYER_STATE);
        /*player.setPoints(0);
        computer.setPoints(0);

        uiListner.updateDiceUi(1,1);
        uiListner.updatePlayerUi(0, "Computer");
        uiListner.updatePlayerUi(0,"Player 1");*/



    }

    @Override
    public void hold(int dice1, int dice2) {
        currentState.hold(dice1,dice2);
        /*if(getPlayer().getName().equals("Player 1")){
            uiListner.updatePlayerUi(player.getPoints(),player.getName());
            setPlayer(computer);
            uiListner.updatePlayerUi(computer.getPoints(),computer.getName());
        }else{
            uiListner.updatePlayerUi(computer.getPoints(),computer.getName());

            setPlayer(player);
            uiListner.updatePlayerUi(player.getPoints(),player.getName());
        }
        uiListner.updateDiceUi(dice1,dice2);*/


    }

    @Override
    public void reset() {
        currentState.reset();
        /*player.setPoints(0);
        computer.setPoints(0);

        uiListner.updateDiceUi(1,1);*/


    }

}
