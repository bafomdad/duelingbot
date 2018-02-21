package com.bafomdad.duelingbot.cards;

import com.bafomdad.duelingbot.api.ICard;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bafomdad on 12/28/2017.
 */
public abstract class ACard implements ICard {

    @SerializedName("name")
    protected String cardName;
    @SerializedName("description")
    protected String cardDescription;
    @SerializedName("script")
    private List<String> script;
    @SerializedName("id")
    private String cardId;
    @SerializedName("property")
    protected String cardProperty;

    public ACard() {}

    public String getCardName() {

        if (cardName == null)
            return "John Doe";

        return cardName;
    }

    public void setCardName(String name) {

        this.cardName = name;
    }

    public String getCardId() {

        return cardId;
    }

    public void setCardId(String id) {

        this.cardId = id;
    }

    public String getCardDescription() {

        if (cardDescription == null)
            return "Descriptive text here";

        return cardDescription;
    }

    public void setCardDescription(String desc) {

        this.cardDescription = desc;
    }

    public List<String> getScript() {

        return script;
    }

    public int getCardLimit() {

        return 3;
    }
}
