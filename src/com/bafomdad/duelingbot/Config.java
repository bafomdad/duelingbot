package com.bafomdad.duelingbot;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bafomdad on 12/27/2017.
 */
public class Config {

    @SerializedName("token")
    private String token;
    @SerializedName("channel")
    private String channel;

    public String getToken() {

        return token;
    }

    public String getChannel() {

        return channel;
    }
}
