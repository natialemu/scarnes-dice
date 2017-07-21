package com.natialemu.scarnesdice.Model.State;

import com.natialemu.scarnesdice.Model.ScarnesGame;
import com.natialemu.scarnesdice.Model.ScarnesGameImpl;
import com.natialemu.scarnesdice.Model.UiUpdateListner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Nathnael on 7/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComputerStateTest {


    @Mock
    UiUpdateListner uiUpdateListner;



    @InjectMocks
    private ScarnesGameImpl scarnesGame;

    private ComputerState computerState;





    //mock the ui listener and set it in the scarne's game

    //whenever the notify thing is called then do something

    //this is very sad

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        scarnesGame = new ScarnesGameImpl();
        scarnesGame.setuiListner(uiUpdateListner);
        computerState = new ComputerState(scarnesGame);

        when(uiUpdateListner.updateDiceUi(anyInt(),anyInt())).thenAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable{
                return null;
            }
        });
        when(uiUpdateListner.updatePlayerUi(anyString())).thenAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable{
                return null;
            }
        });

        when(uiUpdateListner.updateScoreUi(anyInt(), anyString())).thenAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable{
                return null;
            }
        });

        when(uiUpdateListner.updateTextViewUi(anyString())).thenAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable{
                return null;
            }
        });

    }
    @Test
    public void roll() throws Exception {



        //initial rolls
        int testFirstDice = 2;
        int testSecondDice = 4;

        computerState.roll(testFirstDice,testSecondDice);//update dice makes use of the
        assertEquals (ComputerState.getPlayerScore(),6);
        assertEquals(ComputerState.getPlayerCurrentScore(),6);

        //tests if both are 1
        int testDice1 = 1;
        int testDice2 = 1;
        computerState.roll(testDice1,testDice2);

        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());
        assertEquals (ComputerState.getPlayerScore(),0);
        assertEquals(ComputerState.getPlayerCurrentScore(),0);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());

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