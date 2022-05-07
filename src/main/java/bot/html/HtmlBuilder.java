package bot.html;

public class HtmlBuilder {
	
	StringBuilder stringBuilder = new StringBuilder();
	
	public HtmlBuilder addImg(String imgHref) {
		stringBuilder.append("<img src='").append(imgHref).append("' />");
		return this;
	}
	
	public HtmlBuilder addParagraph(String p) {
		stringBuilder.append("<p>").append(p).append("</p>");
		return this;
	}
	
	public HtmlBuilder addText(String p) {
		stringBuilder.append(p);
		return this;
	}
	
	public HtmlBuilder addBR() {
		stringBuilder.append("<br>");
		return this;
	}
	
	public String build() {
		return stringBuilder.toString();
	}
}
