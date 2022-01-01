package bot.event;

import bot.command.StaticCommands;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;

        String[] message = e.getMessage().getContentRaw().split(" ");
        switch (StaticCommands.commandParser(message[0])) {
            case _NULL:
                return;
            case GAY: {
                e.getChannel().sendMessageFormat("You " + e.getAuthor().getAsMention() + "!").queue();
                break;
            }
            case BUG: {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("REPORT BUG PLEASE")
                        .setDescription("GITHUB - https://github.com/GT-IMPACT/IMPACT-MODPACK/issues")
                        .setColor(new Color(51, 152, 189))
                        .setImage("https://cdn.discordapp.com/attachments/839579228281372702/839618802630918174/unknown.png")
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
                e.getChannel().sendMessage(em.build()).queue();
                break;
            }
            case HELP: {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("IMPACT BOT INFO")
                        .setColor(new Color(51, 152, 189))
                        .addField("!help", "this", false)
                        .addField("!wiki", "wiki link", false)
                        .addField("!download", "download modpack link", false)
                        .addField("!bug", "bug report", false)
                        .addField("!kekw", "kekw pic", false)
                        .addField("!gay", "secret bot.command", false)
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
                e.getChannel().sendMessage(em.build()).queue();
                break;
            }
            case WIKI: {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("IMPACT WIKI")
                        .setColor(new Color(51, 152, 189))
                        .setImage("https://media.discordapp.net/attachments/839579228281372702/839613295546859530/unknown.png")
                        .setDescription("https://gtimpact.space/wiki")
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
                e.getChannel().sendMessage(em.build()).queue();
                break;
            }
            case DOWNLOAD: {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("IMPACT DOWNLOAD")
                        .setColor(new Color(51, 152, 189))
                        .setImage("https://media.discordapp.net/attachments/838702315149197322/838702364545253406/impactTwitch.png")
                        .setDescription("https://gtimpact.space/download")
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
                e.getChannel().sendMessage(em.build()).queue();
                break;
            }
            case KEKW: {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("KEKW")
                        .setColor(new Color(51, 152, 189))
                        .setImage("https://c.tenor.com/ASGuOCPGrKEAAAAC/kekw-kek.gif")
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
                e.getChannel().sendMessage(em.build()).queue();
                break;
            }
        }
    }
}