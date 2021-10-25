import event.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class App {

    public static String TOKEN = "";

    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault(TOKEN).build();
        jda.addEventListener(new CommandListener());
        jda.awaitReady();
    }
}