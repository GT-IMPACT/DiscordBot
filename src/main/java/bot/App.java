package bot;

import bot.cfg.Config;
import bot.event.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class App {
	public static void main(String[] args) throws Exception {
		Config.startConfig();
		JDA jda = JDABuilder.createDefault(Config.TOKEN).build();
		System.out.println("========== BOT STARTED ==========");
		System.out.println("DISCORD_TOKEN: " + Config.TOKEN.substring(0, 10) + "***");
		System.out.println("GIT_TOKEN: " + Config.GITTOKEN.substring(0, 10) + "***");
		System.out.println("=================================");
		jda.addEventListener(new CommandListener());
		jda.awaitReady();
	}
}