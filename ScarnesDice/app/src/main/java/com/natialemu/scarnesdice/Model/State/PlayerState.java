package com.natialemu.scarnesdice.Model.State;

import com.natialemu.scarnesdice.Model.ScarnesGame;

/**
 * Created by Nathnael on 6/26/2017.
 */

public class PlayerState implements ScarnesGameState {

    private ScarnesGame game;

    public static int getPlayerScore() {
        return playerScore;
    }

    public static int getPlayerCurrentScore() {
        return playerCurrentScore;
    }

    private static int playerScore = 0;
    private static int playerCurrentScore = 0;
    private static boolean doubles = false;

    public PlayerState(ScarnesGame game) {
        this.game = game;
    }

    @Override
    public void roll(int dice1, int dice2) {
        doubles = false;
        updateDice(dice1, dice2);
        if (dice1 == 1 && dice2 == 1) {
            playerScore = 0;
            playerCurrentScore = 0;
            updatePlayerInfo();
            game.toComputerTurn();

        } else if (dice1 == 1 || dice2 == 1) {
            updateScore();
            playerCurrentScore = 0;
            updatePlayerInfo();
            game.toComputerTurn();

        } else if (dice1 == dice2) {
            doubles = true;
            notifyPlayer("Player must roll again");
            playerScore += (dice1 + dice2);
            playerCurrentScore += (dice1 + dice2);
            updateScore();
        } else {
            playerScore += (dice1 + dice2);
            playerCurrentScore += (dice1 + dice2);
            updateScore();
        }

    }

    private void notifyPlayer(String s) {
        game.notificationTextViewUi(s);
    }


    @Override
    public boolean hold(int dice1, int dice2) {
        if (playerCurrentScore != 0 && !doubles) {
            updatePlayerInfo();
            playerCurrentScore = 0;
            game.toComputerTurn();
            return true;
        } else if (playerCurrentScore == 0) {
            notifyPlayer("You must Roll first");
            return false;
        } else if (doubles) {
            notifyPlayer("You must Roll again since you rolled a double");
            return false;
        }
        return false;
    }

    @Override
    public void reset() {
        //TODO: reset does not behave differently for different states
        updateDice(1, 1);
        playerCurrentScore = 0;
        playerScore = 0;
        updateScore();
        notifyPlayer("");
        game.init();
    }

    @Override
    public void updateDice(int dice1, int dice2) {
        game.getUiListner().updateDiceUi(dice1, dice2);
    }

    @Override
    public void updateScore() {
        game.getUiListner().updateScoreUi(playerScore, "player");
    }

    @Override
    public void updatePlayerInfo() {
        game.getUiListner().updatePlayerUi("Computer");
    }
}
