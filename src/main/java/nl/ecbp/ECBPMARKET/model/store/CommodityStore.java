package nl.ecbp.ECBPMARKET.model.store;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.db.PersistanceController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.DuplicateCommodityException;
import nl.ecbp.ECBPMARKET.model.Commodity;
import nl.ecbp.ECBPMARKET.views.gui.ShopGui;

public class CommodityStore {
	private PersistanceController db;
	private HashMap<String, Commodity> map;
	private ShopGui gui;

	public CommodityStore(PersistanceController db,ShopGui gui) {
		this.db = db;
		this.gui = gui;
		map = new HashMap<String, Commodity>();
		List<Commodity> list = db.loadAll();
		for (Commodity c : list) {
			map.put(c.getName(), c);
			gui.addItem(c);
		}
	}

	public Commodity getComodity(String name) throws CommodityNotFoundException {
		Commodity c;
		c = map.get(name);
		if (c == null) {
			throw new CommodityNotFoundException();
		}
		return c;
	}

	public void removeComodity(String name) throws CommodityNotFoundException {
		Commodity c;
		c = map.remove(name);
		if (c == null) {

		}
	}

	public void addComodity(Commodity ac) throws DuplicateCommodityException {
		Commodity c;
		c = map.get(ac.getName());
		if (c == null) {
			map.put(ac.getName(), ac);
		} else {
			throw new DuplicateCommodityException();
		}
	}

	public String getComodityString(int id, int data)
			throws CommodityNotFoundException {
		Iterator<Entry<String, Commodity>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Commodity> pairs = it.next();
			if ((pairs.getValue().getId() == id) && (pairs.getValue().getData() == data)) {
				return pairs.getKey();
			}
		}
		throw new CommodityNotFoundException();
	}
}
