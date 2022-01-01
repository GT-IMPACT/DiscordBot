package bot.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Config {
	
	public static String TOKEN;
	
	public Config() {
		try {
			String PATH_CFG = "config/bot.properties";
			Properties property = new Properties();
			File path = new File("config");
			if (path.mkdir()) {
				FileOutputStream fos = new FileOutputStream(PATH_CFG);
				property.setProperty("token", "");
				property.store(fos, "Property File Bot");
				fos.close();
				return;
			}
			FileInputStream fis = new FileInputStream(PATH_CFG);
			property.load(fis);
			TOKEN = property.getProperty("token");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void startConfig() {
		new Config();
	}
}