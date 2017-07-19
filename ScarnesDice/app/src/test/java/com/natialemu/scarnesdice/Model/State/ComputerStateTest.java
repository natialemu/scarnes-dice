package com.natialemu.scarnesdice.Model.State;

import com.natialemu.scarnesdice.Model.ScarnesGame;
import com.natialemu.scarnesdice.Model.ScarnesGameImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nathnael on 7/17/2017.
 */
public class ComputerStateTest {
    private ComputerState computerState;

    private ScarnesGameImpl scarnesGame;

    //mock the ui listener and set it in the scarne's game

    //whenever the notify thing is called then do something

    //this is very sad

    @Before
    public void setUp() throws Exception {
        scarnesGame = new ScarnesGameImpl();
        computerState = new ComputerState(scarnesGame);
    }
    @Test
    public void roll() throws Exception {
        //rules regarding roling when in computer state:
        /*
        *
        * if computer rolls both 1s, computer's score becomes zero, computer's turns end
        * if computer rolls one 1, computer's score stays as is, computer's turn ends
        * if computer doesnt roll any ones, computer's score grows, computer may or may not continue
        * if computer rolls doubles, computer's score grows, computer MUST continue
         */
        int testFirstDice = 2;
        int testSecondDice = 4;

        computerState.roll(testFirstDice,testSecondDice);

        int testDice1 = 1;
        int testDice2 = 1;
        computerState.roll(testDice1,testDice2);
        assertEquals (ComputerState.getPlayerScore(),0);
        assertNotEquals(scarnesGame.getCurrentState(),computerState);

        computerState.roll(testFirstDice,testSecondDice);
        testDice2 = 3;
        computerState.roll(testDice1,testDice2);
        assertEquals(ComputerState.getPlayerScore(),testFirstDice+testSecondDice);
        assertEquals(ComputerState.getPlayerCurrentScore(),0);
        assertNotEquals(scarnesGame.getCurrentState(),computerState);


    }

    @Test
    public void hold() throws Exception {

    }

    @Test
    public void reset() throws Exception {

    }

    @Test
    public void updateDice() throws Exception {

    }

    @Test
    public void updateScore() throws Exception {

    }

    @Test
    public void updatePlayerInfo() throws Exception {

    }

}