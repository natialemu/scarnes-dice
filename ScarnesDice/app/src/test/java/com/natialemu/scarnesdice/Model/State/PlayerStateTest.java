package com.natialemu.scarnesdice.Model.State;

import com.natialemu.scarnesdice.Model.ScarnesGameImpl;
import com.natialemu.scarnesdice.Model.UiUpdateListner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class PlayerStateTest {

    @Mock
    private UiUpdateListner uiUpdateListner;

    @InjectMocks
    private ScarnesGameImpl scarnesGame;
    private PlayerState playerState;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        scarnesGame = new ScarnesGameImpl();
        scarnesGame.setuiListner(uiUpdateListner);
        playerState = new PlayerState(scarnesGame);

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
        int testFirstDice = 2;
        int testSecondDice = 4;


//-------------------------------------------------------------------------------------------

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        playerState.roll(testFirstDice,testSecondDice);//update dice makes use of the
        assertEquals (PlayerState.getPlayerScore(),6);
        assertEquals(PlayerState.getPlayerCurrentScore(),6);

//-------------------------------------------------------------------------------------------


        int testDice1 = 1;
        int testDice2 = 1;

        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());
        playerState.roll(testDice1,testDice2);

        assertEquals (PlayerState.getPlayerScore(),0);
        assertEquals(PlayerState.getPlayerCurrentScore(),0);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());


//-------------------------------------------------------------------------------------------

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        playerState.roll(testFirstDice,testSecondDice);
        assertEquals (PlayerState.getPlayerScore(),6);
        assertEquals(PlayerState.getPlayerCurrentScore(),6);

//-------------------------------------------------------------------------------------------


        testDice2 = 3;
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());

        playerState.roll(testDice1,testDice2);


        assertEquals(PlayerState.getPlayerScore(),testFirstDice+testSecondDice);
        assertEquals(PlayerState.getPlayerCurrentScore(),0);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());


//-------------------------------------------------------------------------------------------

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        playerState.roll(testFirstDice,testSecondDice);
        assertEquals (PlayerState.getPlayerScore(),12);
        assertEquals(PlayerState.getPlayerCurrentScore(),6);

//-------------------------------------------------------------------------------------------


        testDice1 = 3;
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());
        playerState.roll(testDice1,testDice2);

        assertEquals(PlayerState.getPlayerCurrentScore(), 12 );
        assertEquals(PlayerState.getPlayerScore(),18);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());



    }

    @Test
    public void hold() throws Exception {
        int testDice1 = 1;
        int testDice2 = 1;

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertFalse(playerState.hold(testDice1,testDice2));

        testDice2 = 3;

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertFalse(playerState.hold(testDice1,testDice2));

        testDice1 = 3;

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertFalse(playerState.hold(testDice1,testDice2));
        scarnesGame.roll(1,1);

        testDice1 = 4;

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        scarnesGame.roll(testDice1,testDice2);
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getPLAYER_STATE());
        assert(playerState.hold(testDice1,testDice2));
        assertEquals(scarnesGame.getCurrentState(),scarnesGame.getCOMPUTER_STATE());
        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        scarnesGame.roll(1,1);

    }

    @Test
    public void reset() throws Exception {
        final int testFirstDice = 2;
        final int testSecondDice = 4;

        scarnesGame.setGameState(scarnesGame.getPLAYER_STATE());
        playerState.roll(testFirstDice,testSecondDice);
        playerState.reset();
        assertEquals(PlayerState.getPlayerScore(),0);
        assertEquals(PlayerState.getPlayerCurrentScore(),0);

    }

}