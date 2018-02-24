package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.ICondition;
import com.bafomdad.duelingbot.utils.MinigameUtil;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bafomdad on 2/9/2018.
 */
public class Duel {

    PlayingField[] players = new PlayingField[2];
    List<DuelCondition> duelConditions = new ArrayList();

    private boolean isDueling = false;
    private int currentPlayer = 0;

    public Duel() {}

    public boolean addPlayer(IUser player) {

        for (int i = 0; i < players.length; i++) {
            PlayingField pf = players[i];
            if (pf == null) {
                pf = new PlayingField(player);
                players[i] = pf;
                return true;
            }
        }
        return false;
    }

    public PlayingField getOpposite(IUser player) {

        for (int i = 0; i < players.length; i++) {
            PlayingField pf = players[i];
            if (pf.getOwner() == player) {
                if (i == 0)
                    return players[1];
                else if (i == 1)
                    return players[0];
            }
        }
        return null;
    }

    public boolean playerExists(IUser user) {

        for (PlayingField pf : players) {
            if (pf != null && pf.getOwner() == user)
                return pf.getOwner() == user;
        }
        return false;
    }

    public boolean canPlay() {

        return !Arrays.asList(players).contains(null);
    }

    public PlayingField getPlayingField(IUser owner) {

        for (PlayingField pf : players) {
            if (pf.getOwner() == owner)
                return pf;
        }
        return null;
    }

    public PlayingField[] getPlayers() {

        return players;
    }

    public boolean canStartDuel() {

        for (PlayingField pf : players) {
            if (!pf.getPlayerDeck().canPlay())
                return false;
        }
        return true;
    }

    public void startDuel() {

        for (PlayingField pf : players) {
            pf.getPlayerDeck().shuffle();
            for (int i = 0; i < pf.getPlayerHand().DRAW_HAND; i++) {
                pf.getPlayerHand().add(pf.getPlayerDeck().draw());
            }
        }
        isDueling = true;
        setFirstTurn();
    }

    public void stopDuel() {

        players = new PlayingField[2];
        isDueling = false;
    }

    public boolean isDuelStarted() {

        return isDueling;
    }

    public boolean isDueling(IUser user) {

        return playerExists(user) && isDueling;
    }

    public PlayingField getPlayingTurn() {

        return players[currentPlayer];
    }

    private void setFirstTurn() {

        int rollX = MinigameUtil.rollDice();
        int rollY = MinigameUtil.rollDice();

        if (rollX == rollY) {
            setFirstTurn();
            return;
        }
        boolean flag = rollX > rollY;
        this.currentPlayer = (flag) ? 1 : 0;
        getPlayingTurn().setFirstTurn();
    }

    private List<DuelCondition> getConditions() {

        return duelConditions;
    }
}
