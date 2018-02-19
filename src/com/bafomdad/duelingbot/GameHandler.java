package com.bafomdad.duelingbot;

import com.bafomdad.duelingbot.internal.Deck;
import com.bafomdad.duelingbot.internal.Duel;
import com.bafomdad.duelingbot.utils.WrapperUtil;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bafomdad on 2/12/2018.
 */
public final class GameHandler {

    private Map<IUser, List<String>> deckEditors = new HashMap();
    private Duel currentDuel = new Duel();

    public Duel getCurrentDuel() {

        return currentDuel;
    }

    public List<String> getDeck(IUser user) {

        List<String> deckEdit = null;
        if (deckEditors.containsKey(user)) {
            for (Map.Entry<IUser, List<String>> entry : deckEditors.entrySet()) {
                if (entry.getKey() == user) {
                    deckEdit = entry.getValue();
                    return deckEdit;
                }
            }
        }
        deckEdit = WrapperUtil.readDeckFromFile(user.getName());
        if (deckEdit == null) {
            deckEdit = new ArrayList();
            deckEditors.put(user, deckEdit);
        }
        else {
            deckEditors.put(user, deckEdit);
        }
        return deckEdit;
    }

    public void saveDeck(IUser user, List<String> list) {

        if (deckEditors.containsKey(user)) {
            for (Map.Entry<IUser, List<String>> entry : deckEditors.entrySet()) {
                if (entry.getKey() == user) {
                    entry.setValue(list);
                    return;
                }
            }
        }
    }

    public void stopEditingAndSave(IUser user) {

        if (deckEditors.containsKey(user)) {
            for (Map.Entry<IUser, List<String>> entry : deckEditors.entrySet()) {
                if (entry.getKey() == user) {
                    WrapperUtil.writeDeckToFile(entry.getValue(), user.getName());
                    deckEditors.remove(user);
                    return;
                }
            }
        }
    }

    public Deck showDeck(IUser user) {

        if (deckEditors.containsKey(user)) {
            for (Map.Entry<IUser, List<String>> entry : deckEditors.entrySet()) {
                if (entry.getKey() == user) {
                    Deck deck = new Deck(user);
                    List<String> stringList = entry.getValue();
                    if (!stringList.isEmpty()) {
                        for (String s : stringList) {
                            WrapperUtil.add(deck, s);
                        }
                        return deck;
                    }
                }
            }
        }
        return null;
    }
}
