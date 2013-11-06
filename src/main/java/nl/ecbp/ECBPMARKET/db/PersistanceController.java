package nl.ecbp.ECBPMARKET.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.model.Commodity;

/**
 * idea for JSON persistance
 * from:http://www.mkyong.com/java/how-do-convert-java-
 * object-to-from-json-format-gson-api/
 * 
 * @author Eric
 * 
 */
public class PersistanceController {
	private ECBPMarket plugin;

	public PersistanceController(ECBPMarket plugin) {
		this.plugin = plugin;
		File f = new File(plugin.getDataFolder() + File.separator
				+ "Commodities");
		if (!f.exists()) {
			f.mkdir();
		}
	}

	public void Save(Commodity c) {
		Gson gson = new Gson();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		String json = gson.toJson(c);
		try {
			// write converted json data to a file named "file.json"
			FileWriter writer = new FileWriter(new File(plugin.getDataFolder()
					+ File.separator + "Commodities" + File.separator
					+ c.getName()));
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Commodity load(String name) {
		Gson gson = new Gson();
		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(
					plugin.getDataFolder() + File.separator + "Commodities"
							+ File.separator + name)));

			// convert the json string back to object
			return gson.fromJson(br, Commodity.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Commodity> loadAll() {
		List<Commodity> list = new ArrayList<Commodity>();
		File basedir = new File(plugin.getDataFolder() + File.separator
				+ "Commodities");
		for (String filename : basedir.list()) {
			list.add(load(filename));
		}
		return list;
	}

	public boolean exists(String c) {
		File f = new File(plugin.getDataFolder() + File.separator
				+ "Commodities" + File.separator + c);
		return f.exists();
	}

	public void remove(String c) {
		if (exists(c)) {
			File f = new File(plugin.getDataFolder() + File.separator
					+ "Commodities" + File.separator + c);
			f.delete();
		}
	}
}
