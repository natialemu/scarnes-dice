package com.natialemu.scarnesdice.Model.State;

import com.natialemu.scarnesdice.Model.ScarnesGame;

/**
 * Created by Nathnael on 6/26/2017.
 */

public class ComputerState implements ScarnesGameState {
    private ScarnesGame game;

    public ScarnesGame getGame() {
        return game;
    }

    public void setGame(ScarnesGame game) {
        this.game = game;
    }

    private static int playerScore = 0;

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore) {
        ComputerState.playerScore = playerScore;
    }

    public static int getPlayerCurrentScore() {
        return playerCurrentScore;
    }

    public static void setPlayerCurrentScore(int playerCurrentScore) {
        ComputerState.playerCurrentScore = playerCurrentScore;
    }

    public static boolean isDoubles() {
        return doubles;
    }

    public static void setDoubles(boolean doubles) {
        ComputerState.doubles = doubles;
    }

    private static int playerCurrentScore = 0;
    private static boolean doubles = false;

    public ComputerState(ScarnesGame game){
        this.game = game;
    }
    @Override
    public void roll(int dice1, int dice2) {
        notifyPlayer("");
        doubles = false;
        playerCurrentScore = dice1 + dice2;
        updateDice(dice1,dice2);
        if(dice1==1 && dice2 == 1){
            playerScore = 0;
            playerCurrentScore = 0;
            updatePlayerInfo();
            game.toComputerTurn();



        } else if (dice1 == 1 || dice2 == 1){
            //playerScore = 0;
            updateScore();
            playerCurrentScore = 0;
            updatePlayerInfo();
            game.toComputerTurn();



        } else if(dice1 == dice2) {
            doubles = true;
            notifyPlayer("Computer must roll again");
            playerScore += (dice1 + dice2);
            updateScore();
        }else{
            playerScore += (dice1 + dice2);
            updateScore();
        }

    }

    private void notifyPlayer(String s) {
        game.notificationTextViewUi(s);
    }



    @Override
    public void hold(int dice1, int dice2) {
        notifyPlayer("");
        if(playerCurrentScore != 0 && !doubles){
            updatePlayerInfo();
            playerCurrentScore = 0;
            game.toPlayersTurn();
        }else if(playerCurrentScore == 0){
            notifyPlayer("You must Roll first");
        }

    }

    @Override
    public void reset() {
        updateDice(1,1);
        playerCurrentScore = 0;
        playerScore = 0;
        updateScore();
        notifyPlayer("");
        game.init();

    }

    @Override
    public void updateDice(int dice1, int dice2) {
        game.getUiListner().updateDiceUi(dice1,dice2);

    }


    @Override
    public void updateScore() {
        game.getUiListner().updateScoreUi(playerScore,"computer");

    }

    @Override
    public void updatePlayerInfo() {
        game.getUiListner().updatePlayerUi("Player 1");

    }
}
