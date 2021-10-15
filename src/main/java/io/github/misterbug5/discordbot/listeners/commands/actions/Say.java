package io.github.misterbug5.discordbot.listeners.commands.actions;

import java.util.concurrent.TimeUnit;

import io.github.misterbug5.discordbot.listeners.commands.Arguments;

public class Say extends IAction {

    private Arguments args;
    private String text;

    public Say() {
        this.text = "";
    }

    @Override
    protected void process() {
        switch (args) {
            case MESSAGE:
                this.text = String.join(" ", this.context.getArgs());
                break;

            case USER:
                this.text = this.context.getUser().getName();
                break;

            case PING:
                this.text = "Pong!";
                break;
        
            default:
                this.text = String.join(" ", this.context.getArgs());
                break;
        }
    }

    public void setArgs(Arguments... arg) {
        this.args = arg[0];
    }

    @Override
    protected void execute() {
        switch (args) {
            case PING:
                this.context.getChannel().sendMessage(text).queue(response -> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - this.context.getTime()).queue();
                });
                break;
        
            default:
                this.context.getChannel().sendMessage(text).queueAfter(1, TimeUnit.SECONDS);
                break;
        }
        
    }
    
}
