package bot.command;

public enum StaticCommands {
    _NULL(""),
    BUG("bug"),
    DOWNLOAD("download"),
    GAY("gay"),
    HELP("help"),
    ISSUE("issue"),
    KEKW("kekw"),
    NPE("npe"),
    WIKI("wiki");

    private final String command;

    StaticCommands(String command) {
        this.command = "!" + command;
    }

    public String getCommand() {
        return command;
    }

    public static StaticCommands commandParser(String msg) {
        for (StaticCommands a : StaticCommands.values()) {
            if (a.getCommand().equals(msg)) {
                return a;
            }
        }
        return _NULL;
    }
}