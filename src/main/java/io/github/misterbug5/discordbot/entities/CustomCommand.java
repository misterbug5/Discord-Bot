package io.github.misterbug5.discordbot.entities;

public class CustomCommand extends Command {
    private String desc;
    private String def;
    private boolean DM;
    private boolean silent;
    private boolean everyone;

    public CustomCommand(){
        this.desc = "";
        this.def = "";
        this.DM = false;
        this.silent = false;
        this.everyone = false;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public boolean getDM() {
        return DM;
    }

    public void setDM(boolean dM) {
        DM = dM;
    }

    public boolean getSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public boolean getEveryone() {
        return everyone;
    }

    public void setEveryone(boolean everyone) {
        this.everyone = everyone;
    }
}
