package bot.command;

public enum StaticCommands {
    HELP("help"),
    GAY("gay"),
    BUG("bug"),
    WIKI("wiki"),
    DOWNLOAD("download"),
    KEKW("kekw"),

    _NULL("")
    ;

    private final String command;

    StaticCommands(String command) {
        this.command = "!" + command;
    }

    public String getCommand() {
        return command;
    }

    public static StaticCommands commandParser(String msg) {
        for(StaticCommands a : StaticCommands.values()) {
            if (a.getCommand().equals(msg)) {
                return a;
            }
        }
        return _NULL;
    }
}