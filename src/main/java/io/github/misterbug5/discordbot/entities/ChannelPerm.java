package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

public class ChannelPerm extends Perm {
    private ArrayList<String> channels;

    public ArrayList<String> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<String> channels) {
        this.channels = channels;
    }
}
