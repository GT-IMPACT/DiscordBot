package bot.event;

import bot.cfg.Config;
import bot.command.StaticCommands;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
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
						.addField("!issue", "create github issue", false)
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
			
			case ISSUE: {
				if (e.getMember() == null) return;
				boolean checkRole = false;
				
				for (Role role : e.getMember().getRoles()) {
					if (role.getName().equals("GITHUB VERIFY")) {
						checkRole = true;
					}
				}
				if (!checkRole) {
					e.getChannel().sendMessage("Need Role @GITHUB VERIFY!").queue();
					return;
				}
				
				if (message.length < 2) {
					EmbedBuilder em = new EmbedBuilder()
							.setTitle("ISSUE TEMPLATE")
							.setColor(new Color(51, 152, 189))
							.setDescription("!issue\n<TITLE>\n<BODY>")
							.setFooter("IMPACT BOT");
					e.getChannel().sendMessage(em.build()).queue();
					return;
				}
				
				StringBuilder builder = new StringBuilder();
				for (int i = 2; i < message.length; i++) {
					builder.append(message[i]).append("<br>");
				}
				
				String post = "{" + "\"title\":\"" + message[1] + "\"" + "," + "\"body\":\"" + builder.toString() + "\"" + "}";
				
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