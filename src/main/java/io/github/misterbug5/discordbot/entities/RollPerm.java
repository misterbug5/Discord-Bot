package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

public class RollPerm extends Perm {
    private ArrayList<String> rolls;

    public ArrayList<String> getRolls() {
        return rolls;
    }

    public void setRolls(ArrayList<String> rolls) {
        this.rolls = rolls;
    }
    
}
