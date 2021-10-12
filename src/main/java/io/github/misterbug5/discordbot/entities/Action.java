package io.github.misterbug5.discordbot.entities;

import java.util.Arrays;
import java.util.List;

import io.github.misterbug5.discordbot.listeners.commands.Actions;
import io.github.misterbug5.discordbot.listeners.commands.Arguments;

public class Action {
    private Actions action;
    private List<Arguments> args;

    public Action() {
    }

    public Action(Actions action, Arguments... args) {
        this.action = action;
        this.args = Arrays.asList(args);
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public List<Arguments> getArgs() {
        return args;
    }

    public void setArgs(List<Arguments> args) {
        this.args = args;
    }
}
