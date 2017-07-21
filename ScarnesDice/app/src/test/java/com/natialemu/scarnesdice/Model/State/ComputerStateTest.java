package com.natialemu.scarnesdice.Model.State;

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
import static org.mockito.Mockito.when;

/**
 * Created by Nathnael on 7/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComputerStateTest {

    @Mock
    private UiUpdateListner uiUpdateListner;

    @InjectMocks
    private ScarnesGameImpl scarnesGame;
    private ComputerState computerState;

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


//-------------------------------------------------------------------------------------------

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        computerState.roll(testFirstDice,testSecondDice);//update dice makes use of the
        assertEquals (ComputerState.getPlayerScore(),6);
        assertEquals(ComputerState.getPlayerCurrentScore(),6);

//-------------------------------------------------------------------------------------------


        int testDice1 = 1;
        int testDice2 = 1;

        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());
        computerState.roll(testDice1,testDice2);

        assertEquals (ComputerState.getPlayerScore(),0);
        assertEquals(ComputerState.getPlayerCurrentScore(),0);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());


//-------------------------------------------------------------------------------------------

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        computerState.roll(testFirstDice,testSecondDice);
        assertEquals (ComputerState.getPlayerScore(),6);
        assertEquals(ComputerState.getPlayerCurrentScore(),6);

//-------------------------------------------------------------------------------------------


        testDice2 = 3;
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());

        computerState.roll(testDice1,testDice2);


        assertEquals(ComputerState.getPlayerScore(),testFirstDice+testSecondDice);
        assertEquals(ComputerState.getPlayerCurrentScore(),0);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());


//-------------------------------------------------------------------------------------------

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        computerState.roll(testFirstDice,testSecondDice);
        assertEquals (ComputerState.getPlayerScore(),12);
        assertEquals(ComputerState.getPlayerCurrentScore(),6);

//-------------------------------------------------------------------------------------------


        testDice1 = 3;
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());
        computerState.roll(testDice1,testDice2);

        assertEquals(ComputerState.getPlayerCurrentScore(), 12 );
        assertEquals(ComputerState.getPlayerScore(),18);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());


    }

    //a computer is allowed to hold only if a double is not rolled
    //a  computer will not get the chance to hold if a 1 is rolled
    // if none of the above happen, holding will preserve the computer's state and then change state to player
    @Test
    public void hold() throws Exception {

        int testDice1 = 1;
        int testDice2 = 1;

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertFalse(computerState.hold(testDice1,testDice2));

        testDice2 = 3;

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertFalse(computerState.hold(testDice1,testDice2));

        testDice1 = 3;

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertFalse(computerState.hold(testDice1,testDice2));
        scarnesGame.roll(1,1);

        testDice1 = 4;

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());
        assert(computerState.hold(testDice1,testDice2));
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());
        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        scarnesGame.roll(1,1);

    }

    @Test
    public void reset() throws Exception {

        final int testFirstDice = 2;
        final int testSecondDice = 4;

        scarnesGame.setGameState(scarnesGame.getCOMPUTER_STATE());
        computerState.roll(testFirstDice,testSecondDice);
        computerState.reset();
        assertEquals(ComputerState.getPlayerScore(),0);
        assertEquals(ComputerState.getPlayerCurrentScore(),0);

    }

}