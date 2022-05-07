package bot.event;

import bot.cfg.Config;
import bot.command.StaticCommands;
import bot.html.HtmlBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.*;
import java.awt.*;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;

        String[] message = e.getMessage().getContentRaw().split("\n");
        switch (StaticCommands.commandParser(message[0])) {
            case _NULL:
                return;
            case GAY: {
                e.getChannel().sendMessageFormat("You " + e.getAuthor().getAsMention() + "!").queue();
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
                        .addField("!issue", "create github issue", false)
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
				e.getChannel().sendMessageEmbeds(em.build()).queue();
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
				e.getChannel().sendMessageEmbeds(em.build()).queue();
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
				e.getChannel().sendMessageEmbeds(em.build()).queue();
                break;
            }
            case KEKW: {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("KEKW")
                        .setColor(new Color(51, 152, 189))
                        .setImage("https://c.tenor.com/ASGuOCPGrKEAAAAC/kekw-kek.gif")
                        .setFooter("IMPACT BOT")
                        .setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
				e.getChannel().sendMessageEmbeds(em.build()).queue();
                break;
            }
            case NPE: {
                Message ref = e.getMessage().getReferencedMessage();
                if (ref != null) {
					EmbedBuilder em = new EmbedBuilder()
							.setTitle("NullPointerException(СЛОЖНА)")
							.setColor(Color.red)
							.setImage("https://cdn.discordapp.com/attachments/757630396535013387/972324544574545920/unknown.png")
							.setFooter("IMPACT BOT")
							.setThumbnail("https://cdn.discordapp.com/attachments/672463927476748289/838544798435770408/2123.png");
                    ref.replyEmbeds(em.build()).queue();
                }
                break;
            }
			case BUG:
			case ISSUE: {
				if (e.getMember() == null) return;
		
				if (message.length <= 2) {
					EmbedBuilder em = new EmbedBuilder()
							.setTitle("ISSUE TEMPLATE")
							.setColor(new Color(51, 152, 189))
							.setDescription(
									"!issue <bug, recipes, suggestion, question>\n" +
											"<TITLE>\n" +
											"<BODY>"
							)
							.setFooter("IMPACT BOT");
					e.getChannel().sendMessageEmbeds(em.build()).queue();
					return;
				}
		
				HtmlBuilder htmlBuilder = new HtmlBuilder();
				for (int i = 2; i < message.length; i++) {
					String str = message[i];
			
					if (str.contains("http") || str.startsWith("http")) {
						String[] splitForHref = str.split(" ");
						for (String s : splitForHref) {
							if ((s.startsWith("https://") || s.startsWith("http://")) &&
									(s.endsWith(".png") || s.endsWith(".jpg") || s.endsWith(".jpeg") || s.endsWith(".gif"))) {
								htmlBuilder.addImg(s).addBR();
							} else {
								if (s.length() > 0) {
									htmlBuilder.addText(s).addText(" ");
								}
							}
						}
					} else {
						htmlBuilder.addParagraph(str).addBR();
					}
				}
		
				String[] s1 = message[0].split(" ");
				String label = "";
				for (String s : s1) {
					if (s.startsWith("bug")) label = "bug";
					else if (s.startsWith("suggestion")) label = "suggestion";
					else if (s.startsWith("question")) label = "question";
					else if (s.startsWith("recipes")) label = "recipes";
				}
		
				if (label.isEmpty()) {
					EmbedBuilder em = new EmbedBuilder()
							.setTitle("ISSUE TEMPLATE")
							.setColor(new Color(51, 152, 189))
							.setDescription(
									"!issue <bug, recipes, suggestion, question>\n" +
											"<TITLE>\n" +
											"<BODY>"
							)
							.setFooter("IMPACT BOT");
					e.getChannel().sendMessageEmbeds(em.build()).queue();
					return;
				}
		
				String title = "[" + e.getAuthor().getName() + "] " + message[1];
				title = title.replaceAll("\"", "'");
		
				String bodyHtml = htmlBuilder.build();
				bodyHtml = bodyHtml.replaceAll("\"", "'");
		
				String post =
						"{" +
								"\"title\":\"" + title + "\"," +
								"\"body\":\"" + bodyHtml + "\"," +
								"\"labels\":[\"" + label + "\"]" +
								"}";
				try {
					OkHttpClient client = new OkHttpClient().newBuilder().build();
					MediaType mediaType = MediaType.parse("application/json");
					RequestBody body = RequestBody.create(mediaType, post);
					Request request = new Request.Builder()
							.url("https://api.github.com/repos/GT-IMPACT/IMPACT-MODPACK/issues")
							.method("POST", body)
							.addHeader("Authorization", "token " + Config.GITTOKEN)
							.addHeader("Accept", "application/vnd.github.v3+json")
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();
			
					if (response.code() == 201) {
						if (response.body() != null) {
							String jsonGET = response.body().string();
							JsonObject jsonObject = JsonParser.parseString(jsonGET).getAsJsonObject();
							String uri = jsonObject.get("html_url").getAsString();
							e.getChannel().sendMessage(uri).queue();
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				break;
			}
        }
    }
}